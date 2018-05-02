
package mystudies.dao;

import mystudies.domain.Course;
import java.sql.*;
import java.util.List;

/**
 * This class saves the courses to the database
 *
 * @author olgaviho
 */


public class DatabaseCourseDao implements Dao<Course, Integer> {
    
    private Database database;
    
    
    public DatabaseCourseDao(Database database) {
        this.database = database;
    
    }

    
    /**
 * This method checks if the course already is in the database
 *
 * @param key the id of the course
 * 
 * @return course, if it exists, otherwise null
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
        stmt.close();
        rs.close();
        conn.close();
        
        return course;
     
    }
/**
 * This method does not work yet
 *
 * 
 * 
 * @return null
 */
    @Override
    public List<Course> findAll() throws SQLException {
        return null; 
    }
    
    
    /**
 * This method checks if the course already is in the database, then it updates or saves the course
 *
 * @param course the course
 * 
 * @return course
 */
    @Override
    public Course save(Course course) throws SQLException {
        Connection conn = database.getConnection();
        int courseId = course.getId();
        String description = course.getDescription();      
        Course newCourse = findOne(courseId);        
        if (newCourse == null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses " + "(courseid, name, description, credits) " + "VALUES (?, ?, ?, ?)");
            stmt.setInt(1, courseId);
            stmt.setString(2, course.getName());           
            stmt.setString(3, description); 
            stmt.setInt(4, course.getCredits());
            stmt.executeUpdate();
            stmt.close();
            Course newCourse2 = findOne(courseId);
            conn.close();
            return newCourse2;
        }
        
        conn.close();
        return newCourse;
    }

    /**
 * This method deletes the course
 *
 * @param key course id
 * 
 */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM courses WHERE courseid = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
    }

}
