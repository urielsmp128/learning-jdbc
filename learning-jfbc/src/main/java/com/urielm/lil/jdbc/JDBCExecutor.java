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
            CustomerDAO customerDAO =  new CustomerDAO(connection);
            Customer customer = customerDAO.findById(10000);
            System.out.println("Before update: " + customer);

            customer.setEmail("ggwashington@wh.gov");
            customerDAO.update(customer);
            System.out.println("After update: " + customer);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
