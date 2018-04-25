/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudies.ui;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mystudies.dao.Database;
import mystudies.dao.DatabaseCourseDao;
import mystudies.dao.DatabaseCourseUserDao;
import mystudies.dao.DatabaseUserDao;
import mystudies.domain.CourseService;
import mystudies.domain.MyStudiesService;



/**
 *
 * @author olgaviho
 */
public class MyStudiesUi extends Application {
    
    private MyStudiesService myStudiesService;
    private Scene coursesScene;
    private Scene newCourseScene;
    private Scene loginScene;
    private Scene newUserScene;
    private VBox courseNodes;
    private Label menuLabel = new Label();
    
   
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        
        Database database = new Database("jdbc:sqlite:mycourses.db");
        
        createTables(database);
        
        DatabaseUserDao userDao = new DatabaseUserDao(database);
        DatabaseCourseDao courseDao = new DatabaseCourseDao(database);
        DatabaseCourseUserDao usersAndCourses = new DatabaseCourseUserDao(database);
        this.myStudiesService = new MyStudiesService(courseDao, userDao, usersAndCourses);
      
        
    }
    
    public static void createTables(Database database) throws SQLException {
   
        Connection conn = database.getConnection();
    
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists users (id integer PRIMARY KEY, name varchar(20))");
        PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer)");
        PreparedStatement stmt3 = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");

        stmt.execute();
        stmt2.execute();
        stmt3.execute();
    }
    
 
     
    public static void main(String[] args) {
        launch(MyStudiesUi.class);
        
    }
   

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //luo aloitusnäytön
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Id");
        TextField loginInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, loginInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Login");
        
        loginButton.setOnAction(e->{
            
            int id = 0;
            boolean nomistake = true;
            try {
            
                id = Integer.parseInt(loginInput.getText());
                
            } catch (Exception ex) {
                nomistake = false;
            }

            if (!nomistake) {
                loginMessage.setText("Id is not valid");
                loginMessage.setTextFill(Color.RED);
                
            } else if (myStudiesService.login(id)){
                
                loginMessage.setText("");
                primaryStage.setScene(coursesScene); 
                loginInput.setText("");
                
            } else {
                
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        }); 
        
        Button createNewUserButton = new Button("Create New User");
        
        createNewUserButton.setOnAction(e->{
            loginInput.setText("");
            primaryStage.setScene(newUserScene);   
        }); 
         
        
       
        loginPane.getChildren().addAll(loginMessage, loginButton, inputPane, createNewUserButton);       
        
        loginScene = new Scene(loginPane, 300, 250);    
        
        
        
        
        // luo kirjautumisnäytön
        
        VBox newUserPane = new VBox(10);
        
        HBox newIdPane = new HBox(10);
        newIdPane.setPadding(new Insets(10));
        TextField newIdInput = new TextField(); 
        Label newIdLabel = new Label("Id");
        newIdLabel.setPrefWidth(100);
        newIdPane.getChildren().addAll(newIdLabel, newIdInput);
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);                
        Label userCreationMessage = new Label();
        
        Button createButton = new Button("create");
        
        Button returnButton = new Button("return");
        returnButton.setPadding(new Insets(10));
        
        returnButton.setOnAction(e->{
            primaryStage.setScene(loginScene);
        }); 
        
        
        createButton.setPadding(new Insets(10));
        
        createButton.setOnAction(e->{
            
            int id = 0;
            boolean mistake = true;
            try {
            
                id = Integer.parseInt(newIdInput.getText());
                
            } catch (Exception ex) {
                mistake = false;
            }
            String name = newNameInput.getText();
 

            if (name.length()<=2 ) {
                userCreationMessage.setText("Name is too short");
                userCreationMessage.setTextFill(Color.RED); 

            } else if (!mistake){
                userCreationMessage.setText("Id is not valid");
                userCreationMessage.setTextFill(Color.RED); 
                
            } else if (myStudiesService.createUser(id, name)){
                
                userCreationMessage.setText("");                
                loginMessage.setText("New User created!");
                loginMessage.setTextFill(Color.GREEN);                
                primaryStage.setScene(loginScene);  

            } else {
                
                userCreationMessage.setText("Your id has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
        }); 

        
        newUserPane.getChildren().addAll(returnButton, userCreationMessage, newIdPane, newNamePane, createButton); 
        newUserScene = new Scene(newUserPane, 300, 350);
        
        
        
        
        // luo päänäkymän
        
        ScrollPane coursesScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(coursesScollbar);
        HBox menuPane = new HBox(10); 
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        
        
        
        Button logoutButton = new Button("Logout"); 
        
        menuPane.getChildren().addAll(menuLabel, space,logoutButton);

        logoutButton.setOnAction(e->{
            
            myStudiesService.logout();
            primaryStage.setScene(loginScene);
        }); 
        
        HBox createForm = new HBox(10);    
        Button newCourse = new Button("New Course");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        
        createForm.getChildren().addAll(spacer, newCourse);

        
        newCourse.setOnAction(e->{
            primaryStage.setScene(newCourseScene);
            
        });

        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        coursesScene = new Scene(mainPane, 300, 350);
        
      
        
        
        // luo kurssinluomisnäkymän, newCourseScene

        VBox coursePane = new VBox(10);

        Button returnCoursesButton = new Button("Return"); 

        returnCoursesButton.setOnAction(e->{

            primaryStage.setScene(coursesScene);
        }); 
        
        HBox newCourseIdPane = new HBox(10);
        
        HBox newCourseNamePane = new HBox(10);
        newCourseNamePane.setPadding(new Insets(10));
        newCourseIdPane.setPadding(new Insets(10));
        TextField newCourseIdInput = new TextField(); 
        Label newCourseIdLabel = new Label("Id");
        newCourseIdLabel.setPrefWidth(100);
        newCourseIdPane.getChildren().addAll(newCourseIdLabel, newCourseIdInput);
        TextField newCourseNameInput = new TextField();
        Label newCourseNameLabel = new Label("Name");
        newCourseNameLabel.setPrefWidth(100);
        newCourseNamePane.getChildren().addAll(newCourseNameLabel, newCourseNameInput); 
        
        HBox newCourseDescriptionPane = new HBox(10);
        newCourseDescriptionPane.setPadding(new Insets(10));
        TextField newCourseDescriptionInput = new TextField();
        Label newCourseDescriptionLabel = new Label("Description");
        newCourseDescriptionLabel.setPrefWidth(100);
        newCourseDescriptionPane.getChildren().addAll(newCourseDescriptionLabel, newCourseDescriptionInput); 
        
        HBox newCourseCreditsPane = new HBox(10);
        newCourseCreditsPane.setPadding(new Insets(10));
        TextField newCourseCreditsInput = new TextField();
        Label newCourseCreditsLabel = new Label("Credits");
        newCourseCreditsLabel.setPrefWidth(100);
        newCourseCreditsPane.getChildren().addAll(newCourseCreditsLabel, newCourseCreditsInput); 
        
        
        Label courseCreationMessage = new Label();
      

        Button courseCreateButton = new Button("Create");
        
        courseCreateButton.setOnAction(e->{
            
            int id = 0;
            int credits = 0;
            boolean exists = true;
            boolean mistake = true;
            try {
            
                id = Integer.parseInt(newCourseIdInput.getText());
                credits = Integer.parseInt(newCourseCreditsInput.getText());
                
            } catch (Exception ex) {
                 mistake= false;
            }
            String courseName = newCourseNameInput.getText();
            String description = newCourseDescriptionInput.getText();
            
            if (!mistake) {
                courseCreationMessage.setText("Id or credits is not valid");
                courseCreationMessage.setTextFill(Color.RED); 

            } else if (myStudiesService.doesCourseExist(id)) {
                courseCreationMessage.setText("This course id already exixts");
                
                if (myStudiesService.userHasCourse(id)) {
                    // ilmoita että sulla on jo kurssi
                    primaryStage.setScene(coursesScene);
                } else {
                    myStudiesService.createRelation(id);
                    // ilmoita että kurssi id oli varattu, kurssi luotu
                    primaryStage.setScene(coursesScene);
                }
                
            } else if (courseName.length() <= 2 ) {
                courseCreationMessage.setText("Name is too short");
                courseCreationMessage.setTextFill(Color.RED); 
                
            
                
            } else
                
                myStudiesService.createCourse(id, courseName, description, credits);
                courseCreationMessage.setText("");                
//                coursesMessage.setText("New Course created!");
//                loginMessage.setTextFill(Color.GREEN);                
                primaryStage.setScene(coursesScene);  
        
                 
        }); 

        
        coursePane.getChildren().addAll(returnCoursesButton, courseCreationMessage, newCourseIdPane, newCourseNamePane, newCourseDescriptionPane, newCourseCreditsPane, courseCreateButton); ;

        newCourseScene = new Scene(coursePane, 300, 350);
        
         
        
        // muuta

       
        primaryStage.setTitle("MyStudies");
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

}
