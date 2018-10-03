/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.Sale;

/**
 *
 * @author Ilman Iqbal
 */
public interface SaleDAO extends CrudDAO<Sale, String> {
    public ArrayList<Sale> getSaleBySaleId(String id) throws Exception;
    
    public ArrayList<Sale> getSaleByName(String id) throws Exception;
    
    public ArrayList<Sale> getSaleByContact(String id) throws Exception;
    
    public ArrayList<Sale> getSaleByDateOfSale(String id) throws Exception;
    
    public ArrayList<Sale> getSaleByOrderId(String id) throws Exception;
}
