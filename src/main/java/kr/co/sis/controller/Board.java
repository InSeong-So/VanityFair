package kr.co.sis.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sis.dao.DaoInterface;
import kr.co.sis.edit.HomeController;
import kr.co.sis.service.BoardServiceInterface;
import kr.co.sis.util.HttpUtil;

@Controller
public class Board {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	HashMap<String, Object> param;
	
	@Autowired
	DaoInterface di;

	@Autowired
	BoardServiceInterface bsi;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String home() {
		logger.info("board()");
		return "board";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file() {
		logger.info("file()");
		return "file";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, RedirectAttributes attr, HttpSession session) {
		param = HttpUtil.getParamMap(req);
		
		HashMap<String, Object> result = new HashMap<String,Object>();
		
		HashMap<String, Object> paramMap = (HashMap<String, Object>) session.getAttribute("user");
		String userNo = paramMap.get("userNo").toString();
		String boardTitle = req.getParameter("boardTitle");
		String boardContents = req.getParameter("boardContents");
		String boardClass = req.getParameter("boardClass");
		
		param.put("userNo", userNo);
		param.put("boardTitle", boardTitle);
		param.put("boardContents", boardContents);
		param.put("boardClass", boardClass);
		
		param.put("sql", "insert");
		param.put("sqlType", "board.boardInsert");
		
		result.put("result", di.call(param));
		result.put("boardTitle", boardTitle);
		result.put("boardContents", boardContents);
		
		logger.info(param.toString());
		
//		JSONObject json = new JSONObject();
//		json = JSONObject.fromObject(JSONSerializer.toJSON(result));
//		String resultString = json.toString();
//		logger.info(resultString);
//		
//		try {
//			res.setHeader("charset", "utf-8");
//			res.setCharacterEncoding("utf-8");
//			res.setContentType("text/json; charset=utf-8");
//			PrintWriter printWriter = res.getWriter();
//			printWriter.println(result);
//			printWriter.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return HttpUtil.makeJsonView(result);
	}
	
    @RequestMapping("/boardSelect")
    public String boardSelect() {
        return "board/detail";
    }	
	
    @RequestMapping("/boardDetail")
    public ModelAndView boardDetail(HttpServletRequest req) {
    	String boardNo = req.getParameter("boardNo");
    	String userNo = req.getParameter("userNo");
    	    	
    	System.out.println("boardNo test 중 : " + boardNo);
    	System.out.println("userNo test 중 : " + userNo);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("boardNo", boardNo);
    	param.put("userNo", userNo);
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	param.put("sqlType","board.boardOne");
    	param.put("sql","selectOne");
    	resultMap.put("boardData", di.call(param));
    	
    	return HttpUtil.makeJsonView(resultMap);
    }
	
    @RequestMapping("/boardCheck")
    public String boardCheck(HttpServletRequest req,Model model,HttpSession session) {
    	String boardNo = req.getParameter("boardNo");
    	String userNo = req.getParameter("userNo");
    	
    	System.out.println("boardNo Check : " + boardNo);
    	System.out.println("userNo Check : " + userNo);
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("boardNo", boardNo);
    	param.put("userNo", userNo);
    	param.put("sqlType", "board.boardUserSelect");
    	param.put("sql", "selectOne");
    	param.putAll((HashMap<String, Object>) session.getAttribute("user"));
    	
    	HashMap<String, Object> params = (HashMap<String, Object>) di.call(param);
    	System.out.println(params);
    	if(params == null) {
    		model.addAttribute("status", 0);
    	}
    	else {
    		model.addAttribute("status", 1);
    		model.addAttribute("data", params);
    	}
    	return "board/update";
     }
    
    @RequestMapping("/boardUpdate")
    public ModelAndView boardUpdate(HttpServletRequest req) {
    	String boardNo = req.getParameter("boardNo");
    	String boardTitle = req.getParameter("boardTitle");
    	String boardContents = req.getParameter("boardContents");
    	
    	System.out.println("boardNo Update test 중 : " + boardNo);
    	System.out.println("boardTitle Update test 중 : " + boardTitle);
    	System.out.println("boardContents Update test 중 : " + boardContents);
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("boardNo", boardNo);
    	param.put("boardTitle", boardTitle);
    	param.put("boardContents", boardContents);

    	param.put("sqlType","board.boardUpdate");
    	param.put("sql","update");
    	param.put("status",(int) di.call(param));
		return HttpUtil.makeJsonView(param);
    }
    
    @RequestMapping("/boardDelete")
    public String boardDelete(HttpServletRequest req, Model model, HttpSession session) {
       	String boardNo = req.getParameter("boardNo");
    	String userNo = req.getParameter("userNo");
    	
    	System.out.println("boardNo Check : " + boardNo);
    	System.out.println("userNo Check : " + userNo);
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("boardNo", boardNo);
    	param.put("userNo", userNo);
    	param.put("sqlType", "board.boardUserSelect");
    	param.put("sql", "selectOne");
    	param.putAll((HashMap<String, Object>) session.getAttribute("user"));
    	
    	HashMap<String, Object> params = (HashMap<String, Object>) di.call(param);
    	if(params == null) {
    		model.addAttribute("status", 0);
    		return "board/update";
    	}
    	else {
    		model.addAttribute("status", 1);
    		model.addAttribute("data", params);
    		return "redirect:/resources/main.html";
    	}	
    }
    
    @RequestMapping("/boardList")
    public ModelAndView boardList() {
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("sqlType", "board.boardList");
    	param.put("sql", "selectList");
    	List list = (List) di.call(param);
//    	List list = session.selectList("board.boardList");
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("list", list);
    	return HttpUtil.makeJsonView(resultMap);
    }
	

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest req, @RequestParam HashMap<String, Object> paramMap, HttpServletResponse res) {
		HashMap<String, Object> resultMap = bsi.fileupload(req, paramMap);
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
