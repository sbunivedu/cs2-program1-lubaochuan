import java.util.Scanner;

public class Converter{
  public static boolean isOperand(char c){
    return !(c=='+' || c=='-' || c=='*' || c=='/');
  }

  // turn an operator to a precedence order
  public static int precedenceOrder(char c){
    if(c == '*' || c == '/'){
      return 2;
    }else {
      return 1;
    }
  }

  public static void main(String[] args){
    // read expression from keyboard
    Scanner scan = new Scanner(System.in);
    CharStack stack = new CharStack();

    System.out.println("Input an infix expression:");
    String line = scan.nextLine();
    boolean error = false; // too many ')'

    // examine the expression
    for(int i=0; i<line.length(); i++){
      char current = line.charAt(i);
      if(current == '('){          // case 1: '(' is read
        stack.push(current);
      }else if(current == ')'){    // case 2: ')' is read
        char top = stack.pop();
        while(top != '('){
          System.out.print(top);
          top = stack.pop();
        }
      }else if(isOperand(current)){
        System.out.print(current);
      }else{ // current is an operator: +,-,*,/
        if(stack.isEmpty() || stack.peek()=='('){
          stack.push(current);
        }else if(precedenceOrder(current) > precedenceOrder(stack.peek())){
          stack.push(current);
        }else{
          do{
            char top = stack.pop();
            System.out.print(top);
          }while(!(stack.isEmpty() ||
            precedenceOrder(current) > precedenceOrder(stack.peek())));
          stack.push(current);
        }
      }
    }
    while(!stack.isEmpty()){
      System.out.print(stack.pop());
    }
    System.out.println();
    System.out.println(stack);
  }
}