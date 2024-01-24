/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;




/**
 *
 * @author Nouran
 */
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javafx.scene.control.ComboBox;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import project.trial.Trips.Destination;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Booking implements manages<Booking>, Serializable {

    public static final HashMap<Integer, Booking> book = new HashMap<>();
    public static boolean goFired = false;
    public static Booking currentBooking;
    private transient ComboBox<Trips> tripComboBox;
    private transient Spinner<Integer> ticketsSpinner;
    private transient List<Trips> filteredTrips;
    double windowWidth = 600;
    double windowHeight = 400;
    String tolocation;
    LocalDate selectedDate;
    static String file = "View Bookings";
    String fromlocation;
    int numberOfTickets;
    int buttonClickCount = 0;
    private int booking_id;
    private int guest_id;
    private String guestPassword;
    private int trip_id;
    private String guest_Name;
    private int total_price;
    private String license_plate;
    private static int receptionist_id;
    private LocalDateTime booking_time;
    private int no_of_tickets;
    private static final long serialVersionUID = 1L;
    Destination fromDestination;
    String fromBusStop;
    LocalDateTime departureDateTime;
    private Destination toDestination;
    private String toBusStop;
    private LocalDateTime arrivalDateTime;
    String searchValue;
    private transient Scene scene1;
    private transient Scene scene4;
    private transient Scene scene6;
    private transient VBox bookingsVBox = new VBox(10);
    private transient HBox bookingHBox = new HBox(10); 

    private transient Image backgroundImage = new Image("file:/home/jana/Downloads/Project(6).png");
    private transient BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight, false, false, true, true);
    private transient BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize
    );

    //empty constructor
    public Booking() {

    }

    //constructor
  public Booking(int booking_id, int guest_id, String guest_Name, String guestPassword, int trip_id,String fromDestination, String fromBusStop, LocalDateTime DepartureDateTime,String toDestination, String toBusStop, LocalDateTime arrivalDateTime,double total_price, String license_plate, int receptionist_id, int no_of_tickets,LocalDateTime booking_time) {
    this.guest_id = guest_id;
    this.guestPassword = guestPassword;
    this.trip_id = trip_id;
    this.guest_Name = guest_Name;
    this.total_price = (int) total_price;
    this.license_plate = license_plate;
    this.receptionist_id = receptionist_id;
    this.booking_id = booking_id;
    this.no_of_tickets = no_of_tickets;
    this.booking_time = booking_time;
  }

    //setters and getters
    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public static HashMap<Integer, Booking> getBook() {
        return book;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getGuest_Name() {
        return guest_Name;
    }

    public void setGuest_Name(String guest_Name) {
        this.guest_Name = guest_Name;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

  public void setBooking_time(LocalDateTime now) {
    this.booking_time = LocalDateTime.now();
}
    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuestPassword() {
        return guestPassword;
    }

    public void setGuestPassword(String guestPassword) {
        this.guestPassword = guestPassword;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public static int getReceptionist_id() {
        return receptionist_id;
    }

    public static void setReceptionist_id(int receptionist_id) {
        Booking.receptionist_id = receptionist_id;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Destination getFromDestination() {
        return fromDestination;
    }

    public void setFromDestination(Destination fromDestination) {
        this.fromDestination = fromDestination;
    }

    public Destination getToDestination() {
        return toDestination;
    }

    public void setToDestination(Destination toDestination) {
        this.toDestination = toDestination;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public String getFromlocation() {
        return fromlocation;
    }

    public void setFromlocation(String fromlocation) {
        this.fromlocation = fromlocation;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public static Booking getCurrentBooking() {
        return currentBooking;
    }

    public static void setCurrentBooking(Booking currentBooking) {
        Booking.currentBooking = currentBooking;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getFromBusStop() {
        return fromBusStop;
    }

    public void setFromBusStop(String fromBusStop) {
        this.fromBusStop = fromBusStop;
    }

    public String getToBusStop() {
        return toBusStop;
    }

    public void setToBusStop(String toBusStop) {
        this.toBusStop = toBusStop;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public void add() {
        System.out.println("--Add--");
        try {

            if (!book.containsKey(booking_id)) {
                if (false == Vehicle.newBooking(getTrip_id(), (int) getNumberOfTickets())) {
                    System.out.println("Sorry cant book! Try to look for another available trip");
                } else {
                    book.put(booking_id, this);
                    System.out.println(book);
                    String file = "View Bookings";

                    saveToFile();
                    viewFile();
                }

            } else {
                throw new RuntimeException("This booking already exists with the id :" + booking_id);
            }
        } catch (IllegalStateException e) {
            System.err.println("Error in adding booking " + e.getMessage());
        }
    }

   @Override
public String toString() {
    return "Booking{" +
            "guest_id=" + guest_id +
            ", guest_Name='" + guest_Name + '\'' +
            ", guestPassword='" + guestPassword + '\'' +
            ", trip_id=" + trip_id +
            ", fromDestination='" + fromDestination + '\'' +
            ", fromBusStop='" + fromBusStop + '\'' +
            ", DepartureDateTime=" + departureDateTime +
            ", toDestination='" + toDestination + '\'' +
            ", toBusStop='" + toBusStop + '\'' +
            ", arrivalDateTime=" + arrivalDateTime +
            ", total_price=" + total_price +
            ", license_plate='" + license_plate + '\'' +
            ", receptionist_id=" + receptionist_id +
            ", no_of_tickets=" + numberOfTickets +
            ", booking_time=" + booking_time + 
            '}';
}

    //remove
    @Override
    public void remove() {
        System.out.println("--remove--");
        Vehicle v = new Vehicle();
        book.remove(booking_id);
        v.cancelBooking(booking_id, no_of_tickets);
        System.out.println("Your booking has been successfully deleted.");
        String file = "View Bookings";
        saveToFile();
        viewFile();
    }

    public static void saveToFile() {
        int i = 0;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Booking bb : book.values()) {
                System.out.println(bb.booking_id);
                out.writeObject(bb);
                i++;

            }
            System.out.println("number of objects written into file: " + i);
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public static void readFromFile() {
        int i = 0;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Booking books = (Booking) in.readObject();
                    book.put(books.booking_id, books);

                    i++;
                } catch (EOFException e) {
                    // End of file reached
                    System.out.println("number of bookings read from file into hashmap: " + i);
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Error reading object: " + e);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public void viewFile() {
        String file = "B";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Booking booking : book.values()) {
                writer.write(booking.toString()); 
                writer.newLine(); 
            }
            System.out.println("Booking data successfully saved to file.");
        } catch (IOException e) {
            System.err.println("Failed to save booking data to file! " + e.getMessage());
        }
    }

    public static int generateBookingId() {
        Random random = new Random();
        int maxAttempts = 1000; 
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            int generatedId = Integer.parseInt("11" + String.format("%03d", random.nextInt(1000)));

            if (!book.containsKey(generatedId)) {
                return generatedId;
            }
        }

        throw new RuntimeException("Unable to generate a unique booking ID after multiple attempts.");
    }

    public static int calculate_payment(int trip_id, int no_of_tickets) {
        try {
            if (no_of_tickets < 0) {
                throw new IllegalArgumentException("The number inserted is below zero (Negative). Please try to insert a valid value.");
            }

            Trips trip = Trips.TripsMap.get(trip_id);

            int total_price = (int) (no_of_tickets * trip.getPrice());
            
            return total_price;
        } catch (IllegalArgumentException e) {
            System.err.println("Error in payment calculation! Please try again: " + e.getMessage());
            return 0; 
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
            return 0; 
        }
    }

    @Override
    public void edit() //partially implemented
    {

        Alert editBookingAlert1 = new Alert(Alert.AlertType.NONE);
        editBookingAlert1.setTitle("Confirmation");
        editBookingAlert1.setHeaderText("Do You Want To Edit Number of tickets: " + getTotal_price());

        ButtonType yesButton1 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton1 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        editBookingAlert1.getButtonTypes().setAll(yesButton1, cancelButton1);

        Optional<ButtonType> result1 = editBookingAlert1.showAndWait();

        if (result1.isPresent() && result1.get() == yesButton1) {
          
            Alert editBookingAlert2 = new Alert(Alert.AlertType.NONE);
            editBookingAlert2.setTitle("Edit Number of tickets");

            Spinner<Integer> spinner = new Spinner<>(1, 10, 1);
            VBox alertContent2 = new VBox(10);

            Label ticketsLabel = new Label("Number of Tickets:");
            Label totalPriceLabel = new Label("Total Price: $" + Booking.calculate_payment(getTrip_id(), 1));

            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                int editNumberOfTickets = spinner.getValue();
                double editTotal = Booking.calculate_payment(getTrip_id(), editNumberOfTickets);
                ticketsLabel.setText("Number of Tickets: " + editNumberOfTickets);
                totalPriceLabel.setText("Total Price: $" + editTotal);

            });

            alertContent2.getChildren().addAll(ticketsLabel, spinner, totalPriceLabel);
            editBookingAlert2.getDialogPane().setContent(alertContent2);

            ButtonType yesButton2 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton2 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            editBookingAlert2.getButtonTypes().setAll(yesButton2, cancelButton2);

            Optional<ButtonType> result2 = editBookingAlert2.showAndWait();

            if (result2.isPresent() && result2.get() == yesButton2) {
                int editNumberOfTickets = spinner.getValue();
                setTotal_price((editNumberOfTickets));
                //Vehicle v= new Vehicle();
                //if(v.AvailibilityMap.get(trip.getTrip_id())>editNumberOfTickets)
                //{
                setTotal_price(Booking.calculate_payment(getTrip_id(), editNumberOfTickets));
                System.out.println("Yes button clicked in the second alert");
                System.out.println(book);

                Booking.getBook().put(booking_id, this);
                saveToFile();
                viewFile();
            //}else{ System.out.println("sorry all seats are booked");};
            } else {
                System.out.println("Cancel button clicked in the second alert");
            }
        } else {
            System.out.println("Cancel button clicked in the first alert");
        }
    }

    
    public static Scene createPrimaryStage(Stage primaryStage) throws FileNotFoundException {
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(1).jpg'); -fx-background-size: cover;");

        // Image
        Image i = new Image(new FileInputStream("/home/jana/Downloads/review.png"));
        ImageView iv = new ImageView(i);
        iv.setFitHeight(150);
        iv.setFitWidth(150);

        Label description = new Label("Generate Guest's Login Information.");
        description.setTextFill(Color.web("#ffb000"));
        description.setFont(Font.font("Helvetica World", FontWeight.BOLD, 30));

        Label fnamelbl = new Label("Full Name:");
        fnamelbl.setTextFill(Color.web("#ffb000"));
        fnamelbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 25));
        
        TextField fnametf = new TextField();
        fnametf.setMinSize(300, 60);  // Adjust the size as needed
        fnametf.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-border-width: 4px; -fx-text-fill: #ffb000; -fx-font-size: 18px;");

        // GridPane
        GridPane gridpane = new GridPane();
        gridpane.add(fnamelbl, 0, 0);
        gridpane.add(fnametf, 1, 0);
        

        // Buttons
        Button generate = new Button("Generate");
        generate.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        generate.setPrefWidth(300);
        generate.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24));  // Increase the font size
        generate.setOnMouseEntered(eh ->
        {
            generate.setCursor(Cursor.HAND);
            generate.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        generate.setOnMouseExited(eh ->
        {
            generate.setCursor(Cursor.DEFAULT);
            generate.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        Button startBookingbtn = new Button("Start Booking");
        startBookingbtn.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        startBookingbtn.setPrefWidth(300);
        startBookingbtn.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24));  
        startBookingbtn.setOnMouseEntered(eh ->
        {
            startBookingbtn.setCursor(Cursor.HAND);
            startBookingbtn.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        startBookingbtn.setOnMouseExited(eh ->
        {
            startBookingbtn.setCursor(Cursor.DEFAULT);
            startBookingbtn.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        // VBox
        VBox vbox = new VBox();
        vbox.setMinSize(600, 600);
        
        Button existingGuest = new Button("Existing Guest");
        existingGuest.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        existingGuest.setPrefWidth(300);
        existingGuest.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24));  // Increase the font size
        existingGuest.setOnMouseEntered(eh ->
        {
            existingGuest.setCursor(Cursor.HAND);
            existingGuest.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        existingGuest.setOnMouseExited(eh ->
        {
            existingGuest.setCursor(Cursor.DEFAULT);
            existingGuest.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        Button newGuest = new Button("New Guest");
        newGuest.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        newGuest.setPrefWidth(300);
        newGuest.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24));  // Increase the font size
        newGuest.setOnMouseEntered(eh ->
        {
            newGuest.setCursor(Cursor.HAND);
            newGuest.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        newGuest.setOnMouseExited(eh ->
        {
            newGuest.setCursor(Cursor.DEFAULT);
            newGuest.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        VBox buttons = new VBox();
        buttons.getChildren().addAll(iv, description, newGuest, existingGuest);
        buttons.setMinSize(600, 600);

        // Function to validate the name
        // Scene settings
        startBookingbtn.setOnAction(e -> {
            try {
                System.out.println("Button 1 clicked");
                primaryStage.setScene(currentBooking.chooseTrip(primaryStage));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(40);
        gridpane.setVgap(25);

        vbox.setSpacing(50);
        vbox.setAlignment(Pos.CENTER);
        
        buttons.setSpacing(30);
        buttons.setAlignment(Pos.CENTER);
        
        // StackPane
        StackPane stackpane = new StackPane();
        Rectangle rectangle = new Rectangle(700, 600);
        rectangle.setArcWidth(20); 
        rectangle.setArcHeight(20); 
        rectangle.setFill(Color.rgb(10,12,38, 0.5));
        rectangle.setStyle("-fx-border-radius: 5px;");
        stackpane.getChildren().addAll(rectangle, buttons);
        
        Label GID = new Label("Guest ID");
        GID.setTextFill(Color.web("#ffb000"));
        GID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 25));
                
        TextField gid = new TextField();
        gid.setMinSize(300, 60);  // Adjust the size as needed
        gid.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-border-width: 4px; -fx-text-fill: #ffb000; -fx-font-size: 18px;");

        GridPane eg = new GridPane();
        eg.add(GID, 0, 0);
        eg.add(gid, 1, 0);
        eg.setHgap(40);
        eg.setVgap(25);
        eg.setAlignment(Pos.CENTER);
        
        Button go = new Button("Go");
        go.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        go.setPrefWidth(300);
        go.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24));  // Increase the font size
        go.setOnMouseEntered(eh ->
        {
            go.setCursor(Cursor.HAND);
            go.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        go.setOnMouseExited(eh ->
        {
            go.setCursor(Cursor.DEFAULT);
            go.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        newGuest.setOnAction(eh -> 
        {
            stackpane.getChildren().clear();
            vbox.getChildren().addAll(iv, description, gridpane, generate);
            stackpane.getChildren().addAll(rectangle, vbox);
        });
        
        existingGuest.setOnAction(eh -> 
        {
            stackpane.getChildren().clear();
            vbox.getChildren().clear();
            vbox.getChildren().addAll(iv, description, eg, go);
            stackpane.getChildren().addAll(rectangle, vbox);
        });
                
        go.setOnAction(eh -> 
        {
            if(!gid.getText().isEmpty())
            {
                goFired = true;
                generate.fire();
            }
        });

        generate.setOnAction((ActionEvent event) -> {
            String enteredName = fnametf.getText().trim();
            
            Label gNamelbl = new Label("Guest Name:");
            gNamelbl.setTextFill(Color.web("white"));
            gNamelbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            
            Label gIDlbl = new Label("Guest ID:");
            gIDlbl.setTextFill(Color.web("white"));
            gIDlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
            Label gPasslbl = new Label("Guest Password:");
            gPasslbl.setTextFill(Color.web("white"));
            gPasslbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
            Label gBookingIDlbl = new Label("Booking ID:");
            gBookingIDlbl.setTextFill(Color.web("white"));
            gBookingIDlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            
            if(goFired == true)
            {
                Booking boo = new Booking();
                boo.setGuest_Name(Guest.guests.get(Integer.parseInt(gid.getText())).Name);
                boo.setGuestPassword(Guest.guests.get(Integer.parseInt(gid.getText())).Password);
                boo.setGuest_id(Guest.guests.get(Integer.parseInt(gid.getText())).ID);
                boo.setBooking_id(boo.generateBookingId());
                boo.setBooking_time(LocalDateTime.now());
                                
                currentBooking = boo;
                
                System.out.println(currentBooking.booking_id);
                System.out.println( boo.getBooking_id());
                
                
                Label gName = new Label(boo.getGuest_Name());
                gName.setTextFill(Color.web("#ffb000"));
                gName.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
        
                Label gID = new Label("" + boo.getGuest_id());
                gID.setTextFill(Color.web("#ffb000"));
                gID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                Label gPass = new Label(boo.getGuestPassword());
                gPass.setTextFill(Color.web("#ffb000"));
                gPass.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                Label gBookingID = new Label("" + boo.getBooking_id());
                gBookingID.setTextFill(Color.web("#ffb000"));
                gBookingID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                description.setText("Guest's Information");
                
                gridpane.getChildren().clear();
                gridpane.add(gNamelbl, 0, 0);
                gridpane.add(gName, 1, 0);
                gridpane.add(gIDlbl, 0, 1);
                gridpane.add(gID, 1, 1);
                gridpane.add(gPasslbl, 0, 2);
                gridpane.add(gPass, 1, 2);
                gridpane.add(gBookingIDlbl, 0, 3);
                gridpane.add(gBookingID, 1, 3);

                vbox.getChildren().clear();
                vbox.getChildren().addAll(iv, description, gridpane, startBookingbtn);
            }
            
            else{
            // Check if the name is empty
            if (enteredName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Name Field cannot be empty. Please enter your name.");
                alert.showAndWait();
            } else if (!isValidName(enteredName)) {
                // Check if the name contains only alphabets and spaces
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid name. Please enter a valid name with only alphabets and spaces.");
                alert.showAndWait();
            } else {
                // If all validations pass, proceed with setting the information
                Booking boo = new Booking();
                boo.setGuest_Name(enteredName);
                boo.setGuestPassword(Admin.generateGuestPassword());
                boo.setGuest_id(Admin.generateGuestId());
                boo.setBooking_id(boo.generateBookingId());
                boo.setBooking_time(LocalDateTime.now());
                
                Guest g = new Guest(boo.getGuest_id(), boo.getGuestPassword(), enteredName);
                
                currentBooking = boo;
                
                System.out.println(currentBooking.booking_id);
                System.out.println( boo.getBooking_id());
                
                Label gName = new Label(boo.getGuest_Name());
                gName.setTextFill(Color.web("#ffb000"));
                gName.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                        
                Label gID = new Label("" + boo.getGuest_id());
                gID.setTextFill(Color.web("#ffb000"));
                gID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                Label gPass = new Label(boo.getGuestPassword());
                gPass.setTextFill(Color.web("#ffb000"));
                gPass.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                Label gBookingID = new Label("" + boo.getBooking_id());
                gBookingID.setTextFill(Color.web("#ffb000"));
                gBookingID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                
                description.setText("Guest's Information");
                
                gridpane.getChildren().clear();
                gridpane.add(gNamelbl, 0, 0);
                gridpane.add(gName, 1, 0);
                gridpane.add(gIDlbl, 0, 1);
                gridpane.add(gID, 1, 1);
                gridpane.add(gPasslbl, 0, 2);
                gridpane.add(gPass, 1, 2);
                gridpane.add(gBookingIDlbl, 0, 3);
                gridpane.add(gBookingID, 1, 3);

                vbox.getChildren().clear();
                vbox.getChildren().addAll(iv, description, gridpane, startBookingbtn);
                
            }
            }
        });
        

        // Adding items to the page
        root.setCenter(stackpane);
        Region centerRegion = (Region) root.getCenter();
        centerRegion.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        Scene scene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setTitle("Bus-Ticket Booking System - Generate Guest Information");
                
        // Stage settings
        return scene;
    }

    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }


    @Override
    public void search() {
    
    int count = 0;

    bookingsVBox.getChildren().clear();
    for (Booking book : Booking.getBook().values()) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String formattedArrivalDateTime = book.getArrivalDateTime() != null ?
        book.getArrivalDateTime().format(formatter) : "N/A";

    String formattedDepartureDateTime = book.getDepartureDateTime() != null ?
        book.getDepartureDateTime().format(formatter) : "N/A";

    String formattedBookingTime = book.getBooking_time() != null ?
        book.getBooking_time().format(formatter) : "N/A";

    String stringTrip = String.format("%s %s %s %s %s %d %.2f %s %s %s %s %s",
        book.getFromBusStop(),
        book.getToBusStop(),
        book.getFromDestination(),
        book.getToDestination(),
        book.getLicense_plate(),
        book.getBooking_id(),
        book.getTotal_price(),
        formattedArrivalDateTime,
        formattedDepartureDateTime,
        book.getGuest_Name(),
        formattedBookingTime,
        book.getReceptionist_id()
);


       if (stringTrip.toLowerCase().contains(searchValue.toLowerCase())) {
                bookingHBox.setAlignment(Pos.CENTER);

            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            Label bookingIdLabel = new Label(Integer.toString(book.getBooking_id()));
            Label guestNameLabel = new Label(book.getGuest_Name());
            Label guestIdLabel = new Label(Integer.toString(book.getGuest_id()));
            Label tripIdLabel = new Label(Integer.toString(book.getTrip_id()));
            Label ticketsLabel = new Label(Integer.toString(book.getNumberOfTickets()));
            Label priceLabel = new Label(Double.toString(book.getTotal_price()));
            Label toDestinationLabel = new Label(String.valueOf(book.getToDestination()));
            Label fromBusStopLabel = new Label(book.getFromBusStop());
            Label toBusStopLabel = new Label(book.getToBusStop());
            Label departureLabel = new Label(formattedDepartureDateTime);
            Label arrivalLabel = new Label(formattedArrivalDateTime);
            Label vehicleIdLabel = new Label(book.getLicense_plate());
            Label bookingTimeLabel = new Label(formattedBookingTime);
            Label Reception= new Label(Integer.toString(Booking.getReceptionist_id()));

            bookingHBox.getChildren().addAll(
                   bookingIdLabel ,guestNameLabel,guestIdLabel,tripIdLabel,ticketsLabel, fromBusStopLabel, toBusStopLabel, departureLabel,
                    arrivalLabel, vehicleIdLabel, priceLabel, editButton, deleteButton,bookingTimeLabel,Reception
            );

            bookingsVBox.getChildren().add(bookingHBox);
            editButton.setOnAction(event -> {
            
              book.edit();
               ticketsLabel.setText((Integer.toString(book.getNumberOfTickets())));
               
               priceLabel.setText(Double.toString(book.getTotal_price()));

   
            }
            );
         deleteButton.setOnAction(event -> {
    book.remove();
    System.out.println(book);
    VBox parentVBox = (VBox) bookingHBox.getParent();
    parentVBox.getChildren().remove(bookingHBox);
});
            count++;
        }
    }

      if (count == 0) {
            Label noResultsLabel = new Label("No matching trips found.");
            bookingsVBox.getChildren().add(noResultsLabel);
        }

 
}

        public Scene ExistingBookings(Stage primaryStage) throws FileNotFoundException {
        GridPane root1 = new GridPane();
        root1.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(6).jpg'); -fx-background-size: cover;");
        scene4 = new Scene(root1, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

        root1.setAlignment(Pos.TOP_CENTER);
        root1.setHgap(10);
        root1.setVgap(10);
        root1.setPadding(new Insets(20));
        
    Label lbl24 = new Label("Manage Bookings");
    lbl24.setTextFill(Color.WHITE);
    lbl24.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-size: 35; -fx-font-weight: bold;");

    Label lbl25 = new Label("Search:");
    lbl25.setTextFill(Color.WHITE);
    lbl25.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-size: 19; -fx-font-weight: bold;");

    TextField txt1 = new TextField("");
    txt1.setPromptText("Search Users");
    txt1.setStyle("-fx-font-size: 15; -fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-border-width: 3px");
    txt1.setMaxWidth(800);
    txt1.setMinWidth(800);
    Button btn8 = new Button("cancel search");
    btn8.setStyle("-fx-font-size: 15; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

        Button backBtn = new Button("Back");
                    backBtn.setStyle("-fx-font-size: 15; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

        Button exitBtn = new Button("Exit");
            exitBtn.setStyle("-fx-font-size: 15; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

        exitBtn.setOnAction(e -> primaryStage.close());

        root1.add(exitBtn, 3, 3);
 

        backBtn.setOnAction(e -> {
            primaryStage.setScene(Receptionist.homepage);
            
            readFromFile();
        });

        txt1.setOnKeyPressed(eh -> {
            searchValue = txt1.getText().trim();
            search(); 
        });

        Button deleteAllBtn = new Button("Delete All");
        deleteAllBtn.setStyle("-fx-font-size: 15; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

        deleteAllBtn.setOnAction(eh -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete All Bookings");
            confirmationAlert.setContentText("Are you sure you want to delete all bookings?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                book.clear();
                saveToFile();
                viewFile();
                try {
                    ExistingBookings(primaryStage);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
btn8.setOnAction(eh->{
 bookingsVBox.getChildren().clear();

            try {
                ExistingBookings(primaryStage);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }


});

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
            primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
          }
        });
        
        Button home = new Button();
        home.setStyle("-fx-background-color: transparent;");
        ImageView homee = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/home.png")));
        homee.setFitWidth(40);
        homee.setFitHeight(40);
        home.setGraphic(homee);
      
        home.setOnMouseEntered(eh -> 
        {
            home.setCursor(Cursor.HAND);
        });
      
        home.setOnAction(eh ->
        {
          Alert homeq = new Alert(Alert.AlertType.CONFIRMATION);
          homeq.setHeaderText(null);
          homeq.setContentText("Are you sure you want to return to homepage?");
          Optional<ButtonType> response = homeq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
              primaryStage.setScene(Receptionist.homepage);
          }
        });
        
        HBox buttons = new HBox(2,logout, home );
        buttons.setAlignment(Pos.TOP_RIGHT);

        root1.add(deleteAllBtn, 3, 4);

        root1.add(lbl24, 0, 0);
        root1.add(lbl25, 0, 1);
       
        root1.add(txt1, 1, 1);
        root1.add(btn8, 2, 1);
        root1.add(backBtn, 9, 0);
        root1.add(buttons, 10, 0);
        ScrollPane scrollPane = new ScrollPane(bookingsVBox);

        HBox propertyNamesHBox = new HBox(10);
        Label bookIdLabel = new Label(String.format("%-10s", "Book ID"));
        Label guestNameLabel = new Label(String.format("%-30s", "Guest Name"));
        Label guestIdLabel = new Label(String.format("%-10s", "Guest ID"));
        Label tripIdLabel = new Label(String.format("%-10s", "Trip ID"));
        Label ticketsLabel = new Label(String.format("%-10s", "Tickets"));
        Label totalPriceLabel = new Label(String.format("%-20s", "Total Price"));
        Label fromDestinationLabel = new Label(String.format("%-30s", "From Destination"));
        Label fromBusStopLabel = new Label(String.format("%-20s", "From Bus Stop"));
        Label departureDateTimeLabel = new Label(String.format("%-25s", "Departure Date Time"));
        Label toDestinationLabel = new Label(String.format("%-30s", "To Destination"));
        Label toBusStopLabel = new Label(String.format("%-30s", "To Bus Stop"));
        Label arrivalDateTimeLabel = new Label(String.format("%-30s", "Arrival Date Time"));
        Label licensePlateLabel = new Label(String.format("%-30s", "License Plate"));
        Label receptionistIdLabel = new Label(String.format("%-30s", "Receptionist ID"));
        
        
    String labelFontStyle = "-fx-font-family: 'Helvetica World'; -fx-font-weight: bold; -fx-text-fill: #ffb000;";
    bookIdLabel.setStyle(labelFontStyle);
    guestNameLabel.setStyle(labelFontStyle);
    guestIdLabel.setStyle(labelFontStyle);
    tripIdLabel.setStyle(labelFontStyle);
    ticketsLabel.setStyle(labelFontStyle);
    totalPriceLabel.setStyle(labelFontStyle);
    fromDestinationLabel.setStyle(labelFontStyle);
    fromBusStopLabel.setStyle(labelFontStyle);
    departureDateTimeLabel.setStyle(labelFontStyle);
    toDestinationLabel.setStyle(labelFontStyle);
    toBusStopLabel.setStyle(labelFontStyle);
    arrivalDateTimeLabel.setStyle(labelFontStyle);
    licensePlateLabel.setStyle(labelFontStyle);
    receptionistIdLabel.setStyle(labelFontStyle);

        propertyNamesHBox.getChildren().addAll(
                bookIdLabel,
                guestNameLabel,
                guestIdLabel,
                tripIdLabel,
                ticketsLabel,
                totalPriceLabel,
                fromDestinationLabel,
                fromBusStopLabel,
                departureDateTimeLabel,
                toDestinationLabel,
                toBusStopLabel,
                arrivalDateTimeLabel,
                licensePlateLabel,
                receptionistIdLabel
        );

        bookingsVBox.getChildren().add(propertyNamesHBox);

        for (Booking book : Booking.getBook().values()) {
            HBox bookingHBox = new HBox(10); // HBox for each booking

            Label bookingIdLabel2 = new Label(String.format("%-15s", book.getBooking_id()));
            Label guestNameLabel2 = new Label(String.format("%-30s", book.getGuest_Name()));
            Label guestIdLabel2 = new Label(String.format("%-15s", book.getGuest_id()));
            Label tripIdLabel2 = new Label(String.format("%-20s", book.getTrip_id()));
            Label ticketsLabel2 = new Label(String.format("%-15s", book.getNumberOfTickets()));
            Label totalPriceLabel2 = new Label(String.format("%-30s", book.getTotal_price()));
            Label fromDestinationLabel2 = new Label(String.format("%-30s", book.getFromDestination()));
            Label fromBusStopLabel2 = new Label(String.format("%-30s", book.getFromBusStop()));
            Label departureDateTimeLabel2 = new Label(String.format("%-30s", book.getDepartureDateTime()));
            Label toDestinationLabel2 = new Label(String.format("%-35s", book.getToDestination()));
            Label toBusStopLabel2 = new Label(String.format("%-30s", book.getToBusStop()));
            Label arrivalDateTimeLabel2 = new Label(String.format("%-35s", book.getArrivalDateTime()));
            Label licensePlateLabel2 = new Label(String.format("%-30s", book.getLicense_plate()));
            Label receptionistIdLabel2 = new Label(String.format("%-30s", Booking.getReceptionist_id()));

            Button btn9 = new Button("Edit Booking");
            btn9.setOnAction(event -> {
                book.edit();
                bookingsVBox.getChildren().clear();
                try {
                    ExistingBookings(primaryStage);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Button btn10 = new Button("Cancel Booking");
            btn10.setOnAction(event -> {
                book.remove();
                System.out.println(book);
                VBox parentVBox = (VBox) bookingHBox.getParent();
                parentVBox.getChildren().remove(bookingHBox);
            });

            bookingHBox.getChildren().addAll(
                    bookingIdLabel2,
                    guestNameLabel2,
                    guestIdLabel2,
                    tripIdLabel2,
                    ticketsLabel2,
                    totalPriceLabel2,
                    fromDestinationLabel2,
                    fromBusStopLabel2,
                    departureDateTimeLabel2,
                    toDestinationLabel2,
                    toBusStopLabel2,
                    arrivalDateTimeLabel2,
                    licensePlateLabel2,
                    receptionistIdLabel2,
                    btn9,
                    btn10
            );
            bookingsVBox.getChildren().add(bookingHBox);
        }
        bookingsVBox.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setFitToHeight(true);
     
        root1.add(scrollPane, 0, 2, 3, 4); // Adjusted row and column indices for ScrollPane

        primaryStage.setTitle("Existing Bookings");
        primaryStage.setScene(scene4);
        return scene4;
    }


    public Scene chooseTrip(Stage primaryStage) throws FileNotFoundException {
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(5).jpg'); -fx-background-size: cover;");
        
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
            primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
          }
        });
        
        Button home = new Button();
        home.setStyle("-fx-background-color: transparent;");
        ImageView homee = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/home.png")));
        homee.setFitWidth(40);
        homee.setFitHeight(40);
        home.setGraphic(homee);
      
        home.setOnMouseEntered(eh -> 
        {
            home.setCursor(Cursor.HAND);
        });
      
        home.setOnAction(eh ->
        {
          Alert homeq = new Alert(Alert.AlertType.CONFIRMATION);
          homeq.setHeaderText(null);
          homeq.setContentText("Are you sure you want to return to homepage?");
          Optional<ButtonType> response = homeq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
              primaryStage.setScene(Receptionist.homepage);
          }
        });
        
        HBox buttons = new HBox(2,logout, home );
        buttons.setAlignment(Pos.TOP_RIGHT);
        
        root.setRight(buttons);

        // Image
        Image i = new Image(new FileInputStream("/home/jana/Downloads/distance(1).png"));
        ImageView iv = new ImageView(i);
        iv.setFitHeight(150);
        iv.setFitWidth(150);

        GridPane rooting = new GridPane();

        Label lblChooseTrip = new Label("Select Trip Details");
        lblChooseTrip.setTextFill(Color.web("#ffb000"));
        lblChooseTrip.setFont(Font.font("Montserrat ", FontWeight.BOLD, 50));

        // Add other components and functionality for choosing a trip
        Label lbl34 = new Label("PickUp Point:");
        lbl34.setTextFill(Color.web("#ffb000"));
        lbl34.setFont(Font.font("Montserrat ", FontWeight.BOLD, 30));        
        
        Label lbl35 = new Label("Destination Point:");
        lbl35.setTextFill(Color.web("#ffb000"));
        lbl35.setFont(Font.font("Montserrat ", FontWeight.BOLD, 30)); 
        
        Label lbl36 = new Label("Date:");
        lbl36.setTextFill(Color.web("#ffb000"));
        lbl36.setFont(Font.font("Montserrat ", FontWeight.BOLD, 30)); 

        Label lbl37 = new Label("Number of tickets :");
        lbl37.setTextFill(Color.web("#ffb000"));
        lbl37.setFont(Font.font("Montserrat ", FontWeight.BOLD, 30)); 
        
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setTextFill(Color.GRAY);
                }
            }
        });
        
        ticketsSpinner = new Spinner<>(1, 10, 1);
        
        ObservableList<String> destOptions = FXCollections.observableArrayList();
        for (Destination dest : Destination.values()) {
            destOptions.add(dest.getDisplayName());
        }
        
        ComboBox<Destination> from1 = new ComboBox<>();
        ComboBox<Destination> to1 = new ComboBox<>();
        ComboBox<String> location1 = new ComboBox<>();
        ComboBox<String> location2 = new ComboBox<>();

        from1.setVisible(false);
        to1.setVisible(false);
        
        Button btn12 = new Button("View Available Trips");
        btn12.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        btn12.setPrefWidth(800);
        btn12.setFont(Font.font("Helvetica World", FontWeight.BOLD, 25));  
        btn12.setOnMouseEntered(eh ->
        {
            btn12.setCursor(Cursor.HAND);
            btn12.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        btn12.setOnMouseExited(eh ->
        {
            btn12.setCursor(Cursor.DEFAULT);
            btn12.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        // Add event handlers and other functionality as needed
        rooting.add(lbl34, 0, 0);
        rooting.add(from1, 2, 0);
        rooting.add(lbl35, 0, 1);
        rooting.add(to1, 2, 1);
        rooting.add(lbl36, 0, 2);
        rooting.add(datePicker, 1, 2);
        rooting.add(lbl37, 0, 3);
        rooting.add(ticketsSpinner, 1, 3);
        
        
        ticketsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            numberOfTickets = newValue;
            try {
                if (numberOfTickets < 0) {
                    throw new IllegalArgumentException("The number inserted is below zero (Negative). Please enter a valid value.");
                }

                Trips selectedTrip = tripComboBox.getValue();

                if (selectedTrip == null) {
                    throw new RuntimeException("No tickets available at the moment");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("Error in payment calculation! Please try again. " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
            }
        });

        HBox fromComboBox = create_destination_ComboBox(5, 1, from1, location2, new ArrayList<>());
        rooting.add(fromComboBox, 1, 0);

        HBox toComboBox = create_destination_ComboBox(5, 1, to1, location1, new ArrayList<>());
        rooting.add(toComboBox, 1, 1);
        Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
        Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
        btn12.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (buttonClickCount == 0) {
                    // Initial action
                    Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
                    Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
                    setFromDestination(selectedFromDestination != null ? selectedFromDestination : null);
                    setToDestination(selectedToDestination != null ? selectedToDestination : null);

                    setTolocation(location1.getValue());
                    setFromlocation(location2.getValue());
                    setSelectedDate(datePicker.getValue());
                    int numberOfTickets = ticketsSpinner.getValue();
                    setNumberOfTickets(numberOfTickets);

                    // Check if from1, to1, and selectedDate are null
                    if (from1.getValue() == null || to1.getValue() == null || selectedDate == null) {
                        // Display an alert if any of the fields is not filled
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;  // Stop further execution
                    }

                    System.out.println("Selected Information:");
                    System.out.println("From Destination: " + from1.getValue());
                    System.out.println("To Destination: " + to1.getValue());
                    System.out.println("Selected Bus Stop (location1): " + location1.getValue());
                    System.out.println("Selected Bus Stop (location2): " + location2.getValue());
                    System.out.println("Selected Date: " + datePicker.getValue());
                    System.out.println("Number of Tickets: " + ticketsSpinner.getValue());
                    // Display the filtered trips
                    try {
                        // Continue with the rest of your logic
                        primaryStage.setScene(createManageBookings(primaryStage));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Increment the counter
                    buttonClickCount++;
                } else {
                    // Reset data and get new data from nodes
                    // Reset other fields as needed
                    Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
                    Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
                    setFromDestination(selectedFromDestination != null ? selectedFromDestination : null);
                    setToDestination(selectedToDestination != null ? selectedToDestination : null);

                    setTolocation(location1.getValue());
                    setFromlocation(location2.getValue());
                    setSelectedDate(datePicker.getValue());
                    int numberOfTickets = ticketsSpinner.getValue();
                    setNumberOfTickets(numberOfTickets);

                    // Check if from1, to1, and selectedDate are null
                    if (from1.getValue() == null || to1.getValue() == null || selectedDate == null) {
                        // Display an alert if any of the fields is not filled
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;  // Stop further execution
                    }

                    System.out.println("Selected Information:");
                    System.out.println("From Destination: " + from1.getValue());
                    System.out.println("To Destination: " + to1.getValue());
                    System.out.println("Selected Bus Stop (location1): " + location1.getValue());
                    System.out.println("Selected Bus Stop (location2): " + location2.getValue());
                    System.out.println("Selected Date: " + datePicker.getValue());
                    System.out.println("Number of Tickets: " + ticketsSpinner.getValue());
                    // Display the filtered trips
                    try {
                        // Continue with the rest of your logic
                        primaryStage.setScene(currentBooking.createManageBookings(primaryStage));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Reset the counter
                    buttonClickCount = 0;
                }
            }
        });
                
        rooting.setAlignment(Pos.CENTER);
        rooting.setHgap(40);
        rooting.setVgap(25);
        rooting.setPadding(new Insets(40));
        
        VBox layout = new VBox(lblChooseTrip, rooting, btn12);
        layout.setSpacing(50);
        layout.setAlignment(Pos.CENTER);
        
        StackPane stackpane = new StackPane();
        Rectangle rectangle = new Rectangle(900, 600);
        rectangle.setArcWidth(20); 
        rectangle.setArcHeight(20); 
        rectangle.setFill(Color.rgb(10,12,38, 0.5));
        rectangle.setStyle("-fx-border-radius: 5px;");
        rectangle.setMouseTransparent(true);
        stackpane.getChildren().addAll(rectangle, layout);

        // Adding items to the page
        root.setCenter(stackpane);
        Region centerRegion = (Region) root.getCenter();
        centerRegion.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        scene6 = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setTitle("Bus-Ticket Booking System - Choose Trip");
        return scene6;
    }
    
    public static HBox create_destination_ComboBox(int spacing, int par, ComboBox<Destination> to1, ComboBox<String> location1, List<String> get) {
        ObservableList<String> destOptions = FXCollections.observableArrayList();
        for (Destination dest : Destination.values()) {
            destOptions.add(dest.getDisplayName());
        }
        ComboBox<String> destinationCBO = new ComboBox<>(destOptions);

        ComboBox<String> busStopsCBO = new ComboBox<>();
        busStopsCBO.setVisible(false);

        destinationCBO.setOnAction(event -> {
            busStopsCBO.setVisible(true);

            String selectedDestination = destinationCBO.getValue();
            selectedDestination = selectedDestination.replaceAll("\\s", "_");
            List<String> busStopsList = Destination.destinationBusStops.get(Destination.valueOf(selectedDestination.toUpperCase()));

            if (busStopsList != null && !busStopsList.isEmpty()) {
                busStopsCBO.setVisible(true);
                ObservableList<String> busStopObs = FXCollections.observableArrayList(busStopsList);
                busStopsCBO.setItems(busStopObs);
                to1.setValue(Destination.valueOf(selectedDestination.toUpperCase()));
            } else {
                busStopsCBO.setVisible(false);
            }
        });

        busStopsCBO.setOnAction(event -> {
            String selectedBusStop = busStopsCBO.getValue();

            if (selectedBusStop != null) {
                selectedBusStop = selectedBusStop.replaceAll("\\s", "_");

                try {

                    location1.setValue(selectedBusStop);

                    if (location1.isVisible()) {
                        location1.setValue(selectedBusStop);

                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid Bus Stop: " + selectedBusStop);
                }
            } else {
                System.out.println("I can't read");
            }
        });

        HBox hbox = new HBox(spacing, destinationCBO, busStopsCBO);
        return hbox;
    }

    public Scene createManageBookings(Stage primaryStage) throws FileNotFoundException {
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(6).jpg'); -fx-background-size: cover;");
        Insets in= new Insets(50);
        pane.setPadding(in);
        pane.setHgap(20);
        pane.setVgap(20);
        
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
            primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
          }
        });
        
        Button home = new Button();
        home.setStyle("-fx-background-color: transparent;");
        ImageView homee = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/home.png")));
        homee.setFitWidth(40);
        homee.setFitHeight(40);
        home.setGraphic(homee);
      
        home.setOnMouseEntered(eh -> 
        {
            home.setCursor(Cursor.HAND);
        });
      
        home.setOnAction(eh ->
        {
          Alert homeq = new Alert(Alert.AlertType.CONFIRMATION);
          homeq.setHeaderText(null);
          homeq.setContentText("Are you sure you want to return to homepage?");
          Optional<ButtonType> response = homeq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
              primaryStage.setScene(Receptionist.homepage);
          }
        });
        
        HBox buttons = new HBox(2,logout, home );
        buttons.setAlignment(Pos.TOP_RIGHT);
                
        //pane.setBackground(new Background(background));
        scene1 = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        VBox contentVBox = new VBox(10);
        contentVBox.setSpacing(10);
        contentVBox.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        Label lbl6 = new Label("Available Trips According to Your Choice:");
        lbl6.setTextFill(Color.web("#ffb000"));
        lbl6.setVisible(false);
        lbl6.setFont(Font.font("Montserrat", FontWeight.BOLD, 30));
        Button updateLabelButton = new Button("Available trips");
        updateLabelButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        updateLabelButton.setPrefWidth(300);
        updateLabelButton.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));  
        updateLabelButton.setOnMouseEntered(eh ->
        {
            updateLabelButton.setCursor(Cursor.HAND);
            updateLabelButton.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        updateLabelButton.setOnMouseExited(eh ->
        {
            updateLabelButton.setCursor(Cursor.DEFAULT);
            updateLabelButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        
        Label pressButtonLabel = new Label("Press the button to view trips");
        pressButtonLabel.setTextFill(Color.web("#ffb000"));
        pressButtonLabel.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        pane.add(updateLabelButton, 1, 0);
        pane.add(pressButtonLabel, 0, 0);
        Label dplace = new Label();
        dplace.setTextFill(Color.web("#ffb000"));
        dplace.setFont(Font.font("Montserrat", FontWeight.BOLD, 15));
        updateLabelButton.setOnAction(e -> {
        lbl6.setVisible(true);
        updateLabelButton.setVisible(false);
       
        dplace.setText("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
        System.out.println("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
            

            pane.add(dplace, 0, 2);
            System.out.println("Matching Trips:");
            System.out.println("Filtering Criteria:");
            System.out.println("From Destination: " + getFromDestination());
            System.out.println("To Destination: " + getToDestination());
            System.out.println("From Bus Stop: " + getFromlocation());
            System.out.println("To Bus Stop: " + getTolocation());
            System.out.println("Selected Date: " + getSelectedDate());
            filteredTrips = Trips.TripsMap.values().stream()
                   .filter(trip -> {
                        System.out.println("Trip Details:");
                        System.out.println("Trip ID: " + trip.getTrip_id());
                        System.out.println("From Destination: " + trip.getFrom_Destination() + "From Destination: " + getFromDestination());
                        System.out.println("To Destination: " + trip.getTo_Destination() + "To Destination: " + getToDestination());
                        System.out.println("From Bus Stop: " + trip.getFrom_BusStop() + "Selected Bus Stop (location1): " + getFromlocation());
                        System.out.println("To Bus Stop: " + trip.getTo_BusStop() + "Selected Bus Stop (location2): " + getTolocation());

                        Label numTicketsLabel = new Label("Number of Tickets: 0");
                        Label totalPriceLabel = new Label("Total Price: $0");

                        boolean isMatching = Objects.nonNull(trip.getFrom_Destination())
                                && Objects.nonNull(trip.getTo_Destination())
                                && trip.getFrom_Destination().equals(getFromDestination())
                                && trip.getTo_Destination().equals(getToDestination())
                                && Objects.equals(trip.getTo_BusStop(), getTolocation())
                                && Objects.equals(trip.getFrom_BusStop(), getFromlocation())
                                && Vehicle.AvailibilityMap.get(trip.getTrip_id()) > getNumberOfTickets();

                        if (isMatching && trip.getDepartureDateTime() != null) {
                            LocalDateTime tripDepartureDateTime = trip.getDepartureDateTime();
                            LocalDate selectedDating = getSelectedDate();
                            LocalDateTime selectedDateTime = selectedDate.atStartOfDay();

                            if (!tripDepartureDateTime.toLocalDate().isEqual(selectedDateTime.toLocalDate())) {
                                isMatching = false;
                            }
                        }

                        System.out.println("Is Matching: " + isMatching);
                        System.out.println("Trip Details:");
                        System.out.println("Trip ID: " + trip.getTrip_id());
                        System.out.println("From Destination: " + trip.getFrom_Destination());
                        System.out.println("To Destination: " + trip.getTo_Destination());
                        System.out.println("From Bus Stop: " + trip.getFrom_BusStop());
                        System.out.println("To Bus Stop: " + trip.getTo_BusStop());
                        System.out.println("Departure Date: " + trip.getDepartureDateTime());
                        System.out.println("Is Matching: " + isMatching);
                        return isMatching;
                    })
                    .collect(Collectors.toList());

            System.out.println("Filtered Trips: " + filteredTrips);

            contentVBox.setAlignment(Pos.CENTER);
            
            HBox tripHBox = new HBox(8);

        
        Label tripIDLabel = new Label("ID");
        tripIDLabel.setTextFill(Color.web("#ffb000"));
        tripIDLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        tripIDLabel.setPrefWidth(40);
        HBox.setHgrow(tripIDLabel, Priority.ALWAYS);
        
        Label vehicleIDTitleLabel = new Label("License Plate");
        vehicleIDTitleLabel.setTextFill(Color.web("#ffb000"));
        vehicleIDTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        vehicleIDTitleLabel.setPrefWidth(150);
        HBox.setHgrow(vehicleIDTitleLabel, Priority.ALWAYS);
            
        Label fromDestinationTitleLabel = new Label("From");
        fromDestinationTitleLabel.setTextFill(Color.web("#ffb000"));
        fromDestinationTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        fromDestinationTitleLabel.setPrefWidth(100);
        HBox.setHgrow(fromDestinationTitleLabel, Priority.ALWAYS);
            
        Label toDestinationLabel = new Label("To");
        toDestinationLabel.setTextFill(Color.web("#ffb000"));
        toDestinationLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        toDestinationLabel.setPrefWidth(150);
        HBox.setHgrow(toDestinationLabel, Priority.ALWAYS);
            
        Label fromBusStopTitleLabel = new Label("Pick Up Point");
        fromBusStopTitleLabel.setTextFill(Color.web("#ffb000"));
        fromBusStopTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        fromBusStopTitleLabel.setPrefWidth(150);
        HBox.setHgrow(fromBusStopTitleLabel, Priority.ALWAYS);
        
        Label toBusStopTitleLabel = new Label("Destination Point");
        toBusStopTitleLabel.setTextFill(Color.web("#ffb000"));
        toBusStopTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        toBusStopTitleLabel.setPrefWidth(150);
        HBox.setHgrow(toBusStopTitleLabel, Priority.ALWAYS);
            
        Label priceTitleLabel = new Label("Price: ");
        priceTitleLabel.setTextFill(Color.web("#ffb000"));
        priceTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        priceTitleLabel.setPrefWidth(100);
        HBox.setHgrow(priceTitleLabel, Priority.ALWAYS);
        
        Label departureDateTimeTitleLabel = new Label("Departure Date: ");
        departureDateTimeTitleLabel.setTextFill(Color.web("#ffb000"));
        departureDateTimeTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        departureDateTimeTitleLabel.setPrefWidth(150);
        HBox.setHgrow(departureDateTimeTitleLabel, Priority.ALWAYS);
            
        Label arrivalDateTimeTitleLabel = new Label("Arrival Date: ");
        arrivalDateTimeTitleLabel.setTextFill(Color.web("#ffb000"));
        arrivalDateTimeTitleLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
        arrivalDateTimeTitleLabel.setPrefWidth(150);
        HBox.setHgrow(arrivalDateTimeTitleLabel, Priority.ALWAYS);
        
        
            tripHBox.getChildren().addAll(
                    tripIDLabel, vehicleIDTitleLabel, fromDestinationTitleLabel,
                    toDestinationLabel, fromBusStopTitleLabel, toBusStopTitleLabel,
                    priceTitleLabel, arrivalDateTimeTitleLabel
            );
            contentVBox.getChildren().add(tripHBox);
            
            if (filteredTrips.isEmpty()) {
                HBox noTripsHBox = new HBox(10);
                Label noTripsLabel = new Label("No trips found.");
                noTripsLabel.setStyle(" -fx-font-size: 16;");
                updateLabelButton.setVisible(false);

                noTripsHBox.getChildren().addAll(noTripsLabel);
                contentVBox.getChildren().add(noTripsHBox);
            } else {
                for (Trips trip : filteredTrips) {
                    HBox tripHBoxs = new HBox(5);

                    Label tripIDValueLabel = new Label(String.valueOf(trip.getTrip_id()));
                    tripIDValueLabel.setTextFill(Color.web("white"));
                    tripIDValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    tripIDValueLabel.setPrefWidth(55);
                    HBox.setHgrow(tripIDValueLabel, Priority.ALWAYS);
        
                    Label vehicleIDValueLabel = new Label(trip.getVehicle_id());
                    vehicleIDValueLabel.setTextFill(Color.web("white"));
                    vehicleIDValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    vehicleIDValueLabel.setPrefWidth(125);
                    HBox.setHgrow(vehicleIDValueLabel, Priority.ALWAYS);
  
                    Label fromDestinationValueLabel = new Label(String.valueOf(trip.getFrom_Destination()));
                    fromDestinationValueLabel.setTextFill(Color.web("white"));
                    fromDestinationValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    fromDestinationValueLabel.setPrefWidth(130);
                    HBox.setHgrow(fromDestinationValueLabel, Priority.ALWAYS);
        
                    Label toDestinationLabel1 = new Label(String.valueOf(trip.getTo_Destination()));
                    toDestinationLabel1.setTextFill(Color.web("white"));
                    toDestinationLabel1.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    toDestinationLabel1.setPrefWidth(150);
                    HBox.setHgrow(toDestinationLabel1, Priority.ALWAYS);
        
                    Label fromBusStopValueLabel = new Label(trip.getFrom_BusStop());
                    fromBusStopValueLabel.setTextFill(Color.web("white"));
                    fromBusStopValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    fromBusStopValueLabel.setPrefWidth(130);
                    HBox.setHgrow(fromBusStopValueLabel, Priority.ALWAYS);
                    
                    Label toBusStopValueLabel = new Label(trip.getTo_BusStop());
                    toBusStopValueLabel.setTextFill(Color.web("white"));
                    toBusStopValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    toBusStopValueLabel.setPrefWidth(200);
                    HBox.setHgrow(toBusStopValueLabel, Priority.ALWAYS);
        
                    Label priceValueLabel = new Label(String.valueOf(trip.getPrice()));
                    priceValueLabel.setTextFill(Color.web("white"));
                    priceValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    priceValueLabel.setPrefWidth(85);
                    HBox.setHgrow(tripIDLabel, Priority.ALWAYS);
        
                    Label departureDateTimeValueLabel = new Label(String.valueOf(trip.getDepartureDate()));
                    departureDateTimeValueLabel.setTextFill(Color.web("white"));
                    departureDateTimeValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    departureDateTimeValueLabel.setPrefWidth(139);
                    HBox.setHgrow(departureDateTimeValueLabel, Priority.ALWAYS);
           
                    Label arrivalDateTimeValueLabel = new Label(String.valueOf(trip.getArrivalDateTime()));
                    arrivalDateTimeValueLabel.setTextFill(Color.web("white"));
                    arrivalDateTimeValueLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 14));
                    arrivalDateTimeValueLabel.setPrefWidth(139);
                    HBox.setHgrow(arrivalDateTimeValueLabel, Priority.ALWAYS);
                   
                    Button bookbtn = new Button("Choose");
                    String normalStyle = "-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);";
                    String hoverStyle = "-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);";
                    bookbtn.setStyle(normalStyle);
            
          
                    bookbtn.setOnMouseEntered(eh ->
                    {
                        bookbtn.setStyle(hoverStyle);
                    });
            
    
                    bookbtn.setOnMouseExited(eh ->
                    {
                        bookbtn.setStyle(normalStyle);
                    });
                    
                    Label confirmationLabel = new Label();
                    confirmationLabel.setTextFill(Color.WHITE);
                    
                    bookbtn.setVisible(true);
                    Button bookSeatsButton = new Button("Book Seats");
                    bookSeatsButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
                    bookSeatsButton.setPrefWidth(800);
                    bookSeatsButton.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));  
                    bookSeatsButton.setOnMouseEntered(eh ->
                    {
                        bookSeatsButton.setCursor(Cursor.HAND);
                        bookSeatsButton.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
                    });

        // non-hover event
       bookSeatsButton.setOnMouseExited(eh -> {
    bookSeatsButton.setCursor(Cursor.DEFAULT);
    bookSeatsButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
});
                    bookbtn.setOnMouseClicked(eh -> {
                        if (bookbtn.getText().equals("Choose")) {
                            for (Node node : contentVBox.getChildren()) {
                                if (node instanceof HBox && node != tripHBoxs) {
                                    // Hide other HBoxes
                                    node.setVisible(false);
                                }
                            }
                            bookbtn.setText("Remove");

                            Label numTicketsLabel = new Label("Number of Tickets: 0");
                            numTicketsLabel.setText("Number of Tickets: " + getNumberOfTickets());

                            contentVBox.getChildren().add(numTicketsLabel);

                            Label totalPriceLabel = new Label("Total Price: $0");

                            totalPriceLabel.setText("Total Price: $" + Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets()));
                            contentVBox.getChildren().add(totalPriceLabel);

                            bookSeatsButton.setTextFill(Color.BLUE);
                            contentVBox.getChildren().add(bookSeatsButton);
                            
                            
bookSeatsButton.setOnMouseClicked(event -> {
 
    Stage confirmationStage = new Stage();
    confirmationStage.initModality(Modality.APPLICATION_MODAL);
    confirmationStage.setTitle("Booking Confirmation");

    GridPane confirmationPane = new GridPane();
    confirmationPane.setStyle("-fx-background-color: #0A0C26;"); 
    confirmationPane.setHgap(10);
    confirmationPane.setVgap(10);
 Booking b = this;
    Label tripDetailsLabel = new Label("Trip Details:\n" +
            "Guest Name: " + b.getGuest_Name() + "\n" +
            "Guest ID: " + b.getGuest_id() + "\n" +
            "Guest Password: " + b.getGuestPassword() + "\n" +
            "Booking ID: " + b.getBooking_id() + "\n" +
            "Trip ID: " + trip.getTrip_id() + "\n" +
            "From Destination: " + trip.getFrom_Destination() + "\n" +
            "Bus Stop:" + trip.getFrom_BusStop() + "\n" +
            "Departure Time: " + trip.getDepartureDateTime() + "\n" +
            "To Destination: " + trip.getTo_Destination() + "\n" +
            "Bus Stop:" + trip.getTo_BusStop() + "\n" +
            "Arrival Time: " + trip.getArrivalDateTime() + "\n" +
            "Number of tickets: " + getNumberOfTickets() + "\n" +
            "License plate: " + trip.getVehicle_id() + "\n" +
            "Booking ID: " + b.getBooking_id() + "\n" +
             "Receptionist Id: "+Booking.getReceptionist_id()+
            "\nTotal Price: $" + Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets()));
                                b.setTotal_price(Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets()));
                                b.setArrivalDateTime(trip.getArrivalDateTime());
                                b.setDepartureDateTime(trip.getDepartureDateTime());
                                b.setFromBusStop(trip.getFrom_BusStop());
                                b.setTrip_id(trip.getTrip_id());
                                b.setFromDestination(trip.getFrom_Destination());
                                b.setLicense_plate(trip.getVehicle_id());
                                b.setToBusStop(trip.getTo_BusStop());
                                b.setToDestination(trip.getTo_Destination());
                           
                                bookSeatsButton.setDisable(true);
                                pane.add(confirmationLabel, 0, 5);
                                    bookSeatsButton.setDisable(true);

                                    
            tripDetailsLabel.setTextFill(Color.web("#ffb000"));
tripDetailsLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15)); 
confirmationPane.add(tripDetailsLabel, 0, 0);

   

    Button confirmBookingButtonStage = new Button("Confirm");
    confirmBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        confirmBookingButtonStage.setPrefWidth(100);
        confirmBookingButtonStage.setFont(Font.font("Helvetica World", FontWeight.BOLD, 10));  
        confirmBookingButtonStage.setOnMouseEntered(es ->
        {
            confirmBookingButtonStage.setCursor(Cursor.HAND);
            confirmBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
      confirmBookingButtonStage.setOnMouseExited(es -> {
    confirmBookingButtonStage.setCursor(Cursor.DEFAULT);
    confirmBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
});
        
    confirmBookingButtonStage.setOnMouseClicked(confirmEvent -> {
       
        confirmationStage.close();
             add();
    
    });
    confirmationPane.add(confirmBookingButtonStage, 0, 2);

    Button cancelBookingButtonStage = new Button("Cancel");
    cancelBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        cancelBookingButtonStage.setPrefWidth(100);
        cancelBookingButtonStage.setFont(Font.font("Helvetica World", FontWeight.BOLD, 10));  
        cancelBookingButtonStage.setOnMouseEntered(ehs ->
        {
            cancelBookingButtonStage.setCursor(Cursor.HAND);
            cancelBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

      cancelBookingButtonStage.setOnMouseExited(ehs -> {
    cancelBookingButtonStage.setCursor(Cursor.DEFAULT);
    cancelBookingButtonStage.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
});
    cancelBookingButtonStage.setOnMouseClicked(cancelEvent -> {
      
        confirmationStage.close();
    });
    confirmationPane.add(cancelBookingButtonStage, 1, 2);

    // Create a scene and set it to the stage
    Scene confirmationScene = new Scene(confirmationPane, 400, 200);
    confirmationStage.setScene(confirmationScene);

    // Show the new stage
    confirmationStage.showAndWait();
});
                            
                         

                        } else {
                            for (Node node : contentVBox.getChildren()) {
                                if (node instanceof HBox) {
                                    node.setVisible(true);
                                }
                            }
                            bookbtn.setText("Choose");

                            contentVBox.getChildren().removeIf(node
                                    -> node instanceof Label && (((Label) node).getText().startsWith("Number of Tickets")
                                    || ((Label) node).getText().startsWith("Total Price"))
                                    || node instanceof Button && ((Button) node).getText().equals("Book Seats"));
                            pane.getChildren().removeAll(confirmationLabel);

                            bookSeatsButton.setDisable(false);
                        }
                    });

                 

                    tripHBoxs.getChildren().addAll(
                            tripIDValueLabel,
                            vehicleIDValueLabel,
                            fromDestinationValueLabel,
                            toDestinationLabel1,
                            fromBusStopValueLabel,
                            toBusStopValueLabel,
                            priceValueLabel,
                            //departureDateTimeValueLabel,
                            arrivalDateTimeValueLabel, bookbtn
                    );

                    contentVBox.getChildren().add(tripHBoxs);
                }
            }

            ScrollPane scrollPane = new ScrollPane(contentVBox);
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
            scrollPane.setFitToWidth(true);
            System.out.println("TripsMap: " + Trips.TripsMap);
            System.out.println("Adding ScrollPane to pane");

            pane.add(scrollPane, 0, 3);
        });

       
        pane.add(lbl6, 0, 1);
        primaryStage.setTitle("Booking trip");
        primaryStage.setScene(scene1);
        Button backButtonu = new Button("Back");
        backButtonu.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        backButtonu.setPrefWidth(300);
        backButtonu.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));  
        backButtonu.setOnMouseEntered(eh ->
        {
            backButtonu.setCursor(Cursor.HAND);
            backButtonu.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        backButtonu.setOnMouseExited(eh ->
        {
            backButtonu.setCursor(Cursor.DEFAULT);
            backButtonu.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });
        backButtonu.setOnAction(event -> {

            dplace.setText("");
            updateLabelButton.setVisible(true);
            filteredTrips.clear();
            pane.getChildren().remove(contentVBox);
            for (Node node : contentVBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox tripHBoxs = (HBox) node;
                    
                    for (Node hBoxNode : tripHBoxs.getChildren()) {
                        if (hBoxNode instanceof Button) {
                            Button chooseTripButton = (Button) hBoxNode;
                            chooseTripButton.setText("Choose");
                            chooseTripButton.setDisable(false);
                        }
                    }
                }
            }
            try {
                primaryStage.setScene(chooseTrip(primaryStage));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pane.add(backButtonu, 2, 0);
        return scene1;
    }
 public static int findReceptionistWithMostBookings() {
        Map<Integer, Integer> receptionistBookingsCount = new HashMap<>();
        for (Booking booking : Booking.getBook().values()) {
            int receptionistId = Booking.getReceptionist_id();
            receptionistBookingsCount.put(receptionistId, receptionistBookingsCount.getOrDefault(receptionistId, 0) + 1);
        }
        int maxBookings = 0;
        int receptionistWithMostBookings = -1;

        for (Map.Entry<Integer, Integer> entry : receptionistBookingsCount.entrySet()) {
            int receptionistId = entry.getKey();
            int bookingsCount = entry.getValue();

            if (bookingsCount > maxBookings) {
                maxBookings = bookingsCount;
                receptionistWithMostBookings = receptionistId;
            }
        }

        return receptionistWithMostBookings;
    }
  
  public static int findReceptionistWithMostRevenue() {
    HashMap<Integer, Double> receptionistRevenueMap = new HashMap<>();

    for (Booking booking : book.values()) {
        int receptionistId = Booking.getReceptionist_id();
        double revenue = booking.getTotal_price();

        if (receptionistRevenueMap.containsKey(receptionistId)) {
            receptionistRevenueMap.put(receptionistId, receptionistRevenueMap.get(receptionistId) + revenue);
        } else {
            receptionistRevenueMap.put(receptionistId, revenue);
        }
    }
    int receptionistWithMostRevenue = -1;
    double maxRevenue = 0;

    for (int receptionistId : receptionistRevenueMap.keySet()) {
        double revenue = receptionistRevenueMap.get(receptionistId);
        if (revenue > maxRevenue) {
            maxRevenue = revenue;
            receptionistWithMostRevenue = receptionistId;
        }
    }

    return receptionistWithMostRevenue;
}
public static int findGuestWithMostRevenue() {
        HashMap<Integer, Double> guestRevenueMap = new HashMap<>();

        for (Booking booking : book.values()) {
            int guestId = booking.getGuest_id();
            double revenue = booking.getTotal_price();

            if (guestRevenueMap.containsKey(guestId)) {
                guestRevenueMap.put(guestId, guestRevenueMap.get(guestId) + revenue);
            } else {
                guestRevenueMap.put(guestId, revenue);
            }
        }

        int guestWithMostRevenue = -1;
        double maxRevenue = 0;

        for (int guestId : guestRevenueMap.keySet()) {
            double revenue = guestRevenueMap.get(guestId);
            if (revenue > maxRevenue) {
                maxRevenue = revenue;
                guestWithMostRevenue = guestId;
            }
        }

        return guestWithMostRevenue;
    }

    public static int findGuestWithMostBookings() {
        HashMap<Integer, Integer> guestBookingsMap = new HashMap<>();

        for (Booking booking : book.values()) {
            int guestId = booking.getGuest_id();

            if (guestBookingsMap.containsKey(guestId)) {
                guestBookingsMap.put(guestId, guestBookingsMap.get(guestId) + 1);
            } else {
                guestBookingsMap.put(guestId, 1);
            }
        }

        int guestWithMostBookings = -1;
        int maxBookings = 0;

        for (int guestId : guestBookingsMap.keySet()) {
            int bookingsCount = guestBookingsMap.get(guestId);
            if (bookingsCount > maxBookings) {
                maxBookings = bookingsCount;
                guestWithMostBookings = guestId;
            }
        }

        return guestWithMostBookings;
    }
    
    //average revenue over a specific period of time 
public static double calculateTotalRevenue(LocalDateTime start_Time, LocalDateTime end_Time) {
    int booking_count = 0;
    double total_revenues = 0;

    for (Booking booking : book.values()) {
        LocalDateTime booking_timing = booking.getBooking_time();
        if (booking_timing.isAfter(start_Time) && booking_timing.isBefore(end_Time)) {
            booking_count++;
            total_revenues += booking.getTotal_price();
        }
    }

    return total_revenues;
}
public static int getBookingsCountOverTimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        int bookingCount = 0;

        for (Booking booking : book.values()) {
            LocalDateTime bookingTime = booking.getBooking_time();
            if (bookingTime.isAfter(startTime) && bookingTime.isBefore(endTime)) {
                bookingCount++;
            }
        }

        return bookingCount;
    }

}
