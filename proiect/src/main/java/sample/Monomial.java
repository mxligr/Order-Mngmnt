package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {
    private double coefficient;
    private int power;

    public Monomial(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Monomial() {

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    // validates a monomial string("+7x^2") and sets the coefficient and power
    // I've taken and modified this method after debugging it and realising it doesn't
    // work how I want from https://code-it.ro/calculator-de-polinoame-in-java/#_Toc445825166
    public boolean validate(String monom) {
        if (!monom.matches("^[xX0-9\\^\\*\\-\\+ ]*"))
            return false;
        Pattern polynomialFormat = Pattern.compile("\\^");
        Matcher m = polynomialFormat.matcher(monom);
        String s = "";
        while (m.find()) {
            s = m.group();
        }
        if (s.isEmpty()) { // no ^ symbol
            String[] buffer = monom.split("[a-zA-Z]");
            if (buffer.length == 0) {   // just x, without coefficient or power
                coefficient = 1;
                power = 1;
            } else {
                if (buffer[0].equals("-")) {  // x, but with a coefficient before it (or a +/- sign)
                    coefficient = -1;
                    power = 1;
                } else if (buffer[0].equals("+")) {
                    coefficient = 1;
                    power = 1;
                } else {
                    coefficient = (!buffer[0].isEmpty()) ? Integer.parseInt(buffer[0]) : 1;
                    power = (buffer[0].equals(monom)) ? 0 : 1;
                }
            }
        } else { // contains ^ symbol
            String[] buffer = monom.split("\\^");
            try {
                StringBuilder nrStr = new StringBuilder("");
                for (int i = 0; i < buffer[0].length(); i++) {
                    char c = buffer[0].charAt(i);
                    if (c == 45)        // - sign
                        nrStr.append(c);
                    if (c > 47 && c < 58)        // digits 0-9
                        nrStr.append(c);
                }
                if (nrStr.toString().equals("-")) {
                    coefficient = -1;
                } else {
                    coefficient = (nrStr.isEmpty()) ? 1 : Integer.parseInt(String.valueOf(nrStr));
                }
                power = Integer.parseInt(buffer[1]);
            } catch (NumberFormatException e) {
                System.out.println("Format not valid. Try again");
                return false;
            }
        }
        return true;
    }

    // rounds a double value
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // takes a monomial and forms a string
    // we have two cases, depending on if the coefficient is an integer or a double
    public String monToString() {
        StringBuilder res = new StringBuilder();
        if (coefficient == (int) coefficient) {
            // If the coefficient is zero or the exponent is zero, the result is simply the coefficient:
            if (coefficient == 0 || power == 0) {
                return "" + (int) coefficient;
            }
            // Do not print the number for the coefficient of +/- 1
            if (Math.abs(coefficient) == 1) {
                // For +1 do not print the sign either
                if (coefficient == -1) {
                    res.append("-");
                }
            } else {
                res.append((int) coefficient);
            }
            // For exponent of 1, do not print ^1
        } else {
            if (coefficient == 0 || power == 0) {
                return "" + round(coefficient, 2);
            }
            if (Math.abs(coefficient) == 1) {
                if (coefficient == -1) {
                    res.append("-");
                }
            } else {
                res.append(round(coefficient, 2));
            }
        }
        res.append("x");
        if (power != 1) {
            res.append("^");
            res.append(power);
        }
        return res.toString();
    }

}
