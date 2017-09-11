import java.util.Scanner;

public class Converter{
  public static boolean isOperand(char c){
    if(
      (c>='a' && c<='z') ||
      (c>='A' && c<='Z') ||
      (c>='0' && c<='9')){
        return true;
      }else{
        return false;
      }
  }

  public static boolean isOperator(char c){
    return c=='+' || c=='-' || c=='*' || c=='/';
  }

  // turn an operator to a precedence order
  public static int precedenceOrder(char c){
    if(c == '*' || c == '/' ){
      return 2;
    }else {
      return 1;
    }
  }

  public static void main(String[] args){
    // read expression from keyboard
    Scanner scan = new Scanner(System.in);
    CharStack opstack = new CharStack();
    String result = "";

    System.out.println("Input an infix expression:");
    String line = scan.nextLine();
    boolean error = false; // too many ')'

    // examine the expression
    for(int i=0; i<line.length(); i++){
      char current = line.charAt(i);

      if(isOperand(current)){ // if operand, write out
        result += current;
      }else if(current == '('){ // if '(', push to opstack
        opstack.push(current);
      }else if(current == ')'){ // if ')', pop and write out till '(' is popped
        char top = opstack.pop();
        while(top != '('){
          result += top;
          top = opstack.pop();
        }
      }else if(isOperator(current)){
        // if operator, push it on the opstack.
        // However, first remove any operators already on the opstack
        // that have higher or equal precedence and write them out.
        while (!opstack.isEmpty() &&
          isOperator(opstack.peek()) &&
          precedenceOrder(opstack.peek())>=precedenceOrder(current)){
          result += opstack.pop();
        }
        opstack.push(current);
      }else {
        System.err.println("invalid character: "+current);
      }
    }
    while(!opstack.isEmpty()){
      result += opstack.pop();
    }
    System.out.println("The postfix expression: "+result);
  }
}