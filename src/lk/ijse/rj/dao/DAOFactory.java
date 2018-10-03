/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao;

import lk.ijse.rj.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.rj.dao.custom.impl.OrderBalancePaymentDAOImpl;
import lk.ijse.rj.dao.custom.impl.OrderDAOImpl;
import lk.ijse.rj.dao.custom.impl.OrderItemDAOImpl;
import lk.ijse.rj.dao.custom.impl.QueryDAOImpl;
import lk.ijse.rj.dao.custom.impl.SaleDAOImpl;
import lk.ijse.rj.dao.custom.impl.SaleItemDAOImpl;
import lk.ijse.rj.dao.custom.impl.StockDAOImpl;

/**
 *
 * @author Ilman Iqbal
 */
public class DAOFactory {
    private static DAOFactory dAOFactory;
    
    public enum DAOTypes{
        EMPLOYEE, ORDER_BALANCE_PAYMENT, ORDER, ORDER_ITEM, SALE, SALE_ITEM, STOCK, QUERY;
    }
    
    private DAOFactory(){
        
    }
    
    public static DAOFactory getInstance(){
        if (dAOFactory == null){
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }
    
    public SuperDAO getDAO(DAOTypes dAOType){
        switch (dAOType){
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ORDER_BALANCE_PAYMENT:
                return new OrderBalancePaymentDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_ITEM:
                return new OrderItemDAOImpl();
            case SALE:
                return new SaleDAOImpl();
            case SALE_ITEM:
                return new SaleItemDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
