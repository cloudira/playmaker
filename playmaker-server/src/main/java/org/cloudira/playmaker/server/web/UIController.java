package org.cloudira.playmaker.server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController {

	@RequestMapping(value = "/ui/**", method = RequestMethod.GET)
	public String ui() {
		return "/index.html";
	}
	
}
