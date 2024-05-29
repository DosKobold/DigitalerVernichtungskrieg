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

public class Data {

	private ArrayList<ArrayList<Character>> map   = new ArrayList<>();
	private ArrayList<ArrayList<Character>> spawn = new ArrayList<>();
	private String[] texts = new String[3];

	public void loadMap(String mapPath ) throws Exception {
		//1. Load map.csv into 2D-ArrayList
		Scanner scanner = new Scanner(new File(mapPath + "/map.csv" ));
		int lineNo = 0;
		while(scanner.hasNextLine()) {
			map.add(new ArrayList());
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				map.get(lineNo).add(field);
				System.out.print(field);
			}
			System.out.print("\n");
			lineNo++;
		}
		scanner.close();
	}

	public ArrayList<ArrayList<Character>> getMap() {
		return map;
	}

	public void loadSpawn(String mapPath ) throws Exception {
		//2. Load spawn.csv into 2D-ArrayList and return it
		Scanner scanner = new Scanner(new File(mapPath + "/spawn.csv"));
		int lineNo = 0;
		while(scanner.hasNextLine()) {
			spawn.add(new ArrayList());
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				spawn.get(lineNo).add(field);
				System.out.print(field);
			}
			System.out.print("\n");
			lineNo++;
		}
		scanner.close();
	}

	public ArrayList<ArrayList<Character>> getSpawn() {
		return spawn;
	}

	public void setText(int pos, String text) {
		texts[pos] = text;
	}

	public void remText(int pos) {
		texts[pos] = null;
	}
}
