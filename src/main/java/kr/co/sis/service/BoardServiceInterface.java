package kr.co.sis.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface BoardServiceInterface {

	public ModelAndView user(String menu, HttpServletRequest req, RedirectAttributes attr, HttpSession session);
	public HashMap<String,Object> fileupload(MultipartHttpServletRequest req, HashMap<String, Object> paramMap);
}
