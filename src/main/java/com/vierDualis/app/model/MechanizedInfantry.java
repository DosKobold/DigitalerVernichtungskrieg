package model;

import java.util.Map;
import java.util.HashMap;

public class MechanizedInfantry extends Troop {

    private Map<String, Integer> dmgTable = new HashMap<>();

    public MechanizedInfantry(int x, int y, String color) {
        super(100, 1, 2, x, y, color);

	dmgTable.put("Infantry", 65);
	dmgTable.put("MechanizedInfantry", 55);
	dmgTable.put("Tank", 55);
	dmgTable.put("MobileArtillery", 70);
	dmgTable.put("AntiAir", 65);
	dmgTable.put("Fighter", 0);
	dmgTable.put("Bomber", 0);
	dmgTable.put("BattleCopter", 9);
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
		return (color.equals("red") ? 'M' : 'm');
    }

    public String toString() {
	return "Panzergrenadiere";
    }

}
