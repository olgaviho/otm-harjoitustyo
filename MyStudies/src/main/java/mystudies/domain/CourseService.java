
package mystudies.domain;

/**
 * sovelluslogiikka luokka
 * @author olgaviho
 * 
 */

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import mystudies.dao.CourseDao;
import mystudies.dao.UserDao;


public class CourseService {
    
    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn;
    private Scanner reader;
    private Map<String, String> commands;
    
    public CourseService(CourseDao courseDao, UserDao userDao, Scanner reader) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.reader = reader;
        commands = new TreeMap<>();
        
        commands.put("x", "x lopeta");
        commands.put("1", "1 ");
        commands.put("2", "2 ");
        
        
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
    
    
    public void start() {
        System.out.println("My Studies: Course Service");
        printInstructions();
        
        while (true) {
            System.out.println();
            System.out.print("command: ");
            String command = reader.nextLine();

            if (!commands.keySet().contains(command)) {
                printInstructions();
                
            }
 
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                createUser();             
                
            } else if (command.equals("2")) {
                logIn();
            
 
            }
        }
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

    
    
    /*
    *    public boolean login(String username) {
    *    User user = userDao.findUsername(username);           
    *    return true;
    * 
    *    ei toimi, koska findUsername ei toimi
    */
    
    public boolean createUser()  {   
        System.out.print("name: ");
        String name = reader.nextLine();
        System.out.print("username: ");
        String username = reader.nextLine();
        
        
        User user = new User(username, name);
        try {
            userDao.createUser(user);
        } catch(Exception e) {
            return false;
        }

        return true;
}

    
    
    public void printInstructions() {
        
        System.out.println("commends:  \n 1. add new user \n 2. log in \n x. quit");
        
        
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
            System.out.println("error");
        } else {
            System.out.println("Welcome " + loggedIn.getName());
        }
        
        return true;
}
}

