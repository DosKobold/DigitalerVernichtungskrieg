/*TODO:
 * Return 2D ArrayList of spawns at loadMap(String)
 * Troop drawing
 * Scaling with the correct ratio
 * Design, especially the placement of text output
 */

//package view;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.text.Font;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class graphics extends Application {
	
	int windowX = 1000;
	int windowY =  900;

	//Window objects
	Stage primaryStage;
	Group root = new Group();
	Scene scene = new Scene(root,windowX,windowY,Color.RED);
	final Canvas canvas = new Canvas(windowX,windowY);
	GraphicsContext gc = canvas.getGraphicsContext2D();


	//Data
	private ArrayList<ArrayList<Character>> map = new ArrayList<>();
	private ArrayList<String> textStrings = new ArrayList<>();
	private ArrayList<Paint>  textColors  = new ArrayList<>();


	//Functions
	public void graphics(String text) throws Exception {
		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.show();
	}

	//TODO: return spawn?
	public void loadMap(String fileName) throws Exception {
		Scanner scanner = new Scanner(new File(fileName));
		scanner.useDelimiter(",");
		int lineNo = 0;
		map.add(new ArrayList());
		//Load map.csv into 2D-ArrayList
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				map.get(lineNo).add(field);
			}
			lineNo++;
			map.add(new ArrayList());
		}
		scanner.close();
	}

	public void drawMap() throws Exception {
		//Draw background map
		final Image plain    = new Image("file:../../textures/plain.png");
		final Image wood     = new Image("file:../../textures/wood.png");	//FIXME: Images should be placed in class, not every time loaded
		final Image mountain = new Image("file:../../textures/mountain.png");
		final Image sea      = new Image("file:../../textures/sea.png");
		for (int lineNo=0; lineNo<map.size(); lineNo++) {
			for (int fieldNo=0; fieldNo<map.get(lineNo).size(); fieldNo++) {
				char field = map.get(lineNo).get(fieldNo);
				switch (field) {
					case 'P':
						gc.drawImage(plain, 32*fieldNo, 32*lineNo);
						break;
					case 'W':
						gc.drawImage(wood, 32*fieldNo, 32*lineNo);
						break;	//FIXME: 32 should be variable to window size
					case 'M':
						gc.drawImage(mountain, 32*fieldNo, 32*lineNo);
						break;
					case 'S':
						gc.drawImage(sea, 32*fieldNo, 32*lineNo);
						break;
				}
			}
		}

		//Draw troops
		/*TODO: implement drawing troops
		 *	under construction
		*/
		
		//Draw text
		gc.setFont(new Font(20));
		for (int pos=0; pos<textStrings.size(); pos++) {
			switch (pos) {
				case 0:
					gc.setFill(textColors.get(pos));
					gc.fillText(textStrings.get(pos),10,20);
				case 1:
					gc.setFill(textColors.get(pos));
					gc.fillText(textStrings.get(pos),windowX-10,20);
				case 2:
					gc.setFill(textColors.get(pos));
					gc.fillText(textStrings.get(pos),windowX/2,windowY/2);
				case 3:
					gc.setFill(textColors.get(pos));
					gc.fillText(textStrings.get(pos),10,windowY-20);
				case 4:
					gc.setFill(textColors.get(pos));
					gc.fillText(textStrings.get(pos),windowX-10,windowY-20);
			}
		}
	}

	public void setText(int pos, String text, Paint color) {
		textStrings.add(pos, text);
		textColors.add(pos, color);
	}

	public void removeText(int pos) {
		textStrings.remove(pos);
		textColors.remove(pos);
	}

	public char getTerrain(int x, int y) {
		return map.get(y).get(x);
	}

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		graphics("THIS IS A TEXT");
		loadMap("../../maps/17_Piston_Dam/map.csv"); //FIXME: ONLY DURING NOT IMPLEMENTED
		
		setText(0, "THIS IS A TEST", Color.BLACK);
		setText(1, "THIS IS A TEST", Color.BLACK);
		setText(2, "THIS IS A TEST", Color.BLACK);
		setText(3, "THIS IS A TEST", Color.BLACK);
		setText(4, "THIS IS A TEST", Color.BLACK);
		drawMap();
	}

	public static void main (String[] args) {
		launch(args);
	}
}
