package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        Iterable<Job> jobs = jobRepository.findAll(); //added 11.10.23
        model.addAttribute("jobs", jobs);//added 11.10.23
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());//added
        model.addAttribute("skills", skillRepository.findAll()); //added
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        Employer selectedEmployer = employerRepository.findById(employerId).orElse(new Employer("Default Location"));

        List<Skill> selectedSkills = (List<Skill>) skillRepository.findAllById(skills);

        newJob.setSkills(selectedSkills);

        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("/view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:/";
        }
    }
}
