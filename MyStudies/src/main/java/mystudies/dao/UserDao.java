
package mystudies.dao;

/**
 * rajapinta tallentamista varten
 * @author olgaviho
 * 
 */
import mystudies.domain.User;
import java.util.List;

public interface UserDao {
    
    List<User> getAll();
    
    User createUser(User user) throws Exception;
    
    User findUsername(String username);

    
}
