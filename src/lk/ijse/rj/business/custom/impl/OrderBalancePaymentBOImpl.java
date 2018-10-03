/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.rj.business.custom.OrderBalancePaymentBO;
import lk.ijse.rj.dao.DAOFactory;
import lk.ijse.rj.dao.custom.OrderBalancePaymentDAO;
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.db.DBConnection;
import lk.ijse.rj.dto.OrderBalancePaymentDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderBalancePayment;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderBalancePaymentBOImpl implements OrderBalancePaymentBO {

    private OrderBalancePaymentDAO orderBalancePaymentDAO;
    private OrderDAO orderDAO;

    public OrderBalancePaymentBOImpl() {
        orderBalancePaymentDAO = (OrderBalancePaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_BALANCE_PAYMENT);
        orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    }

    @Override
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePaymentByOrderId(String id) throws Exception {
        ArrayList<OrderBalancePayment> orderBalancePayments = orderBalancePaymentDAO.getAllOrderBalancePaymentsByOrderId(id);
        ArrayList<OrderBalancePaymentDTO> orderBalancePaymentDTOs = new ArrayList<>();
        for (OrderBalancePayment orderBalancePayment : orderBalancePayments) {
            OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(orderBalancePayment.getInc(),
                    orderBalancePayment.getOrderId(), orderBalancePayment.getDate(), orderBalancePayment.getAmount());
            orderBalancePaymentDTOs.add(orderBalancePaymentDTO);
        }
        return orderBalancePaymentDTOs;
    }

    @Override
    public boolean insertOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {
        OrderBalancePayment orderBalancePayment = new OrderBalancePayment(dto.getDateOfPayment(), dto.getAmount(),
                dto.getOrderId());
        
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            
            boolean t1 = orderBalancePaymentDAO.insert(orderBalancePayment);

            if (!t1) {
                connection.rollback();
                return false;
            }
            
            Order order1 = orderDAO.find(dto.getOrderId());
            
            BigDecimal balance = order1.getBalance();
            balance = balance.subtract(dto.getAmount());
            
            Order order2 = new Order(dto.getOrderId(), balance);
            
            boolean t2 = orderDAO.updateBalanceByOrderId(order2);
            
            if (!t2) {
                connection.rollback();
                return false;
            }
            
            connection.commit();
            return true;
        } 
        catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally{
            connection.setAutoCommit(true);
        }

    }
    
    @Override
    public boolean deleteOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        
        try {
            connection.setAutoCommit(false);
            
            boolean t1 = orderBalancePaymentDAO.delete(Integer.toString(dto.getInc()));
            
            if (!t1) {
                connection.rollback();
                return false;
            }
            
            Order order1 = orderDAO.find(dto.getOrderId());
            
            BigDecimal balance = order1.getBalance();
            balance = balance.add(dto.getAmount());
            
            Order order2 = new Order(dto.getOrderId(), balance);
            
            
            boolean t2 = orderDAO.updateBalanceByOrderId(order2);
            
            if (!t2) {
                connection.rollback();
                return false;
            }
            
            connection.commit();
            return true;
            
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(true);
        }
        
        
    }

    ////////////////////checked upto here
    @Override
    public boolean updateOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {
        OrderBalancePayment orderBalancePayment = new OrderBalancePayment(dto.getDateOfPayment(), dto.getAmount(), dto.getOrderId());
        return orderBalancePaymentDAO.update(orderBalancePayment);
    }

    

    @Override
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePayments() throws Exception {
        ArrayList<OrderBalancePayment> all = orderBalancePaymentDAO.getAll();
        ArrayList<OrderBalancePaymentDTO> allPayments = new ArrayList<>();
        for (OrderBalancePayment a : all) {
            OrderBalancePaymentDTO balancePaymentDTO = new OrderBalancePaymentDTO(a.getOrderId(), a.getDate(), a.getAmount());
            allPayments.add(balancePaymentDTO);
        }
        return allPayments;
    }

    @Override
    public OrderBalancePaymentDTO findOrderBalancePayment(String id) throws Exception {
        OrderBalancePayment bal = orderBalancePaymentDAO.find(id);
        OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(bal.getOrderId(), bal.getDate(), bal.getAmount());
        return orderBalancePaymentDTO;

    }

}
