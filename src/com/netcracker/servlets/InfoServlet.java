package com.netcracker.servlets;

import com.netcracker.model.ResponseTextBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "InfoServlet", urlPatterns = "/info")
public class InfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String browserName = request.getHeader("User-Agent");
        Date date = new Date();
        PrintWriter pw = response.getWriter();
        String responseString = ResponseTextBuilder.infoServletResponse(date, browserName);

        pw.println(responseString);
    }
}
