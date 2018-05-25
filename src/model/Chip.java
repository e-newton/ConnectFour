package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Chip {

    static final Color RED = Color.RED;
    static final Color BLUE = Color.BLUE;

    private Color colour;
    private String owner;// either a "1" or a "0"
    private ArrayList<Chip> surroundingChips;
    private Coordinate coordinate;
    private Chip left, right, up, down, topLeft, topRight, bottomLeft, bottomRight;

    public Chip(String owner, Coordinate coordinate, ArrayList<Chip> surroundingChips) {
        this.owner = owner;
        this.coordinate = coordinate;
        setColour();
        this.surroundingChips = surroundingChips;
        initializeChips();

    }

    private void initializeChips() {
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }


    private void setColour() {
        if (owner.equals("1")) {
            colour = RED;

        } else if (owner.equals("0")) {
            colour = BLUE;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chip chip = (Chip) o;
        return Objects.equals(coordinate, chip.coordinate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coordinate);
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Chip> getSurroundingChips() {
        return surroundingChips;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Chip getLeft() {
        return left;
    }

    public void setLeft(Chip left) {
        this.left = left;
    }

    public Chip getRight() {
        return right;
    }

    public void setRight(Chip right) {
        this.right = right;
    }

    public Chip getUp() {
        return up;
    }

    public void setUp(Chip up) {
        this.up = up;
    }

    public Chip getDown() {
        return down;
    }

    public void setDown(Chip down) {
        this.down = down;
    }

    public Chip getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Chip topLeft) {
        this.topLeft = topLeft;
    }

    public Chip getTopRight() {
        return topRight;
    }

    public void setTopRight(Chip topRight) {
        this.topRight = topRight;
    }

    public Chip getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Chip bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Chip getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Chip bottomRight) {
        this.bottomRight = bottomRight;
    }
}
