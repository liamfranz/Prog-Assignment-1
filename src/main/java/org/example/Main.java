import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    // A simple Student class to hold student information
    static class Student {
        String number;
        String name;
        int age;
        String email;
        String course;

        Student(String number, String name, int age, String email, String course) {
            this.number = number;
            this.name = name;
            this.age = age;
            this.email = email;
            this.course = course;
        }

        @Override
        public String toString() {
            return "Student Number: " + number + "\nName: " + name + "\nAge: " + age + "\nEmail: " + email + "\nCourse: " + course;
        }
    }

    // A list to store students
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initial menu
        System.out.println("Student Management Application");
        System.out.println("Enter (1) to launch menu or any other key to exit:");
        String menuOption = scanner.nextLine();

        if ("1".equals(menuOption)) {
            manageStudentMenu();
        }
    }

    private static void manageStudentMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nStudent Management Menu");
            System.out.println("1. Capture a new student");
            System.out.println("2. Search for a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Print a student report");
            System.out.println("5. Exit Application");
            System.out.print("Please select one of the following menu items: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    captureStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    printStudentReport();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }

    private static void captureStudent() {
        System.out.print("Enter student number: ");
        String number = scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        int age;
        while (true) {
            System.out.print("Enter student age (must be 16 or older): ");
            String ageInput = scanner.nextLine();
            try {
                age = Integer.parseInt(ageInput);
                if (age >= 16) {
                    break;
                } else {
                    System.out.println("Age must be 16 or older. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter student email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        students.add(new Student(number, name, age, email, course));
        System.out.println("Student added successfully.");
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private static void searchStudent() {
        System.out.print("Enter student number to search: ");
        String number = scanner.nextLine();
        Student foundStudent = null;

        for (Student student : students) {
            if (student.number.equals(number)) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Student found:\n" + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student number to delete: ");
        String number = scanner.nextLine();
        Iterator<Student> iterator = students.iterator();
        boolean deleted = false;

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.number.equals(number)) {
                iterator.remove();
                deleted = true;
                break;
            }
        }

        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void printStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }

        StringBuilder report = new StringBuilder("Student Report:\n");
        for (Student student : students) {
            report.append(student).append("\n\n");
        }

        System.out.println(report.toString());
    }
}
