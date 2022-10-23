package QXD20222.CalculadoraTCP;

public class Calculadora {

    public double operation (double n1, double n2, char op) {
        double result = 0;

        if( op == '+') {
            result = n1 + n2;
        } else if(op == '-') {
            result = n1 - n2;
        } else if(op == '*') {
            result = n1 * n2;
        } else if(op == '/') {
            if(n2 != 0)
                result = n1 / n2;
        }

        return result;
    }
}
