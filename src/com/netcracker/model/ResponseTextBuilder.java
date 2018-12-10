package com.netcracker.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseTextBuilder {
    public static String emailServletResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Email Send</title>");
        sb.append("<link rel=\"stylesheet\" href=\"visual.css\"/>");
        sb.append("</head>");
        sb.append("<body><center><h1>Email Send</h1></center>" + "<center><p>Sent message successfully</p></center></div></body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static String infoServletResponse(Date date, String BrowserName) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head> <link rel=\"stylesheet\" href=\"visual.css\"/><title>Information</title></head>");
        sb.append("<body>");
        sb.append("<div class=\"browser-info\">");
        sb.append("<b>Today</b> is ");
        sb.append(date);
        sb.append("<br/>");
        sb.append("<b>Browser Info:</b> <br/>");
        sb.append(BrowserName);
        sb.append("<br/>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static String loginServletResponse(String login, String password, String requiredPassword) {
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
            sb.append("Password must be \"");
            sb.append(requiredPassword);
            sb.append("\"");
        } else {
            sb.append("<center>We are glad to see you, <b>");
            sb.append(login);
            sb.append("</b></center><br/>");
            sb.append("<center>We are glad to see your password: <b>");
            sb.append(password);
            sb.append("</b></center><br/>");
            sb.append("<center><a href=\"/email.html\"><h1>Sent Email</h1></a></center>");
            sb.append("<center><a href=\"/info\"><h1>Get info</h1></a></center>");
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
