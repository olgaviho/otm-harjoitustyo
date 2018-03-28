package mystudies.domain;


/**
* käyttäjää edustava luokka
 */
public class User {
    private String name;
    private String username;
    
    
    public User(String username, String name) {
        this.name = name;
        this.username = username;
        
    }
    
    public Object getUsername() {
        return username;
    }
    
    public String getName() {
        return name;
    }

   
    
}


