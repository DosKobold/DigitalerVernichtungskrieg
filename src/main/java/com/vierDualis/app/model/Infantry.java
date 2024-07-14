package model;

import java.util.Map;
import java.util.HashMap;

public class Infantry extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public Infantry(int x, int y, String color) {
        super(100, 1, 3, x, y, color);

	dmgTable.put("Infantry", 55);
	dmgTable.put("MechanizedInfantry", 45);
	dmgTable.put("Tank", 5);
	dmgTable.put("MobileArtillery", 15);
	dmgTable.put("AntiAir", 5);
	dmgTable.put("Fighter", 0);
	dmgTable.put("Bomber", 0);
	dmgTable.put("BattleCopter", 7);
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
		return (color.equals("red") ? 'I' : 'i');
    }

    public String toString() {
	return "Infanterie";
    }

}
