package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
    public class Employer extends AbstractEntity {

        @NotNull(message = "Location is required")
        @Size(max = 50, message = "Location must be less than or equal to 50 characters")
        private String location;

        @OneToMany
        @JoinColumn (name = "employer_id")
        private List <Job> jobs = new ArrayList<>();

        public Employer(String location) {
            this.location = location;
    }

        public List<Job> getJobs() {
            return jobs;
    }

        public String getLocation() {
            return location;
        }

        public Employer() {
        }

    public void setLocation(String location) {
            this.location = location;
        }
    }