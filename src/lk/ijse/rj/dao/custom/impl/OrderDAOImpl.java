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
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderDAOImpl implements OrderDAO {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean insert(Order entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", entity.getOrderId(),
                entity.getName(), entity.getAddress(), entity.getContact(), entity.getDateOfOrder(), 
                entity.getDateOfDelivery(),entity.getTotal(), entity.getBuyGold(), entity.getAdvance(), 
                entity.getNetTotal(), entity.getBalance(), entity.getDateOfActualDelivery(), 
                entity.getDateOfCancellation(), entity.getEmployeeId());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE `Order` SET name=?, address=?, contact=?, "
                + "dateOfOrder=?, dateOfDelivery=?, total=?, buyGold=?, advance=?, netTotal=?, balance=?, "
                + "dateOfActualDelivery=?, dateOfCancellation=?, employeeId=? WHERE orderId=?", entity.getName(), 
                entity.getAddress(), entity.getContact(), entity.getDateOfOrder(), entity.getDateOfDelivery(), 
                entity.getTotal(), entity.getBuyGold(), entity.getAdvance(), entity.getNetTotal(), entity.getBalance(), 
                entity.getDateOfActualDelivery(), entity.getDateOfCancellation(), entity.getEmployeeId(), 
                entity.getOrderId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM `Order` WHERE orderId=?", id);
    }

    @Override
    public Order find(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE orderId=?", id);
        Order order = null;
        if (rst.next()) {
            order = new Order(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDate(5),
                    rst.getDate(6), rst.getBigDecimal(7), rst.getBigDecimal(8), rst.getBigDecimal(9),
                    rst.getBigDecimal(10), rst.getBigDecimal(11),
                    rst.getDate(12), rst.getDate(13), rst.getString(14));
        }
        return order;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` ORDER BY dateOfOrder DESC");
        return getArrayList(rst);
    }


    private ArrayList<Order> getArrayList(ResultSet rst) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        while (rst.next()) {
            Order order = new Order(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), 
                    rst.getDate(5), rst.getDate(6), rst.getBigDecimal(7), rst.getBigDecimal(8), rst.getBigDecimal(9),
                    rst.getBigDecimal(10), rst.getBigDecimal(11),
                    rst.getDate(12), rst.getDate(13), rst.getString(14));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public ArrayList<Order> getOrderByOrderId(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE orderId LIKE ? ORDER BY dateOfOrder DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Order> getOrderByName(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE name LIKE ? ORDER BY dateOfOrder DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Order> getOrderByContact(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE contact LIKE ? ORDER BY dateOfOrder DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Order> getOrderByDateOfOrder(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE dateOfOrder LIKE ? ORDER BY dateOfOrder DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Order> getOrderByDateOfDelivery(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE dateOfDelivery LIKE ? ORDER BY dateOfOrder DESC", id);
        return getArrayList(rst);
    }
    
    @Override
    public boolean updateBalanceByOrderId(Order entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE `Order` SET balance = ? WHERE orderId = ?", entity.getBalance(), 
                entity.getOrderId());
    }
    



    @Override
    public boolean updateDateOfActualDeliveryByOrderId(Order order) throws Exception {
        return CrudUtil.executeUpdate("UPDATE `Order` SET dateOfActualDelivery=? WHERE orderId=?", 
                order.getDateOfActualDelivery(), order.getOrderId());
    }

    ////////checked upto here

    

}
