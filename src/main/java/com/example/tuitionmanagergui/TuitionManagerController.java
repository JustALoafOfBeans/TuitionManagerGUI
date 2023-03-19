package com.example.tuitionmanagergui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TuitionManagerController {
    @FXML
    public TextField FirstNameInput, LastNameInput, CreditsInput, ScholarshipInput;
    public DatePicker DobInput;
    public RadioButton MajorBAIT, MajorCS, MajorEE, MajorITI, MajorMATH;
    public RadioButton ResidentButton, NonResidentButton, TristateButton, TriNYButton, TriCTButton, IntlButton;
    public CheckBox StudyAbroad;
    /**
     * Init text area for output at the bottom of the Tuition Manager window.
     * Add new text with "setText()" command. This will replace all current
     * text with the desired String.
     */
    public TextArea output;
    Roster studentRoster = new Roster();
    Enrollment enrolledStudents = new Enrollment();
    private static int ONE = 1;
    private static int INIT = 0;

    @FXML
    public void initialize() {
        System.out.println("Initial things"); // todo remove lol
        output.setText("Tuition manager running...");
        TristateButton.setDisable(true);
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
        IntlButton.setDisable(true);
        StudyAbroad.setDisable(true);
    }

    @FXML
    protected void onResidentTrue() { // Disables related Non-Resident button options
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

    @FXML
    protected void onNonresidentTrue() { // Enables Non-REsident button options
        TristateButton.setDisable(false);
        IntlButton.setDisable(false);
    }

    @FXML
    protected void onTristateTrue() { // Enables Tristate button options
        TriNYButton.setDisable(false);
        TriCTButton.setDisable(false);
        StudyAbroad.setSelected(false);
    }

    @FXML
    protected void onIntlTrue() { // Disables Tristate Non-res button options
        TriNYButton.setDisable(true);
        TriNYButton.setSelected(false);
        TriCTButton.setDisable(true);
        TriCTButton.setSelected(false);
        StudyAbroad.setDisable(false);
    }

    @FXML
    protected void printByProfile() {
        studentRoster.print(); //todo make print to output instead of console
    }

    @FXML
    protected void onChangeMajorButtonClick() {
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty()
                || DobInput == null ) {
            output.setText("Missing data to add student. Please check " +
                    "first/last name, and/or date of birth.");
            return;
        }
        String[] studentDetails = createStudentArr();
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


    @FXML
    protected void onRemoveButtonClick() {
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty()
                || DobInput == null || CreditsInput.getText().isEmpty()) {
            output.setText("Missing data to add student. Please check " +
                    "first/last name, date of birth and/or completed credits.");
            return;
        }
        String student =
                FirstNameInput.getText() + " " + LastNameInput.getText() + " " +
                DobInput.getValue().toString() + " FAKE 50";
        Student toRemove = new Resident(student);
        if (studentRoster.contains(toRemove)) {
            studentRoster.remove(toRemove);
            output.setText(FirstNameInput.getText() + " " + LastNameInput.getText() + " " +
                    DobInput.getValue().toString() + " removed from the roster.");
        } else {
            output.setText(FirstNameInput.getText() + " " + LastNameInput.getText() + " " +
                    DobInput.getValue().toString() + " is not in the roster.");
        }
        clearRosterFields();
    }

    @FXML
    protected void onAddButtonClick() {
        if (FirstNameInput.getText().isEmpty() || LastNameInput.getText().isEmpty() || DobInput == null || CreditsInput.getText().isEmpty()) {
            output.setText("Missing data to add student. Please check " +
                    "first/last name, date of birth and/or completed credits.");
            return;
        }
        String[] stuDetails = createStudentArr();
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

    private String[] createStudentArr() {
        String[] studentDetails = {FirstNameInput.getText(),
                LastNameInput.getText(),DobInput.getValue().toString(),"",CreditsInput.getText()};
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

}