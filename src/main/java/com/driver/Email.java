package com.driver;

import java.util.HashMap;
import java.util.Map;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        if (oldPassword == this.password) {
            // 1. It contains at least 8 characters
            if (newPassword.length() >= 8 && isUppercase(newPassword) && isLowercase(newPassword) && hasSpecial(newPassword) && hasDigit(newPassword)) {
                this.password = newPassword;
            }
            // 2. It contains at least one uppercase letter
            // 3. It contains at least one lowercase letter
            // 4. It contains at least one digit
            // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        }

    }

    Map<Character,Integer>digitMap=new HashMap<>();

    private boolean isUppercase(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            digitMap.put(newPassword.charAt(i), digitMap.getOrDefault(newPassword.charAt(i),0)+1);
        }
        for (Character c:digitMap.keySet()) {
            if ((int)c >= 65 && (int)c <= 90) {
                return true;
            }
        }
        return false;
    }

    private boolean isLowercase(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            digitMap.put(newPassword.charAt(i), digitMap.getOrDefault(newPassword.charAt(i),0)+1);
        }
        for (Character c:digitMap.keySet()) {
            if ((int)c >= 97 && (int)c <= 122) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSpecial(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            digitMap.put(newPassword.charAt(i), digitMap.getOrDefault(newPassword.charAt(i),0)+1);
        }
        for(Character c : digitMap.keySet()){
            if(((int)c >= 32 && (int)c <= 47)||((int)c >= 58 && (int)c <= 64)||((int)c >= 91 && (int)c <= 96)||((int)c >= 123 && (int)c <= 126)){
                return true;
            }
        }
        return false;
    }

    private boolean hasDigit(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            digitMap.put(newPassword.charAt(i), digitMap.getOrDefault(newPassword.charAt(i),0)+1);
        }
        for (Character c:digitMap.keySet()) {
            if ((int)c >= 48 && (int)c <= 57) {
                return true;
            }
        }
        return false;
    }
}
