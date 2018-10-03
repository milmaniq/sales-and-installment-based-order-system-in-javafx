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
public class OrderBalancePayment {
    private int inc;
    private Date date;
    private BigDecimal amount;
    private String orderId;

    public OrderBalancePayment() {
    }
    
    public OrderBalancePayment(Date date, BigDecimal amount, String orderId) {
        this.date = date;
        this.amount = amount;
        this.orderId = orderId;
    }

    public OrderBalancePayment(int inc, Date date, BigDecimal amount, String orderId) {
        this.inc = inc;
        this.date = date;
        this.amount = amount;
        this.orderId = orderId;
    }

    /**
     * @return the inc
     */
    public int getInc() {
        return inc;
    }

    /**
     * @param inc the inc to set
     */
    public void setInc(int inc) {
        this.inc = inc;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
