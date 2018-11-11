package com.kyleluoma.application.authenticate;

import com.kyleluoma.application.controller.UserController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.kyleluoma.application.model.User;
import org.apache.commons.codec.binary.Base64;

public class UserAuthentication {

    public static boolean authenticateUser(String userName, String plainTextPwd) {
        UserController userController = new UserController();
        User user = userController.getUserByUserName(userName);
        if(hashPassword(plainTextPwd).equals(user.getHashedPassword())) {
            return true;
        }
        return false;
    }

    public static String hashPassword(String plainTextPwd) {

        String hashedPassword = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(plainTextPwd.getBytes(StandardCharsets.UTF_8));
            hashedPassword = Base64.encodeBase64String(hashBytes);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
