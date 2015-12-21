package com.programmer.web;

import com.programmer.commons.AimForm;
import com.programmer.services.aim.AimBuilder;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.commons.StepForm;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kolyan on 8/24/15.
 */
@Controller
@RequestMapping(value = "aim")
public class AimAddController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private AimBuilder aimBuilder;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(AimForm aimForm, Model model) {
        aimForm.getStepForms().add(new StepForm());
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        return "programmer/add-aim";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute AimForm aimForm,
                      Model model) {
        aimBuilder.buildAim(aimForm);
        MessageHelper.addSuccessAttribute(model, "Aim added success");
        return "redirect:/";
    }

    @RequestMapping(value = "/add", params = {"addRow"})
    public String addRow(AimForm aimForm, BindingResult bindingResult, Model model) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        aimForm.getStepForms().add(new StepForm());
        return "programmer/add-aim";
    }

    @RequestMapping(value = "/add", params = {"removeRow"})
    public String removeRow(AimForm aimForm, BindingResult bindingResult, Model model,
                            HttpServletRequest request) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        Integer index = Integer.valueOf(request.getParameter("removeRow"));
        if(!aimForm.getStepForms().isEmpty()) {
            aimForm.getStepForms().remove(index.intValue());
        }
        return "programmer/add-aim";
    }

}
