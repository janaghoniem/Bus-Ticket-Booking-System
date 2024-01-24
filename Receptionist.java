/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;

/**
 *
 * @author Nouran
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
 
/**
 *
 * @author jana
 */
public class Receptionist extends Users {
    public static Scene homepage;

    public Receptionist() {
    }
    
    public Receptionist(int id, String password, String name) {
        super(id, password, name, Type.RECEPTIONIST);
        storeReceptionistInfo(this);
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
    
    
    
    public Scene ManageBookings(Stage primaryStage) throws FileNotFoundException {
        
      Vehicle.initializeAvailibilityMap();
      Booking.setReceptionist_id(this.ID);
               
      BorderPane pane = new BorderPane();
      pane.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(4).jpg'); -fx-background-size: cover;");
      
      Button logout = new Button();
      logout.setStyle("-fx-background-color: transparent;");
      ImageView log_out = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/leave.png")));
      log_out.setFitWidth(40);
      log_out.setFitHeight(40);
      logout.setGraphic(log_out);
      
      logout.setOnMouseEntered(eh -> 
      {
          logout.setCursor(Cursor.HAND);
      });
      
      logout.setOnAction(eh ->
      {
          Alert logoutq = new Alert(Alert.AlertType.CONFIRMATION);
          logoutq.setHeaderText(null);
          logoutq.setContentText("Are you sure you want to log out?");
          Optional<ButtonType> response = logoutq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
            primaryStage.setScene(NewFXMain.loginscene);
          }

      });
      
      Label welcomelbl = new Label("Welcome " + this.Name + ".");
      welcomelbl.setTextFill(Color.web("#ffb000"));
      welcomelbl.setFont(Font.font("Montserrat ", FontWeight.BOLD, 60));
      HBox welcomebox = new HBox(400, welcomelbl, logout);
      welcomebox.setAlignment(Pos.CENTER);

      
      try {
            ImageView addbookingimg = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/tickets.png")));
            addbookingimg.setFitHeight(200);
            addbookingimg.setFitWidth(200);            
            Label addbookinglbl = new Label("Create Booking");
            addbookinglbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
            VBox add = new VBox(addbookingimg, addbookinglbl);
            add.setAlignment(Pos.CENTER);
            add.setSpacing(15);
            StackPane addbooking = new StackPane();
            Rectangle rectangle1 = new Rectangle(600, 600);
            rectangle1.setArcWidth(20); 
            rectangle1.setArcHeight(20); 
            rectangle1.setFill(Color.rgb(10,12,38, 0.5));
            rectangle1.setStyle("-fx-border-radius: 5px;");
            addbooking.getChildren().addAll(rectangle1, add);
            
            addbooking.setOnMouseEntered(eh -> {
                addbooking.setCursor(Cursor.HAND);
                rectangle1.setFill(Color.rgb(87, 90, 131, 0.5));
                addbookinglbl.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    addbookingimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/tickets(1).png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                // stackpane slightly goes up when hovered on or clicked, and back down when exited
                Admin.upUponHover(addbooking);
            });
            
            addbooking.setOnMouseExited(eh ->
            {
                addbooking.setCursor(Cursor.DEFAULT);
                rectangle1.setFill(Color.rgb(10,12,38, 0.5));
                addbookinglbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    addbookingimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/tickets.png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                Admin.downUponExit(addbooking);
            });
            
            addbooking.setOnMouseClicked(eh -> 
            {
                try {
                    primaryStage.setScene(Booking.createPrimaryStage(primaryStage));
                    primaryStage.setTitle("Bus-Ticket Booking System - Create Booking Page");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            ImageView managebookingimg = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/booking.png")));
            managebookingimg.setFitHeight(200);
            managebookingimg.setFitWidth(200);    
            Label managebookinglbl = new Label("Manage Existing Bookings");
            managebookinglbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
            VBox bookings = new VBox(managebookingimg, managebookinglbl);
            bookings.setAlignment(Pos.CENTER);
            bookings.setSpacing(15);
            StackPane manageBookings = new StackPane();
            Rectangle rectangle2 = new Rectangle(600, 600);
            rectangle2.setArcWidth(20); 
            rectangle2.setArcHeight(20); 
            rectangle2.setFill(Color.rgb(10,12,38, 0.5));
            rectangle2.setStyle("-fx-border-radius: 5px;");
            manageBookings.getChildren().addAll(rectangle2, bookings);
            manageBookings.setStyle("-fx-cursor: hand");
            
            manageBookings.setOnMouseEntered(eh -> {
                manageBookings.setCursor(Cursor.HAND);
                rectangle2.setFill(Color.rgb(87, 90, 131, 0.5));
                managebookinglbl.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    managebookingimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/booking(1).png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                // stackpane slightly goes up when hovered on or clicked, and back down when exited
                Admin.upUponHover(manageBookings);
            });
            
            manageBookings.setOnMouseExited(eh ->
            {
                manageBookings.setCursor(Cursor.DEFAULT);
                rectangle2.setFill(Color.rgb(10,12,38, 0.5));
                managebookinglbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    managebookingimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/booking.png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                Admin.downUponExit(manageBookings);
            });
            
            manageBookings.setOnMouseClicked(eh ->
            {
                Booking book = new Booking();
                try {
                    primaryStage.setScene(book.ExistingBookings(primaryStage));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
                }
                primaryStage.setTitle("Bus-Ticket Booking System - Manage Existing Bookings Page");
            });
           
            HBox box = new HBox(20, addbooking, manageBookings);
            box.setAlignment(Pos.CENTER);
            Insets in = new Insets(50);
            pane.setPadding(in);
            pane.setCenter(box);
            pane.setTop(welcomebox);
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } 
        

        homepage = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        return homepage;
    }
    
}
    
