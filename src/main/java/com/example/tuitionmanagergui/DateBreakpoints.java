package com.example.tuitionmanagergui;

/**
 An enum class containing all the breakpoints for the Date class as integers.
 Call values using "DateBreakpoints.KEY.num". For example
 "DateBreakpoints.MAXDAYS.num" will return "31" as an integer.
 */
public enum DateBreakpoints {
    /**
     * Number of maximum possible days in a month
     */
    MAXDAYS(31),
    /**
     * Number of days in a shorter month excluding February
     */
    SHORTERMONTH(30),
    /**
     * Maximum value a month can take
     */
    MAXMONTHS(12),
    /**
     * Padding to adjust when using the Calendar class (index off by 1)
     */
    MONTHPADDING(1),
    /**
     * For calculating quadrennials for leap years
     */
    QUADRENNIAL(4),
    /**
     * For calculating centennials for leap years
     */
    CENTENNIAL(100),
    /**
     * For calculating quatercentennials for leap years
     */
    QUATERCENTENNIAL(400),
    /**
     * Max number of days in February not in a leap year
     */
    FEBMAX(28),
    /**
     * Max number of days in February in a leap year
     */
    FEBMAXLEAP(29);

    /**
     * Integer representing the value assigned to each enum
     */
    public final int num;
    /**
     This method assigns the enum its respective integer value
     * @param num integer value
     */
    DateBreakpoints(int num) {
        this.num = num;
    }

}
