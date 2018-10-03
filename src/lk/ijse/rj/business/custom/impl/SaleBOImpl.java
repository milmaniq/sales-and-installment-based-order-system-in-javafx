/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.rj.business.custom.SaleBO;
import lk.ijse.rj.dao.DAOFactory;
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.dao.custom.SaleDAO;
import lk.ijse.rj.dao.custom.SaleItemDAO;
import lk.ijse.rj.db.DBConnection;
import lk.ijse.rj.dto.SaleDTO;
import lk.ijse.rj.dto.SaleItemDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.Sale;
import lk.ijse.rj.entity.SaleItem;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleBOImpl implements SaleBO {

    private SaleDAO saleDAO;
    private OrderDAO orderDAO;
    private SaleItemDAO saleItemDAO;

    public SaleBOImpl() {
        saleDAO = (SaleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SALE);
        orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        saleItemDAO = (SaleItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SALE_ITEM);
    }
    
    
    
    @Override
    public boolean insertSale(SaleDTO dto) throws Exception {
        Sale sale = new Sale(dto.getSalesId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfSales(),
                dto.getTotal(), dto.getBuyGold(), dto.getNetTotal(), dto.getDateOfCancellation(), dto.getEmployeeId(), 
                dto.getOrderId());
        
        Connection connection = DBConnection.getInstance().getConnection();
        
        try {
            connection.setAutoCommit(false);
            
            boolean t1 = saleDAO.insert(sale);
            if (!t1) {
                connection.rollback();
                return false;               
            }
            
            ArrayList<SaleItemDTO> saleItemDTOs = dto.getSaleItems();
            for (SaleItemDTO saleItemDTO : saleItemDTOs) {
                SaleItem saleItem = new SaleItem(saleItemDTO.getItemId(), saleItemDTO.getDescription(), 
                        saleItemDTO.getPrice(), dto.getSalesId());
                
                boolean t2 = saleItemDAO.insert(saleItem);
                if (!t2) {
                    connection.rollback();
                    return false;
                }
            }
            
            if (dto.getOrderId() != null) {
                Order order = new Order(dto.getOrderId(), dto.getDateOfSales());
                
                boolean t3 = orderDAO.updateDateOfActualDeliveryByOrderId(order);
                if (!t3) {
                    connection.rollback();
                    return false;           
                }
            }
            
            connection.commit();
            return true;
            
        } catch (Exception e) {
            connection.rollback();
            throw e;
            
        } finally {
            connection.setAutoCommit(true);
        }
        
    }
    
    @Override
    public ArrayList<SaleItemDTO> getSaleItemBySaleId(String id) throws Exception {
        ArrayList<SaleItem> saleItems = saleItemDAO.getSaleItemBySaleId(id);
        ArrayList<SaleItemDTO> saleItemDTOs = new ArrayList<>();
        for (SaleItem saleItem : saleItems) {
            SaleItemDTO saleItemDTO = new SaleItemDTO(saleItem.getItemId(), saleItem.getDescription(), 
                    saleItem.getPrice());
            saleItemDTOs.add(saleItemDTO);
        }
        return saleItemDTOs;
    }

    @Override
    public boolean updateSale(SaleDTO dto) throws Exception {
        Sale sale = new Sale(dto.getSalesId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfSales(),
                dto.getTotal(), dto.getBuyGold(), dto.getNetTotal(), dto.getDateOfCancellation(), 
                dto.getEmployeeId(), dto.getOrderId());
        
        Connection connection = DBConnection.getInstance().getConnection();
        
        try {
            connection.setAutoCommit(false);
            
            boolean t1 = saleDAO.update(sale);
            if (!t1) {
                connection.rollback();
                return false;               
            }
            
            ArrayList<SaleItemDTO> saleItemDTOs = dto.getSaleItems();
            
            for (SaleItemDTO saleItemDTO : saleItemDTOs) {
                SaleItem saleItem = new SaleItem(saleItemDTO.getItemId(), saleItemDTO.getDescription(), 
                        saleItemDTO.getPrice(), dto.getSalesId());
                
                boolean t2 = saleItemDAO.update(saleItem);
                
                if (!t2) {
                    connection.rollback();
                    return false;
                    
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        
    }
    
     private ArrayList<SaleDTO> getArrayList(ArrayList<Sale> allSales)throws Exception{
        ArrayList<SaleDTO> allSaleDTOs = new ArrayList<>();
        for (Sale sale : allSales) {
            SaleDTO saleDTO = new SaleDTO(sale.getSaleId(), sale.getEmployeeId(), sale.getOrderId(), sale.getName(), 
                    sale.getContact(), sale.getDateOfSale(), sale.getAddress(), sale.getTotal(), sale.getBuyGold(), 
                    sale.getNetTotal(), sale.getDateOfCancellation());
            allSaleDTOs.add(saleDTO);
        }
        return allSaleDTOs;
    }
     
     @Override
    public ArrayList<SaleDTO> getSaleBySaleId(String id) throws Exception {
        ArrayList<Sale> sales = saleDAO.getSaleBySaleId(id);
        return getArrayList(sales);
    }

    @Override
    public ArrayList<SaleDTO> getSaleByName(String id) throws Exception {
        ArrayList<Sale> sales = saleDAO.getSaleByName(id);
        return getArrayList(sales);
    }

    @Override
    public ArrayList<SaleDTO> getSaleByContact(String id) throws Exception {
        ArrayList<Sale> sales = saleDAO.getSaleByContact(id);
        return getArrayList(sales);
    }

    @Override
    public ArrayList<SaleDTO> getSaleByDateOfSale(String id) throws Exception {
        ArrayList<Sale> sales = saleDAO.getSaleByDateOfSale(id);
        return getArrayList(sales);
    }

    @Override
    public ArrayList<SaleDTO> getSaleByOrderId(String id) throws Exception {
        ArrayList<Sale> sales = saleDAO.getSaleByOrderId(id);
        return getArrayList(sales);
    }
    
    ///////////////////checked upto here

    @Override
    public boolean deleteSale(String id) throws Exception {
        return saleDAO.delete(id);
    }

    

    @Override
    public SaleDTO findSale(String id) throws Exception {
        Sale sale = saleDAO.find(id);
        SaleDTO saleDTO = new SaleDTO(sale.getSaleId(), sale.getEmployeeId(), sale.getOrderId(), sale.getName(), 
                    sale.getContact(), sale.getDateOfSale(), sale.getAddress(), sale.getTotal(), sale.getBuyGold(), 
                    sale.getNetTotal(), sale.getDateOfCancellation());
        return saleDTO;
        
    }
    
   
    
    @Override
    public ArrayList<SaleDTO> getAllSales() throws Exception {
        ArrayList<Sale> all = saleDAO.getAll();
        return getArrayList(all);
    }

    

    

    
}
