import java.util.Scanner;

public class ParenthesisTester{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    String line = scan.nextLine();
    for(int i=0; i<line.length(); i++){
      System.out.println(line.charAt(i));
    }
  }
}