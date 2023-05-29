package Model;

import Model.PacMan.PacMan;
import View.Game.ScorePanel;

public class Timer extends Thread{
    private int hours;
    private int mins;
    private int secs;
    private PacMan pacMan;
    private ScorePanel scorePanel;

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void run(){
        while(pacMan.isStart() && pacMan.getLives() > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(secs + 1 == 60){
                secs = 0;
                if(mins + 1 == 60){
                    mins = 0;
                    hours++;
                }else mins++;

            }else secs++;

            scorePanel.updateTime();
        }
    }

    @Override
    public String toString() {
        return (hours < 10 ? "0"+hours : String.valueOf(hours)) + ":" +
                (mins < 10 ? "0"+mins : String.valueOf(mins)) + ":" +
                (secs < 10 ? "0"+secs : String.valueOf(secs));
    }
}
