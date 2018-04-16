/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import mystudies.dao.Database;
import mystudies.dao.DatabaseUserDao;
import mystudies.domain.User;
import static mystudies.ui.Main.createTables;
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
public class DatabaseUserDaoTest {
    
    Database database;
    User user;
    DatabaseUserDao userDao;
    
    
    @Before
    public void setUp() throws Exception {
        Scanner reader = new Scanner(System.in);
        database = new Database("jdbc:sqlite:mystudiestest.db");        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists users (id integer PRIMARY KEY, name varchar(20))");
        stmt.execute();        
        userDao = new DatabaseUserDao(database);
        
        user = new User(123, "nimi");

    }
    
    
    @Test
    public void itIsPossibleToFindUsers() throws SQLException {        
          
       assertEquals(null,userDao.findOne(678));
    }
     
    @Test
    public void itIsPossibleToSaveUsers() throws SQLException {
        userDao.save(user);
        assertEquals("nimi",userDao.findOne(user.getId()).getName());
        
    }
    
    
}
