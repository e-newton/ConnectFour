package model;

import exceptions.FilledColumnException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    int rows;
    int columns;
    private HashMap<Coordinate, Chip> board;

    public Board(int columns, int rows) {
        board = new HashMap<>();
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                board.put(new Coordinate(x, y), null);
            }
        }
        this.rows = rows;
        this.columns = columns;
    }

    void placeChip(String owner, int column) throws FilledColumnException {
        for (int i = 0; i < rows; i++) {
            Coordinate tempCord = new Coordinate(column, i);
            if (board.get(tempCord) == null) {
                Chip chip = new Chip(owner, tempCord, getSurrondingChips(tempCord));
                board.put(tempCord, chip);
                for (Chip c : board.values()) {
                    if (c != null) {
                        setSurroundingChips(c);
                    }
                }

                return;
            }
        }
        throw new FilledColumnException();
    }

    ArrayList<Chip> getSurrondingChips(Coordinate coordinate) {
        ArrayList<Chip> chips = new ArrayList<>();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int selectedCol = coordinate.getColumn();
        int selectedRow = coordinate.getRow();
        getSurrondingCoordinates(coordinates, selectedCol, selectedRow);
        for (Coordinate c : coordinates) {
            chips.add(board.get(c));
        }
        return chips;
    }

    public void setSurroundingChips(Chip c) {
        int selectedCol = c.getCoordinate().getColumn();
        int selectedRow = c.getCoordinate().getRow();
        c.setBottomLeft(board.get(new Coordinate(selectedCol - 1, selectedRow - 1)));
        c.setLeft(board.get(new Coordinate(selectedCol - 1, selectedRow)));
        c.setTopLeft(board.get(new Coordinate(selectedCol - 1, selectedRow + 1)));
        c.setUp(board.get(new Coordinate(selectedCol, selectedRow + 1)));
        c.setTopRight(board.get(new Coordinate(selectedCol + 1, selectedRow + 1)));
        c.setRight(board.get(new Coordinate(selectedCol + 1, selectedRow)));
        c.setBottomRight(board.get(new Coordinate(selectedCol + 1, selectedRow - 1)));
        c.setDown(board.get(new Coordinate(selectedCol, selectedRow - 1)));
    }

    private void getSurrondingCoordinates(ArrayList<Coordinate> coordinates, int selectedCol, int selectedRow) {
        coordinates.add(new Coordinate(selectedCol - 1, selectedRow - 1));//1
        coordinates.add(new Coordinate(selectedCol - 1, selectedRow));//2
        coordinates.add(new Coordinate(selectedCol - 1, selectedRow + 1));//3
        coordinates.add(new Coordinate(selectedCol, selectedRow + 1));//4
        coordinates.add(new Coordinate(selectedCol + 1, selectedRow + 1));//5
        coordinates.add(new Coordinate(selectedCol + 1, selectedRow));//6
        coordinates.add(new Coordinate(selectedCol + 1, selectedRow - 1));//7
        coordinates.add(new Coordinate(selectedCol, selectedRow - 1));
    }


    public void paintBoard(Graphics g) {

        for (Coordinate coordinate : board.keySet()) {
            if (board.get(coordinate) != null) {
                Chip chip = board.get(coordinate);
                g.setColor(chip.getColour());
                g.fillOval((coordinate.getColumn() * 100) + 12, 500 - coordinate.getRow() * 100, 75, 75);
            } else {
                g.setColor(Color.BLACK);
                g.fillOval((coordinate.getColumn() * 100) + 12, 500 - coordinate.getRow() * 100, 75, 75);
            }
        }


    }

    public HashMap<Coordinate, Chip> getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<Chip> getColumn(int x) {
        ArrayList<Chip> chips = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            Coordinate temp = new Coordinate(x, y);
            if (board.get(temp) != null) {
                chips.add(board.get(temp));
            }
        }

        return chips;
    }

    public boolean checkGameOver() {

        if (checkDown()) return true;
        else if (checkLeft()) return true;
        else if (checkTopRight()) return true;
        else return checkTopLeft();


    }

    private boolean checkTopLeft() {
        for (Chip c : board.values()) {
            if (c != null) {
                ArrayList<Chip> chips = new ArrayList<>();
                chips.add(c);
                while (chips.get(chips.size() - 1).getTopLeft() != null) {
                    chips.add(chips.size(), chips.get(chips.size() - 1).getTopLeft());
                }
                if (chips.size() < 4) {
                    continue;
                }
                //System.out.println(chips.size());
                int count = 0;
                String owner = "-1";
                for (Chip c1 : chips) {
                    if (c1.getOwner() != owner) {
                        count = 0;
                        owner = c1.getOwner();
                    } else if (c1.getOwner().equals("-1")) {
                        count++;
                        owner = c1.getOwner();
                    } else {
                        count++;
                    }
                    if (count == 3) {
                        System.out.println(owner);
                        return true;
                    }
                }


            }
        }
        return false;
    }

    private boolean checkTopRight() {
        for (Chip c : board.values()) {
            if (c != null) {
                ArrayList<Chip> chips = new ArrayList<>();
                chips.add(c);
                while (chips.get(chips.size() - 1).getTopRight() != null) {
                    chips.add(chips.size(), chips.get(chips.size() - 1).getTopRight());
                }
                if (chips.size() < 4) {
                    continue;
                }
                //System.out.println(chips.size());
                int count = 0;
                String owner = "-1";
                ArrayList<Chip> winningChips = new ArrayList<>();
                for (Chip c1 : chips) {
                    if (c1.getOwner() != owner) {
                        count = 0;
                        owner = c1.getOwner();
                        winningChips.clear();
                        winningChips.add(c1);
                    } else if (c1.getOwner().equals("-1")) {
                        count++;
                        owner = c1.getOwner();
                        winningChips.add(c1);
                    } else {
                        count++;
                        winningChips.add(c1);
                    }
                    //System.out.println(count);
                    if (count == 3) {
                        System.out.println(owner);
                        for (Chip c2 : winningChips) {
                            c2.setColour(Color.YELLOW);
                        }
                        return true;
                    }
                }


            }
        }
        return false;
    }

    private boolean checkLeft() {
        for (Chip c : board.values()) {
            if (c != null) {
                ArrayList<Chip> chips = new ArrayList<>();
                chips.add(c);
                while (chips.get(chips.size() - 1).getRight() != null) {
                    chips.add(chips.get(chips.size() - 1).getRight());
                }

                if (chips.size() < 4) {
                    //System.out.println(chips.size());
                    continue;
                }
                int count = 0;
                String owner = "-1";
                ArrayList<Chip> winningChips = new ArrayList<>();
                for (Chip c1 : chips) {
                    if (c1.getOwner() != owner) {
                        count = 1;
                        owner = c1.getOwner();
                        winningChips.clear();
                        winningChips.add(c1);
                    } else if (c1.getOwner().equals("-1")) {
                        winningChips.add(c1);
                        count++;
                        owner = c1.getOwner();
                    } else {
                        winningChips.add(c1);
                        count++;
                    }
                    //System.out.println(count);
                    if (count == 4) {
                        System.out.println(owner);
                        for (Chip c2 : winningChips) {
                            c2.setColour(Color.YELLOW);
                        }
                        return true;
                    }
                }


            }
        }
        return false;
    }

    private boolean checkDown() {
        ArrayList<Chip> chips;
        for (int x = 0; x < columns; x++) {
            chips = getColumn(x);
            if (chips.size() < 4) {
                continue;
            }
            int count = 0;
            String owner = "-1";
            ArrayList<Chip> winningChips = new ArrayList<>();
            for (Chip c1 : chips) {
                if (c1.getOwner() != owner) {
                    count = 0;
                    owner = c1.getOwner();
                    winningChips.clear();
                    winningChips.add(c1);
                } else if (c1.getOwner().equals("-1")) {
                    count++;
                    owner = c1.getOwner();
                    winningChips.add(c1);
                } else {
                    winningChips.add(c1);
                    count++;
                }
                if (count == 3) {
                    for (Chip c2 : winningChips) {
                        c2.setColour(Color.YELLOW);
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
