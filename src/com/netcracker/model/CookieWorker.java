package com.netcracker.model;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CookieWorker {
    private static List<String> userNames = new ArrayList<>();
    private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        CookieWorker.currentUser = currentUser;
    }

    public static String getValueByName(String name, Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void setUserList(String namesInCookie) {
        userNames.clear();
        if (namesInCookie == null) {
            return;
        }
        String[] names = namesInCookie.split("-");
        Collections.addAll(userNames, names);
    }

    public static boolean findInUserList(String user) {
        for (String userInList : userNames) {
            if (userInList.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public static String userListToString() {
        StringBuilder sb = new StringBuilder("");
        if (userNames.size() == 0) {
            return "";
        }
        String lastUser = userNames.get(userNames.size() - 1);
        for (String user : userNames) {
            sb.append(user);
            if (!user.equals(lastUser))
                sb.append("-");
        }
        return sb.toString();
    }

    public static List<String> getUserList() {
        return userNames;
    }

    public static void addUser(String user) {
        userNames.add(user);
    }
}
