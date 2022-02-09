package sample;

import org.jetbrains.annotations.NotNull;

public class Operations {
    public Polynomial addition(Polynomial p, Polynomial q) {
        return appendPolynomials(p, q);
    }

    public Polynomial subtraction(Polynomial p, Polynomial q) {
        q.negate();
        return appendPolynomials(p, q);
    }

    @NotNull
    private Polynomial appendPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        for (Monomial mon : p.getMonomials()) {
            result.addMonomial(mon);
        }
        for (Monomial mon : q.getMonomials()) {
            result.addMonomial(mon);
        }
        result.check();
        return result;
    }

    public Polynomial multiplication(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        for (Monomial moni : p.getMonomials()) {
            for (Monomial monj : q.getMonomials()) {
                double coef = moni.getCoefficient() * monj.getCoefficient();
                int power = moni.getPower() + monj.getPower();
                Monomial aux = new Monomial(coef, power);
                result.addMonomial(aux);
            }
        }

        result.check();
        return result;
    }

    // "stole" this method from https://gist.github.com/derlin/40545e447fffe7599d26d0a435d9b113
    // and adapted it to the format of my classes, also debugged it and saw it doesn't work
    // for some cases so I added some more stuff
    public Polynomial[] division(Polynomial p, Polynomial q) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = p;
        if (remainder.getMonomials().size() > 0) {
            while (!remainder.isZero() && remainder.degree() >= q.degree()) {
                double coef = remainder.getHighestCoefficient() / q.getHighestCoefficient();
                int deg = remainder.degree() - q.degree();
                Polynomial t = new Polynomial();
                Monomial aux = new Monomial(coef, deg);
                t.addMonomial(aux);
                quotient = addition(quotient, t);
                remainder = subtraction(remainder, multiplication(t, q));
                quotient.check();
                remainder.check();
            }
        }
        return new Polynomial[]{quotient, remainder};
    }

    public Polynomial derivative(Polynomial p) {
        Polynomial result = new Polynomial();
        if (p.degree() == 0) {
            Monomial mon = new Monomial(0, 0);
            result.addMonomial(mon);
        } else {
            for (Monomial monomial : p.getMonomials()) {
                if (monomial.getPower() > 0) {
                    double coef = monomial.getCoefficient() * monomial.getPower();
                    int power = monomial.getPower() - 1;
                    Monomial aux = new Monomial(coef, power);
                    result.addMonomial(aux);
                }
            }
        }
        result.check();
        return result;
    }

    public Polynomial integrate(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial monomial : p.getMonomials()) {
            double coef = monomial.getCoefficient() / (monomial.getPower() + 1);
            int power = monomial.getPower() + 1;
            Monomial aux = new Monomial(coef, power);
            result.addMonomial(aux);
        }
        result.check();
        return result;
    }
}
