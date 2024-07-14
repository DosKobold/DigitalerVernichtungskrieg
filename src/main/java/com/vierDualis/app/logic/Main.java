package logic;

import view.*;
import model.*;
import javafx.application.Application;
import java.lang.Thread;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class Main {

	private static ArrayList<ArrayList<Character>> mapChar;
	private static ArrayList<ArrayList<Character>> troopsChar;
	private static ArrayList<ArrayList<Character>> markedChar = new ArrayList();
	
	private static int cursorX = 0;
	private static int cursorY = 0;
	private static Troop choosenTroop;
	private static String playerColor;

	private static ArrayList<ArrayList<Troop>> troops = new ArrayList<>();

	private static Converter converter = new Converter();
	private static MovementFinder movFinder = new MovementFinder();

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
			switch (key) {
				case DIGIT1: graphics.loadMap("/maps/01_Little_Island");
					     break exit;
				case DIGIT2: graphics.loadMap("/maps/05_Eon_Springs");
					     break exit;
				case DIGIT3: graphics.loadMap("/maps/17_Piston_Dam");
					     break exit;
			}
		}
		
		mapChar    = graphics.getMap();
		troopsChar = graphics.getSpawn();

		graphics.clearText();

		troops = converter.charToTroop(troopsChar);

		for (int run=0; ; run++) {
			turn(run % 2);
		}
	}

	private static void turn(int player) throws Exception  {
		ArrayList<ArrayList<Character>> choosenChar = new ArrayList<>();
		choosenTroop = null;

		if (player == 0) playerColor = "red";
		else 	     playerColor = "blue";

		System.out.println("[logic] " + "It is " + playerColor + "'s turn");

		for (int line=0; line<mapChar.size(); line++) {
			choosenChar.add(new ArrayList<Character>());
			for (int field=0; field<mapChar.get(line).size(); field++) {
				choosenChar.get(line).add('_');
				if (troops.get(line).get(field) != null)
					troops.get(line).get(field).setAttack(1);
			}
		}

		exit:
		for (;;) {
			//Output
			troopsChar = converter.troopToChar(troops);

			graphics.setText(0, "Spieler:\n" + ((playerColor.equals("red")) ? "Rot" : "Blau"));
			
			for (int i=0; i<mapChar.size(); i++) {
				for (int n=0; n<mapChar.get(i).size(); n++) {
					choosenChar.get(i).set(n, '_');
				}
			}
			choosenChar.get(cursorY).set(cursorX, 'X');
			markedChar = movFinder.stupidMovementRange(troops, choosenTroop);
			graphics.setMap(troopsChar, choosenChar, markedChar);

			//Input
			KeyCode key = graphics.waitForKey();
			graphics.setText(2, "");
			switch (key) {
				case UP: 	if (cursorY > 0) cursorY -= 1;
					 	break;
				case DOWN:	if (cursorY < mapChar.size()-1) cursorY += 1;
					   	break;
				case RIGHT: 	if (cursorX < mapChar.get(0).size()-1) cursorX += 1;
					    	break;
				case LEFT:  	if (cursorX > 0) cursorX -= 1;
					     	break;
				case SPACE:	interact();
						break;
				case Q:		choosenTroop = null;
						graphics.setText(1, "");
						break;
				case ENTER:	graphics.clearText();
						break exit;
			}
		}
	}

	private static void interact() throws Exception {
		Troop cursorTroop = troops.get(cursorY).get(cursorX);
		if (cursorTroop != null) {
			if (choosenTroop == null) {
				if (playerColor.equals(cursorTroop.getColor())) {
					//Choose own troop
					choosenTroop = cursorTroop;
					graphics.setText(1, "Eigene Truppe:\n" + choosenTroop.toString() + "\nHP: " + choosenTroop.getHp());
					System.out.println("[logic] " + choosenTroop.getColor() + " " + choosenTroop.getClass().getSimpleName() + " choosen");
				}
			} else  if (!(playerColor.equals(cursorTroop.getColor()))) {
				//Choose enemy troop
				graphics.setText(2, "Fremde Truppe:\n" + cursorTroop.toString() + "\nHP: " + cursorTroop.getHp());
				System.out.println("[logic] " + cursorTroop.getColor() + " " + cursorTroop.getClass().getSimpleName() + " choosen");
				if ((choosenTroop.getX()>=cursorTroop.getX()-1) && (choosenTroop.getX()<=cursorTroop.getX()+1) && (choosenTroop.getY()>=cursorTroop.getY()-1) && (choosenTroop.getY()<=cursorTroop.getY()+1) && (choosenTroop.getAttack()==1)) {
					//Attack enemy troop
					graphics.setText(0, "Spieler:\n" + ((playerColor.equals("red")) ? "Rot" : "Blau") + "\nAngreifen? [J]");
					if (graphics.waitForKey() == KeyCode.J) {
						choosenTroop.setAttack(0);
						choosenTroop.attack(cursorTroop);
						graphics.setText(2, "Fremde Truppe:\n" + cursorTroop.toString() + "\nHP: " + cursorTroop.getHp());
						if (cursorTroop.getHp() < 1) {
							//Enemy troop got killed
							System.out.println("[logic] " + cursorTroop.getColor() + " " + cursorTroop.getClass().getSimpleName() + " got killed");
							troops.get(cursorTroop.getY()).set(cursorTroop.getX(), null);
							graphics.setText(2, "Fremde Truppe:\nVernichtet");
						}
					}
				}
			}
		} else if (choosenTroop != null) {
			//Replace the troop
			replace(choosenTroop, cursorX, cursorY);
		}
	}

	private static void replace(Troop troop, int posX, int posY) {
		troops.get(troop.getY()).set(troop.getX(),null);
		System.out.println("[logic] " + choosenTroop.getColor() + " " + choosenTroop.getClass().getSimpleName() + " replaced ((" + troop.getX() + "|" + troop.getY() + ") -> (" + posX + "|" + posY + "))");
		troop.setX(posX);
		troop.setY(posY);
		troops.get(posY).set(posX, troop);
	}
}
