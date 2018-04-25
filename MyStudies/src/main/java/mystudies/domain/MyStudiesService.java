/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudies.domain;

import java.util.Scanner;
import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.dao.DatabaseUserDao;

/**
 *
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
    
    public boolean createUser(int id, String name) {
        
        
        User uuseri = null;
        
        try {
            User user = new User(id,name);
            uuseri = userDao.save(user);
            
        } catch (Exception e) {
            return false;
        }
        
        return uuseri == null;

    }
    
    public void logout() {
        this.loggedIn = null;
    }
    
    public String getLogged() {
        return this.getLogged();
    } 
    
    public boolean createCourse(int id, String name, String description, int credits) {
       
        
        Course newCourse = null;
        
        try {
            Course course = new Course(id,name, description, credits);
            newCourse = courseDao.save(course);
  
        
        if (usersAndCourses.findOne(loggedIn.getId(), id) == false) {             
                usersAndCourses.save(loggedIn.getId(), id); 
                return true;
            } else {
            
            return false;
    
            }
        
        } catch (Exception e) {
            return false;
        }
    }
    
    public void createRelation(int id) {
        
        try {
            
        usersAndCourses.save(loggedIn.getId(), id);
        
        } catch (Exception e) {
                
        }

    }
    
    public boolean userHasCourse(int id) {
        
        try {
            
            return usersAndCourses.findOne(loggedIn.getId(), id);
        
        } catch (Exception e) {
            return true;
        }

    }
    
    
    public boolean doesCourseExist(int id) {
        
        try {
        Course course = courseDao.findOne(id);
        
            return course != null;
        
        } catch (Exception e) {
            return false;
        }
   
    }
    
    
    
}
