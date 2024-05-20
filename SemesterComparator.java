/**
@author Tarun Vaidhyanathan
115510562
R02
 */
import java.util.Comparator;
/**
 * The SemesterComparator class implements the Comparator interface to compare courses based on their semesters.
 * It compares courses by their seasons and years.
 */
public class SemesterComparator implements Comparator<Course> {
    /**
     * Compares two courses based on their semesters.
     * @param left  The first course to be compared.
     * @param right The second course to be compared.
     * @return A negative integer, zero, or a positive integer as the first course's semester is less than, equal to,
     *         or greater than the second course's semester, respectively.
     */
    @Override
    public int compare(Course left, Course right){
        String leftSeason = left.getSemester().substring(0,2);
        String rightSeason = right.getSemester().substring(0,2);
        int leftYear = Integer.parseInt(left.getSemester().substring(1));
        int rightYear = Integer.parseInt(right.getSemester().substring(1));

        if(leftYear == rightYear){
            return rightSeason.compareTo(leftSeason);
        }
        else if(leftYear > rightYear){
                return 1;
        }
        else{
            return -1;
        }
    }
}
