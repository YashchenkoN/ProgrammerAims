package com.programmer.web;

import com.programmer.api.StepForm;
import com.programmer.api.aim.AimForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.programmer.services.aim.AimBuilder;
import com.programmer.utils.MessageHelper;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by kolyan on 8/24/15.
 */
@Controller
@RequestMapping(value = "aim")
public class AimAddController {

    @Autowired
    private AimBuilder aimBuilder;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Principal principal, AimForm aimForm, Model model, RedirectAttributes ra) {
        if(principal != null) {
            aimForm.getSteps().add(new StepForm());
            return "programmer/add-aim";
        } else {
            MessageHelper.addErrorAttribute(ra, "programmer.permission");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute AimForm aimForm,
                      Model model) {
        aimBuilder.buildAim(aimForm);
        MessageHelper.addSuccessAttribute(model, "Aim added success");
        return "redirect:/aim/edit";
    }

    @RequestMapping(value = "/add", params = {"addRow"})
    public String addRow(AimForm aimForm, BindingResult bindingResult, Model model) {
        aimForm.getSteps().add(new StepForm());
        return "programmer/add-aim";
    }

    @RequestMapping(value = "/add", params = {"removeRow"})
    public String removeRow(AimForm aimForm, BindingResult bindingResult, Model model,
                            HttpServletRequest request) {
        Integer index = Integer.valueOf(request.getParameter("removeRow"));
        if(!aimForm.getSteps().isEmpty()) {
            aimForm.getSteps().remove(index.intValue());
        }
        return "programmer/add-aim";
    }

}
