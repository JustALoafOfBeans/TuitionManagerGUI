package com.example.tuitionmanagergui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TuitionManagerController {
    @FXML
    public TextField FirstNameInput, LastNameInput, CreditsInput;
    @FXML
    public DatePicker DobInput;
    public RadioButton MajorBAIT, MajorCS, MajorECE, MajorITI, MajorMATH;
    public RadioButton ResidentButton, NonResidentButton, TristateButton, TriNYButton, TriCTButton, IntlButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        System.out.println("Initial things"); // todo remove lol
        TristateButton.setDisable(true);
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
        IntlButton.setDisable(true);
    }

    @FXML
    protected void onResidentTrue() { // Disables related Non-Resident button options
        TristateButton.setDisable(true);
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
        IntlButton.setDisable(true);
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
    }

    @FXML
    protected void onIntlTrue() { // Disables Tristate Non-res button options
        TriNYButton.setDisable(true);
        TriCTButton.setDisable(true);
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