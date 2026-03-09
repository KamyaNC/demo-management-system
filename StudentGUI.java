import java.awt.*;
import javax.swing.*;

// GUI for DemoManagement: BITS CS Student System
public class StudentGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;

    private JTextArea displayArea;

    private StudentManager manager;

    public StudentGUI() {

        manager = new StudentManager();

        setTitle("DemoManagement – BITS CS Students");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input fields
        add(new JLabel("Student ID"));
        idField = new JTextField(20);
        add(idField);

        add(new JLabel("Name"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Age"));
        ageField = new JTextField(20);
        add(ageField);

        // Buttons
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton genBtn = new JButton("Generate 100 CS Students");

        add(addBtn);
        add(viewBtn);
        add(genBtn);

        displayArea = new JTextArea(15, 35);
        add(new JScrollPane(displayArea));

        // Button actions
        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> showStudents());
        genBtn.addActionListener(e -> generateStudents());
    }

    private void addStudent() {

        String id = idField.getText();

        if (!Student.isValidBITSID(id)) {
            displayArea.setText("Invalid BITS ID format.\nExample: 2024A7PS0119U");
            return;
        }

        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        manager.addStudent(new Student(id, name, age));

        displayArea.setText("Student added successfully.\n");
    }

    private void showStudents() {

        displayArea.setText("");

        for (Student s : manager.getStudents()) {
            displayArea.append(s.toString() + "\n");
        }
    }

    private void generateStudents() {

        manager.generateCSStudents();
        displayArea.setText("100 CS students generated!\n");
    }
}