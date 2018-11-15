package com.kyleluoma.application.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

@Component
@Order(1)
/*@WebFilter(filterName = "UserAuthenticationFilter",
    urlPatterns = {"/*"},
    initParams = {
        @WebInitParam(name = "userAuth", value = "test")
})*/
public class UserAuthenticationFilter implements Filter {
    
    private final Integer INVALID_USER_ID = -1;
    
    public void init(FilterConfig config) throws ServletException {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession(true);
        
        System.out.println("This is the UserAuthenticationFilter saying hello world!");
        System.out.println(httpSession.getId().toString());

        //Check if userId attribute exists, or if it is flagged (-1) as invalid:
        if(httpSession.getAttribute("userId") == null || httpSession.getAttribute("userId") == INVALID_USER_ID) {
            httpSession.setAttribute("userId", -1);
            System.out.println("Redirecting to login page");
            httpResponse.sendRedirect("/login.html");
        }
        chain.doFilter(request, response);
    }
        
    public void destroy() {
    
    }
}
