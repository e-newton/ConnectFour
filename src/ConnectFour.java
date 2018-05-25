import exceptions.FilledColumnException;
import model.Game;
import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectFour extends JFrame {


    public static final int WIDTH = 700;
    public static final int HEIGHT = 650;
    GamePanel gp;
    Game game;
    MenuBar menu;


    public ConnectFour() {
        super("Connect Four");
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        //setUndecorated(true);
        centreOnScreen();
        game = new Game();
        gp = new GamePanel(game.getBoard());
        menu = new MenuBar();
        add(gp);
        setJMenuBar(menu);
        GameMouseListener gml = new GameMouseListener();
        addMouseListener(gml);
        addMouseMotionListener(gml);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {
        new ConnectFour();

    }

    public void handleMousePressed(MouseEvent e) {
        try {
            game.handleMouse(e);
            repaint();
        } catch (FilledColumnException e1) {
            e1.printStackTrace();
        }

    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    public class GameMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            handleMousePressed(e);
        }


    }


    class MenuBar extends JMenuBar implements ActionListener {

        JMenu menu;
        JMenuItem newGame;

        public MenuBar() {
            super();
            menu = new JMenu("Game");
            newGame = new JMenuItem("New Game");
            newGame.addActionListener(this);
            menu.add(newGame);
            this.add(menu);


        }


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("NEW GAME");
            game = new Game();
            getContentPane().remove(gp);
            //remove(gp);
            gp = new GamePanel(game.getBoard());
            getContentPane().add(gp);
            gp.repaint();
            revalidate();
        }


    }


}
