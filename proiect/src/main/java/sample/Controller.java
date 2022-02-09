package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField p1TextField;
    @FXML
    private TextField p2TextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private TextField auxResultTextField;
    @FXML
    private Button zero;
    @FXML
    private Button oneButton;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button plusButton;
    @FXML
    private Button minusButton;
    @FXML
    private Button powerButton;
    @FXML
    private Button xButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button additionOpButton;
    @FXML
    private Button subtractionOpButton;
    @FXML
    private Button multiplicationOpButton;
    @FXML
    private Button divisionOpButton;
    @FXML
    private Button derivativeOpButton;
    @FXML
    private Button IntegrationOpButton;
    @FXML
    private Text incorrectText;
    @FXML
    private Text resultText;
    @FXML
    private Text auxResultText;

    @FXML
    void addition(ActionEvent event) {
        if (event.getSource() == additionOpButton) {
            String exp1 = p1TextField.getText();
            String exp2 = p2TextField.getText();
            if (!p1TextField.getText().isEmpty() && !p2TextField.getText().isEmpty()) {
                Polynomial p1 = new Polynomial();
                try {
                    boolean test = p1.validateAndSet(exp1);
                    if (!test) {
                        incorrectText.setText("Incorrect polynomial syntax. Try again!");
                        clear();
                    } else {
                        incorrectText.setText("");
                        Polynomial p2 = new Polynomial();
                        try {
                            if (!p2.validateAndSet(exp2)) {
                                incorrectText.setText("Incorrect polynomial syntax. Try again!");
                                clear();
                            } else {
                                incorrectText.setText("");
                                Operations op = new Operations();
                                Polynomial ad = op.addition(p1, p2);
                                resultTextField.setText(ad.polToString());
                                resultText.setText("Addition Result:");
                                auxResultText.setText(" ");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else {
                incorrectText.setText("Please input both polynomials and try again.");
                clear();
            }
            auxResultTextField.clear();
        }
    }

    @FXML
    void subtraction(ActionEvent event) {
        if (event.getSource() == subtractionOpButton) {
            String exp1 = p1TextField.getText();
            String exp2 = p2TextField.getText();
            if (!p1TextField.getText().isEmpty() && !p2TextField.getText().isEmpty()) {
                Polynomial p1 = new Polynomial();
                try {
                    boolean test = p1.validateAndSet(exp1);
                    if (!test) {
                        incorrectText.setText("Incorrect polynomial syntax. Try again!");
                        clear();
                    } else {
                        incorrectText.setText("");
                        Polynomial p2 = new Polynomial();
                        try {
                            if (!p2.validateAndSet(exp2)) {
                                incorrectText.setText("Incorrect polynomial syntax. Try again!");
                                clear();
                            } else {
                                incorrectText.setText("");
                                Operations op = new Operations();
                                Polynomial sub = op.subtraction(p1, p2);
                                resultTextField.setText(sub.polToString());
                                resultText.setText("Subtraction Result:");
                                auxResultText.setText(" ");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }

            } else {
                incorrectText.setText("Please input both polynomials and try again.");
                clear();
            }
            auxResultTextField.clear();
        }
    }

    @FXML
    void multiplication(ActionEvent event) {
        if (event.getSource() == multiplicationOpButton) {
            String exp1 = p1TextField.getText();
            String exp2 = p2TextField.getText();
            if (!p1TextField.getText().isEmpty() && !p2TextField.getText().isEmpty()) {
                Polynomial p1 = new Polynomial();
                try {
                    boolean test = p1.validateAndSet(exp1);
                    if (!test) {
                        incorrectText.setText("Incorrect polynomial syntax. Try again!");
                        clear();
                    } else {
                        incorrectText.setText("");
                        Polynomial p2 = new Polynomial();
                        try {
                            if (!p2.validateAndSet(exp2)) {
                                incorrectText.setText("Incorrect polynomial syntax. Try again!");
                                clear();
                            } else {
                                incorrectText.setText("");
                                Operations op = new Operations();
                                Polynomial mul = op.multiplication(p1, p2);
                                resultTextField.setText(mul.polToString());
                                resultText.setText("Multiplication Result:");
                                auxResultText.setText(" ");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else {
                incorrectText.setText("Please input both polynomials and try again.");
                clear();
            }
            auxResultTextField.clear();
        }
    }

    @FXML
    void division(ActionEvent event) {
        if (event.getSource() == divisionOpButton) {
            String exp1 = p1TextField.getText();
            String exp2 = p2TextField.getText();
            if (!p1TextField.getText().isEmpty() && !p2TextField.getText().isEmpty()) {
                if (!p2TextField.getText().equals("0")) {
                    Polynomial p1 = new Polynomial();
                    try {
                        boolean test = p1.validateAndSet(exp1);
                        if (!test) {
                            incorrectText.setText("Incorrect polynomial syntax. Try again!");
                            clear();
                        } else {
                            incorrectText.setText("");
                            Polynomial p2 = new Polynomial();
                            try {
                                if (!p2.validateAndSet(exp2)) {
                                    incorrectText.setText("Incorrect polynomial syntax. Try again!");
                                    clear();
                                } else {
                                    incorrectText.setText("");
                                    Operations op = new Operations();
                                    Polynomial[] result = op.division(p1, p2);
                                    resultTextField.setText(result[0].polToString());
                                    auxResultTextField.setText(result[1].polToString());
                                    resultText.setText("Quotient:");
                                    auxResultText.setText("Rest:");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                e.getCause();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    incorrectText.setText("Please make sure that the second polynomial is not 0.");
                    clear();
                }
            } else {
                incorrectText.setText("Please input both polynomials and try again.");
                clear();
            }
        }
    }

    @FXML
    void derivative(ActionEvent event) {
        if (event.getSource() == derivativeOpButton) {
            String exp1 = p1TextField.getText();
            if (!p1TextField.getText().isEmpty()) {
                Polynomial p1 = new Polynomial();
                try {
                    boolean test = p1.validateAndSet(exp1);
                    if (!test) {
                        incorrectText.setText("Incorrect polynomial syntax. Try again!");
                        clear();
                    } else {
                        incorrectText.setText("");
                        Operations op = new Operations();
                        Polynomial result = op.derivative(p1);
                        resultTextField.setText(result.polToString());
                        resultText.setText("Derivation Result:");
                        auxResultText.setText(" ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else {
                incorrectText.setText("Please input polynomial and try again.");
                clear();
            }
            auxResultTextField.clear();
        }
    }

    @FXML
    void integration(ActionEvent event) {
        if (event.getSource() == IntegrationOpButton) {
            String exp1 = p1TextField.getText();
            if (!p1TextField.getText().isEmpty()) {
                Polynomial p1 = new Polynomial();
                try {
                    boolean test = p1.validateAndSet(exp1);
                    if (!test) {
                        incorrectText.setText("Incorrect polynomial syntax. Try again!");
                        clear();
                    } else {
                        incorrectText.setText("");
                        Operations op = new Operations();
                        Polynomial result = op.integrate(p1);
                        resultTextField.setText(result.polToString() + "+C");
                        resultText.setText("Integration Result:");
                        auxResultText.setText(" ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else {
                incorrectText.setText("Please input polynomial and try again.");
                clear();
            }
            auxResultTextField.clear();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        if (event.getSource() == clearButton) {
            p1TextField.clear();
            p2TextField.clear();
            resultTextField.clear();
            incorrectText.setText("");
        }
    }

    void clear() {
        resultTextField.clear();
        auxResultTextField.clear();
    }

    private boolean p1Pushed;
    private boolean p2Pushed;
    @FXML
    private void p1Clicked(MouseEvent event) {
        if (event.getSource() == p1TextField) {
            p1Pushed = true;
            p2Pushed = false;
        }
    }

    @FXML
    private void p2Clicked(MouseEvent event) {
        if (event.getSource() == p2TextField) {
            p1Pushed = false;
            p2Pushed = true;
        }
    }

    @FXML
    private void calculation(ActionEvent event) {
        if (p1Pushed) {
            append(event, p1TextField);
        } else if (p2Pushed) {
            append(event, p2TextField);
        }
    }

    private void append(ActionEvent event, TextField textField) {
        if (event.getSource() == oneButton) {
            textField.appendText("1");
        } else if (event.getSource() == two) {
            textField.appendText("2");
        } else if (event.getSource() == three) {
            textField.appendText("3");
        } else if (event.getSource() == four) {
            textField.appendText("4");
        } else if (event.getSource() == five) {
            textField.appendText("5");
        } else if (event.getSource() == six) {
            textField.appendText("6");
        } else if (event.getSource() == seven) {
            textField.appendText("7");
        } else if (event.getSource() == eight) {
            textField.appendText("8");
        } else if (event.getSource() == nine) {
            textField.appendText("9");
        } else if (event.getSource() == zero) {
            textField.appendText("0");
        } else if (event.getSource() == plusButton) {
            textField.appendText("+");
        } else if (event.getSource() == minusButton) {
            textField.appendText("-");
        } else if (event.getSource() == powerButton) {
            textField.appendText("^");
        } else if (event.getSource() == xButton) {
            textField.appendText("x");
        }
    }
}
