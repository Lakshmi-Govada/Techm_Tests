package com.techm;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    private StudentDAO studentDAO = new StudentDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1: Add Student");
            System.out.println("2: View All Students");
            System.out.println("3: Find Student by ID");
            System.out.println("4: Update Student");
            System.out.println("5: Delete Student");
            System.out.println("6: Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        findStudentById();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private void addStudent() throws SQLException {
        System.out.println("Enter Student ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Student Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        studentDAO.addStudent(new Student(id, name, age));
        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void findStudentById() throws SQLException {
        System.out.println("Enter Student ID to find:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void updateStudent() throws SQLException {
        System.out.println("Enter Student ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new name:");
        String newName = scanner.nextLine();
        System.out.println("Enter new age:");
        int newAge = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        studentDAO.updateStudent(new Student(id, newName, newAge));
        System.out.println("Student updated successfully.");
    }

    private void deleteStudent() throws SQLException {
        System.out.println("Enter Student ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }

    public static void main(String[] args) {
        StudentApp app = new StudentApp();
        app.run();
    }
}

