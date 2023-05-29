package Control.Buttons;

import View.Game.GameFrame;
import View.Menu.HighScorePanel;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StartButton extends JButton {
    private final AtomicInteger width;
    private final AtomicInteger height;
    HighScorePanel highScorePanel;
    GameFrame gameFrame;

    public StartButton(AtomicInteger width, AtomicInteger height,HighScorePanel highScorePanel){
        this.width = width;
        this.height = height;
        this.highScorePanel = highScorePanel;
        create();
    }

    private void create(){
        setText("StartGame");
        addActionListener( e -> SwingUtilities.invokeLater(() -> {
            if(width.intValue() != 0 && height.intValue() != 0){
                if(gameFrame == null || !gameFrame.isVisible()) {
                    gameFrame = new GameFrame(width,height,highScorePanel);
                }
            }
        }));
    }
}
