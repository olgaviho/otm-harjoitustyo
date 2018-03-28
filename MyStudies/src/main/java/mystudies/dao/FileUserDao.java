
package mystudies.dao;


import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

import mystudies.domain.Course;
import mystudies.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * tallentaa käyttäjätiedot tiedostoon
 * @author olgaviho
 */
public class FileUserDao implements UserDao {
    
        private List<User> users;
        private String filename;
        
        
        
        public FileUserDao(String filename) throws Exception {
            
        users = new ArrayList<>();
        this.filename = filename;     
        
}             
    @Override
    public List<User> getAll() {
         return users;
    }
    
    
    private void save() throws Exception {
        try (FileWriter filewriter = new FileWriter(new File(filename))) {
            for (User user : users) {
                filewriter.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        } 
}
    
    @Override
    public User createUser(User newUser) throws Exception {
        users.add(newUser);
        save();
        return newUser;

     }

    @Override
    public User findUsername(String username) {
    return users.stream().filter(user->user.getUsername().equals(username)).findFirst().orElse(null);   
        
    }
    
}
