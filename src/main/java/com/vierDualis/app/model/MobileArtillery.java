package model;

import java.util.Map;
import java.util.HashMap;

public class MobileArtillery extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public MobileArtillery(int x, int y, String color) {
        super(100, 1, 1, 5, x, y, color);

	dmgTable.put("Infantry", 90);
	dmgTable.put("MechanizedInfantry", 85);
	dmgTable.put("Tank", 70);
	dmgTable.put("MobileArtillery", 75);
	dmgTable.put("AntiAir", 75);
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
		return (color.equals("red") ? 'D' : 'd');
    }

    public String toString() {
	return "Mobile Artillerie";
    }

}
