package com.netcracker.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "InfoServlet", urlPatterns = "/info")
public class InfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String BrowserName = request.getHeader("User-Agent");
        Date date = new Date();
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head> <link rel=\"stylesheet\" href=\"visual.css\"/><title>Information</title></head>");
        sb.append("<body>");
        sb.append("<div class=\"browser-info\">");
        sb.append("<b>Today</b> is " + date + "<br/>");
        sb.append("<b>Browser Info:</b> <br/>" + BrowserName + "<br/>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        pw.println(sb);
    }
}
