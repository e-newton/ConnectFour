package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 600;
    private Board board;

    public GamePanel(Board board) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    public void drawBoard(Graphics g) {
        //super.paintComponent(g);
        board.paintBoard(g);
        if (board.checkGameOver()) {
            board.paintBoard(g);
            //repaint();
            drawGameOver(g);
        }
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(WIDTH / 2 - WIDTH / 8, 0, WIDTH / 4, HEIGHT / 4);
        g.setColor(Color.BLACK);
        g.drawString("GAME OVER", 300, 50);
    }


}
