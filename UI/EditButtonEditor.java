// This class lets the "Edit" button in a JTable cell open the EditStudentDialog
// and update the table with new values if the user clicks Save.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private int row;

    // Make the Edit button
    public EditButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Edit");
        
        // Stop editing when button is clicked
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    // Show the button in the cell
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    public Object getCellEditorValue() {
       // Get current values from the table 
    String id = table.getValueAt(row, 0).toString();
    String name = table.getValueAt(row, 1).toString();
    String gpa = table.getValueAt(row, 2).toString();

    // Open the edit dialog
    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(table);
    EditStudentDialog dialog = new EditStudentDialog(parent, name, gpa);
    dialog.setVisible(true);

    // If user clicked Save, update the table and data model
    if (dialog.isSaved()) {
        table.setValueAt(dialog.getStudentName(), row, 1);
         try {
                double gpaVal = Double.parseDouble(dialog.getStudentGpa());
                table.setValueAt(String.format("%.2f", gpaVal), row, 2);
            } catch (NumberFormatException e) {
                // If invalid, just put the raw string (or show error)
                table.setValueAt(dialog.getStudentGpa(), row, 2);
            }
        // Update your data model if you want changes to persist
        MainFrame.link.editStudent(id, dialog.getStudentName(), dialog.getStudentGpa());
    }
    return null;
 }
}
