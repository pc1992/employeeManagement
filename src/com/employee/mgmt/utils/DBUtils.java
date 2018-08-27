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
 
        String sql = "Select a.userName, a.password, a.clientIp, a.role from employee_personal a " //
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
            user.setRole(rs.getString(4));
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
           // String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            Employee user = new Employee();
            user.setUserName(userName);
            //Add data members here
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static Employee queryEmployee(Connection conn, String userName) throws SQLException {
        //String sql = "Select a.Code, a.Name, a.Price from Product a ";
    	System.out.println(userName);
    	
    	String sql = "Select a.userName, a.gender, a.department, a.salary, a.dob from employee a " //
                + " where a.userName = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
       // List<Employee> list = new ArrayList<Employee>();
        Employee employee = new Employee();
        while (rs.next()) {
            /*userName = rs.getString("userName");
            String gender = rs.getString("gender");
            String department = rs.getString("department");*/
            
            //Add Data members here
        	System.out.println(rs.getString("department"));
            employee.setUserName(rs.getString("userName"));
            employee.setDepartment(rs.getString("department"));
            employee.setGender(rs.getString("gender"));
            employee.setSalary(rs.getString("salary"));
            employee.setDob(rs.getString("dob"));
            
            //list.add(employee);
        }
        return employee;
    }
 
    public static EmployeePersonal findEmployee(Connection conn, String userName) throws SQLException {
        String sql = "Select a.userName, a.password from Product a where a.userName=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            EmployeePersonal employee = new EmployeePersonal();//data members here
            employee.setUserName(rs.getString("userName"));
            employee.setPassword(rs.getString("password"));
            return employee;
        }
        return null;
    }
 
    public static void updateProduct(Connection conn, EmployeePersonal employee) throws SQLException {
        String sql = "Update employee_personal a set a.userName =?, a.password=? where a.userName=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, employee.getUserName());
        pstm.setString(2, employee.getUserName());
        pstm.setString(3, employee.getPassword());
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