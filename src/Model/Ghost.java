package Model;

import Model.PacMan.Direction;
import Model.PacMan.PacMan;
import Model.PacMan.Shot;

import java.util.Random;

public class Ghost implements Runnable {
    private int startX;
    private int startY;
    private int x;
    private int y;
    private Direction direction;
    private final Direction[] directions = Direction.values();
    private Object[][] board;
    private MyTableModel model;
    private PacMan pacMan;
    private final Random random = new Random();
    private Object temp = 0;
    private boolean shouldLeaveUpgrade = false;

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartCords(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void setBoard(Object[][] board) {
        this.board = board;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setModel(MyTableModel model) {
        this.model = model;
    }

    public Ghost(){
        Direction[] dirArr = Direction.values();
        this.direction = dirArr[random.nextInt(4)];
    }
    public synchronized void move() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case LEFT -> newY = y - 1;
            case RIGHT -> newY = y + 1;
            case TOP -> newX = x - 1;
            case BOTTOM -> newX = x + 1;
        }

        if (board[newX][newY] != Integer.valueOf(1)) {
            if (board[newX][newY] == pacMan) {
                ((PacMan) board[newX][newY]).isCaught();
            }

            if(board[newX][newY] instanceof Shot){
                board[startX][startY] = this;
                model.setValueAt(this, startX, startY);
                board[newX][newY] = temp;
                model.setValueAt(temp,newX,newY);
            }

            for (Object[] objects : board) {
                for (Object object : objects) {
                    if (
                            object instanceof Ghost &&
                                    ((Ghost) object).getX() == newX &&
                                    ((Ghost) object).getY() == newY
                    ) {
                        direction = directions[random.nextInt(4)];
                        return;
                    }
                }
            }

            board[x][y] = temp instanceof Ghost ? 0 : temp;
            model.setValueAt(temp instanceof Ghost ? 0 : temp, x, y);

            if (shouldLeaveUpgrade) {
                if(board[x][y] == Integer.valueOf(11)){
                    pacMan.setFinalScore(pacMan.getFinalScore() - 1);
                }
                shouldLeaveUpgrade = false;
                board[x][y] = 100;
                model.setValueAt(100, x, y);
            } else {
                if (random.nextInt(50) == 0) {
                    shouldLeaveUpgrade = true;
                }
            }
            temp = model.getValueAt(newX, newY);
            board[newX][newY] = this;
            model.setValueAt(this, newX, newY);
            x = newX;
            y = newY;
        } else {
            direction = directions[random.nextInt(4)];
        }
    }

    @Override
    public void run() {
        while(this.pacMan.isStart() && pacMan.getLives() > 0){
            if(random.nextInt(5)+1 == 1){
                direction = directions[random.nextInt(4)];
            }

            move();

            if(pacMan.isCollision()){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
