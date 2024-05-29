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

public class graphics extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		/* TESTING AREA
		   -> TO BE REMOVED */
		
		OutputController oc = new OutputController();
		//Window objects
		//private Group root = new Group();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("prototype2.fxml"));
		loader.setController(oc);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		//canvas = new Canvas(windowX,windowY);
		//canvas = Canvas.getInstance();

		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.setScene(scene);
		primaryStage.show();

		ArrayList<ArrayList<Character>> spawn = new ArrayList<>();
		spawn = oc.loadMap("../../maps/01_Little_Island");
		oc.drawMap(spawn);

		/*this.primaryStage = primaryStage;
		graphics("THIS IS A TEXT");
		ArrayList<ArrayList<Character>> spawn = new ArrayList<>();
		spawn = loadMap("../../maps/01_Little_Island");
		
		setText(0, "THIS IS A TEST", Color.BLACK);
		setText(1, "THIS IS A TEST", Color.BLACK);
		setText(2, "THIS IS A TEST", Color.BLACK);
		setText(3, "THIS IS A TEST", Color.BLACK);
		setText(4, "THIS IS A TEST", Color.BLACK);
		drawMap(spawn);*/
	}

	public static void main (String[] args) {
		launch(args);
	}
}

