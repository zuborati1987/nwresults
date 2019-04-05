package com.codecool.service;

import com.codecool.database.Q5Dao;
import com.codecool.model.Q5pojo;

import java.sql.SQLException;
import java.util.List;

public class Q5service {
    private final Q5Dao Q5Dao;

    public Q5service(Q5Dao Q5Dao) {
        this.Q5Dao = Q5Dao;
    }

    public List<Q5pojo> getPojos() throws SQLException {
        return Q5Dao.findAll();
    }

    public List<Q5pojo> getPojosByName(String name) throws SQLException {
        return Q5Dao.findByName(name);
    }
}
