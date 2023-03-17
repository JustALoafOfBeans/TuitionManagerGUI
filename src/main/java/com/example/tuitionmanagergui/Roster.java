package com.example.tuitionmanagergui;

/**
 The Roster class is an abstract class which provides methods to create
 Roster objects with no parameters and for manipulating the Roster fields.
 The Roster class is mainly only used in RosterManager to contain a list of
 Student objects to be manipulated.
 @author Bridget Zhang
 */
public class Roster {
    /**
     * Array of Students in roster
     */
    private Student[] roster;
    /**
     * Number of students currently in roster
     */
    private int size;
    /**
     * Constant start size for roster
     */
    private static final int STARTSIZE = 0;

    /**
     Constructor for Roster class
     */
    public Roster() {
        size = STARTSIZE;
        roster = new Student[size];
    }

    /**
     Method to find the student in the roster
     * @param student student that is to be found
     * @return index in roster where student is located, or NOT_FOUND = -1 if not found
     */
    private int find (Student student) {
        // For each Student in roster[], use <Student>.equals() to compare
        for (int rIndex = 0; rIndex < size; rIndex++) {
            if (roster[rIndex].getProfile().equals(student.getProfile())) {
                // Student found
                return rIndex;
            }
        }
        // Returns -1 if reach end of roster without hit
        return -1;
    }

    /**
     Method returns the student object given a dummy student obj as long as
     the profile matches.
     * @param student Student object to find.
     * @return Full student object.
     */
    public Student getStudent(Student student) {
        int NOTFOUND = -1;
        int foundIndex = find(student);
        if (foundIndex == NOTFOUND) {
            return null;
        }
        return roster[foundIndex];
    }

    /**
     Increases the capacity of the array by 4
     */
    private void grow () {
        // Increase array capacity by 4
        Student[] rosterNew = new Student[roster.length + 4];
        for (int rIndex = 0; rIndex < size; rIndex++) {
            rosterNew[rIndex] = roster[rIndex];
        }
        roster = rosterNew;
    }

    /**
     Add student to the end of the array
     * @param student the student to be added to the roster
     * @return true if student valid and added successfully, false otherwise
     */
    public boolean add(Student student) {
        if (!student.isValid()) { // Student not valid, can not be added
            return false;
        }
        // Check if student already in roster
        int lastFullIndex = roster.length - 1; // Assume full to start
        for (int rostInd = 0; rostInd < roster.length; rostInd++) {
            if (roster[rostInd] == null) {
                lastFullIndex = rostInd - 1; // Track last full index
                break;
            }
            if (roster[rostInd].equals(student)) {
                return false; // todo do we need some message to say already there?
            }
        }
        // Is valid and not yet in roster, add accordingly
        if (lastFullIndex == (roster.length - 1)) {
            // Resize of last index full (roster filled)
            grow();
        }
        roster[lastFullIndex + 1] = student;
        size += 1;
        return true;
    }

    /**
     Removes student from array and maintains order
     * @param student student to be removed from roster
     * @return true if removed successfully, false if otherwise
     */
    public boolean remove (Student student) {
        int rem = find(student); // Index to find student at
        if (rem < 0) { // Not found
            return false;
        }
        // Move everything after remIndex up (null for last)
        for (int shift = rem + 1; shift < size; shift++) {
            roster[shift - 1] = roster[shift];
            if (shift == roster.length-1 || roster[shift+1] == null) { // Set last to null
                roster[shift] = null;
            }
        }
        size -= 1;
        return true;
    }

    /**
     Checks if target student can be found in roster
     * @param student target student being sought
     * @return true if student in roster, false if not
     */
    public boolean contains (Student student) {
        int foundIndex = find(student);
        return foundIndex >= 0; // If foundIndex < 0, student not found in roster
    }

    /**
     Prints roster sorted by student profiles
     */
    public void print() {
        // Sort by profile
        sortProfile();
        // Print all elements of sorted roster
        if (size == 0) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by last name, first " +
                    "name, DOB **");
            for (int ind = 0; ind < size; ind++) {
                System.out.println(roster[ind].toString());
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     Print students belonging to a specified school, sorted by profiles
     * @param targetSchool school from which to print students
     */
    public void print(String targetSchool) {
        // Check if targetSchool exists
        boolean schoolFound = false;
        for (Major maj : Major.values()) {
            if (targetSchool.compareTo(maj.school) == 0) {
                schoolFound = true;
                break;
            }
        }
        if (!schoolFound) {
            System.out.println("School doesn't exist: " + targetSchool);
            return;
        }
        // Sort by profile
        sortProfile();
        // Print if student found in school
        if (size == 0) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Students in " + targetSchool + " *");
            for (int ind = 0; ind < size; ind++) {
                if (roster[ind].getMajor().school.compareTo(targetSchool) == 0) {
                    // Print if Student's school matches target
                    System.out.println(roster[ind].toString());
                }
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     Prints roster sorted by student majors (alphabetical) and profile
     */
    public void printBySchoolMajor() {
        // Sort by standing
        sortSchoolMajor();
        if (size == 0) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by school, major **");
            // Print all elements of sorted roster
            for (int ind = 0; ind < size; ind++) {
                System.out.println(roster[ind].toString());
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     Prints roster sorted by student standing (alphabetical) and profile
     */
    public void printByStanding() {
        // Sort by school and major
        sortStanding();
        if (size == 0) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* Student roster sorted by standing **");
            // Print all elements of sorted roster
            for (int ind = 0; ind < size; ind++) {
                System.out.println(roster[ind].toString());
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     Sorts roster by student Profile
     Called to sort by print()
     */
    public void sortProfile() {
        for (int i = 1; i < size; i++) { // todo change counter names (note size not roster.length)
            Student currStudent = roster[i];
            int j = i - 1;
            while (j >= 0 && roster[j].getProfile().compareTo(currStudent.getProfile()) > 0) {
                roster[j+1] = roster[j];
                j -= 1;
            }
            roster[j + 1] = currStudent;
        }
    }

    /**
     Sorts roster by student Major then by School (and if same, by Profile)
     Called to sort by printBySchoolMajor()
     */
    public void sortSchoolMajor () {
        int schoolComp, majorComp; // Value of school comparison
        for (int i = 1; i < size; i++) {
            Student currStudent = roster[i];
            int j = i-1;
            while (j >= 0) {
                // If student at j not greater than student at i, stop swapping
                schoolComp = roster[j].getMajor().school.compareTo(currStudent.getMajor().school);
                if (schoolComp < 0) {
                    break;
                } else if (schoolComp == 0) { // School same, check Major
                    majorComp = roster[j].getMajor().name().compareTo(currStudent.getMajor().name());
                    if (majorComp < 0) {
                        break;
                    } else if (majorComp == 0) { // School and Major same, check Profile
                        if (roster[j].getProfile().compareTo(currStudent.getProfile()) < 0) {
                            break;
                        }
                    }
                }
                // Confirmed roster[j] > roster[i], continue to move down
                roster[j+1] = roster[j];
                j -= 1;
            }
            roster[j+1] = currStudent;
        }
    }

    /**
     Sorts roster by student Standing then Profile
     Called to sort by printByStanding()
     */
    public void sortStanding () {
        int standingComp;
        for (int i = 1; i < size; i++) {
            Student currStudent = roster[i];
            int j = i - 1;
            while (j >= 0) {
                // Check to make sure roster[j] > roster[i] or stop moving down
                standingComp = roster[j].getStanding().name().compareTo(currStudent.getStanding().name());
                if (standingComp < 0) {
                    break;
                } else if (standingComp == 0) { // If standings same, check Profile
                    if (roster[j].getProfile().compareTo(currStudent.getProfile()) < 0) {
                        break;
                    }
                }
                roster[j+1] = roster[j];
                j -= 1;
            }
            roster[j+1] = currStudent;
        }
    }

    /**
     * Changes major of a student given profile
     * @param prof Profile of student whose major should be changed
     * @param strMaj new major as a string
     * @return false if student not found or major invalid, true if successful
     */
    public boolean changeMajor(Profile prof, String strMaj) {
        for (int ind = 0; ind < size; ind++) {
            if (roster[ind].getProfile().compareTo(prof) == 0) {
                if (roster[ind].changeMajor(strMaj)) {
                    // Successfully found student and changed their major
                    return true;
                } else {
                    // Student found but major could not be changed
                    return false;
                }
            }
        }
        return false; // Student not found
    }

    /**
     Swaps elements at indices "x" and "y" in roster
     * @param indX index of first element in swap
     * @param indY index of other element in swap
     */
    private void swapRoster(int indX, int indY) {
        Student swapTemp = roster[indX];
        roster[indX] = roster[indY];
        roster[indY] = swapTemp;
    }

    /**
     Returns array of students in roster
     @return array of students
     */
    public Student[] getStudentArr() {
        return roster;
    }


}
