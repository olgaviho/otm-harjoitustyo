
package mystudies.domain;

/**
 * This class represents a course
 *
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
        this.description = description;
        this.credits = credits;
    

    }
    
/**
 * With this method it is possible to edit description
 *
 *@param newDescription new description for the course
 * 
 */

    public void editDescription(String newDescription) {
        this.description = newDescription;
        
    }
    
    
/**
 * With this method it is possible to edit name
 *
 *@param newName new name for the course
 * 
 */
    public void editName(String newName) {
        this.name = newName;
        
    }
    
/**
 * This method gives the credits
 *
 *@return returns the credits
 * 
 */
    public int getCredits() {
        return credits;
        
    }
/**
 * This method gives the name
 *
 *@return returns the name
 * 
 */
    public String getName() {
        return name;
    }
    /**
 * This method gives the description
 *
 *@return returns the description
 * 
 */
    public String getDescription() {
        return description;        
    }
    /**
 * This method gives the id
 *
 *@return returns the id
 * 
 */
    public int getId() {
        return courseId;
    }
   
    
    
}
