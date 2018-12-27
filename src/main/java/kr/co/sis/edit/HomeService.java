package kr.co.sis.edit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//@Service
public class HomeService implements HomeServiceInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

	@Override
	public HashMap<String, Object> fileupload(MultipartHttpServletRequest req, HashMap<String, Object> paramMap) {
		logger.info("fileupload()");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("CKEditorFuncNum", paramMap.get("CKEditorFuncNum").toString());
		resultMap.put("ContextPath", req.getContextPath());
		
		// 변수 선언
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		
		// 파일이름들 담기
		Iterator<String> iterator = req.getFileNames();
		// 파일 갯수만큼 반복문 실행
		while(iterator.hasNext()) {
			String paramName = iterator.next();
			// 업로드된 파일 정보를 배열에 담기
			List<MultipartFile> multipartFiles = req.getFiles(paramName);
			for(MultipartFile multipartFile : multipartFiles) {
				try {
					byte[] bytes = multipartFile.getBytes();
					String fileFullName = multipartFile.getOriginalFilename();
					String fileName = sha256(fileFullName.substring(0, fileFullName.lastIndexOf(".")));
					String fileExtension = fileFullName.substring(fileFullName.lastIndexOf("."));
					String Path = "";
					           Path += "D:/GDJ10/IDE/workspace/Editor/src/main/webapp/";
//					           Path += req.getSession().getServletContext().getRealPath("/");
					String Path2 = "resources/upload/" + today + "/";
					File dirF = new File(Path + Path2);
													
					if(!dirF.exists()) {
						dirF.mkdirs();
					}
					
					File f = new File(Path + Path2 + fileName + fileExtension);
					OutputStream out = new FileOutputStream(f);
					out.write(bytes);
					out.close();
					
					resultMap.put("Path", Path2 + fileName + fileExtension);
					resultMap.put("Message", "정상 업로드 되었습니다.");
				} catch (Exception e) {
					e.printStackTrace();
					resultMap.put("Path", "");
					resultMap.put("Message", "업로드 도중 오류가 발생하였습니다.");
				}
			}
		}
		return resultMap;
	}
	
	// 암호화(중복방지)
	public String sha256(String str) {
 		String SHA = "";
 		try {
 			MessageDigest sh = MessageDigest.getInstance("SHA-256");
 			sh.update(str.getBytes());
 			byte byteData[] = sh.digest();
 			StringBuffer sb = new StringBuffer();
 			for (int i = 0; i < byteData.length; i++) {
 				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
 			}
 			SHA = sb.toString();
 		} catch (NoSuchAlgorithmException e) {
 			e.printStackTrace();
 			SHA = null;
 		}
 		return SHA.substring(30);
 	}

}
