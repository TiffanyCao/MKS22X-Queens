import java.util.*;

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
      board[r][c] = -1;
      makeX(r, c); //a method to mark the places where this queen can attack
      return false;
    }
  }

  private void makeX(int r, int c){
    for(int i = c+1; i < board[r].length; i++){
      board[r][i] = board[r][i] + 1;
    }
    int z = c+1;
    for(int y = r+1; y < board.length; y++){
        board[y][z] = board[y][z] + 1;
        z++;
    }
    z = c+1;
    for(int y = r-1; y >= 0; y--){
      board[y][z] = board[y][z] + 1;
      z++;
    }
  }

  private boolean removeQueen(int r, int c){
    if(board[r][c] == -1){ //if there is a queen present on this square
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
    int z = c+1;
    for(int y = r+1; y < board.length; y++){
        board[y][z] = board[y][z] - 1;
        z++;
    }
    z = c+1;
    for(int y = r-1; y >= 0; y--){
      board[y][z] = board[y][z] - 1;
      z++;
    }
  }

  public String toString(){
    String result = "";
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] == -1){
          result += "Q ";
        }else{
          result += "" + board[i][x] + " ";
        }
        if(x == board[i].length - 1){
          result += "\n";
        }
      }
    }
    return result;
  }

  public boolean solve(){
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] != 0) return false;
      }
    }
    return solveH(0, 0, 0, board.length);
  }

  public boolean solveH(int r, int c, int added, int length){
    if(c >= length) return added == length;
    if(r >= length && r == added) return false;
    int queenR = 0;
    int queenC = 0;
    if(r >= length){
      removeQueen(queenR, queenC);
      solveH(queenR+1, queenC, added-1, length);
    }else if(board[r][c] == 0){
      addQueen(r, c);
      queenR = r;
      queenC = c;
      solveH(0, c+1, added+1, length);
    }else{
      solveH(r+1, c, added, length);
    }
    return false;
  }


  public static void main(String[] args){
    QueenBoard board1 = new QueenBoard(5);

    System.out.println("---Testing addQueen and removeQueen---");
    board1.addQueen(2, 0);
    System.out.println(board1);

    QueenBoard board2 = board1;
    board2.removeQueen(1, 0);
    System.out.println(board2);

    QueenBoard board3 = new QueenBoard(4);
    System.out.println(board3.solve());
    System.out.println(board3);
  }
}
