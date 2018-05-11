
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseDao;
import mystudies.domain.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseCourseDaoTest {
    
    Database database;
    DatabaseCourseDao courseDao;
    Course course;
    Course course2;

    
    @Before
    public void setUp() throws Exception {
        database = new Database("jdbc:sqlite:mystudiestest.db");        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer)");
        stmt.execute();        
        courseDao = new DatabaseCourseDao(database);
        course = new Course(1234, "nimi", "description", 10);
        course2 = new Course(1235, "nimi2", "description2", 10);  
    }
    
    @Test
    public void returnsNullWhenCantFindCourse() throws SQLException {                  
       assertEquals(null,courseDao.findOne(678));
    }
     
    @Test
    public void itIsPossibleToSaveAndFindCourses() throws SQLException {
        courseDao.save(course);
        assertEquals("nimi",courseDao.findOne(course.getId()).getName());       
    }
        
    
    @Test
    public void findAllCoursesFindsAllCourses() throws SQLException {
        courseDao.save(course2);
        courseDao.save(course);
        List<Course> twoCourses = courseDao.findAll();
        assertEquals(2,twoCourses.size());        
    }
        
    @After
    public void tearDown() throws Exception {
        Connection conn = database.getConnection();  
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE courses");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}
