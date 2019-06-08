package com.t1708m.model;

import com.t1708m.entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {

    public Student findByUsernameAndStatus(String username, Student.Status status) {
        try {
            String sqlCommand = "select * from students where username = ? and status = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, status.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String rsUsername = resultSet.getString("username");
                String rsPassword = resultSet.getString("password");
                String rsEmail = resultSet.getString("email");
                String rsFullName = resultSet.getString("fullName");
                String rsAddress = resultSet.getString("address");
                String rsPhone = resultSet.getString("phone");
                int rsStatus = resultSet.getInt("status");
                int rsRole = resultSet.getInt("role");
                Student student = new Student();
                student.setUsername(rsUsername);
                student.setPassword(rsPassword);
                student.setEmail(rsEmail);
                student.setFullName(rsFullName);
                student.setAddress(rsAddress);
                student.setPhone(rsPhone);
                student.setStatus(Student.Status.findByValue(rsStatus));
                student.setRole(Student.Role.findByValue(rsRole));
                return student;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean save(Student student) {
        try {
            String sqlCommand = "insert into students (username, password, email, fullName, address, phone, role, status) values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getFullName());
            preparedStatement.setString(5, student.getAddress());
            preparedStatement.setString(6, student.getPhone());
            preparedStatement.setInt(7, student.getRole());
            preparedStatement.setInt(8, student.getStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Student> getList() {
        ArrayList<Student> results = new ArrayList<>();
        try {
            String sqlCommand = "select * from students";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rsUsername = resultSet.getString("username");
                String rsPassword = resultSet.getString("password");
                String rsFullName = resultSet.getString("fullName");
                String rsEmail = resultSet.getString("email");
                String rsPhone = resultSet.getString("phone");
                String rsAddress = resultSet.getString("address");
                int rsStatus = resultSet.getInt("status");
                int rsRole = resultSet.getInt("role");
                Student student = new Student();
                student.setUsername(rsUsername);
                student.setPassword(rsPassword);
                student.setFullName(rsFullName);
                student.setEmail(rsEmail);
                student.setAddress(rsAddress);
                student.setPhone(rsPhone);
                student.setStatus(Student.Status.findByValue(rsStatus));
                student.setRole(Student.Role.findByValue(rsRole));
                results.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
