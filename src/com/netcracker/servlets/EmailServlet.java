package com.netcracker.servlets;

import com.netcracker.model.ResponseTextBuilder;
import com.netcracker.model.Sender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EmailServlet", urlPatterns = "/email")
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = "stestfami@gmail.com";
        String password = "easypass";
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");
        String toEmail = request.getParameter("email");

        Sender sender = new Sender(username, password);
        sender.send(subject, text, toEmail);
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String responseString = ResponseTextBuilder.emailServletResponse();

        out.println(responseString);
    }
}
