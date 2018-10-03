/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Ilman Iqbal
 */
public class Sale {
    private String saleId;
    private String name;
    private String address;
    private String contact;
    private Date dateOfSale;
    private BigDecimal total;
    private BigDecimal buyGold;
    private BigDecimal netTotal;
    private Date dateOfCancellation;
    private String employeeId;
    private String orderId;

    public Sale() {
    }

    public Sale(String saleId, String name, String address, String contact, Date dateOfSale, BigDecimal total, BigDecimal buyGold, BigDecimal netTotal, Date dateOfCancellation, String employeeId, String orderId) {
        this.saleId = saleId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfSale = dateOfSale;
        this.total = total;
        this.buyGold = buyGold;
        this.netTotal = netTotal;
        this.dateOfCancellation = dateOfCancellation;
        this.employeeId = employeeId;
        this.orderId = orderId;
    }

    
    /**
     * @return the saleId
     */
    public String getSaleId() {
        return saleId;
    }

    /**
     * @param saleId the saleId to set
     */
    public void setSaleId(String saleId) {
        this.saleId = saleId;
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
     * @return the dateOfSale
     */
    public Date getDateOfSale() {
        return dateOfSale;
    }

    /**
     * @param dateOfSale the dateOfSale to set
     */
    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
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
     * @return the buyGold
     */
    public BigDecimal getBuyGold() {
        return buyGold;
    }

    /**
     * @param buyGold the buyGold to set
     */
    public void setBuyGold(BigDecimal buyGold) {
        this.buyGold = buyGold;
    }

    /**
     * @return the netTotal
     */
    public BigDecimal getNetTotal() {
        return netTotal;
    }

    /**
     * @param netTotal the netTotal to set
     */
    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    /**
     * @return the dateOfCancellation
     */
    public Date getDateOfCancellation() {
        return dateOfCancellation;
    }

    /**
     * @param dateOfCancellation the dateOfCancellation to set
     */
    public void setDateOfCancellation(Date dateOfCancellation) {
        this.dateOfCancellation = dateOfCancellation;
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

  
}
