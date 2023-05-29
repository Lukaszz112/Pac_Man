package Control.KeyListeners;

import Model.Ghost;
import Model.PacMan.Direction;
import Model.PacMan.PacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class KeyControler implements KeyListener {
    private PacMan pacMan;
    private List<Thread> threads;
    private List<Ghost> ghostList;
    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public KeyControler(List<Ghost> ghostList, List<Thread> threadList){
        this.threads = threadList;
        this.ghostList = ghostList;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int modifiers = e.getModifiers();
        if (modifiers == (
                KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK) && keyCode == KeyEvent.VK_Q
        ) {
            pacMan.setStart(false);
        } else {
            switch (keyCode) {
                case KeyEvent.VK_UP -> {
                    this.pacMan.setDirection(Direction.TOP);
                }
                case KeyEvent.VK_DOWN -> {
                    this.pacMan.setDirection(Direction.BOTTOM);
                }
                case KeyEvent.VK_LEFT -> {
                    this.pacMan.setDirection(Direction.LEFT);
                }
                case KeyEvent.VK_RIGHT -> {
                    this.pacMan.setDirection(Direction.RIGHT);
                }
                case KeyEvent.VK_X -> {
                    if(this.pacMan.isGunActivated()){
                        this.pacMan.shoot();
                    }
                }
                case KeyEvent.VK_SPACE -> {
                    if (!pacMan.isStart()) {
                        pacMan.setStart(true);
                        threads.add(new Thread(pacMan));
                        for (Ghost ghost : ghostList) {
                            threads.add(new Thread(ghost));
                        }

                        for (Thread thread : threads) {
                            thread.start();
                        }

                        pacMan.getScorePanel().updateStartVisibility();
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void exitGame() {
        System.exit(0);
    }
}
