package com.netcracker.servlets;

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
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
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
