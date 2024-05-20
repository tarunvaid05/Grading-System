/**
 @author Tarun Vaidhyanathan
 115510562
 R02
 */
import java.io.Serializable;
/**
 * The Course class represents a course entity with department, number, and semester information.
 * It implements the Serializable interface to support object serialization.
 */
public class Course implements Serializable {
    /** Department of the course */
    private String department;
    /** Number of the course */
    private int number;
    /** Semester of the course */
    private String semester;

    /**
     * Constructs a new Course object with the specified department, number, and semester.
     * @param department The department of the course.
     * @param number     The number of the course.
     * @param semester   The semester in which the course is offered.
     */
    public Course(String department, int number, String semester){
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    /**
     * Returns the department of the course.
     * @return The department of the course.
     */
    public String getDepartment(){
        return this.department;
    }
    /**
     * Sets the department of the course.
     * @param department The new department to be set.
     */
    public void setDepartment(String department){
        this.department = department;
    }
    /**
     * Returns the number of the course.
     * @return The number of the course.
     */
    public int getNumber(){
        return this.number;
    }
    /**
     * Sets the number of the course.
     * @param number The new number to be set.
     */
    public void setNumber(int number){
        this.number = number;
    }
    /**
     * Returns the semester in which the course is offered.
     * @return The semester in which the course is offered.
     */
    public String getSemester(){
        return this.semester;
    }
    /**
     * Sets the semester of the course.
     * @param semester The new semester to be set.
     */
    public void setSemester(String semester){
        this.semester = semester;
    }
    /**
     * Prints the formatted semester string.
     * @return The formatted semester string (e.g., "Fall 2024").
     */
    public String printSemester(){
        String str = "";
        if(this.semester.charAt(0) == 'F'){
            str += ("Fall ");
        }
        else{
            str += ("Spring ");
        }
        str += (this.semester.substring(1,this.semester.length()));
        return str;
    }
}
