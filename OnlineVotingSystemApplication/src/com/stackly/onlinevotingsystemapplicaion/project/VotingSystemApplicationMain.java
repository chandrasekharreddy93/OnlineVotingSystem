package com.stackly.onlinevotingsystemapplicaion.project;

import java.util.*;

public class VotingSystemApplicationMain {

    public static void main(String[] args) {

        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        // Create VotingSystem object
        VotingSystem system = new VotingSystem();

        // Keep displaying main menu
        while (true) {

            System.out.println("\n==========================================");
            System.out.println("       COLLEGE ONLINE VOTING SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Add Candidate");
            System.out.println("4. View Candidates");
            System.out.println("5. Search Candidate");
            System.out.println("6. Election Statistics");
            System.out.println("7. Election Result");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            // Read menu choice
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            // Clear Enter key
            sc.nextLine();

            // Process menu choice
            switch (choice) {

                // Student Registration
                case 1:

                    System.out.println("\n===== STUDENT REGISTRATION =====");

                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Create Password: ");
                    String password = sc.nextLine();

                    // Create User object
                    User user = new User(studentId, name, department, password);

                    // Register student
                    try {
                        system.registerUser(user);
                    } catch (VotingException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;


                // Student Login
                case 2:

                    System.out.println("\n========== STUDENT LOGIN ==========");

                    System.out.print("Enter Student ID: ");
                    String loginId = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    // Login
                    try {

                        User loggedUser = system.login(loginId, loginPassword);

                        System.out.println("\nLogin successful!");
                        System.out.println("Welcome " + loggedUser.getName());

                        // Open student menu
                        studentMenu(sc, system, loggedUser);

                    } catch (VotingException e) {
                        System.out.println("Login Error: " + e.getMessage());
                    }

                    break;


                // Add Candidate
                case 3:

                    System.out.println("\n===== ADD CANDIDATE =====");

                    System.out.print("Enter Candidate ID: ");
                    int candidateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Candidate Name: ");
                    String candidateName = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String candidateDepartment = sc.nextLine();

                    System.out.print("Enter Manifesto: ");
                    String manifesto = sc.nextLine();

                    // Create Candidate object
                    Candidate candidate = new Candidate(candidateId, candidateName, candidateDepartment, manifesto);

                    // Add candidate
                    try {
                        system.addCandidate(candidate);
                    } catch (VotingException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;


                // View Candidates
                case 4:

                    system.displayCandidates();

                    break;


                // Search Candidate
                case 5:

                    System.out.print("Enter candidate name: ");
                    String searchName = sc.nextLine();

                    system.searchCandidate(searchName);

                    break;


                // Election Statistics
                case 6:

                    system.displayStatistics();

                    break;


                // Election Result
                case 7:

                    system.displayResult();

                    break;


                // Exit
                case 8:

                    System.out.println("\nThank you for using College Online Voting System!");

                    sc.close();

                    return;


                // Invalid choice
                default:

                    System.out.println("Invalid choice.");
            }
        }
    }


    // Student menu
    public static void studentMenu(Scanner sc, VotingSystem system, User user) {

        // Keep student menu running
        while (true) {

            System.out.println("\n========== STUDENT MENU ==========");
            System.out.println("1. View Candidates");
            System.out.println("2. Cast Vote");
            System.out.println("3. Check Voting Status");
            System.out.println("4. Search Candidate");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            int choice;

            // Read student menu choice
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            // Clear Enter key
            sc.nextLine();

            switch (choice) {

                // View candidates
                case 1:

                    system.displayCandidates();

                    break;


                // Cast vote
                case 2:

                    System.out.println("\n========== CAST VOTE ==========");

                    system.displayCandidates();

                    System.out.print("\nEnter Candidate ID: ");
                    int candidateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Confirm vote? (yes/no): ");
                    String confirmation = sc.nextLine();

                    // Confirm vote
                    if (confirmation.equalsIgnoreCase("yes")) {

                        try {

                            system.castVote(user.getStudentId(), candidateId);

                        } catch (VotingException e) {

                            System.out.println("Voting Error: " + e.getMessage());
                        }

                    } else {

                        System.out.println("Vote cancelled.");
                    }

                    break;


                // Check voting status
                case 3:

                    system.checkVotingStatus(user.getStudentId());

                    break;


                // Search candidate
                case 4:

                    System.out.print("Enter candidate name: ");
                    String searchName = sc.nextLine();

                    system.searchCandidate(searchName);

                    break;


                // Logout
                case 5:

                    System.out.println("Logged out successfully.");

                    return;


                // Invalid choice
                default:

                    System.out.println("Invalid choice.");
            }
        }
    }
}
