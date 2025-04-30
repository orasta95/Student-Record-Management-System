import java.util.Scanner;

/**
 * Student Record Management System
 * 
 * This program allows an administrator to:
 *  - Add a new student
 *  - Update student information
 *  - View student details
 * 
 * Student data is stored using static variables and arrays.
 * 
 * Instructions for Running:
 * 1. Compile the program using: javac StudentRecordManagementSystem.java
 * 2. Run the program using: java StudentRecordManagementSystem
 * 3. Follow the menu prompts to manage student records.
 */

public class StudentRecordManagementSystem {

    // Static variables for student data storage
    static String[] studentNames = new String[100];
    static String[] studentIDs = new String[100];
    static int[] studentAges = new int[100];
    static String[] studentGrades = new String[100];
    static int totalStudents = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Administrator menu
        do {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Error handling for menu input
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    viewStudent(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        } while (choice != 4);

        scanner.close();
    }

    /**
     * Adds a new student to the record.
     */
    public static void addStudent(Scanner scanner) {
        if (totalStudents >= 100) {
            System.out.println("Student limit reached. Cannot add more students.");
            return;
        }

        System.out.print("Enter student name: ");
        studentNames[totalStudents] = scanner.nextLine();

        System.out.print("Enter student ID: ");
        studentIDs[totalStudents] = scanner.nextLine();

        System.out.print("Enter student age: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid age: ");
            scanner.next();
        }
        studentAges[totalStudents] = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter student grade: ");
        studentGrades[totalStudents] = scanner.nextLine();

        totalStudents++;
        System.out.println("Student added successfully.");
    }

    /**
     * Updates an existing student's information.
     */
    public static void updateStudent(Scanner scanner) {
        System.out.print("Enter student ID to update: ");
        String id = scanner.nextLine();

        int index = findStudentByID(id);

        if (index == -1) {
            System.out.println("Student ID not found.");
            return;
        }

        System.out.println("Updating information for " + studentNames[index]);
        System.out.print("Enter new name (leave blank to keep unchanged): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            studentNames[index] = name;
        }

        System.out.print("Enter new age (enter -1 to keep unchanged): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid age: ");
            scanner.next();
        }
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        if (age != -1) {
            studentAges[index] = age;
        }

        System.out.print("Enter new grade (leave blank to keep unchanged): ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) {
            studentGrades[index] = grade;
        }

        System.out.println("Student information updated successfully.");
    }

    /**
     * Displays a student's details based on ID.
     */
    public static void viewStudent(Scanner scanner) {
        System.out.print("Enter student ID to view details: ");
        String id = scanner.nextLine();

        int index = findStudentByID(id);

        if (index == -1) {
            System.out.println("Student ID not found.");
            return;
        }

        System.out.println("\n--- Student Details ---");
        System.out.println("Name : " + studentNames[index]);
        System.out.println("ID   : " + studentIDs[index]);
        System.out.println("Age  : " + studentAges[index]);
        System.out.println("Grade: " + studentGrades[index]);
    }

    /**
     * Finds a student index by their ID.
     * 
     * @param id The student ID to search for.
     * @return The index of the student, or -1 if not found.
     */
    public static int findStudentByID(String id) {
        for (int i = 0; i < totalStudents; i++) {
            if (studentIDs[i].equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
