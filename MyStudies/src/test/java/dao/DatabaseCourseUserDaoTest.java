/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.domain.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olgaviho
 */
public class DatabaseCourseUserDaoTest {
    
    Database database;
  
    DatabaseCourseUserDao usersAndCoursesDao;
    
   
    
    @Before
    public void setUp() throws Exception {
        database = new Database("jdbc:sqlite:mystudiestest.db");        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, grade integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");
        stmt.execute();        
        usersAndCoursesDao = new DatabaseCourseUserDao(database);
    }
    
   

    @Test
    public void returnsFalseIfThereIsNotRealtion() throws SQLException {                  
       assertEquals(false,usersAndCoursesDao.findOne(12,678));
    }
    
    @Test
    public void itIsPossibleToSaveUsersAndCourses() throws SQLException {
        usersAndCoursesDao.save(1234,12,5);
 
        assertEquals(true,usersAndCoursesDao.findOne(1234,12));       


    }
    
    @Test
    public void itIsPossibleToFindAllGrades() throws SQLException {
        usersAndCoursesDao.save(12345,14,4);
        usersAndCoursesDao.save(12345,15,4);
        List<Integer> twoGrades = usersAndCoursesDao.findAllGrades(12345);
        assertEquals(2,twoGrades.size());
        
    }
    
    @Test
    public void itIsPossibleToFindAllCourses() throws SQLException {
        usersAndCoursesDao.save(12346,14,4);
        usersAndCoursesDao.save(12346,15,4);
        List<Integer> twoGrades = usersAndCoursesDao.findAllIds(12346);
        assertEquals(2,twoGrades.size());
        
    }
    
    
    @After
    public void tearDown() throws SQLException {
        Connection conn = database.getConnection();  
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE usersandcourses");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
     
}
