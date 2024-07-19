package logic;

import view.*;
import model.*;
import java.util.*;

public class MovementFinder {

	/*
	 * The movement finder is unfinished. It should be there to mark all possible fields
	 * and only allow moves to these fields.
	 */

	public static ArrayList<ArrayList<Character>> stupidMovementRange(ArrayList<ArrayList<Character>> map, ArrayList<ArrayList<Character>> troops, Troop current) {
		int line, field;
		Converter converter = new Converter();
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();
		ArrayList<ArrayList<Troop>> trooplist = converter.charToTroop(troops);

		for (line=0; line<troops.size(); line++) {
			marked.add(new ArrayList<Character>());
			for (field=0; field<troops.get(line).size(); field++) {
				marked.get(line).add('_');
			}
		}


		exit:
		for (line=0; line<troops.size(); line++) {
			for (field=0; field<troops.get(line).size(); field++) {
				if (trooplist.get(line).get(field).getX() == current.getX() &&
				    trooplist.get(line).get(field).getY() == current.getY()) {
					break exit;
				}
			}
		}

		// Now search around the current player
		

		return marked;
	}

	private static void searchAroundField(ArrayList<ArrayList<Character>> map, int posX, int posY) {
		// look left
		
		// look left up

		// look left down

		// look down

		// look right down

		// look right

		// look right up

		// look up
	}

	private static int getTerrainRange(char troop, char terrain) {
		switch (troop) {
		case 'I':
		case 'i':
			switch (terrain) {
			case 'S':
				return 0;
			case 'P':
				return 1;
			case 'W':
				return 1;
			case 'M':
				return 2;
			default:
				return 0;
			}
		case 'M':
		case 'm':
			switch (terrain) {
			case 'S':
				return 0;
			case 'P':
				return 1;
			case 'W':
				return 1;
			case 'M':
				return 1;
			default:
				return 0;
			}
		case 'T':
		case 't':
			switch (terrain) {
			case 'S':
				return 0;
			case 'P':
				return 1;
			case 'W':
				return 2;
			case 'M':
				return 0;
			default:
				return 0;
			}
		case 'D':
		case 'd':
			switch (terrain) {
			case 'S':
				return 0;
			case 'P':
				return 1;
			case 'W':
				return 2;
			case 'M':
				return 0;
			default:
				return 0;
			}
		case 'A':
		case 'a':
			switch (terrain) {
			case 'S':
				return 0;
			case 'P':
				return 1;
			case 'W':
				return 2;
			case 'M':
				return 0;
			default:
				return 0;
			}
		case 'F':
		case 'f':
			switch (terrain) {
			case 'S':
				return 1;
			case 'P':
				return 1;
			case 'W':
				return 1;
			case 'M':
				return 1;
			default:
				return 0;
			}
		case 'B':
		case 'b':
			switch (terrain) {
			case 'S':
				return 1;
			case 'P':
				return 1;
			case 'W':
				return 1;
			case 'M':
				return 1;
			default:
				return 0;
			}
		case 'C':
		case 'c':
			switch (terrain) {
			case 'S':
				return 1;
			case 'P':
				return 1;
			case 'W':
				return 1;
			case 'M':
				return 1;
			default:
				return 0;
			}
		default:
			return 0;
		}
	}
}
