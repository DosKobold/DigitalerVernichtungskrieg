package model;

import java.util.Map;
import java.util.HashMap;

public class Bomber extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public Bomber(int x, int y, String color) {
        super(100, 1, 1, 7, x, y, color);

	dmgTable.put("Infantry", 110);
	dmgTable.put("MechanizedInfantry", 110);
	dmgTable.put("Tank", 105);
	dmgTable.put("MobileArtillery", 105);
	dmgTable.put("AntiAir", 95);
	dmgTable.put("Fighter", 0);
	dmgTable.put("Bomber", 0);
	dmgTable.put("BattleCopter", 0);
    }

    
    public void move(int newX, int newY) {
        if (Math.abs(newX - this.x) + Math.abs(newY - this.y) <= movementRange) {
            this.x = newX;
            this.y = newY;
        }
    }

    
    public void attack(Troop target) {
	String targetName = target.getClass().getSimpleName();
	int dmg = dmgTable.get(targetName);
	System.out.println("[model] " + this.getColor() + " " + this.getClass().getSimpleName() + " attacked " + target.getColor() + " " + targetName + " and made " + dmg + " damage (" + target.getHp() + " -> " + (target.getHp()-dmg) + ")");
        target.setHp(target.getHp() - dmg);
    }

    public char toChar() {
		return (color.equals("red") ? 'B' : 'b');
    }

    public String toString() {
	return "Bomber";
    }

}
