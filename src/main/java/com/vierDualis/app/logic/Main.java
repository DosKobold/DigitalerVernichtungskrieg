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
	
	private static ArrayList<Troop> troops = new ArrayList<>();

	private static Converter converter = new Converter();

	public static void main (String[] args) throws Exception {
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(view.Graphics.class);
			}
		}.start();
		view.Graphics graphics = view.Graphics.waitForStartUp();
		
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


		troops = converter.convertTroops(troops2d);

		System.out.println(troops.size());

		for (int i=0; i<troops.size(); i++) {
			System.out.println(troops.get(i).getColor());
		}

		/*for (int lineNo=0; lineNo<troops2d.size(); lineNo++) {
			for (int fieldNo=0; fieldNo<troops2d.get(lineNo).size(); fieldNo++) {
				
			}
		}
		*/

		for (int run=0;;) {
			turn(run % 2);
		}
	}

	private void turn(player) {
		//Output
		graphics.setText(1, "BLABLA");
		converter.
		//Input
	}
}
