package com.employee.mgmt.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.employee.mgmt.beans.*;

import oracle.net.aso.p;
 
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
 
    public static Employee findEmployeeForEdit(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.userName, a.gender, a.department, a.salary, a.dob from employee a "//
                + " where a.userName = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            Employee employee = new Employee();
            employee.setUserName(rs.getString("userName"));
            employee.setDepartment(rs.getString("department"));
            employee.setGender(rs.getString("gender"));
            employee.setSalary(rs.getString("salary"));
            employee.setDob(rs.getString("dob"));
            return employee;
        }
        return null;
    }
 
    public static Employee queryEmployee(Connection conn, String userName) throws SQLException {
    	System.out.println(userName);
    	
    	String sql = "Select a.userName, a.gender, a.department, a.salary, a.dob, a.id from employee a " //
                + " where a.userName = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
        Employee employee = new Employee();
        System.out.println("Before whileLoop");
        while (rs.next()) {
        	System.out.println(rs.getString("department"));
            employee.setUserName(rs.getString("userName"));
            employee.setDepartment(rs.getString("department"));
            employee.setGender(rs.getString("gender"));
            employee.setSalary(rs.getString("salary"));
            employee.setDob(rs.getString("dob"));
            employee.setId(rs.getString("id"));
        }
        System.out.println("Afer whileLoop");
        return employee;
    }
    
    public static List<Employee> queryEmployee(Connection conn) throws SQLException {
        String sql = "Select a.userName, a.gender, a.department, a.salary, a.dob from employee a " //
                + " where a.role = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, "EMPLOYEE");
 
        ResultSet rs = pstm.executeQuery();
        List<Employee> list = new ArrayList<Employee>();
        while (rs.next()) {
            Employee employee = new Employee();
            System.out.println(rs.getString("department"));
            employee.setUserName(rs.getString("userName"));
            employee.setDepartment(rs.getString("department"));
            employee.setGender(rs.getString("gender"));
            employee.setSalary(rs.getString("salary"));
            employee.setDob(rs.getString("dob"));
            list.add(employee);
        }
        return list;
    }
 
    public static EmployeePersonal findEmployee(Connection conn, String userName) throws SQLException {
        String sql = "Select a.userName, a.password, a.id from employee_personal a where a.userName=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            EmployeePersonal employee = new EmployeePersonal();//data members here
            employee.setUserName(rs.getString("userName"));
            employee.setPassword(CryptoUtil.decrypt(rs.getString("password")));
            employee.setId(rs.getString("id"));
            return employee;
        }
        return null;
    }
    
    public static List<Employee> findAll(Connection conn) throws SQLException {
    	String sql = "Select a.userName, a.gender, a.department, a.salary, a.dob, a.id from employee a "; //
                
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Employee> list = new ArrayList<Employee>();
        System.out.println("Before whileLoop");
        while (rs.next()) {
        	Employee employee = new Employee();
        	System.out.println(rs.getString("department"));
            employee.setUserName(rs.getString("userName"));
            employee.setDepartment(rs.getString("department"));
            employee.setGender(rs.getString("gender"));
            employee.setSalary(rs.getString("salary"));
            employee.setDob(rs.getString("dob"));
            employee.setId(rs.getString("id"));
            list.add(employee);
        }
        System.out.println("Afer whileLoop");
        return list;
    }
 
    public static void updateEmployee(Connection conn, EmployeePersonal employee) throws SQLException {
        String sql = "Update employee_personal a set a.userName =?, a.password=? where a.id=? ";
        String sqlEmp = "Update employee a set a.userName =? where a.id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        PreparedStatement pstmEmp = conn.prepareStatement(sqlEmp);
 
        pstm.setString(3, employee.getId());
        pstm.setString(1, employee.getUserName());
        pstm.setString(2, employee.getPassword());
        pstmEmp.setString(1, employee.getUserName());
        pstmEmp.setString(2, employee.getId());
        pstm.executeUpdate();
        pstmEmp.executeUpdate();
    }
    
    public static void updateEmployeeDetails(Connection conn, Employee employee) throws SQLException {
        String sql = "Update employee a set a.gender=?, a.department=?, a.salary=?, a.dob=? where a.userName=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(6, employee.getUserName());
        pstm.setString(1, employee.getId());
        pstm.setString(2, employee.getGender());
        pstm.setString(3, employee.getDepartment());
        pstm.setString(4, employee.getSalary());
        pstm.setString(5, employee.getDob());
        pstm.executeUpdate();

    }
 
    public static void insertEmployee(Connection conn, Employee employee) throws SQLException {
        String sql = "Insert into employee(id, userName,gender,department,salary,dob,role) values (?,?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, employee.getId());
        pstm.setString(2, employee.getUserName());
        pstm.setString(3, employee.getGender());
        pstm.setString(4, employee.getDepartment());
        pstm.setString(5, employee.getSalary());
        pstm.setString(6, employee.getDob());
        pstm.setString(7, employee.getRole());
        
 
        pstm.executeUpdate();
    }
 
    public static void deleteEmployee(Connection conn, String id) throws SQLException {
        
        String delSql = "Update employee a set a.dFlag=? where a.id=? ";
        String delSqlEmp = "Update employee_personal a set a.dFlag=? where a.id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(delSql);
        PreparedStatement pstmEmp = conn.prepareStatement(delSqlEmp);
        pstm.setString(1, "D");
        pstm.setString(2, id);
        pstmEmp.setString(1, "D");
        pstmEmp.setString(2, id);
        
 
        pstm.executeUpdate();
        pstmEmp.executeUpdate();
    }
 
}