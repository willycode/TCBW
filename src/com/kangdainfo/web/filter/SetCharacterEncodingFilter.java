/**
 * Copyright (c) 2000-2005 Kangda, Inc Road, Taipei, Taiwan, R.O.C. All Rights
 * Reserved.
 */
package com.kangdainfo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The <code>SetCharacterEncodingFilter</code>
 * 
 * @author airy.huang
 * @version 1.0 ,Created at 2004/1/14,上午 10:24:10
 */
public class SetCharacterEncodingFilter implements Filter {

    private static String _encoding;

    protected FilterConfig _filterConfig = null;

    public static String getRequestEncoding() {
        return _encoding;
    }

    public void destroy() {
        _encoding = null;
        _filterConfig = null;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
        _encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if(null != _encoding) {
            request.setCharacterEncoding(_encoding);
            response.setCharacterEncoding(_encoding);
        }
        chain.doFilter(request, response);
    }
}
