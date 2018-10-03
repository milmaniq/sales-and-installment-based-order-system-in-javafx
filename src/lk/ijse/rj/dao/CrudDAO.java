/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao;

import java.util.ArrayList;

/**
 *
 * @author Ilman Iqbal
 */
public interface CrudDAO<T, XID> extends SuperDAO {
    public boolean insert(T entity) throws Exception;
    
    public boolean update(T entity) throws Exception;
    
    public boolean delete(XID id) throws Exception;
    
    public T find(XID id) throws Exception;
    
    public ArrayList<T> getAll() throws Exception;
}
