package com.netcracker.servlets;

import com.netcracker.model.ResponseTextBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String requiredPassword = "servlet";

        if (login == null || password == null) {
            return;
        }

        PrintWriter pw = response.getWriter();
        String responseString = ResponseTextBuilder.loginServletResponse(login, password, requiredPassword);
        pw.println(responseString);
    }
}
