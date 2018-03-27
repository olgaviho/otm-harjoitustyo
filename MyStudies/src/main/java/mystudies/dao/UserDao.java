
package mystudies.dao;

/**
 * rajapinta tallentamista varten
 * @author olgaviho
 * 
 */
import mystudies.domain.User;
import java.util.ArrayList;

public interface UserDao {
    
    ArrayList<User> getAll();

    
}
