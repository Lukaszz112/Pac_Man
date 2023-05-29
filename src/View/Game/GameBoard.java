package View.Game;

import Control.KeyListeners.KeyControler;
import Model.*;
import Model.PacMan.PacMan;
import Model.Timer;
import View.Menu.HighScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GameBoard extends JPanel{
    private MyTableModel model;
    private final AtomicInteger width;
    private final AtomicInteger height;
    private final ArrayList<Ghost> ghostList = new ArrayList<>();
    private final PacMan pacMan;
    private final ArrayList<Thread> threads = new ArrayList<>();
    private final CustomCellRenderer renderer = new CustomCellRenderer();
    private final KeyControler keyControler = new KeyControler(ghostList, threads);
    private JTable table;
    public GameBoard(
            AtomicInteger width,
            AtomicInteger height,
            PacMan pacMan,
            Timer timer,
            HighScorePanel highScorePanel
    ){
        this.width = width;
        this.height = height;
        this.pacMan = pacMan;
        pacMan.setHighScorePanel(highScorePanel);
        keyControler.setPacMan(pacMan);
        setFocusable(true);
        addKeyListener(keyControler);
        PacManBoardGenerator();
        timer.setPacMan(pacMan);
        threads.add(timer);
        setBackground(Color.BLACK);
    }

    public void PacManBoardGenerator() {
        Maze maze = new Maze();
        Object[][] data = maze.generateMaze(width.intValue(),height.intValue(), this.pacMan, this.ghostList);

        UIManager.put("Table.gridColor", Color.BLACK);
        model = new MyTableModel(data);


        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setDefaultRenderer(Object.class, renderer);
        table.addKeyListener(keyControler);
        Dimension tableSize = table.getPreferredSize();
        setPreferredSize(new Dimension(tableSize.width, tableSize.height));

        renderer.setPacman(pacMan);
        pacMan.setModel(model);
        ghostList.forEach(e -> e.setModel(model));

        setLayout(new GridBagLayout());
        add(table, new GridBagConstraints());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scale();
            }
        });

    }

    public void scale() {
        int width = this.getWidth();
        int height = this.getHeight();
        int rowsNum = model.getRowCount();
        int colsNum = model.getColumnCount();
        int size = Math.min(width / colsNum, height / rowsNum);
        for (int i = 0; i < colsNum; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(size);
            table.getColumnModel().getColumn(i).setPreferredWidth(size);
        }
        renderer.setIconSize(size);

        table.setRowHeight(size);
        table.repaint();
    }

    public Dimension getPreferredSize() {
        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setDefaultRenderer(Object.class, renderer);
        Dimension tableSize = table.getPreferredSize();
        int preferredWidth = (int)tableSize.getWidth()/3;
        int preferredHeight = (int)tableSize.getHeight()+86;
        return new Dimension(preferredWidth, preferredHeight);
    }


}
