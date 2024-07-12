package logic;

import view.*;
import model.*;
import javafx.application.Application;
import java.lang.Thread;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class Main {

	private static ArrayList<ArrayList<Character>> map2d;
	private static ArrayList<ArrayList<Character>> troops2d;
	
	private static ArrayList<ArrayList<Troop>> troops = new ArrayList<>();

	private static Converter converter = new Converter();

	private static Graphics graphics;

	public static void main (String[] args) throws Exception {
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(view.Graphics.class);
			}
		}.start();
		graphics = view.Graphics.waitForStartUp();
		
		graphics.setText(0, "\n[1] Little Island");
		graphics.setText(1, "Choose your map\n[2] Eon Springs");
		graphics.setText(2, "\n[3] Piston Dam");

		exit:
		for (;;) {
			KeyCode key = graphics.waitForKey();
			System.out.println(key);
			switch (key) {
				case DIGIT1: graphics.loadMap("/maps/01_Little_Island");
					     break exit;
				case DIGIT2: graphics.loadMap("/maps/05_Eon_Springs");
					     break exit;
				case DIGIT3: graphics.loadMap("/maps/17_Piston_Dam");
					     break exit;
			}
		}
		
		map2d    = graphics.getMap();
		troops2d = graphics.getSpawn();

		graphics.clearText();


		troops = converter.charToTroop(troops2d);

		System.out.println(troops.size());

		for (int i=0; i<troops.size(); i++) {
			System.out.println(troops.get(i).getColor());
		}

		for (int run=0;;) {
			turn(run % 2);
		}
	}

	private static void turn(int player) throws Exception  {
		int cursorX = 0;
		int cursorY = 0;
		ArrayList<ArrayList<Character>> choosen2d = new ArrayList<>();

		for (int i=0; i<map2d.size(); i++) {
			choosen2d.add(new ArrayList<Character>());
			for (int n=0; n<map2d.get(i).size(); n++) {
				choosen2d.get(i).add('_');
			}
		}

		for (;;) {
			//Output
			graphics.setText(1, "BLABLA");
			troops2d = converter.troopToChar(troops, troops2d.get(0).size(), troops2d.size());
			
			for (int i=0; i<map2d.size(); i++) {
				for (int n=0; n<map2d.get(i).size(); n++) {
					choosen2d.get(i).set(n, '_');
				}
			}
			choosen2d.get(cursorY).set(cursorX, 'X');
			//TODO: marked arraylist
			graphics.setMap(troops2d, choosen2d, choosen2d);

			//Input
			KeyCode key = graphics.waitForKey();
			System.out.println(key);
			switch (key) {
				case UP: if (cursorY > 0) cursorY -= 1;
					 break;
				case DOWN: if (cursorY < map2d.size()-1) cursorY += 1;
					   break;
				case RIGHT: if (cursorX < map2d.get(0).size()-1) cursorX += 1;
					    break;
				case LEFT:  if (cursorX > 0) cursorX -= 1;
					     break;
			}
		}
	}
}
