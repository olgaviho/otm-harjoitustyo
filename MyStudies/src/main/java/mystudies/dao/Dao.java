
package mystudies.dao;

import java.sql.*;


public interface Dao<T, K> {
    
    T findOne(K key) throws SQLException;
    
    void save(T object) throws SQLException;
}
    
    
    

