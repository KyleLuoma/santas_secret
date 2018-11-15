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

@Controller
@RequestMapping(path="/authenticate")
public class UserAuthenticationController {
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping(path="/login_attempt")
    public @ResponseBody String loginAttempt(@RequestParam String userName, @RequestParam String password) {
        HttpSession httpSession = fetchSession();
        User user = userRepository.findByUserName(userName);
        if(user == null) { return "Username " + userName + " is not valid.") }; 
        if(user.getHashedPassword().equals(UserAuthentication.hashPassword(password))) {
            httpSession.setAttribute("userId", user.getId());
        }
        return "Username " + userName + " is logged in with user id " + user.getId();
    }
    
    private HttpSession fetchSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest().getSession(false);
    }
}
