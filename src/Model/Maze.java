package Model;

import Model.PacMan.PacMan;

import java.util.*;

public class Maze {
    Random random = new Random();
    private int finalScore = 0;

    public Object[][] generateMaze(int width, int height, PacMan pacMan, ArrayList<Ghost> ghostList) {
        Object[][] maze = new Object[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(maze[i], 1);
        }

        Stack<Integer> stack = new Stack<>();
        int startX = random.nextInt(width - 2) + 1;
        int startY = random.nextInt(height - 2) + 1;
        maze[startY][startX] = 0;

        stack.push(startY * width + startX);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            int x = current % width;
            int y = current / width;
            Vector<Integer> neighbors = new Vector<>();
            if (x > 1) {
                if (maze[y][x - 2] == Integer.valueOf(1)) {
                    neighbors.add(y * width + x - 2);
                }
            }
            if (x < width - 2) {
                if (maze[y][x + 2] == Integer.valueOf(1)) {
                    neighbors.add(y * width + x + 2);
                }
            }
            if (y > 1) {
                if (maze[y - 2][x] == Integer.valueOf(1)) {
                    neighbors.add((y - 2) * width + x);
                }
            }
            if (y < height - 2) {
                if (maze[y + 2][x] == Integer.valueOf(1)) {
                    neighbors.add((y + 2) * width + x);
                }
            }
            if (!neighbors.isEmpty()) {
                Collections.shuffle(neighbors);
                int neighbor = neighbors.get(0);
                int nx = neighbor % width;
                int ny = neighbor / width;
                maze[ny][nx] = 0;
                if (nx > x) {
                    maze[y][x + 1] = 0;
                } else if (nx < x) {
                    maze[y][x - 1] = 0;
                } else if (ny > y) {
                    maze[y + 1][x] = 0;
                } else if (ny < y) {
                    maze[y - 1][x] = 0;
                }
                stack.push(current);
                stack.push(neighbor);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == Integer.valueOf(0)) {
                    Vector<Integer> neighbors = new Vector<>();
                    if (j > 0 && maze[i][j - 1] == Integer.valueOf(0)) {
                        neighbors.add(i * width + j - 1);
                    }
                    if (j < width - 1 && maze[i][j + 1] == Integer.valueOf(0)) {
                        neighbors.add(i * width + j + 1);
                    }
                    if (i > 0 && maze[i - 1][j] == Integer.valueOf(0)) {
                        neighbors.add((i - 1) * width + j);
                    }
                    if (i < height - 1 && maze[i + 1][j] == Integer.valueOf(0)) {
                        neighbors.add((i + 1) * width + j);
                    }
                    Collections.shuffle(neighbors);
                    for (int neighbor : neighbors) {
                        int nx = neighbor % width;
                        int ny = neighbor / width;
                        if (nx < j) {
                            for (int k = nx; k < j; k++) {
                                maze[ny][k] = 0;
                            }
                        } else if (nx > j) {
                            for (int k = j; k < nx; k++) {
                                maze[ny][k] = 0;
                            }
                        } else if (ny < i) {
                            for (int k = ny; k < i; k++) {
                                maze[k][nx] = 0;
                            }
                        } else if (ny > i) {
                            for (int k = i; k < ny; k++) {
                                maze[k][nx] = 0;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            maze[i][0] = 1;
            maze[i][width - 1] = 1;
        }
        for (int i = 0; i < width; i++) {
            maze[0][i] = 1;
            maze[height - 1][i] = 1;
        }

        for (int i = 1; i < height-1; i++) {
            maze[i][1] = 0;
            maze[i][width - 2] = 0;
        }
        for (int i = 1; i < width-1; i++) {
            maze[1][i] = 0;
            maze[height - 2][i] = 0;
        }

        maze[maze.length/2][1] = pacMan;
        pacMan.setX(maze.length/2);
        pacMan.setY(1);

        for(int i=0; i<4; i++){
            ghostList.add(new Ghost());
        }

        maze[1][1] = ghostList.get(0);
        ghostList.get(0).setX(1);
        ghostList.get(0).setY(1);
        ghostList.get(0).setStartCords(1,1);
        maze[maze.length-2][1] = ghostList.get(1);
        ghostList.get(1).setX(maze.length-2);
        ghostList.get(1).setY(1);
        ghostList.get(1).setStartCords(maze.length-2,1);
        maze[1][maze[1].length-2] = ghostList.get(2);
        ghostList.get(2).setX(1);
        ghostList.get(2).setY(maze[1].length-2);
        ghostList.get(2).setStartCords(1,maze[1].length-2);
        maze[maze.length-2][maze[1].length-2] = ghostList.get(3);
        ghostList.get(3).setX(maze.length-2);
        ghostList.get(3).setY(maze[1].length-2);
        ghostList.get(3).setStartCords(maze.length-2,maze[1].length-2);

        pacMan.setBoard(maze);
        for (Ghost ghost :
                ghostList) {
            ghost.setPacMan(pacMan);
            ghost.setBoard(maze);
        }

        for (int x=0; x<maze.length-1; x++){
            for(int y=0; y<maze[x].length-1; y++){
                if(maze[x][y] != Integer.valueOf(1) && maze[x][y] != pacMan && !(maze[x][y] instanceof Ghost)){
                    maze[x][y] = 11;
                    finalScore++;
                }
            }
        }

        pacMan.setFinalScore(finalScore);
        return maze;
    }
}
