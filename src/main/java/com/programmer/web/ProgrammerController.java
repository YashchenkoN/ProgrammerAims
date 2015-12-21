package com.programmer.web;

import com.programmer.entity.Aim;
import com.programmer.services.aim.AimService;
import com.programmer.entity.Programmer;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Created by kolyan on 8/5/15.
 */

@Controller
@RequestMapping(value = "programmer")
public class ProgrammerController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private AimService aimService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProgrammerById(@PathVariable Long id, Model model, RedirectAttributes ra) {
        if(id != null) {
            Programmer programmer = programmerService.findById(id);
            if(programmer != null) {
                List<Aim> programmerAims = aimService.getListOfProgrammerAims(programmer);
                for(Aim aim : programmerAims) {
                    System.out.println(aim);
                }
                model.addAttribute("programmer", programmer);
                model.addAttribute("aims", programmerAims);
                return "programmer/information";
            } else {
                MessageHelper.addErrorAttribute(ra, "programmer.notfound");
                return "redirect:/";
            }
        } else {
            MessageHelper.addErrorAttribute(ra, "programmer.error");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String getMyPage(RedirectAttributes ra) {
        Programmer loggedProgrammer = programmerService.getLoggedProgrammer();
        if(loggedProgrammer != null) {
            return "redirect:/programmer/" + loggedProgrammer.getId().toString();
        } else {
            MessageHelper.addErrorAttribute(ra, "programmer.permission");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String programmersList(Model model, Principal principal) {
        //Programmer programmer = programmerRepository.findByEmail(principal.getName());
        List<Programmer> list = programmerService.getListOfProgrammers();
        model.addAttribute("list", list);
        //model.addAttribute(programmer);
        return "programmer/list";
    }

}
