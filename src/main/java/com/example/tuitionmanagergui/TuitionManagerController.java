package com.example.tuitionmanagergui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TuitionManagerController {
    @FXML
    public TextField FirstNameInput, LastNameInput, CreditsInput;
    @FXML
    public DatePicker DobInput;
    public RadioButton MajorBAIT, MajorCS, MajorECE, MajorITI, MajorMATH;
    public RadioButton ResidentButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onAddButtonClick() {
        System.out.println(FirstNameInput.getText());
        System.out.println(LastNameInput.getText());
        String dobString = DobInput.getValue().toString();
        System.out.println(dobString);
        if (MajorBAIT.isSelected()) {
            System.out.println("BAIT");
        } else if (MajorCS.isSelected()) {
            System.out.println("CS");
        } else if (MajorECE.isSelected()) {
            System.out.println("ECE");
        } else if (MajorITI.isSelected()) {
            System.out.println("ITI");
        } else if (MajorMATH.isSelected()) {
            System.out.println("MATH");
        }
        System.out.println("Credits completed: " + CreditsInput.getText());
    }
}