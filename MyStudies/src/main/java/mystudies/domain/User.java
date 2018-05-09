package mystudies.domain;


/**
 * This class represents a user.
 *
 * @author olgaviho
 */

public class User {
    
    private int studentNumber;
    private String name;
    
    /**
     * This creates the user.
     * 
     * @param studentNumber the id of the user
     * @param name name of the user
     */

    public User(int studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;

    }
    
    /**
    * This method gives the name.
    *
    *@return returns the name
    */
    
    public String getName() {
        return name;
    }

    /**
    * This method gives the id.
    *
    *@return returns the id 
    */
    
    public int getId() {
        return studentNumber;
    }
}


