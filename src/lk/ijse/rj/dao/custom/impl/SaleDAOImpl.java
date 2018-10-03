/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.SaleDAO;
import lk.ijse.rj.entity.Sale;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleDAOImpl implements SaleDAO {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(Sale entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Sale VALUES(?,?,?,?,?,?,?,?,?,?,?)", entity.getSaleId(),
                entity.getName(), entity.getAddress(), entity.getContact(), entity.getDateOfSale(),
                entity.getTotal(), entity.getBuyGold(), entity.getNetTotal(), entity.getDateOfCancellation(),
                entity.getEmployeeId(), entity.getOrderId());
    }

    @Override
    public boolean update(Sale entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Sale SET name=?, address=?, contact=?, dateOfSale=?, total=?, "
                + "buyGold=?, netTotal=?, dateOfCancellation=?, employeeId=?, orderId=? WHERE saleId=?", entity.getName(),
                entity.getAddress(),entity.getContact(), entity.getDateOfSale(), entity.getTotal(), entity.getBuyGold(),
                entity.getNetTotal(), entity.getDateOfCancellation(), entity.getEmployeeId(), entity.getOrderId(), 
                entity.getSaleId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Sale WHERE saleId=?", id);
    }

    
    @Override
    public Sale find(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE saleId=?", id);
        Sale sale = null;
        if (rst.next()){
            sale = new Sale(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDate(5), 
                    rst.getBigDecimal(6), rst.getBigDecimal(7), rst.getBigDecimal(8), rst.getDate(9),
                    rst.getString(10), rst.getString(11));
        }
        return sale;
    }
    
    @Override
    public ArrayList<Sale> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale ORDER BY dateOfSale DESC");
        return getArrayList(rst);
        
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private ArrayList<Sale> getArrayList(ResultSet rst) throws Exception{
        ArrayList<Sale> sales = new ArrayList<>();
        while (rst.next()){
            Sale sale = new Sale(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDate(5), 
                    rst.getBigDecimal(6), rst.getBigDecimal(7), rst.getBigDecimal(8),
                    rst.getDate(9), rst.getString(10), rst.getString(11));
            sales.add(sale);
        }
        return sales;
    }
    
    @Override
    public ArrayList<Sale> getSaleBySaleId(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE saleId LIKE ? ORDER BY dateOfSale DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Sale> getSaleByName(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE name LIKE ? ORDER BY dateOfSale DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Sale> getSaleByContact(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE contact LIKE ? ORDER BY dateOfSale DESC", id);
        return getArrayList(rst);
    }

    @Override
    public ArrayList<Sale> getSaleByDateOfSale(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE dateOfSale LIKE ? ORDER BY dateOfSale DESC", id);
        return getArrayList(rst);
    }
    
    @Override
    public ArrayList<Sale> getSaleByOrderId(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Sale WHERE orderId LIKE ? ORDER BY dateOfSale DESC", id);
        return getArrayList(rst);
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

}
