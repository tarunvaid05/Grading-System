/**
 @author Tarun Vaidhyanathan
 115510562
 R02
 */
import java.io.*;
import java.util.*;

/**
 * Lunar System is a simulator that mimics a class registration portal
 */
public class LunarSystem {
    /** Hashmap to store students and their respective webid */
    private static HashMap<String, Student> database = new HashMap<>(0);
    /**
     * Main method to start the LunarSystem program.
     * It loads previous data if available, displays the menu, and handles user inputs.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Student[] currentStudents = new Student[0];
        Course[] currentCourses = new Course[0];
        System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school.");
        try {
            FileInputStream file = new FileInputStream("Lunar.ser");
            ObjectInputStream inStream = new ObjectInputStream(file);
            database = (HashMap<String, Student>) inStream.readObject();
            inStream.close();
            int stCount = 0;
            for(Student st: database.values()){
                stCount++;
            }
            Student[] tempStudent = new Student[stCount];
            String[] webidArr = database.keySet().toArray(new String[0]);
            for(int i = 0; i < webidArr.length; i++){
                tempStudent[i] = database.get(webidArr[i]);
            }
            currentStudents = tempStudent;

            int courseCount = 0;
            for(int i = 0; i < tempStudent.length; i++){
                for(int j = 0; j < tempStudent[i].getCourses().size(); j++){
                    courseCount++;
                }
            }
            Course[] tempCourse = new Course[courseCount];
            int addcount = 0;
            for(int i = 0; i < tempStudent.length; i++){
                for(int j = 0; j < tempStudent[i].getCourses().size(); j++){
                    tempCourse[addcount] = tempStudent[i].getCourses().get(j);
                    addcount++;
                }
            }
            currentCourses = tempCourse;
            System.out.println("Previous data loaded.");
        }
        catch (Exception e){
            System.out.println("No previous data found.");
        }

        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Menu:");
            System.out.println("\tL)Login");
            System.out.println("\tX)Save state and quit");
            System.out.println("\tQ)Quit without saving state.");
            System.out.print("Please select an option: ");
            String choice = input.nextLine().trim().toUpperCase();

            switch (choice){
                case "L":
                    System.out.print("Please enter webid: ");
                    String webid = input.nextLine().trim();
                    if(webid.equalsIgnoreCase("Registrar")){
                        System.out.println("Welcome " + webid);
                        System.out.println("Options:");
                        System.out.println("\tR) Register a student");
                        System.out.println("\tD) De-register a student");
                        System.out.println("\tE) View course enrollment");
                        System.out.println("\tL) Logout");
                        String choice1 = "";
                        while(!(choice1.equals("L"))){
                            System.out.print("Please select an option: ");
                            choice1 = input.nextLine().trim().toUpperCase();
                            switch(choice1){
                                case "R":
                                    System.out.print("Please enter a webid for the new student: ");
                                    String registerWebid = input.nextLine().trim();
                                    if(database.containsKey(registerWebid)){
                                        System.out.print(registerWebid + " is already registered");
                                    }
                                    else{
                                        Student temp = new Student(registerWebid);
                                        database.put(registerWebid,temp);
                                        Student[] tempArr = new Student[currentStudents.length + 1];
                                        int length = 0;
                                        while(length < currentStudents.length){
                                            tempArr[length] = currentStudents[length];
                                            length++;
                                        }
                                        tempArr[tempArr.length-1] = temp;
                                        currentStudents = tempArr;
                                        System.out.print(registerWebid + " registered");
                                    }
                                    System.out.println();
                                    break;
                                case "D":
                                    System.out.print("Please enter a webid for the student to be deregistered: ");
                                    String deregisterWebid = input.nextLine();
                                    if(database.containsKey(deregisterWebid)){
                                        database.remove(deregisterWebid);
                                        Student[] tempArr = new Student[currentStudents.length - 1];
                                        int counter = 0;
                                        if(currentStudents.length == 1){
                                            currentStudents = tempArr;
                                        }
                                        else{
                                            for(int i = 0; i < currentStudents.length; i++){
                                                if(currentStudents[i].getWebID() != deregisterWebid){
                                                    tempArr[counter] = currentStudents[i];
                                                    counter++;
                                                }
                                            }
                                            currentStudents = tempArr;
                                        }
                                        System.out.println(deregisterWebid + " deregistered");
                                    }
                                    else{
                                        System.out.println("Error: Could not find student in database");
                                    }
                                    break;
                                case "E":
                                    System.out.print("Please enter course department: ");
                                    String department = input.nextLine();
                                    System.out.print("Please enter course number: ");
                                    int number = input.nextInt();
                                    for(int k = 0; k < currentCourses.length; k++){
                                        if(currentCourses[k].getDepartment().equals(department) && currentCourses[k].getNumber() == number){
                                            System.out.println("Students Registered in " + department + " " + number);
                                        }
                                    }
                                    System.out.println("Student    Semester");
                                    System.out.println("--------------------");
                                    for(int i = 0; i < currentStudents.length; i++){
                                        for (int j = 0; j < currentStudents[i].getCourses().size(); j++){
                                            if(currentStudents[i].getCourses().get(j).getDepartment().equalsIgnoreCase(department)
                                                    && currentStudents[i].getCourses().get(j).getNumber() == number){
                                                System.out.println(currentStudents[i].getWebID() + "      " + currentStudents[i].getCourses().get(j).getSemester());
                                            }
                                        }
                                    }
                                    break;
                                case "L":
                                    System.out.println(webid + " logged out");
                                    break;
                            }
                        }
                    }

                    else if(database.containsKey(webid)){
                        System.out.println("Welcome " + webid);
                        System.out.println("Options:");
                        System.out.println("\tA)Add a class");
                        System.out.println("\tD)Drop a class");
                        System.out.println("\tC)View your classes sorted by course name/department");
                        System.out.println("\tS)View your courses sorted by semester");
                        String choice1 = "";
                        while(!(choice1.equals("L"))){
                            System.out.print("Please select an option: ");
                            choice1 = input.nextLine().trim().toUpperCase();
                            switch(choice1){
                                case "A":
                                    System.out.print("Please enter course department: ");
                                    String addDepartment = input.nextLine();
                                    System.out.print("Please enter course number: ");
                                    int addNumber = input.nextInt();
                                    input.nextLine();
                                    System.out.print("Please select a semester: ");
                                    String addSemester = input.nextLine().trim();

                                    Course[] temp = new Course[currentCourses.length + 1];
                                    int length = 0;
                                    while(length < currentCourses.length){
                                        temp[length] = currentCourses[length];
                                        length++;
                                    }
                                    temp[temp.length - 1] = new Course(addDepartment, addNumber, addSemester);
                                    currentCourses = temp;
                                    database.get(webid).addCourse(currentCourses[currentCourses.length - 1]);
                                    System.out.println("Added course" + addDepartment + " " + addNumber + " for semester " + currentCourses[currentCourses.length - 1].printSemester());
                                    break;
                                case "D":
                                    System.out.print("Please enter course department: ");
                                    String dropDepartment = input.nextLine();
                                    System.out.print("Please enter course number: ");
                                    int dropNumber = input.nextInt();
                                    input.nextLine();

                                    Student dropStudent = database.get(webid);
                                    int index = -1;
                                    for(int i = 0; i < dropStudent.getCourses().size(); i++){
                                        if(dropStudent.getCourses().get(i).getDepartment().equals(dropDepartment) && dropStudent.getCourses().get(i).getNumber() == dropNumber){
                                            index = i;
                                        }
                                    }
                                    if(index != -1){
                                        String droppedSem = dropStudent.getCourses().get(index).printSemester();
                                        dropStudent.getCourses().remove(index);
                                        System.out.println("removed 1 course(s) from student " + webid);
                                    }
                                    else{
                                        System.out.println("removed 0 course(s) from student " + webid);
                                    }
                                    break;
                                case "C":
                                    System.out.println("Dept. Course Semester");
                                    System.out.println("-------------------------------");
                                    ArrayList<Course> printCourses = database.get(webid).getCourses();
                                    CourseNameComparator comparator = new CourseNameComparator();
                                    Collections.sort(printCourses, comparator);
                                    for(int i = 0; i <printCourses.size(); i++){
                                        System.out.print(printCourses.get(i).getDepartment() + " " + printCourses.get(i).getNumber() + " " + printCourses.get(i).getSemester());
                                        System.out.println();
                                    }
                                    break;
                                case "S":
                                    System.out.println("Dept. Course Semester");
                                    System.out.println("-------------------------------");
                                    ArrayList<Course> printCourses1 = database.get(webid).getCourses();
                                    SemesterComparator comparator1 = new SemesterComparator();
                                    Collections.sort(printCourses1, comparator1);
                                    for(int i = 0; i <printCourses1.size(); i++){
                                        System.out.print(printCourses1.get(i).getDepartment() + " " + printCourses1.get(i).getNumber() + " " + printCourses1.get(i).getSemester());
                                        System.out.println();
                                    }
                                    break;
                                case "L":
                                    System.out.println(webid + " logged out");
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Could not find user");
                    }
                    break;
                case "X":
                    File saveFile = new File("Lunar.ser");
                    try{
                        FileOutputStream file = new FileOutputStream(saveFile);
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(database);
                        outStream.close();
                        System.out.println("System state saved, system shut down for maintenance");
                        System.exit(0);
                    }
                    catch (IOException e){
                        System.out.println("Nothing to Save");
                    }
                    break;
                case "Q":
                    System.out.println("Good bye, please pick the right SUNY next time!");
                    System.exit(0);
                    break;
            }
        }
    }

}
