/**
 * Copyright (c) 2000-2005 Kangda, Inc
 * Road, Taipei, Taiwan, R.O.C. All Rights Reserved.
 */
package com.kangdainfo.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * The <code>ResponseHeaderFilter</code>
 *
 * @author :  airy.huang
 * @version : 1.0 ,Date : 16:23:42  2005/4/21
 */

public class ResponseHeaderFilter implements Filter {
    FilterConfig filerConfig;

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        // set the provided HTTP response parameters
        for (Enumeration e = filerConfig.getInitParameterNames(); e.hasMoreElements();) {
            String headerName = (String) e.nextElement();
            response.addHeader(headerName, filerConfig.getInitParameter(headerName));
        }

        // pass the request/response on
        chain.doFilter(req, response);
    }

    public void init(FilterConfig filterConfig) {
        this.filerConfig = filterConfig;
    }

    public void destroy() {
        this.filerConfig = null;
    }
}
