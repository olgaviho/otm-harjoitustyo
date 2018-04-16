
package mystudies.ui;

/**
 *
 * @author olgaviho
 * 
 */

import java.io.FileInputStream;
import java.sql.*;

import java.util.Properties;
import java.util.Scanner;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.dao.DatabaseUserDao;
import mystudies.domain.CourseService;



        
public class Main {
    

    public static void main(String[] args) throws Exception {   
        
        Scanner reader = new Scanner(System.in);
        Database database = new Database("jdbc:sqlite:mystudies.db");
        
        createTables(database);
        
        DatabaseUserDao userDao = new DatabaseUserDao(database);
        DatabaseCourseDao courseDao = new DatabaseCourseDao(database);
        DatabaseCourseUserDao usersAndCourses = new DatabaseCourseUserDao(database);
        CourseService courseservice = new CourseService(courseDao, userDao, usersAndCourses, reader);
        
        courseservice.start();
        
    }
    
    
    public static void createTables(Database database) throws SQLException {
   
    Connection conn = database.getConnection();
    
    PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists users (id integer PRIMARY KEY, name varchar(20))");
    PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer)");
    PreparedStatement stmt3 = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");

    stmt.execute();
    stmt2.execute();
    stmt3.execute();

    
    
    }


    
}
