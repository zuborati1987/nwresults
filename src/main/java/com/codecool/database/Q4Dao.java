package com.codecool.database;

import com.codecool.model.Q4pojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q4Dao extends AbstractDao {

    public Q4Dao(Connection connection) {
        super(connection);
    }

    public List<Q4pojo> findAll() throws SQLException {
        String sql = "SELECT Company_name as Company, String_AGG (cast(Order_id as text), ',') AS IDs\n" +
            "FROM orders \n" +
            "left join customers\n" +
            "on orders.customer_id = customers.customer_id\n" +
            "GROUP BY customers.customer_id\n" +
            "order by company_name;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Q4pojo> pojos = new ArrayList<>();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }


    public List<Q4pojo> findByName(String name) throws SQLException {
        List<Q4pojo> pojos = new ArrayList<>();
        String sql = "SELECT Company_name as Company, String_AGG (cast(Order_id as text), ',') AS IDs\n" +
            "FROM orders \n" +
            "left join customers\n" +
            "on orders.customer_id = customers.customer_id\n" +
            "where company_name = ?\n" +
            "GROUP BY customers.customer_id\n" +
            "order by company_name;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pojos.add(fetchPojo(resultSet));
            }
            return pojos;
        }
    }

    private Q4pojo fetchPojo(ResultSet resultSet) throws SQLException {
        String ids = resultSet.getString("IDs");
        String companyName = resultSet.getString("Company");
        return new Q4pojo(companyName, ids);
    }

}
