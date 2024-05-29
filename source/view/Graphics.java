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
import javafx.fxml.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Graphics extends Application {

	private Data data = new Data();
	private Texture texture = new Texture();
	private OutputController oc = new OutputController();
	
	private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

	public void loadMap(String mapPath) throws Exception {
		data.loadMap(mapPath);
		data.loadSpawn(mapPath);
		
		ArrayList<ArrayList<Character>> map   = getMap();
		ArrayList<ArrayList<Character>> spawn = getSpawn();
		for (int lineNo=0; lineNo<map.size(); lineNo++) {
			fields.add(new ArrayList());
			for (int fieldNo=0; fieldNo<map.get(lineNo).size(); fieldNo++) {
				fields.get(lineNo).add(new Field());
				fields.get(lineNo).get(fieldNo).setBackground(texture.getMapImage(map.get(lineNo).get(fieldNo)));
				fields.get(lineNo).get(fieldNo).setForeground(texture.getTroopImage(spawn.get(lineNo).get(fieldNo)));
			}
		}
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
	}

	public void setField(int xPos, int yPos, char troop, boolean choosen, boolean marked) {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("prototype2.fxml"));
		loader.setController(oc);
		Parent root = loader.load();
		Scene scene = new Scene(root);

		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.setScene(scene);
		primaryStage.show();

		/* TESTING AREA : TO BE REMOVED */
		loadMap("../../maps/01_Little_Island");
		/* END OF TESTING AREA 		*/
	}

	public static void main (String[] args) {
		launch(args);
	}

	private void drawOnScreen() {
		for (int lineNo=0; lineNo<fields.size(); lineNo++) {
			for (int fieldNo=0; fieldNo<fields.get(lineNo).size(); fieldNo++) {
				Field field = fields.get(lineNo).get(fieldNo);
				oc.drawField(fieldNo, lineNo, field.getBackground());
				oc.drawField(fieldNo, lineNo, field.getForeground());
				if (field.isChoosen())
					oc.markAsChoosen(fieldNo, lineNo);
				if (field.isMarked())
					oc.markAsMarked(fieldNo, lineNo);
			}
		}

	}
}

