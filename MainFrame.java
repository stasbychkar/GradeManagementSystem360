import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class MainFrame extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public static final String EDIT = "Edit";
    public static final String DELETE = "Delete";

    public MainFrame() {
        // set the MainFrame
        setTitle("Grade Manager");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addStudentButton = new JButton("Add New Student");
        JButton generateChartButton = new JButton("Generate Chart");
        JButton viewStatsButton = new JButton("View Statistics");

        addStudentButton.addActionListener(e -> {
            // TODO: Add new student form
        });
        generateChartButton.addActionListener(e -> {
            // TODO: Generate chart with Python
        });
        viewStatsButton.addActionListener(e -> {
            // TODO: Show statistics
        });

        topPanel.add(addStudentButton);
        topPanel.add(generateChartButton);
        topPanel.add(viewStatsButton);
        add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"ID", "Name", "GPA", EDIT, DELETE};
        tableModel = new DefaultTableModel(null, columnNames) {
            // Needed because we add button elements in the table
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3; // Only Edit/Delete columns are editable
            }
        };

        studentTable = new JTable(tableModel);
        // Custom button renderers and editors for two last columns
        studentTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit"));
        studentTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add test dummy data
        for (Object[] student : DummyData.sampleStudents()) {
            tableModel.addRow(student);
        }
    }

    // Start the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
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

    public ButtonEditor(JCheckBox checkBox, String label) {
        super(checkBox);
        this.label = label;
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
                // TODO: Open edit form for student at `row`
            } else if (label.equals("Delete")) {
                // TODO: Delete student at `row`
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