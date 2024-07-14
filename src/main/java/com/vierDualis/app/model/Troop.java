package model;

public class Troop {
    protected int hp;
    protected int attack;
    protected int movementRange;
    protected int x, y;
    protected String color;

    public Troop(int hp, int attack, int movementRange, int x, int y, String color) {
        this.hp = hp;
        this.attack = attack;
        this.movementRange = movementRange;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getMovementRange() { return movementRange; }
    public void setMovementRange(int movementRange) { this.movementRange = movementRange; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public char toChar() { return '0'; }

    public void attack(Troop target) {}

}
