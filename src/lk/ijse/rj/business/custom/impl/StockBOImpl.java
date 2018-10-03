/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dao.DAOFactory;
import lk.ijse.rj.dao.custom.QueryDAO;
import lk.ijse.rj.dao.custom.StockDAO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.entity.CustomEntity;
import lk.ijse.rj.entity.Stock;

/**
 *
 * @author Ilman Iqbal
 */
public class StockBOImpl implements StockBO{
    
    private StockDAO stockDAO;
    private QueryDAO queryDAO;

    public StockBOImpl() {
        stockDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
        queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }
    
    @Override
    public ArrayList<StockDTO> getAvailableStocks() throws Exception {
        ArrayList<Stock> stocks = queryDAO.getAvailableStockDetail();
        ArrayList<StockDTO> stockDTOs = new ArrayList<>();
        for (Stock stock : stocks) {
            StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(), 
                    stock.getWeight(), stock.getRate(), stock.getDateAdded());
            stockDTOs.add(stockDTO);
        }
        return stockDTOs;
    }
    

    
    @Override
    public StockDTO findStock(String id) throws Exception {
        
        Stock stock = stockDAO.find(id);
        if (stock == null) {
            return null;
        }
        StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(), stock.getWeight(), 
                stock.getRate(), stock.getDateAdded());
        return stockDTO;
    }

    
    ////////////checked upto here

    @Override
    public boolean insertStock(StockDTO dto) throws Exception {
        Stock stock = new Stock(dto.getItemId(), dto.getDescription(), dto.getWeight(), dto.getRate(), dto.getDateAdded());
        return stockDAO.insert(stock);
    }

    @Override
    public boolean updateStock(StockDTO dto) throws Exception {
        Stock stock = new Stock(dto.getItemId(), dto.getDescription(), dto.getWeight(), dto.getRate(), dto.getDateAdded());
        return stockDAO.update(stock);
    }

    @Override
    public boolean deleteStock(String id) throws Exception {
        return stockDAO.delete(id);
    }

    @Override
    public ArrayList<StockDTO> getAllStocks() throws Exception {
        ArrayList<Stock> all = stockDAO.getAll();
        ArrayList<StockDTO> allStocks = new ArrayList<>();
        for (Stock stock : all) {
            StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(), stock.getWeight(), stock.getRate(), stock.getDateAdded());
            allStocks.add(stockDTO);
        }
        return allStocks;
    }

    
    

   
}
