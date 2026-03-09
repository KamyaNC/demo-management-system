import java.io.*;
import java.util.*;

// Manages students: add, update, delete, load/save, generate IDs
public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    // Constructor loads existing students
    public StudentManager() {
        loadStudents();
    }

    // Add a student and save to file
    public void addStudent(Student s) {
        students.add(s);
        saveStudents();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudent(String id) {

        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }

        return null;
    }

    public boolean deleteStudent(String id) {

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {

            Student s = iterator.next();

            if (s.getId().equals(id)) {
                iterator.remove();
                saveStudents();
                return true;
            }
        }

        return false;
    }

    public boolean updateStudent(String id, String name, int age) {

        Student s = findStudent(id);

        if (s != null) {
            s.updateInfo(name, age);
            saveStudents();
            return true;
        }

        return false;
    }

    // Sort students alphabetically by name
    public void sortStudentsByName() {
        students.sort(Comparator.comparing(Student::toString));
    }

    // Display statistics: total students & average age
    public void showStatistics() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        int total = students.size();
        int sumAge = 0;

        for (Student s : students) {
            String[] data = s.toFileFormat().split(",");
            sumAge += Integer.parseInt(data[2]);
        }

        double average = (double) sumAge / total;

        System.out.println("\nStudent Statistics");
        System.out.println("------------------");
        System.out.println("Total Students : " + total);
        System.out.println("Average Age    : " + average);
    }

    // Save all students to file
    private void saveStudents() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                writer.println(s.toFileFormat());
            }

        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    // Load students from file
    private void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                students.add(Student.fromFileFormat(scanner.nextLine()));
            }

        } catch (Exception e) {
            System.out.println("Error loading student data.");
        }
    }

    // Generate a completely valid BITS ID
    public static String generateBITSID(int year, String track, int number) {

        String numStr = String.format("%04d", number); // 4-digit number
        return year + "A7" + track + numStr + "U";
    }

    // Generate 100 CS students automatically
    public void generateCSStudents() {

        Random rand = new Random();
        String[] tracks = {"PS", "TS"};

        for (int i = 1; i <= 100; i++) {

            int year = rand.nextBoolean() ? 2023 : 2024;
            String track = tracks[rand.nextInt(tracks.length)];
            String id = generateBITSID(year, track, i);
            String name = "Student" + i;
            int age = 18 + rand.nextInt(5);

            addStudent(new Student(id, name, age));
        }

        System.out.println("100 CS students generated successfully!");
    }
}