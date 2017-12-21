/**
 * Created by lchoo on 2/9/16.
 */
public class MathTools {
    public static double absoluteValue(double n){
        if (n < 0) {
            n *= -1;
        }
        return n;
    }
    public static double power (double base, int exponent){
        int temp = 0;
        if(exponent > 0 ){
            while (temp < exponent){
                base = base*exponent;
            }
            return base;
        }
        else {
            int power = exponent*-1;
            while(temp<power){
                base = 1/(base*power);
            }
            return base;
        }

    }
    public static double nthRoot (double value, int root){
        if (root == 0){
            return 0;
        }
        if(value <= 0){
            return 0;
        }

        double tolerance = 0.0000000001;
        double xk;
        double xkplus1;
        double change;
        do {

            if (root > 0) {
                xk = value;
            } else {
                xk = 1 / value;
            }
            xkplus1 = 1 / root * ((root - 1) * xk + (value / power(xk, (root - 1))));
            change = absoluteValue(xkplus1 - xk);
            xkplus1 = xk;
        }
        while(change>tolerance);

        return xkplus1;
    }
    public static String scientificNotation (double n){
        int count = 0;
        if(n>10){
            while(n>10){
                n= n / 10;
                count++;
            }
            return n + "x 10 ^ " + count ;

        }
        while(n<1){
            n= n * 10;
            count++;
        }
        return n + "x 10 ^ " + count ;

    }

}
