package web.common.core.util;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import web.common.core.exception.SisRuntimeException;

public class SisStringUtil
{
    // 난수를 이용한 이메일 인증용 키 생성 1
    private static boolean lowerCheck;
    
    // 난수를 이용한 이메일 인증용 키 생성 2
    private static int size;
    
    public final static int _SEC = 60;
    
    public final static int _MIN = 60;
    
    public final static int _HOUR = 24;
    
    public final static int _DAY = 7;
    
    public final static int _MONTH = 12;
    
    static DecimalFormat sm_DF = new DecimalFormat("###,###,###.##########");
    
    static SimpleDateFormat sm_SDF_YMD = new SimpleDateFormat("yyyyMMdd");
    
    static SimpleDateFormat sm_SDF_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
    
    static SimpleDateFormat sm_SQL_SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    
    public static String formatComma(double num)
    {
        return sm_DF.format(num);
    }
    
    public static String formatComma(double num, String format)
    {
        return new DecimalFormat(format).format(num);
    }
    
    public static String formatCommaScale(double num, int scale)
    {
        return new DecimalFormat("###,###,###." + SisStringUtil.repeat("0", scale)).format(num);
    }
    
    public static String formatDate(String format, Calendar cal)
    {
        return new SimpleDateFormat(format).format(cal.getTime());
    }
    
    public static String formatDate(String format)
    {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
    
    public static String getYmdhms()
    {
        return sm_SDF_YMDHMS.format(Calendar.getInstance().getTime());
    }
    
    public static String getYmdhms(HttpServletRequest request)
    {
        SimpleDateFormat lo_SDF_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            lo_SDF_YMDHMS.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getFormatDate(HttpServletRequest request, String format)
    {
        SimpleDateFormat lo_SDF_YMDHMS = new SimpleDateFormat(format);
        try
        {
            lo_SDF_YMDHMS.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getYmd()
    {
        return sm_SDF_YMD.format(Calendar.getInstance().getTime());
    }
    
    public static String getYmd(SimpleDateFormat format)
    {
        return format.format(Calendar.getInstance().getTime());
    }
    
    public static String getYmd(HttpServletRequest request)
    {
        SimpleDateFormat lo_SDF_YMD = new SimpleDateFormat("yyyyMMdd");
        try
        {
            lo_SDF_YMD.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMD.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMD.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getLastYmdOfMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        return sm_SDF_YMD.format(cal.getTime());
    }
    
    public static Calendar makeCalendar(String yyyyMMddHHmmss)
    {
        int parseInt;
        int i = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.parseInt(yyyyMMddHHmmss.substring(0, 4)));
        cal.set(2, Integer.parseInt(yyyyMMddHHmmss.substring(4, 6)) - 1);
        cal.set(5, Integer.parseInt(yyyyMMddHHmmss.substring(6, 8)));
        if (yyyyMMddHHmmss.length() >= 10)
        {
            parseInt = Integer.parseInt(yyyyMMddHHmmss.substring(8, 10));
        }
        else
        {
            parseInt = 0;
        }
        cal.set(11, parseInt);
        if (yyyyMMddHHmmss.length() >= 12)
        {
            parseInt = Integer.parseInt(yyyyMMddHHmmss.substring(10, 12));
        }
        else
        {
            parseInt = 0;
        }
        cal.set(12, parseInt);
        if (yyyyMMddHHmmss.length() >= 14)
        {
            i = Integer.parseInt(yyyyMMddHHmmss.substring(12, 14));
        }
        cal.set(13, i);
        return cal;
    }
    
    public static String formatCommaString(String numStr, String format)
    {
        try
        {
            numStr = new DecimalFormat(format).format(Double.parseDouble(numStr));
        }
        catch (Exception e)
        {
        }
        return numStr;
    }
    
    public static String formatDateString(String dateStr, String format)
    {
        try
        {
            dateStr = formatDate(format, makeCalendar(dateStr));
        }
        catch (Exception e)
        {
        }
        return dateStr;
    }
    
    public static String mySqlCurrentTimeFormat()
    {
        return formatDateString(getYmdhms(), "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String CreateDataWithCheck(String dataString)
    {
        String msg = null;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        
        try
        {
            date = format.parse(dataString);
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("시간 변환에 실패했습니다. 에러 메세지 : " + e.getMessage());
        }
        
        long curTime = System.currentTimeMillis();
        long regTime = date.getTime();
        long diffTime = (curTime - regTime) / 1000;
        
        if (diffTime < _SEC)
        {
            msg = "방금 전";
        }
        else if ((diffTime /= _SEC) < _MIN)
        {
            msg = diffTime + "분 전";
        }
        else if ((diffTime /= _MIN) < _HOUR)
        {
            msg = (diffTime) + "시간 전";
        }
        else if ((diffTime /= _HOUR) < _DAY)
        {
            msg = (diffTime) + "일 전";
        }
        else
        {
            SimpleDateFormat aformat = new SimpleDateFormat("yyyy-MM-dd");
            msg = aformat.format(date);
        }
        
        return msg;
    }
    
    // oracle NVL 기능 구현 01
    public static String nvl(String str)
    {
        return nvl(str, "");
    }
    
    // oracle NVL 기능 구현 02
    public static String nvl(String str, String defaultStr)
    {
        return (str == null || str.length() == 0) ? defaultStr : str;
    }
    
    // 문자열 배열에 포함된 값이 있는지 확인
    public static boolean contains(String arrStr[], String str)
    {
        for (int n = 0; n < arrStr.length; n++)
            if (str.equals(arrStr[n]))
                return true;
            
        return false;
    }
    
    // 문자열 null 체크 01
    public static boolean isNull(String str)
    {
        return str == null || str.length() == 0;
    }
    
    // 문자열 null 체크 02
    public static String isNull(String source, String value)
    {
        String retVal;
        if (source == null || source.trim().equals("") || source.trim().equals("null"))
            retVal = value;
        else
            retVal = source.trim();
        return retVal;
    }
    
    // 문자열 null 체크 03
    public static int isNull(String source, int value)
    {
        int retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Integer.parseInt(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 04
    public static long isNull(String source, long value)
    {
        long retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Long.parseLong(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 05
    public static float isNull(String source, float value)
    {
        float retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Float.parseFloat(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 06
    public static double isNull(String source, double value)
    {
        double retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Double.parseDouble(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 해당 문자열을 인자값만큼 반복하여 생성
    public static String repeat(String str, int cnt)
    {
        StringBuffer sb = new StringBuffer();
        for (int n = cnt; n > 0; n--)
        {
            sb.append(str);
        }
        return sb.toString();
    }
    
    // Xss 공격에 대비한 uri replace01
    public static String xss(String srcStr)
    {
        return (srcStr == null) ? null : srcStr.replaceAll("[<>\"'\n\r\f]", "");
    }
    
    // Xss 공격에 대비한 uri replace02
    public static String nvlXss(String srcStr, String defaultStr)
    {
        return xss(nvl(srcStr, defaultStr));
    }
    
    // Xss 공격에 대비한 uri replace03
    public static String nvlXss(String srcStr)
    {
        return xss(nvl(srcStr));
    }
    
    public static String slashPlus(String identified)
    {
        
        String url = "/" + identified;
        
        return url;
    }
    
    // 객체 null 체크용 함수
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof String) && (((String) obj).trim().length() == 0))
        {
            return true;
        }
        if (obj instanceof Map)
        {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof List)
        {
            return ((List<?>) obj).isEmpty();
        }
        if (obj instanceof Object[])
        {
            return (((Object[]) obj).length == 0);
        }
        
        return false;
    }
    
    /**
     * 이메일 인증용 키를 만드는 메서드
     * 
     * @return
     */
    private static String init()
    {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        
        do
        {
            num = ran.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122))
            {
                sb.append((char) num);
            }
            else
            {
                continue;
            }
            
        } while (sb.length() < size);
        if (lowerCheck)
        {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }
    
    /**
     * 이메일 인증용 키를 가져오는 메소드
     * 
     * @param lowerCheck
     * @param size
     * @return
     */
    public static String getKey(boolean lowerCheck, int size)
    {
        SisStringUtil.lowerCheck = lowerCheck;
        SisStringUtil.size = size;
        return init();
    }
}
