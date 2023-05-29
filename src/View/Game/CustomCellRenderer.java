package View.Game;

import Model.Ghost;
import Model.PacMan.PacMan;
import Model.PacMan.Shot;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    private PacMan pacman;

    private final ImageIcon coinIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/line.png"))
    );
    private final ImageIcon wallIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/wall.png"))
    );
    private final ImageIcon ghostIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/ghost.png"))
    );
    private final ImageIcon pacManRightIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanRight.png"))
    );
    private final ImageIcon pacManLeftIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanLeft.png"))
    );
    private final ImageIcon pacManDownIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanDown.png"))
    );
    private final ImageIcon pacManUpIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanUp.png"))
    );
    private final ImageIcon pacManUpGunIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanUpGun.png"))
    );
    private final ImageIcon pacManDownGunIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanDownGun.png"))
    );
    private final ImageIcon pacManLeftGunIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanLeftGun.png"))
    );
    private final ImageIcon pacManRightGunIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/pacmanRightGun.png"))
    );
    private final ImageIcon gunIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/gun.png"))
    );
    private final ImageIcon bulletIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("Model/resources/bullet.png"))
    );

    public List<ImageIcon> iconList = new ArrayList<>();

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    public CustomCellRenderer() {
        iconList.add(coinIcon);
        iconList.add(ghostIcon);
        iconList.add(pacManDownIcon);
        iconList.add(pacManLeftIcon);
        iconList.add(pacManRightIcon);
        iconList.add(pacManUpIcon);
        iconList.add(pacManUpGunIcon);
        iconList.add(pacManDownGunIcon);
        iconList.add(pacManLeftGunIcon);
        iconList.add(pacManRightGunIcon);
        iconList.add(wallIcon);
        iconList.add(gunIcon);
        iconList.add(bulletIcon);
    }


    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {

        setPreferredSize(table.getCellRect(row, column, true).getSize());

        if (value instanceof PacMan) {
            if(pacman.isGunActivated()){
                switch(pacman.getDirection()){
                    case TOP -> setIcon(pacManUpGunIcon);
                    case BOTTOM -> setIcon(pacManDownGunIcon);
                    case LEFT -> setIcon(pacManLeftGunIcon);
                    case RIGHT -> setIcon(pacManRightGunIcon);
                }
            }else{
                switch(pacman.getDirection()){
                    case TOP -> setIcon(pacManUpIcon);
                    case BOTTOM -> setIcon(pacManDownIcon);
                    case LEFT -> setIcon(pacManLeftIcon);
                    case RIGHT -> setIcon(pacManRightIcon);
                }
            }

        }

        if (value instanceof Ghost) {
             setIcon(ghostIcon);
        }

        if (value instanceof Shot){
            setIcon(bulletIcon);
        }

        if (value instanceof Integer) {
            int intValue = (Integer) value;
            if (intValue == 1) {
                setIcon(wallIcon);
            } else if (intValue == 11) {
                setIcon(coinIcon);
                setBackground(Color.black);
            } else if (intValue == 100) {
                setIcon(gunIcon);
                setBackground(Color.black);
            } else{
                setBackground(Color.BLACK);
                setIcon(null);
            }
        }

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        return this;
    }
    public void setIconSize(int size) {
        int cellSize = Math.max(size, getPreferredSize().height);

        coinIcon.setImage(scaleImage(coinIcon.getImage(), cellSize/2, cellSize/2));
        setImg(
                cellSize,
                wallIcon,
                ghostIcon,
                gunIcon,
                pacManRightIcon,
                pacManLeftIcon,
                pacManDownIcon
        );
        setImg(
                cellSize,
                pacManUpIcon,
                pacManUpGunIcon,
                pacManRightGunIcon,
                pacManLeftGunIcon,
                pacManDownGunIcon,
                bulletIcon
        );
    }

    private void setImg(
            int cellSize,
            ImageIcon wallIcon,
            ImageIcon ghostIcon,
            ImageIcon gunIcon,
            ImageIcon pacManRightIcon,
            ImageIcon pacManLeftIcon,
            ImageIcon pacManDownIcon
    ) {
        wallIcon.setImage(scaleImage(wallIcon.getImage(), cellSize, cellSize));
        ghostIcon.setImage(scaleImage(ghostIcon.getImage(), cellSize, cellSize));
        gunIcon.setImage(scaleImage(gunIcon.getImage(), cellSize, cellSize));
        pacManRightIcon.setImage(scaleImage(pacManRightIcon.getImage(), cellSize, cellSize));
        pacManLeftIcon.setImage(scaleImage(pacManLeftIcon.getImage(), cellSize, cellSize));
        pacManDownIcon.setImage(scaleImage(pacManDownIcon.getImage(), cellSize, cellSize));
    }

    public Image scaleImage(Image image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }
}