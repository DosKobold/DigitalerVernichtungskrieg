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
import javafx.fxml.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class Data {

	private ArrayList<ArrayList<Character>> map     = new ArrayList<>();
	private ArrayList<ArrayList<Character>> spawn   = new ArrayList<>();
	private ArrayList<ArrayList<Character>> troops  = new ArrayList<>();
	private ArrayList<ArrayList<Character>> choosen = new ArrayList<>();
	private ArrayList<ArrayList<Character>> marked  = new ArrayList<>();
	private String[] texts = new String[3];

	public void loadMap(String mapPath ) throws Exception {
		mapPath = mapPath + "/map.csv";
		System.out.println("[view] Following map path is given:\n\t" + mapPath 
			+ "\n[view] Following map is loaded into memory:");
		map = readCsv(mapPath);
	}

	public ArrayList<ArrayList<Character>> getMap() {
		return map;
	}

	public void loadSpawn(String mapPath ) throws Exception {
		mapPath = mapPath + "/spawn.csv";
		System.out.println("[view] Following troop spawn path is given:\n\t" + mapPath 
			+ "\n[view] Following troop spawn is loaded into memory:");
		spawn = readCsv(mapPath);
	}

	public ArrayList<ArrayList<Character>> getSpawn() {
		return spawn;
	}

	public void setTroops(ArrayList<ArrayList<Character>> list) {
		troops = list;
	}

	public ArrayList<ArrayList<Character>> getTroops() {
		return troops;
	}

	public void setChoosen(ArrayList<ArrayList<Character>> list) {
		choosen = list;
	}

	public ArrayList<ArrayList<Character>> getChoosen() {
		return choosen;
	}

	public void setMarked(ArrayList<ArrayList<Character>> list) {
		marked = list;
	}

	public ArrayList<ArrayList<Character>> getMarked() {
		return marked;
	}

	public void setText(int pos, String text) {
		texts[pos] = text;
	}

	public void remText(int pos) {
		texts[pos] = null;
	}
	
	public String[] getText() {
		return texts;
	}

	private ArrayList<ArrayList<Character>> readCsv(String filePath) throws Exception {
		ArrayList<ArrayList<Character>> list = new ArrayList<>();
		Scanner scanner = new Scanner(new File(filePath));
		int lineNo = 0;
		while(scanner.hasNextLine()) {
			System.out.print("\t");
			list.add(new ArrayList());
			String line = scanner.nextLine().replaceAll(",","");
			for (char field : line.toCharArray()) {
				list.get(lineNo).add(field);
				System.out.print(field);
			}
			System.out.print("\n");
			lineNo++;
		}
		scanner.close();
		return list;
	}

}
