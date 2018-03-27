
package mystudies.domain;

/**
 * sovelluslogiikka luokka
 * @author olgaviho
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import mystudies.dao.CourseDao;
import mystudies.dao.UserDao;

public class CourseService {
    
    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn;
    
    public CourseService(CourseDao courseDao, UserDao userDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        
    }
    
    /*
    *    public boolean login(String username) {
    *    User user = userDao.findUsername(username);
    *    if (user == null) {
    *        return false;
    *    }
    *
    *
    *    loggedIn = user;
    *    
    *    return true;
    */    
        

    public void logout() {
        loggedIn = null;  
     } 
    
    public User getLoggedUser() {
        return loggedIn;
    }   
    
    public boolean createCourse(String name, String content, int credits) {
        Course course= new Course(name, content, credits ,loggedIn);
        try {   
            courseDao.createCourse(course);
        } catch (Exception e) {
            return false;
        }
        return true;
}
    
    /*
    *public ArrayList<Course> getCourses() {
    *    if (loggedIn == null) {
    *        return new ArrayList<>();
    *    }
    *      
    *    return CourseDao.getAll()
    *            .stream()
    *            .filter(t-> t.getUser().equals(loggedIn))
    *            .collect(Collectors.toList());
    */       
}
    
    /*
    *    public boolean login(String username) {
    *    User user = userDao.findUsername(username);           
    *    return true;
    * 
    *    ei toimi, koska findUsername ei toimi
    */



