package Control.Buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NewGameButton extends JButton {
    public NewGameButton() {
        create();
    }

    private void create(){
        setText("New Game");
    }
}
