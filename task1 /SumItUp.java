import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The SumItUp program is a simple addition game implemented using Java Swing.
 * It generates two random operands and prompts the user to add them and input
 * the result.
 * 
 * @version 1.0
 * @author Yongyuan Su
 */

public class SumItUp extends JFrame {
    /**
     * Field for input and display of first operand.
     */
    private JTextField operand1Field;

    /**
     * Field for input and display of second operand.
     */
    private JTextField operand2Field;

    /**
     * Field for input and display of result.
     */
    private JTextField resultField;

    /**
     * Label for displaying messages to the user.
     */
    private JLabel messageLabel;

    /**
     * Label for displaying the plus icon.
     */
    private JLabel plusLabel;

    /**
     * Panel for displaying the messageLabel.
     */
    private JPanel messagePanel;

    /**
     * Panel for displaying images corresponding to the first operand.
     */
    private JPanel operand1Panel;

    /**
     * Panel for displaying images corresponding to the second operand.
     */
    private JPanel operand2Panel;

    /**
     * Main panel containing all other GUI components.
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
     * Icon for displaying rabbit images.
     */
    private ImageIcon rabbitIcon;

    /**
     * Icon for displaying plus image.
     */
    private ImageIcon plusIcon;

    /**
    * SumItUp class constructor
    * Initialize GUI components and sets initial state
    */

    public SumItUp() {
        setTitle("Welcome to SumItUp!"); // Set the window title
        setSize(800, 600); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the window close button behavior

        // Load the images
        rabbitIcon = new ImageIcon("rabbit.jpg");
        plusIcon = new ImageIcon("plus.png");

        // Create and set up the messagePanel
        messagePanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        plusLabel = new JLabel();
        plusLabel.setIcon(plusIcon);

        // Create operands panels, create and set plus panel, and add it to a new
        // JPanel's center section
        operand1Panel = new JPanel(new GridLayout(0, 3));
        operand2Panel = new JPanel(new GridLayout(0, 3));
        JPanel plusPanel = new JPanel(new GridBagLayout());
        plusPanel.add(plusLabel);

        // Create text input fields
        operand1Field = new JTextField(2);
        operand2Field = new JTextField(2);
        resultField = new JTextField(2);

        // Create and set up the Check button
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

        // Create and set up a new BorderLayout JPanel
        borderLayoutPanel = new JPanel(new BorderLayout());
        getContentPane().add(borderLayoutPanel, BorderLayout.CENTER);

        // Add panels to the new JPanel
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
        // Randomly generate two operands
        Random random = new Random();
        operand1 = random.nextInt(10) + 1;
        operand2 = random.nextInt(10) + 1;

        // Clear the input fields
        operand1Field.setText("");
        operand2Field.setText("");
        resultField.setText("");

        // Generate operands and add rabbit images
        generateAndDisplayRabbits(operand1, operand1Panel);
        generateAndDisplayRabbits(operand2, operand2Panel);

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

        // Add the operand number of rabbit images
        for (int i = 0; i < operand; i++) {
            panel.add(new JLabel(rabbitIcon));
        }
        // Fill the rest of the panel with empty JLabels
        for (int i = operand; i < 12; i++) {
            panel.add(new JLabel());
        }
    }

    /**
    * Check the answer input by the user
    */

    public void checkAnswer() {
            // Parse the user inputs
            int userOperand1 = Integer.parseInt(operand1Field.getText());
            int userOperand2 = Integer.parseInt(operand2Field.getText());
            int userResult = Integer.parseInt(resultField.getText());

            // If the user's operands and result are correct, generate new operands and prompt for another round
            if (userOperand1 == operand1 && userOperand2 == operand2 && userResult == userOperand1 + userOperand2) {
                messageLabel.setText("Correct! Have another go?");
                generateOperands();
            } else {
                messageLabel.setText("Wrong! Try again!");
            }
    }

    /**
    * Main method
    * @param args Command line arguments. These are not used in this program.
    */

    public static void main(String[] args) {
        SumItUp sumItUp = new SumItUp();
        sumItUp.setVisible(true);
    }
}