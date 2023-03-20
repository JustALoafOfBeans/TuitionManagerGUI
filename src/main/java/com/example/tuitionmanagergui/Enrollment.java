package com.example.tuitionmanagergui;

/**
 The Enrollment class provides methods to create Enrollment objects with no
 parameters and for manipulating the Enrollment fields. An Enrollment object
 holds an array-equivalent of EnrollStudent objects.
 @author Bridget Zhang
 */
public class Enrollment {
    /**
     * Array of students in enrollment
     */
    private EnrollStudent[] enrollStudents;
    /**
     * Number of students currently enrolled
     */
    private int size;
    /**
     * Start size for enrollment array
     */
    private static final int STARTSIZE = 0;

    /**
     Constructor for Enrollment class
     */
    public Enrollment() {
        size = STARTSIZE;
        enrollStudents = new EnrollStudent[size];
    }

    /**
     Adds a new student to the enrollStudents array
     * @param enrollStudent new student to add
     */
    public void add(EnrollStudent enrollStudent) {
        // Check if student in roster (if not, print error)
        // Check to see if student already enrolled
        int foundIndex = find(enrollStudent);
        if (foundIndex >= 0) {
            // Student found in enrollment, update credits but do not add
            enrollStudents[foundIndex].setCredits(enrollStudent.getCredits());
        } else {
            if (enrollStudents.length == size) { // Enrollment full, resize
                grow();
            }
            enrollStudents[size] = enrollStudent; // Add to end
            size += 1; // Increase count
        }
    }

    /**
     Method that increases capacity of enrollStudents array
     */
    private void grow () {
        // Increase array capacity by 4
        EnrollStudent[] enrollStudentsNew = new EnrollStudent[enrollStudents.length + 4];
        for (int ind = 0; ind < size; ind++) {
            enrollStudentsNew[ind] = enrollStudents[ind];
        }
        enrollStudents = enrollStudentsNew;
    }

    /**
     Removes a specified student from the enrollment list
     * @param enrollStudent target student to remove
     */
    public void remove(EnrollStudent enrollStudent) {
        int remIndex = find(enrollStudent); // Index to remove at
        if (remIndex < 0) { // Not in enrollment, nothing to remove
            return; // todo error?
        }
        size -= 1; // Decrease enrollment count
        // For every index at and beyond remIndex, move up
        for (int ind = remIndex; ind < enrollStudents.length; ind++) {
            if (ind == enrollStudents.length-1 || enrollStudents[ind + 1] == null) {
                // At end (of array or of non-null indices in array)
                enrollStudents[ind] = null;
                break;
            }
            enrollStudents[ind] = enrollStudents[ind+1];
        }
    }

    /**
     Helper method that finds target student
     * Used by add(), remove(), and find() methods
     * @param enrollStudent target student
     * @return index at which target student found, or -1 if not found
     */
    private int find(EnrollStudent enrollStudent) {
        Profile seekProf = enrollStudent.getProfile(); // Profile of enrollStudent sought
        for (int ind = 0; ind < size; ind++) {
            if (seekProf.compareTo(enrollStudents[ind].getProfile()) == 0) {
                // Found, return index
                return ind;
            }
        }
        return -1; // Not found
    }

    /**
     Method that checks if a target student is contained in the enrollment list
     * @param enrollStudent target student that is being checked for
     * @return true if found in array, false if not
     */
    public boolean contains(EnrollStudent enrollStudent) {
        int containsIndex = find(enrollStudent);
        // Returns "true" if in enrollment at some index >=0 containsIndex
        return (containsIndex >= 0);
    }

    /**
     Method that prints out enrollment in order of addition
     */
    public String print() {
        String printStr = "";
        if (size == STARTSIZE) {
            printStr = "Enrollment is empty!\n";
        } else {
            printStr += "** Enrollment **\n";
            for (int ind = 0; ind < size; ind++) {
                printStr += enrollStudents[ind].toString() + "\n";
            }
            printStr += "** end of enrollment **\n";
        }
        return printStr;
    }

    /**
     Method that prints out enrollment as well as each student's tuition
     * @param rost uses roster of students to get profiles
     */
    public String printTuition(Roster rost) {
        String printStr = "";
        // Get array of roster students
        Student[] rostArr = rost.getStudentArr();
        if (rostArr.length == STARTSIZE) {
            printStr = ("Student roster is empty!\n");
        } else if (size == STARTSIZE) {
            printStr = "Enrollment is empty!\n";
        } else {
            Profile enrollProf;
            int profIndex;
            String tuitionStr;
            double tuitionDouble;
            printStr += "** Tuition due **\n";
            // For each student in enrollArr, find profile in Roster and print details
            for (int eInd = 0; eInd < size; eInd++) {
                enrollProf = enrollStudents[eInd].getProfile();
                // Find student in roster that matches profile
                profIndex = findProfile(enrollProf, rostArr); // todo should not be -1 (not found in roster)

                // Print tuition
                tuitionStr = rostArr[profIndex].getProfile().toString() + " (";
                tuitionStr += rostArr[profIndex].returnType() + ") enrolled ";
                tuitionStr += enrollStudents[eInd].getCredits() + " credits: tuition due: $";
                tuitionDouble = rostArr[profIndex].tuitionDue(enrollStudents[eInd].getCredits());
                tuitionStr += String.format("%,.2f", tuitionDouble);

                printStr += tuitionStr + "\n";
            }
            printStr += "** end of tuition due **\n";
        }
        return printStr;
    }

    /**
     Method to find the student in the roster
     * @param profile profile that should be found and matched
     * @param roster list of students in roster
     * @return index in roster where student with profile is located, or NOT_FOUND = -1 if not found
     */
    private int findProfile (Profile profile, Student[] roster) {
        // For each Student in roster[], check if profile equal
        for (int rIndex = 0; rIndex < roster.length; rIndex++) {
            if (roster[rIndex] == null) {
                break;
            }
            if (roster[rIndex].getProfile().equals(profile)) {
                // Profile found
                return rIndex;
            }
        }
        // Returns -1 if reach end of roster without hit
        return -1;
    }

    /**
     Method returns the EnrollStudent object given a dummy student obj as
     long as the profile matches.
     * @param enrollStudent EnrollStudent object to find.
     * @return Full EnrollStudent object.
     */
    public EnrollStudent getStudent(EnrollStudent enrollStudent) {
        int NOTFOUND = -1;
        int foundIndex = find(enrollStudent);
        if (foundIndex == NOTFOUND) {
            return null;
        }
        return enrollStudents[foundIndex];
    }

    /**
     Method ends the semester and adds the enrolled credits to credits
     completed.
     * @return Profile array of all students enrolled.
     */
    public Profile[] getGraduates() {
        Profile[] students = new Profile[size];
        for (int ind = 0; ind < size; ind++) {
            String studentDetails = enrollStudents[ind].toString();
            String profile[] = studentDetails.split(": ");
            Profile tempProfile = new Profile(profile[0]);
            students[ind] = tempProfile;
        }
        return students;
    }
}
