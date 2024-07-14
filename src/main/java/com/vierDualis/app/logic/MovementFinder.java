package logic;

import view.*;
import model.*;
import java.util.*;

public class MovementFinder {

	/*
	 * The movement finder is unfinished. It should be there to mark all possible fields
	 * and only allow moves to these fields.
	 */

	public static ArrayList<ArrayList<Character>> stupidMovementRange(ArrayList<ArrayList<Troop>> troops, Troop current) {
		int line, field;
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();

		for (line=0; line<troops.size(); line++) {
			marked.add(new ArrayList<Character>());
			for (field=0; field<troops.get(line).size(); field++) {
				marked.get(line).add('_');
			}
		}
		return marked;
	}
}
