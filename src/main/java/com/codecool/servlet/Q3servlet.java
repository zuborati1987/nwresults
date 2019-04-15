package com.codecool.servlet;

import com.codecool.database.Q3Dao;
import com.codecool.model.Q3pojo;
import com.codecool.service.Q3service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/query3")
public class Q3servlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q3Dao q3Dao = new Q3Dao(connection);
            Q3service q3service = new Q3service(q3Dao);
            List<Q3pojo> results = q3service.getPojos();

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q3.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q3Dao q3Dao = new Q3Dao(connection);
            Q3service q3service = new Q3service(q3Dao);

            String name = req.getParameter("name");
            List<Q3pojo> results = q3service.getPojosByName(name);

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q3.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
