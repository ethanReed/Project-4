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
                    m.maze[row][column].setRight(true);//we go into the right and set right to be true
                    //load the east movement onto the stack bc the coordinates are right
                    s.push(eastMovement);


                } else if (options[randomIndex] == 2) {//west

                    column = column - 1;
                    int[] westMovement = new int[2];
                    westMovement[0] = row;
                    westMovement[1] = column;
                    m.maze[row][column].setVisited(true);//since we know we can go to the left, this is true
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
        }
//        Q1Gen q = new Q1Gen();
//        q.add(new int[]{0, 0});
//
//        while (!(q.isEmpty())) {
//
//            int[] frontElement = (int[]) q.remove();
//
//            if (frontElement[0] == this.rows - 1 && frontElement[1] == this.cols - 1) {
//                break;
//            } else {
//                int column = frontElement[1];
//                int row = frontElement[0];
//                int[] options = spotsOpen(this.maze, frontElement, row, column);
//
//                for (int i = 0; i < options.length; i++) {
//                    if (options[i] == 1) {//East
////                int[] eastMovement = nextPossiblelevel[[0]+1][1];
//
//                        row = row + 1;
//                        int[] eastMovement = new int[2];
//                        eastMovement[0] = row;
//                        eastMovement[1] = column;
//                        this.maze[row][column].setVisited(true);//since we know we can go to the right, this is true
//                        this.maze[row][column].setRight(true);//we go into the right and set right to be true
//                        //load the east movement onto the stack bc the coordinates are right
//                        q.add(eastMovement);
//                    } else if (options[i] == 2) {//west
//                        row = row - 1;
//                        int[] westMovement = new int[2];
//                        westMovement[0] = row;
//                        westMovement[1] = column;
//                        this.maze[row][column].setVisited(true);//since we know we can go to the left, this is true
//                        //There is no right wall so we do not have to worry about that.
//                        q.add(westMovement);
//                    } else if (options[i] == 3) {//south
//                        column = column - 1;
//                        int[] southMovement = new int[2];
//                        southMovement[0] = row;
//                        southMovement[1] = column;
//                        this.maze[row][column].setVisited(true);//since we know we can go below, this is true
//                        this.maze[row][column].setBottom(true);//we go into below and set right to be true
//                        //load the south movement onto the stack bc the coordinates are right
//                        q.add(southMovement);
//                    } else if (options[i] == 4) {//north
//                        column = column + 1;
//                        int[] northMovement = new int[2];
//                        this.maze[row][column].setVisited(true);//since we know we can go below, this is true
//                        northMovement[0] = row;
//                        northMovement[1] = column;
//                        //load the south movement onto the stack bc the coordinates are right
//                        q.add(northMovement);
//                    }
//                }
//            }
//        }
//        printMaze(true);
//    }
    public static void main(String[] args){
        /* Any testing can be put in this main function */

        MyMaze x = new MyMaze(6,6);
        x.makeMaze(x.rows,x.cols);

    }
}
