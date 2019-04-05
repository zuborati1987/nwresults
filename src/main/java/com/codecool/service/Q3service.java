package com.codecool.service;

import com.codecool.database.Q3Dao;
import com.codecool.model.Q3pojo;

import java.sql.SQLException;
import java.util.List;

public class Q3service {
    private final Q3Dao Q3Dao;

    public Q3service(Q3Dao Q3Dao) {
        this.Q3Dao = Q3Dao;
    }

    public List<Q3pojo> getPojos() throws SQLException {
        return Q3Dao.findAll();
    }

    public List<Q3pojo> getPojosByName(String name) throws SQLException {
        return Q3Dao.findByName(name);
    }
}
