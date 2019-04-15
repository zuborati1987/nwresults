package com.codecool.servlet;

import com.codecool.database.Q5Dao;
import com.codecool.model.Q5pojo;
import com.codecool.service.Q5service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/query5")
public class Q5servlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q5Dao q5Dao = new Q5Dao(connection);
            Q5service q5service = new Q5service(q5Dao);
            List<Q5pojo> results = q5service.getPojos();

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q5.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q5Dao q5Dao = new Q5Dao(connection);
            Q5service q5service = new Q5service(q5Dao);

            String name = req.getParameter("name");
            List<Q5pojo> results = q5service.getPojosByName(name);

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q5.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
