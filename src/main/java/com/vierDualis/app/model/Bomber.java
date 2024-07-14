package model;

public class Bomber extends Troop {

    public Bomber(int x, int y, String color) {
        super(10, 1, 7, x, y, color);
	this.movementRange = 7;
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
		return (color.equals("red") ? 'B' : 'b');
    }

    public String toString() {
	return "Bomber";
    }

}
