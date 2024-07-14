package model;

import java.util.Map;
import java.util.HashMap;

public class Fighter extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public Fighter(int x, int y, String color) {
        super(100, 1, 9, x, y, color);

	dmgTable.put("Infantry", 0);
	dmgTable.put("MechanizedInfantry", 0);
	dmgTable.put("Tank", 0);
	dmgTable.put("MobileArtillery", 0);
	dmgTable.put("AntiAir", 0);
	dmgTable.put("Fighter", 55);
	dmgTable.put("Bomber", 100);
	dmgTable.put("BattleCopter", 100);
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
		return (color.equals("red") ? 'F' : 'f');
    }

    public String toString() {
	return "Kampfjet";
    }

}
