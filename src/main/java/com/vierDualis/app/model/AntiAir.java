package model;

import java.util.Map;
import java.util.HashMap;

public class AntiAir extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public AntiAir(int x, int y, String color) {
        super(100, 1, 6, x, y, color);

	dmgTable.put("Infantry", 105);
	dmgTable.put("MechanizedInfantry", 105);
	dmgTable.put("Tank", 25);
	dmgTable.put("MobileArtillery", 50);
	dmgTable.put("AntiAir", 45);
	dmgTable.put("Fighter", 65);
	dmgTable.put("Bomber", 75);
	dmgTable.put("BattleCopter", 120);
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
        target.setHp(target.getHp() - dmg);
	System.out.println(this.getClass().getSimpleName() + " attacked " + targetName + " and made " + dmg + " damage");
    }

    public char toChar() {
		return (color.equals("red") ? 'A' : 'a');
    }

    public String toString() {
	return "Mobile Luftabwehr";
    }
}
