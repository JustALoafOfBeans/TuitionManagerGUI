package com.example.tuitionmanagergui;

/**
 The International class extends NonResident and provides methods to create
 subtypes of Student objects from a String or Profile object and for
 manipulating the Student fields.
 @author Victoria Chen, Bridget Zhang
 */
public class International extends NonResident {
    /**
     * Boolean that is TRUE if student is studying abroad and FALSE if not
     */
    private boolean isStudyAbroad;
    /**
     * Maximum number of credits for student in study abroad program
     */
    private static int CREDITBRKPT = 12;
    /**
     * Maximum number of credits for a student to be enrolled in
     */
    private  static int MAXCREDITS = 24;

    /**
     Constructor for International object
     * @param studentParam string of format FIRST LAST DOB for Student super
     * @param studentAbroad TRUE if student is in study abroad program
     */
    public International (String studentParam, boolean studentAbroad) { // International constructor, using Student
        // todo this constructor too
        super(studentParam);
        isStudyAbroad = studentAbroad;

        // todo should we check cred requirements for international students?
    }

    /**
     Returns the number of credits completed
     @return integer for credits completed
     */
    public int getCreditCompleted() {
        return super.getCreditCompleted();
    }

    /**
     Returns the tuition that the International student must pay
     * @param creditsEnrolled number of credits enrolled in
     * @return amount for tuition in DOLLARS.CENTS
     */
    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        double interSum = super.tuitionDue(creditsEnrolled);
        if (isStudyAbroad) {
            interSum = Tuition.FUNI.fee + Tuition.FIHEALTH.fee;
        } else {
            interSum += Tuition.FIHEALTH.fee;
        }
        return interSum;
    }

    /**
     Method overrides isValid(int creditEnrolled) from Student class. Return
     value is based on the Student's study abroad status and number of
     credits enrolled.
     * @param creditEnrolled integer value of credits the student is
     *                       currently enrolled for.
     * @return true if the student is study abroad and 12 or less credits
     * or if the student is not study abroad and 12 or more credits. False
     * otherwise.
     */
    @Override
    public boolean isValid(int creditEnrolled) {
        if (isStudyAbroad && creditEnrolled <= CREDITBRKPT) {
            return true;
        } else if (!isStudyAbroad && creditEnrolled >= CREDITBRKPT && creditEnrolled <= MAXCREDITS){
            return true;
        }
        return false;
    }

    /**
     Method returns what type of student this object is.
     * @return International as a string. May have study abroad if applicable.
     */
    public String returnType() {
        String ans = "International Student";
        if (isStudyAbroad) {
            ans += ", study abroad";
        }
        return ans;
    }
}
