/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom;

import java.util.ArrayList;
import lk.ijse.rj.business.SuperBO;
import lk.ijse.rj.dto.StockDTO;

/**
 *
 * @author Ilman Iqbal
 */
public interface StockBO extends SuperBO{
    public boolean insertStock(StockDTO dto) throws Exception;
    
    public boolean updateStock(StockDTO dto) throws Exception;
    
    public boolean deleteStock(String id) throws Exception;
    
    public ArrayList<StockDTO> getAllStocks() throws Exception;
    
    public StockDTO findStock(String id) throws Exception;
    
    public ArrayList<StockDTO> getAvailableStocks() throws Exception;
 
}
