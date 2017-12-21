import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Student extends AcademicPerson {

    // Instance variables
    private int[] grades; // grade for the corresponding course
    private static final int MAX_COURSES = 30; // maximum number of courses
    protected HashMap<String, Integer> courseGrade = new HashMap<String, Integer>();
    private int numCourses;
    private int numGrades;

    // Constructor
    public Student(String name, String address) {
        super(name, address);
        courses = new String[MAX_COURSES];
        grades = new int[MAX_COURSES];
        numCourses = 0;
        numGrades = 0;
    }

    // It adds a course and corresponding grade to the lists.
    // Student can take the same course couple of times. If a course that
    // already exists in the list is given as the input of the method you need
    // to compare the input grade with the one that is saved in the Grades list,
    // the higher grade needs to be saved in the Grades list.
    public void addCourseGrade(String course, int grade) {
        if (numCourses == 0) {
            courses[numCourses] = course;
            grades[numGrades] = grade;
            numCourses++;
            numGrades++;
            System.out.println("Course " + course + " added with the grade " + grade);
        } else {
            for (int i = 0; i < this.numCourses; i++) {
                if (!courses[i].equals(course) && i == numCourses - 1) {
                    courses[numCourses] = course;
                    grades[numGrades] = grade;
                    numCourses++;
                    numGrades++;
                    System.out.println("Course " + course + " added with the grade " + grade);
                    break;
                } else if (courses[i].equals(course) && grades[i] != 0) {
                    if (grades[i] <= grade) {
                        courses[numCourses] = course;
                        grades[i] = 0;
                        grades[numGrades] = grade;
                        numCourses++;
                        numGrades++;
                        System.out.println("Course " + course + " added with the grade " + grade);
                        break;
                    } else if (grades[i] > grade) {
                        courses[numCourses] = course;
                        grades[numGrades] = 0;
                        numCourses++;
                        numGrades++;
                        break;
                    }
                }
            }
        }
    }

    // This method prints the student's average grade for all the courses.
    // This method throws IllegalDivisionByZero exception, when there is no
    // courses in the list.
    public void getAverageGrade() throws IllegalDivisionByZero {
        double result = 0.0;
        int numOfGrades = 0;

        if (numGrades == 0) {
            throw new IllegalDivisionByZero("Division by zero!");
        } else {
            for (int i = 0; i < numGrades; i++) {
                if (grades[i] > 0) {
                    result += grades[i];
                    numOfGrades++;
                }
            }
            result = result / numOfGrades;
            System.out.println("Average is: " + result);
        }
    }

    // It prints all the courses with the corresponding grades in each line.
    @Override
    public void printCourses() {
        for (int j = 0; j < MAX_COURSES; j++) {
            if (grades[j] != 0) {
                courseGrade.put(courses[j], grades[j]);
            }
        }

        System.out.println("CourseName\t\t\tCourseGrade");
        Set set = courseGrade.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + "\t\t\t\t\t");
            System.out.println(me.getValue());
        }
    }

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}