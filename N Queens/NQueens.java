/* THIS  CODE  WAS MY OWN WORK , IT WAS  WRITTEN  WITHOUT  CONSULTING  ANY
SOURCES  OUTSIDE  OF  THOSE  APPROVED  BY THE  INSTRUCTOR. _Gene Lee_ */

import java.util.Stack;

public class NQueens {
 
  public static Stack<Integer> track;  // Define a stack to keep track of queens by their column #
  public static int count = 0, N; // Count # of solutions; N stores n
  public static int i, j; // Indices for rows and columns (col) respectively

  //finds and prints out all solutions to the n-queens problem
  public static int solve(int n) {
    N = n;
    track = new Stack<>();  // Create the stack

    track.push(0);  // Add 0 to stack for (0,0)

    if (n == 1){  // For 1x1 board
      printSolution(track);
      return 1;
    }

    // Go through the board, which has rows 0 to N - 1 and columns 0 to N - 1
    for (i = 1; i < n; i++){  // Check each row, starting at row 1
      j = compareStack(0); // Check for a valid Q position; pass column # (start at 0)

      // When i = n - 1 (last row)
      if (track.size() == n){ // If there are n positions/queens in the stack, then a solution has been found
        printSolution(track);
        count++;  // Add to the count of solutions
        
        track.pop();  // Pop the last queen
        j = n;  // Set col position to n to backtrack
      }

      // Backtracking
      while (j == n){   // If j == n, then a valid col position was not found in the current row
        if (track.empty() == true){  // If the stack is empty, then all possible solutions have been found
          i = n;  // Enables exiting of outer for-loop
          break;
        }

        i--;  // Backtrack to previous row
        j = track.pop() + 1;   // Move the queen in this row to the next column

        if (j == n)  // If the queen has gone past the end of the row, backtrack again
          continue;

        j = compareStack(j); // Check for the next valid position
      }
    }

    //update the following statement to return the number of solutions found
    return count;  
  }//solve()

  public static int compareStack(int j){  // Compares current position with positions recorded in the stack
    int a;  // Stores j for for-loop

    for (a = j; a < N; a++){  // Check each column
      int b;  // Index for stack

      for (b = 0; b < track.size(); b++) // Check each # stored in stack
        /* Stop checking if current column # is equal to # in stack (column conflict) or the difference between the column #s is equal to
        the difference between the row #s, as b represents row # (diagonal conflict) */
        if (a == track.get(b) || Math.abs(a - track.get(b)) == Math.abs(i - b))
          break;

      if (b == track.size()){ /* If you checked through the stack, meaning there was no conflict, push the current column # into the stack
                                and stop checking the columns */
        track.push(a);
        break;
      }
    }

    return a; // Return current column # for j
  }
  
  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();  
  }//printSolution()
  
  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {
  
  int n = 8;
  
  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if   
  }//if
  
  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()
  
}