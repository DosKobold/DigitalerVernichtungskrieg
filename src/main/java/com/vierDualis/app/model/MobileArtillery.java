package model;

public class MobileArtillery extends Troop {

    public MobileArtillery(int x, int y, String color) {
        super(10, 3, 5, x, y, color);
	this.movementRange = 5;
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
		return (color.equals("red") ? 'D' : 'd');
    }

    public String toString() {
	return "Mobile Artillerie";
    }

}
