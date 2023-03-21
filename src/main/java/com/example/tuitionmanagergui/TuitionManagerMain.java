package com.example.tuitionmanagergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 Launches the Tuition manager GUI.
 @author Victoria Chen, Bridget Zhang
 */
public class TuitionManagerMain extends Application {
    /**
     * Method that starts GUI and sets stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerMain.class.getResource("TuitionManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Tuition Manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main function that launches Tuition Manager GUI
     * @param args String array of arguments
     */
    public static void main(String[] args) {
        launch();
    }
}