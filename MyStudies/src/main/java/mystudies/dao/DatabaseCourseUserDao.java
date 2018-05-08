
package mystudies.dao;

import mystudies.domain.Course;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;



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
        
        rs.close();
        stmt.close();
        conn.close();
        
        if (!hasOne) {
            
            return false;
        } 
        
        return true;
        
    }
    
    public List<Integer> findAllIds(Integer userkey) throws SQLException {
        Connection conn = database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersandcourses WHERE userid = ?"); 
        stmt.setInt(1, userkey);
        ResultSet rs = stmt.executeQuery();

        
        ArrayList<Integer> ids = new ArrayList<Integer>();

        while (rs.next()) {

            int id =  rs.getInt("courseid");
            ids.add(id);
        }
        
        rs.close();
        stmt.close();
        
        conn.close();
        
        return ids; 
    }
    
    public List<Integer> findAllGrades(Integer userkey) throws SQLException {
        Connection conn = database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersandcourses WHERE userid = ?"); 
        stmt.setInt(1, userkey);
        ResultSet rs = stmt.executeQuery();

        
        ArrayList<Integer> grades = new ArrayList<Integer>();

        while (rs.next()) {

            int grade =  rs.getInt("grade");
            grades.add(grade);
        }
        
        rs.close();
        stmt.close();
        
        conn.close();
        
        return grades; 
        
    }
    
    public Course save(Integer userkey, Integer courseKey, Integer grade) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usersandcourses " + "(userid, courseid, grade) " + "VALUES (?, ?, ?)");
        stmt.setInt(1, userkey);
        stmt.setInt(2, courseKey);
        stmt.setInt(3, grade);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
        return null;
        
    }
    
    public void deleteCourse(Integer coursekey, Integer userid) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM usersandcourses WHERE userid = ? AND courseid = ?");
        stmt.setInt(1, userid);
        stmt.setInt(2, coursekey);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    

}