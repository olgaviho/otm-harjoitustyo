
package mystudies.dao;

import java.sql.*;

/**
 * This is an interface for all daos.
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
    
    T findOne(K key) throws SQLException;
    
    /**
    * This method saves the course.
    *
    * @param object that object will be saved
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    void save(T object) throws SQLException;
}
    
    
    

