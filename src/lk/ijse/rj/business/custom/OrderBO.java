/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom;

import java.util.ArrayList;
import lk.ijse.rj.business.SuperBO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;

/**
 *
 * @author Ilman Iqbal
 */
public interface OrderBO extends SuperBO {
    
    public boolean insertOrder(OrderDTO dto) throws Exception;
    
    public boolean updateOrder(OrderDTO dto) throws Exception;
    
    public ArrayList<OrderDTO> getAllOrders() throws Exception;
    
    public OrderDTO findOrder(String id) throws Exception;
    
    public ArrayList<OrderDTO> getOrderByOrderId(String id) throws Exception;
    
    public ArrayList<OrderDTO> getOrderByName(String id) throws Exception;
    
    public ArrayList<OrderDTO> getOrderByContact(String id) throws Exception;
    
    public ArrayList<OrderDTO> getOrderByDateOfOrder(String id) throws Exception;
    
    public ArrayList<OrderDTO> getOrderByDateOfDelivery(String id) throws Exception;
    
    public ArrayList<OrderItemDTO> getOrderItemsByOrderId(String id) throws Exception;
    
}

