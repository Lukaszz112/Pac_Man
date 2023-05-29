package View.Menu;

import Control.Buttons.BackButton;
import Control.MyDataReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HighScorePanel extends JPanel {
    private final CardLayout cl;
    private final Container container;
    private final String[] colums = {"Name", "Score", "Time"};
    private DefaultTableModel model;

    public HighScorePanel(CardLayout cl, Container m){
        this.container = m;
        this.cl = cl;
        create();
    }
    private void create(){
        setLayout(new BorderLayout());
        model = new DefaultTableModel(colums, 0);
        uploadData();
        JTable jTable = new JTable(model);
        setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(jTable);

        BackButton backButton = new BackButton("Back to menu");
        backButton.addActionListener(e -> {
            cl.show(container, "main");
        });

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.PAGE_END);
    }
    public void uploadData(){
        model.setRowCount(0);
        Object[][] data = new MyDataReader().readData("highscore.txt");
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
}