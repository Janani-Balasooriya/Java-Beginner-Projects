package RandomPasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomPasswordGenerator extends JFrame {
    private JTextField lengthField;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox lowerCaseCheckBox;
    private JCheckBox digitsCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JTextArea passwordArea;

    public RandomPasswordGenerator() {
        setTitle("Random Password Generator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Popup requirements button
        JButton requirementsButton = new JButton("Requirements");
        requirementsButton.addActionListener(e -> showRequirements());
        add(requirementsButton);

        // Length input
        add(new JLabel("Password Length:"));
        lengthField = new JTextField(5);
        add(lengthField);

        // Character type checkboxes
        upperCaseCheckBox = new JCheckBox("Include Uppercase Letters");
        lowerCaseCheckBox = new JCheckBox("Include Lowercase Letters");
        digitsCheckBox = new JCheckBox("Include Digits");
        specialCharsCheckBox = new JCheckBox("Include Special Characters");
        add(upperCaseCheckBox);
        add(lowerCaseCheckBox);
        add(digitsCheckBox);
        add(specialCharsCheckBox);

        // Generate password button
        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new GeneratePasswordListener());
        add(generateButton);

        // Password output area
        passwordArea = new JTextArea(5, 30);
        passwordArea.setEditable(false);
        add(new JScrollPane(passwordArea));

        // Copy to clipboard button
        JButton copyButton = new JButton("Copy to Clipboard");
        copyButton.addActionListener(e -> copyToClipboard());
        add(copyButton);
    }

    private void showRequirements() {
        String message = "Requirements:\n" +
                "- Choose length (minimum 1 character)\n" +
                "- Select character types to include:\n" +
                "  - Uppercase letters\n" +
                "  - Lowercase letters\n" +
                "  - Digits\n" +
                "  - Special characters";
        JOptionPane.showMessageDialog(this, message, "Password Requirements", JOptionPane.INFORMATION_MESSAGE);
    }

    private class GeneratePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int length = Integer.parseInt(lengthField.getText());
                if (length < 1) {
                    JOptionPane.showMessageDialog(RandomPasswordGenerator.this, "Length must be at least 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StringBuilder characterSet = new StringBuilder();
                if (upperCaseCheckBox.isSelected()) {
                    characterSet.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                }
                if (lowerCaseCheckBox.isSelected()) {
                    characterSet.append("abcdefghijklmnopqrstuvwxyz");
                }
                if (digitsCheckBox.isSelected()) {
                    characterSet.append("0123456789");
                }
                if (specialCharsCheckBox.isSelected()) {
                    characterSet.append("!@#$%^&*()-_=+[]{};:,.<>?/");
                }

                if (characterSet.length() == 0) {
                    JOptionPane.showMessageDialog(RandomPasswordGenerator.this, "Please select at least one character type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String password = generateRandomPassword(characterSet.toString(), length);
                passwordArea.setText(password);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RandomPasswordGenerator.this, "Invalid length. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String generateRandomPassword(String characterSet, int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }
        return password.toString();
    }

    private void copyToClipboard() {
        String password = passwordArea.getText();
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No password to copy!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringSelection stringSelection = new StringSelection(password);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(this, "Password copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RandomPasswordGenerator generator = new RandomPasswordGenerator();
            generator.setVisible(true);
        });
    }
}
