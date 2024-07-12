package model;

public class Fighter extends Troop {

    public Fighter(int x, int y, String color) {
        super(10, 1, 9, x, y, color);
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
}
