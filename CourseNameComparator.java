/**
 @author Tarun Vaidhyanathan
 115510562
 R02
 */
import java.util.Comparator;
/**
 * The CourseNameComparator class implements the Comparator interface to compare courses based on their names.
 * It compares courses by their department names and course numbers.
 */
public class CourseNameComparator implements Comparator<Course>{
    /**
     * Compares two courses based on their names.
     * @param left  The first course to be compared.
     * @param right The second course to be compared.
     * @return A negative integer, zero, or a positive integer as the first course's name is less than, equal to,
     *         or greater than the second course's name, respectively.
     */
    @Override
    public int compare(Course left, Course right){
        if(left.getDepartment().equals(right.getDepartment())){
            if(left.getNumber() == right.getNumber()){
                return 0;
            }
            else if(left.getNumber() > right.getNumber()){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            return left.getDepartment().compareTo(right.getDepartment());
        }
    }
}

