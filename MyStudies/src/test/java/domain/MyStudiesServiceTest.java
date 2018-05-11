
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mystudies.dao.CourseUserDao;
import mystudies.dao.Dao;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.dao.DatabaseUserDao;
import mystudies.domain.MyStudiesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olgaviho
 */

public class MyStudiesServiceTest {
    
    Database database;
    Database emptyDatabase;
    DatabaseCourseDao courseDao;
    Dao userDao;
    CourseUserDao usersAndCoursesDao;
    MyStudiesService service;
    MyStudiesService emptyService;
    
    @Before
    public void setUp() throws Exception {
        database = new Database("jdbc:sqlite:mystudiestest.db");        
        Connection conn = database.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer)");
        PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE if not exists users (id integer PRIMARY KEY, name varchar(20))");
        PreparedStatement stmt3 = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, grade integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");
        stmt1.execute(); 
        stmt2.execute(); 
        stmt3.execute(); 
               
        courseDao = new DatabaseCourseDao(database);
        userDao = new DatabaseUserDao(database);
        usersAndCoursesDao = new DatabaseCourseUserDao(database);
        service = new MyStudiesService(courseDao, userDao, usersAndCoursesDao);
            
        service.createUser(1, "Tester1");
        service.createCourse(1, "Tester course 1", "test course", 5);        
        conn.close();       
    }
    
    @Test
    public void itIsPossibleToCreateUser() {            
        assertEquals(true,service.createUser(2, "tester2"));
    }
    
    @Test
    public void userIdHasToBeUnique() {
        assertEquals(false, service.createUser(1, "Tester3"));
    }
    
    @Test
    public void itIsPossibleToLogIn() {
        assertEquals(true, service.login(1));
    }
    
    @Test
    public void cantLogInIfHaventCreatedUser() {
        assertEquals(false, service.login(4));
    }

    @Test
    public void itIsPossibleToLogOut() {
        service.login(1);
        service.logOut();        
        assertEquals(null,service.getLogged());
    }
    
    @Test
    public void roundTwoDecimalsWorks() {
        double tester3 = service.roundTwoDecimals(5.0111);
        String stringTester3 = Double.toString(tester3);
        assertEquals("5.01", stringTester3);
    }
    
    @Test
    public void itIsPossibleToCreateCourse() {
        assertEquals(true, service.createCourse(2, "Tester course 2", "test course", 1));
    }
    
    @Test
    public void courseIdHasToBeUnique() {
        assertEquals(false, service.createCourse(1, "Tester course 1", "test course", 3));
    }
    
    @Test
    public void itIsPossibleToCreateRelation() {
        service.login(1);
        assertEquals(true, service.createRelation(2, 5));
        service.logOut();
    }
    
    @Test
    public void getMeanWorks() {
        service.login(1);
        assertEquals(true, service.createRelation(3, 5));
        assertEquals(true, service.createRelation(4, 3));
        assertEquals("4.0", Double.toString(service.getMean()));
        service.logOut();        
    }
    
    @Test
    public void returnsFalseIfUserDoesntHaveTheCourse() {
        service.login(1);
        assertEquals(false, service.userHasCourse(77));
        service.logOut();        
    }
    
    @Test
    public void returnsTrueIfUserHasTheCourse() {
        service.login(1);
        service.createRelation(3, 5);
        assertEquals(true, service.userHasCourse(3));
        service.logOut();        
    }
    
    @Test
    public void returnsFalseIfCourseDoesNotExist() {
        assertEquals(false, service.doesCourseExist(66));
    }
    
    @Test
    public void returnsTrueIfCourseExists() {
        assertEquals(true, service.doesCourseExist(1));
    }
    
    @Test
    public void returnsSizeOfUserCourses() {
        service.login(1);
        assertEquals(1, service.getAllCourses().size());
        service.logOut();
    }
    
    @Test
    public void deleteCourseWorks() {
        service.login(1);
        service.createRelation(4, 1);
        assertEquals(true, service.userHasCourse(4));
        service.deleteCourse(4);
        assertEquals(false, service.userHasCourse(4));
        service.logOut();
    }
    
    @Test
    public void getYourCoursesReturnsNullIfThereIsNoLoggedIn() {
        assertEquals(null, service.getYourCourses());
    }
    
    @Test
    public void getYourGradesReturnsNullIfThereIsNoLoggedIn() {
        assertEquals(null, service.getYourGrades());
    }
    
    @Test
    public void getMeanReturns0IfThereIsNoLoggedIn() {
        assertEquals(Double.toString(0.0), Double.toString(service.getMean()));
    }
    
    @Test
    public void getMeanReturns0IfLoggedInHasZeroCourses() {
        service.createUser(7, "tester4");
        service.login(7);
        assertEquals(Double.toString(0.0), Double.toString(service.getMean()));
        service.logOut();
    }
    
    @After
    public void tearDown() throws SQLException {
        Connection conn = database.getConnection();  
        
        PreparedStatement stmt1 = conn.prepareStatement("DROP TABLE courses");
        PreparedStatement stmt2 = conn.prepareStatement("DROP TABLE users");
        PreparedStatement stmt3 = conn.prepareStatement("DROP TABLE usersandcourses");

        stmt1.executeUpdate();
        stmt2.executeUpdate();
        stmt3.executeUpdate();
        stmt1.close();
        stmt2.close();
        stmt3.close();
        conn.close();
    }
}
