//Notes:
//	If the user already entered a decimal, all subsequent decimals will be ignored for current input
//	If the user enters a decimal without any other numbers that input will be ignored

package debug.calculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 622922277308063387L;
    private JButton[] btnNum = new JButton[10];
    private JButton[] btnOp = new JButton[5];
    private JButton btnCE, btnDecimal;
    private JPanel pnlNum, pnlOp;
    private JLabel txtResult;

    private String buffer;
    private String operation;
    private float result;
    private boolean init;

    private Font largeFont = new Font("Arial", Font.BOLD, 22);

    public CalculatorGUI() {
        // variable to store the value of the state of the calculator
        result = 0;
        // String buffer for multi-digit inputs
        buffer = "";
        // String container for the type of arithmetic operation to perform
        operation = "";
        // Set whether the calculator state variables are storing valid values
        // to handle edge cases
        init = false;

        // set attributes
        setSize(330, 350);
        setTitle("COSC121 Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create objects
        txtResult = new JLabel("" + result);
        txtResult.setFont(largeFont);
        txtResult.setHorizontalAlignment(JTextField.RIGHT);

        // buttons
        for (int i = 0; i < btnNum.length; i++) {
            btnNum[i] = new JButton(i + "");
            btnNum[i].setFont(largeFont);
            btnNum[i].addActionListener(new DigitActionListener());
        }

        btnDecimal = new JButton(".");
        btnCE = new JButton("CE");
        btnDecimal.setFont(largeFont);
        btnCE.setFont(largeFont);
        btnDecimal.addActionListener(new DigitActionListener());
        btnCE.addActionListener(new CEActionListener());

        btnOp[0] = new JButton("+");
        btnOp[1] = new JButton("-");
        btnOp[2] = new JButton("x");
        btnOp[3] = new JButton("/");
        btnOp[4] = new JButton("=");

        // Set font of Operations and add Action Listeners
        for (int i = 0; i < btnOp.length-1; i++) {
            btnOp[i].setFont(largeFont);
            btnOp[i].addActionListener(new OpActionListener());
        }
        btnOp[4].addActionListener(new OpEActionListener());
        // panels
        pnlNum = new JPanel(new GridLayout(3, 4));
        pnlOp = new JPanel(new GridLayout(5, 1));

        // 3) ADDING
        // add to panels
        for (int i = 0; i < btnNum.length; i++)
            pnlNum.add(btnNum[i]);

        pnlNum.add(btnDecimal);
        pnlNum.add(btnCE);

        for (int i = 0; i < btnOp.length; i++)
            pnlOp.add(btnOp[i]);

        // add to JFrame
        add(txtResult, "North");
        add(pnlNum);
        add(pnlOp, "East");
    }

    // Method to process the input of digits and decimal points
    public void processDigitInput(JButton value) {
        // ignore decimal points if they are already exists one in buffer
        if (!(buffer.contains(".") && value.getText().equals(".")))
            buffer = buffer + value.getText();
        txtResult.setText(buffer);
    }

    // method to handle input from operation buttons
    public void processOperationInput(String in) {
        switch (in) {
        case "+":
            operation = "+";
        case "-":
            operation = "-";
        case "x":
            operation = "*";
        case "/":
            operation = "/";
        }
    }

    // Carries out appropriate arithmetic operation based on state variables
    public void performOperation() {
        System.out.println("result:" + result + "op:" + operation + "bu" + buffer + init);
        if (!buffer.equals(".")) {

            // Handles edge case of first input after default state where buffer is empty
            // and result is zero
            if (!init && !buffer.equals("")) {
                result = Float.parseFloat(buffer);
                txtResult.setText("" + result);
                buffer = "";
                init = true;
            }

            // Find appropriate arithmetic operation to perform given the user's input

            if (!operation.equals("") && !buffer.equals("")) {
                switch (operation) {
                case "+":
                    result = result + Float.parseFloat(buffer);
                    buffer = "";

                    System.out.println(result + "+ok");

                    txtResult.setText("" + result);
                    break;
                case "-":
                    result = result - Float.parseFloat(buffer);
                    buffer = "";

                    txtResult.setText("" + result);
                    System.out.println("-ok");
                    break;
                case "x":
                    result = result * Float.parseFloat(buffer);
                    buffer = "";
                    txtResult.setText("" + result);
                    break;
                case "/":
                    result=result/Float.parseFloat(buffer);
                    buffer = "";
                    txtResult.setText("" + result);
                    break;

                }
                System.out.println("Current result: " + result);
            }
        }
    }

    // Method to reset calculator's state to default
    public void clear() {
        result = 0;
        txtResult.setText("" + result);
        buffer = "";
        operation = "";
        init = false;
    }

    // Action listener for operation buttons
    class OpActionListener extends JFrame implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = 6056332886484149038L;

        @Override
        public void actionPerformed(ActionEvent op) {
            System.out.println("operation" + operation);
            operation = ((JButton) op.getSource()).getText();
            txtResult.setText(operation);
            performOperation();
            System.out.println(operation);
        }
    }

    // Action listener for digit buttons
    class DigitActionListener extends JFrame implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = 6600227012715400365L;

        @Override
        public void actionPerformed(ActionEvent digit) {
            processDigitInput((JButton) digit.getSource());
            System.out.println(buffer);

        }
    }

    class OpEActionListener extends JFrame implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = 3181451459890465715L;

        @Override
        public void actionPerformed(ActionEvent equ) {

            performOperation();

        }

    }

    // Action listener for digit buttons
    class CEActionListener extends JFrame implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent ce) {
            clear();
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI().setVisible(true);
    }
}
