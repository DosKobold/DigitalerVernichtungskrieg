package logic;

import model.*;
import java.util.ArrayList;

public class Converter {
	public ArrayList<ArrayList<Troop>> charToTroop(ArrayList<ArrayList<Character>> troops2d)
	{
		ArrayList<ArrayList<Troop>> troops = new ArrayList<>();

		for (int line = 0; line < troops2d.size(); line++) {
			troops.add(new ArrayList<Troop>());
			for (int field = 0; field < troops2d.get(line).size(); field++) {
				switch (troops2d.get(line).get(field)) {
				case 'I':
					troops.get(line).add(new Infantry(field, line, "red"));
					break;
				case 'i':
					troops.get(line).add(new Infantry(field, line, "blue"));
					break;
				case 'M':
					troops.get(line).add(new MechanizedInfantry(field, line, "red"));
					break;
				case 'm':
					troops.get(line).add(new MechanizedInfantry(field, line, "blue"));
					break;
				case 'T':
					troops.get(line).add(new Tank(field, line, "red"));
					break;
				case 't':
					troops.get(line).add(new Tank(field, line, "blue"));
					break;
				case 'D':
					troops.get(line).add(new MobileArtillery(field, line, "red"));
					break;
				case 'd':
					troops.get(line).add(new MobileArtillery(field, line, "blue"));
					break;
				case 'A':
					troops.get(line).add(new AntiAir(field, line, "red"));
					break;
				case 'a':
					troops.get(line).add(new AntiAir(field, line, "blue"));
					break;
				case 'F':
					troops.get(line).add(new Fighter(field, line, "red"));
					break;
				case 'f':
					troops.get(line).add(new Fighter(field, line, "blue"));
					break;
				case 'B':
					troops.get(line).add(new Bomber(field, line, "red"));
					break;
				case 'b':
					troops.get(line).add(new Bomber(field, line, "blue"));
					break;
				case 'C':
					troops.get(line).add(new BattleCopter(field, line, "red"));
					break;
				case 'c':
					troops.get(line).add(new BattleCopter(field, line, "blue"));
					break;
				default:
					troops.get(line).add(null);
					break;
				}
			}
		}

		return troops;
	}

	ArrayList<ArrayList<Character>> troopToChar(ArrayList<ArrayList<Troop>> troops) {

		ArrayList<ArrayList<Character>> troops2d = new ArrayList<>();
		
		for (int line=0; line<troops.size(); line++) {
			troops2d.add(new ArrayList<Character>());
			for (int field=0; field<troops.get(line).size(); field++) {
				if (troops.get(line).get(field) == null)
					troops2d.get(line).add('_');
				else
					troops2d.get(line).add(troops.get(line).get(field).toChar());
			}
			
		}
		return troops2d;
	}
}
