package com.codecool.service;

import com.codecool.database.Q4Dao;
import com.codecool.model.Q4pojo;

import java.sql.SQLException;
import java.util.List;

public class Q4service {
    private final Q4Dao Q4Dao;

    public Q4service(Q4Dao Q4Dao) {
        this.Q4Dao = Q4Dao;
    }

    public List<Q4pojo> getPojos() throws SQLException {
        return Q4Dao.findAll();
    }

    public List<Q4pojo> getPojosByName(String name) throws SQLException {
        return Q4Dao.findByName(name);
    }
}
