// Class creates a dialog window for editing a student's name and GPA.
// Shows text fields for the user to enter values and Save or Cancel buttons.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditStudentDialog extends JDialog {
   private JTextField nameField;
    private JTextField gpaField;
    private boolean saved = false;

    public EditStudentDialog(JFrame parent, String name, String gpa) {
        super(parent, "Edit Student", true);
        setLayout(new GridLayout(3, 2));

        // Name input
        add(new JLabel("Name:"));
        nameField = new JTextField(name);
        add(nameField);

        // GPA input
        add(new JLabel("GPA:"));
        gpaField = new JTextField(gpa);
        add(gpaField);

        // Buttons
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        add(saveBtn);
        add(cancelBtn);

        // Save button action
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saved = true;
                setVisible(false);
            }
        });

        // Cancel button action
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        pack();
        setLocationRelativeTo(parent);
    }

    // Check if user saved
    public boolean isSaved() {
        return saved;
    }

    // Get name
    public String getStudentName() {
        return nameField.getText();
    }

    // Get GPA
    public String getStudentGpa() {
        return gpaField.getText();

    }
}
    
