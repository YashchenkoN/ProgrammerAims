package com.programmer.signup;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.programmer.programmer.Programmer;
import com.programmer.programmer.roles.ProgrammerRoleService;
import com.programmer.programmer.roles.Roles;
import com.programmer.programmer.service.ProgrammerDetailsService;
import com.programmer.programmer.service.ProgrammerService;
import com.programmer.mail.MailService;
import com.programmer.utils.LinkGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.programmer.support.web.*;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";
	
	@Autowired
	private ProgrammerService programmerService;

	@Autowired
	private ProgrammerDetailsService programmerDetailsService;

	@Autowired
	private MailService mailService;

	@Autowired
	private ProgrammerRoleService programmerRoleService;

	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors,
						 HttpServletRequest req, Model model) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		if(programmerService.findByEmail(signupForm.getEmail()) != null) {
			MessageHelper.addErrorAttribute(model, "signup.error");
			return SIGNUP_VIEW_NAME;
		}
		Programmer programmer = programmerService.add(signupForm.createProgrammer());
		programmer.setRole(programmerRoleService.read(Roles.ROLE_UNACTIVE));
		programmerDetailsService.signin(programmer);
		String activationLink = LinkGenerationUtil.getActivationLink(req, programmer.getActivationKey());
		mailService.sendMail(programmer.getEmail(), "Activation", activationLink);
        MessageHelper.addSuccessAttribute(model, "signup.success");
		return "redirect:/";
	}
}
