/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.view.util.tblmodel;

import java.sql.Date;

/**
 *
 * @author Ilman Iqbal
 */
public class EmployeeTM {
    private String employeeId;
    private String name;
    private String contact;
    private String address;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeId, String name, String contact, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    
    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
}
