package com.netcracker.model;

import java.util.Date;

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

    public static String welcomeResponseServlet(String userName, String textValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head> <link rel=\"stylesheet\" href=\"visual.css\"/><title>Information</title></head>");
        sb.append("<body>");
        sb.append("<div class=\"browser-info\">");
        sb.append("<center>");
        sb.append("<b>Hello</b>, ");
        sb.append(userName);
        sb.append("</center>");
        sb.append("<br/>");
        if (CookieWorker.findInUserList(userName)) {
            sb.append("<div class=\"login-form\" style=\"margin: 0 auto\">\n" +
                    "<form action=\"/welcome\" method=\"POST\">");
            sb.append("<center><label for=\"text\">Saved text in session</label></center>");
            sb.append("<br/>");
            sb.append("<center><textarea id=\"text\" name=\"text\" rows=\"2\" cols=\"20\" placeholder=\"Text here...\">");
            if (textValue != null) {
                sb.append(textValue);
            }
            sb.append("</textarea></center>");
            sb.append("<center><input class=\"submit-button\" type=\"submit\" value=\"Save\" /><center>\n" +
                    "</form></div>");
        }
        sb.append("<br/>");
        sb.append("<center><a class=\"\" href=\"/email.html\"><b>Send Email</b></a></center><br/>");
        sb.append("<center><a class=\"\" href=\"/info\"><b>Browser Info</b></a></center><br/>");
        sb.append("<br/>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
