
package mystudies.domain;

/**
 * sovelluslogiikkaluokka
 * @author olgaviho
 * 
 */

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
    private boolean logged;
    
    public CourseService(DatabaseCourseDao courseDao, DatabaseUserDao userDao, DatabaseCourseUserDao usersAndCourses, Scanner reader) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.reader = reader;
        this.logged = false;
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
            String nimi = askCourseName();
            String description = askCourseDescription();
            System.out.print("credits: ");
            int credits = Integer.parseInt(reader.nextLine()); 

            Course course = new Course(courseId, nimi, description, credits);    
               
            Course newCourse = courseDao.save(course);
            if (usersAndCourses.findOne(loggedIn.getId(), courseId) == false) {             
                usersAndCourses.save(loggedIn.getId(), courseId);              
            }
        } catch (Exception e) {           
            System.out.println(e.toString());
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
    /**
    public void getYourCourses() {        
        int numberOfCredits = 0;
        List<Course> courses = courseDao.getAll().stream().filter(cour -> cour.getUser().equals(loggedIn)).collect(Collectors.toList());
        int numberOfCourses = courses.size();
        
        
        for (Course course : courses) {
            numberOfCredits = numberOfCredits + course.getCredits();
            System.out.println(course.getName() + ", " + course.getDescription() + ", " + course.getCredits());           
        }
        
        System.out.println("Number of courses: " + numberOfCourses);
        System.out.println("Number of credits: " + numberOfCredits + "\n");
     }
    */
    
    
    public void yourCourses() {
              
        while (true) {
            printCourseInstructions();
            System.out.print("\n command: ");
            
            String command = reader.nextLine();
    
            if (!commands.keySet().contains(command)) {
                printCourseInstructions();
                 
            }
    
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {                
                System.out.println("\n create a course");
                createCourse();
                
                
            } else if (command.equals("2")) {
                System.out.println("\n Your courses:");
                System.out.println("not working yet :( \n");
                /**getYourCourses();*/
         
            }
       
        }
    }
      
      
    public boolean createUser()  {          
        System.out.println("student number:");
        
        try {
        
            int studentnumber = Integer.parseInt(reader.nextLine());
            System.out.print("name: ");
            String name = reader.nextLine();
            
            System.out.println("\n");
            printLogInInstructions();
            
            User user = new User(studentnumber, name);
            userDao.save(user);
            
        } catch (Exception e) {            
            System.out.println("error");
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
        System.out.print("student number: ");
        int studentnumber = Integer.parseInt(reader.nextLine());
        
        try {
            loggedIn = userDao.findOne(studentnumber);
            
            
        } catch (Exception e) {
            return false;
        }
        
        if (loggedIn == null) {
            System.out.println("Error");
        } else {
            
            System.out.println("Welcome " + loggedIn.getName() + "\n"); 
            yourCourses();
            loggedIn = null;      
        }   
        return true;
    }
   
}

