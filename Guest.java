/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author jana
 */

public class Guest extends Users implements Serializable{
    int rating = 0;

    private ArrayList<Booking> bookingHistory;
    private HashMap<Integer, Integer> guestsRatings;
    protected static HashMap<Integer, Guest> guests = new HashMap<>();

    public Guest() {
        guests.put(this.ID, this);
        this.bookingHistory = new ArrayList<>();
        this.guestsRatings = new HashMap<Integer, Integer>();
        this.getBookingHistory(this.ID);
    }

    public Guest(int id, String password, String name) {
        super(id, password, name, Type.GUEST);
        guests.put(this.ID, this);
        this.bookingHistory = new ArrayList<>();
        this.guestsRatings = new HashMap<Integer, Integer>();        
        this.getBookingHistory(this.ID);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    

    public static void saveGuestsToBinaryFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user_data.dat"))) {
            for(Guest guests: guests.values())
            {
                objectOutputStream.writeObject(guests);
            }
        } catch (IOException e) {
            System.out.println("Error saving user information to binary file: " + e.getMessage());
        }
    }
    
    public static void loadGuestsFromBinaryFile() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user_data.dat"))) {
            while (true) {
                try {
                    Guest guest = (Guest) objectInputStream.readObject();
                    Guest newg = new Guest(guest.ID, guest.Password, guest.Name);
                    System.out.println("Guest information loaded from binary file.");
                } catch (EOFException e) {
                    System.out.println("End of file reached.");
                    break;  
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found: " + e.getMessage());
                }
            }
        }
        catch(EOFException exc)
        {
            System.out.println("End of file reached.");
        }
    }

    
    public void calculateRating(int tripId, int rating)
    {
        Trips trip = Trips.TripsMap.get(tripId);
        double totalRating = trip.getAverageRating() * trip.countTrips;
        trip.countRatings++;
        trip.setAverageRating((rating + totalRating)/trip.countRatings);
        this.guestsRatings.put(tripId, rating);
    }
    
    public void getBookingHistory(int guestId) {
        for (Booking booking : Booking.book.values()) {
            if (booking.getGuest_id() == this.ID) {
                System.out.println("Added booking to guest booking history");
                this.bookingHistory.add(booking);
            }
        }
    }
    
    public Scene guestHomePage() throws FileNotFoundException
    {
        getBookingHistory(this.ID);
        FlowPane root = new FlowPane();
        root.setOrientation(Orientation.VERTICAL);
        root.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(8).jpg'); -fx-background-size: cover;");
        
        Insets in = new Insets(20);
        root.setPadding(in);
        
        
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
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(NewFXMain.loginscene);
            primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
          }
        });
        
        Label welcomelbl = new Label("Welcome " + this.Name);
        welcomelbl.setTextFill(Color.web("#ffb000"));
        welcomelbl.setFont(Font.font("Montserrat ", FontWeight.BOLD, 60));
        welcomelbl.setAlignment(Pos.CENTER);
        HBox welcomebox = new HBox( 700, welcomelbl, logout);
        welcomebox.setAlignment(Pos.TOP_RIGHT);
        
        //column labels
        Label bookingid = new Label("Booking ID");
        bookingid.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        bookingid.setPrefWidth(200);
        HBox.setHgrow(bookingid, Priority.ALWAYS);
        
        Label tripid = new Label("Trip ID");
        tripid.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        tripid.setPrefWidth(200);
        HBox.setHgrow(tripid, Priority.ALWAYS);
        
        Label recepid = new Label("Receptionist ID");
        recepid.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        recepid.setPrefWidth(200);
        HBox.setHgrow(recepid, Priority.ALWAYS);
        
        Label pickup = new Label("Pickup Point");
        pickup.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        pickup.setPrefWidth(200);
        HBox.setHgrow(pickup, Priority.ALWAYS);
        
        Label dest = new Label("Destination Point");
        dest.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        dest.setPrefWidth(220);
        HBox.setHgrow(dest, Priority.ALWAYS);
        
        Label price = new Label("Total price");
        price.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        price.setPrefWidth(200);
        HBox.setHgrow(price, Priority.ALWAYS);
        
        Label lplate = new Label("License Plate");
        lplate.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        lplate.setPrefWidth(200);
        HBox.setHgrow(lplate, Priority.ALWAYS);
        
        Label numberoftickets = new Label("Ticket Amount");
        numberoftickets.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        numberoftickets.setPrefWidth(200);
        HBox.setHgrow(numberoftickets, Priority.ALWAYS);
        
        Label arrivaldate = new Label("Arrival Date");
        arrivaldate.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000; -fx-font-size: 20");
        arrivaldate.setPrefWidth(200);
        HBox.setHgrow(arrivaldate, Priority.ALWAYS);
        
        //labels hbox
        HBox labels = new HBox( bookingid, tripid, recepid, pickup, dest , price, lplate, numberoftickets, arrivaldate);
        
        //Scrollpane and vbox
        VBox historyview = new VBox();
        historyview.setStyle("-fx-background: transparent; -fx-background-color: transparent");
        historyview.getChildren().add(labels);
        historyview.setSpacing(15);
        
        if(bookingHistory.isEmpty())
        {
            Label empty = new Label("No Bookings yet.");
            historyview.getChildren().add(empty);
        }
        else
        {
        for(Booking b: bookingHistory)
        {
            Label bookingidv = new Label("" + b.getBooking_id());
            bookingidv.setTextFill(Color.web("#ffb000"));
            bookingidv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            bookingidv.setPrefWidth(200);
            HBox.setHgrow(bookingidv, Priority.ALWAYS);
        
            Label tripidv = new Label("" + b.getTrip_id());
            tripidv.setTextFill(Color.web("#ffb000"));
            tripidv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            tripidv.setPrefWidth(200);
            HBox.setHgrow(tripidv, Priority.ALWAYS);
        
            Label recepidv = new Label("" + b.getReceptionist_id());
            recepidv.setTextFill(Color.web("#ffb000"));
            recepidv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            recepidv.setPrefWidth(200);
            HBox.setHgrow(recepidv, Priority.ALWAYS);
        
            Label pickupv = new Label(b.fromBusStop);
            pickupv.setTextFill(Color.web("#ffb000"));
            pickupv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            pickupv.setPrefWidth(200);
            HBox.setHgrow(pickupv, Priority.ALWAYS);
        
            Label destv = new Label(b.getToBusStop());
            destv.setTextFill(Color.web("#ffb000"));
            destv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            destv.setPrefWidth(220);
            HBox.setHgrow(destv, Priority.ALWAYS);
        
            Label pricev = new Label("" + b.getTotal_price());
            pricev.setTextFill(Color.web("#ffb000"));
            pricev.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            pricev.setPrefWidth(200);
            HBox.setHgrow(pricev, Priority.ALWAYS);
        
            Label lplatev = new Label(b.getLicense_plate());
            lplatev.setTextFill(Color.web("#ffb000"));
            lplatev.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            lplatev.setPrefWidth(200);
            HBox.setHgrow(lplatev, Priority.ALWAYS);
        
            Label numberofticketsv = new Label("" + b.getNumberOfTickets());
            numberofticketsv.setTextFill(Color.web("#ffb000"));
            numberofticketsv.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            numberofticketsv.setPrefWidth(200);
            HBox.setHgrow(numberofticketsv, Priority.ALWAYS);
        
            Label arrivaldatevalue = new Label("" + b.getArrivalDateTime());
            arrivaldatevalue.setTextFill(Color.web("#ffb000"));
            arrivaldatevalue.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            arrivaldatevalue.setPrefWidth(200);
            HBox.setHgrow(arrivaldatevalue, Priority.ALWAYS);
            
            Button rate = new Button("Rate");
            String normalStyle = "-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);";
            String hoverStyle = "-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);";
            rate.setStyle(normalStyle);
            
            //hover event
            rate.setOnMouseEntered(eh ->
            {
                rate.setStyle(hoverStyle);
            });
            
            //non-hover event
            rate.setOnMouseExited(eh ->
            {
                rate.setStyle(normalStyle);
            });
            
            rate.setOnAction(eh -> 
            {
                try {
                    ratingSystem(b.getTrip_id());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            HBox temphbox = new HBox(bookingidv, tripidv, recepidv, pickupv, destv , pricev, lplatev, numberofticketsv, arrivaldatevalue, rate);   
            historyview.getChildren().add(temphbox);
        }
        }
        
        ScrollPane pane = new ScrollPane(historyview);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //pane.setPrefWidth(750);
        pane.setStyle("-fx-background: transparent; -fx-background-color: transparent");
    
        root.getChildren().add(welcomebox);
        root.getChildren().add(pane);
        root.setVgap(20);
        
        Scene scene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        return scene;
    }

    public static HashMap<Integer, Guest> getGuests() {
        return Guest.guests;
    }
    
    //Design 
    public void ratingSystem(int tripID) throws FileNotFoundException
    {
        Stage ratingStage = new Stage();
        
        Label rate = new Label("Rate your Trip");
        rate.setTextFill(Color.web("#ffb000"));
        rate.setFont(Font.font("Helvetica World", FontWeight.BOLD, 60));
        
        //star(1) image is an unfilled star, star(2) image is of a filled star
        
        ImageView star1 = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
        star1.setFitWidth(50);
        star1.setFitHeight(50);
        
        ImageView star2 = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
        star2.setFitWidth(50);
        star2.setFitHeight(50);
        
        ImageView star3 = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
        star3.setFitWidth(50);
        star3.setFitHeight(50);
        
        ImageView star4 = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
        star4.setFitWidth(50);
        star4.setFitHeight(50);
        
        ImageView star5 = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
        star5.setFitWidth(50);
        star5.setFitHeight(50);
        
        HBox stars = new HBox(5, star1, star2, star3, star4, star5);
        stars.setAlignment(Pos.CENTER);
        
        Button save = new Button("Save Rating");
        save.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        save.setPrefWidth(300);
        save.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));  
        save.setOnMouseEntered(eh ->
        {
            save.setCursor(Cursor.HAND);
            save.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        save.setOnMouseExited(eh ->
        {
            save.setCursor(Cursor.DEFAULT);
            save.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        save.setOnAction(eh ->
        {
            calculateRating(tripID, rating);
            ratingStage.close();
        });
        
        VBox root = new VBox(rate, stars, save);
        root.setStyle("-fx-background-color: #0A0C26;");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        star1.setOnMouseEntered(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star1.setOnMouseClicked(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                rating = 1;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
        
        star1.setOnMouseExited(eh -> 
        {
            try {
                if(rating != 1){
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star2.setOnMouseEntered(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star2.setOnMouseClicked(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                rating = 2;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
        
        star2.setOnMouseExited(eh -> 
        {
            try {
                if(rating != 2){
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));  
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star3.setOnMouseEntered(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });   
        
        star3.setOnMouseClicked(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                rating = 3;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
                
        star3.setOnMouseExited(eh -> 
        {
            try {
                if(rating != 3)
                {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));  
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star4.setOnMouseEntered(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star4.setOnMouseClicked(eh -> 
        {
            try {
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                rating = 4;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
                
        star4.setOnMouseExited(eh -> 
        {
            try {
                if(rating != 4){
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                }            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star5.setOnMouseEntered(eh -> 
        {
            try {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        star5.setOnMouseClicked(eh -> 
        {
            try {
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(2).png")));
                rating = 5;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
                
        star5.setOnMouseExited(eh -> 
        {
            try {
                if(rating != 5)
                {
                star1.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star2.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star3.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star4.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png")));
                star5.setImage(new Image(new FileInputStream("/home/jana/Downloads/star(1).png"))); 
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
  
        
        Scene scene = new Scene(root, 600,600);
        ratingStage.setTitle("Rating Page");
        ratingStage.setScene(scene);
        ratingStage.show();
    }
}
