package model;

public class MobileArtillery extends Troop {

    public MobileArtillery(int x, int y, String color, String weapon) {
        super(10, 3, 5, x, y, color, weapon);
    }

    @Override
    public void move(int newX, int newY) {
        if (Math.abs(newX - this.x) + Math.abs(newY - this.y) <= movementRange) {
            this.x = newX;
            this.y = newY;
        }
    }

    @Override
    public void attack(Troop target) {
        target.setHp(target.getHp() - this.attackPower);
    }
}
