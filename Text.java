import java.util.Scanner;

public class Text{

  /*Base colors*/
  public static int BLACK = 30;
  public static int RED = 31;
  public static int GREEN = 32;
  public static int YELLOW = 33;
  public static int BLUE = 34;
  public static int MAGENTA = 35;
  public static int CYAN = 36;
  public static int WHITE = 37;

  /*Text modifiers to be ADDED to a color*/
  public static int BACKGROUND = 10;
  public static int BRIGHT = 60;

  /*Text modifiers that are separate from color*/
  public static int BOLD = 1;
  public static int UNDERLINE = 4;
  public static int INVERTED = 7;

  /*Reset colors*/
  public static void reset(){
    System.out.print("\u001b[0m");
  }


  public static void hideCursor(){
    System.out.print("\u001b[?25l");
  }

  public static void showCursor(){
    System.out.print("\u001b[?25h");
  }

  /*Move the cursor to a specified row/col on the terminal*/
  public static void go(int row,int col){
      System.out.print("\u001b[" + row + ";" + col + "f");
  }

  /*Erases all text on the terminal.*/
  public static void clear(){
    System.out.print("\u001b[2J");
  }

  /*Overloaded Colorize methods.
    c1,c2 and c3 are any color modifiers such as bold/color/background color etc.
  */
  public static String colorize(String text,int c1){
    return ("\u001b[" + c1 + "m"+text+"\u001b[0m");
  }
  public static String colorize(String text,int c1,int c2){
    return ("\u001b[" + c1 + ";" + c2 + "m"+text+"\u001b[0m");
  }
  public static String colorize(String text,int c1,int c2,int c3){
    return ("\u001b[" + c1 + ";" + c2 + ";" + c3 + "m"+text+"\u001b[0m");
  }

}