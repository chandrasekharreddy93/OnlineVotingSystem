package com.stackly.onlinevotingsystemapplicaion.project;

import java.util.*;


public class VotingSystem {

    // Store registered students
    private Map<String, User> users = new HashMap<>();

    // Store candidates
    private Map<Integer, Candidate> candidates = new HashMap<>();

    // Store voting status
    private Map<String, Boolean> votedUsers = new HashMap<>();

    // Register student
    public void registerUser(User user) throws VotingException {
        if (users.containsKey(user.getStudentId())) {
            throw new VotingException("Student ID already registered.");
        }

        users.put(user.getStudentId(), user);
        votedUsers.put(user.getStudentId(), false);

        System.out.println("Student registered successfully!");
    }

    // Student login
    public User login(String studentId, String password) throws VotingException {
        if (!users.containsKey(studentId)) {
            throw new VotingException("Student ID not found.");
        }

        User user = users.get(studentId);

        if (!user.getPassword().equals(password)) {
            throw new VotingException("Incorrect password.");
        }

        return user;
    }

    // Add candidate
    public void addCandidate(Candidate candidate) throws VotingException {
        if (candidates.containsKey(candidate.getCandidateId())) {
            throw new VotingException("Candidate ID already exists.");
        }

        candidates.put(candidate.getCandidateId(), candidate);

        System.out.println("Candidate added successfully!");
    }

    // Display candidates
    public void displayCandidates() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }

        System.out.println("\n========== CANDIDATE LIST ==========");

        for (Candidate candidate : candidates.values()) {
            System.out.println("Candidate ID : " + candidate.getCandidateId());
            System.out.println("Name         : " + candidate.getName());
            System.out.println("Department   : " + candidate.getDepartment());
            System.out.println("Manifesto    : " + candidate.getManifesto());
            System.out.println("Votes        : " + candidate.getVotes());
            System.out.println("------------------------------------");
        }
    }

    // Cast vote
    public void castVote(String studentId, int candidateId) throws VotingException {
        if (!users.containsKey(studentId)) {
            throw new VotingException("Student is not registered.");
        }

        if (votedUsers.get(studentId)) {
            throw new VotingException("You have already voted.");
        }

        if (!candidates.containsKey(candidateId)) {
            throw new VotingException("Invalid candidate ID.");
        }

        Candidate candidate = candidates.get(candidateId);

        candidate.addVote();

        votedUsers.put(studentId, true);

        System.out.println("\nVote cast successfully!");
        System.out.println("You voted for: " + candidate.getName());
    }

    // Check voting status
    public void checkVotingStatus(String studentId) {
        if (votedUsers.get(studentId)) {
            System.out.println("You have already voted.");
        } else {
            System.out.println("You have not voted yet.");
        }
    }

    // Search candidate
    public void searchCandidate(String searchName) {
        boolean found = false;

        for (Candidate candidate : candidates.values()) {
            if (candidate.getName().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("\nCandidate ID : " + candidate.getCandidateId());
                System.out.println("Name         : " + candidate.getName());
                System.out.println("Department   : " + candidate.getDepartment());
                System.out.println("Manifesto    : " + candidate.getManifesto());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Candidate not found.");
        }
    }

    // Get total students
    public int getTotalStudents() {
        return users.size();
    }

    // Get total votes
    public int getTotalVotes() {
        int totalVotes = 0;

        for (Candidate candidate : candidates.values()) {
            totalVotes = totalVotes + candidate.getVotes();
        }

        return totalVotes;
    }

    // Display election statistics
    public void displayStatistics() {
        int totalStudents = getTotalStudents();
        int totalVotes = getTotalVotes();
        int notVoted = totalStudents - totalVotes;
        double percentage = 0;

        if (totalStudents > 0) {
            percentage = (totalVotes * 100.0) / totalStudents;
        }

        System.out.println("\n========== ELECTION STATISTICS ==========");
        System.out.println("Total Students : " + totalStudents);
        System.out.println("Total Votes    : " + totalVotes);
        System.out.println("Not Voted      : " + notVoted);
        System.out.printf("Voting %%       : %.2f%%\n", percentage);
    }

    // Display election result
    public void displayResult() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }

        Candidate winner = null;
        Candidate runnerUp = null;

        // Find winner and runner-up
        for (Candidate candidate : candidates.values()) {
            if (winner == null || candidate.getVotes() > winner.getVotes()) {
                runnerUp = winner;
                winner = candidate;
            } else if (runnerUp == null || candidate.getVotes() > runnerUp.getVotes()) {
                runnerUp = candidate;
            }
        }

        System.out.println("\n========== ELECTION RESULT ==========");

        // Display all vote counts
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getName() + " : " + candidate.getVotes() + " votes");
        }

        // Display winner
        System.out.println("\n******** WINNER ********");
        System.out.println("Name       : " + winner.getName());
        System.out.println("Department : " + winner.getDepartment());
        System.out.println("Votes      : " + winner.getVotes());

        // Display runner-up
        if (runnerUp != null) {
            System.out.println("\n******** RUNNER-UP ********");
            System.out.println("Name       : " + runnerUp.getName());
            System.out.println("Department : " + runnerUp.getDepartment());
            System.out.println("Votes      : " + runnerUp.getVotes());
        }
    }
}
