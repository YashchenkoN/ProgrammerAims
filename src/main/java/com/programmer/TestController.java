package com.programmer;

import com.programmer.aim.AimForm;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.step.StepForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.NamedStoredProcedureQueries;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolyan on 8/30/15.
 */
/*@Controller
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testGet(Model model) {
        List<String> list = new ArrayList<>();
        list.add(new String());
        model.addAttribute("list", list);
        return "test";
    }
}*/

@Controller
public class TestController {

    @RequestMapping(value = "test")
    public String add(AimForm aimForm) {
        return "test";
    }

    @RequestMapping(value = "test", params = {"addOption"})
    public String addOption(AimForm aimForm, BindingResult result) {
        aimForm.getStepForms().add(new StepForm());
        return "test";
    }
}