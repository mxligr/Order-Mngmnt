package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private final List<Monomial> monomials = new ArrayList<Monomial>();

    public void addMonomial(Monomial monom) {
        monomials.add(monom);
    }

    public List<Monomial> getMonomials() {
        return monomials;
    }

    private void sort() {
        int n = monomials.size();
        // I don't use a foreach loop because i'm modifying the ArrayList
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (monomials.get(i).getPower() < monomials.get(j).getPower()) {
                    Collections.swap(monomials, i, j);
                }
            }
        }
    }

    // adds the monomials in the polynomial that have the same degree and modifies the polynomial
    public void group() {
        sort();
        for (int i = 0; i < monomials.size() - 1; i++) {
            int j = i + 1;
            while (j < monomials.size() && monomials.get(i).getPower() == monomials.get(j).getPower()) {
                double sum = monomials.get(i).getCoefficient() + monomials.get(j).getCoefficient();
                monomials.get(i).setCoefficient(sum);
                monomials.remove(j);
            }
        }
    }

    // checks and discards the monomials that have coefficient 0
    public void check() {
        group();
        for (int i = 0; i < monomials.size(); i++) {
            if (monomials.get(i).getCoefficient() == 0) {
                monomials.remove(i);
            }
        }
    }

    //tests if the polynomial is zero
    public boolean isZero() {
        group();
        if (monomials.size() > 0) {
            return monomials.get(0).getPower() == 0 && monomials.get(0).getCoefficient() == 0;
        } else {
            return true;
        }
    }

    // returns the degree of the polynomial
    public int degree() {
        group();
        return monomials.get(0).getPower();
    }

    // returns the coefficient of the highest degree
    public double getHighestCoefficient() {
        group();
        return monomials.get(0).getCoefficient();
    }

    // returns the coefficient for the degree d
    public double getCoefficient(int degree) {
        double result = 0;
        group();
        if (degree > monomials.get(0).getPower())
            throw new RuntimeException("Bad degree");
        for (Monomial mon : monomials) {
            if (mon.getPower() == degree) {
                result = mon.getCoefficient();
                break;
            }
        }

        if (result != 0) {
            return result;
        } else throw new RuntimeException("No such degree in polynomial");
    }

    // multiplies the polynomial with -1 (used in the subtraction operation)
    public void negate() {
        group();
        for (Monomial monomial : monomials) {
            monomial.setCoefficient(monomial.getCoefficient() * (-1));
        }
    }

    // takes a string representing a polynomial, separates it before the +/-, sets the coefficient
    // and power of the monomial and adds the monomial to the ArrayList representing the polynomial
    public boolean validateAndSet(String exp) {
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(exp);
        int x = 0;
        while (matcher.find()) {
            x = x + 1;
            Monomial mon = new Monomial();
            if (mon.validate(matcher.group(1))) {
                addMonomial(mon);
            } else {
                return false;
            }
        }
        group();
        return true;
    }

    // takes the ArrayList of Monomials and returns a string representing the polynomial,
    // which will be the output that will be displayed on the screen after the selected operation
    public String polToString() {
        if (monomials.size() <= 0)
            return "0";
        StringBuilder res = new StringBuilder();
        res.append(monomials.get(0).monToString());
        for (int i = 1; i < monomials.size(); i++) {
            if (monomials.get(i).getCoefficient() > 0) {
                res.append("+");
                res.append(monomials.get(i).monToString());
            } else {
                res.append(monomials.get(i).monToString());
            }
        }

        return res.toString();
    }
}
