// Names:
// x500s:
//REEDX500 JOHAL004

import java.util.Random;

import static sun.jvm.hotspot.runtime.PerfMemory.top;

public class MyMaze{
    private Cell[][] maze;

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = new Cell();
            }
        }
        //Check
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze m = new MyMaze(rows,cols);
        Stack1Gen s = new Stack1Gen();
        m.maze[0][0].setVisited(true);
        Cell start = m.maze[0][0];
        s.push(new int[]{0, 0});
        Random r = new Random();

        while(!(s.isEmpty())){

            int[] nextPossiblelevel = (int[]) s.top();
            int [] options = spotsOpen(m.maze, nextPossiblelevel);

            int randomIndex = r.nextInt((options.length) + 1);

            //need to check each value
            if(options[randomIndex] == 1){//East


            }else if(options[randomIndex] == 2){//west

            }else if(options[randomIndex] == 3){//south

            }else if(options[randomIndex] == 4){//north

            }

        }



        return null;
    }
    public static int[] spotsOpen(Cell[][] maze, int[] spot){
        int count = 0;

        boolean east = false;
        boolean south = false;
        boolean north = false;
        boolean west = false;

        if(maze[spot[0]-1][spot[1]].getVisited() == false){//East
            count++;
            east = true;
        }
        if(maze[spot[0]+1][spot[1]].getVisited() == false){//West
            count++;
            west = true;
        }
        if(maze[spot[0]][spot[1]-1].getVisited() == false){//South
            count++;
            south = true;

        }
        if (maze[spot[0]][spot[1]+1].getVisited() == false){//North
            count++;
            north = true;
        }
        int[] possibleChoices = [1][count];

        int fillCheck = 0;
        while(fillCheck != count){
            if(east == true){
                possibleChoices[fillCheck] = 1;
                fillCheck++;
            }
            if(west == true){
                possibleChoices[fillCheck] = 2;
                fillCheck++;
            }
            if(north == true){
                possibleChoices[fillCheck] = 3;
                fillCheck++;
            }
            if(south == true){
                possibleChoices[fillCheck] = 4;
                fillCheck++;
            }
        }
        return possibleChoices;
    }
    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze(boolean path) {
        String
        for(int i = 0; int){
                        for(int j = 0; int){
                String stringy = " "
                        //In this order
                '|---|'
                if(this.maze[i][j].getVisited() == true){
                    '*'

                }
                //check if the bottom is there
                //check to see if right wall is there
            }
                        //add '/n' to string
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
    }
}
