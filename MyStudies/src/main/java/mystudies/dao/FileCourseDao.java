package mystudies.dao;

/**
 * luokka tallentaa kurssin tiedot tiedostoon
 * @author olgaviho
 */
import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;

import mystudies.domain.Course;
import mystudies.domain.User;

import java.util.ArrayList;

public class FileCourseDao implements CourseDao {
    
    public ArrayList<Course> courses;
    private String file;
    
    public FileCourseDao(String file, UserDao users) throws Exception {
        courses = new ArrayList<>();
        this.file =file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while(reader.hasNextLine()) {
                
                String[] parts = reader.nextLine().split(",");
                int id = Integer.parseInt(parts[0]);
                int credits = Integer.parseInt(parts[3]);          
                User user = new User("pipapo","pipapo");
                
                /**
                 * en saanut ylläolevaa komentoa hakemaan käyttäjää käyttäjänimellä, joten se on toistaiseksi vajaa
                 */
                
                
                Course course = new Course(id, parts[1],parts[2], credits, user);
                courses.add(course);
                
            }
            
            
        } catch (Exception e) {
            
            FileWriter filewriter = new FileWriter(new File(file));
            filewriter.close();
        }
        
    }
    
    private int randomId() {
        return courses.size() + 13;
        
    }
    
    @Override
    public ArrayList<Course> getAll() {
        return courses;
}
    
    @Override
    public Course createCourse(Course newCourse) throws Exception {
        newCourse.setId(randomId());
        courses.add(newCourse);
        save();
        return newCourse;
} 
    
    private void save() throws Exception {     
        try (FileWriter filewriter = new FileWriter(new File(file))) {
            for (Course course : courses) {
                filewriter.write(course.getId() + "," + course.getName() + "," + course.getDescription() + "," + course.getCredits() + "," + course.getUser().getUsername() + "\n");
            }
}
        
    }
    
    

    
}
