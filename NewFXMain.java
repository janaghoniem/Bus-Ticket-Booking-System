/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project.trial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jana
 */
public class NewFXMain extends Application {
    
    
    @Override
    public void start(Stage primaryStage){
                
        //LOGIN - SIGNUP SARA
        
        //ADMIN PAGE MARIAM
        //will have different buttons each one switching to one of the scenes below
        
        //MANAGE USERS BUTTON PAGE MARIAM
        
        //MANAGE VEHICLES BUTTON PAGE JANA
        
        Scene vehicleScene = Admin.managesVehicle();

        //MANAGE TRIPS BUTTON PAGE ROAA
        
        //MANAGE BOOKINGS BUTTON PAGE NOURAN
        
        //RECEPTIONIST PAGE MARIAM
        
        //MANAGE GUESTS BUTTON PAGE SARA

        

        //FOR TESTING - CHANGE THE PARAMETERS IF YOU NEED TO TEST YOUR OWN SCENE
        
        primaryStage.setTitle("Bus-Ticket Booking System");
        primaryStage.setScene(vehicleScene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
