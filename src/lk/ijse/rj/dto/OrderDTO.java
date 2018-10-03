/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import lk.ijse.rj.entity.Stock;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderDTO {
    private String orderId;
    private String employeeId;
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
    private ArrayList<OrderItemDTO> orderItems;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String employeeId, String name, String address, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal buyGold, BigDecimal advance, BigDecimal netTotal, BigDecimal balance, Date dateOfActualDelivery, Date dateOfCancellation) {
        this.orderId = orderId;
        this.employeeId = employeeId;
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
    }

    public OrderDTO(String orderId, String employeeId, String name, String address, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal buyGold, BigDecimal advance, BigDecimal netTotal, BigDecimal balance, Date dateOfActualDelivery, Date dateOfCancellation, ArrayList<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.employeeId = employeeId;
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
        this.orderItems = orderItems;
    }
    
    
    
    

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
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
     * @return the orderItems
     */
    public ArrayList<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(ArrayList<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    
    
}
