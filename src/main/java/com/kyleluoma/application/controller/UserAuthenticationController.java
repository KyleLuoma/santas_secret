package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import com.kyleluoma.application.repository.UserRepository;
import com.kyleluoma.application.authenticate.UserAuthentication;
import com.kyleluoma.application.model.User;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path="/authenticate")
public class UserAuthenticationController {
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(path="/login_attempt")
    public @ResponseBody String loginAttempt(@RequestParam String userName,
                                             @RequestParam String password,
                                             ServletRequest request,
                                             ServletResponse response) {
        HttpSession httpSession = fetchSession();
        User user = userRepository.findByUserName(userName);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(user == null) {
            return "Username " + userName + " is not valid.";
        }

        if(user.getHashedPassword().equals(UserAuthentication.hashPassword(password))) {
            httpSession.setAttribute("userId", user.getId());
            System.out.println(user.getUserName() + " is logged in.");
            try{
                httpResponse.sendRedirect("/user_dashboard.html");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }


            return "forward:/user_dashboard.html";
        }

        fetchSession().setAttribute("userId", UserAuthentication.INVALID_USER_ID);
        System.out.println("Invalid login or password!");
        return "Invalid login or password.";
    }
    
    private HttpSession fetchSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        System.out.println("FetchSession fetched session ID " + attributes.getRequest().getSession(false).getId());
        return attributes.getRequest().getSession(false);
    }
}
