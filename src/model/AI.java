package model;

import java.util.*;

public class AI {
    Random random;
    String owner;
    private Board board;

    public AI(Board board, String owner) {
        this.board = board;
        random = new Random();
        this.owner = owner;
    }

    public int playMove() {
        ArrayList<Coordinate> validSpaces = new ArrayList<>();
        Map<Coordinate, Integer> values = new HashMap<>();
        getValidSpaces(validSpaces);
        for (Coordinate c : validSpaces) {
            values.put(c, random.nextInt(2));
        }
        for (Coordinate c : validSpaces) {
            Chip chip = new Chip("1", c, board.getSurrondingChips(c));
            board.setSurroundingChips(chip);
            int temp = values.get(c) + checkDown(c.getColumn());
            temp += checkLeft(chip);
            temp += checkDiag(chip);
            values.replace(c, temp);


        }
        ArrayList<Integer> scores = new ArrayList<>();
        for (Integer i : values.values()) {
            scores.add(i);
        }
        int winner = Collections.max(scores);
        //System.out.println(winner);
        for (Coordinate c : values.keySet()) {
            if (values.get(c) == winner) {
                return c.getColumn();
            }

        }


        return 0;
    }

    private void getValidSpaces(ArrayList<Coordinate> validSpaces) {
        for (int x = 0; x < board.getColumns(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                Coordinate temp = new Coordinate(x, y);
                if (board.getBoard().get(temp) == null) {
                    validSpaces.add(temp);
                    break;
                }
            }
        }
    }

    private int checkDown(int column) {
        ArrayList<Chip> chips;
        chips = board.getColumn(column);
        String count = "";
        for (Chip c1 : chips) {
            count += c1.getOwner();
        }
        if (count.endsWith("111") || count.endsWith("000")) {
            if (count.endsWith("111")) {
                return 5;
            }
            return 4;
        } else if (count.endsWith("11") || count.endsWith("00")) {
            return 2;
        } else if (count.endsWith("1") || count.endsWith("0")) {
            return 1;
        }

        return 0;
    }

    private int checkLeft(Chip c) {
        ArrayList<Chip> chipsRight = new ArrayList<>();
        chipsRight.add(c);
        ArrayList<Chip> chipsLeft = new ArrayList<>();
        chipsLeft.add(c);
        while (chipsRight.get(chipsRight.size() - 1).getRight() != null) {
            chipsRight.add(chipsRight.get(chipsRight.size() - 1).getRight());
        }
        while (chipsLeft.get(chipsLeft.size() - 1).getLeft() != null) {
            chipsLeft.add(chipsLeft.get(chipsLeft.size() - 1).getLeft());
        }
        String left = "";
        String right = "";
        for (Chip c1 : chipsLeft) {
            left += c1.getOwner();
        }
        for (Chip c1 : chipsRight) {
            right += c1.getOwner();
        }
        if (left.endsWith("111") || left.endsWith("000") || right.endsWith("111") || right.endsWith("000")) {
            if (left.endsWith("111") || right.endsWith("111")) {
                return 5;
            }
            return 4;
        } else if (left.endsWith("11") || left.endsWith("00") || right.endsWith("11") || right.endsWith("00")) {
            return 2;
        } else if (left.endsWith("1") || left.endsWith("0") || right.endsWith("1") || right.endsWith("0")) {
            return 1;
        }
        return 0;
    }

    private int checkDiag(Chip c) {
        ArrayList<Chip> chipsRight = new ArrayList<>();
        chipsRight.add(c);
        ArrayList<Chip> chipsLeft = new ArrayList<>();
        chipsLeft.add(c);
        while (chipsRight.get(chipsRight.size() - 1).getTopRight() != null) {
            chipsRight.add(chipsRight.get(chipsRight.size() - 1).getTopRight());
        }
        while (chipsLeft.get(chipsLeft.size() - 1).getTopLeft() != null) {
            chipsLeft.add(chipsLeft.get(chipsLeft.size() - 1).getTopLeft());
        }
        String left = "";
        String right = "";
        for (Chip c1 : chipsLeft) {
            left += c1.getOwner();
        }
        for (Chip c1 : chipsRight) {
            right += c1.getOwner();
        }
        if (left.endsWith("111") || left.endsWith("000") || right.endsWith("111") || right.endsWith("000")) {
            if (left.endsWith("111") || right.endsWith("111")) {
                return 5;
            }
            return 2;
        } else if (left.endsWith("11") || left.endsWith("00") || right.endsWith("11") || right.endsWith("00")) {
            return 1;
        } else if (left.endsWith("1") || left.endsWith("0") || right.endsWith("1") || right.endsWith("0")) {
            return 0;
        }
        return 0;
    }


}
