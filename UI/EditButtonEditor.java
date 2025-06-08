// This class lets the "Edit" button in a JTable cell open the EditStudentDialog
// and update the table with new values if the user clicks Save.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private int row;
    private Link link;

    public EditButtonEditor(JCheckBox checkBox, JTable table, Link link) {
        super(checkBox);
        this.table = table;
        this.link = link;
        button = new JButton("Edit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        button.setText("Edit");
        return button;
    }

    public Object getCellEditorValue() {
        String id = table.getValueAt(row, 0).toString();
        String name = table.getValueAt(row, 1).toString();
        String gpa = table.getValueAt(row, 2).toString();

        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(table);
        EditStudentDialog dialog = new EditStudentDialog(parent, name, gpa);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            table.setValueAt(dialog.getStudentName(), row, 1);
            // table.setValueAt(dialog.getStudentGpa(), row, 2);
            try {
                double gpaVal = Double.parseDouble(dialog.getStudentGpa());
                table.setValueAt(String.format("%.2f", gpaVal), row, 2);
            } catch (NumberFormatException e) {
                // If invalid, just put the raw string (or show error)
                table.setValueAt(dialog.getStudentGpa(), row, 2);
            }
            link.editStudent(id, dialog.getStudentName(), dialog.getStudentGpa());
        }
        System.out.println("Returning: Edit");
        return "Edit";
    }
}
