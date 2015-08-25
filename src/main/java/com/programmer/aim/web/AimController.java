package com.programmer.aim.web;

import com.programmer.aim.AimForm;
import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.step.StepForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kolyan on 8/24/15.
 */
@Controller
@RequestMapping(value = "aim")
public class AimController {

    @Autowired
    private ProgrammerService programmerService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("aimForm", new AimForm());
        return "programmer/add-aim";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute AimForm aimForm) {
        Programmer programmer = programmerService.getLoggedProgrammer();
        programmer.addAim(aimForm.createAim());
        programmerService.update(programmer);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", params = "addRow")
    public String addRow(@ModelAttribute AimForm aimForm) {
        aimForm.getStepForms().add(new StepForm());
        return "programmer/add";
    }

}
