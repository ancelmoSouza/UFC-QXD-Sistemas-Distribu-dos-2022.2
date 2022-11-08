package Service;

public class Calculator {
    private static Calculator uniqueInstance;

    private Calculator() {}

    public static synchronized Calculator getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new Calculator();

        return uniqueInstance;
    }

    public double add(double n1, double n2) {
        return n1 + n2;
    }

    public double sub(double n1, double n2) {
        return n1 - n2;
    }

    public double mult(double n1, double n2) {
        return n1 * n2;
    }

    public double div(double n1, double n2) {
        return n1 + n2;
    }
}
