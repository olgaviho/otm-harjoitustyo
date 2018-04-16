/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudies.dao;

import mystudies.domain.Course;
import java.sql.*;
import java.util.List;


/**
 *
 * @author olgaviho
 */

public class DatabaseCourseUserDao  {
    
     private Database database;
     
     public DatabaseCourseUserDao(Database database) {
        this.database = database;
    }
     
    public boolean findOne(Integer userkey, Integer coursekey) throws SQLException {
    
        Connection conn = database.getConnection();               
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersandcourses WHERE userid = ? and courseid = ?");        
        stmt.setInt(1, userkey);
        stmt.setInt(2, coursekey);        
        ResultSet rs = stmt.executeQuery();
        
        boolean hasOne = rs.next();
        if (!hasOne) {
            return false;
        } 
        
        return true;
        
    }
    
    public List<Course> findAll() throws SQLException {
        return null; 
    }
    
    public Course save(Integer userkey, Integer courseKey) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usersandcourses " + "(userid, courseid) " + "VALUES (?, ?)");
        stmt.setInt(1, userkey);
        stmt.setInt(2, courseKey);           

        stmt.executeUpdate();

        stmt.close();
        conn.close();
        return null;
        
    }
    
    public void delete(Integer key) throws SQLException {
        
 
    }
    
    
}