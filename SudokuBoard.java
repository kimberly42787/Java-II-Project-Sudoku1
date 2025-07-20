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
import java.util.Scanner;

public class SudokuBoard {

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
   public SudokuBoard(String filename) throws FileNotFoundException {
   
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
}



