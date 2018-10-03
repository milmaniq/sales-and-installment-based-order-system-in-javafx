/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business;

import lk.ijse.rj.business.custom.impl.EmployeeBOImpl;
import lk.ijse.rj.business.custom.impl.OrderBOImpl;
import lk.ijse.rj.business.custom.impl.OrderBalancePaymentBOImpl;
import lk.ijse.rj.business.custom.impl.SaleBOImpl;
import lk.ijse.rj.business.custom.impl.StockBOImpl;

/**
 *
 * @author Ilman Iqbal
 */
public class BOFactory {

    private static BOFactory bOFactory;

    public enum BOTypes {
        EMPLOYEE, ORDER, ORDER_BALANCE_PAYMENT, SALE, STOCK;
    }

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }

    public SuperBO getBO(BOTypes bOType) {
        switch (bOType) {
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_BALANCE_PAYMENT:
                return new OrderBalancePaymentBOImpl();
            case SALE:
                return new SaleBOImpl();
            case STOCK:
                return new StockBOImpl();
            default:
                return null;
        }
    }

}
