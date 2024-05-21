package com.urielm.lil.jdbc;

import com.urielm.lil.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {

    private static final String INSERT = "INSERT INTO CUSTOMER (first_name, last_name, email," +
            "phone, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ONE = "SELECT customer_id, first_name, last_name, email," +
            "phone, address, city, state, zipcode FROM customer WHERE customer_id = ?";

    private static final String UPDATE = "UPDATE customer SET first_name=?, last_name=?," +
            "email=?, phone=?, address=?, city=?, state=?, zipcode=? WHERE customer_id = ?";

    private static final String DELETE = "DELETE FROM customer WHERE customer_id = ?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        Customer customer = new Customer();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ONE);){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setEmail(resultSet.getString("email"));
                 customer.setPhone(resultSet.getString("phone"));
                 customer.setAddress(resultSet.getString("address"));
                 customer.setCity(resultSet.getString("city"));
                 customer.setState(resultSet.getString("state"));
                 customer.setZipCode(resultSet.getString("zipcode"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Customer update(Customer dto) {

        Customer customer = null;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, dto.getFirstName());
            preparedStatement.setString(2, dto.getLastName());
            preparedStatement.setString(3, dto.getEmail());
            preparedStatement.setString(4, dto.getPhone());
            preparedStatement.setString(5, dto.getAddress());
            preparedStatement.setString(6, dto.getCity());
            preparedStatement.setString(7, dto.getState());
            preparedStatement.setString(8, dto.getZipCode());
            preparedStatement.setLong(9, dto.getId());
            preparedStatement.execute();

            customer = this.findById(dto.getId());

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto) {

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT);) {
            preparedStatement.setString(1, dto.getFirstName());
            preparedStatement.setString(2, dto.getLastName());
            preparedStatement.setString(3, dto.getEmail());
            preparedStatement.setString(4, dto.getPhone());
            preparedStatement.setString(5, dto.getAddress());
            preparedStatement.setString(6, dto.getCity());
            preparedStatement.setString(7, dto.getState());
            preparedStatement.setString(8, dto.getZipCode());
            preparedStatement.execute();
            int id = this.getLastVal(CUSTOMER_SEQUENCE);
            return this.findById(id);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE);){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Customer deleted succesfully...");

    }
}
