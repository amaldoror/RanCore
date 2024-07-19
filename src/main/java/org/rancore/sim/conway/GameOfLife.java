package org.rancore.sim.conway;

public class GameOfLife {
    private final int rows;
    private final int cols;
    private int[][] grid;
    private boolean isSimulating = true;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    // Initialize the grid with random 0s and 1s
    public void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Math.random() < 0.5 ? 0 : 1;
            }
        }
    }

    // Print the current state of the grid
    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] == 0 ? "." : "*");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Update the grid to the next state
    public void update() {
        int[][] newGrid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);

                if (grid[i][j] == 1) {
                    // Rule 1 and 3
                    newGrid[i][j] = (liveNeighbors < 2 || liveNeighbors > 3) ? 0 : 1;
                } else {
                    // Rule 4
                    newGrid[i][j] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }

        grid = newGrid;
    }

    // Count the number of live neighbors around a given cell
    private int countLiveNeighbors(int row, int col) {
        int liveNeighbors = 0;
        int[] directions = {-1, 0, 1};

        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    liveNeighbors += grid[newRow][newCol];
                }
            }
        }

        return liveNeighbors;
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(20, 20);
        game.initialize();

        long lastTime = System.currentTimeMillis();
        final int delay = 500; // 500 milliseconds delay

        while (game.isSimulating) {
            game.printGrid();
            game.update();
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastTime;

            if (elapsedTime < delay) {
                try {
                    Thread.sleep(delay - elapsedTime);
                } catch (InterruptedException e) {
                    System.err.println("Thread was interrupted");
                    break;
                }
            }
            lastTime = System.currentTimeMillis();
        }
    }
}
