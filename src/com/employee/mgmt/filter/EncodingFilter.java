package com.employee.mgmt.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
 
import com.employee.mgmt.conn.ConnectionUtils;
import com.employee.mgmt.utils.MyUtils;
 
@WebFilter(filterName = "encodingFilter", urlPatterns = { "/*" })
public class EncodingFilter implements Filter {
 
  public EncodingFilter() {
  }
 
  @Override
  public void init(FilterConfig fConfig) throws ServletException {
 
  }
 
  @Override
  public void destroy() {
 
  }
 
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
      request.setCharacterEncoding("UTF-8");
 
      chain.doFilter(request, response);
  }
 
}