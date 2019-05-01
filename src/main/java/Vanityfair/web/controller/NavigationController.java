package Vanityfair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/navigation")
public class NavigationController {

	@GetMapping("/introduce")
	public String introduce() {
		return "/introduce";
	}

	@GetMapping("/cost")
	public String cost() {
		return "/cost";
	}

	@GetMapping("/qna")
	public String qna() {
		return "/qna";
	}

	@GetMapping("/reservation")
	public String reservation() {
		return "/reservation";
	}

	@GetMapping("/login")
	public String login() {
		return "/customers/login";
	}
}
