
package mystudies.dao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


/**
 * This class saves user's ids and course's ids.
 *
 * @author olgaviho
 */


public class DatabaseCourseUserDao  {
    private final Database database;
    
     /**
     * This creates the CourseUserDao.
     * 
     * @param database database that will be used
     */
     
    public DatabaseCourseUserDao(Database database) {
        this.database = database;
    }
    
    /**
     * This method checks if the ids already are in the database.
     *
     * @param userkey the id of the user
     * @param coursekey the id of the course
     * @return true, if the combination already is in the database, else false
     * 
     * @throws java.sql.SQLException if there is a problem in the database
     * 
     */
     
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
        
        return hasOne;        
    }
    
    /**
    * This method returns a list, where are all courses, that the user has.
    *
    * @param userkey id of the user
    * @return courses in a list
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    public List<Integer> findAllIds(Integer userkey) throws SQLException {
        
        Connection conn = database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersandcourses WHERE userid = ?"); 
        stmt.setInt(1, userkey);
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Integer> ids = new ArrayList<>();

        while (rs.next()) {

            int id =  rs.getInt("courseid");
            ids.add(id);
        }        
        rs.close();
        stmt.close();        
        conn.close();       
        return ids; 
    }
    
    /**
    * This method returns a list, where are all grades, that the user has.
    *
    * @param userkey id of the user
    * @return grades in a list
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    public List<Integer> findAllGrades(Integer userkey) throws SQLException {
        Connection conn = database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersandcourses WHERE userid = ?"); 
        stmt.setInt(1, userkey);
        ResultSet rs = stmt.executeQuery();        
        ArrayList<Integer> grades = new ArrayList<>();

        while (rs.next()) {
            int grade =  rs.getInt("grade");
            grades.add(grade);
        }
        
        rs.close();
        stmt.close();        
        conn.close();        
        return grades;         
    }
    
    /**
    * This method saves the combination.
    *
     * @param userkey id for the user
     * @param courseKey id for the course
     * @param grade grade of the course
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    public void save(Integer userkey, Integer courseKey, Integer grade) throws SQLException {
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usersandcourses " + "(userid, courseid, grade) " + "VALUES (?, ?, ?)");
        stmt.setInt(1, userkey);
        stmt.setInt(2, courseKey);
        stmt.setInt(3, grade);
        stmt.executeUpdate();
        stmt.close();
        conn.close();       
    }
    
    /**
    * This method deletes a combination.
    *
    * @param coursekey the id of the course
    * @param userid the id of the user
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
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