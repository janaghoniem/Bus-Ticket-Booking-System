/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication13;

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
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javaapplication13.Trips.Destination;
import static javaapplication13.Trips.TripsMap;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Booking implements manages<Booking>, Serializable {

    private static final HashMap<Integer, Booking> book = new HashMap<>();
    public static Booking currentBooking;
    private transient TextField txtName;
    private transient ComboBox<Trips> tripComboBox;
    private transient Spinner<Integer> ticketsSpinner;
    private transient TableView<Trips> tripTable;
    private transient List<Trips> filteredTrips;
    double windowWidth = 600;
    double windowHeight = 400;
    private Destination selectedDestination;
    String tolocation;
    LocalDate selectedDate;
    static String file = "View Bookings";
    private String selectedBusStop;
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
    private int receptionist_id;
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
    private transient Scene scene;
    private transient Scene scene1;
    private transient Scene scene2;
    private transient Scene scene3;
    private transient Scene scene4;
    private transient Scene scene5;
    private transient Scene scene6;
     private transient VBox bookingsVBox = new VBox(10);
       private transient      HBox bookingHBox = new HBox(10); 

    private transient Image backgroundImage = new Image("file:///D:/year 2/OOP/picturee.jpg");
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
    public Booking(int booking_id, int guest_id, String guest_Name, String guestPassword, int trip_id, String fromDestination, String fromBusStop, LocalDateTime DepartureDateTime, String toDestination, String toBusStop, LocalDateTime arrivalDateTime, double total_price, String license_plate, int receptionist_id, int no_of_tickets) {
        this.guest_id = guest_id;
        this.guestPassword = guestPassword;
        this.trip_id = trip_id;
        this.guest_Name = guest_Name;
        this.total_price = (int) total_price;
        this.license_plate = license_plate;
        this.receptionist_id = receptionist_id;
        this.booking_id = booking_id;
        this.no_of_tickets = no_of_tickets;
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

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuestPassword() {
        return guestPassword;
    }

    public void setGuestPassword(String guestPassword) {
        this.guestPassword = guestPassword;
    }

    public double getTotal_price() {
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

    public int getReceptionist_id() {
        return receptionist_id;
    }

    public void setReceptionist_id(int receptionist_id) {
        this.receptionist_id = receptionist_id;
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
                if (false == Vehicle.newBooking(getTrip_id(), (int) getTotal_price())) {
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
        return "Booking{"
                + "guest_id=" + guest_id
                + ", guest_Name='" + guest_Name + '\''
                + ", guestPassword='" + guestPassword + '\''
                + ", trip_id=" + trip_id
                + ", fromDestination='" + fromDestination + '\''
                + ", fromBusStop='" + fromBusStop + '\''
                + ", DepartureDateTime=" + departureDateTime + '\''
                + ", toDestination='" + toDestination + '\''
                + ", toBusStop='" + toBusStop + '\''
                + ", arrivalDateTime=" + arrivalDateTime+ '\''
                + ", total_price=" + total_price+ '\''
                + ", license_plate='" + license_plate + '\''
                + ", receptionist_id=" + receptionist_id+ '\''
                + ", no_of_tickets=" + numberOfTickets+ '\''
                + '}';
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
                    System.out.println("number of objects read from file into hashmap: " + i);
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

            fillTripsMap();
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

    
    public Scene createPrimaryStage(Stage primaryStage) {

        GridPane root = new GridPane();
        scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Booking System");
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));

        root.setBackground(new Background(background));

        Label lbl = new Label("Check your information before booking:");
        lbl.setTextFill(Color.WHITE);

        Label lbl1 = new Label("Full Name:");
        TextField txt = new TextField();
        Button btn = new Button("Go");

        Label lbl2 = new Label("");
        Label lbl3 = new Label("");
        Label lbl4 = new Label("");
        Label lbl5 = new Label("");
        lbl1.setTextFill(Color.WHITE);
        lbl2.setTextFill(Color.WHITE);
        lbl3.setTextFill(Color.WHITE);
        lbl4.setTextFill(Color.WHITE);
        lbl5.setTextFill(Color.WHITE);
        Button btn1 = new Button("Let's start booking");
        btn1.setVisible(false);

        // Add components to the GridPane
        root.add(lbl, 0, 1, 3, 1);
        root.add(lbl1, 0, 2);
        root.add(txt, 1, 2);
        root.add(btn, 2, 2);
        root.add(lbl2, 0, 4);
        root.add(lbl3, 0, 5);
        root.add(lbl4, 0, 3);
        root.add(lbl5, 0, 6);
        root.add(btn1, 2, 7);

        btn.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setConstraints(btn, 2, 2, 1, 1, HPos.RIGHT, VPos.BOTTOM);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredName = txt.getText().trim();

                if (enteredName.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Name cannot be empty. Please enter your name.");
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
                    //Guest g = new Guest(boo.getGuest_id(), boo.getGuestPassword(), enteredName);

                    boo.setBooking_id(boo.generateBookingId());
                    lbl4.setText("Guest Name: " + boo.getGuest_Name());
                    lbl2.setText("Guest ID: " + boo.getGuest_id());
                    lbl3.setText("Guest Password: " + boo.getGuestPassword());
                    lbl5.setText("Booking ID: " + boo.getBooking_id());

                    currentBooking = boo;

                    System.out.println(currentBooking.booking_id);
                    System.out.println(boo.getBooking_id());

                    btn1.setVisible(true);
                }
            }
        });
        btn1.setOnAction(e -> {
            System.out.println("Button 1 clicked");
            primaryStage.setScene(currentBooking.chooseTrip(primaryStage));
        });

        return scene;
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    public Scene createBookingSuccessful(Stage primaryStage) {

        GridPane trial = new GridPane();
        primaryStage.setTitle("Booking Successful");

        scene2 = new Scene(trial, windowWidth, windowHeight);

        trial.setBackground(new Background(background));

        Button exitButton = new Button("Exit");

        Label successLabel = new Label("YOUR TRIP IS SUCCESSFULLY BOOKED");
        Label infoLabel = new Label("What would you like to do?");
        Label thankYouLabel = new Label("Thank you for choosing Our bus booking system. See you later!");

        successLabel.setTextFill(Color.WHITE);
        successLabel.setTextFill(Color.WHITE);

        infoLabel.setTextFill(Color.WHITE);
        thankYouLabel.setTextFill(Color.WHITE);

        trial.add(successLabel, 0, 0, 2, 1);
        trial.add(thankYouLabel, 0, 3, 2, 1);
        trial.add(infoLabel, 0, 4, 2, 1);

        trial.add(exitButton, 1, 5);

        infoLabel.setTextFill(Color.WHITE);
        thankYouLabel.setTextFill(Color.WHITE);

        exitButton.setOnAction(e -> Platform.exit());

        primaryStage.setScene(scene2);

        return scene2;
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

        String stringTrip = String.format("%s %s %s %s %s %d %.2f %s %s %s",
                book.getFromBusStop(),
                book.getToBusStop(),
                book.getFromDestination(),
                book.getToDestination(),
                book.getLicense_plate(),
                book.getBooking_id(),
                book.getTotal_price(),
                formattedArrivalDateTime,
                formattedDepartureDateTime,
                book.getGuest_Name()
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


            bookingHBox.getChildren().addAll(
                   bookingIdLabel ,guestNameLabel,guestIdLabel,tripIdLabel,ticketsLabel, fromBusStopLabel, toBusStopLabel, departureLabel,
                    arrivalLabel, vehicleIdLabel, priceLabel, editButton, deleteButton
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

    public Scene ExistingBookings(Stage primaryStage) {
        GridPane root1 = new GridPane();
        scene4 = new Scene(root1, windowWidth, windowHeight);

        root1.setAlignment(Pos.CENTER);
        root1.setHgap(10);
        root1.setVgap(10);
        root1.setPadding(new Insets(20));
        
        Label lbl24 = new Label("All ExistingBookings");
        lbl24.setTextFill(Color.WHITE);
        Label lbl25 = new Label("Booking ID:");
        lbl25.setTextFill(Color.WHITE);
        TextField txt1 = new TextField("");

        Button btn8 = new Button("cancel search");
        Button backBtn = new Button("Back");
        Button cancelBtn = new Button("Cancel");
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> primaryStage.close());

        root1.add(exitBtn, 3, 3);
 

        backBtn.setOnAction(e -> {
            Receptionist r = new Receptionist();
            try {
                primaryStage.setScene(r.ManageBookings(primaryStage));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }

            readFromFile();

        });

  txt1.setOnKeyPressed(eh -> {
            searchValue = txt1.getText().trim();
            search(); 
        });

        Button deleteAllBtn = new Button("Delete All");

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
                ExistingBookings(primaryStage);

            }
        });
btn8.setOnAction(eh->{
 bookingsVBox.getChildren().clear();

 ExistingBookings(primaryStage);


});

        root1.add(deleteAllBtn, 3, 4);

        root1.add(lbl24, 0, 0, 2, 1);
        root1.add(lbl25, 1, 1);
       
        root1.add(txt1, 2, 1);
        root1.add(btn8, 3, 1);
        root1.add(backBtn, 0, 3);
        root1.add(cancelBtn, 2, 2);
        ScrollPane scrollPane = new ScrollPane(bookingsVBox);
        cancelBtn.setOnAction(event -> {
            for (Node node : bookingsVBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox bookingHBox = (HBox) node;
                    bookingHBox.setVisible(true);
                }
            }

        });

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
            Label receptionistIdLabel2 = new Label(String.format("%-30s", book.getReceptionist_id()));

            Button btn9 = new Button("Edit Booking");
            btn9.setOnAction(event -> {
                book.edit();
                bookingsVBox.getChildren().clear();
                ExistingBookings(primaryStage);
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

     
        root1.add(scrollPane, 0, 2, 3, 1); // Adjusted row and column indices for ScrollPane

        primaryStage.setTitle("Existing Bookings");
        primaryStage.setScene(scene4);
        root1.setBackground(new Background(background));
        return scene4;
    }

    private HBox findBookingHBox(VBox bookingsVBox, Node sourceNode) {
        for (Node node : bookingsVBox.getChildren()) {
            if (node instanceof HBox) {
                HBox bookingHBox = (HBox) node;
                if (bookingHBox.getChildren().contains(sourceNode)) {
                    return bookingHBox;
                }
            }
        }
        return null;
    }

    public Scene chooseTrip(Stage primaryStage) {
        GridPane rooting = new GridPane();
        rooting.setAlignment(Pos.CENTER);
        rooting.setHgap(10);
        rooting.setVgap(10);
        rooting.setPadding(new Insets(20));
        scene6 = new Scene(rooting, windowWidth, windowHeight);
        primaryStage.setTitle("Choose Trip");
        primaryStage.setScene(scene6);
        rooting.setBackground(new Background(background));

        Label lblChooseTrip = new Label("Choose Your Trip");
        lblChooseTrip.setTextFill(Color.WHITE);

        Label lbl33 = new Label("Book your Trip");
        lbl33.setTextFill(Color.WHITE);
        Label lbl34 = new Label("From :");
        lbl34.setTextFill(Color.WHITE);
        Label lbl35 = new Label("To:");
        lbl35.setTextFill(Color.WHITE);
        Label lbl36 = new Label("Date :");
        lbl36.setTextFill(Color.WHITE);
        Label lbl37 = new Label("Number of tickets :");
        lbl37.setTextFill(Color.WHITE);
        DatePicker datePicker = new DatePicker();
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
        Button btn12 = new Button("View Trips");
        rooting.add(lblChooseTrip, 0, 0, 2, 1);
        rooting.add(lbl33, 0, 1, 2, 1);
        rooting.add(lbl34, 0, 2);
        rooting.add(lbl35, 2, 2);
        rooting.add(from1, 1, 3);
        rooting.add(to1, 3, 3);
        rooting.add(lbl36, 0, 4);
        rooting.add(datePicker, 1, 4);
        rooting.add(lbl37, 0, 5);
        rooting.add(btn12, 4, 6);
        rooting.add(ticketsSpinner, 1, 5);

        ticketsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            numberOfTickets = newValue;
            try {
                if (numberOfTickets < 0) {
                    throw new IllegalArgumentException("The number inserted is below");
                }

          

            } catch (IllegalArgumentException e) {
                System.err.println("Error in payment calculation! Please try again. " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
            }
        });

        VBox fromComboBox = create_destination_ComboBox(5, 1, from1, location2, new ArrayList<>());
        rooting.add(fromComboBox, 1, 2);

        VBox toComboBox = create_destination_ComboBox(5, 1, to1, location1, new ArrayList<>());
        rooting.add(toComboBox, 3, 2);
        Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
        Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
        btn12.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (buttonClickCount == 0) {
                    Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
                    Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
                    setFromDestination(selectedFromDestination != null ? selectedFromDestination : null);
                    setToDestination(selectedToDestination != null ? selectedToDestination : null);

                    setTolocation(location1.getValue());
                    setFromlocation(location2.getValue());
                    setSelectedDate(datePicker.getValue());
                    int numberOfTickets = ticketsSpinner.getValue();
                    setNumberOfTickets(numberOfTickets);
                    if (from1.getValue() == null || to1.getValue() == null || selectedDate == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;
                    }

                    System.out.println("Selected Information:");
                    System.out.println("From Destination: " + from1.getValue());
                    System.out.println("To Destination: " + to1.getValue());
                    System.out.println("Selected Bus Stop (location1): " + location1.getValue());
                    System.out.println("Selected Bus Stop (location2): " + location2.getValue());
                    System.out.println("Selected Date: " + datePicker.getValue());
                    System.out.println("Number of Tickets: " + ticketsSpinner.getValue());
                    primaryStage.setScene(createManageBookings(primaryStage));
                    buttonClickCount++;
                } else {
                    Destination selectedFromDestination = from1.getSelectionModel().getSelectedItem();
                    Destination selectedToDestination = to1.getSelectionModel().getSelectedItem();
                    setFromDestination(selectedFromDestination != null ? selectedFromDestination : null);
                    setToDestination(selectedToDestination != null ? selectedToDestination : null);
                    setTolocation(location1.getValue());
                    setFromlocation(location2.getValue());
                    setSelectedDate(datePicker.getValue());
                    int numberOfTickets = ticketsSpinner.getValue();
                    setNumberOfTickets(numberOfTickets);
                    if (from1.getValue() == null || to1.getValue() == null || selectedDate == null) {
                        // Display an alert if any of the fields is not filled
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                        return;
                    }

                    System.out.println("Selected Information:");
                    System.out.println("From Destination: " + from1.getValue());
                    System.out.println("To Destination: " + to1.getValue());
                    System.out.println("Selected Bus Stop (location1): " + location1.getValue());
                    System.out.println("Selected Bus Stop (location2): " + location2.getValue());
                    System.out.println("Selected Date: " + datePicker.getValue());
                    System.out.println("Number of Tickets: " + ticketsSpinner.getValue());

                    primaryStage.setScene(currentBooking.createManageBookings(primaryStage));

                    buttonClickCount = 0;
                }
            }
        });

        return scene6;
    }

    public static VBox create_destination_ComboBox(int spacing, int par, ComboBox<Destination> to1, ComboBox<String> location1, List<String> get) {
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

        VBox vbox = new VBox(spacing, destinationCBO, busStopsCBO);
        return vbox;
    }

    public Scene createManageBookings(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setBackground(new Background(background));
        scene1 = new Scene(pane, windowWidth, windowHeight);
        VBox contentVBox = new VBox(10);
        Button updateLabelButton = new Button("Available trips");
        Label pressButtonLabel = new Label("Press the button to view trips");
        pressButtonLabel.setTextFill(Color.WHITE);
        pane.add(updateLabelButton, 1, 0);
        pane.add(pressButtonLabel, 0, 0);
        Label dplace = new Label();
        updateLabelButton.setOnAction(e -> {
            updateLabelButton.setVisible(false);
            if (getFromlocation() == null) {
                dplace.setText("From :" + getFromDestination() + " (" + getFromDestination() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
                dplace.setTextFill(Color.WHITE);
                System.out.println("From :" + getFromDestination() + " (" + getFromDestination() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
            } else if (getTolocation() == null) {
                dplace.setText("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getToDestination() + ")");
                dplace.setTextFill(Color.WHITE);
                System.out.println("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getToDestination() + ")");
            } else if (getFromlocation() == null && getTolocation() == null) {
                dplace.setText("From :" + getFromDestination() + " (" + getFromDestination() + ")  -->  " + " To: " + getToDestination() + " (" + getToDestination() + ")");
                dplace.setTextFill(Color.WHITE);
                System.out.println("From :" + getFromDestination() + " (" + getFromDestination() + ")  -->  " + " To: " + getToDestination() + " (" + getToDestination() + ")");
            } else {
                dplace.setText("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
                dplace.setTextFill(Color.WHITE);
                System.out.println("From :" + getFromDestination() + " (" + getFromlocation() + ")  -->  " + " To: " + getToDestination() + " (" + getTolocation() + ")");
            }

            pane.add(dplace, 0, 2);
            fillTripsMap();
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
                                && Objects.equals(trip.getFrom_BusStop(), getFromlocation());
                        //&&Vehicle.AvailibilityMap.get(trip.getTrip_id())>getNo_of_tickets();

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
            HBox tripHBox = new HBox(10);

            Label tripIDLabel = new Label("Trip ID: ");
            Label vehicleIDTitleLabel = new Label("Vehicle ID: ");
            Label fromDestinationTitleLabel = new Label("From Destination: ");
            Label toDestinationLabel = new Label("To Destination: ");
            Label fromBusStopTitleLabel = new Label("From Bus Stop: ");
            Label toBusStopTitleLabel = new Label("To Bus Stop: ");
            Label priceTitleLabel = new Label("Price: ");
            Label departureDateTimeTitleLabel = new Label("Departure Date: ");
            Label arrivalDateTimeTitleLabel = new Label("Arrival Date: ");
            tripHBox.getChildren().addAll(
                    tripIDLabel, vehicleIDTitleLabel, fromDestinationTitleLabel,
                    toDestinationLabel, fromBusStopTitleLabel, toBusStopTitleLabel,
                    priceTitleLabel, departureDateTimeTitleLabel, arrivalDateTimeTitleLabel
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
                    HBox tripHBoxs = new HBox(10);

                    Label tripIDValueLabel = new Label(String.valueOf(trip.getTrip_id()));

                    Label vehicleIDValueLabel = new Label(trip.getVehicle_id());

                    Label fromDestinationValueLabel = new Label(String.valueOf(trip.getFrom_Destination()));

                    Label toDestinationLabel1 = new Label(String.valueOf(trip.getTo_Destination()));

                    Label fromBusStopValueLabel = new Label(trip.getFrom_BusStop());

                    Label toBusStopValueLabel = new Label(trip.getTo_BusStop());

                    Label priceValueLabel = new Label(String.valueOf(trip.getPrice()));

                    Label departureDateTimeValueLabel = new Label(String.valueOf(trip.getDepartureDate()));

                    Label arrivalDateTimeValueLabel = new Label(String.valueOf(trip.getArrivalDateTime()));

                    Button bookbtn = new Button("Choose Trip");
                    Label confirmationLabel = new Label();
                    confirmationLabel.setTextFill(Color.WHITE);
                    Button confirmBookingButton = new Button("Confirm Booking");
                    confirmBookingButton.setTextFill(Color.WHITE);
                    bookbtn.setVisible(true);
                    Button bookSeatsButton = new Button("Book Seats");

                    bookbtn.setOnMouseClicked(eh -> {
                        if (bookbtn.getText().equals("Choose Trip")) {
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
                            Button doneButton = new Button("Done");

                            bookSeatsButton.setOnMouseClicked(event -> {
                                Booking b = this;
                                String confirmationMessage = """
                                 Booking Confirmation
                                 
                                 Trip Details:
                                 Guest Name: """ + b.getGuest_Name() + "\n"
                                        + "Guest ID: " + b.getGuest_id() + "\n"
                                        + "Guest Password: " + b.getGuestPassword() + "\n"
                                        + "Booking ID: " + b.getBooking_id() + "\n"
                                        + "Trip ID: " + trip.getTrip_id() + "\n"
                                        + "From Destination: " + trip.getFrom_Destination() + "\n"
                                        + "Bus Stop:" + trip.getFrom_BusStop() + "\n"
                                        + "Departure Time: " + trip.getDepartureDateTime() + "\n"
                                        + "To Destination: " + trip.getTo_Destination() + "\n"
                                        + "Bus Stop:" + trip.getTo_BusStop() + "\n"
                                        + "Arrival Time: " + trip.getArrivalDateTime() + "\n"
                                        + "Number of tickets: " + getNumberOfTickets() + "\n"
                                        + "Liscene plate: " + trip.getVehicle_id() + "\n"
                                        + "\nTotal Price: $" + Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets());

                                b.setTotal_price(Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets()));
                                b.setArrivalDateTime(trip.getArrivalDateTime());
                                b.setDepartureDateTime(trip.getDepartureDateTime());
                                b.setFromBusStop(trip.getFrom_BusStop());
                                b.setTrip_id(trip.getTrip_id());
                                b.setFromDestination(trip.getFrom_Destination());
                                b.setLicense_plate(trip.getVehicle_id());
                                b.setToBusStop(trip.getTo_BusStop());
                                b.setToDestination(trip.getTo_Destination());

                                confirmationLabel.setText(confirmationMessage);
                                pane.add(confirmBookingButton, 1, 5);
                                bookSeatsButton.setDisable(true);
                                pane.add(confirmationLabel, 0, 5);

                                bookSeatsButton.setDisable(true);
                            });

                        } else {
                            for (Node node : contentVBox.getChildren()) {
                                if (node instanceof HBox) {
                                    node.setVisible(true);
                                }
                            }
                            bookbtn.setText("Choose Trip");

                            contentVBox.getChildren().removeIf(node
                                    -> node instanceof Label && (((Label) node).getText().startsWith("Number of Tickets")
                                    || ((Label) node).getText().startsWith("Total Price"))
                                    || node instanceof Button && ((Button) node).getText().equals("Book Seats"));
                            pane.getChildren().removeAll(confirmationLabel, confirmBookingButton);

                            bookSeatsButton.setDisable(false);
                        }
                    });

                    confirmBookingButton.setOnAction(eh -> {

                        add();

                        primaryStage.setScene(createBookingSuccessful(primaryStage));

                    });

                    tripHBoxs.getChildren().addAll(
                            tripIDValueLabel,
                            vehicleIDValueLabel,
                            fromDestinationValueLabel,
                            toDestinationLabel1,
                            fromBusStopValueLabel,
                            toBusStopValueLabel,
                            priceValueLabel,
                            departureDateTimeValueLabel,
                            arrivalDateTimeValueLabel, bookbtn
                    );

                    contentVBox.getChildren().add(tripHBoxs);
                }
            }

            ScrollPane scrollPane = new ScrollPane(contentVBox);
            scrollPane.setFitToWidth(true);
            System.out.println("TripsMap: " + Trips.TripsMap);
            System.out.println("Adding ScrollPane to pane");

            pane.add(scrollPane, 0, 3);
        });

        Label lbl6 = new Label("Available Trips According to Your Choice:");
        pane.add(lbl6, 0, 1);
        lbl6.setTextFill(Color.WHITE);
        primaryStage.setTitle("Booking trip");
        primaryStage.setScene(scene1);
        Button backButtonu = new Button("Back");
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
                            // Reset the "Choose Trip" button
                            Button chooseTripButton = (Button) hBoxNode;
                            chooseTripButton.setText("Choose Trip");
                            chooseTripButton.setDisable(false);
                        }
                    }
                }
            }

            primaryStage.setScene(chooseTrip(primaryStage));
        });
        pane.add(backButtonu, 2, 0);
        return scene1;
    }

    public static void fillTripsMap() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime departureDate = LocalDateTime.parse("19/01/2024 05:30:00", formatter);

        Trips trip1 = new Trips(1, "ABC123", Destination.CAIRO, Destination.ALEXANDRIA, "Cairo_(Tahrir)", "Alexandria_(Moharam_BK)", 20, departureDate, departureDate);
        Trips trip4 = new Trips(4, "Aggfggf", Destination.CAIRO, Destination.ALEXANDRIA, "Cairo_(Tahrir)", "Alexandria_(Moharam_BK)", 50, departureDate, departureDate);

        Trips trip2 = new Trips(2, "XYZ789", Destination.SHARM_EL_SHIEKH, Destination.HURGHADA, "Sharm El Sheikh (Royssat)", "Hurghada", 30, null, null);
        Trips trip3 = new Trips(3, "DEF456", Destination.NUWEIBA, Destination.CAIRO, "Nuweiba", "Cairo (Giza)", 40, null, null);

        TripsMap.put(trip1.getTrip_id(), trip1);
        TripsMap.put(trip2.getTrip_id(), trip2);
        TripsMap.put(trip3.getTrip_id(), trip3);
        TripsMap.put(trip4.getTrip_id(), trip4);
    }
     public static int findReceptionistWithMostBookings() {
        Map<Integer, Integer> receptionistBookingsCount = new HashMap<>();
        for (Booking booking : Booking.getBook().values()) {
            int receptionistId = booking.getReceptionist_id();
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
        int receptionistId = booking.getReceptionist_id();
        double revenue = booking.gettotal_price();

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
            double revenue = booking.gettotal_price();

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
            total_revenues += booking.gettotal_price();
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
