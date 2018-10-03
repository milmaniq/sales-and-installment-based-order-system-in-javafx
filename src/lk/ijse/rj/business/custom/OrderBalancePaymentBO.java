/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom;

import java.util.ArrayList;
import lk.ijse.rj.business.SuperBO;
import lk.ijse.rj.dto.OrderBalancePaymentDTO;

/**
 *
 * @author Ilman Iqbal
 */
public interface OrderBalancePaymentBO extends SuperBO {
    public boolean insertOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception;
    
    public boolean updateOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception;
    
    public boolean deleteOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception;
    
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePayments() throws Exception;
    
    public OrderBalancePaymentDTO findOrderBalancePayment(String id) throws Exception;
    
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePaymentByOrderId(String id) throws Exception;
    
}
