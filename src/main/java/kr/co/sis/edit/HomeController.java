package kr.co.sis.edit;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

//@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("home()");
		return "home";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file() {
		logger.info("file()");
		return "file";
	}
	
	@Autowired
	HomeServiceInterface hsi;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("contents", contents);
		logger.info(paramMap.toString());
		
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(JSONSerializer.toJSON(paramMap));
		String resultString = json.toString();
		logger.info(resultString);
		
		try {
			res.setHeader("charset", "utf-8");
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/json; charset=utf-8");
			PrintWriter printWriter = res.getWriter();
			printWriter.println(resultString);
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest req, @RequestParam HashMap<String, Object> paramMap, HttpServletResponse res) {
		HashMap<String, Object> resultMap = hsi.fileupload(req, paramMap);
		try {
			/********************************************************************************************************************************************************************* 
			 * CKEDITOR Response 규칙
			 * <script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('콜백의 식별 ID 값', '파일의 URL', '전송완료 메시지')</script>
			 * 보낼 내용 : 몇건, 이미지주소, 결과메세지 반환
	         *********************************************************************************************************************************************************************/
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html;charset=utf-8");
			PrintWriter printWriter = res.getWriter();
			
			String CKEditorFuncNum = resultMap.get("CKEditorFuncNum").toString();
			String ContextPath = resultMap.get("ContextPath").toString();  //req.getContextPath();
			String Path = resultMap.get("Path").toString();
			String Message = resultMap.get("Message").toString();
			
			String resultString = "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(";
					   resultString += CKEditorFuncNum + ", "; 
					   resultString += " '" + ContextPath +"/"+ Path + "', ";
					   resultString += " '" + Message + "' ";
					   resultString += ")</script>";
			printWriter.println(resultString);
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
