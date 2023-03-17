package com.example.tuitionmanagergui;

/**
 The NonResident class is a subclass of Student which provides methods to create
 NonResident objects using no parameters and for manipulating the NonResident
 fields.
 @author Victoria Chen, Bridget Zhang
 */
public class NonResident extends Student {
    /**
     * Initial value for tuition
     */
    private int INITVALUE = 0;
    /**
     * Minimum credits a student can take
     */
    private int MINCREDS = 3;
    /**
     * Maximum credits a student can take
     */
    private int MAXCREDS = 24;

    /**
     Constructor for NonResident object
     @param studentParam string for student constructor in FIRST LAST DOB format
     */
    public NonResident(String studentParam) { // NonResident constructor, using Student
        // todo check constructor, should have no additional instance vars
        super(studentParam);
    }

    /**
     Returns the amount of tuition due for the student. Tuition is calculated
     based on the student's residential status and credit load.
     * @param creditsEnrolled integer value of how many credits the student
     *                        is enrolled for.
     * @return double representing how much tuition the student has.
     */
    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        int PARTTTIME = 12;
        int FULLTIME = 16;
        double tuitionSum = INITVALUE;

        if (creditsEnrolled < PARTTTIME) { // Considered parttime
            tuitionSum += (Tuition.PTNRTUITION.fee * creditsEnrolled)
                    + Tuition.PTUNI.fee;
        } else { // Considered full time
            tuitionSum += Tuition.FNRTUITION.fee + Tuition.FUNI.fee;
            if (creditsEnrolled > FULLTIME) { // Over 16 credits
                tuitionSum += (Tuition.PTNRTUITION.fee * (creditsEnrolled - FULLTIME));
            }
        }

        return tuitionSum;
    }

    /**
     Method to tell if a student is valid given their number of credits.
     * @param creditEnrolled integer value of credits the student is
     *                       currently enrolled for.
     * @return true if the student has a valid amount of credits.
     */
    public boolean isValid(int creditEnrolled) {
        if (creditEnrolled < MINCREDS || creditEnrolled > MAXCREDS) {
            return false;
        }
        return true;
    }

    /**
     Method always returns false to indicate a NonResident student is
     not classified as a resident.
     * @return false always.
     */
    public boolean isResident() { // From Abstract Student
        return false;
    }

    /**
     Method returns what type of student this object is.
     * @return Non-Resident as a string.
     */
    public String returnType() {
        return "Non-Resident";
    }

}
