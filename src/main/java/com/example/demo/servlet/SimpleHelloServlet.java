package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

public class SimpleHelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.reset(); 
        response.setContentType("text/plain");
        response.getWriter().write("Hello World");
        response.getWriter().flush(); 
    }

    @Override
    public String getServletInfo() {
        return "SimpleHelloServlet";
    }
}
