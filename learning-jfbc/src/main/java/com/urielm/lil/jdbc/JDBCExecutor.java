package com.urielm.lil.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String[] args) {

        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");

        try{
            Connection connection = databaseConnectionManager.getConnection();
            OrderDAO  orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order);

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
