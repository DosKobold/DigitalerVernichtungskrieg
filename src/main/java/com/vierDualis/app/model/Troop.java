package model;

public class Troop implements Movable, Attackable {
    protected int hp;
    protected int attackPower;
    protected int movementRange;
    protected int x, y;
    protected String color; // Team color
    protected String weapon;

    public Troop(int hp, int attackPower, int movementRange, int x, int y, String color, String weapon) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.movementRange = movementRange;
        this.x = x;
        this.y = y;
        this.color = color;
        this.weapon = weapon;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

    public int getMovementRange() { return movementRange; }
    public void setMovementRange(int movementRange) { this.movementRange = movementRange; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getWeapon() { return weapon; }
    public void setWeapon(String weapon) { this.weapon = weapon; }
}
