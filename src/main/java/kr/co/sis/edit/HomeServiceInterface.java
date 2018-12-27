package kr.co.sis.edit;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface HomeServiceInterface {
	public HashMap<String,Object> fileupload(MultipartHttpServletRequest req, HashMap<String, Object> paramMap);
}
