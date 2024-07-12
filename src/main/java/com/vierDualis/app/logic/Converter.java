package logic;

import model.*;
import java.util.ArrayList;

public class Converter {
	public ArrayList<ArrayList<Troop>> charToTroop(ArrayList<ArrayList<Character>> troops2d)
	{
		ArrayList<ArrayList<Troop>> troops = new ArrayList<>();

		for (int line = 0; line < troops2d.size(); ++line) {
			for (int field = 0; field < troops2d.get(line).size(); ++field) {
				switch (troops2d.get(line).get(field)) {
				case 'I':
					troops.add(new Infantry(field, line, "red"));
					break;
				case 'i':
					troops.add(new Infantry(field, line, "blue"));
					break;
				case 'M':
					troops.add(new MechanizedInfantry(field, line, "red"));
					break;
				case 'm':
					troops.add(new MechanizedInfantry(field, line, "blue"));
					break;
				case 'T':
					troops.add(new Tank(field, line, "red"));
					break;
				case 't':
					troops.add(new Tank(field, line, "blue"));
					break;
				case 'D':
					troops.add(new MobileArtillery(field, line, "red"));
					break;
				case 'd':
					troops.add(new MobileArtillery(field, line, "blue"));
					break;
				case 'A':
					troops.add(new AntiAir(field, line, "red"));
					break;
				case 'a':
					troops.add(new AntiAir(field, line, "blue"));
					break;
				case 'F':
					troops.add(new Fighter(field, line, "red"));
					break;
				case 'f':
					troops.add(new Fighter(field, line, "blue"));
					break;
				case 'B':
					troops.add(new Bomber(field, line, "red"));
					break;
				case 'b':
					troops.add(new Bomber(field, line, "blue"));
					break;
				case 'C':
					troops.add(new BattleCopter(field, line, "red"));
					break;
				case 'c':
					troops.add(new BattleCopter(field, line, "blue"));
					break;
				default:
					troops.add(null);
					break;
				}
			}
		}

		return troops;
	}

	ArrayList<ArrayList<Character>> troopToChar(ArrayList<ArrayList<Troop>> troops, int sizeX, int sizeY) {

		ArrayList<ArrayList<Character>> troops2d = new ArrayList<>();
		
		for (int i=0; i<sizeY; i++) {
			troops2d.add(new ArrayList<Character>());
			for (int n=0; n<sizeX; n++) {
				troops2d.get(i).add('_');
			}
		}

		for (int i=0; i<troops.size(); i++) {
			for (int n=0; n<troops.get(i).size(); n++) {
				Character troop = troops.get(i).get(n).toChar();
				int xPos  = troops.get(i).get(n).getX();
				int yPos  = troops.get(i).get(n).getY();
			
				troops2d.get(yPos).set(xPos, troop);
			}
			
		}
		return troops2d;
	}
}
