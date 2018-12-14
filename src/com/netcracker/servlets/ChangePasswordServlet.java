package com.netcracker.servlets;

import com.netcracker.model.CookieWorker;
import com.netcracker.model.UserOperations;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = "/changePass")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        PrintWriter pw = response.getWriter();
        Cookie[] cookies = request.getCookies();
        String name = CookieWorker.getValueByName("blockedUser", cookies);
        UserOperations userOperations = new UserOperations(name, password, request.getServletContext().getRealPath(""));
        if (!"".equals(name)) {
            userOperations.removeUser(name);
            userOperations.createUser();
            UserOperations.userAttempts.remove(name);
            String responseString = "Password Changed";
            pw.println(responseString);
            return;
        }
        String responseString = "Password Changing error";
        pw.println(responseString);
    }
}
