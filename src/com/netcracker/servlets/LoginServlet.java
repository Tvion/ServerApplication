package com.netcracker.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        StringBuilder sb = new StringBuilder();
        Pattern name = Pattern.compile("^[a-zA-Z]{3,25}$");
        Matcher matcher = name.matcher(login);
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>" + "Log in" + "</title>");
        sb.append("<link rel=\"stylesheet\" href=\"visual.css\"/>");
        sb.append("</head>");
        sb.append("<body>");
        if (!matcher.matches()) {
            sb.append("Name must contains 3-15 latin characters" + "<br/>");
        } else if (!requiredPassword.equals(password)) {
            sb.append("Password must be \"" + requiredPassword + "\"");
        } else {
            sb.append("<center>We are glad to see you, <b>" + login + "</b></center><br/>");
            sb.append("<center>We are glad to see your password: <b>" + password + "</b></center><br/>");
            sb.append("<center><a href=\"/email.html\"><h1>Sent Email</h1></a></center>");
            sb.append("<center><a href=\"/info\"><h1>Get info</h1></a></center>");
        }
        sb.append("</body>");
        sb.append("</html>");
        pw.println(sb);
    }
}
