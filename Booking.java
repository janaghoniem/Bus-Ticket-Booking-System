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
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import java.util.stream.Collectors;

import javaapplication13.Trips.Destination;
import static javaapplication13.Trips.TripsMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Booking implements manages<Booking>, Serializable {

    private static final HashMap<Integer, Booking> book = new HashMap<>();

    private transient TextField txtName;
    private transient ComboBox<Trips> tripComboBox;
    private transient Spinner<Integer> ticketsSpinner;
    private transient TableView<Trips> tripTable;
    private transient List<Trips> filteredTrips;
    double windowWidth = 600;
    double windowHeight = 400;
    private Destination selectedDestination;
    //Destination fromDestination;
    //Destination toDestination;
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
    private double total_price;
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
    private LocalDateTime DepartureDateTime;
    String searchText;

    private transient Scene scene;
    private transient Scene scene1;
    private transient Scene scene2;
    private transient Scene scene3;
    private transient Scene scene4;
    private transient Scene scene5;
    private transient Scene scene6;
    private transient Image backgroundImage = new Image("file:///D:/year 2/OOP/picturee.jpg");

    // Create the background for stages
    private transient BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight, false, false, true, true);
    private transient BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize
    );

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
        // Replace this with your actual logic to get the departure date and time
        return DepartureDateTime;
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

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Booking() {
        //   in = new Scanner(System.in);
    }

    //constructor
    public Booking(int booking_id, int guest_id, String guest_Name, String guestPassword, int trip_id, String fromDestination, String fromBusStop, LocalDateTime DepartureDateTime, String toDestination, String toBusStop, LocalDateTime arrivalDateTime, double total_price, String license_plate, int receptionist_id, int no_of_tickets) {
        this.guest_id = guest_id;
        this.guestPassword = guestPassword;
        this.trip_id = trip_id;
        this.guest_Name = guest_Name;
        this.total_price = total_price;
        this.license_plate = license_plate;
        this.receptionist_id = receptionist_id;
        this.booking_id = booking_id;
        this.no_of_tickets = no_of_tickets;
    }

    public String getFromBusStop() {
        return fromBusStop;
    }

    public void setFromBusStop(String fromBusStop) {
        this.fromBusStop = fromBusStop;
    }

    /* public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }*/
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

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
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

    public double gettotal_price() {
        return total_price;
    }

    public void setTicket_price(double ticket_price) {
        this.total_price = ticket_price;
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

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }

    public int getNo_of_tickets() {
        return no_of_tickets;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setNo_of_tickets(int no_of_tickets) {
        this.no_of_tickets = no_of_tickets;
    }

    //methods
    //add
    public static void select_trip_details() throws FileNotFoundException {
        readFromFile();

        // kan feh hena code w shelto 3lshan nesha8al el function bs el main function mawgoda 3la el git hub
    }

    @Override
    public void add() {
        System.out.println("--Add--");
        try {

            if (!book.containsKey(booking_id)) {
                if (false == Vehicle.newBooking(getTrip_id(), getNo_of_tickets())) {
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
                + ", toDestination='" + toDestination + '\''
                + ", toBusStop='" + toBusStop + '\''
                + ", arrivalDateTime=" + arrivalDateTime
                + ", total_price=" + total_price
                + ", license_plate='" + license_plate + '\''
                + ", receptionist_id=" + receptionist_id
                + ", booking_time=" + booking_time
                + ", no_of_tickets=" + no_of_tickets
                + '}';
    }

    //remove
    @Override
    public void remove() {
        System.out.println("--remove--");

        book.remove(booking_id);
        Vehicle.cancelBooking(booking_id, no_of_tickets);
        System.out.println("Your booking has been successfully deleted.");
        String file = "View Bookings";
        saveToFile();
        viewFile();
    }

    public void write() {
        saveToFile();
        viewFile();
    }

    //calculate the most revenue
//    public void calculateAverageTotalRevenue(LocalDateTime MAX, LocalDateTime MIN) {
//        System.out.println("--calculate (Average , Total) Revenue--");
//        System.out.println("Please enter the start date in the format yyyy-MM-dd HH:mm");
//     //   String start = in.nextLine();
//        System.out.println("Please enter the end date in the format yyyy-MM-dd HH:mm");
//       // String end = in.nextLine();
//
//        try {
//           // LocalDateTime start_Time = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            //LocalDateTime end_Time = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            int booking_count = 0;
//            double total_revenues = 0;
//            for (Booking booking : book.values()) {
//                LocalDateTime booking_timing = booking.getBooking_time();
//                if (booking_timing.isAfter(start_Time) && booking_timing.isBefore(end_Time)) {
//                    booking_count++;
//                    total_revenues += booking.gettotal_price();
//                }
//            }
//            if (booking_count > 0) {
//                System.out.println("The number of booked trips over a specific periodof time : " + booking_count);
//                System.out.println("The total revenue over the specific period of time : " + total_revenues);
//                double average = total_revenues / booking_count;
//                System.out.println("The average amount of revenues over a specific period of time : " + average);
//
//            } else {
//                System.out.println("SORRY! no bookings were found at this specific period of time.");
//            }
//        } catch (Exception e) {
//            System.err.println("Invalid date format! Please enter the date in a correct format.");
//        }
//
//    }
    //view reports in the main
    public void viewReports() {
        try {
            System.out.println("--Reports--");
            System.out.println(" Booking details :");
            for (Booking booking : book.values()) {
                System.out.println("Guest Name: " + booking.getGuest_Name());
                System.out.println("Booking id: " + booking.getBooking_id());
                System.out.println("Guest id : " + booking.getGuest_id());
                System.out.println("Trip id: " + booking.getTrip_id());
                Trips selectedTrip = Trips.TripsMap.get(getTrip_id());
                System.out.println("Selected destination: " + selectedTrip.getDestination());
                System.out.println(" vehicle: " + selectedTrip.getVehicle_id());
                System.out.println("The time of the trip :");
                System.out.println("-> Arrival timing:" + selectedTrip.getArrivalDateTime());
                System.out.println("->Departure timing: " + selectedTrip.getDepartureDateTime());
                System.out.println("Total payment: " + booking.gettotal_price());
                System.out.println("Receptionist id:" + booking.getReceptionist_id());
                System.out.println(" ");
            }
        } catch (Exception e) {
            System.err.println("Failed to view the reports! try again later" + e.getMessage());
        }
    }
    //viewing reports in file:
//  public static void readFiles(String file) throws ClassNotFoundException
//  {
//      if(file.isEmpty()){
//              System.out.println("The File is empty and ready to start adding bookings");
//  }
//  else{
//      try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(file))) {
//          book.putAll((HashMap<Integer, Booking>) read.readObject());
//          System.out.println("Old data has been retrieved");
//          
//      }
//      catch(IOException e){
//      }}
//  }

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
                writer.write(booking.toString()); // Assuming 'toString' method provides a string representation of the booking
                writer.newLine(); // Add a new line after each booking
            }
            System.out.println("Booking data successfully saved to file.");
        } catch (IOException e) {
            System.err.println("Failed to save booking data to file! " + e.getMessage());
        }
    }

    public static int generateBookingId() {
        Random random = new Random();

        // Generate a 5-digit ID with the first two digits being 22
        int bookingid = Integer.parseInt("22" + String.format("%03d", random.nextInt(1000)));

        return bookingid;
    }

    public static int calculate_payment(int trip_id, int no_of_tickets) {
        try {
            if (no_of_tickets < 0) {
                throw new IllegalArgumentException("The number inserted is below zero (Negative). Please try to insert a valid value.");
            }

            fillTripsMap();
            Trips trip = Trips.TripsMap.get(trip_id);
//System.out.println("TripsMap: " + Trips.TripsMap);
//System.out.println("trip_id: " + trip_id + ", no_of_tickets: " + no_of_tickets);
            int total_price = (int) (no_of_tickets * trip.getPrice());
            //      System.out.println("The total payment = " + total_price);
//System.out.println("TripsMap: " + Trips.TripsMap);
//System.out.println("trip_id: " + trip_id + ", no_of_tickets: " + no_of_tickets);

            return total_price;
        } catch (IllegalArgumentException e) {
            System.err.println("Error in payment calculation! Please try again: " + e.getMessage());
            return 0; // or handle the error in an appropriate way
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
            return 0; // or handle the error in an appropriate way
        }
    }
  

    @Override
    public void edit() //partially implemented
    {
       
          Alert editBookingAlert1 = new Alert(Alert.AlertType.NONE);
                editBookingAlert1.setTitle("Confirmation");
                editBookingAlert1.setHeaderText("Do You Want To Edit Number of tickets: " + getNo_of_tickets());

                ButtonType yesButton1 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType cancelButton1 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                editBookingAlert1.getButtonTypes().setAll(yesButton1, cancelButton1);

                Optional<ButtonType> result1 = editBookingAlert1.showAndWait();

                if (result1.isPresent() && result1.get() == yesButton1) {
                    // Second Alert
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
                      setNo_of_tickets(editNumberOfTickets);
                        setTotal_price(Booking.calculate_payment(getTrip_id(), editNumberOfTickets));
                        System.out.println("Yes button clicked in the second alert");
                        System.out.println(book);
                        
                       Booking.getBook().put(booking_id, this);     
                          write();
                   
                      
                    } else {
                        System.out.println("Cancel button clicked in the second alert");
                    }
                } else {
                    System.out.println("Cancel button clicked in the first alert");
                }
    }

    @Override
    public void search() {

        String find = getSearchText();

        // Validate if the input is a non-empty integer
        if (find.isEmpty() || !find.matches("\\d+")) {
            Alert invalidInputAlert = new Alert(Alert.AlertType.ERROR);
            invalidInputAlert.setTitle("Invalid Input");
            invalidInputAlert.setHeaderText("Please enter a valid Booking ID (a positive integer).");
            invalidInputAlert.showAndWait();
            return; // Stop further execution
        }

        int bookingIdToSearch = Integer.parseInt(find);

        // Search for the booking ID in the hashmap
        Booking foundBooking = Booking.getBook().get(bookingIdToSearch);

        // Display the appropriate alert based on whether the booking ID was found or not
        Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
        resultAlert.setTitle("Search Result");

        if (foundBooking != null) {
            Parent parentContainer = scene4.getRoot();
            resultAlert.setHeaderText("Booking ID " + bookingIdToSearch + " found.");
            resultAlert.setContentText("Details:\n"
                    + "Guest Name: " + foundBooking.getGuest_Name() + "\n"
                    + "Trip ID: " + foundBooking.getTrip_id() + "\n"
                    + // Add other details as needed
                    "Total Price: $" + foundBooking.getTotal_price());
            VBox bookingsVBox= new VBox();

        for (Node node : parentContainer.getChildrenUnmodifiable()) {
            if (node instanceof HBox) {
                HBox bookingHBox = (HBox) node;
                if (bookingHBox.getChildren().get(0) instanceof Label) {
                    Label label = (Label) bookingHBox.getChildren().get(0);
                    String[] values = label.getText().split(", ");
                    int currentBookingId = Integer.parseInt(values[0]);
                    bookingHBox.setVisible(currentBookingId == bookingIdToSearch);
                }
            }
        }
        } else {
            resultAlert.setHeaderText("Booking ID " + bookingIdToSearch + " not found.");
        }

        resultAlert.showAndWait();
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

        Label lbl1 = new Label("Name:");
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

        // Button alignment
        btn.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setConstraints(btn, 2, 2, 1, 1, HPos.RIGHT, VPos.BOTTOM);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredName = txt.getText().trim();

                // Check if the name is empty
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
                    boo.setBooking_id(boo.generateBookingId());
                    lbl4.setText("Guest Name: " + boo.getGuest_Name());
                    lbl2.setText("Guest ID: " + boo.getGuest_id());
                    lbl3.setText("Guest Password: " + boo.getGuestPassword());
                    lbl5.setText("Booking ID: " + boo.getBooking_id());

                    btn1.setVisible(true);
                }
            }
        });

// Function to validate the name
        // Scene settings
        btn1.setOnAction(e -> {
            System.out.println("Button 1 clicked");
            primaryStage.setScene(chooseTrip(primaryStage));
        });

        // Stage settings
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

    public Scene ExistingBookings(Stage primaryStage) {
        GridPane root1 = new GridPane();
        scene4 = new Scene(root1, windowWidth, windowHeight);

        root1.setAlignment(Pos.CENTER);
        root1.setHgap(10);
        root1.setVgap(10);
        root1.setPadding(new Insets(20));
        VBox bookingsVBox = new VBox(10);
        Label lbl24 = new Label("All ExistingBookings");
        lbl24.setTextFill(Color.WHITE);
        Label lbl25 = new Label("Booking ID:");
        lbl25.setTextFill(Color.WHITE);
        TextField txt1 = new TextField("");

        Button btn8 = new Button("Go");
        Button backBtn = new Button("Back");
        Button cancelBtn = new Button("Cancel");
        ComboBox<String> sortComboBox = new ComboBox<>();
        sortComboBox.getItems().addAll("ID", "Date", "$");
        sortComboBox.setPromptText("1!");
        // Add an event listener to the ComboBox to handle sorting
        sortComboBox.setOnAction(event -> {
            String selectedSortOption = sortComboBox.getValue();

        });
        backBtn.setOnAction(e -> {
            Receptionist r = new Receptionist();
            try {
                primaryStage.setScene(r.ManageBookings(primaryStage));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                select_trip_details();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookingFx.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        root1.add(lbl24, 0, 0, 2, 1); // Move "Search Booking" to the top
        root1.add(lbl25, 1, 1);
        root1.add(sortComboBox, 0, 1);
        root1.add(txt1, 2, 1);
        root1.add(btn8, 3, 1);
        root1.add(backBtn, 0, 3); // Added back button
        root1.add(cancelBtn, 2, 2); // Added cancel button
        // VBox to hold individual booking information
        ScrollPane scrollPane = new ScrollPane(bookingsVBox);

        // Inside your method
        cancelBtn.setOnAction(event -> {
            for (Node node : bookingsVBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox bookingHBox = (HBox) node;
                    bookingHBox.setVisible(true);
                }
            }

        });

        HBox propertyNamesHBox = new HBox(10);

        Label propertyNamesLabel = new Label(
                "Book ID" + "\t"
                + "Guest Name" + "\t"
                + "Guest ID" + "\t"
                + "Trip ID" + "\t"
                + "Tickets" + "\t"
                + "Total Price" + "\t"
                + "From Destination" + "\t" + "\t"
                + "From Bus Stop" + "\t" + "\t"
                + "Departure Date Time" + "\t"
                + "To Destination" + "\t" + "\t" + "\t"
                + "To Bus Stop" + "\t"
                + "Arrival Date Time" + "\t"
                + "License Plate" + "\t"
                + "Receptionist ID"
        );
        propertyNamesHBox.getChildren().add(propertyNamesLabel);
        bookingsVBox.getChildren().add(propertyNamesHBox);
        // Use the class-level book variable directly
        // Map<Integer, Booking> book = Booking.getBook();
        for (Booking book : Booking.getBook().values()) {
            HBox bookingHBox = new HBox(10); // HBox for each booking
            Label propertyValuesLabel = new Label(
                    book.getBooking_id() + "\t"
                    + book.getGuest_Name() + "\t" + "\t"
                    + book.getGuest_id() + "\t"
                    + book.getTrip_id() + "\t" + "\t" + "\t"
                    + book.getNo_of_tickets() + "\t"
                    + book.getTotal_price() + "\t" + "\t" + "\t" + "\t" + "\t"
                    + book.getFromDestination() + "\t" + "\t"
                    + book.getFromBusStop() + "\t" + "\t"
                    + book.getDepartureDateTime() + "\t" + "\t" + "\t"
                    + book.getToDestination() + "\t" + "\t"
                    + book.getToBusStop() + "\t"
                    + book.getArrivalDateTime() + "\t"
                    + book.getLicense_plate() + "\t"
                    + book.getReceptionist_id() + "\t"
            );
            Button btn9 = new Button("Edit Booking");
            Button btn10 = new Button("Cancel Booking");
            btn10.setOnAction(event -> {
                book.remove();
                System.out.println(book);
                // Save the updated book map to the file
                VBox parentVBox = (VBox) bookingHBox.getParent();
                parentVBox.getChildren().remove(bookingHBox);
            });

            btn9.setOnAction(event -> {
                // First Alert
              edit();
                 ExistingBookings( primaryStage);
            });

            bookingHBox.getChildren().addAll(propertyValuesLabel, btn9, btn10);

            bookingsVBox.getChildren().add(bookingHBox);
        }

        btn8.setOnAction(event -> {
            String inputText = txt1.getText().trim();
            setSearchText(inputText);
            search();
        });

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

                // Check if the button is part of this HBox
                if (bookingHBox.getChildren().contains(sourceNode)) {
                    return bookingHBox;
                }
            }
        }
        return null; // HBox not found
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

        // Add other components and functionality for choosing a trip
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
        // Add event handlers and other functionality as needed
        rooting.add(lblChooseTrip, 0, 0, 2, 1);
        rooting.add(lbl33, 0, 1, 2, 1);
        rooting.add(lbl34, 0, 2);
        rooting.add(lbl35, 2, 2);
        rooting.add(from1, 1, 3);  // Position under "from"
        rooting.add(to1, 3, 3);
        rooting.add(lbl36, 0, 4);
        rooting.add(datePicker, 1, 4);
        rooting.add(lbl37, 0, 5);
// Add other components and functionality for choosing a trip
        rooting.add(btn12, 4, 6);
        rooting.add(ticketsSpinner, 1, 5);

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

                    // Continue with the rest of your logic
                    primaryStage.setScene(createManageBookings(primaryStage));

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

                    // Continue with the rest of your logic
                    primaryStage.setScene(createManageBookings(primaryStage));

                    // Reset the counter
                    buttonClickCount = 0;
                }
            }
        });

        return scene6;
    }

    public static VBox create_destination_ComboBox(int spacing, int par, ComboBox<Destination> to1, ComboBox<String> location1, List<String> get) {
        // ComboBox for Destinations
        ObservableList<String> destOptions = FXCollections.observableArrayList();
        for (Destination dest : Destination.values()) {
            destOptions.add(dest.getDisplayName());
        }
        ComboBox<String> destinationCBO = new ComboBox<>(destOptions);

        ComboBox<String> busStopsCBO = new ComboBox<>();
        busStopsCBO.setVisible(false);

        // Update bus stops when the selected destination changes
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
                // Replace spaces with underscores in the selectedBusStop
                selectedBusStop = selectedBusStop.replaceAll("\\s", "_");

                try {
                    // Check if the enum constant exists

                    // Set location1 directly to the selectedBusStop (converted to uppercase and underscores)
                    location1.setValue(selectedBusStop);

                    // Check if location2 is visible and set its value accordingly
                    if (location1.isVisible()) {
                        location1.setValue(selectedBusStop);

                    }
                } catch (IllegalArgumentException e) {
                    // Handle the case where the enum constant doesn't exist
                    System.out.println("Invalid Bus Stop: " + selectedBusStop);
                }
            } else {
                System.out.println("I can't read");
            }
        });

        VBox vbox = new VBox(spacing, destinationCBO, busStopsCBO);
        return vbox;
    }

// Helper method to extract Booking ID from HBox
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

                        // Add null checks for getFrom_Destination() and getTo_Destination()
                        boolean isMatching = Objects.nonNull(trip.getFrom_Destination())
                                && Objects.nonNull(trip.getTo_Destination())
                                && trip.getFrom_Destination().equals(getFromDestination())
                                && trip.getTo_Destination().equals(getToDestination())
                                && Objects.equals(trip.getTo_BusStop(), getTolocation())
                                && Objects.equals(trip.getFrom_BusStop(), getFromlocation());
                        //     &&Vehicle.getNumber_Of_Passengers>book.getNo_of_tickets();

                        if (isMatching && trip.getDepartureDateTime() != null) {
                            LocalDateTime tripDepartureDateTime = trip.getDepartureDateTime();
                            LocalDate selectedDating = getSelectedDate();
                            LocalDateTime selectedDateTime = selectedDate.atStartOfDay();

                            // Compare only the date portion
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

// Create a VBox to hold HBox elements for each trip
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
// Iterate over the filtered trips and create HBox elements
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

// Create labels for Trip ID
                    Label tripIDValueLabel = new Label(String.valueOf(trip.getTrip_id()));

// Create labels for Vehicle ID
                    Label vehicleIDValueLabel = new Label(trip.getVehicle_id());

// Create labels for From Destination
                    Label fromDestinationValueLabel = new Label(String.valueOf(trip.getFrom_Destination()));

// Create labels for To Destination
                    Label toDestinationLabel1 = new Label(String.valueOf(trip.getTo_Destination()));

// Create labels for From Bus Stop
                    Label fromBusStopValueLabel = new Label(trip.getFrom_BusStop());

// Create labels for To Bus Stop
                    Label toBusStopValueLabel = new Label(trip.getTo_BusStop());

// Create labels for Price
                    Label priceValueLabel = new Label(String.valueOf(trip.getPrice()));

// Create labels for Departure Date
                    Label departureDateTimeValueLabel = new Label(String.valueOf(trip.getDepartureDate()));

// Create labels for Arrival Date
                    Label arrivalDateTimeValueLabel = new Label(String.valueOf(trip.getArrivalDateTime()));

                    Button bookbtn = new Button("Choose Trip");
                    // Add event  for booking button if needed
                    Label confirmationLabel = new Label();
                    confirmationLabel.setTextFill(Color.WHITE);
                    Button confirmBookingButton = new Button("Confirm Booking");
                    confirmBookingButton.setTextFill(Color.WHITE);
                    bookbtn.setVisible(true);
                    Button bookSeatsButton = new Button("Book Seats");

                    bookbtn.setOnMouseClicked(eh -> {
                        if (bookbtn.getText().equals("Choose Trip")) {
                            // Disable other buttons or hide other HBoxes
                            for (Node node : contentVBox.getChildren()) {
                                if (node instanceof HBox && node != tripHBoxs) {
                                    // Hide other HBoxes
                                    node.setVisible(false);
                                }
                            }
                            // Enable/Disable the clicked button
                            bookbtn.setText("Remove");

                            // Display Number of Tickets Label
                            Label numTicketsLabel = new Label("Number of Tickets: 0");
                            numTicketsLabel.setText("Number of Tickets: " + getNumberOfTickets());

                            contentVBox.getChildren().add(numTicketsLabel);

                            // Display Total Price Label
                            Label totalPriceLabel = new Label("Total Price: $0");

                            totalPriceLabel.setText("Total Price: $" + Booking.calculate_payment(trip.getTrip_id(), getNumberOfTickets()));
                            contentVBox.getChildren().add(totalPriceLabel);

                            // Display Book Seats Button
                            bookSeatsButton.setTextFill(Color.BLUE);
                            contentVBox.getChildren().add(bookSeatsButton);
                            Button doneButton = new Button("Done");

                            // Set event handler for Book Seats Button if needed
                            bookSeatsButton.setOnMouseClicked(event -> {
                                // Collect all information about the trip and user
                                // For example, collectTripInformation() and collectUserInfo()
                                Booking b = new Booking();
                                // Create a confirmation message
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
                                b.setNo_of_tickets(getNumberOfTickets());

                                // Include other user details as needed
                                // Create a label to display the confirmation message
                                confirmationLabel.setText(confirmationMessage);
                                pane.add(confirmBookingButton, 1, 5);
                                bookSeatsButton.setDisable(true);
                                // Add the label to the existing GridPane or another suitable container
                                pane.add(confirmationLabel, 0, 5);

                                // Optionally, you can clear or hide other unnecessary components
                                // ...
                                // You may want to disable the bookSeatsButton after confirmation
                                bookSeatsButton.setDisable(true);
                            });

                        } else {
                            // Show all HBoxes
                            for (Node node : contentVBox.getChildren()) {
                                if (node instanceof HBox) {
                                    // Show other HBoxes
                                    node.setVisible(true);
                                }
                            }
                            // Enable/Disable the clicked button
                            bookbtn.setText("Choose Trip");

                            // Remove Number of Tickets Label, Total Price Label, and Book Seats Button
                            contentVBox.getChildren().removeIf(node
                                    -> node instanceof Label && (((Label) node).getText().startsWith("Number of Tickets")
                                    || ((Label) node).getText().startsWith("Total Price"))
                                    || node instanceof Button && ((Button) node).getText().equals("Book Seats"));
                            pane.getChildren().removeAll(confirmationLabel, confirmBookingButton);

                            // Re-enable the bookSeatsButton
                            bookSeatsButton.setDisable(false);
                        }
                    });

                    confirmBookingButton.setOnAction(eh -> {

                        //return 
                        add();

                        primaryStage.setScene(createBookingSuccessful(primaryStage));

                    });

                    // Add components to the tripHBox
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

                    // Add the tripHBox to the contentVBox
                    contentVBox.getChildren().add(tripHBoxs);
                }
            }

// Create a ScrollPane and set the content
            ScrollPane scrollPane = new ScrollPane(contentVBox);
            scrollPane.setFitToWidth(true);
            System.out.println("TripsMap: " + Trips.TripsMap);
            System.out.println("Adding ScrollPane to pane");

// Set the style for the VBox
            pane.add(scrollPane, 0, 3);
        });

        Label lbl6 = new Label("Available Trips According to Your Choice:");
        pane.add(lbl6, 0, 1); // Change the grid position to (0, 1)
        lbl6.setTextFill(Color.WHITE);
        primaryStage.setTitle("Booking trip");
        primaryStage.setScene(scene1);
        Button backButtonu = new Button("Back");
        backButtonu.setOnAction(event -> {
            // Reset or clear any data or components in stage 1

            // Clear the labels and reset the button text
            dplace.setText("");
            updateLabelButton.setVisible(true);

            // Clear any previous filtered trips
            filteredTrips.clear();

            // Remove the contentVBox
            pane.getChildren().remove(contentVBox);

            // Reset the "Choose Trip" buttons and other components as needed
            for (Node node : contentVBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox tripHBoxs = (HBox) node;

                    // Reset other components in the HBox
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

            // Show stage 6
            primaryStage.setScene(chooseTrip(primaryStage));
        });
        pane.add(backButtonu, 2, 0);
        return scene1;
    }

    public static void fillTripsMap() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Parse the date string with a default time of midnight
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

}
