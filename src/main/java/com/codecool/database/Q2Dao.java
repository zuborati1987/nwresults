package com.codecool.database;

import com.codecool.model.Q2pojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q2Dao extends AbstractDao {

    public Q2Dao(Connection connection) {
        super(connection);
    }

    public List<Q2pojo> findAll() throws SQLException {
        String sql = "SELECT company_name as Company, count(products.supplier_id) as NumberOfProducts\n" +
            "FROM suppliers \n" +
            "JOIN products\n" +
            "     ON products.supplier_id = suppliers.supplier_id\n" +
            "     group by company_name\n" +
            "order by numberofproducts desc, company_name;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Q2pojo> pojos = new ArrayList<>();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }


    public List<Q2pojo> findByName(String name) throws SQLException {
        List<Q2pojo> pojos = new ArrayList<>();
        String sql = "SELECT company_name as Company, count(products.supplier_id) as NumberOfProducts\n" +
            "FROM suppliers \n" +
            "JOIN products\n" +
            "     ON products.supplier_id = suppliers.supplier_id\n" +
            "where company_name = ?\n" +
            "     group by company_name\n" +
            "order by numberofproducts desc, company_name;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }

    private Q2pojo fetchPojo(ResultSet resultSet) throws SQLException {
        String productName = resultSet.getString("Company");
        int productNum = resultSet.getInt("NumberOfProducts");
        return new Q2pojo(productName, productNum);
    }

}
