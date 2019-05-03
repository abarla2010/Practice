package backtracking;

public class KnightTour {

  static int numRowsAndColumns;

  public static void main(String[] args){
    solveKnightTour(8);
  }

  // Checks if the knight tour is possible for a numRowsAndColumns x numRowsAndColumns array
  // Knight can travel in L shape as done in a chess board
  // Starting position of Knight is (0,0)
  // Returns array of solution with elements marked with unique numbers between 0 and (numRowsAndColumns)(numRowsAndColumns)
  // all of which together show the path the knight covers
  public static void solveKnightTour(int numRowsAndColumns){
    if(numRowsAndColumns < 0) return;
    int[][] solution = new int[numRowsAndColumns][numRowsAndColumns];


    //Initializing the solution array to -1
    for(int i = 0; i < numRowsAndColumns; i++){
      for(int j = 0; j < numRowsAndColumns; j++){
        solution[i][j] = -1;
      }
    }

    solution[0][0] = 0;

    int[] xMove = new int[]{1, 2, 1, 2, -1, -2, -1, -2};
    int[] yMove = new int[]{2, 1, -2, -1, 2, 1, -2, -1};

    if(knightTourUtil(solution, 0, 0, 1, xMove, yMove)){
      printSolution(solution);
    }
  }


  private static boolean knightTourUtil(int[][] solution, int x, int y, int count, int[] xMove, int[] yMove){

    int currX, currY, i;
    if(count == numRowsAndColumns * numRowsAndColumns){
      return true;
    }

    // Find all possibilities for the current x,y
    // and check if the solution exists
    for(i = 0; i < numRowsAndColumns; i++){
      currX = x + xMove[i];
      currY = y + yMove[i];


      if(isSafePoint(currX, currY, solution)){
        // Assume that this point falls in the path of the correct solution
        solution[currX][currY] = count;
        if (knightTourUtil(solution, currX, currY, count + 1, xMove, yMove)) return true;
        else {
          // backtrack - i.e., go back to the first assumption and revert it
          solution[currX][currY] = -1;
        }
      }
    }

    return false;
  }


  // Return true if x,y falls within the boundary and also if it doesn't denote a -1 in the
  // solution array
  private static boolean isSafePoint(int x, int y, int[][] solution){
    return (x >= 0 && y >= 0 && x < numRowsAndColumns
        && y < numRowsAndColumns && solution[x][y] == -1);
  }

  private static void printSolution(int sol[][]) {
    for (int x = 0; x < numRowsAndColumns; x++) {
      for (int y = 0; y < numRowsAndColumns; y++)
        System.out.print(sol[x][y] + " ");
      System.out.println();
    }
  }

}
