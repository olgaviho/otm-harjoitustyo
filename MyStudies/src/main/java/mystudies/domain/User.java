package mystudies.domain;


/**
* käyttäjää edustava luokka
 */

public class User {
    
    private int studentNumber;
    private String name;

    
    public User(String name) {
        this.name = name;       
    }
    
    public User(int studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;

    }
    
    public void setId(int id) {
        this.studentNumber = id;       
    }
    
   
    
    public String getName() {
        return name;
    }

    public int getId() {
        return studentNumber;
    }
   
    
}


