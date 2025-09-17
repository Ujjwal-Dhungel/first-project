import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
     JTextField display;
    private String operator;
    private double firstNumber, secondNumber, result;
    private boolean operatorClicked;

    public Calculator() {
        setTitle("Calculator");
        setSize(250, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(true);
        display.setFont(new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 20));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel);

        operatorClicked = false;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("0123456789".contains(cmd)) {
            if (operatorClicked) {
                display.setText("");
                operatorClicked = false;
            }
            display.setText(display.getText() + cmd);
        } else if ("+-*/".contains(cmd)) {
            firstNumber = Double.parseDouble(display.getText());
            operator = cmd;
            operatorClicked = true;
        } else if ("=".equals(cmd)) {
            secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber != 0)
                        result = firstNumber / secondNumber;
                    else {
                        display.setText("Error: Div by 0");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            operatorClicked = true;
        } else if ("C".equals(cmd)) {
            display.setText("");
            firstNumber = 0;
            secondNumber = 0;
            result = 0;
            operator = "";
            operatorClicked = false;
        }
   }
    public static void main(String[] args) {
        new Calculator();
    }
}