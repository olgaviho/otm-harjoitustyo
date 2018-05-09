
package mystudies.dao;

import java.sql.*;

/**
 * This is a class that represents a database.
 *
 * @author olgaviho
 */

public class Database {
    
    private String databaseAddress;
    
    /**
    * This creates the class.
    *
    * @throws ClassNotFoundException if cant find the class
    * @param databaseAddress address to the database
    */
        
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    /**
    * This is a class that represents a database.
    *
    * @throws SQLException if there is a problem in the database
    * @return connection to the database
    * 
    */

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
}
