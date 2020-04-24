// Names:
// x500s:
//REEDX500 JOHAL004

import java.util.Random;

public class MyMaze{
    private Cell[][] maze;
    private int cols;
    private int rows;

    public MyMaze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
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


        MyMaze m = new MyMaze(rows, cols);
        Stack1Gen s = new Stack1Gen();
        m.maze[0][0].setVisited(true);
        Cell start = m.maze[0][0];


        s.push(new int[]{0, 0});
        Random r = new Random();


        while (!(s.isEmpty())) {

            int[] nextPossiblelevel = (int[]) s.top();
            int[] options = spotsOpen(m.maze, nextPossiblelevel, rows, cols);

            int row = nextPossiblelevel[0];
            int column = nextPossiblelevel[1];

            //fix setters

            if (options.length > 0) {
                int randomIndex = r.nextInt(options.length);
                //need to check each value
                if (options[randomIndex] == 1) {//East

//                int[] eastMovement = nextPossiblelevel[[0]+1][1];
                    column = column + 1;
                    int[] eastMovement = new int[2];
                    eastMovement[0] = row;
                    eastMovement[1] = column;
                    m.maze[row][column].setVisited(true);//since we know we can go to the right, this is true
                    m.maze[row][column].setRight(false);//we go into the right and set right to be true


                    //load the east movement onto the stack bc the coordinates are right

                    s.push(eastMovement);


                } else if (options[randomIndex] == 2) {//west

                    column = column - 1;
                    int[] westMovement = new int[2];
                    westMovement[0] = row;
                    westMovement[1] = column;
                    m.maze[row][column].setVisited(true);
                    m.maze[row][column].setRight(false);

                    //since we know we can go to the left, this is true
                    //There is no right wall so we do not have to worry about that.
                    s.push(westMovement);

                } else if (options[randomIndex] == 3) {//north

                    row = row + 1;
                    int[] northMovement = new int[2];
                    northMovement[0] = row;
                    northMovement[1] = column;
                    m.maze[row][column].setVisited(true);//since we know we can go below, this is true
                    //load the south movement onto the stack bc the coordinates are right
                    s.push(northMovement);

                } else if (options[randomIndex] == 4) {//south
                    row = row - 1;
                    int[] northMovement = new int[2];
                    m.maze[row][column].setVisited(true);//since we know we can go below, this is true
                    m.maze[row][column].setBottom(true);

                    northMovement[0] = row;
                    northMovement[1] = column;
                    //load the south movement onto the stack bc the coordinates are right
                    s.push(northMovement);
                }
            }else {//since we covered our bases if there is not an open spot we pop nextPossibleLevel off the stack
                    s.pop();
                }
            //Now that we have gone through the whole maze, we need to reset has visited, but we have established a path through the maze
        }
        for (int i = 0; i < m.maze.length; i++) {
            for (int j = 0; j < m.maze.length; j++) {
                m.maze[i][j].setVisited(false);
            }
        }
        return m;//return the maze object
    }
    public static int[] spotsOpen(Cell[][] maze, int[] spot, int upperBoundRows, int upperBoundCols){
        int count = 0;

        boolean east = false;
        boolean south = false;
        boolean north = false;
        boolean west = false;

        if(spot[0] >= 1 && maze[spot[0]-1][spot[1]].getVisited() == false){//south
            count++;
            south = true;
        }
        if(spot[0] < upperBoundRows-1  && maze[spot[0]+1][spot[1]].getVisited() == false){//north
            count++;
            north = true;
        }
        if(spot[1] < upperBoundCols-1  && maze[spot[0]][spot[1]+ 1].getVisited() == false){//east
            count++;
            east = true;

        }
        if (spot[1] >= 1 && maze[spot[0]][spot[1]-1].getVisited() == false){//west
            count++;
            west = true;
        }

        int[] possibleChoices = new int[count];

        int fillCheck = 0;
        while(fillCheck < count){
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
        if (path == true) {
            String mazePrinted = "";
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    mazePrinted += "|---|";
                    if (path == true) {
                        if (this.maze[i][j].getVisited() == true) {//make these if statements
                            mazePrinted += "*";
                        }
                        if (this.maze[i][j].getBottom() == true) {
                            mazePrinted += "|---|";
                        }
                        if ((this.maze[i][j].getRight()) == true) {
                            mazePrinted += "|";
                        }
                        //check if the bottom is there
                        //check to see if right wall is there
                    }
                }
                mazePrinted += "\n"; // newline character after for loop
            }
            System.out.println(mazePrinted);

        } else {
            String mazePrinted = "";
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    mazePrinted += "|---|";
                    if (path == true) {
                    }
                    if (this.maze[i][j].getBottom() == true) {
                        mazePrinted += "|---|";
                    }
                    if ((this.maze[i][j].getRight()) == true) {
                        mazePrinted += "|";
                    }
                }
                mazePrinted += "\n"; // newline character after for loop
            }
            System.out.println(mazePrinted);
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        //check to see if there is a right wall or a bottom. Do this before you do anything.
        //there is no bottom or right wall, then you can go from there
        Q1Gen q = new Q1Gen();
        q.add(new int[]{0, 0});

        while (!(q.isEmpty())) {

            int[] frontElement = (int[]) q.remove();


            this.maze[frontElement[0]][frontElement[1]].setVisited(true);

            if (frontElement[0] == (this.rows - 1) && frontElement[1] == (this.cols - 1)) {
                break;
            } else {
                //make a check to avoid index errors
                //check east neighbor

                if (frontElement[1] < this.rows) {
                    Cell east = this.maze[frontElement[0]][frontElement[1] + 1];
                    if (east.getVisited() == false && this.maze[frontElement[0]][frontElement[1]].getRight() == false) {
                        int[] eastOfElement = new int[2];
                        eastOfElement[0] = frontElement[0] + 1;
                        eastOfElement[1] = frontElement[1];

                        q.add(eastOfElement);

                    }
                }
                //check west neighbor
                if (frontElement[1] >= 1) {
                    Cell west = this.maze[frontElement[0]][frontElement[1] - 1];
                    if (west.getVisited() == false && west.getRight() == false) {
                        int[] westOfElement = new int[2];
                        westOfElement[0] = frontElement[0] - 1;
                        westOfElement[1] = frontElement[1];

                        q.add(westOfElement);

                    }
                }
                if (frontElement[0] >= 1) {
                    Cell south = this.maze[frontElement[0] - 1][frontElement[1]];
                    if (south.getVisited() == false && this.maze[frontElement[0]][frontElement[1]].getBottom() == false) {
                        int[] southOfElement = new int[2];
                        southOfElement[0] = frontElement[0];
                        southOfElement[1] = frontElement[1] - 1;

                        q.add(southOfElement);
                    }
                }
                if (frontElement[0] < this.cols) {
                    Cell north = this.maze[frontElement[0] + 1][frontElement[1]];
                    if (north.getVisited() == false && north.getBottom() == false) {
                        int[] northOfElement = new int[2];
                        northOfElement[0] = frontElement[0];
                        northOfElement[1] = frontElement[1] - 1;

                        q.add(northOfElement);
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        /* Any testing can be put in this main function */

        MyMaze x = new MyMaze(6,6);
        x.makeMaze(x.rows,x.cols);

    }
}
