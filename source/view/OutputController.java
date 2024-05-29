
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
import java.net.URL;
import java.util.ResourceBundle;

public class OutputController implements Initializable {

	//Settings
	private int windowX = 1000;
	private int windowY =  900;

	//Window objects
	private Stage primaryStage;
	//private Group root = new Group();
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;
	//Data
	private ArrayList<ArrayList<Character>> map = new ArrayList<>();
	private ArrayList<String> textStrings = new ArrayList<>();
	private ArrayList<Paint>  textColors  = new ArrayList<>();

	//Textures
	private String imagePath = "file:../../textures/";
	private final Image plain    		= new Image(imagePath + "map/plain.png");
	private final Image wood     		= new Image(imagePath + "map/wood.png");
	private final Image mountain 		= new Image(imagePath + "map/mountain.png");
	private final Image sea      		= new Image(imagePath + "map/sea.png");
	private final Image infantry 		= new Image(imagePath + "troops/infantry.png");
	private final Image mechanizedInfantry  = new Image(imagePath + "troops/mechanizedInfantry.png");
	private final Image tank 		= new Image(imagePath + "troops/tank.png");
	private final Image mobileArtillery 	= new Image(imagePath + "troops/mobileArtillery.png");
	private final Image antiAir		= new Image(imagePath + "troops/antiAir.png");
	private final Image fighter 		= new Image(imagePath + "troops/fighter.png");
	private final Image bomber 		= new Image(imagePath + "troops/bomber.png");
	private final Image battleCopter 	= new Image(imagePath + "troops/battleCopter.png");

	//Functions
	@FXML
	public void graphics() throws Exception {
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	public ArrayList<ArrayList<Character>> loadMap(String mapPath) throws Exception {
		//1. Load map.csv into 2D-ArrayList
		Scanner scanner = new Scanner(new File(mapPath + "/map.csv" ));
		int lineNo = 0;
		map.add(new ArrayList());
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				map.get(lineNo).add(field);
			}
			lineNo++;
			map.add(new ArrayList());
		}
		scanner.close();
		
		//2. Load spawn.csv into 2D-ArrayList and return it
		scanner = new Scanner(new File(mapPath + "/spawn.csv"));
		lineNo = 0;
		ArrayList<ArrayList<Character>> spawn = new ArrayList<>();
		spawn.add(new ArrayList());
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				spawn.get(lineNo).add(field);
				System.out.print(field);
			}
			System.out.print("\n");
			lineNo++;
			spawn.add(new ArrayList());
		}
		scanner.close();
		return spawn;
	}

	@FXML
	public void drawMap(ArrayList<ArrayList<Character>> spawn) throws Exception {
		gc = canvas.getGraphicsContext2D();

		//1. Draw background map
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

		//2. Draw troops
		for (int lineNo=0; lineNo<spawn.size(); lineNo++) {
			for (int fieldNo=0; fieldNo<spawn.get(lineNo).size(); fieldNo++) {
				char field = spawn.get(lineNo).get(fieldNo);
				switch (field) {
					case 'I':
						gc.drawImage(infantry, 32*fieldNo, 32*lineNo);
						break;
					case 'i':
						gc.drawImage(infantry, 32*fieldNo, 32*lineNo);
						break;
					case 'M':
						gc.drawImage(mechanizedInfantry, 32*fieldNo, 32*lineNo);
						break;
					case 'm':
						gc.drawImage(mechanizedInfantry, 32*fieldNo, 32*lineNo);
						break;
					case 'T':
						gc.drawImage(tank, 32*fieldNo, 32*lineNo);
						break;
					case 't':
						gc.drawImage(tank, 32*fieldNo, 32*lineNo);
						break;
					case 'D':
						gc.drawImage(mobileArtillery, 32*fieldNo, 32*lineNo);
						break;
					case 'd':
						gc.drawImage(mobileArtillery, 32*fieldNo, 32*lineNo);
						break;
					case 'A':
						gc.drawImage(antiAir, 32*fieldNo, 32*lineNo);
						break;
					case 'a':
						gc.drawImage(antiAir, 32*fieldNo, 32*lineNo);
						break;
					case 'F':
						gc.drawImage(fighter, 32*fieldNo, 32*lineNo);
						break;
					case 'f':
						gc.drawImage(fighter, 32*fieldNo, 32*lineNo);
						break;
					case 'B':
						gc.drawImage(bomber, 32*fieldNo, 32*lineNo);
						break;
					case 'b':
						gc.drawImage(bomber, 32*fieldNo, 32*lineNo);
						break;
					case 'C':
						gc.drawImage(battleCopter, 32*fieldNo, 32*lineNo);
						break;
					case 'c':
						gc.drawImage(battleCopter, 32*fieldNo, 32*lineNo);
						break;
				}
			}
		}

		//3. Draw marked fields + choosen field

		//4. Draw user interface
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//canvas = Canvas.getInstance();
		canvas = this.canvas;
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(1.0, 1.0, 80.0, 80.0);
	}
}
