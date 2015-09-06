package com.programmer.aim.web;

import com.programmer.aim.Aim;
import com.programmer.aim.AimForm;
import com.programmer.aim.AimFormBuilder;
import com.programmer.aim.builder.AimBuilder;
import com.programmer.aim.service.AimService;
import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.step.StepForm;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        Programmer programmer = programmerService.getLoggedProgrammer();
        Set<Aim> aims = programmer.getAims();
        model.addAttribute("programmer", programmer);
        return "programmer/aims-edit";
    }

    @RequestMapping(value = "/edit", params = {"remove"})
    public String remove(Model model,
                            HttpServletRequest request) {
        Programmer programmer = programmerService.getLoggedProgrammer();
        model.addAttribute("programmer", programmer);
        Integer index = Integer.valueOf(request.getParameter("remove"));
        Set<Aim> aims = programmer.getAims();
        if(!aims.isEmpty()) {
            Iterator<Aim> iterator = aims.iterator();
            while(iterator.hasNext()) {
                Aim aim = iterator.next();
                if(aim.getId().intValue() == index) {
                    aims.remove(aim);
                    aimService.delete(aim);
                }
            }
            programmerService.update(programmer);
        }
        return "programmer/aims-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editByNumber(@PathVariable Long id, Model model, AimForm aimForm) {
        aimForm = aimFormBuilder.buildAimForm(id);
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
        aimForm.getStepForms().add(new StepForm());
        return "programmer/add-edit";
    }

    @RequestMapping(value = "/edit/{id}", params = {"removeRow"})
    public String removeRow(@PathVariable Long id, AimForm aimForm, BindingResult bindingResult, Model model,
                            HttpServletRequest request) {
        model.addAttribute("programmer", programmerService.getLoggedProgrammer());
        Integer index = Integer.valueOf(request.getParameter("removeRow"));
        if(!aimForm.getStepForms().isEmpty()) {
            aimForm.getStepForms().remove(index.intValue());
        }
        return "programmer/add-edit";
    }
}
