package com.example.tuitionmanagergui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

/**
 Controller class for event triggers and roster and enroll objects which holds
 student objects. This class is launched by running TuitionManagerMain.java
 and first initiates a new roster object and session. The roster and
 enrollment list can be manipulated during the session but all actions are
 cleared after closing the window.
 @author Victoria Chen, Bridget Zhang
 */
public class TuitionManagerController {
    /**
     * Init text fields for Roster object
     */
    @FXML
    public TextField FirstNameInput, LastNameInput, CreditsInput, ScholarshipInput;
    /**
     * Init text fields for Enroll object
     */
    public TextField EnrollFirst, EnrollLast, EnrollCred;
    /**
     * Init date picker object for Roster
     */
    public DatePicker DobInput, EnrollDob;

    /**
     * Init radio buttons for Roster majors
     */
    public RadioButton MajorBAIT, MajorCS, MajorEE, MajorITI, MajorMATH;
    /**
     * Init radio buttons for Roster student statuses
     */
    public RadioButton ResidentButton, NonResidentButton, TristateButton, TriNYButton, TriCTButton, IntlButton;
    /**
     * Init checkbox for Roster student Study Abroad status
     */
    public CheckBox StudyAbroad;
    /**
     * Init text area for output at the bottom of the Tuition Manager window.
     * Add new text with "setText()" command. This will replace all current
     * text with the desired String.
     */
    public TextArea output;
    /**
     * Add button for Roster
     */
    public Button RostAddBtn;
    /**
     * Init roster object
     */
    Roster studentRoster = new Roster();
    /**
     * Init enrollment list object
     */
    Enrollment enrolledStudents = new Enrollment();
    /**
     * Static variable for 1
     */
    private static int ONE = 1;
    /**
     * Static variable for 0
     */
    private static int INIT = 0;

    /**
     * Initialization status for the GUI
     */
    @FXML
    public void initialize() {
        output.setText("Tuition manager running...");
        TristateButton.setDisable(true);
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
        IntlButton.setDisable(true);
        StudyAbroad.setDisable(true);
    }

    /**
     * When Resident is selected, disables related Non-Resident button options
     */
    @FXML
    protected void onResidentTrue() {
        TristateButton.setDisable(true);
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
        IntlButton.setDisable(true);
        StudyAbroad.setDisable(true);
        TristateButton.setSelected(false);
        IntlButton.setSelected(false);
        TriNYButton.setSelected(false);
        TriCTButton.setSelected(false);
        StudyAbroad.setSelected(false);
    }

    /**
     * When Nonresident is selected, enables Non-Resident button options
     */
    @FXML
    protected void onNonresidentTrue() {
        TristateButton.setDisable(false);
        IntlButton.setDisable(false);
        TristateButton.setSelected(false);
        IntlButton.setSelected(false);
        TriNYButton.setSelected(false);
        TriCTButton.setSelected(false);
        StudyAbroad.setSelected(false);
    }

    /**
     * When Tristate is selected, enables Tristate button options
     */
    @FXML
    protected void onTristateTrue() {
        TriNYButton.setDisable(false);
        TriCTButton.setDisable(false);
        StudyAbroad.setSelected(false);
        NonResidentButton.setSelected(false);
    }

    /**
     * When International is selected, disables Tristate Non-res button options
     */
    @FXML
    protected void onIntlTrue() {
        TriNYButton.setDisable(true);
        TriNYButton.setSelected(false);
        TriCTButton.setDisable(true);
        TriCTButton.setSelected(false);
        StudyAbroad.setDisable(false);
        NonResidentButton.setSelected(false);
    }

    /**
     * On click "Print by Profile" button. Outputs roster to GUI sorted by
     * student profiles.
     */
    @FXML
    protected void printByProfile() {
        String rosterProfilePrint = studentRoster.print();
        output.setText(rosterProfilePrint);
    }

    /**
     * On click "Print by School" button. Outputs roster to GUI sorted by
     * students belonging to a specified school, sorted by profiles.
     */
    @FXML
    protected void printBySchool() {
        String rosterSchoolPrint = studentRoster.printBySchoolMajor();
        output.setText(rosterSchoolPrint);
    }

    /**
     * On click "Print by Standing" button. Outputs roster to GUI sorted by
     * student standing (alphabetical) and profile.
     */
    @FXML
    protected void printByStanding() {
        String rosterStandingPrint = studentRoster.printByStanding();
        output.setText(rosterStandingPrint);
    }

    /**
     * On click RBS print button. Outputs roster to GUI of students
     * belonging to Rutgers Business School, sorted by profiles.
     */
    @FXML
    protected void printSchoolRBS() {
        String printRBS = studentRoster.print("RBS");
        output.setText(printRBS);
    }

    /**
     * On click SAS print button. Outputs roster to GUI of students
     * belonging to School of Arts and Sciences, sorted by profiles.
     */
    @FXML
    protected void printSchoolSAS() {
        String printSAS = studentRoster.print("SAS");
        output.setText(printSAS);
    }

    /**
     * On click SC&I print button. Outputs roster to GUI of students
     * belonging to School of Communication & Information, sorted by profiles.
     */
    @FXML
    protected void printSchoolSCI() {
        String printSCI = studentRoster.print("SC&I");
        output.setText(printSCI);
    }

    /**
     * On click SOE print button. Outputs roster to GUI of students
     * belonging to School of Engineering, sorted by profiles.
     */
    @FXML
    protected void printSchoolSOE() {
        String printSOE = studentRoster.print("SOE");
        output.setText(printSOE);
    }

    /**
     * On click "Print Enrolled Student" button. Outputs list of enrolled
     * students to GUI sorted by profile.
     */
    @FXML
    protected void printEnrollment() {
        String printEnroll = enrolledStudents.print();
        output.setText(printEnroll);
    }

    /**
     * On click "Print Tuition Due" button. Outputs list of enrolled
     * students to GUI sorted by profile with corresponding tuition amount.
     */
    @FXML
    protected void printTuition() {
        String printTui = enrolledStudents.printTuition(studentRoster);
        output.setText(printTui);
    }

    /**
     * On click "Semester End" button. Adds enrolled credits to completed
     * credits in roster and prints all students eligible for graduation.
     */
    @FXML
    protected void onSemesterEndButtonClick() {
        String printStr = "";
        int credsComplete = INIT;
        int GRADCREDITS = 120;
        Profile[] students = enrolledStudents.getGraduates();
        if (students.length == INIT) {
            printStr = "Enrollment is empty!\n";
        } else {
            printStr += "Credit completed has been updated.\n";
            printStr += "** list of students eligible for graduation **\n";
            for (int ind = INIT; ind < students.length; ind++) {
                Profile tempProfile = students[ind];
                Student tempStudent = new Resident(tempProfile);
                Student studentR = studentRoster.getStudent(tempStudent);
                // 12 is arbitrary value just to create the EnrollStudent
                EnrollStudent stuObj = new EnrollStudent(students[ind] + " 12");
                EnrollStudent studentE = enrolledStudents.getStudent(stuObj);

                credsComplete = studentR.getCreditCompleted() + studentE.getCredits();

                studentR.updateCreditCompleted(credsComplete);
                if (credsComplete >= GRADCREDITS) {
                    printStr += printGrad(studentR) + "\n";
                }
            }
        }
        output.setText(printStr);
    }

    /**
     * Helper method which returns the graduate student in the proper format.
     * @param studentR Student object for the student which needs to be printed.
     * @return String representing the student and details.
     */
    private String printGrad(Student studentR) {
        String strStudent = studentR.toString() + " ";

        if (studentR.isResident()) {
            strStudent += "(resident)";
        } else {
            strStudent += "(non-resident) ";
            if (studentR.returnType().equals("International Student")) {
                strStudent += "(international)";
            } else if (studentR.returnType().contains("state")) {
                String needState = studentR.returnType();
                String[] state = needState.split(" ");
                strStudent += "(tri-state:" + state[1] + ")";
            }
        }
        return strStudent;
    }

    /**
     * On click "Change Scholarship" button. Updates the student's
     * scholarship value if they are a Resident and throws an error if they
     * are not eligible or the amount is not an integer.
     */
    @FXML
    protected void onChangeScholarshipButtonClick() {
        String first = EnrollFirst.getText().strip();
        String last = EnrollLast.getText().strip();
        String scholarship = ScholarshipInput.getText().strip();
        if (EnrollFirst.getText().isEmpty() || EnrollLast.getText().isEmpty()
                || EnrollDob == null || ScholarshipInput.getText().isEmpty()) {
            output.setText("Missing data to change scholarship. Please check " +
                    "first/last name, date of birth, and scholarship value.");
            return;
        }
        Date dob = new Date(EnrollDob.getValue().toString());
        if (!dob.checkIfSixteen()) {
            output.setText("DOB invalid: " + dob + " younger than 16 years old.");
            return;
        }
        if (first.matches(".*\\s.*") || last.matches(".*\\s.*") || scholarship.matches(
                ".*\\s.*")) {
            output.setText("First name, last name, and scholarship cannot contain spaces.");
            return;
        }
        String[] prf = {first, last, EnrollDob.getValue().toString()};
        String student = String.join(" ", prf);
        // 12 is arbitrary value just to create the EnrollStudent
        EnrollStudent stuObj = new EnrollStudent(student + " 12");
        if (enrolledStudents.contains(stuObj)) {
            Profile tempProfile = new Profile(student);
            Student tempStudent = new Resident(tempProfile);
            Student toGiveScholarship = studentRoster.getStudent(tempStudent);
            if (toGiveScholarship.isResident()) {
                residentScholarship(toGiveScholarship, scholarship, student);
            } else {
                output.setText(student + " (" + toGiveScholarship.returnType()
                        + ") is not eligible for the scholarship.");
            }
        } else {
            output.setText(student + " is not in the enrollment list.");
        }
    }

    /**
     * Helper function for onChangeScholarshipButtonClick(). Updates the
     * student's scholarship if valid request.
     * @param foundStudent Student object to give scholarship to.
     * @param value String representation of the scholarship value to give.
     * @param student String representation of the student's details.
     */
    private void residentScholarship(Student foundStudent, String value, String student) {
        int PARTTIME = 12;
        int MAXSCHOLAR = 10000;
        int MINSCHOLAR = 1;
        Resident toGive = (Resident) foundStudent;
        char[] digits = value.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (!Character.isDigit(digits[i])) {
                output.setText("Amount is not an integer.");
                return;
            }
        }
        // 12 is arbitrary value just to create the EnrollStudent
        EnrollStudent temp = new EnrollStudent(student + " 12");
        EnrollStudent stuObj = enrolledStudents.getStudent(temp);
        int scholarship = Integer.parseInt(value);
        if (scholarship > MAXSCHOLAR || scholarship < MINSCHOLAR) {
            output.setText(scholarship + ": invalid amount.");
        } else if (stuObj.getCredits() < PARTTIME) {
            output.setText(student + " part time student is not eligible for the scholarship.");
        } else { //assign scholarship
            toGive.assignScholarship(scholarship);
            output.setText(student + ": scholarship amount updated.");
        }
    }

    /**
     * On click "Enroll" button. Enrolls the student to the enrollment list if
     * the student is already in the roster. Throw error message if the student is
     * not in the roster or is already enrolled.
     */
    @FXML
    protected void onEnrollButton() {
        String first = EnrollFirst.getText().strip();
        String last = EnrollLast.getText().strip();
        String cred = EnrollCred.getText().strip();
        if (first.isEmpty() || last.isEmpty() || EnrollDob == null || cred.isEmpty()) {
            output.setText("Missing data to enroll student. Please check first/last name, date of birth, or credit load.");
            return;
        }
        Date dob = new Date(EnrollDob.getValue().toString());
        if (!dob.checkIfSixteen()) {
            output.setText("DOB invalid: " + dob + " younger than 16 years old.");
            return;
        }
        if (!validEnrollCredits(cred)) {
            return;
        }
        if (first.matches(".*\\s.*") || last.matches(".*\\s.*") || cred.matches(".*\\s.*")) {
            output.setText("First name, last name, and credits cannot contain spaces.");
            return;
        }
        Student tempStu = new Resident(new Profile(first + " " + last + " " + dob));
        if (!studentRoster.contains(tempStu)) {
            output.setText("Can not enroll: Student not in roster");
            return;
        } else {
            Student rostStu = studentRoster.getStudent(tempStu); // Gets matching student
            if (rostStu.isValid(Integer.parseInt(cred))) {
                String enrollStr = first + " " + last + " " + dob + " " + cred;
                EnrollStudent newStu = new EnrollStudent(enrollStr);
                enrolledStudents.add(newStu);
                if (enrolledStudents.contains(newStu)) {
                    output.setText(newStu.getProfile().toString() + " enrolled with " + cred + " credits.");
                }
            } else {
                output.setText("Can not enroll: Invalid credit load");
                return;
            }
        }
        clearEnrollFields();
    }

    /**
     * On click "Drop" button. Drops a student from the enroll list if they
     * are already enrolled. Throw error if they are not enrolled.
     */
    @FXML
    protected void onDropButtonClick() {
        String first = EnrollFirst.getText().strip();
        String last = EnrollLast.getText().strip();
        if (EnrollFirst.getText().isEmpty() || EnrollLast.getText().isEmpty() || EnrollDob == null ) {
            output.setText("Missing data to drop student. Please check " +
                    "first/last name, and/or date of birth.");
            return;
        }
        Date dob = new Date(EnrollDob.getValue().toString());
        if (!dob.checkIfSixteen()) {
            output.setText("DOB invalid: " + dob + " younger than 16 years old.");
            return;
        }
        if (first.matches(".*\\s.*") || last.matches(".*\\s.*")) {
            output.setText("First name and last name cannot contain spaces.");
            return;
        }
        String stuDetails = first + " " + last + " " + EnrollDob.getValue().toString();
        // 12 is arbitrary value just to create the EnrollStudent
        EnrollStudent tempStu = new EnrollStudent(stuDetails + " 12");
        if (enrolledStudents.contains(tempStu)) {
            enrolledStudents.remove(tempStu);
            output.setText(stuDetails + " dropped.");
        } else {
            output.setText(stuDetails + " is not enrolled.");
        }
    }

    /**
     * On click "Load from File" button. Reads input from studentList.txt and
     * adds the students to the roster.
     */
    @FXML
    protected void onLoadFileButtonClick() {
        try {
            File studentList = new File("./src/main/java/com/example/tuitionmanagergui/studentList.txt");;
            Scanner intake = new Scanner(studentList);
            String opCode = "init";
            while(intake.hasNextLine()) {
                String command = intake.nextLine();
                String[] parameters = command.split(",");
                if (parameters.length > INIT && parameters[0] != "") {
                    opCode = parameters[0];
                    String[]noOp = {parameters[1], parameters[2], parameters[3], parameters[4], parameters[5]};
                    clearRosterFields();
                    setParams(noOp);
                    processFile(opCode, parameters);
                }
            }
            output.setText("Students loaded to the roster.");
        } catch (Exception e) {
            output.setText(e.getClass().toString());
        }
    }

    /**
     * Private helper method for onLoadFileButtonClick(). Takes in one line
     * from the file with the details for one student and adds that student
     * to the roster by filling out the GUI and using onAddButtonClick().
     * @param opCode String containing the opCode for what type of student to add
     * @param parameters String array of the student's details
     */
    private void processFile(String opCode, String[] parameters) {
        int SAINDEXCOUNT = 5;
        switch (opCode) {
            case "R":
                ResidentButton.setSelected(true);
                onAddButtonClick();
                break;
            case "I":
                if (parameters.length > SAINDEXCOUNT && parameters[6].equals("true")) {
                    StudyAbroad.setSelected(true);
                }
                IntlButton.setSelected(true);
                onAddButtonClick();
                break;
            case "T":
                if (parameters[6].equals("CT")) {
                    TriCTButton.setSelected(true);
                } else if (parameters[6].equals("NY")) {
                    TriNYButton.setSelected(true);
                }
                onAddButtonClick();
                break;
            case "N":
                NonResidentButton.setSelected(true);
                onAddButtonClick();
                break;
        }
    }

    /**
     * Helper method which sets the corresponding fields in the GUI to match
     * the information in the String array input.
     * @param parameters String array of the student's details
     */
    private void setParams(String[] parameters) {
        FirstNameInput.setText(parameters[0]);
        LastNameInput.setText(parameters[1]);
        String[] date = parameters[2].split("/");
        DobInput.setValue(LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
                Integer.parseInt(date[1])));
        if (parameters[3].equals("BAIT")) {
            MajorBAIT.setSelected(true);
        } else if (parameters[3].equals("CS")) {
            MajorCS.setSelected(true);
        } else if (parameters[3].equals("EE")) {
            MajorEE.setSelected(true);
        } else if (parameters[3].equals("ITI")) {
            MajorITI.setSelected(true);
        } else if (parameters[3].equals("MATH")) {
            MajorMATH.setSelected(true);
        }
        CreditsInput.setText(parameters[4]);
    }

    /**
     * On click "Change Major" button. Changes the major of the student if
     * they are in the roster. Throw error message if they are not in the
     * roster.
     */
    @FXML
    protected void onChangeMajorButtonClick() {
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty()
                || DobInput == null ) {
            output.setText("Missing data to change major. Please check " +
                    "first/last name, and/or date of birth.");
            return;
        }
        String[] studentDetails = createStudentArr();
        if (studentDetails == null) {
            return;
        }
        String student =
                studentDetails[0] + " " + studentDetails[1] + " " + studentDetails[2];
        Profile toChange = new Profile(student);
        Resident stuObj = new Resident(toChange);
        if (studentRoster.contains(stuObj)) {
            studentRoster.changeMajor(toChange, studentDetails[3]);
            output.setText(student + " major changed to " + studentDetails[3]);
        } else {
            output.setText(student + " is not in the roster.");
        }
    }

    /**
     * On click "Remove" button. Removes the student from the roster. If the
     * student is not in the roster, throw error message.
     */
    @FXML
    protected void onRemoveButtonClick() {
        String fname = FirstNameInput.getText().strip();
        String lname = LastNameInput.getText().strip();
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty()
                || DobInput == null) {
            output.setText("Missing data to remove student. Please check " +
                    "first/last name and/or date of birth.");
            return;
        }
        if (fname.matches(".*\\s.*") || lname.matches(".*\\s.*")) {
            output.setText("First and last name cannot contain spaces.");
            return;
        }
        String student =
                fname + " " + lname + " " + DobInput.getValue().toString() + " FAKE 50";
        Student toRemove = new Resident(student);
        if (studentRoster.contains(toRemove)) {
            studentRoster.remove(toRemove);
            output.setText(fname + " " + lname + " " + DobInput.getValue().toString() + " removed from the roster.");
        } else {
            output.setText(fname + " " + lname + " " + DobInput.getValue().toString() + " is not in the roster.");
        }
        clearRosterFields();
    }

    /**
     * On click "Add" button. Adds the student to the roster if the student
     * is not already in the roster. Throw error message if the student is
     * already in the roster.
     */
    @FXML
    protected void onAddButtonClick() {
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty()
                || DobInput == null || CreditsInput.getText().isEmpty()) {
            output.setText("Missing data to add student. Please check " +
                    "first/last name, date of birth and/or completed credits.");
            return;
        }
        String[] stuDetails = createStudentArr();
        if (stuDetails == null) {
            return;
        }
        Date dob = new Date(DobInput.getValue().toString());
        if (!dob.checkIfSixteen()) { //check age
            output.setText("DOB invalid: " + dob + " younger than 16 years old.");
            return;
        }
        if (!validCredits(stuDetails[4])) {
            return;
        }
        Student student = createStudent(stuDetails);
        if (student == null) {
            return;
        } else if (studentRoster.contains(student)) {
            output.setText(stuDetails[0] + " " + stuDetails[1] + " " + stuDetails[2] + " is already in the roster.");
        } else {
            studentRoster.add(student);
            output.setText(stuDetails[0] + " " + stuDetails[1] + " " + stuDetails[2] + " added to the roster.");
        }
        clearRosterFields();
    }

    /**
     * Helper method for onAddButtonClick(). Verifies if the given credit
     * value is a valid value.
     * @param credits String value of credits.
     * @return True if credits is a positive integer. False otherwise.
     */
    private boolean validCredits(String credits) {
        char[] digits = credits.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (i == INIT && digits[0] == '-') {
                continue; // exception made for negative values
            } else if (!Character.isDigit(digits[i])) {
                output.setText("Credits completed invalid: not an integer!");
                return false;
            }
        }
        if(Integer.parseInt(credits) < INIT) {
            output.setText("Credits completed invalid: cannot be negative!");
            return false;
        }
        return true;
    }

    /**
     * Private function that checks if enroll credit load valid
     * @param credits credit load in String form
     * @return true if valid value (positive number)
     */
    private boolean validEnrollCredits(String credits) {
        char[] digits = credits.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (i == INIT && digits[0] == '-') {
                continue; // exception made for negative values
            } else if (!Character.isDigit(digits[i])) {
                output.setText("Current credit load invalid: not an integer!");
                return false;
            }
        }
        if(Integer.parseInt(credits) < INIT) {
            output.setText("Current credit load invalid: cannot be negative!");
            return false;
        }
        return true;
    }

    /**
     * Helper method which initializes a sub-Class of Student object based on
     * what status buttons are selected using the student's details.
     * @param stuDetails String array of the student's details.
     * @return Resident, Nonresident, Tristate or International object.
     */
    private Student createStudent(String[] stuDetails) {
        String toAdd = String.join(" ", stuDetails);
        Student student = null;
        if (TriCTButton.isSelected()) {
            student = new TriState(toAdd, "CT");
        } else if (TriNYButton.isSelected()) {
            student = new TriState(toAdd,"NY");
        } else if (TristateButton.isSelected()) {
            output.setText("Please select a state.");
            return null;
        } else if (IntlButton.isSelected()) {
            Boolean studyAbroad = false;
            if (StudyAbroad.isSelected()) {
                studyAbroad = true;
            }
            student = new International(toAdd, studyAbroad);
        } else if (NonResidentButton.isSelected()) {
            student = new NonResident(toAdd);
        } else {
            student = new Resident(toAdd);
        }
        return student;
    }

    /**
     * Helper method creates an array of a student's details from the
     * information in the GUI.
     * @return String array of a student's details.
     */
    private String[] createStudentArr() {
        String[] studentDetails = {FirstNameInput.getText().strip(), LastNameInput.getText().strip(),
                DobInput.getValue().toString(),"",CreditsInput.getText().strip()};
        if (studentDetails[0].matches(".*\\s.*") || studentDetails[1].matches(".*\\s.*") || studentDetails[4].matches(".*\\s.*")) {
            output.setText("First name, last name, and credits cannot contain spaces.");
            return null;
        }
        if (MajorBAIT.isSelected()) {
            studentDetails[3] = "BAIT";
        } else if (MajorCS.isSelected()) {
            studentDetails[3] = "CS";
        } else if (MajorEE.isSelected()) {
            studentDetails[3] = "EE";
        } else if (MajorITI.isSelected()) {
            studentDetails[3] = "ITI";
        } else if (MajorMATH.isSelected()) {
            studentDetails[3] = "MATH";
        }
        return studentDetails;
    }

    /**
     * Helper method clears all fields on the Roster page. Used after
     * successfully completing an event.
     */
    private void clearRosterFields() {
        FirstNameInput.clear();
        LastNameInput.clear();
        DobInput.setValue(null);
        MajorBAIT.setSelected(true);
        CreditsInput.clear();
        ResidentButton.setSelected(true);
        onResidentTrue();
        ScholarshipInput.clear();
    }

    /**
     * Helper method clears all fields on the Enroll page. Used after
     * successfully completing an event.
     */
    private void clearEnrollFields() {
        EnrollFirst.clear();
        EnrollLast.clear();
        EnrollDob.setValue(null);
        EnrollCred.clear();
        ScholarshipInput.clear();
    }

}