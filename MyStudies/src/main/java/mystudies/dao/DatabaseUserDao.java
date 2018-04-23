/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudies.dao;

import java.sql.*;
import java.util.List;
import mystudies.domain.User;

/**
 *
 * @author olgaviho
 */

public class DatabaseUserDao implements Dao<User, Integer> {
    
    private Database database;

    public DatabaseUserDao(Database database) {
        this.database = database;
    }

    @Override
    public User findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            
            return null;
        }       
        User user = new User(rs.getInt("id"), rs.getString("name"));
        stmt.close();
        rs.close();
        conn.close();
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User save(User user) throws SQLException {
        Connection conn = database.getConnection();
        int userId = user.getId();
        String name = user.getName();       
        User newUser = findOne(userId);
        
        if (newUser == null) {
            

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users " + "(id, name) " + "VALUES (?, ?)");
            stmt.setInt(1, userId);           
            stmt.setString(2, name);       
            stmt.executeUpdate();
            stmt.close();
            
        } else {
            
            System.out.println("error");
        }
        
        conn.close();       
        return null;
        
    }

    @Override
    public void delete(Integer key) throws SQLException {
//        lisää liitostaulun deletointi jonnekin
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
    }
    
}
