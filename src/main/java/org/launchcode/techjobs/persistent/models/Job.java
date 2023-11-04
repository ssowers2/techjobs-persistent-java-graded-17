package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;


@Entity
public class Job extends AbstractEntity { //extended

    private String name; //updated

    @ManyToOne //added
    @JoinColumn (name = "employer_id") //added
    private Employer employer;//updated
    private String skills;//updated

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(String name, Employer employer, String skills) { //updated 20-23
        this.name = name;
        this.employer = employer;
        this.skills = skills;
    }

    // Getters and setters.
    public String getName() { //updated getters & setters
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

