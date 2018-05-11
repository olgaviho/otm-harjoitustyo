
package mystudies.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mystudies.domain.User;

/**
 * This class saves the users to the database.
 *
 * @author olgaviho
 */

public class DatabaseUserDao implements Dao<User, Integer> {
    
    private Database database;
    
    /**
     * This creates the UserDao.
     * 
     * @param database database that will be used
     */

    public DatabaseUserDao(Database database) {
        this.database = database;
    }

    /**
    * This method checks if the user already is in the database.
    *
    * @param key the id of the user
    * @return user, if it is in the database, otherwise null
    * @throws java.sql.SQLException if there is a problem in the database
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
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }
    
    /**
    * This method saves the user.
    *
    * @param user the user
    * @throws java.sql.SQLException if there is a problem in the database
    */
    
    @Override
    public void save(User user) throws SQLException {
        Connection conn = database.getConnection();
        int userId = user.getId();
        String name = user.getName();       
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users " + "(id, name) " + "VALUES (?, ?)");
        stmt.setInt(1, userId);           
        stmt.setString(2, name);
        stmt.executeUpdate();
        stmt.close();
        conn.close();  
    } 
    
    /**
    * This method returns a list, where are all users, that are in the database.
    *
    * @return users in a list
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */

    @Override
    public List<User> findAll() throws SQLException {
        List<User> allUsers = new ArrayList<>();        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {            
            User user = new User(rs.getInt("id"), rs.getString("name"));
            allUsers.add(user);
        }
            
        rs.close();
        stmt.close();        
        conn.close();        
        return allUsers; 
    }
}
