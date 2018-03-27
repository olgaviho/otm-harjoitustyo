
package mystudies.dao;

/**
 * rajapinta tallentamista varten
 * @author olgaviho
 */
import mystudies.domain.Course;
import java.util.ArrayList;

public interface CourseDao {
    
    ArrayList<Course> getAll();
    
    
    Course createCourse(Course course) throws Exception;
    
}
