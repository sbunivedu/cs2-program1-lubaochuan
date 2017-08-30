import java.util.Scanner;

public class ParenthesisTester{
  public static void main(String[] args){
    // read expression from keyboard
    Scanner scan = new Scanner(System.in);
    CharStack stack = new CharStack();

    System.out.println("Input a parenthesized string:");
    String line = scan.nextLine();
    boolean error = false; // too many ')'

    // examine the expression
    for(int i=0; i<line.length(); i++){
      char current = line.charAt(i);
      if(current == '('){          // case 1: '(' is read
        stack.push(current);
      }else if(current == ')'){    // case 2: ')' is read
        if(!stack.isEmpty()){
          stack.pop();
        }else{
          error = true;
        }
      }
    }

    // make a decision by examining the state and the error flag
    if(!stack.isEmpty()){
      System.out.println("Bad expression: too many left parens.");
      return;
    }
    if(error){
      System.out.println("Bad expression: too many right parens.");
      return;
    }
    System.out.println("Properly parenthesized expression.");
  }
}
