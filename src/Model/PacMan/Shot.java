package Model.PacMan;

import Model.Ghost;
import Model.MyTableModel;
import Model.PlaySong;

public class Shot extends Thread{
    private final PacMan pacMan;
    private int x;
    private int y;
    private final MyTableModel model;
    private final Object[][] board;
    private Object temp;
    public Shot(PacMan pacMan){
        this.pacMan = pacMan;
        x = pacMan.getX();
        y = pacMan.getY();
        model = pacMan.getModel();
        board = pacMan.getBoard();
        temp = pacMan;
    }

    public synchronized void shot(int x, int y) {
        if (board[x][y] != Integer.valueOf(1)) {
            new PlaySong().play("shot.wav");
            board[this.x][this.y] = temp;
            temp = model.getValueAt(x, y);
            if (board[x][y] instanceof Ghost) {
                Ghost ghost = (Ghost) board[x][y];
                int startX = ghost.getStartX();
                int startY = ghost.getStartY();
                ghost.setX(startX);
                ghost.setY(startY);
                model.setValueAt(board[x][y], startX, startY);
                board[x][y] = 0;
                model.setValueAt(0, x, y);
                return;
            }
            model.setValueAt(
                    temp instanceof Ghost ||
                            temp instanceof PacMan ||
                            temp == Integer.valueOf(100)
                            ?
                            0 : temp , this.x, this.y
            );
            board[x][y] = this;
            model.setValueAt(this, x, y);
            this.x = x;
            this.y = y;
        }
    }
    public void shotUp(){shot(this.x-1,this.y);}
    public void shotDown(){
        shot(this.x+1,this.y);
    }
    public void shotLeft(){
        shot(this.x,this.y-1);
    }
    public void shotRight(){ shot(this.x,this.y+1); }
    @Override
    public void run() {
        Direction direction = pacMan.getDirection();
        switch (direction){
            case RIGHT -> {
                for (int i=0; i<6; i++){
                    shotRight();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            case LEFT -> {
                for (int i=0; i<6; i++){
                    shotLeft();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            case BOTTOM -> {
                for (int i=0; i<6; i++){
                    shotDown();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            case TOP -> {
                for (int i=0; i<6; i++){
                    shotUp();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        board[x][y] = temp;
        model.setValueAt(temp, x, y);
    }
}
