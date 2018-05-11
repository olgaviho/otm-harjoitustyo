
package mystudies.dao;

import java.sql.SQLException;
import java.util.List;


/**
 * This is an interface for DatabaseCourseUserDao.
 *
 * @author olgaviho
 * @param <C> id of the completed course
 * @param <U> id of the user
 * @param <G> grade of the completed course
 */


public interface CourseUserDao<U, C, G> {
    
    /**
    * This method saves the course.
    *
    * @param userkey key for the user
    * @param coursekey key for the completed course
    * @return true if asked keys were in the database, else false
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    boolean findOne(Integer userkey, Integer coursekey) throws SQLException;
    
    /**
    * This method saves the userkey, the coursekey and the grade.
    *
    * 
    * @param userkey id of the user
    * @param coursekey id of the completed course
    * @param grade grade of the completed course
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    void save(Integer userkey, Integer coursekey, Integer grade) throws SQLException;
    
    /**
    * This method returns all the keys in the database.
    *
    * @param userkey key for the user
    * @return list of keys
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    List<Integer> findAllIds(Integer userkey) throws SQLException;
    
    /**
    * This method returns all the grades in the database.
    *
    * @param userkey key for the user
    * @return list of grades
    * 
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    List<Integer> findAllGrades(Integer userkey) throws SQLException;
    
    /**
    * This method deletes the userkey, the coursekey and the grade.
    *
    * 
    * @param userkey id of the user
    * @param coursekey id of the completed course
    * @throws java.sql.SQLException if there is a problem in the database
    * 
    */
    
    void deleteCourse(Integer userkey, Integer coursekey) throws SQLException;
    
}
