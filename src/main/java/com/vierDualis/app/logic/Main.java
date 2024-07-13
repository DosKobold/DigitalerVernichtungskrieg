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

		for (int run=0; ; run++) {
			turn(run % 2);
		}
	}

	private static void turn(int player) throws Exception  {
		int cursorX = 0;
		int cursorY = 0;
		ArrayList<ArrayList<Character>> choosen2d = new ArrayList<>();
		Troop choosenTroop = null;
		String playerColor;
		if (player == 0) playerColor = "red";
		else 	     playerColor = "blue";

		//System.out.println(playerColor.equals("red"));

		graphics.setText(0, "Spieler:\n" + ((playerColor.equals("red")) ? "Rot" : "Blau"));

		for (int i=0; i<map2d.size(); i++) {
			choosen2d.add(new ArrayList<Character>());
			for (int n=0; n<map2d.get(i).size(); n++) {
				choosen2d.get(i).add('_');
			}
		}

		exit:
		for (;;) {
			//Output
			troops2d = converter.troopToChar(troops);
			
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
				case UP: 	if (cursorY > 0) cursorY -= 1;
					 	break;
				case DOWN:	if (cursorY < map2d.size()-1) cursorY += 1;
					   	break;
				case RIGHT: 	if (cursorX < map2d.get(0).size()-1) cursorX += 1;
					    	break;
				case LEFT:  	if (cursorX > 0) cursorX -= 1;
					     	break;
				case SPACE:	if (troops.get(cursorY).get(cursorX) != null && choosenTroop == null) {
							choosenTroop = troops.get(cursorY).get(cursorX);
							graphics.setText(1, choosenTroop.toString() + "\n" + choosenTroop.getHp());
						} else if (troops.get(cursorY).get(cursorX) == null && choosenTroop != null) {
							replace(choosenTroop, cursorX, cursorY);
							choosenTroop = null;
							graphics.setText(1,"");
						}
						break;
				case Q:		choosenTroop = null;
						graphics.setText(1, "");
						break;
				case ENTER:	graphics.clearText();
						break exit;
			}
		}
	}

	private static void replace(Troop troop, int posX, int posY) {
		troops.get(troop.getY()).set(troop.getX(),null);
		troop.setX(posX);
		troop.setY(posY);
		troops.get(posY).set(posX, troop);
	}
}
