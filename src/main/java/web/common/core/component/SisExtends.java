package web.common.core.component;

import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import web.common.core.sql.SisDomParsing;
import web.common.core.util.SisEncUtil;
import web.common.core.util.SisStringUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Configuration
public class SisExtends {
    @PersistenceContext
    protected EntityManager entityManager;

    protected SisDomParsing xmlParsingQuery = SisDomParsing.getInstance();

    protected SisEncUtil vfEnc = SisEncUtil.getInstance();

    protected HashMap<String, String> columnMap = new HashMap<>();

    //    protected HttpSession session;
    //    
    //    protected HttpServletRequest request;

    public SisExtends() {
    }

    @Synchronized
    protected static String codeCreator(HttpServletRequest request) {
        String nativeCode = UUID.randomUUID().toString();
        String replaceColde = nativeCode.replace("-", "");

        log.info("[TEST] codeCreator_getRemoteAddr : " + request.getRemoteAddr());
        //        log.info("[TEST] codeCreator_getRemoteHost : " + request.getRemoteHost());
        log.info("[TEST] codeCreator_getRemotePort : " + request.getRemotePort());
        log.info("[TEST] codeCreator_nativeCode : " + nativeCode);
        log.info("[TEST] codeCreator_replaceColde : " + replaceColde);
        log.info("[TEST] codeCreator_getYmd : " + SisStringUtil.getYmd());

        return replaceColde;
    }

    // Domain 객체를 조회할 때 사용함
    //    protected List<Object[]> nativeQueryExec(String query, Class classObject)
    //    {
    //        Query nativeQuery = entityManager.createNativeQuery(query, classObject);
    //        return nativeQuery.getResultList();
    //    }

    // FIXME
    // null 이 발생함, 내부 로직을 확인할 필요가 있음
    // 우선적으로 JPA를 적극 활용할 것
    // 특정 컬럼을 조회할 때 사용
    //    protected List<Object[]> nativeQueryExec(String query)
    //    {
    //        Query nativeQuery = entityManager.createNativeQuery(query);
    //        return nativeQuery.getResultList();
    //    }

    // FIXME
    // 위의 경우와 병행
    //    protected Map<String, String> queryResultSet(String query, Class xmlClass, Class targetClass)
    //    protected HashMap<String, String> queryResultSet(String query, Class targetClass)
    //    {
    //        //        if("".equals(targetClass))
    //        
    //        String xmlQuery = xmlParsingQuery.getElement(SisQueryFactory.class, query, null);
    //        
    //        List<Object[]> a = nativeQueryExec(xmlQuery, targetClass);
    //        //        List<Object[]> a = nativeQueryExec(xmlQuery);
    //        
    //        columnMap = new HashMap<>();
    //        
    //        try
    //        {
    //            for (Object o : a)
    //            {
    //                for (Field field : o.getClass().getDeclaredFields())
    //                {
    //                    field.setAccessible(true);
    //                    Object value = field.get(o);
    //                    log.info("field : " + field.getName() + " | value : " + value.toString());
    //                    columnMap.put(field.getName(), value.toString());
    //                }
    //            }
    //        }
    //        catch (Exception e)
    //        {
    //            // TODO: handle exception
    //        }
    //        
    //        return columnMap;
    //    }

    // TODO : 차후 입력받은 문자열에서 키값 추출하도록 변경
    @Synchronized
    protected static String codeCreator(boolean uuidYn) {
        String nativeCode = UUID.randomUUID().toString();
        String replaceColde = nativeCode.replace("-", "");

        String codePrefix = "10";
        log.info("codePrefix : " + codePrefix);
        String codeFirstName = SisStringUtil.getYmdhms().substring(6, 8);
        log.info("codeFirstName : " + codeFirstName);
        String codeMiddleName = "VA";
        log.info("codeMiddleName : " + codeMiddleName);
        String codeLastName = SisStringUtil.getYmdhms().substring(10, 12);
        log.info("codeLastName : " + codeLastName);
        String codeSuffix = SisStringUtil.getYmdhms().substring(8, 10);
        log.info("codeSuffix : " + codeSuffix);
        // 2 0 1 9 1 1 1 9 1 5 5  5  2  2
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13

        if (uuidYn)
            return replaceColde;

        return codePrefix + codeFirstName + codeMiddleName + codeLastName + codeSuffix;
    }

    // 코드 생성 : UUID 사용
    @Synchronized
    protected static String codeCreator(HttpServletRequest request, String caller) {
        if (caller == null || caller == "")
            return codeCreator(request);

        switch (caller) {
            case "user":

                break;

            case "article":
                break;

            default:
                break;
        }
        return "";
    }

    protected HashMap<Integer, String> defaultGrade() {
        HashMap<Integer, String> gradeMap = new HashMap<Integer, String>();

        //        String[] gradeArray = vfConf.getGrade().split("||");
        //
        //        int i = 1;
        //
        //        for (String str : gradeArray)
        //        {
        //            gradeMap.put(i, str);
        //        }

        return gradeMap;
    }
}
