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

	public void loadMap(String mapPath) throws Exception {
		data.loadMap(mapPath);
		data.loadSpawn(mapPath);
		data.setTroops(data.getSpawn());
		drawOnScreen();
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

	public void setMap(ArrayList<ArrayList<Character>> troops, ArrayList<ArrayList<Character>> choosen, ArrayList<ArrayList<Character>> marked) {
		data.setTroops(troops);
		data.setChoosen(choosen);
		data.setMarked(marked);
		drawOnScreen();
	}

	public KeyCode waitForKey() throws Exception {
		return ic.getKey();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("[view] Starting graphical application");
		
		data    = new Data();
		texture = new Texture();
		ic  	= new InputController();
		oc 	= new OutputController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		loader.setController(oc);
		Parent root = loader.load();
		Scene scene = new Scene(root);

		oc.initialize();
	
		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(1000);
		//primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {ic.setKey(keyEvent);});

		/* TESTING AREA : TO BE REMOVED */
		loadMap("../maps/01_Little_Island");
		//setText(0, "This is text\nnumber 0");
		//setText(1, "This is text\nnumber 1");
		//setText(2, "This is text\nnumber 2");
		/* END OF TESTING AREA 		*/

		setStartUp(this);
	}

	public static void main (String[] args) {
		launch(args);
	}

	private void drawOnScreen() {
		ArrayList<ArrayList<Character>> mapChar     = data.getMap();
		ArrayList<ArrayList<Character>> troopsChar  = data.getTroops();
		ArrayList<ArrayList<Character>> choosenChar = data.getChoosen();
		ArrayList<ArrayList<Character>> markedChar  = data.getMarked();

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
				if (!choosenChar.isEmpty())
					choosenImg.get(lineNo).add(texture.getChoosenImage(choosenChar.get(lineNo).get(fieldNo)));
				if (!markedChar.isEmpty())
					markedImg.get(lineNo).add(texture.getMarkedImage(markedChar.get(lineNo).get(fieldNo)));
			}
		}
		frame.add(mapImg);
		frame.add(troopsImg);
		frame.add(choosenImg);
		frame.add(markedImg);
		oc.draw(frame);
	}
}

