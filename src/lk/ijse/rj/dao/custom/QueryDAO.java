/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import lk.ijse.rj.dao.SuperDAO;
import lk.ijse.rj.entity.Stock;

/**
 *
 * @author Ilman Iqbal
 */
public interface QueryDAO extends SuperDAO {

    public ArrayList<Stock> getAvailableStockDetail() throws Exception;
}
