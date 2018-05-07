
package mystudies.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.dao.DatabaseUserDao;

/**
 * This class handles the logic
 * @author olgaviho
 */
public class MyStudiesService {
    
    private DatabaseCourseDao courseDao;
    private DatabaseUserDao userDao;
    private User loggedIn;
    private DatabaseCourseUserDao usersAndCourses;

    
    public MyStudiesService(DatabaseCourseDao courseDao, DatabaseUserDao userDao, DatabaseCourseUserDao usersAndCourses) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.usersAndCourses = usersAndCourses;

    }
        /**
    * login
    * 
    * @param   id   users`s id
    * 
    * @return true if user exists, otherwise false
    */ 
    public boolean login(int id) {

        try {
        
            loggedIn = userDao.findOne(id);
        
        } catch (Exception e) {
            return false;
        }

        if (loggedIn == null) {
            return false;
        }               
        return true;
    }
    
    /**
    * create a new user
    * 
    * @param   id   user`s id
    * @param   name   user`s name
    * 
    * @return true if a new user was created to the system, otherwise false 
    */ 
    public boolean createUser(int id, String name) {

        
        try {
            User user = new User(id,name);
            userDao.save(user);
            return true;
            
        } catch (Exception e) {
            return false;
        }
        


    }
    
    /**
    * logout
    */ 
    public void logout() {
        this.loggedIn = null;
    }
    
    /**
    * get logged
    * 
    * @return the logged user
    */ 
    public String getLogged() {
        return this.getLogged();
    } 
    
    /**
    * this method creates a new course
    * 
    * @param id id of the course
    * @param name name of the course
    * @param description description of the course
    * @param credits credits of the course
    * 
    * 
    * @return true, if a new course was created to the system and the user does not have it yet, otherwise false
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
    * creates a relation to the user and the course
    * 
    * @param courseid the course id
    */ 
    public void createRelation(int courseid, int grade) {
        
        try {
            
        usersAndCourses.save(loggedIn.getId(), courseid, grade);
        
        } catch (Exception e) {
                
        }

    }
    
    /**
    * this method finds out if the user already has the course
    * 
    * @param courseid the course id
    * 
    * @return false, if there wasn't relationship, otherwise true
    */ 
    public boolean userHasCourse(int courseid) {
        
        try {
            
            return usersAndCourses.findOne(loggedIn.getId(), courseid);
        
        } catch (Exception e) {
            
            return false;
        }
    }
    
    /**
    * this method finds out if the course already exists
    * 
    * @param courseid the course id
    * 
    * @return true, if the course already exists, otherwise false
    */ 
    public boolean doesCourseExist(int courseid) {
        
        try {
            
        Course course = courseDao.findOne(courseid);
        
            return course != null;
        
        } catch (Exception e) {
            
            return false;
        }
   
    }
    
    /**
    * this method gives user`s the courses
    * 

    * @return  a list of user`s courses
    */ 
    public List<Course> getYourCourses() {
        List<Course> courses = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        int id = 0;
        
        try {
            id = loggedIn.getId();
            

            ids = usersAndCourses.findAllIds(id);     
           
            for (Integer courseId : ids) {
                Course course = courseDao.findOne(courseId);
               
                courses.add(course);
            }        
            
        } catch (Exception e) {

            return courses;
        }  
        
        return courses;
        
    }
    
    public List<Course> getAllCourses() {
         List<Course> allCourses = new ArrayList<>();

        try {
            allCourses = courseDao.findAll();             
           
        } catch (Exception e) {
            
            return allCourses;
        }  
        return allCourses;
    }
    
    public List<Integer> getYourGrades() {
        
         List<Integer> userGrades = new ArrayList<>();
         
         int id = 0;
        
        try {
            id = loggedIn.getId();
            userGrades = usersAndCourses.findAllGrades(id); 
            
      
        } catch (Exception e) {
            
            return userGrades;
        }  
        return userGrades;
    }
    
    
    
    
    
    public boolean deleteCourse(int courseid) {
        
        try {
      
            usersAndCourses.deleteCourse(courseid, loggedIn.getId());
            
            return true;
        
        } catch (Exception e) {
            
            return false;
        }

    }
    
    
    public double getMean() {
        
        double sum = 0;

        
        List<Course> courses = getYourCourses();
        double numberOfCourses = courses.size();
        
        if (numberOfCourses == 0) {
            return 0;
        }
        List<Integer> grades = getYourGrades();
        
        
        for (Integer grade : grades) {
            sum = sum + grade;
            
        }
        
        double mean = sum/numberOfCourses;
        mean = roundTwoDecimals(mean);
        return mean;
        
    }
    
    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    
    
}
