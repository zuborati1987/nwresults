package com.codecool.servlet;

import com.codecool.database.Q1Dao;
import com.codecool.model.Q1pojo;
import com.codecool.service.Q1service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("query1")
public class Q1servlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q1Dao q1Dao = new Q1Dao(connection);
            Q1service q1service = new Q1service(q1Dao);
            List<Q1pojo> results = q1service.getPojos();

            req.setAttribute("results", results);
            req.getRequestDispatcher("q1.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q1Dao q1Dao = new Q1Dao(connection);
            Q1service q1service = new Q1service(q1Dao);

            String name = req.getParameter("name");
            List<Q1pojo> results = q1service.getPojosByName(name);

            req.setAttribute("results", results);
            req.getRequestDispatcher("q1.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
