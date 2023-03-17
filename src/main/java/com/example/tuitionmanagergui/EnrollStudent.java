package com.example.tuitionmanagergui;

/**
 The EnrollStudent class provides methods to create EnrollStudent objects
 from a String and for manipulating the EnrollStudent fields.
 @author Bridget Zhang
 */
public class EnrollStudent {
    /**
     * Profile object for student's name and DOB
     */
    private Profile profile;
    /**
     * Number of credits that student has completed
     */
    private int creditsEnrolled;

    /**
     Constructor for EnrollStudent object
     * @param enrollInput student profile and credits as string
     */
    public EnrollStudent(String enrollInput) { // Constructor
        // todo check input, takes in String eg. "Emma Miller 2/28/2003 8"
        // Expect profile to conclude just before 3rd space
        String profileStr = "", creditStr = ""; // todo is this syntax legal for Chang
        int spaceCount = 0;
        for (int ind = 0; ind < enrollInput.length(); ind++) {
            if (enrollInput.charAt(ind) == ' ') { // Look for " " delimiter
                spaceCount += 1;
            }
            if (spaceCount == 3) { // Encountered 3rd space
                profileStr = enrollInput.substring(0, ind);
                // Expect credits after space following profile, goes to end
                creditStr = enrollInput.substring(ind+1);
                break;
            }
        }
        profile = new Profile(profileStr);
        creditsEnrolled = Integer.parseInt(creditStr);
    }

    /**
     Converts student to string for profile and credits
     * Overrides toString() from Object class
     * @return string in form "[PROFILE]: credits enrolled: [CREDITS]"
     */
    @Override
    public String toString() {
        // Format: [profile as FIRST LAST DOB]: credits enrolled: [credits]
        String strEnroll = profile.toString();
        strEnroll += ": credits enrolled: ";
        strEnroll += creditsEnrolled;
        return strEnroll;
    }

    /**
     Returns profile of current student
     * @return profile of EnrollStudent on which it is called
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     Returns credits current student currently enrolled in
     * @return current credit enrollment
     */
    public int getCredits() {
        return creditsEnrolled;
    }

    /**
     Changes credit for student in question
     * @param newCredits new credit load to set to
     */
    public void setCredits(int newCredits) {
        creditsEnrolled = newCredits;
    }
}
