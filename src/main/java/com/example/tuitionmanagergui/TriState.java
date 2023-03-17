package com.example.tuitionmanagergui;

/**
 The TriState class is a subclass of NonResident which provides methods to
 create TriState objects using no parameters and for manipulating the TriState
 fields.
 @author Victoria Chen, Bridget Zhang
 */
public class TriState extends NonResident {

    /**
     * String that refers to state that TriState student from
     */
    private String state;

    /**
     Constructor for TriState object
     * @param studentParam student constructor string in form FIRST LAST DOB
     * @param studentState state that student is from
     */
    public TriState(String studentParam, String studentState) { // TriState constructor, using Student
        // todo this constructor too
        super(studentParam);
        state = studentState;
    }

    /**
     Method that returns number of credit that student has completed
     * @return number of credits completed
     */
    public int getCreditCompleted() {
        return super.getCreditCompleted();
    }

    /**
     Method that returns the amount of tuition that student must pay
     * @param creditsEnrolled number of credits currently enrolled in
     * @return amount for tuition in DOLLARS.CENTS
     */
    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        int PARTTTIME = 12;
        double tristateSum = super.tuitionDue(creditsEnrolled);
        if (creditsEnrolled >= PARTTTIME) {
            if (state.toUpperCase().compareTo("NY") == 0) { // NY tristate discount
                tristateSum -= 4000;
            } else if (state.toUpperCase().compareTo("CT") == 0) { // CT tristate discount // todo check abbrev
                tristateSum -= 5000;
            }
        }
        return tristateSum;
    }

    /**
     Method returns what type of student this object is.
     * @return Resident as a string.
     */
    public String returnType() {
        return "Tri-state " + state;
    }

}
