package model;

public class Infantry extends Troop {

    public Infantry(int x, int y, String color) {
        super(10, 1, 3, x, y, color);
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
		return (color.equals("red") ? 'I' : 'i');
    }
}
