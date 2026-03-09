// Represents a CS student at BITS Pilani Dubai Campus
// ID format: 20XXA7PS/TSXXXXU

public class Student {

    private String id; // BITS student ID
    private String name;
    private int age;

    // Constructor
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    // Extract PS/TS track from the ID
    public String getTrack() {
        return id.substring(6, 8);
    }

    // Convert student to file format for storage
    public String toFileFormat() {
        return id + "," + name + "," + age;
    }

    // Convert file line to Student object
    public static Student fromFileFormat(String line) {

        String[] data = line.split(",");

        return new Student(
                data[0],
                data[1],
                Integer.parseInt(data[2]));
    }

    // Validate BITS ID format
    public static boolean isValidBITSID(String id) {

        if (id.length() != 13)
            return false;

        if (!id.substring(4, 6).equals("A7"))
            return false;

        if (!(id.substring(6, 8).equals("PS") || id.substring(6, 8).equals("TS")))
            return false;

        if (!id.endsWith("U"))
            return false;

        return true;
    }

    // String format for displaying in tables
    @Override
    public String toString() {

        String track = getTrack();

        return String.format("%-15s %-15s %-5d %-5s",
                id, name, age, track);
    }

    // Update student info
    public void updateInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }
}