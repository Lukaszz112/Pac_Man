package Model.PacMan;

import Control.MyDataWriter;
import Model.Ghost;
import Model.MyTableModel;
import Model.PlaySong;
import Model.Timer;
import View.Game.GameFrame;
import View.Game.ScorePanel;
import View.Menu.HighScorePanel;

import java.util.Random;

public class PacMan implements Runnable{
    private int x;
    private int y;
    private Direction direction;
    private int score;
    private int finalScore;
    private int lives;
    private boolean isStart = false;
    private int shotTime = 0;
    private Object[][] board;
    private MyTableModel model;
    private final PlaySong collect = new PlaySong();
    private ScorePanel scorePanel;
    private Timer timer;
    private GameFrame gameFrame;
    private HighScorePanel highScorePanel;
    private boolean isGunActivated = false;


    public Object[][] getBoard() {
        return board;
    }

    public MyTableModel getModel() {
        return model;
    }

    public boolean isGunActivated() {
        return isGunActivated;
    }

    public void setGunActivated(boolean gunActivated) {
        isGunActivated = gunActivated;
    }

    public void setHighScorePanel(HighScorePanel highScorePanel) {
        this.highScorePanel = highScorePanel;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isCollision() {
        return false;
    }

    public int getScore() {
        return score;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
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

    public int getLives() {
        return lives;
    }

    public void setModel(MyTableModel model) {
        this.model = model;
    }

    Random random = new Random();

    public PacMan(){
        Direction[] dirArr = Direction.values();
        this.direction = dirArr[random.nextInt(4)];
        this.score = 0;
        this.lives = 3;
    }

    public void move(int x, int y, Direction direction){
        if(board[x][y] != Integer.valueOf(1)){
            if(board[x][y] == Integer.valueOf(11)){
                score++;
                this.scorePanel.updateScore();
                collect.play("coincollect.wav");
                board[x][y] = 0;
                model.setValueAt(0, x, y);
            }else if(board[x][y] instanceof Ghost){
                isCaught();
                return;
            }else if(board[x][y] == Integer.valueOf(100)){
                if(!isGunActivated){
                    setGunActivated(true);
                    shotTime = 100;
                }
            }
            board[this.x][this.y] = 0;
            model.setValueAt(0,this.x,this.y);
            if(direction == Direction.TOP || direction == Direction.BOTTOM)
                setX(x);
            else
                setY(y);
            this.direction = direction;
            board[this.x][this.y] = this;
            model.setValueAt(this,this.x,this.y);
        }
    }
    public void moveUp(){
        move(this.x-1,this.y, Direction.TOP);
    }
    public void moveDown(){
        move(this.x+1,this.y, Direction.BOTTOM);
    }
    public void moveLeft(){
        move(this.x,this.y-1, Direction.LEFT);
    }
    public void moveRight(){
        move(this.x,this.y+1, Direction.RIGHT);
    }

    public void shoot(){
        new Shot(this).start();
    }
    public void isCaught() {
        lives--;
        board[this.x][this.y] = 0;
        model.setValueAt(0, this.x, this.y);
        setY(1);
        setX(board.length / 2);
        direction = Direction.RIGHT;
        board[this.x][this.y] = this;
        model.setValueAt(this, this.x, this.y);
        scorePanel.updateLives();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(isStart && lives > 0 && score < finalScore){
            switch (direction){
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case BOTTOM -> moveDown();
                case TOP -> moveUp();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(shotTime > 0){
                shotTime--;
            }else if(shotTime == 0){
                setGunActivated(false);
            }
        }
        new MyDataWriter().writeDataToFile(this);
        highScorePanel.uploadData();
        gameFrame.dispose();
    }

}
