package com.stackly.onlinevotingsystemapplicaion.project;

public class User {

    // Student details
    private String studentId;
    private String name;
    private String department;
    private String password;

    // Constructor
    public User(String studentId, String name, String department, String password) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.password = password;
    }

    // Get student ID
    public String getStudentId() {
        return studentId;
    }

    // Get student name
    public String getName() {
        return name;
    }

    // Get department
    public String getDepartment() {
        return department;
    }

    // Get password
    public String getPassword() {
        return password;
    }
}
