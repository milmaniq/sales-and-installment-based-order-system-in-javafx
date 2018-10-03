/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.rj.dao.CrudUtil;
import lk.ijse.rj.dao.custom.QueryDAO;
import lk.ijse.rj.entity.CustomEntity;
import lk.ijse.rj.entity.Stock;

/**
 *
 * @author Ilman Iqbal
 */
public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<Stock> getAvailableStockDetail() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT s.itemId, s.description, weight, rate, dateAdded\n"
                + "FROM stock s\n"
                + "LEFT JOIN saleitem si\n"
                + "ON s.itemId = si.itemId\n"
                + "LEFT JOIN orderitem oi\n"
                + "ON s.itemId = oi.itemId\n"
                + "WHERE si.itemId IS NULL AND oi.itemId IS NULL\n"
                + "ORDER BY dateAdded");

        ArrayList<Stock> stocks = new ArrayList<>();
        while (rst.next()) {
            Stock stock = new Stock(rst.getString(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4), rst.getDate(5));
            stocks.add(stock);
        }
        return stocks;
    }
}
    