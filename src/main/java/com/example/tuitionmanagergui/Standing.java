package com.example.tuitionmanagergui;

/**
 An enum class that contains the possible standings of students based on
 their credits.
 @author Victoria Chen, Bridget Zhang
 */

public enum Standing {

    /**
     * Standing for freshmen with less than 30 credits
     */
    FRESHMAN("Freshman"),
    /**
     * Standing for sophomore with between 30 and 60 credits
     */
    SOPHOMORE("Sophomore"),
    /**
     * Standing for juniors with between 60 and 90 credits
     */
    JUNIOR("Junior"),
    /**
     * Standing for seniors with 90 or more credits
     */
    SENIOR("Senior");

    /**
     * String representing the title assigned to each enum
     */
    public final String title;

    /**
     This method assigns the enum its respective value
     * @param title String value
     */
    Standing (String title) {
        this.title = title;
    }
}
