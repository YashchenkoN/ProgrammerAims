package com.programmer.web;

import com.programmer.entity.Programmer;
import com.programmer.entity.ProgrammerRole;
import com.programmer.services.programmer.ProgrammerRoleService;
import com.programmer.entity.Roles;
import com.programmer.services.programmer.ProgrammerService;
import com.programmer.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by kolyan on 8/7/15.
 */

@Controller
@RequestMapping(value = "activate")
public class ProgrammerActivationController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private ProgrammerRoleService programmerRoleService;

    @RequestMapping(value = "/{activationKey}", method = RequestMethod.GET)
    public String activate(@PathVariable String activationKey, RedirectAttributes ra) {
        Programmer programmer = programmerService.findByActivationKey(activationKey);
        if(programmer != null && programmer.getRole().equals(new ProgrammerRole())) {
            if(programmer.getActivationKey().equals(activationKey)) {
                programmer.setRole(programmerRoleService.read(Roles.ROLE_ACTIVE));
                programmer = programmerService.update(programmer);
                MessageHelper.addSuccessAttribute(ra, "activation.success");
                return "redirect:/";
            } else {
                MessageHelper.addErrorAttribute(ra, "activation.already");
                return "redirect:/";
            }
        } else {
            MessageHelper.addErrorAttribute(ra, "activation.failed");
            return "redirect:/";
        }
    }
}
