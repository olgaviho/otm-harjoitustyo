

import mystudies.domain.Course;
import mystudies.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    Course course1;
    Course course2;
    User user;
    
    @Before
    public void setUp() {
        user = new User("nimi", "kayttajanimi");
        course1 = new Course("nimi", "kuvaus", 5, user);
        course2 = new Course(1,"nimi", "kuvaus", 5, user);
    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi",course1.getName());  
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1,course2.getId());
    }
   
    @Test
    public void getDescriptionWorks() {
        assertEquals("kuvaus",course1.getDescription());
    }
    
    @Test
    public void getCreditWorks() {
        assertEquals(5, course1.getCredits());       
    }
    
    @Test
    public void getUserWorks() {
        assertEquals(user,course1.getUser());
    }
    
    @Test
    public void editDescriptionWorks() {
        course1.editDescription("new Description");
        assertEquals("new Description", course1.getDescription());
    }
    
    @Test
    public void editNameWorks() {
        course1.editName("New Name");
        assertEquals("New Name",course1.getName());
    }
    
    @Test
    public void setIdWorks() {
        course1.setId(5);
        assertEquals(5,course1.getId());
    }
  
   
}
