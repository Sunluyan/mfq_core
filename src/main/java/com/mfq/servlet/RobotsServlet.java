package com.mfq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.utils.Config;


public class RobotsServlet extends HttpServlet {

	private static final long serialVersionUID = 411634834058653752L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/plain;charset=utf-8");
        if (!Config.isProduct()) {
            resp.getWriter().println("User-agent: *");
            resp.getWriter().println("Disallow: /");
        }
    }
}
