/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication13;

/**
 *
 * @author Nouran
 */
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
/**
 *
 * @author Nouran
 */
public class Receptionist extends Users {
transient Scene scene3;
transient  Scene scene4;

Booking f= new Booking();

Scene scene1;
Scene scene5;
//scene= f.createPrimaryStage( primaryStage);

     //scene6=f.chooseTrip( primaryStage);

       //scene2= f.createBookingSuccessful(primaryStage);

private transient Image backgroundImage = new Image("file:///D:/year 2/OOP/picturee.jpg");
  double  windowWidth = 600;
    double windowHeight = 400;
    // Create the background for stages
private transient BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight, true, true, true, true);
    private transient BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize
    );



    public Receptionist() {
    }
     public Receptionist(int id, String password, String name) {
        super(id, password, name);
    }

   
    public static List<Receptionist> receptionists = new ArrayList<>();

    public static void storeReceptionistInfo(Receptionist newReceptionist) {
        receptionists.add(newReceptionist);
    }

    public static void displayReceptionists() {
        System.out.println("Receptionists:");
        for (Receptionist r : receptionists) {
            System.out.println("ID: " + r.getID() + ", Name: " + r.getName() + ", Password: " + r.getPassword());
        }
    }
    
    
    
    public Scene ManageBookings( Stage primaryStage) throws FileNotFoundException {
       Scene scene=f.createPrimaryStage( primaryStage);
       
        Booking.select_trip_details();

        GridPane trials = new GridPane();
        primaryStage.setTitle("Manage Bookings");

        scene3 = new Scene(trials, windowWidth, windowHeight);
        primaryStage.setScene(scene3);
        trials.setBackground(new Background(background));

        Label lbl19 = new Label("Welcome To Manage Bookings");
        Label lbl20 = new Label("What Would you like to do? ");
        Label lbl21 = new Label("New Booking :");
        Label lbl22 = new Label("Existing Booking");
        Button btn4 = new Button("ADD");
        Button btn5 = new Button("Manage");

        trials.add(lbl19, 0, 0, 2, 1);
        GridPane.setValignment(lbl19, VPos.TOP); // Align to the top
        GridPane.setMargin(lbl19, new Insets(10, 10, 0, 10)); // Add margin
        lbl19.setTextFill(Color.WHITE);
        lbl20.setTextFill(Color.WHITE);
        lbl21.setTextFill(Color.WHITE);
        lbl22.setTextFill(Color.WHITE);
        trials.setAlignment(Pos.CENTER);
        trials.add(lbl20, 0, 1, 2, 1); // lbl20 spans 2 columns
        trials.add(lbl21, 0, 2);
        trials.add(btn4, 1, 2);
        trials.add(lbl22, 0, 3);
        trials.add(btn5, 1, 3);
        GridPane.setMargin(lbl19, new Insets(0, 10, 0, 0));
        GridPane.setMargin(lbl20, new Insets(0, 10, 0, 0));
        trials.setHgap(10);
        trials.setVgap(10);

        btn4.setOnAction(e -> {
           primaryStage.setScene(scene);
        });
        btn5.setOnAction(e -> {
           primaryStage.setScene(f.ExistingBookings(primaryStage));
        });

        return scene3;
    }
    
}
    
    
    
    
    
    
    
    
    
    