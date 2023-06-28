import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The NewSumItUp program is a modified version of the SumItUp game using Java Swing.
 * It generates two random operands and prompts the user to add them and input the result.
 * Now it uses JComboBoxes for user input instead of JTextFields.
 * <p>
 * <b>Note:</b> Images for rabbit and plus symbol are expected to be present in the src directory.
 * 
 * @version 1.0
 * @author Yongyuan Su 
 */

public class SumItUp extends JFrame {

    /**
    * JComboBox for user to select operand1, operand2 and result.
    */
    private JComboBox<Integer> operand1Field, operand2Field, resultField;

    /**
    * Label for displaying messages and plus icon.
    */
    private JLabel messageLabel, plusLabel;

    /**
    * Panels for displaying messages, operand1 images, operand2 images and layout.
    */
    private JPanel messagePanel, operand1Panel, operand2Panel, borderLayoutPanel;

    /**
    * Operand1 and Operand2 values.
    */
    private int operand1, operand2;

    /**
    * Icons for displaying rabbit and plus images.
    */
    private ImageIcon rabbitIcon, plusIcon;

    /**
    * Constructor for the NewSumItUp class
    * Initialize GUI components and sets initial state
    */

    public SumItUp() {
        // Set up the main window
        setTitle("Welcome to SumItUp!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load images
        rabbitIcon = new ImageIcon("rabbit.jpg");
        plusIcon = new ImageIcon("plus.png");

        // Create and set up the message panel
        messagePanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Set up plus label with icon
        plusLabel = new JLabel();
        plusLabel.setIcon(plusIcon);

        // Set up panels for operands and plus symbol
        operand1Panel = new JPanel(new GridLayout(0, 3));
        operand2Panel = new JPanel(new GridLayout(0, 3));
        JPanel plusPanel = new JPanel(new GridBagLayout());
        plusPanel.add(plusLabel);

        // Create ComboBoxes for input fields
        Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Integer[] values2 = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        operand1Field = new JComboBox<>(values);
        operand2Field = new JComboBox<>(values);
        resultField = new JComboBox<>(values2);

        // Create and set up the "Check!" button
        JButton checkButton = new JButton("Check!");
        
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        // Create and set up the result panel
        JPanel resultPanel = new JPanel();
        resultPanel.add(operand1Field);
        resultPanel.add(new JLabel("+"));
        resultPanel.add(operand2Field);
        resultPanel.add(new JLabel("="));
        resultPanel.add(resultField);
        resultPanel.add(checkButton);

        // Set up the main layout panel
        borderLayoutPanel = new JPanel(new BorderLayout());
        getContentPane().add(borderLayoutPanel, BorderLayout.CENTER);

        // Add panels to the main layout panel
        borderLayoutPanel.add(messagePanel, BorderLayout.NORTH);
        borderLayoutPanel.add(plusPanel, BorderLayout.CENTER);
        borderLayoutPanel.add(operand1Panel, BorderLayout.WEST);
        borderLayoutPanel.add(operand2Panel, BorderLayout.EAST);
        borderLayoutPanel.add(resultPanel, BorderLayout.SOUTH);

        generateOperands();
    }

    /**
    * Generate two random operands
    */

    public void generateOperands() {
        // Generate two random operands
        Random random = new Random();
        operand1 = random.nextInt(10) + 1;
        operand2 = random.nextInt(10) + 1;

        // Clear the operand panels
        operand1Panel.removeAll();
        operand2Panel.removeAll();

        // Generate and display the appropriate number of rabbits for each operand
        generateAndDisplayRabbits(operand1, operand1Panel);
        generateAndDisplayRabbits(operand2, operand2Panel);

        // Clear the selected items in the ComboBoxes
        operand1Field.setSelectedItem(null);
        operand2Field.setSelectedItem(null);
        resultField.setSelectedItem(null);

        // Redraw the window
        this.revalidate();
        this.repaint();
    }

    /**
    * Add a number of rabbit images corresponding to the operand to the given JPanel
    * @param operand Number of rabbit images to add
    * @param panel JPanel to add images to
    */

    public void generateAndDisplayRabbits(int operand, JPanel panel) {
        panel.removeAll();
        panel.setLayout(new GridLayout(4, 3, 0, 10));

        // Add a label with a rabbit icon for each value of the operand
        for (int i = 0; i < operand; i++) {
            panel.add(new JLabel(rabbitIcon));
        }
        // Fill the rest of the grid with empty labels
        for (int i = operand; i < 12; i++) {
            panel.add(new JLabel());
        }
    }

    /**
    * Check the answer input by the user
    */

    public void checkAnswer() {
        // If any of the fields are null, do nothing
        if (operand1Field.getSelectedItem() == null || operand2Field.getSelectedItem() == null
                || resultField.getSelectedItem() == null) {
            return;
        }

        // Get the user's input
        int userOperand1 = (int) operand1Field.getSelectedItem();
        int userOperand2 = (int) operand2Field.getSelectedItem();
        int userResult = (int) resultField.getSelectedItem();

        // If the user's input matches the operands and their sum, show a success message and generate new operands
        if (userOperand1 == operand1 && userOperand2 == operand2 && userResult == userOperand1 + userOperand2) {
            messageLabel.setText("Correct! Have another go?");
            generateOperands();
        } else {
            // If the user's input does not match, show an error message
            messageLabel.setText("Wrong! Try again!");
        }
    }

    /**
    * Main method
    * @param args Command line arguments
    */
    
    public static void main(String[] args) {
        // Create and show the GUI
        SumItUp SumItUp = new SumItUp();
        SumItUp.setVisible(true);
    }
}