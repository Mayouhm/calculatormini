package org.hamza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Font displayFont = new Font("SansSerif", Font.PLAIN, 20);

        JTextField display = new JTextField();
        display.setPreferredSize(new Dimension(300, 100));
        display.setEditable(false);
        display.setFont(displayFont);
        display.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "Ans", "&", "^", "%",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+",
        };

        final String[] operation = {"null", "null", "null", "≠", "result"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    double result = -1;

                    String[] operators = {"+", "-", "*", "/", "^", "&", "%"};

                    if (operation[3].equals("=")) {
                        display.setText("");
                        operation[3] = "≠";
                    }

                    if (Character.isDigit(button.getText().charAt(0))) {
                        display.setText(display.getText() + button.getText());
                    } else if (button.getText().equals("C")) {
                        display.setText("");
                    } else if (button.getText().equals("Ans")) {
                        if (!operation[4].equals("result")) {
                            display.setText("Ans");
                            operation[0] = operation[4];
                        }
                    }


                    for (String operator: operators) {
                        if (button.getText().equals(operator) && !display.getText().isEmpty()) {
                            if (!display.getText().equals("Ans")) {
                                operation[0] = display.getText();
                            }
                            display.setText("");
                            operation[1] = operator;
                        }
                    }

                    if ((button.getText().equals("=")) && !display.getText().isEmpty()) {
                        double secondNum = Double.parseDouble(display.getText());
                        double firstNum = Double.parseDouble(operation[0]);
                        String operand = operation[1];

                        /*for (operator : basicOperators) {

                        }*/
                        if (operand.equals("+")) {
                            result = firstNum + secondNum;
                        } else if (operand.equals("-")) {
                            result = firstNum - secondNum;
                        } else if (operand.equals("*")) {
                            result = firstNum * secondNum;
                        } else if (operand.equals("/")) {
                            result = firstNum / secondNum;
                        } else if (operand.equals("^")) {
                            result = Math.pow(firstNum, secondNum);
                        } else if (operand.equals("&")) {
                            result = firstNum + secondNum;
                        } else if (operand.equals("%")) {
                            result = firstNum % secondNum;
                        }

                        operation[3] = "=";

                        operation[4] = String.valueOf(result);

                        System.out.println(Double.toString(result));
                        display.setText(Double.toString(result));

                    }
                }
            });
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}