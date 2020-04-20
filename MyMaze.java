// Names:
// x500s:

import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        Cell i = new Cell();
        Cell j = new Cell();
        Cell[][] newObj = maze[i][j] = new Cell();
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        return null;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze(boolean path) {
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
    }
}
