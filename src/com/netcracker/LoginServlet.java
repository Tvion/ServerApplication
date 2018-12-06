package com.netcracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
        sb.append("<body>");
        if (!matcher.matches()) {
            sb.append("Name must contains 3-15 latin characters" + "<br/>");
        } else if (!requiredPassword.equals(password)) {
            sb.append("Password must be \"" + requiredPassword + "\"");
        } else {
            sb.append("We are glad to see you, " + "<b>" + login + "</b>" + "<br/>");
            sb.append("We are glad to see your password: " + "<b>" + password + "</b>" + "<br/>");
        }
        sb.append("</body>");
        sb.append("</html>");
        pw.println(sb);
    }
}
