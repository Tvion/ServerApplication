package com.netcracker.servlets;

import com.netcracker.model.CookieWorker;
import com.netcracker.model.UserOperations;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        this.doGet(request, response);
    }


    //Если выбрана опция запомнить пользователя, то сохраняются введенные им данные и для последующего входа в
    //течение сессии достаточно ввести его имя, а поле пароль оставить пустым
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String appPath = request.getServletContext().getRealPath("");
        String isSavedUser = request.getParameter("isSavedUser");
        Cookie[] cookies = request.getCookies();


        modelInit(UserOperations.userAttempts, login, cookies);

        if ("".equals(login) && "".equals(password)) {
            if (CookieWorker.getCurrentUser() != null)
                response.sendRedirect("/welcome");
            return;
        }
        if (!"".equals(login) && "".equals(password) && CookieWorker.findInUserList(login)) {
            CookieWorker.setCurrentUser(login);
            response.addCookie(new Cookie("userName", login));
            response.sendRedirect("/welcome");
            return;
        }
        UserOperations userOperations = new UserOperations(login, password, appPath);
        sendResponse(response, isSavedUser, login, userOperations, UserOperations.userAttempts);
    }


    private void sendResponse(HttpServletResponse response, String isSavedUser, String login, UserOperations userOperations, HashMap<String, Integer> userAttempts) throws IOException {

        int countOfAttempts = 3; //Колличество попыток, после которых будет редирект на страницу смены пароля

        if (userOperations.hasUser()) {
            if (userOperations.validPassword()) {
                loginSuccess(response, login, isSavedUser);
            } else {
                userAttempts.put(login, userAttempts.get(login) + 1);
                if (userAttempts.get(login) >= countOfAttempts) {
                    Cookie cookie = new Cookie("blockedUser", login);
                    response.addCookie(cookie);
                    response.sendRedirect("changePassword.html");
                    return;
                }
                response.sendRedirect("fail.html");
            }
        } else {
            response.sendRedirect("registration.html");
        }
    }

    private void loginSuccess(HttpServletResponse response, String login, String isSavedUser) throws IOException {
        if (isSavedUser != null) {
            CookieWorker.addUser(login);
            CookieWorker.setCurrentUser(login);
            Cookie loginCookie = new Cookie("userNames", CookieWorker.userListToString());
            Cookie currentUser = new Cookie("userName", CookieWorker.getCurrentUser());
            response.addCookie(loginCookie);
            response.addCookie(currentUser);
            response.sendRedirect("/welcome");
        } else {
            Cookie loginCookie = new Cookie("userName", "");
            response.addCookie(loginCookie);
            response.sendRedirect("/welcome?userName=" + login);
        }
    }

    private void modelInit(HashMap<String, Integer> userAttempts, String login, Cookie[] cookies) {

        if (!userAttempts.containsKey(login)) {
            userAttempts.put(login, 0);
        }

        String userNames = CookieWorker.getValueByName("userNames", cookies);
        CookieWorker.setUserList(userNames);

    }
}
