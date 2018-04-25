
package mystudies.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseUserDao;
import mystudies.dao.DatabaseCourseUserDao;



public class CourseService {
    
    private DatabaseCourseDao courseDao;
    private DatabaseUserDao userDao;
    private DatabaseCourseUserDao usersAndCourses;
    
    private User loggedIn;
    private Scanner reader;
    private Map<String, String> commands;

    
    public CourseService(DatabaseCourseDao courseDao, DatabaseUserDao userDao, DatabaseCourseUserDao usersAndCourses, Scanner reader) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.reader = reader;

        this.usersAndCourses = usersAndCourses;
        
        commands = new TreeMap<>();
        
        commands.put("x", "x quit");
        commands.put("1", "1 ");
        commands.put("2", "2 ");

    }
       
        
    public User getLoggedUser() {
        return loggedIn;
    }   
    
    public boolean createCourse() {

        try {
            System.out.print("courseId: ");
            int courseId = Integer.parseInt(reader.nextLine());
            Course course = courseDao.findOne(courseId);
            
            if (course == null) {
                String nimi = askCourseName();
                String description = askCourseDescription();
                System.out.print("credits: ");
                int credits = Integer.parseInt(reader.nextLine()); 

                course = new Course(courseId, nimi, description, credits);
                Course newCourse = courseDao.save(course);
                
            } else {
                System.out.println("this course already exists");
            }
 
            if (usersAndCourses.findOne(loggedIn.getId(), courseId) == false) {             
                usersAndCourses.save(loggedIn.getId(), courseId);             
            } else {
                System.out.println("you already have this course");
                return false;
            }
        } catch (Exception e) { 
            System.out.println(e);
            return false;
        } 
        return true;
                
    }
    
    public String askCourseName() {
        System.out.print("\n name: ");
        String name = reader.nextLine();
        return name;    
    }
        
    
    public String askCourseDescription() {
        System.out.print("\n description: ");
        String description = reader.nextLine();
        return description;    
    }

    public void start() {
        System.out.println("My Studies: Course Service");

        while (true) {
            printLogInInstructions();
            System.out.println();
            System.out.print(" \n command: ");
            String command = reader.nextLine();

 
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                
                boolean createuser = createUser();
                
                if (createuser == false) {
                    System.out.println("error");
    
                } else {
                    System.out.println("created");
                }          
                
            } else if (command.equals("2")) {
                boolean login = logIn();
                
                if (login == false) {
                    System.out.println("error");
                }
            }        
        } 
    }
    
    public void getYourCourses() { 
        int numberOfCourses =  0;
        int numberOfCredits = 0;
     
        try {
            List<Integer> courseids = usersAndCourses.findAll(loggedIn.getId());
            numberOfCourses = courseids.size();
            
            for (Integer ids : courseids) {
                Course course = courseDao.findOne(ids);
                numberOfCredits = numberOfCredits + course.getCredits();
                System.out.println(course.getId() + "; " + course.getName() + "; " + course.getDescription() + "; " + course.getCredits());

            }
            
                
        } catch (SQLException e) {
            
            System.out.println("You don't have courses yet");
        }

        
        System.out.println("Number of courses: " + numberOfCourses);
        System.out.println("Number of credits: " + numberOfCredits + "\n");
    
    }
    
    
    
    public void yourCourses() {
              
        while (true) {
            printCourseInstructions();
            System.out.print("\n command: ");
            
            String command = reader.nextLine();
    
            if (!commands.keySet().contains(command)) {
                printCourseInstructions();
                 
            }
    
            if (command.equals("x")) {
                loggedIn = null;
                break;
            } else if (command.equals("1")) {                
                System.out.println("\n create a course");
                boolean newcourse = createCourse();
                
                if (newcourse == false) {
                    System.out.println("error");
                } else {
                    System.out.println("created");
                }
                
                
                
            } else if (command.equals("2")) {
                System.out.println("\n Your courses: \n");
                getYourCourses();
         
            } else if (command.equals("3")) {
                deleteCourse();
            }
       
        }
    }
      
      
    public boolean createUser()  {          
        System.out.println("student number:");
        int studentnumber = 0;
        
        try {
        
            studentnumber = Integer.parseInt(reader.nextLine());
            
        } catch (Exception e) {            
            return false;

        }
            
        System.out.print("name: ");
        String name = reader.nextLine();
            
        System.out.println("\n");
        User uuseri = null;
        
        try {
            User user = new User(studentnumber, name);            
            uuseri = userDao.save(user);
            
        } catch (Exception ex) {

            return false;
        }
        
        if (uuseri == null) {
            return true;
        }
        
            
        return false;

    }


    
    
    public void printLogInInstructions() {        
        System.out.println("** commands:  \n 1 add new user \n 2 log in \n x quit \n");

    }
    
    public void printCourseInstructions() {        
        System.out.println("** commands:  \n 1 add new course \n 2 get your courses \n 3 delete course \n x log out \n");
    }
        
    public boolean logIn()  {   
        System.out.print("student number: ");
        int sn = 0;
        
        try {
        
            sn = Integer.parseInt(reader.nextLine());
            loggedIn = userDao.findOne(sn);
            
        } catch (Exception ee) {
            return false;
        }        

        
        if (loggedIn == null) {
            return false;
            
        } else {
            
            System.out.println("** Welcome " + loggedIn.getName() + " \n"); 
            yourCourses();      
        }           
        return true;
    }
    
    public void deleteCourse()  {
        System.out.println("\n Course id: \n");
        int courseid = 0;
        
        try {
            courseid = Integer.parseInt(reader.nextLine());
            usersAndCourses.deleteCourse(courseid, loggedIn.getId());
            System.out.println("\n Done \n");
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error");
        }
        
        
    }
   
}

