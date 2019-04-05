package com.codecool.service;

import com.codecool.database.Q2Dao;
import com.codecool.model.Q2pojo;

import java.sql.SQLException;
import java.util.List;

public class Q2service {
    private final Q2Dao Q2Dao;

    public Q2service(Q2Dao Q2Dao) {
        this.Q2Dao = Q2Dao;
    }

    public List<Q2pojo> getPojos() throws SQLException {
        return Q2Dao.findAll();
    }

    public List<Q2pojo> getPojosByName(String name) throws SQLException {
        return Q2Dao.findByName(name);
    }
}
