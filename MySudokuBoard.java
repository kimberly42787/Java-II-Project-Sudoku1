// Kimberly Domingo
// CS 143
// Sudoku #1 (Board Setup)

// This program is to verify the constructor and the toString method that
// I wrote in SudokuBoard.

// This program reads a text file (.sdk) where 9 lines contains 9 characters
// (1-9) and '.' to represent empty cells.
// The program will load the board from the file and represent empty cells '.' 
// as blank spaces and uses a toString method to return the board in an organized 
// 9x9 board. 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MySudokuBoard {

   // field: sudoku board - This will store char (so empty cells will be represented as ' '. 
   private char[][] board;
   
   /*
   * Constructor: This takes in a file and load the data into a 9x9 2d array.
   * 
   * Pre: The file needs to contain 9 lines per row with 9 rows. It contains 
   * the characters (0-9) and '.' to represent an empty cell.
   * 
   * Post: A 9x9 board is created and filled with characters on the text file at
   * the exact location as the file. The '.' to represent empty cells is turned into
   * blank spaces.
   * 
   */
   public MySudokuBoard(String filename) throws FileNotFoundException {
   
      board = new char[9][9];
      
      Scanner scanner = new Scanner(new File(filename));
      
      for (int row = 0; row < 9; row++) {
         String line = scanner.nextLine();
         
         for (int col = 0; col < 9; col++) {
            char ch = line.charAt(col);
            if (ch == '.') {
               board[row][col] = ' ';
            }
            else {
               board[row][col] = ch;         
            }
         }
      }
      
      scanner.close();
   }

   /*
    * toString: This returns a formatted 9x9 board where it is divided into 
    * 9 smaller blocks.
    *
    * Pre: 9x9 board with characters (1-9) and empty cells
    *
    * Post: An organized board with vertical and horizontal dividers 
    * to create 9 blocks. 
    *
    */
   public String toString() {
      String result = "";
  
      for (int row = 0; row < 9; row++) {
          if (row % 3 == 0 && row != 0) {
              result += "------+-------+------\n";
          }
  
          for (int col = 0; col < 9; col++) {
              if (col % 3 == 0 && col != 0) {
                  result += "| ";
              }
  
              result += board[row][col] + " ";
          }
  
          result += "\n";
      }
  
      return result;
  }

   /*
    * Checks to see if the board is valid based on: 
    * - The characters are valid (only 1-9 and blank space) - isDataValid
    * - No duplicates in row - isRowsValid
    * - No duplicates in column () - isColumnsValid
    * - No duplicates in the 3x3 mini squares - isMiniSquareValid
    *
    * Pre: A 9x9 2d array sudoku (filled or not filled) board
    *
    * Post: Return true if it meets all the rules 
    * Return false if not
    * 
    */

   public boolean isValid() {
      return isDataValid() && isRowsValid() && isColumnsValid() && isMiniSquareValid();
   }

   // Check to make sure all the characters are either '1' to '9' or a ' '.
   // Pre: A 9x9 2d array sudoku board
   // Post: Return true if characters are 1-9 and blank spaces. If not, returns false
   private boolean isDataValid() {
      for (int row = 0; row < 9; row++) {
         for (int col = 0; col < 9; col++) {
            char ch = board[row][col];
            // if the character at the specific index (row, col) is not a blank space
            // and else is less than 1 or greater than 9, return false
            if (ch != ' ' && (ch < '1' || ch > '9')) {
               return false;
            }
         }
      }
      return true;
   }
   
   // Checks to make sure no rows contains a duplicate values
   // USE SET FOR THIS CHECK
   // Multiple space is okay 
   // Pre: A 9x9 2d array sudoku board
   // Post: Returns true if no duplicates in the rows. Returns if there is. 
   private boolean isRowsValid() {
      for (int row = 0; row < 9; row++) {
         Set<Character> s = new HashSet<>();
         for (int col = 0; col < 9; col++) {
            char ch = board[row][col];
            if (ch!= ' ') {
               if (s.contains(ch)) {
                  return false;
               }
               s.add(ch);

            }
         }
      } 
      return true;
 
   }

   // Checks to make sure no columns contains a duplicate values
   // JUST LIKE THE ROWS METHODS
   // Pre: A 9x9 2d array sudoku board
   // Post: Returns true if no duplicates in the columns. Returns if there is. 
   private boolean isColumnsValid() {
      for (int col = 0; col < 9; col++) {
         Set<Character> s = new HashSet<>();
         for (int row = 0; row < 9; row++) {
            char ch = board[row][col];
            if (ch != ' ') {
               if (s.contains(ch)) {
                  return false;
               }
               s.add(ch);
            }
         }
      }
      return true;

   }
   
   // Checks to make sure no duplicates are in the mini squares 
   // Pre: A 9x9 2d array sudoku board
   // Post: Returns true if no duplicates in the mini squares. Returns if there is. 
   private boolean isMiniSquareValid() {
      for (int spot = 1; spot <= 9; spot++) {
         char[][] miniBoard = miniSquare(spot);
         Set<Character> miniSet = new HashSet<>();
               
         for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
               char ch = miniBoard[row][col];
               if (ch != ' ') {
                  if (miniSet.contains(ch)) {
                     return false;
                  }
                  miniSet.add(ch);
               }
            }
         }
      }
      return true;
   }

   
   // FROM PROFESSOR:
   // Creates the mini squares ([3][3] 2D structure) 
   // Pre: A 9x9 2d array sudoku board
   // Post: returns a 2d (3x3) mini squares of the board. 
   private char[][] miniSquare(int spot) {
      char[][] mini = new char[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
  
   /*
    * This method will return true if isValid is returned true and 
    * there are 9 occurrences of every characters (1-9) in the grid
    *
    * Pre: Each cells contains the characters (1-9) or a blank space
    *
    * Post: Returns true if board is completely filled with the characters (1-9), 
    * isValid is true, and each character appears 9 times.
    * If not, it returns false
    * 
    */

   public boolean isSolved() {
      if (!isValid()) {
         return false;
      }

      Map<Character, Integer> counts = new HashMap<>();

      for (int row = 0; row < 9; row++) {
         for (int col = 0; col < 9; col++) {
            char ch = board[row][col];
            if (ch == ' ') {
               return false; // Still empty cells
            }

            if (counts.containsKey(ch)) {
               int currentCount = counts.get(ch);
               counts.put(ch, currentCount + 1);
            }
            else {
               counts.put(ch, 1);
            }
         }
      }
      for (char digit = '1'; digit <= '9'; digit++) {
         if (counts.getOrDefault(digit, 0) != 9) {
            return false;
         }
      }

      return true;
   }

   public boolean solve() {
      
      boolean boardSolved = false;

      // Base Case: invalid board
      if (!isValid()) {
         return false;
      }

      //Base Case: board is already solved
      if (isSolved()) {
         return true;
      }

      // Solve with recursion
      for (int row = 0; row < 9; row++) {
         for (int col = 0; col < 9; col++) {
            if (board[row][col] == ' ') {
               // Check digits 1-9 as a solution
               for (char digit = '1'; digit <= '9'; digit++) {
                  board[row][col] = digit;

                  if (isValid() && solve()) {
                     return true;
                  }

                  board[row][col] = ' ';
               }
               return false;
            }
         }
      }
      return isSolved();
   }
    
}




