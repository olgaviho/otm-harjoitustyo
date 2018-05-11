
package mystudies.dao;

import java.sql.*;
import java.util.List;

/**
 * This is an interface for courseDao and userDao.
 *
 * @author olgaviho
 * @param <T> an object that will be saved 
 * @param <K> key for the object
 */


public interface Dao<T, K> {
    
    /**
    * This method saves the course.
    *
    * @param key that helps find the object
    * @return object
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    T findOne(Integer key) throws SQLException;
    
    /**
    * This method saves the course.
    *
    * @param object that object will be saved
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    void save(T object) throws SQLException;
    
    /**
    * This method returns all the objects in the database.
    *
    * @return list of objects
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    List<T> findAll() throws SQLException;
}
    
    
    

