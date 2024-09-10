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

            button.setFocusPainted(false);
            button.setOpaque(true);
            if (Character.isDigit(button.getText().charAt(0))) {
                button.setBackground(new Color(239, 248, 255));
            } else {
                button.setBackground(new Color(170, 218, 255));
            }
            button.setForeground(Color.BLACK);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    double result = -1;

                    String[] operators = {"+", "-", "*", "/", "^", "&", "%"};

                    if (operation[3].equals("=")) {  //resets =
                        display.setText("");
                        operation[3] = "≠";
                    }

                    if (Character.isDigit(button.getText().charAt(0))) { // for writing numbers
                        display.setText(display.getText() + button.getText());
                    } else if (button.getText().equals("C")) { // clears everything
                        operation[0] = "null";
                        operation[1] = "null";
                        operation[2] = "null";
                        operation[4] = "result";
                        display.setText("");
                    } else if (button.getText().equals("Ans")) { // should fetch answer
                        if (!operation[4].equals("result")) { // makes sure that there is an answer
                            display.setText("Ans");
                        }
                    }


                    for (String operator: operators) {
                        if (button.getText().equals(operator) && !display.getText().isEmpty()) { // makes sure the display isn't empty bvefore an operator
                            if (display.getText().equals("Ans")) { // if it says Ans in display it uses previous result
                                operation[0] = operation[4];
                            } else {
                                operation[0] = display.getText();
                            }
                            display.setText("");
                            operation[1] = operator;
                        }
                    }

                    if ((button.getText().equals("=")) && !display.getText().isEmpty()) { //makes sure = works only when display has something
                        if (operation[0].equals("null")) {
                            result = Double.parseDouble(display.getText());
                        } else {
                            double secondNum;
                            if (display.getText().equals("Ans")) { // if it says Ans in display it uses previous result
                                secondNum = Double.parseDouble(operation[4]);
                            } else {
                                secondNum = Double.parseDouble(display.getText());
                            }
                            double firstNum = Double.parseDouble(operation[0]);
                            String operand = operation[1];

                        /*for (operator : basicOperators) {

                        }*/
                            if (operand.equals("+")) { // does appropriate calculation for every operator
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
                        }

                        operation[3] = "=";
                        String resultStr = Double.toString(result);
                        if (result == Math.floor(result)) {
                            operation[4] = String.valueOf(result);
                            int resultInt = (int) result;
                            resultStr = Integer.toString(resultInt);
                        }
                        operation[0] = resultStr;
                        operation[1] = "null";

                        System.out.println(resultStr);
                        display.setText(resultStr);

                    }
                }
            });
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}