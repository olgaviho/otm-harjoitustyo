package domain;

import mystudies.domain.Course;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    Course course;
    
    @Before
    public void setUp() {
        course = new Course(111, "nimi", "kuvaus", 5);
    }
    
    @Test
    public void getNameWorks() {
        assertEquals("nimi",course.getName());  
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(111,course.getId());
    }
   
    @Test
    public void getDescriptionWorks() {
        assertEquals("kuvaus",course.getDescription());
    }
    
    @Test
    public void getCreditWorks() {
        assertEquals(5, course.getCredits());       
    }
    

}
