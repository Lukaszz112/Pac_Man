package Control.Buttons;

import javax.swing.*;

public class ExitButton extends JButton {
    public ExitButton(){
        create();
    }

    private void create(){
        setText("Exit");

        addActionListener(e -> {
                String[] tab = {"Yes", "No"};

                int a = JOptionPane.showOptionDialog(null,
                        "Are you sure to exit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tab,
                        tab[1]
                );

                if(a == 0){
                    System.exit(0);
                }
        });
    }
}
