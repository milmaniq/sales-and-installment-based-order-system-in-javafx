/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.OrderBalancePaymentDAO;
import lk.ijse.rj.entity.OrderBalancePayment;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderBalancePaymentDAOImpl implements OrderBalancePaymentDAO {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(OrderBalancePayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO OrderBalancePayment(date, amount, orderId) VALUES(?,?,?)", 
                entity.getDate(), entity.getAmount(), entity.getOrderId());
    }

    @Override
    public boolean update(OrderBalancePayment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE OrderBalancePayment SET date=?, amount=?, orderId=? WHERE inc=?", 
                entity.getDate(), entity.getAmount(), entity.getOrderId(), entity.getInc());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM OrderBalancePayment WHERE inc=?", id);
    }

    @Override
    public OrderBalancePayment find(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderBalancePayment WHERE inc=?", id);
        OrderBalancePayment orderBalancePayment = null;
        if (rst.next()){
            orderBalancePayment = new OrderBalancePayment(rst.getInt(1), rst.getDate(2), rst.getBigDecimal(3), 
                    rst.getString(4));
        }
        return orderBalancePayment;
    }
    
    @Override
    public ArrayList<OrderBalancePayment> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderBalancePayment");
        ArrayList<OrderBalancePayment> orderBalancePayments= new ArrayList<>();
        while (rst.next()){
            OrderBalancePayment orderBalancePayment = new OrderBalancePayment(rst.getInt(1), rst.getDate(2), 
                    rst.getBigDecimal(3), rst.getString(4));
            orderBalancePayments.add(orderBalancePayment);
        }
        return orderBalancePayments;
    }
    
    
    @Override
    public ArrayList<OrderBalancePayment> getAllOrderBalancePaymentsByOrderId(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderBalancePayment WHERE orderId=? ORDER BY inc", id);
        ArrayList<OrderBalancePayment> orderBalancePayments = new ArrayList<>();
        while (rst.next()){
            OrderBalancePayment orderBalancePayment = new OrderBalancePayment(rst.getInt(1), rst.getDate(2), rst.getBigDecimal(3), rst.getString(4));
            orderBalancePayments.add(orderBalancePayment);
        }
        return orderBalancePayments;
    }

    ///////////////checked upto here
    
}
