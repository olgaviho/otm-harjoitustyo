
package mystudies.dao;

import java.sql.*;
import java.util.List;
import mystudies.domain.User;

/**
 * This class saves the users to the database
 *
 * @author olgaviho
 */

public class DatabaseUserDao implements Dao<User, Integer> {
    
    private Database database;

    public DatabaseUserDao(Database database) {
        this.database = database;
    }

    
        /**
 * This method checks if the user already is in the database
 *
 * @param key the id of the user
 * 
 * @return user, if it exists, otherwise null
 */
    @Override
    public User findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            
            return null;
        }       
        User user = new User(rs.getInt("id"), rs.getString("name"));
        stmt.close();
        rs.close();
        conn.close();
        return user;
    }
/**
 * This method does not work yet
 *
 * 
 * 
 * @return null
 */
    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }
    
    /**
 * This method checks if the user already is in the database, updates it or saves the user
 *
 * @param user
 * 
 * @return user with it's new information
 */
    @Override
    public User save(User user) throws SQLException {
        Connection conn = database.getConnection();
        int userId = user.getId();
        String name = user.getName();       
        User newUser = findOne(userId);
        
        if (newUser == null) {
            

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users " + "(id, name) " + "VALUES (?, ?)");
            stmt.setInt(1, userId);           
            stmt.setString(2, name);       
            stmt.executeUpdate();
            stmt.close();
            return null;
            
        } else {

            conn.close();       
            return newUser;
        }
    }

    
       /**
 * This method deletes the user
 *
 * @param key user id
 * 
 */
    @Override
    public void delete(Integer key) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
    }
    
}
