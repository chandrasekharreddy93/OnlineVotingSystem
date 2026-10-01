package com.stackly.onlinevotingsystemapplicaion.project;

public class Candidate {

    // Candidate details
    private int candidateId;
    private String name;
    private String department;
    private String manifesto;
    private int votes;

    // Constructor
    public Candidate(int candidateId, String name, String department, String manifesto) {
        this.candidateId = candidateId;
        this.name = name;
        this.department = department;
        this.manifesto = manifesto;
        this.votes = 0;
    }

    // Get candidate ID
    public int getCandidateId() {
        return candidateId;
    }

    // Get candidate name
    public String getName() {
        return name;
    }

    // Get department
    public String getDepartment() {
        return department;
    }

    // Get manifesto
    public String getManifesto() {
        return manifesto;
    }

    // Get votes
    public int getVotes() {
        return votes;
    }

    // Increase vote count
    public void addVote() {
        votes++;
    }
}
