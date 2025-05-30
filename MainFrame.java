import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    JTable studentTable;
    DefaultTableModel tableModel;
    static Link link;

    public MainFrame() {
        // Set the MainFrame
        setTitle("Grade Manager");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addStudentButton = new JButton("Add New Student");
        JButton generateChartButton = new JButton("Generate Chart");
        JButton viewStatsButton = new JButton("View Statistics");


        addStudentButton.addActionListener(e -> addNewStudent());

        generateChartButton.addActionListener(e -> {
            link.generateChart();
            // TODO: Chart logic
        });
        viewStatsButton.addActionListener(e -> {
            // TODO: Stats logic
        });

        // Add buttons to panel and panel to frame
        topPanel.add(addStudentButton);
        topPanel.add(generateChartButton);
        topPanel.add(viewStatsButton);
        add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"ID", "Name", "GPA", "Edit", "Delete"};
        tableModel = new DefaultTableModel(null, columns) {
            // Needed because we add button elements in the table
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3;  // Only Edit/Delete buttons editable
            }
        };

        // Custom button renderers and editors for two last columns
        studentTable = new JTable(tableModel);
        studentTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", this));
        studentTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", this));


        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add test dummy data
        for(Student student : link.students )
        {
            tableModel.addRow(new Object[]{student.getId(), student.getName(), student.getGPA(), "Edit", "Delete"});
        }
    }

    // Opens the Add New Student dialog
    private void addNewStudent() {
        JDialog dialog = new JDialog(this, "Add New Student", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        // Input fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField gpaField = new JTextField();

        // Create labels with left padding
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JLabel gpaLabel = new JLabel("GPA:");
        gpaLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        // Add padded labels and fields to dialog
        dialog.add(idLabel);
        dialog.add(idField);
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(gpaLabel);
        dialog.add(gpaField);

        // Action buttons
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(addButton);
        dialog.add(cancelButton);

        // Add button logic
        addButton.addActionListener(e -> {
            String idText = idField.getText().trim();
            String name = nameField.getText().trim();
            String gpaText = gpaField.getText().trim();

            // Empty field check
            if (idText.isEmpty() || name.isEmpty() || gpaText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean valid = true;
            StringBuilder errorMessage = new StringBuilder();
            int id = -1;
            double gpa = -1;

            // ID validation
            try {
                id = Integer.parseInt(idText);
                if (id <= 0) {
                    valid = false;
                    errorMessage.append("• ID must be a positive integer.\n");
                }
            } catch (NumberFormatException ex) {
                valid = false;
                errorMessage.append("• ID must be a valid number.\n");
            }

            // GPA validation
            try {
                gpa = Double.parseDouble(gpaText);
                if (gpa < 0.0 || gpa > 4.0) {
                    valid = false;
                    errorMessage.append("• GPA must be between 0.0 and 4.0.\n");
                }
            } catch (NumberFormatException ex) {
                valid = false;
                errorMessage.append("• GPA must be a valid decimal number.\n");
            }

            // Show combined errors if validation fails
            if (!valid) {
                JOptionPane.showMessageDialog(dialog, errorMessage.toString(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Duplicate ID check
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String existingId = tableModel.getValueAt(i, 0).toString();
                if (existingId.equals(String.valueOf(id))) {
                    JOptionPane.showMessageDialog(dialog, "A student with this ID already exists.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            link.addStudent(idText,name,gpaText);

            // Add row and sort table
            tableModel.addRow(new Object[]{String.valueOf(id), name, String.format("%.1f", gpa), "Edit", "Delete"});
            sortTableByID();
            dialog.dispose();
        });

        // Cancel closes dialog
        cancelButton.addActionListener(e -> dialog.dispose());

        // Center dialog on screen
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Sorts table rows by ID (numeric ascending)
    private void sortTableByID() {
        int rowCount = tableModel.getRowCount();
        List<Object[]> rows = new ArrayList<>();

        // Copy rows to list
        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[tableModel.getColumnCount()];
            for (int j = 0; j < row.length; j++) {
                row[j] = tableModel.getValueAt(i, j);
            }
            rows.add(row);
        }

        // Sort by ID
        rows.sort((a, b) -> {
            try {
                int id1 = Integer.parseInt(a[0].toString());
                int id2 = Integer.parseInt(b[0].toString());
                return Integer.compare(id1, id2);
            } catch (NumberFormatException e) {
                return a[0].toString().compareToIgnoreCase(b[0].toString());
            }
        });

        // Update table
        tableModel.setRowCount(0);
        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }

    // Start the program
    public static void main(String[] args) {
        link = new Link();
        link.readFile();
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
        // link.generateChart();
    }
}

// Custom renderer to display table cells as buttons
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// Custom editor to handle button clicks in the table
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean clicked;
    private int row;
    private MainFrame frame;

    public ButtonEditor(JCheckBox checkBox, String label, MainFrame frame) {
        super(checkBox);
        this.label = label;
        this.frame = frame;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.row = row;
        button.setText(label);
        clicked = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (clicked) {
            if (label.equals("Edit")) {
                // TODO
            } else if (label.equals("Delete")) {
                Object idt = ((DefaultTableModel) frame.studentTable.getModel()).getValueAt(row, 0);
                String id = String.valueOf(idt);
                SwingUtilities.invokeLater(() -> {
                    MainFrame.link.deleteStudent(id);
                    ((DefaultTableModel) frame.studentTable.getModel()).removeRow(row);
                });
            }
        }
        clicked = false;
        return label;
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}