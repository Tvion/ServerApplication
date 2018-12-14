package com.netcracker.servlets;

import com.netcracker.model.UserOperations;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String appPath = request.getServletContext().getRealPath("");

        PrintWriter pw = response.getWriter();

        UserOperations userOperations = new UserOperations(login, password, appPath);
        String createUserState = userOperations.createUser();
        pw.println(createUserState);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
