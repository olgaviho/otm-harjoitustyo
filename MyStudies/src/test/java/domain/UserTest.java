package domain;

import mystudies.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;
    User user2;
    
    @Before
    public void setUp() {
        user = new User(1234,"nimi");

    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi", user.getName());
        
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1234, user.getId());
       
    }
    
 
}
