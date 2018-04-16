
package mystudies.dao;

import mystudies.domain.Course;
import java.sql.*;
import java.util.List;




public class DatabaseCourseDao implements Dao<Course, Integer> {
    
    private Database database;
    
    
    public DatabaseCourseDao(Database database) {
        this.database = database;
    
    }

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
        
        Course course = new Course(rs.getInt("courseid"),rs.getString("name"),rs.getString("description"), rs.getInt("credits"));
        stmt.close();
        rs.close();
        conn.close();
        
        return course;
        
        
    }

    @Override
    public List<Course> findAll() throws SQLException {
        return null; 
    }

    @Override
    public Course saveOrUpdate(Course course) throws SQLException {
        Connection conn = database.getConnection();
        int courseId = course.getId();
        String name = course.getName();
        String description = course.getDescription(); 
        int credits = course.getCredits();
        
        Course newCourse = findOne(courseId);
        
        if (newCourse == null) {
        
        
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses " + "(courseid, name, description, credits) " + "VALUES (?, ?, ?, ?)");
            stmt.setInt(1,courseId);
            stmt.setString(2, name);           
            stmt.setString(3, description); 
            stmt.setInt(4, credits);
            
            stmt.executeUpdate();
                        
            
            stmt.close();

            
            Course newCourse2 = findOne(courseId);
            conn.close();
            
            return newCourse2;
            
            } else {
            
            System.out.println("course exists");

                    }
        
        conn.close();
        
        
        return newCourse;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        
 
    }


   
    
}
