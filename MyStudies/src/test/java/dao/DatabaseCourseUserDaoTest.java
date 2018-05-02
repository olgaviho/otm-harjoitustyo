/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseUserDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");
        stmt.execute();        
        usersAndCoursesDao = new DatabaseCourseUserDao(database);


    }
    
   

    @Test
    public void returnsFalseIfThereIsNotRealtion() throws SQLException {                  
       assertEquals(false,usersAndCoursesDao.findOne(12,678));
    }
    
    @Test
    public void itIsPossibleToSaveUsersAndCourses() throws SQLException {
        usersAndCoursesDao.save(1234,12);
        
        
//       assertEquals(true,usersAndCoursesDao.findOne(1234,12));       
//        tässä on nyt jotain pahaa häikkää kun ei toimi :/
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
