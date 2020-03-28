//package common.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import biz.user.EHRBean;
//import biz.user.EHRLogin;
//import hcg.hunel.core.action.hunelBizException;
//import hcg.hunel.core.sql.SQLErrMessage;
//import hcg.hunel.core.util.CommonProperties;
//import web.common.core.util.SisStringUtil;
//
//public class FroalaImage
//{
//    private static final long serialVersionUID = 1L;
//    
//    // 이미지 최대 크기
//    private final int maxImageSize = 10 * 1024 * 1024;// 10MB
//    // 업로드 가능한 이미지 파일 유형
//    
//    private final String[] arrValidExtend = new String[] { "jpg", "jpeg", "gif", "png" };
//    
//    private final String[] arrValidMimeType = new String[] { "image/jpeg", "image/gif", "image/png" };
//    
//    /**
//     * 이미지 업로드
//     * 
//     * @param HttpServletRequest
//     *            request
//     * @param HttpServletResponse
//     *            response
//     * @return
//     * @throws SQLException
//     * @throws JSONException
//     * @throws hunelBizException
//     * @throws IOException
//     */
//    void doAction(HttpServletRequest request, HttpServletResponse response) throws JSONException, SQLException, IOException
//    {
//        if (!ServletFileUpload.isMultipartContent(request))
//        {
//            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
//        }
//        
//        PrintWriter writer = null;
//        JSONObject respJSON = new JSONObject();
//        
//        try
//        {
//            response.setContentType("application/json; charset=utf-8");
//            response.setCharacterEncoding("utf-8");
//            writer = response.getWriter();
//            
//            // 에디터 이미지 저장경로
//            File saveDirectoryFile = new File(commProp.getProperty("DIR_BASE") + "WebRoot/resource/images/editor/");
//            if (!saveDirectoryFile.exists())
//                saveDirectoryFile.mkdirs();
//            String saveDirectory = saveDirectoryFile.getAbsolutePath();
//            
//            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//            
//            for (FileItem item : items)
//            {
//                if (item.isFormField())
//                {
//                    // 파일 이외의 파라미터를 받아 처리해야 할 경우
//                }
//                else
//                {
//                    String fullFileName = item.getName();
//                    String extension = SisStringUtil.getExtString(fullFileName);
//                    String fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));
//                    
//                    if (fullFileName == null || fullFileName.equals(""))
//                    {
//                        respJSON.put("status", "fail");
//                        respJSON.put("message", "image is null");
//                        continue;
//                    }
//                    
//                    // 파일크기 제한
//                    if (item.getSize() > maxImageSize)
//                    {
//                        StringBuffer msg = new StringBuffer();
//                        msg.append(SQLErrMessage.getMessage(ehrbean.getLocale(), "MSG_ALERT_0339")).append(" (max:").append(Integer.toString(maxImageSize / (1024 * 1024))).append("MB)");
//                        log.debug(msg.toString());
//                        respJSON.put("status", "fail");
//                        respJSON.put("message", msg.toString());
//                        log.error(msg);
//                        continue;
//                    }
//                    
//                    // 파일형식 제한 - 확장자, MIME Type
//                    if (!(Arrays.asList(arrValidExtend).contains(extension) && Arrays.asList(arrValidMimeType).contains(item.getContentType())))
//                    {
//                        String msg = SQLErrMessage.getMessage(ehrbean.getLocale(), "MSG_0119");
//                        respJSON.put("status", "fail");
//                        respJSON.put("message", msg);
//                        log.error(msg);
//                        continue;
//                    }
//                    
//                    File uploadFile = new File(saveDirectory, fullFileName);
//                    int fileSuffix = 0;
//                    while (uploadFile.exists())
//                    {
//                        fullFileName = fileName + Integer.toString(fileSuffix) + "." + extension;
//                        uploadFile = new File(saveDirectory, fullFileName);
//                        fileSuffix++;
//                    }
//                    item.write(uploadFile);
//                    // link : 이미지태그의 src값으로 지정. 절대경로 set 
//                    respJSON.put("link", "/resource/images/editor/" + fullFileName);
//                }
//            }
//        }
//        catch (hunelBizException e)
//        {
//            throw new hunelBizException(e.getMessage());
//        }
//        catch (FileUploadException e)
//        {
//            throw new hunelBizException(e.getMessage());
//        }
//        catch (NullPointerException e)
//        {
//            throw new hunelBizException(e.getMessage());
//        }
//        catch (Exception e)
//        {
//            throw new hunelBizException(e.getMessage());
//        }
//        finally
//        {
//            //log.error("response >> " + respJSON.toString());
//        }
//        
//    }
//    
//}