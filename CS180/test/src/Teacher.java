import java.util.HashMap;

// Define class Teacher, subclass of AcademicPerson
public class Teacher extends AcademicPerson {
	// Instance variables
	private static final int MAX_COURSES = 10; // maximum courses
    protected HashMap<String, Integer> courseHour = new HashMap<String, Integer>();
	private int numCourses;

	// Constructor
	public Teacher(String name, String address) {
		super(name, address);
		courses = new String[MAX_COURSES];
        this.numCourses = 0;
	}

	
	// It adds a course into the list of courses.
	// This method throws ArrayElementException when the course that is being
	// added to the list already exists in it.
	public void addCourse(String course) throws ArrayElementException {
		if (numCourses != MAX_COURSES) {
            if (numCourses == 0) {
                courses[numCourses] = course;
                numCourses++;
            } else {
                for (int i = 0; i < numCourses; i++) {
                    if (courses[i].equals(course)) {
                        throw new ArrayElementException("Courses Already in list!");
                    } else if (i == numCourses - 1 && !courses[i].equals(course)) {
                        courses[numCourses] = course;
                        numCourses++;
                        break;
                    }
                }
            }
        }
	}

	// It removes a course into the list of courses.
	// This method throws ArrayElementException when the course does not exist
	// in the list.
	public void removeCourse(String course) throws ArrayElementException {
        if (numCourses == 0) {
            throw new ArrayElementException("Courses not found!");
        } else {
            for (int i = 0; i < numCourses; i++) {
                if (courses[i].equals(course)) {
                    courses[i] = null;
                    numCourses--;
                    break;
                } else if (i == numCourses - 1 && !courses[i].equals(course)) {
                    throw new ArrayElementException("Courses not found!");
                }
            }

            for (int j = 0; j < MAX_COURSES; j++) {
                if (courses[j] == null && j < MAX_COURSES - 1) {
                    courses[j] = courses[j + 1];
                    courses[j + 1] = null;
                }
            }
        }
    }

	// It prints all the courses in the list in each line
	@Override
	public void printCourses() {
        System.out.println("Courses teaching this semester: ");
        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i]);
        }
	}

	@Override
	public String toString() {
		return "Teacher: " + super.toString();
	}

}