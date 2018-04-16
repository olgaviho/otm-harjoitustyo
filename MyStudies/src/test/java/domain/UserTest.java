package domain;

import mystudies.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;
    User user2;
    
    @Before
    public void setUp() {
        user = new User(1234,"nimi");
        user2 = new User ("nimi");

    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi", user.getName());
        
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1234, user.getId());
       
    }
    
    @Test
    public void setIdWorks() {
        user2.setId(12345);
        assertEquals(12345, user2.getId());
    }

}
