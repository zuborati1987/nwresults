package com.codecool.database;

import com.codecool.model.Q5pojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q5Dao extends AbstractDao {

    public Q5Dao(Connection connection) {
        super(connection);
    }

    public List<Q5pojo> findAll() throws SQLException {
        String sql = "select suppliers.company_name as Company, products.product_name as Product, \n" +
            "products.unit_price as Price\n" +
            "from products\n" +
            "join suppliers\n" +
            "on products.supplier_id = suppliers.supplier_id\n" +
            "group by company, product, price\n" +
            "order by price desc, product asc, company asc;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Q5pojo> pojos = new ArrayList<>();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }


    public List<Q5pojo> findByName(String name) throws SQLException {
        List<Q5pojo> pojos = new ArrayList<>();
        String sql = "select suppliers.company_name as Company, products.product_name as Product, \n" +
            "products.unit_price as Price\n" +
            "from products\n" +
            "join suppliers\n" +
            "on products.supplier_id = suppliers.supplier_id\n" +
            "group by company, product, price\n" +
            "order by price desc, product asc, company asc where product_name = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }

    private Q5pojo fetchPojo(ResultSet resultSet) throws SQLException {
        String productName = resultSet.getString("Product");
        String companyName = resultSet.getString("Company");
        int price = resultSet.getInt("Price");
        return new Q5pojo(productName, companyName, price);
    }


}
