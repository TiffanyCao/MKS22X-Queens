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

  private void makeX(int r, int c){
    for(int i = c+1; i < board[r].length; i++){
      board[r][i] = board[r][i] + 1;
    }
    for(int x = r+1; x < board.length; x++){
      board[x][c+1] = board[x][c+1] + 1;
    }
    for(int y = r+1; y < board.length; y++){
      for(int z = c+1; z < board[y].length; z++){
        board[y][z] = board[y][z] + 1;
      }
    }
  }

  private boolean removeQueen(int r, int c){
    if(board[r][c] == -1){
      board[r][c] = 0;
      makeLess(r, c); //a method that backtracks and unmarks the queen's territory
      return true;
    }else{
      return false;
    }
  }

  private void makeLess(int r, int c){
    for(int i = c+1; i < board[r].length; i++){
      board[r][i] = board[r][i] - 1;
    }
    for(int x = r+1; x < board.length; x++){
      board[x][c+1] = board[x][c+1] - 1;
    }
    for(int y = r+1; y < board.length; y++){
      for(int z = c+1; z < board[y].length; z++){
        board[y][z] = board[y][z] - 1;
      }
    }
  }
}
