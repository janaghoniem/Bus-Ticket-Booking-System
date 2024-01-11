/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project.trial;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import project.trial.Admin;
import project.trial.Booking;
import project.trial.Users;
public class NewFXMain extends Application {

    private Admin admin;
    private final StackPane stackPane = new StackPane();

    @Override
public void start(Stage primaryStage) {
    Admin admin = new Admin();
    admin.readUsersFromFile();

    BorderPane border = new BorderPane();
    Label menuLabel = new Label("\u2630"); // Unicode character for hamburger icon
    menuLabel.setStyle("-fx-font-size: 24; -fx-cursor: hand; -fx-background-color: #358dca"); // Set the background color
    menuLabel.setOnMouseClicked(event -> admin.toggleMenu(border));
    HBox menuBox = new HBox(menuLabel);
    menuBox.setAlignment(Pos.TOP_LEFT);
    border.setTop(menuBox);

    Label welcomeLabel = admin.createStyledLabel("Welcome, Admin!"); // Call the createStyledLabel method
    admin.getStackPane().getChildren().add(welcomeLabel);
    border.setCenter(admin.getStackPane());

    Scene scene = new Scene(border, 600, 400);
    scene.setFill(Color.rgb(135, 206, 250)); // Set the background color of the stage
    border.setStyle("-fx-background-color: #358dca;"); // Set the background color of the BorderPane

    primaryStage.setTitle("Admin Dashboard");
    primaryStage.setScene(scene);
    primaryStage.show();
}

    public static void main(String[] args) {
        Vehicle.readFromFile();
        launch(args);
        Vehicle.updateFile();
    }
}
