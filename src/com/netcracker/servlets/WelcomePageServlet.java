package com.netcracker.servlets;

import com.netcracker.model.CookieWorker;
import com.netcracker.model.ResponseTextBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "WelcomePageServlet", urlPatterns = "/welcome")
public class WelcomePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String textValue = request.getParameter("text");
        Cookie[] cookies = request.getCookies();
        String userName = CookieWorker.getValueByName("userName", cookies);
        Cookie textCookie = new Cookie(userName + "_text", textValue);
        response.addCookie(textCookie);
        response.sendRedirect("/welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        String userNameWithoutSession = request.getParameter("userName");
        String responseString;
        Cookie[] cookies = request.getCookies();
        String userName = CookieWorker.getValueByName("userName", cookies);
        String userText = CookieWorker.getValueByName(userName + "_text", cookies);
        if (!"".equals(userName)) {
            responseString = ResponseTextBuilder.welcomeResponseServlet(userName, userText);
        } else {
            responseString = ResponseTextBuilder.welcomeResponseServlet(userNameWithoutSession, "");
        }
        pw.println(responseString);
    }
}
