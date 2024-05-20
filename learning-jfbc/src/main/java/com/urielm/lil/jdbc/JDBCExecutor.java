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
            Customer customer = new Customer();

            customer.setFirstName("George");
            customer.setLastName("Washington");
            customer.setEmail("george.whasington@wh.gov");
            customer.setPhone("(555) 555-6543");
            customer.setAddress("1234 Main St.");
            customer.setCity("Mount Vernon");
            customer.setState("VA");
            customer.setZipCode("22121");

            customerDAO.create(customer);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
