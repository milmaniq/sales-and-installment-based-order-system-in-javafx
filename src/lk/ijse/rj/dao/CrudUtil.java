/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.rj.db.DBConnection;

/**
 *
 * @author Ilman Iqbal
 */
public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object... params) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i+1, params[i]);
        }
        
        return pstm;
    }
    
    public static ResultSet executeQuery(String sql, Object... params) throws ClassNotFoundException, SQLException{
        ResultSet rst = getPreparedStatement(sql, params).executeQuery();
        
        return rst;
    }
    
    public static boolean executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException{
        boolean value = getPreparedStatement(sql, params).executeUpdate()>0;
        return value;
    }
}
