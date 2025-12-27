package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/hello-servlet")
public class SimpleHelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Hello from Simple Servlet");
    }

    @Override
    public String getServletInfo() {
        return "SimpleHelloServlet";
    }
}
