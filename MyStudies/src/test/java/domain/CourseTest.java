package domain;



import mystudies.domain.Course;
import mystudies.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    Course course1;
    Course course2;
    User user;
    
    @Before
    public void setUp() {
        user = new User(1234, "nimi");
        course1 = new Course(111, "nimi", "kuvaus", 5);
    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi",course1.getName());  
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(111,course1.getId());
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
    public void editDescriptionWorks() {
        course1.editDescription("new Description");
        assertEquals("new Description", course1.getDescription());
    }
    
    @Test
    public void editNameWorks() {
        course1.editName("New Name");
        assertEquals("New Name",course1.getName());
    }

   
}