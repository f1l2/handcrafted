package at.f1l2.handcrafted.poc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController
{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(Model model) {
	       model.addAttribute("value", "value");
	       return "index";
	   }
}
