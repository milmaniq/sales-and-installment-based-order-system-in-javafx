/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.view.util.tblmodel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Ilman Iqbal
 */
public class ManageSaleTM {

    private String salesId;
    private String name;
    private String contact;
    private Date dateOfSales;
    private BigDecimal total;
    private String employeeId;
    private String orderId;
    

    public ManageSaleTM() {
    }

    public ManageSaleTM(String salesId, String name, String contact, Date dateOfSales, BigDecimal total, String employeeId, String orderId) {
        this.salesId = salesId;
        this.name = name;
        this.contact = contact;
        this.dateOfSales = dateOfSales;
        this.total = total;
        this.employeeId = employeeId;
        this.orderId = orderId;
        
    }

    
    /**
     * @return the salesId
     */
    public String getSalesId() {
        return salesId;
    }

    /**
     * @param salesId the salesId to set
     */
    public void setSalesId(String salesId) {
        this.salesId = salesId;
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
     * @return the dateOfSales
     */
    public Date getDateOfSales() {
        return dateOfSales;
    }

    /**
     * @param dateOfSales the dateOfSales to set
     */
    public void setDateOfSales(Date dateOfSales) {
        this.dateOfSales = dateOfSales;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

}
