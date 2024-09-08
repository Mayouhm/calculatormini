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

        JTextField display = new JTextField();
        display.setPreferredSize(new Dimension(300, 100));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "Ans", "&", "^", "%",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        final String[] operation = {"-1", "-", "-1"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = -1;

                    String[] basicOperators = {"+", "-", "*", "/"};
                    String[] complexOperators = {"^", "&", "%"};

                    if (Character.isDigit(button.getText().charAt(0))) {
                        display.setText(display.getText() + button.getText());
                    } else if (button.getText().equals("C")) {
                        display.setText("");
                    }
                    for (String operator: basicOperators) {
                        if (button.getText().equals(operator) && !display.getText().equals("")) {
                            operation[0] = display.getText();
                            display.setText("");
                            operation[1] = operator;
                        }
                    }

                    if (button.getText().equals("=")) {
                        int secondNum = Integer.parseInt(display.getText());
                        int firstNum = Integer.parseInt(operation[0]);
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
                        }

                        System.out.println(Integer.toString(result));
                        display.setText(Integer.toString(result));
                    }
                }
            });
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}