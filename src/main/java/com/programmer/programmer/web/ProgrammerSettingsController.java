package com.programmer.programmer.web;

import com.programmer.programmer.Programmer;
import com.programmer.programmer.ProgrammerForm;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by kolyan on 8/7/15.
 */
@Controller
@RequestMapping(value = "programmer")
public class ProgrammerSettingsController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/settings", "/settings/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        model.addAttribute(new ProgrammerForm());
        return "programmer/settings";
    }

    @RequestMapping(value = {"/settings", "/settings"}, method = RequestMethod.POST)
    public String settings(@Valid @ModelAttribute ProgrammerForm programmerForm, Errors errors, Principal principal
            , RedirectAttributes ra) {
        if(errors.hasErrors()) {
            MessageHelper.addErrorAttribute(ra, "programmer.settings.error");
            return "redirect:/programmer/settings";
        }
        Programmer programmer = programmerService.findByEmail(principal.getName());
        if(programmer != null && programmerForm != null) {
            if(programmerForm.getEmail() != null && !programmerForm.getEmail().equals(""))
                programmer.setEmail(programmerForm.getEmail());
            if(programmerForm.getPassword() != null && !programmerForm.getPassword().equals(""))
                programmer.setPassword(passwordEncoder.encode(programmerForm.getPassword()));
            if(programmerForm.getName() != null && !programmerForm.getName().equals(""))
                programmer.setName(programmerForm.getName());
            programmer = programmerService.update(programmer);
            MessageHelper.addSuccessAttribute(ra, "programmer.settings.successful");
        } else {
            MessageHelper.addErrorAttribute(ra, "programmer.settigs.error");
        }
        return "redirect:/programmer/settings";
    }
}
