package com.codecool.service;

import com.codecool.database.Q1Dao;
import com.codecool.model.Q1pojo;

import java.sql.SQLException;
import java.util.List;

public final class Q1service{

    private final Q1Dao q1Dao;

    public Q1service(Q1Dao q1Dao) {
        this.q1Dao = q1Dao;
    }

    public List<Q1pojo> getPojos() throws SQLException {
        return q1Dao.findAll();
    }

    public List<Q1pojo> getPojosByName(String name) throws SQLException {
        return q1Dao.findByName(name);
    }
}
