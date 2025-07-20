// Kimberly Domingo
// CS 143
// Sudoku #1 (Board Setup)

// This program is to verify the constructor and the toString method that
// I wrote in SudokuBoard.

// This will be print out a 9 x 9 2d array board with numbers (0-9) or an empty cell.

/*
 * Pre: The file (data1.sdk) must contain a 9 x 9 format with the characters (1-9) 
 * and '.' to represent empty cells
 * 
 * Post: A board is created from the file (data1.sdk) and is printed in the console.
 * The board is 9 x 9. It contains the characters (0-9). The '.' is represented as 
 * a blank space. 
 * 
 */

public class PlaySudoku {

   public static void main(String[] args) throws Exception {
      SudokuBoard board = new SudokuBoard("data1.sdk");
      System.out.println(board);
      
   }
}


/*
2     | 1   5 |     3 
  5 4 |       | 7 1   
  1   | 2   3 |   8   
------+-------+------
6   2 | 8   7 | 3   4 
      |       |       
1   5 | 3   9 | 8   6 
------+-------+------
  2   | 7   1 |   6   
  8 1 |       | 2 4   
7     | 4   2 |     1 
 */
