package com.example.tuitionmanagergui;

/**
 The Resident class is a subclass of Student which provides methods to create
 Resident objects using no parameters and for manipulating the Resident
 fields.
 @author Victoria Chen, Bridget Zhang
 */
public class Resident extends Student {
    /**
     * Integer representing how much a student has in scholarships.
     */
    private int scholarship;
    /**
     * Integer representing an initial value of 0.
     */
    private int INITVALUE = 0;

    /**
     Constructs a new Student Resident initialized with 0 scholarship.
     * @param studentparam string that creates Student super
     */
    public Resident(String studentparam) {
        // todo check and correct constructor
        super(studentparam);
        scholarship = INITVALUE;
    }

    /**
     Constructs a new Student Resident initialized with 0 scholarship and
     profile parameter.
     * @param studentProf Profile for the Resident Student object.
     */
    public Resident(Profile studentProf) {
        super(studentProf);
        scholarship = INITVALUE;
    }

    /**
     Assigns a scholarship amount to a Student Resident.
     * @param scholarshipValue integer value of how much scholarship to assign.
     */
    public void assignScholarship(int scholarshipValue) {
        scholarship = scholarshipValue;
    }

    /**
     Returns how much scholarship a Student Resident currently has.
     * @return integer value of scholarship
     */
    public int checkScholarship() {
        return scholarship;
    }

    /**
     Returns the amount of tuition due for the student. Tuition is calculated
     based on the student's residential status and scholarship amount if
     applicable.
     * @param creditsEnrolled integer value of how many credits the student
     *                        is enrolled for.
     * @return double representing how much tuition the student has.
     */
    public double tuitionDue(int creditsEnrolled) {
        int PARTTTIME = 12;
        int FULLTIME = 16;
        int tuitionSum = INITVALUE;

        if (creditsEnrolled < PARTTTIME) {
            tuitionSum += (Tuition.PTRTUITION.fee * creditsEnrolled)
                    + Tuition.PTUNI.fee;
        } else {
            tuitionSum += Tuition.FRTUITION.fee + Tuition.FUNI.fee - scholarship;
            if (creditsEnrolled > FULLTIME) {
                tuitionSum += (Tuition.PTRTUITION.fee * (creditsEnrolled - FULLTIME));
            }
        }
        return tuitionSum;
    }

    /**
     Method always returns true to indicate a Resident student is classified
     as resident.
     * @return true always.
     */
    public boolean isResident() {
        return true;
    }

    /**
     Method returns what type of student this object is.
     * @return Resident as a string.
     */
    public String returnType() {
        return "Resident";
    }

}
