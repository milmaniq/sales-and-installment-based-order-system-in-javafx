/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ilman Iqbal
 */
public class Order {
    private String orderId;
    private String name;
    private String address;
    private String contact;
    private Date dateOfOrder;
    private Date dateOfDelivery;
    private BigDecimal total;
    private BigDecimal buyGold;
    private BigDecimal advance;
    private BigDecimal netTotal;
    private BigDecimal balance;
    private Date dateOfActualDelivery;
    private Date dateOfCancellation;
    private String employeeId;
    private ArrayList<OrderItem> orderItems;
    
    public Order(String orderId, BigDecimal balance){
        this.orderId = orderId;
        this.balance = balance;
    }
    
    public Order(String orderId, Date dateOfActualDelivery){
        this.orderId = orderId;
        this.dateOfActualDelivery = dateOfActualDelivery;
    }

    public Order(String orderId, String name, String address, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal buyGold, BigDecimal advance, BigDecimal netTotal, BigDecimal balance, Date dateOfActualDelivery, Date dateOfCancellation, String employeeId) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDelivery = dateOfDelivery;
        this.total = total;
        this.buyGold = buyGold;
        this.advance = advance;
        this.netTotal = netTotal;
        this.balance = balance;
        this.dateOfActualDelivery = dateOfActualDelivery;
        this.dateOfCancellation = dateOfCancellation;
        this.employeeId = employeeId;
    }
    
    public Order(String orderId, String name, String address, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal buyGold, BigDecimal advance, BigDecimal netTotal, BigDecimal balance, Date dateOfActualDelivery, Date dateOfCancellation, String employeeId, ArrayList<OrderItem> orderItems) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDelivery = dateOfDelivery;
        this.total = total;
        this.buyGold = buyGold;
        this.advance = advance;
        this.netTotal = netTotal;
        this.balance = balance;
        this.dateOfActualDelivery = dateOfActualDelivery;
        this.dateOfCancellation = dateOfCancellation;
        this.employeeId = employeeId;
        this.orderItems = orderItems;
    }

  
 
    public Order() {
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
     * @return the dateOfOrder
     */
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    /**
     * @param dateOfOrder the dateOfOrder to set
     */
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * @return the dateOfDelivery
     */
    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * @param dateOfDelivery the dateOfDelivery to set
     */
    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
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
     * @return the advance
     */
    public BigDecimal getAdvance() {
        return advance;
    }

    /**
     * @param advance the advance to set
     */
    public void setAdvance(BigDecimal advance) {
        this.advance = advance;
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
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return the dateOfActualDelivery
     */
    public Date getDateOfActualDelivery() {
        return dateOfActualDelivery;
    }

    /**
     * @param dateOfActualDelivery the dateOfActualDelivery to set
     */
    public void setDateOfActualDelivery(Date dateOfActualDelivery) {
        this.dateOfActualDelivery = dateOfActualDelivery;
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
     * @return the orderItems
     */
    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

  
  
}
