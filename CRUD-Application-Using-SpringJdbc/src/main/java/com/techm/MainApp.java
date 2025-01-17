package com.techm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeManager manager = context.getBean(EmployeeManager.class);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    Employee newEmployee = new Employee();
                    newEmployee.setName(name);
                    newEmployee.setDepartment(department);
                    manager.addEmployee(newEmployee);
                    System.out.println("Employee added successfully.");
                    break;
                case 2:
                    // View All Employees
                    List<Employee> employees = manager.getAllEmployees();
                    System.out.println("Employees:");
                    for (Employee employee : employees) {
                        System.out.println(employee);
                    }
                    break;
                case 3:
                    // View Employee by ID
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    Employee employee = manager.getEmployeeById(id);
                    if (employee != null) {
                        System.out.println("Employee Details: " + employee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 4:
                    // Update Employee
                    System.out.print("Enter employee ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Employee updateEmployee = manager.getEmployeeById(updateId);
                    if (updateEmployee != null) {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new department: ");
                        String newDepartment = scanner.nextLine();
                        updateEmployee.setName(newName);
                        updateEmployee.setDepartment(newDepartment);
                        manager.updateEmployee(updateEmployee);
                        System.out.println("Employee updated successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    // Delete Employee
                    System.out.print("Enter employee ID: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteEmployee(deleteId);
                    System.out.println("Employee deleted successfully.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
        context.close();
    }
}
