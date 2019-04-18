
import java.util.Scanner;

public class Calculator
{
    /* Method is used recursively to break down expression string to two operands  */
    static float Eval(String expression){
        float result, a, b;
        int findx, lindx, snum;
        char operator;

        //Searches for outer most parenthesis of the expression
        findx = expression.indexOf('(');
        lindx = expression.lastIndexOf(')');

        if( findx == -1){ //if statement is only entered if there are no more parenthesis leaving only two operands
            snum = 0;
            if(0 == expression.indexOf('-')){ //checks if first operand is negative
                snum = 1;
            }//end if

            //searches for index of operator
            if(expression.indexOf('*', snum) != -1){
                lindx = expression.indexOf('*', snum);
            }else if(expression.indexOf('/', snum) != -1){
                lindx = expression.indexOf('/', snum);
            }else if(expression.indexOf('+', snum) != -1){
                lindx = expression.indexOf('+', snum);
            }else if(expression.indexOf('-', snum) != -1){
                lindx = expression.indexOf('-', snum);
            }//end if

            a = Float.parseFloat(expression.substring(0, lindx));
            operator = expression.charAt(lindx);
            b = Float.parseFloat(expression.substring(lindx + 1, expression.length()));

        }else{ //else statement is entered if there are still parenthesis in the expression

            if(findx == 0){ //beginning part of the expression is surrounded by parenthesis
                a = Eval(expression.substring(findx +1, lindx)); //method calls itself (recursive)
                operator = expression.charAt(lindx + 1);
                b = Float.parseFloat(expression.substring(lindx + 2, expression.length()));

            }else{ //ending part contains parenthesis
                a = Float.parseFloat(expression.substring(0, findx - 1));
                operator = expression.charAt(findx - 1);
                b = Eval(expression.substring(findx + 1, lindx)); //recursive call
            }
        }//end if

        //Switch statement uses operator to decide what operation to do
        switch(operator){
            case '-':
                result = a - b;
                break;
            case '+':
                result = a + b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                result = 0;
                break;
        }//end switch

        System.out.println("Working with expression:  " + expression);
        System.out.println("result is " + result);
        return result;
    }// end Eval

    public static void main(String[] args) {
        int again;

        if (args.length > 0){
            for(String ex:args){
                System.out.println("The result of the expression " + ex + " is " + Eval(ex));
            }

        } else {
            do {
                String input;
                System.out.println("Enter your math expression");
                Scanner scanIn = new Scanner(System.in);
                input = scanIn.nextLine();

                float result = Eval(input);

                System.out.println("The result of the expression " + input + " is " + result);
                System.out.println("\nWould you like another expression?\n Enter 1 for Yes and 0 for No\n");

                again = scanIn.nextInt();

            } while (again == 1);
        }
    }// end main
}// end Calculator

/* EOF */
