/*

 */
package mystudies.domain;

/**
 * yksittäistä kurssia edustava luokka
 * @author olgaviho
 */
public class Course {
    
    private int courseId;
    private String name;
    private String description;
    private int credits;

    
    public Course(int courseId, String name, String description, int credits) {
        
        this.courseId = courseId;
        this.name = name;
        this. description = description;
        this.credits = credits;

    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
        
    }
    
    public void editName(String newName) {
        this.name = newName;
        
    }
    
    public int getCredits() {
        return credits;
        
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;        
    }
    
    public int getId() {
        return courseId;
    }
    
    
}
