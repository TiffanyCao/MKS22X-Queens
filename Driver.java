public class Driver{
  public static void main(String[] args){

    System.out.println("---Testing solve() and countSolutions()---");
    QueenBoard one = new QueenBoard(1);
    System.out.println("*testing 1 X 1 board: should return true*");
    System.out.println(one.solve());
    System.out.println(one);

    QueenBoard two = new QueenBoard(2);
    System.out.println("*testing 2 X 2 board: should return false*");
    System.out.println(two.solve());
    System.out.println(two);

    QueenBoard three = new QueenBoard(3);
    System.out.println("*testing 3 X 3 board: should return false*");
    System.out.println(three.solve());
    System.out.println(three);

    QueenBoard four = new QueenBoard(4);
    System.out.println("*testing 4 X 4 board: should return true*");
    System.out.println(four.solve());
    System.out.println(four);
    four.toEmpty();
    System.out.println("*countSolutions should return 2*");
    System.out.println(four.countSolutions());

    QueenBoard five = new QueenBoard(5);
    System.out.println("*testing 5 X 5 board: should return true*");
    System.out.println(five.solve());
    System.out.println(five);
    five.toEmpty();
    System.out.println("*countSolutions should return 10*");
    System.out.println(five.countSolutions());

    QueenBoard six = new QueenBoard(6);
    System.out.println("*testing 6 X 6 board: should return true*");
    System.out.println(six.solve());
    System.out.println(six);
    six.toEmpty();
    System.out.println("*countSolutions should return 4*");
    System.out.println(six.countSolutions());

    QueenBoard seven = new QueenBoard(7);
    System.out.println("*testing 7 X 7 board: should return true*");
    System.out.println(seven.solve());
    System.out.println(seven);
    seven.toEmpty();
    System.out.println("*countSolutions should return 40*");
    System.out.println(seven.countSolutions());

    QueenBoard eight = new QueenBoard(8);
    System.out.println("*testing 8 X 8 board: should return true*");
    System.out.println(eight.solve());
    System.out.println(eight);
    eight.toEmpty();
    System.out.println("*countSolutions should return 92*");
    System.out.println(eight.countSolutions());
  }
}
