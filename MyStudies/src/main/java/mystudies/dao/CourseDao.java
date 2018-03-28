
package mystudies.dao;

/**
 * rajapinta tallentamista varten
 * @author olgaviho
 */
import mystudies.domain.Course;
import java.util.List;

public interface CourseDao {
    
    List<Course> getAll();
    
    
    Course createCourse(Course course) throws Exception;
    
}
