package model;

import java.util.Map;
import java.util.HashMap;

public class BattleCopter extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public BattleCopter(int x, int y, String color) {
        super(100, 1, 6, x, y, color);

	dmgTable.put("Infantry", 75);
	dmgTable.put("MechanizedInfantry", 75);
	dmgTable.put("Tank", 55);
	dmgTable.put("MobileArtillery", 65);
	dmgTable.put("AntiAir", 25);
	dmgTable.put("Fighter", 0);
	dmgTable.put("Bomber", 0);
	dmgTable.put("BattleCopter", 65);
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
		return (color.equals("red") ? 'C' : 'c');
    }

    public String toString() {
	return "Kampfhelikopter";
    }

}
