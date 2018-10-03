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
public class BalancePaymentTM {
    private int inc;
    private Date dateOfPayment;
    private BigDecimal amountPaid;

    public BalancePaymentTM() {
    }

    public BalancePaymentTM(int inc, Date dateOfPayment, BigDecimal amountPaid) {
        this.inc = inc;
        this.dateOfPayment = dateOfPayment;
        this.amountPaid = amountPaid;
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
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    
}
