package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Graphics extends Application {

	public static final CountDownLatch latch = new CountDownLatch(1);
	public static Graphics graphics = null;
	
	private Data data;
	private Texture texture;
	private InputController ic;
	private OutputController oc;

	private KeyCode keyCode;
	
	public static Graphics waitForStartUp() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return graphics;
	}

	public static void setStartUp(Graphics graphics0) {
		graphics = graphics0;
		latch.countDown();
	}

	public void loadMap(String mapPath) {
		data.loadMap(mapPath);
		data.loadSpawn(mapPath);
		data.setTroops(data.getSpawn());
		drawWithCatch(true);
	}

	public ArrayList<ArrayList<Character>> getMap() {
		return data.getMap();
	}
	
	public ArrayList<ArrayList<Character>> getSpawn() {
		return data.getSpawn();
	}

	public void setText(int pos, String text) {
		data.remText(pos);
		data.setText(pos, text);
		oc.drawText(data.getText());
	}

	public void clearText() {
		for (int i=0; i<3; i++) {
			setText(i, null);
		}
	}

	public void setMap(ArrayList<ArrayList<Character>> troops, ArrayList<ArrayList<Character>> choosen, ArrayList<ArrayList<Character>> marked) {
		data.setTroops(troops);
		data.setChoosen(choosen);
		data.setMarked(marked);
		drawWithCatch(false);
	}

	public KeyCode waitForKey() {
		try {
			return ic.getKey();
		} catch (InterruptedException e) {
			System.out.println("[view] WARNING! Waiting for key press got interrupted! Returning null...");
			return null;
		}
	}

	@Override
	public void start(Stage primaryStage) throws StartException {
		System.out.println("[view] Starting graphical application");
		
		data    = new Data();
		texture = new Texture();
		ic  	= new InputController();
		oc 	= new OutputController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
		loader.setController(oc);
		Parent root = null;
		try {
			root = loader.load();
		} catch (Exception e) {
			System.out.println("[view] ERROR: Could not load FXML file!");
			throw new StartException("[view] ERROR: Could not start graphical application!");
		}
		Scene scene = new Scene(root);

		oc.initialize();
	
		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(1000);
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {ic.setKey(keyEvent);});

		setStartUp(this);
	}

	private void drawWithCatch(Boolean firstRun) {
		try {
			drawOnScreen(firstRun);
		} catch (MapSizeException e) {
			System.out.println("[view] WARNING! The frame layers have different dimensions or are not rectangles. Can not continue!");
			clearText();
			setText(1, "Warning!\nMap error\nSee logs\nCan not continue!");
			try {
				TimeUnit.DAYS.sleep(1); 
			} catch (InterruptedException f) {
				System.out.println("[view] ERROR! Sleep process interrupted. Terminating application...");
				System.exit(1);
			}
		}
}

	private void drawOnScreen(Boolean firstRun) throws MapSizeException {
		ArrayList<ArrayList<Character>> mapChar     = data.getMap();
		ArrayList<ArrayList<Character>> troopsChar  = data.getTroops();
		ArrayList<ArrayList<Character>> choosenChar = data.getChoosen();
		ArrayList<ArrayList<Character>> markedChar  = data.getMarked();

		try {
			int fields = mapChar.get(0).size();
			for (int lineNo=0; lineNo<mapChar.size(); lineNo++) {
				if (fields != mapChar.get(lineNo).size() || fields != troopsChar.get(lineNo).size())
					throw new MapSizeException();
				if (!firstRun)
					if (fields != choosenChar.get(lineNo).size() || fields != markedChar.get(lineNo).size())
						throw new MapSizeException();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new MapSizeException();
		}

		ArrayList<ArrayList<Image>> mapImg     = new ArrayList<>();
		ArrayList<ArrayList<Image>> troopsImg  = new ArrayList<>();
		ArrayList<ArrayList<Image>> choosenImg = new ArrayList<>();
		ArrayList<ArrayList<Image>> markedImg  = new ArrayList<>();

		ArrayList<ArrayList<ArrayList<Image>>> frame = new ArrayList<>();

		for (int lineNo=0; lineNo<mapChar.size(); lineNo++) {
			mapImg.add(new ArrayList<Image>());
			troopsImg.add(new ArrayList<Image>());
			choosenImg.add(new ArrayList<Image>());
			markedImg.add(new ArrayList<Image>());
			for (int fieldNo=0; fieldNo<mapChar.get(lineNo).size(); fieldNo++) {
				mapImg.get(lineNo).add(texture.getMapImage(mapChar.get(lineNo).get(fieldNo)));
				troopsImg.get(lineNo).add(texture.getTroopImage(troopsChar.get(lineNo).get(fieldNo)));
				if (!markedChar.isEmpty())
					markedImg.get(lineNo).add(texture.getMarkedImage(markedChar.get(lineNo).get(fieldNo)));
				if (!choosenChar.isEmpty())
					choosenImg.get(lineNo).add(texture.getChoosenImage(choosenChar.get(lineNo).get(fieldNo)));
			}
		}

		frame.add(mapImg);
		frame.add(troopsImg);
		frame.add(choosenImg);
		frame.add(markedImg);
		oc.draw(frame);
	}
}

