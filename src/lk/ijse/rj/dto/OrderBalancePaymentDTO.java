/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderBalancePaymentDTO {
    private int inc;
    private String orderId;
    private Date dateOfPayment;
    private BigDecimal amount;

    public OrderBalancePaymentDTO() {
    }
    
    public OrderBalancePaymentDTO(String orderId, Date dateOfPayment, BigDecimal amount) {
        this.orderId = orderId;
        this.dateOfPayment = dateOfPayment;
        this.amount = amount;
    }

    public OrderBalancePaymentDTO(int inc, String orderId, Date dateOfPayment, BigDecimal amount) {
        this.inc = inc;
        this.orderId = orderId;
        this.dateOfPayment = dateOfPayment;
        this.amount = amount;
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
     * @return the dateOfPayment
     */
    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    /**
     * @param dateOfPayment the dateOfPayment to set
     */
    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
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
   
}
