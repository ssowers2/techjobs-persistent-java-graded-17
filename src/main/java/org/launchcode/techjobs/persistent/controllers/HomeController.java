package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository; //added private later

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());//added
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

        // Added 50-59 finding the selected employer by its id
        Optional<Employer> employerOptional = employerRepository.findById(employerId);

        if (employerOptional.isPresent()) {
            Employer selectedEmployer = employerOptional.get();
            newJob.setEmployer(selectedEmployer);
        }

        jobRepository.save(newJob); //added

        return "redirect:";
    }

//    @GetMapping("view/{jobId}")
//    public String displayViewJob(Model model, @PathVariable int jobId) {
//
//            return "view";
//    }

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
