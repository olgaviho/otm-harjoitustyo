
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
    * This method gives the credits
    *
    *@return returns the credits
     */
    
    public int getCredits() {
        return credits;        
    }
    
    /**
    * This method gives the name
    *
    *@return returns the name
    */
    
    public String getName() {
        return name;
    }
    
    /**
    * This method gives the description
    *
    *@return returns the description
    */
    
    public String getDescription() {
        return description;        
    }
    
    /**
    * This method returns the id
    *
    *@return returns the id
    */
    
    public int getId() {
        return courseId;
    }
}
