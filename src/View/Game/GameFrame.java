package View.Game;

import Model.PacMan.PacMan;
import Model.Timer;
import View.Menu.HighScorePanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameFrame extends JFrame {
    private final AtomicInteger width;
    private final AtomicInteger height;
    private final PacMan pacMan = new PacMan();
    private final Timer timer = new Timer();
    private final HighScorePanel highScorePanel;
    public GameFrame(
            AtomicInteger width,
            AtomicInteger height,
            HighScorePanel highScorePanel
    ){
        this.width = width;
        this.height = height;
        this.highScorePanel = highScorePanel;
        create();
    }

    private void create(){

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        GameBoard gameBoard = new GameBoard(
                width,
                height,
                pacMan,
                this.timer,
                highScorePanel
        );
        ScorePanel scorePanel = new ScorePanel(pacMan, this.timer);
        pacMan.setGameFrame(this);

        add(gameBoard,BorderLayout.CENTER);
        add(scorePanel,BorderLayout.PAGE_START);

        setPreferredSize(gameBoard.getPreferredSize());
        pack();
    }
}

