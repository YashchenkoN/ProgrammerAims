package com.programmer.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		if(principal != null) {
			return "home/homeSignedIn";
		}
		return "home/homeNotSignedIn";
	}
}
