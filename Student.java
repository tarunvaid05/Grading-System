/**
 @author Tarun Vaidhyanathan
 115510562
 R02
 */
import java.io.*;
import java.util.*;
/**
 * The Student class represents a student entity with a webID and a list of courses.
 * It implements the Serializable interface to support object serialization.
 */
public class Student implements Serializable {
    /** Web ID of student */
    private String webID;
    /** List of courses for students */
    private ArrayList<Course> courses;
    /**
     * Constructs a new Student object with the specified webID.
     * @param webID The webID of the student.
     */
    public Student(String webID){
        this.webID = webID;
        this.courses = new ArrayList<>(0);

    }
    /**
     * Returns the webID of the student.
     * @return The webID of the student.
     */
    public String getWebID(){
        return this.webID;
    }
    /**
     * Sets the webID of the student.
     * @param webID The new webID to be set.
     */
    public void setWebID(String webID){
        this.webID = webID;
    }
    /**
     * Returns the list of courses the student is enrolled in.
     * @return The list of courses the student is enrolled in.
     */
    public ArrayList<Course> getCourses(){
        return this.courses;
    }
    /**
     * Sets the list of courses for the student.
     * @param courses The new list of courses to be set.
     */
    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
    }
    /**
     * Adds a course to the list of courses for the student.
     * @param course The course to be added.
     */
    public void addCourse(Course course){
        courses.add(course);
    }
    /**
     * Removes a course from the list of courses for the student.
     * @param course The course to be removed.
     */
    public void removeCourse(Course course){
        courses.remove(course);
    }
}