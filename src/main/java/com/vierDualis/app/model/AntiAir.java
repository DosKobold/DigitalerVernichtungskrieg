package model;

public class AntiAir extends Troop {

    public AntiAir(int x, int y, String color) {
        super(10, 1, 6, x, y, color);
	this.movementRange = 6;
    }

    
    public void move(int newX, int newY) {
        if (Math.abs(newX - this.x) + Math.abs(newY - this.y) <= movementRange) {
            this.x = newX;
            this.y = newY;
        }
    }

    
    public void attack(Troop target) {
        target.setHp(target.getHp() - this.attackPower);
    }

    public char toChar() {
		return (color.equals("red") ? 'A' : 'a');
    }

    public String toString() {
	return "Mobile Luftabwehr";
    }
}
