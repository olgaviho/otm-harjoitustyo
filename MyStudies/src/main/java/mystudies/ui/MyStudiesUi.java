
package mystudies.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import mystudies.domain.Course;
import mystudies.domain.MyStudiesService;

/**
 *
 * @author olgaviho
 */
public class MyStudiesUi extends Application {
    
    private MyStudiesService myStudiesService;
    private Scene coursesScene;
    private Scene newCourseScene;
    private Scene newCompletedCourseScene;
    private Scene loginScene;
    private Scene newUserScene;
    private Scene deleteScene;
    private VBox courseNodes;
    private List<String> allCourses;
    private List<String> userCourses;
    private Label menuLabel = new Label();
    private Label userMean = new Label();
    private ComboBox allSystemCourses;
    private ComboBox onlyUserCourses;
    

    @Override
    public void init() throws Exception {
        

        Database database = new Database("jdbc:sqlite:mycourses.db");
        
        createTables(database);
        
        DatabaseUserDao userDao = new DatabaseUserDao(database);
        DatabaseCourseDao courseDao = new DatabaseCourseDao(database);
        DatabaseCourseUserDao usersAndCourses = new DatabaseCourseUserDao(database);
        this.myStudiesService = new MyStudiesService(courseDao, userDao, usersAndCourses);
        allSystemCourses = new ComboBox();
        onlyUserCourses = new ComboBox();     
        
    }
    
    public static void createTables(Database database) throws SQLException {
   
        Connection conn = database.getConnection();
    
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE if not exists users (id integer PRIMARY KEY, name varchar(20))");
        PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE if not exists courses (courseid integer PRIMARY KEY, name varchar(20), description varchar(20), credits integer, grade integer)");
        PreparedStatement stmt3 = conn.prepareStatement("CREATE TABLE if not exists usersandcourses (userid integer, courseid integer, grade integer, foreign key(courseid) references courses(courseid), foreign key(userid) references users(id))");

        stmt.execute();
        stmt2.execute();
        stmt3.execute();
    }
    
     
    public static void main(String[] args) {
        launch(MyStudiesUi.class);
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // creates loginScene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Id");
        TextField loginInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, loginInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Login");
        
        loginButton.setOnAction(e-> {
            
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
                
            } else if (myStudiesService.login(id)) {
                
                loginMessage.setText("");
                redrawCourselist();
                updateMean();

                primaryStage.setScene(coursesScene); 

                loginInput.setText("");
                
            } else {
                
                loginMessage.setText("User does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        }); 
        
        Button createNewUserButton = new Button("Create new user");
        
        createNewUserButton.setOnAction(e-> {
            loginInput.setText("");
            loginMessage.setText("");
            primaryStage.setScene(newUserScene);   
        }); 
         
        
       
        loginPane.getChildren().addAll(loginMessage, loginButton, inputPane, createNewUserButton);       
        
        loginScene = new Scene(loginPane, 450, 400);    
        

        // creates newUserScene
        
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
        
        Button createButton = new Button("Create");
        
        Button returnButton = new Button("Return");
        returnButton.setPadding(new Insets(10));
        
        returnButton.setOnAction(e-> {
            primaryStage.setScene(loginScene);
            updateMean();
            
        }); 
        
        
        createButton.setPadding(new Insets(10));
        
        createButton.setOnAction(e-> {
            
            int id = 0;
            boolean idNotValid = true;
            try {
            
                id = Integer.parseInt(newIdInput.getText());
                
            } catch (Exception ex) {
                idNotValid = false;
            }
            
            String name = newNameInput.getText();
 

            if (name.length() <= 2) {
                userCreationMessage.setText("Name is too short");
                userCreationMessage.setTextFill(Color.RED); 

            } else if (!idNotValid) {
                userCreationMessage.setText("Id is not valid");
                userCreationMessage.setTextFill(Color.RED); 
                
            } else if (myStudiesService.createUser(id, name)) {
                
                userCreationMessage.setText("");                
                loginMessage.setText("New user created!");
                loginMessage.setTextFill(Color.GREEN);                
                primaryStage.setScene(loginScene);  
                newNameInput.setText("");
                newIdInput.setText("");
                updateMean();

            } else {
                
                userCreationMessage.setText("Your id has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
        }); 

        
        newUserPane.getChildren().addAll(returnButton, userCreationMessage, newIdPane, newNamePane, createButton); 
        newUserScene = new Scene(newUserPane, 450, 400);

        // creates coursesScene
        
        ScrollPane coursesScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(coursesScollbar);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox menuPane = new HBox(20); 
        
        Label courseMessage = new Label();
        
        
        allCourses = new ArrayList<>();
        userCourses = new ArrayList<>();
        
        Button logoutButton = new Button("Logout"); 

        
        menuPane.getChildren().addAll(courseMessage, spacer, userMean, logoutButton);

        logoutButton.setOnAction(e-> {
            
            String nimi = myStudiesService.getLogged().getName();
            myStudiesService.logOut();
            courseMessage.setText("");    
            loginMessage.setTextFill(Color.GREEN);  
            loginMessage.setText("Hope to see you soon " + nimi);
            primaryStage.setScene(loginScene);
            
        }); 
        
        HBox createForm = new HBox(20);    
        Button newCourse = new Button("New course");
        Button newCompletedCourse = new Button("New completed course");
        Region spaceri = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button delete = new Button("Delete course");

        createForm.getChildren().addAll(delete, spaceri, newCompletedCourse, newCourse);

        newCourse.setOnAction(e-> {
            primaryStage.setScene(newCourseScene);
        });
        
        newCompletedCourse.setOnAction(e-> {
            primaryStage.setScene(newCompletedCourseScene);
            redrawAllCourses();
  
        });

        delete.setOnAction(e-> {
            primaryStage.setScene(deleteScene);
        
            redrawUserCourses();
            
        });
        
        courseNodes = new VBox(10);
        courseNodes.setMaxWidth(280);
        courseNodes.setMinWidth(280);
        redrawCourselist();
        updateMean();
       
        coursesScollbar.setContent(courseNodes);

        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        coursesScene = new Scene(mainPane, 450, 400);
        

        // creates newCompletedCourseScene;
        
        VBox completedCoursePane = new VBox(10);

        Button returnCoursesButton = new Button("Return"); 
        allCourses = new ArrayList<>();
        redrawAllCourses();

        HBox newCompletedCourseIdPane = new HBox(10);
        newCompletedCourseIdPane.setPadding(new Insets(10));
        Label newCompletedCourseIdLabel = new Label("Choose course");
        newCompletedCourseIdLabel.setPrefWidth(100);
        newCompletedCourseIdPane.getChildren().addAll(newCompletedCourseIdLabel, allSystemCourses);


        HBox newCourseGradePane = new HBox(10);
        newCourseGradePane.setPadding(new Insets(10));
        TextField newCourseGradeInput = new TextField();
        Label newCourseGradeLabel = new Label("Grade");
        newCourseGradeLabel.setPrefWidth(100);
        newCourseGradePane.getChildren().addAll(newCourseGradeLabel, newCourseGradeInput); 

        
        Label completedCourseCreationMessage = new Label();
        Button completedCourseCreateButton = new Button("Create");        
        completedCourseCreateButton.setOnAction(e-> {
            
            int id = 0;
            int grade = 0;
            boolean exists = true;
            boolean idOrGrdadeIsNotInteger = true;
            try {
            
                String idAndCourseName = allSystemCourses.getValue().toString();
                String[] parts = idAndCourseName.split(",");
                String idString = parts[0];                
                id = Integer.parseInt(idString);             
                grade = Integer.parseInt(newCourseGradeInput.getText());
                
            } catch (Exception ex) {              
                idOrGrdadeIsNotInteger = false;
            }
                       
            if (!idOrGrdadeIsNotInteger) {
                completedCourseCreationMessage.setText("Id or grade is not valid");
                completedCourseCreationMessage.setTextFill(Color.RED); 

                
            } else if (myStudiesService.userHasCourse(id)) {

                    completedCourseCreationMessage.setText("You already have this course");
                    completedCourseCreationMessage.setTextFill(Color.RED);

                } else {
                    myStudiesService.createRelation(id, grade);
                    completedCourseCreationMessage.setText(""); 
                    courseMessage.setText("You have a new course");
                    newCourseGradeInput.setText("");
                    courseMessage.setTextFill(Color.GREEN); 
                    redrawCourselist();
                    updateMean();

                    newCourseGradeInput.setText("");
                    primaryStage.setScene(coursesScene);
                    
                }

            
        }); 
        
        returnCoursesButton.setOnAction(e-> {

            primaryStage.setScene(coursesScene);
            courseMessage.setText("");
            completedCourseCreationMessage.setText("");
        }); 

        completedCoursePane.getChildren().addAll(returnCoursesButton, completedCourseCreationMessage, newCompletedCourseIdPane, newCourseGradePane, completedCourseCreateButton);
        newCompletedCourseScene = new Scene(completedCoursePane, 450, 400);
        

//      creates newCourseScene

        VBox coursePane = new VBox(10);

        Button returnCoursesSceneButton = new Button("Return"); 

        HBox newCourseIdPane = new HBox(10);
        newCourseIdPane.setPadding(new Insets(10));
        TextField newCourseIdInput = new TextField(); 
        Label newCourseIdLabel = new Label("Id");
        newCourseIdLabel.setPrefWidth(100);
        newCourseIdPane.getChildren().addAll(newCourseIdLabel, newCourseIdInput);
                
        HBox newCourseNamePane = new HBox(10);
        newCourseNamePane.setPadding(new Insets(10));
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
        courseCreateButton.setOnAction(e-> {
            
            int id = 0;
            int credits = 0;
            boolean exists = true;
            boolean idOrCreditsArentInteger = true;
            try {
            
                id = Integer.parseInt(newCourseIdInput.getText());
                credits = Integer.parseInt(newCourseCreditsInput.getText());
                
            } catch (Exception ex) {
                idOrCreditsArentInteger = false;
            }
            
            String courseName = newCourseNameInput.getText();
            String description = newCourseDescriptionInput.getText();
            
            if (!idOrCreditsArentInteger) {
                courseCreationMessage.setText("Id or credits is not valid");
                courseCreationMessage.setTextFill(Color.RED); 

            } else if (myStudiesService.doesCourseExist(id)) {
                
                courseCreationMessage.setText("Id has to be unique");
                courseCreationMessage.setTextFill(Color.RED);
                courseMessage.setTextFill(Color.RED);

                
            } else if (courseName.length() <= 2) {
                courseCreationMessage.setText("Name is too short");
                courseCreationMessage.setTextFill(Color.RED); 
                
            } else {
                
                myStudiesService.createCourse(id, courseName, description, credits);
                courseCreationMessage.setText("");                
                courseMessage.setText("New Course created!");
                courseMessage.setTextFill(Color.GREEN);                
                primaryStage.setScene(coursesScene);  

                newCourseNameInput.setText("");
                newCourseIdInput.setText("");
                newCourseDescriptionInput.setText("");
                newCourseCreditsInput.setText("");
            }                 
        }); 

        coursePane.getChildren().addAll(returnCoursesSceneButton, courseCreationMessage, newCourseIdPane, newCourseNamePane, newCourseDescriptionPane, newCourseCreditsPane, courseCreateButton);
        newCourseScene = new Scene(coursePane, 450, 400);   
        
        returnCoursesSceneButton.setOnAction(e-> {

            primaryStage.setScene(coursesScene);
            courseMessage.setText("");
            courseCreationMessage.setText("");
        }); 
       
//       creates deleteCourseScene

        userCourses = new ArrayList<>();
        redrawUserCourses();
        VBox deletePane = new VBox(10);
        Button coursesSceneButton = new Button("Return"); 
        
        HBox deleteIdPane = new HBox(10);
        
        deleteIdPane.setPadding(new Insets(10));
        Label deleteIdLabel = new Label("Choose course:");
        deleteIdLabel.setPrefWidth(100);
        deleteIdPane.getChildren().addAll(deleteIdLabel, onlyUserCourses);
        
        Label courseDeleteMessage = new Label();
      

        Button courseDeleteButton = new Button("Delete");        
        courseDeleteButton.setOnAction(e-> {
            
            int id = 0;
            boolean chooseCourse = true;
            try {
            
                String usersIdAndCourseName = onlyUserCourses.getValue().toString();
                String[] parts = usersIdAndCourseName.split(",");
                String idString = parts[0];
                id = Integer.parseInt(idString);
                
            } catch (Exception ex) {
                chooseCourse = false;
            }

            
            if (!chooseCourse) {
                courseDeleteMessage.setText("Choose a course");
                courseDeleteMessage.setTextFill(Color.RED); 


            } else if (myStudiesService.deleteCourse(id)) {
                courseDeleteMessage.setText(""); 
                courseMessage.setText("Course deleted!");
                courseMessage.setTextFill(Color.GREEN); 
                 
                updateMean();
                redrawAllCourses();
                redrawCourselist();
            
                primaryStage.setScene(coursesScene);
                    
            } else {
                
                courseDeleteMessage.setText("Fail");
                courseDeleteMessage.setTextFill(Color.RED);
                courseDeleteMessage.setTextFill(Color.RED);                
            }                 
        }); 

        coursesSceneButton.setOnAction(e-> {
            primaryStage.setScene(coursesScene);
            courseMessage.setText("");
            courseDeleteMessage.setText("");
        }); 
        
        deletePane.getChildren().addAll(coursesSceneButton, courseDeleteMessage, deleteIdPane, courseDeleteButton);
        deleteScene = new Scene(deletePane, 450, 400);

        // other things

        primaryStage.setTitle("MyStudies");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
 

    public void redrawCourselist() {
        courseNodes.getChildren().clear(); 
        
        List<Course> courses = myStudiesService.getYourCourses();
        List<Integer> courseGrades = myStudiesService.getYourGrades();
        
        if (courses == null) {
            courses = new ArrayList<>();
        }
        
        if (courseGrades == null) {
            courseGrades = new ArrayList<>();
        }
        
        int gradeIndex = 0;        
        for (Course course: courses) {            
            courseNodes.getChildren().add(createCourseNode(course, courseGrades.get(gradeIndex)));
            gradeIndex = gradeIndex + 1;            
        }
    }
    
    public void redrawAllCourses() {
        
        allCourses.clear();
        List<Course> courses = myStudiesService.getAllCourses();
        
        if (courses == null) {
            courses = new ArrayList<>();
        }
        
        for (Course course: courses) {
            
            allCourses.add(createStringForCourse(course)); 
        }    
        allSystemCourses.setItems(FXCollections.observableArrayList(allCourses));
        
    }
    public void redrawUserCourses() {
        
        userCourses.clear();
      
        List<Course> courses = myStudiesService.getYourCourses();
        
        if (courses == null) {
            courses = new ArrayList<>();
        }       
        for (Course course: courses) {            
            userCourses.add(createStringForCourse(course));            
        }        
        onlyUserCourses.setItems(FXCollections.observableArrayList(userCourses));
    }

    public void updateMean() {
        
        double mean = myStudiesService.getMean();        
        String creditsText = "Your mean: " + mean;        
        userMean.setText(creditsText);
    }

    public Node createCourseNode(Course course, int grade) {
        HBox box = new HBox(10);
        
        String teksti = course.getId() + ", " + course.getName() + ", " + course.getDescription() + ", " + course.getCredits() + ", " + grade;
        
        Label courseLabel  = new Label(teksti);
        courseLabel.setMinHeight(28);
        courseLabel.setMinWidth(350);
             
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));
        
        box.getChildren().addAll(courseLabel, spacer);
        return box;
    }
    
    public String createStringForCourse(Course course) {
        
        String informations = course.getId() + ", " + course.getName();
        return informations;
    }

}
