import org.junit.jupiter.api.Test;
import sample.Operations;
import sample.Polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {

    @Test
    public void addTest() {
        String exp1 = "x^5+7x^3+5";
        String exp2 = "x^2-13";
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.validateAndSet(exp1);
        p2.validateAndSet(exp2);

        Operations op = new Operations();
        String result = "x^5+7x^3+x^2-8";
        Polynomial res = op.addition(p1, p2);

        assertEquals(result, res.polToString(), "The result of adding x^5+7x^3+5 and x^2-13 is x^5+7x^3+x^2-8");
    }

    @Test
    public void subTest() {
        String exp1 = "x^5+7x^3+5";
        String exp2 = "x^2-13";
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.validateAndSet(exp1);
        p2.validateAndSet(exp2);

        Operations op = new Operations();
        String result = "x^5+7x^3-x^2+18";
        Polynomial res = op.subtraction(p1, p2);

        assertEquals(result, res.polToString(), "The result of subtracting x^5+7x^3+5 and x^2-13 is x^5+7x^3-x^2+18");
    }

    @Test
    public void mulTest() {
        String exp1 = "x^5+7x^3+5";
        String exp2 = "x^2-13";
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.validateAndSet(exp1);
        p2.validateAndSet(exp2);

        Operations op = new Operations();
        String result = "x^7-6x^5-91x^3+5x^2-65";
        Polynomial res = op.multiplication(p1, p2);

        assertEquals(result, res.polToString(), "The result of multiplying x^5+7x^3+5 and x^2-13 is x^7-6x^5-91x^3+5x^2-65");
    }

    @Test
    public void divisionTest() {
        String exp1 = "x^5+7x^3+5";
        String exp2 = "x^2-13";
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.validateAndSet(exp1);
        p2.validateAndSet(exp2);

        Operations op = new Operations();
        String quotient = "x^3+20x";
        String rest = "260x+5";
        Polynomial[] result = op.division(p1, p2);

        assertEquals(quotient, result[0].polToString(), "The quotient of dividing x^5+7x^3+5 and x^2-13 is x^3+20x");
        assertEquals(rest, result[1].polToString(), "The rest of dividing x^5+7x^3+5 and x^2-13 is 260x+5");
    }

    @Test
    public void derivationTest() {
        String exp1 = "x^5+7x^3+5";
        Polynomial p1 = new Polynomial();
        p1.validateAndSet(exp1);

        Operations op = new Operations();
        String result = "5x^4+21x^2";
        Polynomial res = op.derivative(p1);

        assertEquals(result, res.polToString(), "The derivative of x^5+7x^3+5 is 5x^4+21x^2");
    }

    @Test
    public void integrationTest() {
        String exp1 = "x^5+7x^3+5";
        Polynomial p1 = new Polynomial();
        p1.validateAndSet(exp1);

        Operations op = new Operations();
        String result = "0.17x^6+1.75x^4+5x";
        Polynomial res = op.integrate(p1);

        assertEquals(result, res.polToString(), "The integral of x^5+7x^3+5 is 0.16x^6+1.75x^4+5x");
    }
}
