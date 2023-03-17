package com.example.tuitionmanagergui;

/**
 An enum class containing all the majors and corresponding codes and schools
 both as Strings. Call values using "Major.MAJOR.code/school". For example
 "Major.MATH.code" will return "01:198" as a String.
 @author Victoria Chen, Bridget Zhang
 */
public enum Major {
    /**
     * CS Major in SAS school
     */
    CS ("01:198", "SAS"),
    /**
     * MATH Major in SAS school
     */
    MATH ("01:640", "SAS"),
    /**
     * EE Major in SOE school
     */
    EE ("14:332", "SOE"),
    /**
     * ITI Major in SCI school
     */
    ITI ("04:547", "SC&I"),
    /**
     * BAIT Major in RBS school
     */
    BAIT ("33:136", "RBS");

    /**
     * String that designates numerical code for major
     */
    public final String code;
    /**
     * String that designates school in which major is found
     */
    public final String school;

    /**
     * Attributes of Major that designate number code and school
     * @param code designates numerical code for major
     * @param school designates school in which major is found
     */
    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }
}
