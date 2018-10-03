/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.dao.DAOFactory;
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.dao.custom.OrderItemDAO;
import lk.ijse.rj.dao.custom.QueryDAO;
import lk.ijse.rj.db.DBConnection;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;

/**
 *
 * @author Ilman Iqbal
 */
public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private QueryDAO queryDAO;

    public OrderBOImpl() {
        this.orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        this.orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_ITEM);
        this.queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    private ArrayList<OrderDTO> getArrayList(ArrayList<Order> allOrders) throws Exception {
        ArrayList<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order allOrder : allOrders) {
            OrderDTO orderDTO = new OrderDTO(allOrder.getOrderId(), allOrder.getEmployeeId(), allOrder.getName(),
                    allOrder.getAddress(), allOrder.getContact(), allOrder.getDateOfOrder(), 
                    allOrder.getDateOfDelivery(), allOrder.getTotal(),
                    allOrder.getBuyGold(), allOrder.getAdvance(), allOrder.getNetTotal(), allOrder.getBalance(),
                    allOrder.getDateOfActualDelivery(), allOrder.getDateOfCancellation());

            orderDTOs.add(orderDTO);

        }
        return orderDTOs;
    }

    
    
    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {

        ArrayList<Order> allOrders = orderDAO.getAll();
        return getArrayList(allOrders);
    }

    @Override
    public boolean insertOrder(OrderDTO dto) throws Exception {
        Order order = new Order(dto.getOrderId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfOrder(),
                dto.getDateOfDelivery(), dto.getTotal(), dto.getBuyGold(), dto.getAdvance(), dto.getNetTotal(),
                dto.getBalance(), dto.getDateOfActualDelivery(), dto.getDateOfCancellation(), dto.getEmployeeId());

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean t1 = orderDAO.insert(order);
            if (!t1) {
                connection.rollback();
                return false;
            }

            ArrayList<OrderItemDTO> orderItemDTOs = dto.getOrderItems();
            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                OrderItem orderItem = new OrderItem(orderItemDTO.getItemId(), orderItemDTO.getDescription(),
                        orderItemDTO.getPrice(), dto.getOrderId());

                boolean t2 = orderItemDAO.insert(orderItem);
                if (!t2) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    
    
    @Override
    public ArrayList<OrderDTO> getOrderByOrderId(String id) throws Exception {
        ArrayList<Order> orders = orderDAO.getOrderByOrderId(id);
        return getArrayList(orders);
    }

    @Override
    public ArrayList<OrderDTO> getOrderByName(String id) throws Exception {
        ArrayList<Order> orders = orderDAO.getOrderByName(id);
        return getArrayList(orders);

    }

    @Override
    public ArrayList<OrderDTO> getOrderByContact(String id) throws Exception {
        ArrayList<Order> orders = orderDAO.getOrderByContact(id);
        return getArrayList(orders);
    }

    @Override
    public ArrayList<OrderDTO> getOrderByDateOfOrder(String id) throws Exception {
        ArrayList<Order> orders = orderDAO.getOrderByDateOfOrder(id);
        return getArrayList(orders);
    }

    @Override
    public ArrayList<OrderDTO> getOrderByDateOfDelivery(String id) throws Exception {
        ArrayList<Order> orders = orderDAO.getOrderByDateOfDelivery(id);
        return getArrayList(orders);
    }
    
    

    @Override
    public OrderDTO findOrder(String id) throws Exception {
        Order order = orderDAO.find(id);
        OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getEmployeeId(), order.getName(), order.getAddress(),
                order.getContact(), order.getDateOfOrder(), order.getDateOfDelivery(), order.getTotal(), 
                order.getBuyGold(), order.getAdvance(), order.getNetTotal(), order.getBalance(), 
                order.getDateOfActualDelivery(), order.getDateOfCancellation());
        return orderDTO;
    }
    
    

    @Override
    public ArrayList<OrderItemDTO> getOrderItemsByOrderId(String id) throws Exception {

        ArrayList<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(id);
        ArrayList<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem.getItemId(), orderItem.getDescription(), 
                    orderItem.getPrice());
            orderItemDTOs.add(orderItemDTO);
        }
        return orderItemDTOs;
    }
    
    

    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {
        Order order = new Order(dto.getOrderId(), dto.getName(), dto.getAddress(), dto.getContact(),
                dto.getDateOfOrder(), dto.getDateOfDelivery(), dto.getTotal(), dto.getBuyGold(), dto.getAdvance(),
                dto.getNetTotal(), dto.getBalance(), dto.getDateOfActualDelivery(), dto.getDateOfCancellation(), 
                dto.getEmployeeId());

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean t1 = orderDAO.update(order);
            if (!t1) {
                connection.rollback();
                return false;
            }

            ArrayList<OrderItemDTO> orderItemDTOs = dto.getOrderItems();
            
            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                OrderItem orderItem = new OrderItem(orderItemDTO.getItemId(), orderItemDTO.getDescription(),
                        orderItemDTO.getPrice(), dto.getOrderId());
                boolean t2 = orderItemDAO.update(orderItem);

                if (!t2) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }

    }
    
    /////////////// checked upto here
}
