
package mystudies.domain;

/**
 * sovelluslogiikkaluokka
 * @author olgaviho
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import mystudies.dao.CourseDao;
import mystudies.dao.UserDao;


public class CourseService {
    
    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn;
    private Scanner reader;
    private Map<String, String> commands;
    private boolean logged;
    
    public CourseService(CourseDao courseDao, UserDao userDao, Scanner reader) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.reader = reader;
        this.logged = false;
        
        commands = new TreeMap<>();
        
        commands.put("x", "x quit");
        commands.put("1", "1 ");
        commands.put("2", "2 ");
        
        
    }
       
        
    public User getLoggedUser() {
        return loggedIn;
    }   
    
    public boolean createCourse() {
        
        
        System.out.print("\n name: ");
        String name = reader.nextLine(); 
        System.out.print("description: ");
        String description = reader.nextLine(); 
        System.out.print("credits: ");
        
        try {
            int credits = Integer.parseInt(reader.nextLine()); 
            Course course = new Course(name, description, credits, loggedIn);
            courseDao.createCourse(course);
            
        } catch(Exception e) {
            System.out.println("Error \n");
            return false;
        }
        
        return true;
           
      
}
    
    
    public void start() {
        System.out.println("My Studies: Course Service");
        printLogInInstructions();
        
      
        
        while (true) {
            System.out.println();
            System.out.print(" \n command: ");
            String command = reader.nextLine();

            if (!commands.keySet().contains(command)) {
                printLogInInstructions();
                
            }
 
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                createUser();             
                
            } else if (command.equals("2")) {
                logIn();
                printLogInInstructions();
                

            }
        
        } 
    }
    
    public void getYourCourses() {        
        int numberOfCredits = 0;
        int numberOfCourses = 0;
        
        List<Course> courses = courseDao.getAll().stream().filter(cour -> cour.getUser().equals(loggedIn)).collect(Collectors.toList());
        numberOfCourses = courses.size();
        
        
        for (Course course : courses) {
            numberOfCredits = numberOfCredits + course.getCredits();
            System.out.println(course.getName() + ", " + course.getDescription() + ", " + course.getCredits());           
        }
        
        System.out.println("Number of courses: " + numberOfCourses);
        System.out.println("Number of credits: " + numberOfCredits + "\n");
    }
    
    
    
    public void yourCourses() {
        
        
        while (true) {
            printCourseInstructions();
            System.out.print("\n command: \n");
            
            String command = reader.nextLine();

            if (!commands.keySet().contains(command)) {
                printCourseInstructions();
                
            }
 
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                System.out.println("create a course");
                createCourse();
                
                
            } else if (command.equals("2")) {
                System.out.println("Your courses:");
                getYourCourses();
          
            }
    
        }
}
    
    public boolean createUser()  {   
        System.out.print("name: ");
        String name = reader.nextLine();
        System.out.print("username: ");
        String username = reader.nextLine();
        System.out.println("\n");
        printLogInInstructions();
        
        User user = new User(username, name);
        try {
            userDao.createUser(user);
        } catch(Exception e) {
            return false;
        }

        return true;
}

    
    
    public void printLogInInstructions() {
        
        System.out.println("commands:  \n 1 add new user \n 2 log in \n x quit \n");
        
        
    }
    
    public void printCourseInstructions() {
        
        System.out.println("commands:  \n 1 add new course \n 2 get your courses \n x log out \n");
    }
    
    public boolean logIn()  {   
        System.out.print("username: ");
        String username = reader.nextLine();      
        try {
            loggedIn = userDao.findUsername(username);
            
            
        } catch(Exception e) {
            return false;
        }
        
        if (loggedIn == null ) {
            System.out.println("Error");
        } else {
            System.out.println("Welcome " + loggedIn.getName() + "\n");
            
            yourCourses();
            
            loggedIn = null;
            
        }
        
        return true;
    }
    
    
    
    
}

