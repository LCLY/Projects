import java.util.Stack;

/**
 * Created by LeyYen on 4/18/2016.
 */
public class Evaluator {
    public static Double evaluate(String expression) {
        Stack stack = new Stack();
        String[] number = expression.split(" ");
        for (String numbers : number) {
            if (numbers.matches("[0-9]"))
                stack.push(Double.parseDouble(numbers));
            else {
                double op2 = (Double) stack.pop();
                double op1 = (Double) stack.pop();
                if (numbers.equals("+")) {
                    stack.push(op1 + op2);
                } else if (numbers.equals("-")) {
                    stack.push(op1 - op2);
                } else if (numbers.equals("*")) {
                    stack.push(op1 * op2);
                } else if (numbers.equals("/")) {
                    stack.push(op1 / op2);
                } else throw new RuntimeException("unknown operator");

            }
        }
        return (Double) stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("5 1 2 + 4 * + 3 -"));
        //System.out.println(evaluate("4 5 - 2 /"));

    }
}
