import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class MyFinalsLabExam implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[12];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, acButton, negButton;
    JButton percentButton, expButton, sqrtButton;
    JPanel panel;

    StringBuilder expression = new StringBuilder();
    private boolean isResultDisplayed = false;

    static class ModernButton extends JButton {
        private Color normalBg, hoverBg, normalFg;
        private boolean hover = false;
        private int radius = 40;

        public ModernButton(String text, Color bg, Color fg, Color border, Color hoverBg, Color hoverFg) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("SansSerif", Font.BOLD, 24));
            setForeground(fg);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.normalBg = bg;
            this.hoverBg = hoverBg;
            this.normalFg = fg;
            setOpaque(false);

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    hover = true;
                    setForeground(hoverFg);
                    repaint();
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    hover = false;
                    setForeground(normalFg);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hover ? hoverBg : normalBg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setStroke(new BasicStroke(2));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public MyFinalsLabExam() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(430, 600);
        frame.setResizable(false);
        frame.setLayout(null);

        Color bgBlack = new Color(30, 30, 30);
        Color btnBlack = new Color(40, 40, 40);
        Color btnWhite = Color.WHITE;
        Color btnOrange = new Color(255, 149, 0);
        Color borderGray = new Color(80, 80, 80);
        Color hoverBtn = new Color(60, 60, 60);
        Color hoverOrange = new Color(255, 180, 60);

        frame.getContentPane().setBackground(bgBlack);

        textfield = new JTextField();
        textfield.setBounds(0, 0, 420, 80);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setEditable(false);
        textfield.setFont(new Font("Arial", Font.BOLD, 36));
        textfield.setBackground(bgBlack);
        textfield.setForeground(Color.WHITE);
        textfield.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));

        addButton = new ModernButton("+", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        subButton = new ModernButton("-", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        mulButton = new ModernButton("x", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        divButton = new ModernButton("÷", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        decButton = new ModernButton(".", btnBlack, btnWhite, borderGray, hoverBtn, btnWhite);
        equButton = new ModernButton("=", btnOrange, btnWhite, borderGray, hoverOrange, btnWhite);
        delButton = new ModernButton("Del", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        acButton = new ModernButton("AC", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        negButton = new ModernButton("(-)", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        percentButton = new ModernButton("%", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        expButton = new ModernButton("x²", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);
        sqrtButton = new ModernButton("√", btnBlack, btnOrange, borderGray, hoverBtn, btnOrange);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = acButton;
        functionButtons[8] = negButton;
        functionButtons[9] = percentButton;
        functionButtons[10] = expButton;
        functionButtons[11] = sqrtButton;

        for (int i = 0; i < 12; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new ModernButton(String.valueOf(i), btnBlack, btnWhite, borderGray, hoverBtn, btnWhite);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(10, 80, 395, 470);
        panel.setLayout(new GridLayout(6, 4, 12, 12));
        panel.setBackground(bgBlack);

        panel.add(acButton);
        panel.add(delButton);
        panel.add(percentButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(expButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(sqrtButton);
        panel.add(negButton);
        panel.add(new JLabel());
        panel.add(new JLabel());

        frame.add(panel);
        frame.add(textfield);

        frame.setVisible(true);
    }

    private void appendToTextField(String s) {
        if (isResultDisplayed) {
            textfield.setText("");
            expression.setLength(0);
            isResultDisplayed = false;
        }
        expression.append(s);
        textfield.setText(getDisplayString());
    }

    private void clearAll() {
        expression.setLength(0);
        textfield.setText("");
        isResultDisplayed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                appendToTextField(String.valueOf(i));
                return;
            }
        }

        for (int i = 0; i < 12; i++) {
            if (e.getSource() == functionButtons[i]) {
                String text = functionButtons[i].getText();
                switch (text) {
                    case "+":
                    case "x":
                    case "÷":
                    case "%":
                        if (expression.length() > 0 && !endsWithOperator(expression.toString())) {
                            if (isResultDisplayed) isResultDisplayed = false;
                            appendToTextField(text);
                        }
                        break;
                    case "-":
                        if (isResultDisplayed) isResultDisplayed = false;
                        appendToTextField(text);
                        break;
                    case ".":
                        if (expression.length() == 0 || endsWithOperator(expression.toString()) || endsWithDecimal(expression.toString())) {
                            if(expression.length() > 0 && endsWithOperator(expression.toString())){
                                appendToTextField("0.");
                            } else if (!getLastNumber(expression.toString()).contains(".")) {
                                appendToTextField(".");
                            }
                        } else if (!getLastNumber(expression.toString()).contains(".")) {
                            appendToTextField(".");
                        }
                        break;
                    case "=":
                        evaluateExpression();
                        break;
                    case "Del":
                        handleDelete();
                        break;
                    case "AC":
                        clearAll();
                        break;
                    case "(-)":
                        negateLastNumber();
                        break;
                    case "√":
                        sqrtLastNumber();
                        break;
                    case "x²":
                        handleExponent();
                        break;
                }
                return;
            }
        }
    }

    private String getDisplayString() {
        return expression.toString().replaceAll("\\^2", "²");
    }

    private void handleExponent() {
        if (expression.length() > 0 && !endsWithOperator(expression.toString())) {
            expression.append("^2");
            textfield.setText(getDisplayString());
        }
    }

    private void handleDelete() {
        if (expression.length() > 0) {
            if (expression.length() >= 2 && expression.substring(expression.length()-2).equals("^2")) {
                expression.delete(expression.length()-2, expression.length());
            } else {
                expression.deleteCharAt(expression.length() - 1);
            }
            textfield.setText(getDisplayString());
        }
    }

    private boolean endsWithOperator(String expr) {
        if (expr.isEmpty()) return false;
        char c = expr.charAt(expr.length() - 1);
        switch (c) {
            case '+':
            case '-':
            case 'x':
            case '÷':
            case '%':
                return true;
            default:
                return false;
        }
    }
    
    private boolean endsWithDecimal(String expr) {
        if (expr.isEmpty()) return false;
        char c = expr.charAt(expr.length() - 1);
        return c == '.';
    }

    private String getLastNumber(String expr) {
        if (expr.isEmpty()) return "";
        int i = expr.length() - 1;

        while (i >= 0 && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
            i--;
        }

        if (i >= 0 && expr.charAt(i) == '-') {
            if (i == 0 || isOperator(expr.charAt(i - 1))) {
                i--;
            }
        }
        return expr.substring(i + 1);
    }

    private void negateLastNumber() {
        String expr = expression.toString();
        if (expr.isEmpty() || endsWithOperator(expr)) return;

        String lastNumber = getLastNumber(expr);
        if (lastNumber.isEmpty()) return;

        int startIndex = expression.length() - lastNumber.length();
        
        while(startIndex > 0 && Character.isDigit(expression.charAt(startIndex - 1))){
             startIndex--;
        }

        if (lastNumber.startsWith("-")) {
            expression.replace(startIndex, startIndex + lastNumber.length(), lastNumber.substring(1));
        } else {
            expression.replace(startIndex, startIndex + lastNumber.length(), "-" + lastNumber);
        }
        textfield.setText(getDisplayString());
    }

    private void sqrtLastNumber() {
        String expr = expression.toString();
        String lastNumber = getLastNumber(expr);
        if (!lastNumber.isEmpty()) {
            int startIndex = expression.length() - lastNumber.length();
            while(startIndex > 0 && Character.isDigit(expression.charAt(startIndex - 1))){
                 startIndex--;
            }

            try {
                double number = Double.parseDouble(lastNumber);
                if (number < 0) {
                    textfield.setText("Invalid");
                    expression.setLength(0);
                    isResultDisplayed = true;
                    return;
                }
                number = Math.sqrt(number);
                expression.replace(startIndex, startIndex + lastNumber.length(), String.valueOf(number));
                textfield.setText(getDisplayString());
            } catch (NumberFormatException ex) {
                textfield.setText("Error");
                expression.setLength(0);
                isResultDisplayed = true;
            }
        }
    }

    private void evaluateExpression() {
        try {
            String expr = expression.toString()
                                  .replaceAll("x", "*")
                                  .replaceAll("÷", "/")
                                  .replaceAll("²", "^2");

            if (expr.isEmpty() || endsWithOperator(expr.replaceAll("x", "").replaceAll("÷", ""))) {
                if (endsWithOperator(expr.replaceAll("x", "").replaceAll("÷", ""))) {
                    expr = expr.substring(0, expr.length() - 1);
                } else {
                    return;
                }
            }
            
            double result = evaluate(expr);
            String resStr = (result == (long) result) ? String.valueOf((long) result) : String.valueOf(result);
            textfield.setText(resStr);
            expression.setLength(0);
            expression.append(resStr);
            isResultDisplayed = true;
        } catch (Exception ex) {
            textfield.setText("Error");
            expression.setLength(0);
            isResultDisplayed = true;
        }
    }

    private double evaluate(String expr) throws Exception {
        String postfix = infixToPostfix(expr);
        return evalPostfix(postfix);
    }

    private int precedence(char operator) {
        switch (operator) {
            case '^': return 3;
            case '*':
            case '/':
            case '%': return 2;
            case '+':
            case '-': return 1;
            default: return 0;
        }
    }

    private boolean isOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '^':
                return true;
            default:
                return false;
        }
    }

    private String infixToPostfix(String expr) throws Exception {
        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    num.append(expr.charAt(i));
                    i++;
                }
                output.append(num).append(' ');
                i--;
            } else if (c == '-') {
                if (i + 1 < expr.length() && Character.isDigit(expr.charAt(i+1)) && (i == 0 || isOperator(expr.charAt(i - 1)))) {
                    output.append('-');
                } else {
                    while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                        output.append(operatorStack.pop()).append(' ');
                    }
                    operatorStack.push(c);
                }
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    output.append(operatorStack.pop()).append(' ');
                }
                operatorStack.push(c);
            } else if (Character.isWhitespace(c)) {
            } else {
                throw new Exception("Invalid character in expression: " + c);
            }
        }

        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop()).append(' ');
        }
        return output.toString().trim();
    }

    private double evalPostfix(String postfix) throws Exception {
        Stack<Double> numberStack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (token.length() == 1 && isOperator(token.charAt(0))) {
                if (numberStack.size() < 2) {
                    throw new Exception("Invalid expression: insufficient operands for operator " + token);
                }
                double rightOperand = numberStack.pop();
                double leftOperand = numberStack.pop();
                double result;
                switch (token.charAt(0)) {
                    case '+': result = leftOperand + rightOperand; break;
                    case '-': result = leftOperand - rightOperand; break;
                    case '*': result = leftOperand * rightOperand; break;
                    case '/':
                        if (rightOperand == 0) throw new Exception("Division by zero");
                        result = leftOperand / rightOperand; break;
                    case '%':
                        if (rightOperand == 0) throw new Exception("Modulo by zero");
                        result = leftOperand % rightOperand; break;
                    case '^': result = Math.pow(leftOperand, rightOperand); break;
                    default: throw new Exception("Unknown operator: " + token);
                }
                numberStack.push(result);
            } else {
                try {
                    numberStack.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid number format: " + token);
                }
            }
        }
        if (numberStack.size() != 1) throw new Exception("Invalid expression: result stack size is " + numberStack.size());
        return numberStack.pop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFinalsLabExam::new);
    }
}