
package mystudies.dao;


import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

import mystudies.domain.Course;
import mystudies.domain.User;

import java.util.ArrayList;

/**
 * tallentaa käyttäjätiedot tiedostoon
 * @author olgaviho
 */
public class FileUserDao implements UserDao {
    
        private ArrayList<User> users;
        private String filename;
        
        
        
        public FileUserDao(String filename) throws Exception {
            
        users = new ArrayList<>();
        this.filename = filename;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
               
                User newUser = new User(parts[0], parts[1]);
                users.add(newUser);
            }
        } catch (FileNotFoundException e) {
            
            FileWriter filewriter = new FileWriter(new File(filename));
            filewriter.close();
        }
        
}   
    
    
    @Override
    public ArrayList<User> getAll() {
         return users;
    }
    
    
    private void save() throws Exception {
        try (FileWriter filewriter = new FileWriter(new File(filename))) {
            for (User user : users) {
                filewriter.write(user.getUsername() + "," + user.getName() + "\n");
            }
        } 
}
    
    public User createUser(User newUser) throws Exception {
        users.add(newUser);
        save();
        return newUser;

    
    
     }
}
