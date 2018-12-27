package kr.co.sis.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.sis.util.HttpUtil;

@Controller
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model, HttpSession session) {
		
		if(HttpUtil.checkLogin(session)) {
			logger.info("*********메인화면*********");
		}else {
			logger.info("*********로그인이 필요합니다.*********");
		}
		
		return "redirect:/resources/main.html";
	}
}
