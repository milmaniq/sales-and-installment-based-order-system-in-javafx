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
import lk.ijse.rj.dao.custom.StockDAO;
import lk.ijse.rj.entity.Stock;

/**
 *
 * @author Ilman Iqbal
 */
public class StockDAOImpl implements StockDAO {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean insert(Stock entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Stock VALUES (?,?,?,?,?)", entity.getItemId(),
                entity.getDescription(), entity.getWeight(), entity.getRate(), entity.getDateAdded());
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Stock SET description=?, weight=?, rate=?, dateAdded=? WHERE itemId=?",
                entity.getDescription(), entity.getWeight(), entity.getRate(), entity.getDateAdded(), entity.getItemId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Stock WHERE itemId=?", id);
    }

    @Override
    public Stock find(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Stock WHERE itemId=?", id);
        Stock stock = null;
        if (rst.next()) {
            stock = new Stock(rst.getString(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4), rst.getDate(5));
        }
        return stock;
    }
    
    @Override
    public ArrayList<Stock> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Stock");
        ArrayList<Stock> stocks = new ArrayList<>();
        while (rst.next()) {
            Stock stock = new Stock(rst.getString(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4), rst.getDate(5));
            stocks.add(stock);
        }
        return stocks;
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
