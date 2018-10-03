/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom;

import java.util.ArrayList;
import lk.ijse.rj.business.SuperBO;
import lk.ijse.rj.dto.EmployeeDTO;

/**
 *
 * @author Ilman Iqbal
 */
public interface EmployeeBO extends SuperBO {
    public boolean insertEmployee(EmployeeDTO dto) throws Exception;
    
    public boolean updateEmployee(EmployeeDTO dto) throws Exception;
    
    public boolean deleteEmployee(String id) throws Exception;
    
    public ArrayList<EmployeeDTO> getAllEmployees() throws Exception;
    
    public EmployeeDTO findEmployee(String id) throws Exception;
    
}
