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
		marked = searchAroundField(map, current);

		return marked;
	}

	private static ArrayList<ArrayList<Character>> searchAroundField(ArrayList<ArrayList<Character>> map, Troop current) {
		int range;
		int posX = current.getX();
		int posY = current.getY();
		int movRange = current.getMovementRange();
		char troop = current.toChar();
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();

		for (int line=0; line<map.size(); line++) {
			marked.add(new ArrayList<Character>());
			for (int field=0; field<map.get(line).size(); field++) {
				marked.get(line).add('_');
			}
		}

		// look left
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX-i > 0) {
				range -= getTerrainRange(troop, map.get(posY).get(posX-i));
				if (range >= 0) {
					marked.get(posY).set(posX-i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look left up
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX-i > 0 && posY-i > 0) {
				range -= getTerrainRange(troop, map.get(posY-i).get(posX-i));
				if (range >= 0) {
					marked.get(posY-i).set(posX-i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look left down
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX-i > 0 && posY+i < map.size()) {
				range -= getTerrainRange(troop, map.get(posY+i).get(posX-i));
				if (range >= 0) {
					marked.get(posY+i).set(posX-i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look down
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posY+i > map.size()) {
				range -= getTerrainRange(troop, map.get(posY+i).get(posX));
				if (range >= 0) {
					marked.get(posY+i).set(posX, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look right down
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX+i < map.get(0).size() && posY+i < map.size()) {
				range -= getTerrainRange(troop, map.get(posY+i).get(posX+i));
				if (range >= 0) {
					marked.get(posY+i).set(posX+i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look right
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX+i < map.get(0).size()) {
				range -= getTerrainRange(troop, map.get(posY).get(posX+i));
				if (range >= 0) {
					marked.get(posY).set(posX+i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look right up
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posX+i < map.get(0).size() && posY-i > 0) {
				range -= getTerrainRange(troop, map.get(posY-i).get(posX+i));
				if (range >= 0) {
					marked.get(posY-i).set(posX+i, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// look up
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posY-i > 0) {
				range -= getTerrainRange(troop, map.get(posY-i).get(posX));
				if (range >= 0) {
					marked.get(posY-i).set(posX, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		return marked;
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
