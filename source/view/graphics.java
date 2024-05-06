//package view;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class graphics extends Application {
	
	private ArrayList<ArrayList<Character>> map = new ArrayList<>();

	//TODO: return spawn?
	public void loadMap(String fileName) throws Exception {
		Scanner scanner = new Scanner(new File("../../maps/01_Little_Island/map.csv"));
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

	public void start(Stage primaryStage) throws Exception {
		loadMap("TEst"); //FIXME: ONLY DURING NOT IMPLEMENTED
		Group root = new Group();
		Scene scene = new Scene(root,1000,1000,Color.PINK);

		final Canvas canvas = new Canvas(1000,1000);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
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
						break;						//FIXME: 32 should be variable to window size
					case 'M':
						gc.drawImage(mountain, 32*fieldNo, 32*lineNo);
						break;
					case 'S':
						gc.drawImage(sea, 32*fieldNo, 32*lineNo);
						break;
				}
			}
		}
		
		root.getChildren().add(canvas);
	
		primaryStage.setScene(scene);
		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.show();
	}

	public static void main (String[] args) {
		launch(args);
	}

}
