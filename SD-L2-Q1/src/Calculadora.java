public class Calculadora {

    public double operation(char op, double n1, double n2) {
        double result = 0;

        if(op == 's') {
            result = n1 + n2;
        } else if(op == 'm') {
            result = n1 - n2;
        } else if(op == 'p') {
            result = n1 * n2;
        }else if (op == 'd') {
            if(n2 != 0) {
                result = n1 / n2;
            }
        }
        return result;
    }
}
