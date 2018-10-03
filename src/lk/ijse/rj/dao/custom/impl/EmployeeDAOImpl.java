/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.EmployeeDAO;
import lk.ijse.rj.entity.Employee;

/**
 *
 * @author Ilman Iqbal
 */
public class EmployeeDAOImpl implements EmployeeDAO{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(Employee entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?)", entity.getEmployeeId(), entity.getName(), entity.getContact(), entity.getAddress());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Employee SET name=?, address=?, contact=? WHERE employeeId=?", entity.getName(), entity.getContact(), entity.getAddress(), entity.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Employee WHERE employeeId=?", id);
    }
    
    @Override
    public Employee find(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee", id);
        Employee employee = null;
        if (rst.next()){
            employee = new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return employee;
    }
    
    @Override
    public ArrayList<Employee> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee");
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()){
            Employee employee = new Employee(rst.getString(1), 
                    rst.getString(2), rst.getString(3), rst.getString(4));
            employees.add(employee); 
        }
        return employees;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
}
