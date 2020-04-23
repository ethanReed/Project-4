// Names:
// x500s:
//REEDX500 JOHAL004

import java.util.Random;

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

            int row = nextPossiblelevel[0];
            int column = nextPossiblelevel[1];


            int randomIndex = r.nextInt((options.length) + 1);

            //need to check each value
            if(options[randomIndex] == 1){//East
//                int[] eastMovement = nextPossiblelevel[[0]+1][1];
                row = row + 1;
                int[] eastMovement = new int[2];
                eastMovement[0] = row;
                eastMovement[1] = column;


                m.maze[row][column].setVisited(true);//since we know we can go to the right, this is true
                m.maze[row][column].setRight(true);//we go into the right and set right to be true
                //load the east movement onto the stack bc the coordinates are right

                s.push(eastMovement);

            }else if(options[randomIndex] == 2){//west
                row = row - 1;
               int[] westMovement = new int[2];

               westMovement[0] = row;
               westMovement[1] = column;

                m.maze[row][column].setVisited(true);//since we know we can go to the left, this is true
                //There is no right wall so we do not have to worry about that.

                s.push(westMovement);


            }else if(options[randomIndex] == 3){//south
                column = column - 1;
               int[] southMovement = new int[2];

               southMovement[0] = row;
               southMovement[1] = column;

                m.maze[row][column].setVisited(true);//since we know we can go below, this is true
                m.maze[row][column].setBottom(true);//we go into below and set right to be true
                //load the south movement onto the stack bc the coordinates are right

                s.push(southMovement);


            }else if(options[randomIndex] == 4){//north
                column = column + 1;
                int[] northMovement = new int[2];
                m.maze[row][column].setVisited(true);//since we know we can go below, this is true

                northMovement[0] = row;
                northMovement[1] = column;

                //load the south movement onto the stack bc the coordinates are right

                s.push(northMovement);

            }else{//since we covered our bases if there is not an open spot we pop nextPossibleLevel off the stack
                s.pop();
            }

        }
        //Now that we have gone through the whole maze, we need to reset has visited, but we have established a path through the maze

        for(int i = 0; i < m.maze.length; i++){
            for(int j = 0; j < m.maze.length; j++){
                m.maze[i][j].setVisited(false);
            }
        }
        return m;//return the maze object
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
        int[] possibleChoices = new int[count];

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

    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
    }
}
