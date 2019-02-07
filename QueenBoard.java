public class QueenBoard{

  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
    toEmpty();
  }

  public void toEmpty(){
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        board[i][x] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if(board[r][c] != 0){ //if the square has been marked or already has a queen
      return false;
    }else{
      makeX(r, c); //a method to mark the places where this queen can attack
      return false;
    }
  }

  private boolean removeQueen(int r, int c){
    if(board[r][c] == -1){
      board[r][c] == 0;
      makeZero(r, c); //a method that backtracks and unmarks the queen's territory
      return true;
    }else{
      return false;
    }
  }
}
