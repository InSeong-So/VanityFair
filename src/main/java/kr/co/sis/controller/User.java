package kr.co.sis.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sis.dao.DaoInterface;
import kr.co.sis.service.BoardServiceInterface;
import kr.co.sis.util.HttpUtil;

@Controller
public class User {

	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	@Autowired
	BoardServiceInterface bsi;
	
	@Autowired
	DaoInterface di;
	
	@RequestMapping("/user/{menu}")
	public ModelAndView user(@PathVariable("menu") String menu, HttpServletRequest req, RedirectAttributes attr, HttpSession session) {
		return bsi.user(menu, req, attr, session);
	}
	
	@RequestMapping("/session")
	public void Session(HttpServletResponse res,HttpSession session) {
		HashMap<String, Object> param  = (HashMap<String, Object>) session.getAttribute("user");
		HttpUtil.makeJsonWriter(res, param);
	}
	
	@RequestMapping("/sessionDel")
	public void SessionDel(HttpServletResponse res, HttpSession session) {
		session.invalidate();
	}
}
