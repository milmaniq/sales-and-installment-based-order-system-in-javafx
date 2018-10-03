/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;

/**
 *
 * @author Ilman Iqbal
 */
public interface OrderDAO extends CrudDAO<Order, String> {

    public ArrayList<Order> getOrderByOrderId(String id) throws Exception;
    
    public ArrayList<Order> getOrderByName(String id) throws Exception;
    
    public ArrayList<Order> getOrderByContact(String id) throws Exception;
    
    public ArrayList<Order> getOrderByDateOfOrder(String id) throws Exception;
    
    public ArrayList<Order> getOrderByDateOfDelivery(String id) throws Exception;
    
    public boolean updateBalanceByOrderId(Order entity) throws Exception;

    public boolean updateDateOfActualDeliveryByOrderId(Order order) throws Exception;
    
    
}
