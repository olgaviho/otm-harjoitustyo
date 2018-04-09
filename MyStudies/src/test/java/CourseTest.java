

import mystudies.domain.Course;
import mystudies.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    Course course;
    User user;
    
    @Before
    public void setUp() {
        user = new User("nimi", "kayttajanimi");
        course = new Course(1,"nimi", "kuvaus", 5, user);
    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi", course.getName());
    
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1,course.getId());
    }
   
    @Test
    public void getDescriptionWorks() {
        assertEquals("kuvaus",course.getDescription());
    }
    
    @Test
    public void getCreditWorks() {
        assertEquals(5, course.getCredits());
        
    }
    
    @Test
    public void getUserWorks() {
        assertEquals(user,course.getUser());
    }
    
    @Test
    public void editDescriptionWorks() {
        course.editDescription("new Description");
        assertEquals("new Description", course.getDescription());
    }
    
    @Test
    public void editNameWorks() {
        course.editName("New Name");
        assertEquals("New Name",course.getName());
    }
    
    @Test
    public void setIdWorks() {
        course.setId(5);
        assertEquals(5,course.getId());
    }
  
   
}
