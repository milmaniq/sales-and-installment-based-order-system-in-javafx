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
public class ManageOrderTM {
    private String orderId;
    private String name;
    private String contact;
    private Date dateOfOrder;
    private Date dateOfDelivery;
    private BigDecimal total;
    private BigDecimal balance;
    private String status;

    public ManageOrderTM() {
    }

    public ManageOrderTM(String orderId, String name, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal balance, String status) {
        this.orderId = orderId;
        this.name = name;
        this.contact = contact;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDelivery = dateOfDelivery;
        this.total = total;
        this.balance = balance;
        this.status = status;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   

}
