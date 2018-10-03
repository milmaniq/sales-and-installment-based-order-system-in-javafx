/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.SaleItem;

/**
 *
 * @author Ilman Iqbal
 */
public interface SaleItemDAO extends CrudDAO<SaleItem, String> {
    public ArrayList<SaleItem> getSaleItemBySaleId(String id) throws Exception;
}
