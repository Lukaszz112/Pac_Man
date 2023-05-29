package View.Menu;

import Control.Buttons.StartButton;
import Control.Buttons.BackButton;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectSize extends JPanel {
    private final CardLayout cl;
    private final Container c;

    public SelectSize(CardLayout cl, Container c, HighScorePanel highScorePanel){
        this.c = c;
        this.cl = cl;
        create(highScorePanel);
        setOpaque(false);
    }

    private void create(HighScorePanel highScorePanel){
        AtomicInteger width = new AtomicInteger();
        AtomicInteger height = new AtomicInteger();

        setLayout(new GridLayout(5,1));
        JSlider sliderWidth = new JSlider(JSlider.HORIZONTAL, 10, 100, 20);
        JSlider sliderHeight = new JSlider(JSlider.HORIZONTAL, 10, 100, 20);

        setSlider(width, sliderWidth);
        setSlider(height, sliderHeight);
        sliderWidth.setOpaque(false);
        sliderHeight.setOpaque(false);

        add(new JLabel("<html><h1><strong>Select board size</strong></h1><hr></html>"));
        add(sliderWidth);
        add(sliderHeight);
        add(new StartButton(width, height, highScorePanel));

        BackButton backButton = new BackButton("Back to menu");
        backButton.addActionListener( e -> {
            cl.show(c, "main");
        });

        add(backButton);
    }

    private void setSlider(AtomicInteger width, JSlider sliderWidth) {
        sliderWidth.setMajorTickSpacing(10);
        sliderWidth.setMinorTickSpacing(5);
        sliderWidth.setPaintTicks(true);
        sliderWidth.setPaintLabels(true);

        sliderWidth.addChangeListener(e -> {
            JSlider s = (JSlider) e.getSource();
            width.set(sliderWidth.getValue());
        });
    }
}
