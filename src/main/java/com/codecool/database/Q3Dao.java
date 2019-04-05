package com.codecool.database;

import com.codecool.model.Q3pojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q3Dao extends AbstractDao {

    public Q3Dao(Connection connection) {
        super(connection);
    }

    public List<Q3pojo> findAll() throws SQLException {
        String sql = "SELECT company_name as Company\n" +
            "FROM suppliers \n" +
            "JOIN products\n" +
            "     ON products.supplier_id = suppliers.supplier_id\n" +
            "     group by company_name\n" +
            "     having count(products.supplier_id) > 4\n" +
            "order by company_name;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Q3pojo> pojos = new ArrayList<>();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }


    public List<Q3pojo> findByName(String name) throws SQLException {
        List<Q3pojo> pojos = new ArrayList<>();
        String sql = "SELECT company_name as Company\n" +
            "FROM suppliers \n" +
            "JOIN products\n" +
            "     ON products.supplier_id = suppliers.supplier_id\n" +
            "     group by company_name\n" +
            "     having count(products.supplier_id) > 4\n" +
            "order by company_name; where company_name = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }

    private Q3pojo fetchPojo(ResultSet resultSet) throws SQLException {
        String companyName = resultSet.getString("Company");
        return new Q3pojo(companyName);
    }

}
