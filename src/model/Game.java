package model;

import exceptions.FilledColumnException;

import java.awt.event.MouseEvent;

public class Game {
    private Board board;
    private boolean playing;
    private boolean gameOver;
    private AI ai;
    private AI ai2;


    public Game() {
        board = new Board(7, 6);
        playing = true;
        ai = new AI(board, "1");
        ai2 = new AI(board, "0");
    }

    public Board getBoard() {
        return board;
    }

    public void handleMouse(MouseEvent e) throws FilledColumnException {
        if (!gameOver) {
            int column = e.getX() / 100;
            //int column = ai2.playMove();
            board.placeChip("0", column);
            isGameOver();
        }
        if (!gameOver) {
            int column = ai.playMove();
            board.placeChip("1", column);
            isGameOver();
        }


//        if(playing) {
//            int column = e.getX() / 100;
//            board.placeChip("0", column);
//            playing = !playing;
//        }
//        else{
//            int column = ai.playMove();
//            board.placeChip("1", column);
//            playing = !playing;
//        }
    }

    public boolean isGameOver() {
        gameOver = board.checkGameOver();
        //System.out.println(gameOver);
        return gameOver;
    }

    public boolean isPlaying() {
        return playing;
    }
}
