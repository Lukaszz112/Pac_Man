package View.Menu;

import Control.Buttons.ExitButton;
import Control.Buttons.HighScoreButton;
import Control.Buttons.NewGameButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainPanel extends JPanel {
    private final CardLayout cardLayout;
    private final Container container;

    public MainPanel(CardLayout cl, Container m){
        this.cardLayout = cl;
        this.container = m;
        create();
    }

    private void create(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(new JLabel("<html><h1><strong>PAC MAN</strong></h1><hr></html>"), gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        NewGameButton newGameButton = new NewGameButton();
        newGameButton.addActionListener(e -> {
            cardLayout.show(container, "selectSize");

        });

//        try {
//            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("Model/resources/newGameButton.png")));
//            newGameButton.setIcon(new ImageIcon(img));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        add(newGameButton, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);
        HighScoreButton highScoreButton = new HighScoreButton();
        highScoreButton.addActionListener(e -> {
            cardLayout.show(container, "highScore");
        });

        add(highScoreButton, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        add(new ExitButton(), gbc);

    }
}
