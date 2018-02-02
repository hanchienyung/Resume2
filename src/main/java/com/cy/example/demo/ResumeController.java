package com.cy.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ResumeController {

    @Autowired
    ResumeRepository resumeRepository;


    @RequestMapping("/")
    public String listResume(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String resumeForm(Model model){
        model.addAttribute("resume", new Resume());
        return "resumeform";
    };


    @PostMapping("/process")
    public String processForm(@Valid Resume resume, BindingResult result)
    {
        if (result.hasErrors()){
            return "resumeform";
        }
        resumeRepository.save(resume);

        return "redirect:/";
    }

}
