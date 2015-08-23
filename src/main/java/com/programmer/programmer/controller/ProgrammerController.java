package com.programmer.programmer.controller;

import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Programmer programmers(Principal principal) {
        Assert.notNull(principal);
        return programmerService.findByEmail(principal.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProgrammerById(@PathVariable Long id, Model model, RedirectAttributes ra) {
        if(id != null) {
            Programmer programmer = programmerService.findById(id);
            if(programmer != null) {
                model.addAttribute("programmer", programmer);
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String programmersList(Model model, Principal principal) {
        //Programmer programmer = programmerRepository.findByEmail(principal.getName());
        List<Programmer> list = programmerService.getListOfProgrammers();
        model.addAttribute("list", list);
        //model.addAttribute(programmer);
        return "programmer/list";
    }

}
