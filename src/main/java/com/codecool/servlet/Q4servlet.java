package com.codecool.servlet;

import com.codecool.database.Q4Dao;
import com.codecool.model.Q4pojo;
import com.codecool.service.Q4service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/query4")
public class Q4servlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q4Dao q4Dao = new Q4Dao(connection);
            Q4service q4service = new Q4service(q4Dao);
            List<Q4pojo> results = q4service.getPojos();

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q4.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q4Dao q4Dao = new Q4Dao(connection);
            Q4service q4service = new Q4service(q4Dao);

            String name = req.getParameter("name");
            List<Q4pojo> results = q4service.getPojosByName(name);

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q4.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
