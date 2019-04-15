package com.codecool.database;

import com.codecool.model.Q1pojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q1Dao extends AbstractDao {

    public Q1Dao(Connection connection) {
        super(connection);
    }

    public List<Q1pojo> findAll() throws SQLException {
        String sql = "SELECT product_name, suppliers.company_name FROM products\n" +
            "inner join Suppliers \n" +
            "on Products.supplier_id = Suppliers.supplier_id\n" +
            "order by products.product_name, suppliers.company_name asc;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Q1pojo> pojos = new ArrayList<>();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }



    public List<Q1pojo> findByName(String name) throws SQLException {
        List<Q1pojo> pojos = new ArrayList<>();
        String sql = "SELECT product_name, suppliers.company_name FROM products\n" +
            "inner join Suppliers \n" +
            "on Products.supplier_id = Suppliers.supplier_id\n" +
            "where product_name = ?\n" +
            "order by products.product_name, suppliers.company_name asc;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }

    private Q1pojo fetchPojo(ResultSet resultSet) throws SQLException {
        String productName = resultSet.getString("product_name");
        String companyName = resultSet.getString("company_name");
        return new Q1pojo(productName, companyName);
    }
}
