package web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

// TODO: 차후 수정
@Slf4j
public class SisAccessLogFilter implements Filter
{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        
        String remoteAddr = StringUtils.defaultString(request.getRemoteAddr(), "-");
        String url = (req.getRequestURL() == null) ? "" : req.getRequestURL().toString();
        String queryString = StringUtils.defaultIfEmpty(req.getQueryString(), "");
        String refer = StringUtils.defaultString(req.getHeader("Refer"), "-");
        String agent = StringUtils.defaultString(req.getHeader("User-Agent"), "-");
        String fullUrl = url;
        fullUrl += StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString;
        
        StringBuffer result = new StringBuffer();
        result.append(":").append(remoteAddr).append(":").append(fullUrl).append(":").append(refer).append(":").append(agent);
        log.info("LOG FILTER" + result.toString());
        long startDate = System.currentTimeMillis();
        chain.doFilter(req, response);
        long endDate = System.currentTimeMillis();
        String uri = req.getRequestURI();
        if (uri.indexOf("swagger") == -1)
        {
            log.info("========================================\trestAPI Access Time:" + (double) (endDate - startDate) / 1000 + "초\t=====================================================");
        }
    }
    
    @Override
    public void destroy()
    {
    }
    
}
