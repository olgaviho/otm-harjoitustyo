
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseDao;
import mystudies.domain.Course;
import mystudies.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseCourseDaoTest {
    
    Database database;
    DatabaseCourseDao courseDao;
    Course course;

    
    @Before
    public void setUp() throws Exception {
        Scanner reader = new Scanner(System.in);
        database = new Database("jdbc:sqlite:mystudiestest.db");        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer)");
        stmt.execute();        
        courseDao = new DatabaseCourseDao(database);
        course = new Course(1234, "nimi", "description", 10);
  
    }
    
    @Test
    public void tableCanBeEmpty() throws SQLException {                  
       assertEquals(null,courseDao.findOne(678));
    }
     
    @Test
    public void itIsPossibleToSaveCourses() throws SQLException {
        courseDao.save(course);
        assertEquals("nimi",courseDao.findOne(course.getId()).getName());
        
    }
    
    @Test
    public void cantAddTheSameCourse() throws SQLException {
        courseDao.save(course);
        courseDao.save(course);
        assertEquals("nimi",courseDao.findOne(course.getId()).getName());
    }
    
    @Test
    public void itIsPossibleToDeleteCourses() throws SQLException {
//        Ei vielä toimi
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
