package com.programmer.web;

import com.programmer.commons.ProgrammerForm;
import com.programmer.commons.AimForm;
import com.programmer.services.aim.AimFormBuilder;
import com.programmer.services.aim.AimBuilder;
import com.programmer.services.aim.AimService;
import com.programmer.entity.Programmer;
import com.programmer.services.programmer.ProgrammerFormBuilder;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.commons.StepForm;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by kolyan on 9/6/15.
 */
@Controller
@RequestMapping(value = "aim")
public class AimEditController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private AimService aimService;

    @Autowired
    private AimFormBuilder aimFormBuilder;

    @Autowired
    private AimBuilder aimBuilder;

    @Autowired
    private ProgrammerFormBuilder programmerFormBuilder;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Principal principal, Model model, RedirectAttributes ra) {
        if(principal != null) {
            Programmer programmer = programmerService.getLoggedProgrammer();
            ProgrammerForm programmerForm = programmerFormBuilder.build(programmer.getId());
            model.addAttribute("programmer", programmerForm);
            return "programmer/aims-edit";
        } else {
            MessageHelper.addErrorAttribute(ra, "programmer.permission");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editByNumber(@PathVariable Long id, Model model, AimForm aimForm) {
        aimForm = aimFormBuilder.buildAimForm(id);
        model.addAttribute("aimForm", aimForm);
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        return "programmer/aim-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editByNumberPost(@PathVariable Long id, Model model, AimForm aimForm) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        model.addAttribute("aimForm", aimForm);
        aimBuilder.buildAim(aimForm);
        MessageHelper.addSuccessAttribute(model, "Сохранено");
        return "programmer/aim-edit";
    }

    @RequestMapping(value = "/edit/{id}", params = {"addRow"})
    public String addRow(@PathVariable Long id, AimForm aimForm, BindingResult bindingResult, Model model) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        aimForm.getSteps().add(new StepForm());
        return "programmer/add-edit";
    }

    @RequestMapping(value = "/edit/{id}", params = {"removeRow"})
    public String removeRow(@PathVariable Long id, AimForm aimForm, BindingResult bindingResult, Model model,
                            HttpServletRequest request) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        Integer index = Integer.valueOf(request.getParameter("removeRow"));
        if(!aimForm.getSteps().isEmpty()) {
            aimForm.getSteps().remove(index.intValue());
        }
        return "programmer/add-edit";
    }
}
