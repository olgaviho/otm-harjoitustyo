
package mystudies.ui;

/**
 *
 * @author olgaviho
 * 
 */

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;
import mystudies.domain.CourseService;
import mystudies.domain.Course;

import mystudies.dao.FileCourseDao;
import mystudies.dao.FileUserDao;

        
public class Main {
    

    public static void main(String[] args) throws Exception {
        
        Scanner reader = new Scanner(System.in);
        
        
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String courseFile = properties.getProperty("courseFile");
        
        
            
        FileUserDao userDao = new FileUserDao(userFile);
        FileCourseDao courseDao = new FileCourseDao(courseFile, userDao);
        CourseService courseservice = new CourseService(courseDao, userDao, reader);
        
        courseservice.start();
        
    }
    
}
