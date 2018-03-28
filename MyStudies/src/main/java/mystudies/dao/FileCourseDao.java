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

import java.util.List;
import java.util.ArrayList;

public class FileCourseDao implements CourseDao {
    
    public List<Course> courses;
    private String file;
    
    public FileCourseDao(String file, UserDao users) {
        courses = new ArrayList<>();
        this.file =file;                 
        
    }
    
    private int randomId() {
        return courses.size() + 1;
        
    }
    
    @Override
    public List<Course> getAll() {
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
                filewriter.write(course.getId() + ";" + course.getName() + ";" + course.getDescription() + ";" + course.getCredits() + ";" + course.getUser().getUsername() + "\n");
            }
}
        
    }
    
    

    
}
