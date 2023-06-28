import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class extends JFrame to create a graphical user interface where a user 
 * is asked to sum two numbers (operands) and enter the result in a field.
 * User inputs the operands and the result in text fields.
 * After entering the numbers, the user can check if their answer is correct by clicking a button.
 * @version 1.0
 * @author Yongyuan Su
 */

public class SumItUpExtra extends JFrame {

    /**
    * JTextField for input and display of the first operand.
    */
    private JTextField operand1Field;
    
    /**
    * JTextField for input and display of the second operand.
    */
    private JTextField operand2Field;
    
    /**
    * JTextField for input and display of the result.
    */
    private JTextField resultField;
    
    /**
    * JLabel for displaying messages to the user.
    */
    private JLabel messageLabel;
    
    /**
    * JLabel for displaying the plus symbol.
    */
    private JLabel plusLabel;
    
    /**
    * JPanel for displaying the messageLabel.
    */
    private JPanel messagePanel;
    
    /**
    * JPanel for displaying images corresponding to the first operand.
    */
    private JPanel operand1Panel;
    
    /**
    * JPanel for displaying images corresponding to the second operand.
    */
    private JPanel operand2Panel;
    
    /**
    * Main JPanel containing all other GUI components.
    */
    private JPanel borderLayoutPanel;
    
    /**
    * Integer value of the first operand.
    */
    private int operand1;
    
    /**
    * Integer value of the second operand.
    */
    private int operand2;
    
    /**
    * Integer value for the maximum number of rabbits(operands) that can be generated.
    */
    private int maxRabbits;
    
    /**
    * ImageIcon for displaying rabbit images.
    */
    private ImageIcon rabbitIcon;
    
    /**
    * ImageIcon for displaying plus symbol.
    */
    private ImageIcon plusIcon;
    /**
     * Constructor for the SumItUpExtra class.
     *
     * @param maxRabbits the maximum number of rabbit images (operands) that can be generated.
     */

    public SumItUpExtra(int maxRabbits) {
        // Initialize instance variables
        this.maxRabbits = maxRabbits;

        // Set up the JFrame
        setTitle("Welcome to SumItUp!");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the images
        rabbitIcon = new ImageIcon("rabbit.jpg");
        plusIcon = new ImageIcon("plus.png");

        // Create the message panel at the top of the frame
        messagePanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Create the plus label
        plusLabel = new JLabel();
        plusLabel.setIcon(plusIcon);

        // Create the operand panels
        operand1Panel = new JPanel(new GridLayout(0, 3));
        operand2Panel = new JPanel(new GridLayout(0, 3));

        // Create the plus panel
        JPanel plusPanel = new JPanel(new GridBagLayout());
        plusPanel.add(plusLabel);

        // Create the text fields for the operands and the result
        operand1Field = new JTextField(2);
        operand2Field = new JTextField(2);
        resultField = new JTextField(2);

        // Create the check button
        JButton checkButton = new JButton("Check!");

        // Add an ActionListener to the check button to check the answer when the button is clicked
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        // Create the result panel at the bottom of the frame
        JPanel resultPanel = new JPanel();
        resultPanel.add(operand1Field);
        resultPanel.add(new JLabel("+"));
        resultPanel.add(operand2Field);
        resultPanel.add(new JLabel("="));
        resultPanel.add(resultField);
        resultPanel.add(checkButton);

        // Create the BorderLayout panel
        borderLayoutPanel = new JPanel(new BorderLayout());
        // Add the BorderLayout panel to the frame
        getContentPane().add(borderLayoutPanel, BorderLayout.CENTER);

        // Add the other panels to the BorderLayout panel
        borderLayoutPanel.add(messagePanel, BorderLayout.NORTH);
        borderLayoutPanel.add(plusPanel, BorderLayout.CENTER);
        borderLayoutPanel.add(operand1Panel, BorderLayout.WEST);
        borderLayoutPanel.add(operand2Panel, BorderLayout.EAST);
        borderLayoutPanel.add(resultPanel, BorderLayout.SOUTH);

        // Generate the first set of operands
        generateOperands();
    }

    /**
     * Method to generate new operands as random integers and to display 
     * the corresponding number of rabbit images.
     */

    public void generateOperands() {
        // Generate two random numbers between 1 and maxRabbits
        Random random = new Random();
        operand1 = random.nextInt(maxRabbits) + 1;
        operand2 = random.nextInt(maxRabbits) + 1;

        // Clear the operand panels
        operand1Panel.removeAll();
        operand2Panel.removeAll();

        // Set the layout of the operand panels to accommodate the maximum number of rabbits
        operand1Panel.setLayout(new GridLayout((maxRabbits + 4) / 5, 5, 0, 5));
        operand2Panel.setLayout(new GridLayout((maxRabbits + 4) / 5, 5, 0, 5));

        // Add the appropriate number of rabbit icons to each operand panel
        for (int i = 0; i < operand1; i++) {
            JLabel label = new JLabel(rabbitIcon);
            label.setBorder(null);
            operand1Panel.add(label);
        }
        for (int i = operand1; i < maxRabbits; i++) {
            JLabel label = new JLabel();
            label.setBorder(null);
            operand1Panel.add(label);
        }

        // Same for operand2 panel
        for (int i = 0; i < operand2; i++) {
            JLabel label = new JLabel(rabbitIcon);
            label.setBorder(null);
            operand2Panel.add(label);
        }
        for (int i = operand2; i < maxRabbits; i++) {
            JLabel label = new JLabel();
            label.setBorder(null);
            operand2Panel.add(label);
        }

        // Clear the text fields
        operand1Field.setText("");
        operand2Field.setText("");
        resultField.setText("");

        // Redraw the frame
        this.revalidate();
        this.repaint();
    }

    /**
     * Method to check if the user's inputs match the operands and their sum.
     * If they do, a success message is shown and new operands are generated.
     * If they do not, an error message is shown.
     */

    public void checkAnswer() {
        try {
            // Parse the text fields to get the user's inputs
            int userOperand1 = Integer.parseInt(operand1Field.getText());
            int userOperand2 = Integer.parseInt(operand2Field.getText());
            int userResult = Integer.parseInt(resultField.getText());

            // If the user's inputs match the operands and their sum, they are correct
            if (userOperand1 == operand1 && userOperand2 == operand2 && userResult == userOperand1 + userOperand2) {
                messageLabel.setText("Correct! Have another go?");
                generateOperands();
            } else {
                messageLabel.setText("Wrong! Try again!");
            }
        } catch (NumberFormatException e) {
            // If the text field is empty, do not throw an error and do not perform any operation
        }
    }

    /**
     * The main method of the program. It accepts a command line argument for 
     * the maximum number of rabbits that can be generated. If the argument 
     * is not an integer between 10 and 25 (inclusive), an error message is shown.
     *
     * @param args Command line arguments
     */

    public static void main(String[] args) {
        // Check the command line argument
        if (args.length != 1) {
            System.out.println("Error: Please specify the maximum number of rabbits as a command line argument.");
            System.exit(1);
        }

        int maxRabbits;

        try {
            maxRabbits = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid command line argument. Please enter a number.");
            System.exit(1);
            return;
        }

        // Check the maximum number of rabbits is within the allowed range
        if (maxRabbits < 10 || maxRabbits > 25) {
            System.out.println("Error: The maximum number of rabbits must be between 10 and 25 (inclusive).");
            System.exit(1);
        }

        // Create and display the frame
        SumItUpExtra sumItUp = new SumItUpExtra(maxRabbits);
        sumItUp.setVisible(true);
    }
}