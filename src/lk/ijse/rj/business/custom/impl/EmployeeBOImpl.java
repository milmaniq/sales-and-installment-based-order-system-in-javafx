/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.rj.business.custom.EmployeeBO;
import lk.ijse.rj.dao.DAOFactory;
import lk.ijse.rj.dao.custom.EmployeeDAO;
import lk.ijse.rj.dto.EmployeeDTO;
import lk.ijse.rj.entity.Employee;

/**
 *
 * @author Ilman Iqbal
 */
public class EmployeeBOImpl implements EmployeeBO{
    private EmployeeDAO employeeDAO;

    public EmployeeBOImpl() {
        employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    }
    
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws Exception {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getName(), 
                    employee.getContact(), employee.getAddress());
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }
    
/////////////checked upto here

    
    @Override
    public boolean insertEmployee(EmployeeDTO dto) throws Exception {
        Employee employee = new Employee(dto.getEmployeeId(), dto.getName(), dto.getAddress(), dto.getContact());
        return employeeDAO.insert(employee);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
        Employee employee = new Employee(dto.getEmployeeId(), dto.getName(), dto.getAddress(), dto.getContact());
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    

    @Override
    public EmployeeDTO findEmployee(String id) throws Exception {
        Employee employee = employeeDAO.find(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getContact(), employee.getAddress());
        return employeeDTO;
    }

   
}
