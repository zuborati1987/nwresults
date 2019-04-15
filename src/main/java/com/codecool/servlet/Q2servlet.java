package com.codecool.servlet;

import com.codecool.database.Q2Dao;
import com.codecool.model.Q2pojo;
import com.codecool.service.Q2service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/query2")
public class Q2servlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q2Dao q2Dao = new Q2Dao(connection);
            Q2service q2service = new Q2service(q2Dao);
            List<Q2pojo> results = q2service.getPojos();

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q2.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            Q2Dao q2Dao = new Q2Dao(connection);
            Q2service q2service = new Q2service(q2Dao);

            String name = req.getParameter("name");
            List<Q2pojo> results = q2service.getPojosByName(name);

            req.setAttribute("results", results);
            req.getRequestDispatcher("Q2.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
