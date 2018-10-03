/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.SaleItemDAO;
import lk.ijse.rj.entity.Sale;
import lk.ijse.rj.entity.SaleItem;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleItemDAOImpl implements SaleItemDAO{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(SaleItem entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SaleItem VALUES(?,?,?,?)", entity.getItemId(), 
                entity.getDescription(), entity.getPrice(), entity.getSaleId());
    }

    @Override
    public boolean update(SaleItem entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE SaleItem SET description=?, price=?, saleId=? "
                + "WHERE itemId=?", entity.getDescription(), entity.getPrice(),
                entity.getSaleId(), entity.getItemId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM SaleItem WHERE itemId=?", id);
    }

    @Override
    public SaleItem find(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SaleItem WHERE itemId=?", id);
        SaleItem saleItem = null;
        if (rst.next()){
            saleItem = new SaleItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3),
                    rst.getString(4));
        }
        return saleItem;
        
    }
    
    @Override
    public ArrayList<SaleItem> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SaleItem");
        ArrayList<SaleItem> saleItems = new ArrayList<>();
        while (rst.next()){
            SaleItem saleItem = new SaleItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3),
                    rst.getString(4));
            saleItems.add(saleItem);
        }
        return saleItems;
    }



    @Override
    public ArrayList<SaleItem> getSaleItemBySaleId(String id) throws Exception {
        
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SaleItem WHERE saleId=?", id);
        
        ArrayList<SaleItem> saleItems = new ArrayList<>();
        System.out.println(id);
        while(rst.next()){
            SaleItem saleItem = new SaleItem(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), 
                    rst.getString(4));
            saleItems.add(saleItem);
            
        }
        return saleItems;
    }
    
    //////////////////checked upto here


    
    
}
