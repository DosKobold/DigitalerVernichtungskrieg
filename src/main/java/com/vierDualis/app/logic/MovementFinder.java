package logic;

import view.*;
import model.*;
import java.util.*;

public class MovementFinder {

	public static ArrayList<ArrayList<Character>> movementRange(ArrayList<ArrayList<Character>> map, ArrayList<ArrayList<Character>> troops, Troop current) {
		Converter converter = new Converter();
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();
		ArrayList<ArrayList<Troop>> trooplist = converter.charToTroop(troops);

		for (int line=0; line<map.size(); line++) {
			marked.add(new ArrayList<Character>());
			for (int field=0; field<map.get(line).size(); field++) {
				marked.get(line).add('_');
			}
		}

		// Now search around the current player
		if (current != null) {
			marked = searchAroundField(map, current);
		}

		return marked;
	}

	private static ArrayList<ArrayList<Character>> searchAroundField(ArrayList<ArrayList<Character>> map, Troop current) {
		int ret = 0;
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

		// look left up
		for (int k=0; k<movRange; k++) {
			range = movRange;
			for (int i=1; range>0; i++) {
				if (posX-i >= 0 && posY-k >= 0) {
					ret = getTerrainRange(troop, map.get(posY-k).get(posX-i));
					if (ret == 0) {
						break;
					}
					range -= ret;
					if (range >= 0) {
						marked.get(posY-k).set(posX-i, 'X');
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}

		// look left down
		for (int k=0; k<movRange; k++) {
			range = movRange;
			for (int i=1; range>0; i++) {
				if (posX-i >= 0 && posY+k < map.size()) {
					ret = getTerrainRange(troop, map.get(posY+k).get(posX-i));
					if (ret == 0) {
						break;
					}
					range -= ret;
					if (range >= 0) {
						marked.get(posY+k).set(posX-i, 'X');
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}

		// look right up
		for (int k=0; k<movRange; k++) {
			range = movRange;
			for (int i=1; range>0; i++) {
				if (posX+i < map.get(0).size() && posY-k >= 0) {
					ret = getTerrainRange(troop, map.get(posY-k).get(posX+i));
					if (ret == 0) {
						break;
					}
					range -= ret;
					if (range >= 0) {
						marked.get(posY-k).set(posX+i, 'X');
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}

		// look right down
		for (int k=0; k<movRange; k++) {
			range = movRange;
			for (int i=1; range>0; i++) {
				if (posX+i < map.get(0).size() && posY+k < map.size()) {
					ret = getTerrainRange(troop, map.get(posY+k).get(posX+i));
					if (ret == 0) {
						break;
					}
					range -= ret;
					if (range >= 0) {
						marked.get(posY+k).set(posX+i, 'X');
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}

		// look down
		range = movRange;
		for (int i=1; range>0; i++) {
			if (posY+i < map.size()) {
				ret = getTerrainRange(troop, map.get(posY+i).get(posX));
				if (ret == 0) {
					break;
				}
				range -= ret;
				if (range >= 0) {
					marked.get(posY+i).set(posX, 'X');
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
			if (posY-i >= 0) {
				ret = getTerrainRange(troop, map.get(posY-i).get(posX));
				if (ret == 0) {
					break;
				}
				range -= ret;
				if (range >= 0) {
					marked.get(posY-i).set(posX, 'X');
				} else {
					break;
				}
			} else {
				break;
			}
		}

		System.out.println("[logic] the following fields are marked");
		for (int i=0; i<marked.size(); i++) {
			for (int k=0; k<marked.get(i).size(); k++) {
				System.out.print(marked.get(i).get(k));
			}
			System.out.println("");
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
