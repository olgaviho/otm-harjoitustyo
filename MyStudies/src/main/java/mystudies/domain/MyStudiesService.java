
package mystudies.domain;
import java.util.ArrayList;
import java.util.List;
import mystudies.dao.CourseUserDao;
import mystudies.dao.Dao;


/**
 * This class handles the logic.
 * 
 * @author olgaviho
 */

public class MyStudiesService {
    
    private final Dao courseDao;
    private final Dao userDao;
    private User loggedIn;
    private final CourseUserDao usersAndCourses;
    
    /**
     * This creates the service.
     * 
     * @param courseDao this saves the courses
     * @param userDao this saves the users
     * @param usersAndCourses this saves user's ids and ids of the courses
     */

    
    public MyStudiesService(Dao courseDao, Dao userDao, CourseUserDao usersAndCourses) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.usersAndCourses = usersAndCourses;
    }
        
    /**
    * Login to the service.
    * 
    * @param   id   user's id
    * @return true if user exists, otherwise false
    * 
    */ 
    
    public boolean login(int id) {

        try {
        
            loggedIn = (User) userDao.findOne(id);       
        } catch (Exception e) {            
            return false;
        }
        return loggedIn != null;
    }
    
    /**
    * Creates a new user.
    * 
    * @param   id   user`s id
    * @param   name   user`s name
    * @return true if a new user was created to the system, otherwise false 
    */ 
    
    public boolean createUser(int id, String name) {
        
        try {
            User user = new User(id, name);
            userDao.save(user);
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
    * This method sets the logged user to null.
    */ 
    
    public void logOut() {
        
        this.loggedIn = null;
    }
    
    /**
    * This method return the logged user.
    * @return the logged user
    */ 
    
    public User getLogged() {
        
        if (loggedIn == null) {
            return null;
        } else {

            return this.loggedIn;
        
        }
    }

    /**
    * this method creates a new course.
    * 
    * @param id id of the course
    * @param name name of the course
    * @param description description of the course
    * @param credits credits of the course
    * @return true, if a new course was created to the system, otherwise false
    */ 
    
    public boolean createCourse(int id, String name, String description, int credits) {

        try {
            Course course = new Course(id, name, description, credits);
            courseDao.save(course);
            return true;
        
        } catch (Exception e) {   
            return false;
        }
    }
    
    /**
    * Creates a relationship between the user and the course.
    * 
    * @param courseid the course id
    * @param grade the grade of the course
    * @return true if the relation was created, else false
    */ 
    
    public boolean createRelation(int courseid, int grade) {
        
        try {           
            usersAndCourses.save(loggedIn.getId(), courseid, grade);        
            return true;
        
        } catch (Exception e) {                
            return false;
        }
    }
    
    /**
    * This method finds out if the user already has the course.
    * 
    * @param courseid the course id
    * 
    * @return true, if there was already a relationship, otherwise false
    */ 
    
    public boolean userHasCourse(int courseid) {
        
        try {            
            return usersAndCourses.findOne(loggedIn.getId(), courseid);        
        } catch (Exception e) {            
            return false;
        }
    }
    
    /**
    * This method finds out if the course already exists.
    * 
    * @param courseid the course id
    * @return true, if the course already exists, otherwise false
    */ 
    
    public boolean doesCourseExist(int courseid) {
        
        try {            
            Course course = (Course) courseDao.findOne(courseid);        
            return course != null;
        
        } catch (Exception e) {            
            return false;
        }  
    }
    
    /**
    * This method gives the courses of the user.
    * 
    * @return  a list of user`s courses or null, if there was a problem in the database
    */ 
    
    public List<Course> getYourCourses() {
        List<Course> courses = new ArrayList<>();
        
        try {
            
            int id = loggedIn.getId();
            List<Integer> ids = usersAndCourses.findAllIds(id);     
           
            for (Integer courseId : ids) {
                Course course = (Course) courseDao.findOne(courseId);               
                courses.add(course);
            }   
            return courses; 
            
        } catch (Exception e) {
            return null;
        }          
              
    }
    
    /**
    * This method returns all courses.
    * 
    * @return  courses in a list or null, if there was a problem in the database
    */ 
    
    public List<Course> getAllCourses() {

        try {
            List<Course> allCourses = courseDao.findAll();     
            return allCourses;
            
        } catch (Exception e) {           
            return null;
        }  
        
    }
    
     /**
    * This method gives the grades of the user.
    * 
    * @return  a list of user`s grades or null, if there was a problem in the database
    */ 
    
    public List<Integer> getYourGrades() {
        
        try {
            
            int id = loggedIn.getId();
            List<Integer>  userGrades = usersAndCourses.findAllGrades(id); 
            return userGrades;

        } catch (Exception e) {            
            return null;
        }  
    }
    
    /**
    * This method deletes the course.
    * 
    * @param courseid id of the course
    * @return true, if a new course was deleted, otherwise false
    */
    

    public boolean deleteCourse(int courseid) {
        
        try {      
            usersAndCourses.deleteCourse(courseid, loggedIn.getId());            
            return true;
        
        } catch (Exception e) {           
            return false;
        }
    }
    
    /**
    * This method calculates mean of user's courses.
    * 
    * @return mean of user's courses or 0 if user doesn't have any courses
    */ 
    
    
    public double getMean() {
        
        double sum = 0;        
        List<Course> courses = getYourCourses();
        
        if (courses == null) {
            return 0.0;
        }
        
        double numberOfCourses = courses.size();        
        if (numberOfCourses == 0) {
            return 0.0;
        }
        List<Integer> grades = getYourGrades();
        for (Integer grade : grades) {
            sum = sum + grade;           
        }       
        double mean = sum / numberOfCourses;
        mean = roundTwoDecimals(mean);
        return mean;        
    }
    
    /**
    * This method rounds decimals to two decimal places.
    * 
    * @param d decimal that method will round
    * @return new decimal
    */ 
    
    public double roundTwoDecimals(double d) {
        d = Math.floor(100 * d + 0.5) / 100;
        return d;
    }

}
