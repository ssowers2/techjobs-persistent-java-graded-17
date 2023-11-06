package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;


@Entity
public class Job extends AbstractEntity { //extended

    @ManyToOne //added
    @JoinColumn (name = "employer_id") //added
    private Employer employer;//updated
    private String skills;//updated

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer employer, String skills) { //updated 20-23
        this.employer = employer;
        this.skills = skills;
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}

