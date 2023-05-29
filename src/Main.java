import Model.PlayTheme;
import View.Menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PacMan PJEdition");

            frame.add(new Menu());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}