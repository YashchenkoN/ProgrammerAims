package com.programmer.home;

import java.security.Principal;
import java.util.Calendar;

import com.programmer.programmer.Programmer;
import com.programmer.programmer.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	@Autowired
	private ProgrammerService programmerRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		if(principal != null) {
			String currentEmail = principal.getName();
			Programmer programmer = programmerRepository.findByEmail(currentEmail);
			programmer.setLastVisit(Calendar.getInstance());
			programmerRepository.update(programmer);
			model.addAttribute("programmer", programmer);
			return "home/homeSignedIn";
		}
//		return "home/homeNotSignedIn";
		return "test1";
	}
}
