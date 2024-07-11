package model;

public class Terrain {
    private String type;
    private int movementCost;

    public Terrain(String type, int movementCost) {
        this.type = type;
        this.movementCost = movementCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }
}
