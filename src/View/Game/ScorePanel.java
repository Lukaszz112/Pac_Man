package View.Game;

import Model.PacMan.PacMan;
import Model.Timer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScorePanel extends JPanel{
    private final PacMan pacMan;
    private final Timer timer;
    public ScorePanel(PacMan pacMan, Timer timer){
        this.pacMan = pacMan;
        this.timer = timer;
        create();
    }

    public void create() {
        setLayout(new BorderLayout());
        setVisible(true);
        setPreferredSize(new Dimension(100, 50));
        setBackground(Color.BLACK);

        Font font = loadFont("GalacticaGrid.ttf", 18);
        Font scoreFont = loadFont("GalacticaGrid.ttf", 20);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel livesLabel = new JLabel("<html><h1>" + pacMan.getLives() + "</h1></html>");
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setFont(font);
        topPanel.add(livesLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.BLACK);

        JLabel start = new JLabel("<html><h1>Press space to start!</h1></html>");
        start.setForeground(Color.WHITE);
        start.setFont(font);
        centerPanel.add(start);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(6, 1, 1, 6));

        JLabel time = new JLabel("<html><p>" + timer.toString() + "</p></html>");
        time.setForeground(Color.WHITE);
        time.setFont(scoreFont);
        rightPanel.add(time);

        JLabel score = new JLabel(
                "<html><p>" + pacMan.getScore() + "/" + pacMan.getFinalScore() + "</p></html>"
        );
        score.setForeground(Color.WHITE);
        score.setFont(scoreFont);
        rightPanel.add(score);

        add(topPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        pacMan.setScorePanel(this);
        timer.setScorePanel(this);
        pacMan.setTimer(timer);
    }

    private Font loadFont(String path, float size) {
        try {
            Path relativePath = Paths.get("src", "Model", "resources", path);
            File fontFile = relativePath.toFile();
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void updateLives() {
        JPanel topPanel = (JPanel) getComponent(0);
        JLabel label = (JLabel) topPanel.getComponent(0);
        label.setText("<html><h1>" + pacMan.getLives() + "</h1></html>");
        repaint();
    }

    public void updateTime() {
        JPanel rightPanel = (JPanel) getComponent(2);
        JLabel label = (JLabel) rightPanel.getComponent(0);
        label.setText("<html><p>" + timer.toString() + "</p></html>");
        repaint();
    }

    public void updateStartVisibility() {
        JPanel centerPanel = (JPanel) getComponent(1);
        JLabel label = (JLabel) centerPanel.getComponent(0);
        label.setVisible(false);
        repaint();
    }

    public void updateScore() {
        JPanel rightPanel = (JPanel) getComponent(2);
        JLabel label = (JLabel) rightPanel.getComponent(1);
        label.setText("<html><p>" + pacMan.getScore() + "/" + pacMan.getFinalScore() + "</p></html>");
        repaint();
    }
}
