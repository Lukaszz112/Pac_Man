package Control.Buttons;

import javax.swing.*;

public class BackButton extends JButton {
    private final String placeholder;

    public BackButton(String placeholder) {
        this.placeholder = placeholder;
        create();
    }

    private void create(){
        setText(placeholder);
    }
}
