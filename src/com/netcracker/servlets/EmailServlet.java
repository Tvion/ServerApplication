package com.netcracker.servlets;

import com.netcracker.model.Sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

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

        Sender sender=new Sender(username,password);
        sender.send(subject,text,toEmail);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Email Send";
        String res = "Sent message successfully";
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>\"" + title + "\"</title>");
        sb.append("<link rel=\"stylesheet\" href=\"visual.css\"/>");
        sb.append("</head>");
        sb.append("<body><center><h1>" + title + "</h1></center>" + "<center><p>" + res + "</p></center></div></body>");
        sb.append("</html>");
        out.println(sb);
    }
}
