package logic;

import view.*;
import model.*;
import java.util.*;

public class MovementFinder {

	/*
	 * Alternative implementation of the troop-marking-algorithm.
	 * It doesn't suffice for the given requirements, but at least does _something_.
	 */
	public static ArrayList<ArrayList<Character>> stupidMovementRange(ArrayList<ArrayList<Character>> map, Troop current) {
		int line, field;
		Converter converter = new Converter();
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();
		ArrayList<ArrayList<Troop>> troopMap = new ArrayList<>();

		troopMap = converter.charToTroop(map);

		exit:
		for (line=0; line<map.size(); line++) {
			for (field=0; field<map.get(line).size(); field++) {
				// Found the position of the current troop on the map
				if (getTroop(troopMap, line, field).getX() == current.getX() && getTroop(troopMap, line, field).getY() == current.getY()) {
					break exit;
				}
			}
		}

		// Now look at the fields around the current player.
		for (int i=0; i<current.getMovementRange(); i++) {
			// Check if the fields around the player are accessible.
			for (int k=0; k<current.getMovementRange(); k++) {
				
			}
		}

		return marked;
	}

	private static Troop getTroop(ArrayList<ArrayList<Troop>> map, int x, int y) {
		Troop troop = map.get(0).get(0);

		for (int line=0; line<map.size(); line++) {
			for (int field=0; field<map.get(line).size(); field++) {
				
			}
		}

		return troop;
	}
}
