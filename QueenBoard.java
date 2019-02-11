import java.util.*;

public class QueenBoard{

  private int[][] board;

  /**A constructor for a chessboard of the given size
  *@param size
  */
  public QueenBoard(int size){
    board = new int[size][size];
    toEmpty();
  }

  /*A method to clear the board and fill it with zeroes
  */
  public void toEmpty(){
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        board[i][x] = 0;
      }
    }
  }

  /**A method to place a queen on the board and mark all the places in danger
  *@param r the row of the queen
  *@param c the column of the queen
  *@return true if the queen can be placed
           false if the queen can't be placed
  */
  private boolean addQueen(int r, int c){
    if(board[r][c] != 0){ //if the square has been marked or already has a queen
      return false;
    }else{
      makeX(r, c); //a method to mark the places where this queen can attack
      board[r][c] = -1; //place a queen
      return true;
    }
  }

  /**A helper method to mark all the places in danger
  *@param r the row of the queen
  *@param c the column of the queen
  */
  private void makeX(int r, int c){
    for(int i = c+1; i < board[r].length; i++){ //mark all the places directly to the right of the queen
      board[r][i] = board[r][i] + 1;
    }
    for(int i = 0; i < board.length; i++){ //mark all the places up and down the row of the queen
      board[i][c] = board[i][c] + 1;
    }
    int z = c+1;
    for(int y = r+1; y < board.length; y++){ //mark all the diagonals (downwards)
      if(z < board.length){ //provided that this is within the column range
        board[y][z] = board[y][z] + 1;
        z++;
      }
    }
    z = c+1;
    for(int y = r-1; y >= 0; y--){ //mark all the diagonals (upwards)
      if(z < board.length){ //provided that this is within the column range
        board[y][z] = board[y][z] + 1;
        z++;
      }
    }
  }

  /**A method to remove a queen on the board and unmark all the places in danger
  *@param r the row of the queen
  *@param c the column of the queen
  *@return true if the queen can be removed
           false if the queen can't be removed
  */
  private boolean removeQueen(int r, int c){
    if(board[r][c] == -1){ //if there is a queen present on this square
      makeLess(r, c); //a method that backtracks and unmarks the queen's territory
      board[r][c] = 0; //restores the place to empty
      return true;
    }else{
      return false; //if there is no queen present on this square
    }
  }

  /**A helper method to unmark all the places in danger
  *@param r the row of the queen
  *@param c the column of the queen
  */
  private void makeLess(int r, int c){
    for(int i = c+1; i < board[r].length; i++){ //unmark all the places directly to the right of the queen
      board[r][i] = board[r][i] - 1;
    }
    for(int i = 0; i < board.length; i++){ //unmark all the places up and down the row of the queen
      board[i][c] = board[i][c] - 1;
    }
    int z = c+1;
    for(int y = r+1; y < board.length; y++){ //unmark all the diagonals (downwards)
      if(z < board.length){ //provided that this is within the column range
        board[y][z] = board[y][z] - 1;
        z++;
      }
    }
    z = c+1;
    for(int y = r-1; y >= 0; y--){ //unmark all the diagonals (upwards)
      if(z < board.length){ //provided that this is within the column range
        board[y][z] = board[y][z] - 1;
        z++;
      }
    }
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *All others are displayed as underscores '_'
  *There are spaces between each symbol
  * _ _ Q _
  * Q _ _ _
  * _ _ _ Q
  * _ Q _ _
  *(pythonic string notation for clarity,
  *excludes the charatcer up to the *)
  */
  public String toString(){
    String result = "";
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] == -1){ //if there's a queen
          result += "Q ";
        }else{
          result += '_' + " "; //if there isn't a queen
        }
        if(x == board[i].length - 1){ //new row
          result += "\n";
        }
      }
    }
    return result;
  }

  /**A method that attempt to solve the board
  *@return false when the board is not solveable and leaves the board filled with zeroes
  *        true when the board is solveable and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    for(int i = 0; i < board.length; i++){ //if the board is not empty
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] != 0) throw new IllegalStateException();
      }
    }
    if(solveH(0)){ //if it's solveable
      return true;
    }else{ //if it's not solveable
      toEmpty(); //empty the board
      return false;
    }
  }

  /**A method that counts the current number of queens on the board
  *@return int number of queens
  */
  private int countQueens(){
    int count = 0;
    for(int i = 0; i < board.length; i++){ //loops throw the board for a queen
      for(int y = 0; y < board[i].length; y++){
        if(board[i][y] == -1) count++;
      }
    }
    return count;
  }

  /**A recursive helper method that tests whether a solution is possible
  *@param int c is the present column
  *@return false when the board is not solveable and leaves the board filled with zeroes
  *        true when the board is solveable and leaves the board in a solved state
  */
  public boolean solveH(int c){
    if(c >= board.length) return true; //if the last column is reached, that's a solution
    for(int i = 0; i < board.length; i++){ //loops through all the rows
      if(addQueen(i, c)){ //if a queen can be added
        if(solveH(c+1)){ //check if the next column has an available space
          return true;
        }
        removeQueen(i, c); //if not, remove the queen
      }
    }
    return false; //the board is not solveable after all the options have been explored
  }

  /**A method that counts the number of possible solutions to the board
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for(int i = 0; i < board.length; i++){ //if the board is not empty
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] != 0) throw new IllegalStateException();
      }
    }
    return countH(0); //helper function
  }

  /**A helper recursive method that figures out the number of possible solutions
  *@param int c is the current column
  *@return int is the number of solutions
  */
  private int countH(int c){
    int count = 0; //counter keeping track of number of solutions
    if(c >= board.length) return 1; //if the last column is reached and the max number of queens has been placed, that counts as one solution
    for(int i = 0; i < board.length; i++){ //loops through all the rows
      if(addQueen(i, c)){ //if a queen can be placed
        count += countH(c+1); //adds one if the configuration had a solution, otherwise 0 is added
        removeQueen(i, c); //remove the queen so the next possible placement in this column can be tested
      }
    }
    return count; //returns the number of solutions;
  }

  public static void main(String[] args) {
    QueenBoard board1 = new QueenBoard(5);

    System.out.println("---Testing addQueen and removeQueen---");
    System.out.println("*5 X 5 board: adding queen at (2, 0): should return true*");
    System.out.println(board1.addQueen(2, 0));
    System.out.println(board1);
    System.out.println("*5 X 5 board: removing queen at (2, 0): should return true*");

    QueenBoard board2 = board1;
    System.out.println(board2.removeQueen(2, 0));
    System.out.println(board2);

    System.out.println("*5 X 5 board: adding queen at (1, 0): should return true*");
    System.out.println(board2.addQueen(1, 0));
    System.out.println(board2);

    System.out.println("*5 X 5 board: adding queen at (2, 0): should return false*");
    System.out.println(board2.addQueen(2, 0));
    System.out.println(board2);

    System.out.println("*5 X 5 board: adding queen at (2, 1): should return false*");
    System.out.println(board2.addQueen(2, 1));
    System.out.println(board2);

    System.out.println("*5 X 5 board: adding queen at (4, 1): should return true*");
    System.out.println(board2.addQueen(4, 1));
    System.out.println(board2);

    System.out.println("*5 X 5 board: removing queen at (4, 1): should return true*");
    System.out.println(board2.removeQueen(4, 1));
    System.out.println(board2);
  }
}
