
package mystudies.dao;

import mystudies.domain.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class saves courses to the database.
 *
 * @author olgaviho
 */


public class DatabaseCourseDao implements Dao<Course, Integer> {
    
    private Database database;
    
    /**
     * This creates the course DAO.
     * 
     * @param database database that will used
     */
    
        
    public DatabaseCourseDao(Database database) {
        this.database = database;
    
    }

    
    /**
     * This method checks if the course already is in the database.
     *
     * @param key the id of the course 
     * @return course, if it exists, otherwise null
     * 
     * @throws java.sql.SQLException if there is a problem in the database
     * 
     */

    @Override
    public Course findOne(Integer key) throws SQLException {
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses WHERE courseid = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }      
        
        Course course = new Course(rs.getInt("courseid"), rs.getString("name"), rs.getString("description"), rs.getInt("credits"));
        rs.close();
        stmt.close();
        
        conn.close();
        
        return course;
     
    }
    
    /**
    * This method returns a list, where are all courses, that are in the database.
    *
    * @return courses in a list
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    @Override
    public List<Course> findAll() throws SQLException {
        
        List<Course> allCourses = new ArrayList<>();        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses");
        ArrayList<Integer> grades = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {            
            Course course = new Course(rs.getInt("courseid"), rs.getString("name"), rs.getString("description"), rs.getInt("credits"));
            allCourses.add(course);
        }
            
        rs.close();
        stmt.close();        
        conn.close();        
        return allCourses; 
    }
    
    
    /**
    * This method saves the course.
    *
    * @param course that course will be saved
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    @Override
    public void save(Course course) throws SQLException {
        Connection conn = database.getConnection();
        int courseId = course.getId();
        String description = course.getDescription();      
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses " + "(courseid, name, description, credits) " + "VALUES (?, ?, ?, ?)");
        stmt.setInt(1, courseId);
        stmt.setString(2, course.getName());           
        stmt.setString(3, description); 
        stmt.setInt(4, course.getCredits());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    
}
