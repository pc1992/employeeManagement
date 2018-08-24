package com.employee.mgmt.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.employee.mgmt.beans.*;
 
public class DBUtils {
 
    public static EmployeePersonal findUser(Connection conn, //
            String userName, String password, String ip) throws SQLException {
    	
    	String encryptedPassword = CryptoUtil.encrypt(password);
    	System.out.println(encryptedPassword);
 
        String sql = "Select a.userName, a.password, a.clientIp from employee_personal a " //
                + " where a.userName = ? and a.password= ? and a.clientIp= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, encryptedPassword);
        pstm.setString(3, ip);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            EmployeePersonal user = new EmployeePersonal();
            user.setUserName(userName);
           //Add data members here
            user.setPassword(password);
            return user;
        }
        return null;
    }
 
    public static Employee findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
                + " where a.User_Name = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            Employee user = new Employee();
            user.setUserName(userName);
            //Add data members here
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static List<Employee> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Employee> list = new ArrayList<Employee>();
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Employee employee = new Employee();
            //Add Data members here
            employee.setUserName(name);
            
            list.add(employee);
        }
        return list;
    }
 
    public static EmployeePersonal findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            EmployeePersonal product = new EmployeePersonal();//data members here
            return product;
        }
        return null;
    }
 
    public static void updateProduct(Connection conn, EmployeePersonal product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where Code=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
       /* pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());*/
        pstm.executeUpdate();
    }
 
    public static void insertEmployee(Connection conn, Employee employee) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 /*
        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());*/
 
        pstm.executeUpdate();
    }
 
    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
    }
 
}