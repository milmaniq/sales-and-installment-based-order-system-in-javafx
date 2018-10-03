/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.OrderItemDAO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderItemDAOImpl implements OrderItemDAO{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(OrderItem entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO OrderItem VALUES(?,?,?,?)", entity.getItemId(), 
                entity.getDescription(), entity.getPrice(), entity.getOrderId());
    }

    @Override
    public boolean update(OrderItem entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE OrderItem SET description=?, price=?, orderId=? WHERE itemId=?", 
                entity.getDescription(), entity.getPrice(), entity.getOrderId(), entity.getItemId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM OrderItem WHERE itemId=?", id);
    }


    @Override
    public OrderItem find(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderItem WHERE itemId=?", id);
        OrderItem orderItem = null;
        if (rst.next()){
            orderItem = new OrderItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getString(4));
        }
        return orderItem;
    }

    @Override
    public ArrayList<OrderItem> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderItem");
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        while (rst.next()){
            OrderItem orderItem = new OrderItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getString(4));
            orderItems.add(orderItem);
        }
        return orderItems;
    }
    

    @Override
    public ArrayList<OrderItem> getOrderItemsByOrderId(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM OrderItem WHERE orderId=?", id);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        while (rst.next()) {
            OrderItem orderItem = new OrderItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), 
                    rst.getString(4));
            orderItems.add(orderItem);
        }
        return orderItems;
    }
    
///////////checked upto here
}
