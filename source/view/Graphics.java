/*TODO:
 * Scaling with the correct ratio
 * 	-> Squares needed, maybe alltime border
 * Good UI (with good text placement)
 * Print marked fields (new argument in drawMap())
 * Console output
 * Input control (new method, which waits for input)
 * Better structure
 * Error handling. Example: Error while loading some image
 */

package view;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.text.Font;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.fxml.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Graphics extends Application {
	
	private Data data = new Data();
	private Texture texture = new Texture();
	private OutputController oc = new OutputController();

	private KeyCode keyCode;
	
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

	private synchronized void setKey(KeyEvent keyEvent) {
		keyCode = keyEvent.getCode();
		System.out.println("[view] Key \"" + keyCode.getName() + "\" was pressed");
		notifyAll();
	}

	public synchronized KeyCode getKey() throws Exception {
		wait();
		KeyCode keyCode = this.keyCode;
		this.keyCode = null;
		return keyCode;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
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

		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {setKey(keyEvent);});

		/* TESTING AREA : TO BE REMOVED */
		loadMap("../../maps/01_Little_Island");
		setText(0, "This is text\nnumber 0");
		setText(1, "This is text\nnumber 1");
		setText(2, "This is text\nnumber 2");
		/* END OF TESTING AREA 		*/
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
			mapImg.add(new ArrayList());
			troopsImg.add(new ArrayList());
			choosenImg.add(new ArrayList());
			markedImg.add(new ArrayList());
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

