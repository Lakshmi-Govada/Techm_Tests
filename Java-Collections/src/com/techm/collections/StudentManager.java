package com.techm.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String name, int age) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

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

            switch (choice) {
                case 1:
                    System.out.println("Enter Student ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Student Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Student Age:");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    addStudent(new Student(id, name, age));
                    break;
                case 2:
                    System.out.println("All Students:");
                    getAllStudents().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter Student ID to find:");
                    int findId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Student student = getStudentById(findId);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter Student ID to update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new age:");
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (updateStudent(updateId, newName, newAge)) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter Student ID to delete:");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (deleteStudent(deleteId)) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.run();
    }
}
