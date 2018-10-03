/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleDTO {
    private String salesId;
    private String employeeId;
    private String orderId;
    private String name;
    private String contact;
    private Date dateOfSales;
    private String address;
    private BigDecimal total;
    private BigDecimal buyGold;
    private BigDecimal netTotal;
    private Date dateOfCancellation;
    private ArrayList<SaleItemDTO> SaleItems;

    public SaleDTO() {
    }

    public SaleDTO(String salesId, String employeeId, String orderId, String name, String contact, Date dateOfSales, String address, BigDecimal total, BigDecimal buyGold, BigDecimal netTotal, Date dateOfCancellation) {
        this.salesId = salesId;
        this.employeeId = employeeId;
        this.orderId = orderId;
        this.name = name;
        this.contact = contact;
        this.dateOfSales = dateOfSales;
        this.address = address;
        this.total = total;
        this.buyGold = buyGold;
        this.netTotal = netTotal;
        this.dateOfCancellation = dateOfCancellation;
    }

    public SaleDTO(String salesId, String employeeId, String orderId, String name, String contact, Date dateOfSales, String address, BigDecimal total, BigDecimal buyGold, BigDecimal netTotal, Date dateOfCancellation, ArrayList<SaleItemDTO> SaleItems) {
        this.salesId = salesId;
        this.employeeId = employeeId;
        this.orderId = orderId;
        this.name = name;
        this.contact = contact;
        this.dateOfSales = dateOfSales;
        this.address = address;
        this.total = total;
        this.buyGold = buyGold;
        this.netTotal = netTotal;
        this.dateOfCancellation = dateOfCancellation;
        this.SaleItems = SaleItems;
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
     * @return the SaleItems
     */
    public ArrayList<SaleItemDTO> getSaleItems() {
        return SaleItems;
    }

    /**
     * @param SaleItems the SaleItems to set
     */
    public void setSaleItems(ArrayList<SaleItemDTO> SaleItems) {
        this.SaleItems = SaleItems;
    }

    
}
