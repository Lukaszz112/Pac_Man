package View.Menu;

import Model.PlayTheme;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private static final PlayTheme playTheme = new PlayTheme();
    public Menu(){
        create();
    }

    private void create(){
        CardLayout cardLayout = new CardLayout();
        playTheme.play("PacManTheme.wav");
        MainPanel mainPanel = new MainPanel(cardLayout, this);
        HighScorePanel highScorePanel = new HighScorePanel(cardLayout, this);
        SelectSize selectSize = new SelectSize(cardLayout, this, highScorePanel);
        mainPanel.setOpaque(false);
        setBackground(Color.BLACK);
        setLayout(cardLayout);

        add(mainPanel, "main");
        add(highScorePanel, "highScore");
        add(selectSize, "selectSize");

        cardLayout.show(this,"0");
    }
}
