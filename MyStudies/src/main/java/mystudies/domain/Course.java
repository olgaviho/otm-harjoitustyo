/*

 */
package mystudies.domain;

/**
 * yksittäistä kurssia edustava luokka
 * @author olgaviho
 */
public class Course {
    
    private int id;
    private String name;
    private String description;
    private int credits;
    private User user;
    
    public Course(int id, String name, String description, int credits, User user) {
        this.id = id;
        this.name = name;
        this. description = description;
        this.credits = credits;
        this.user = user;
        
    }
    
    public Course(String name, String description, int credits, User user) {
        
        this.name = name;
        this. description = description;
        this.credits = credits;
        this.user = user;
        
    }
    
    public Course(int id, String name, int credits, User user) {
        
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.user = user;
        
    }
    
    public Course(String name, int credits, User user) {
        
        this.name = name;
        this. description = "";
        this.credits = credits;
        this.user = user;
        
    }

  
    
    public void setId(int id) {
        this.id =id;
        
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
    
    public User getUser() {
        return user;        
    }
    
    public int getId() {
        return id;
    }
    
    
}
