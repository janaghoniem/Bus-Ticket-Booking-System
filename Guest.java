/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaApplication1;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author sarad
 */
public class Guest extends Users implements Serializable{

    protected int guest_phone;
    private HashMap<Integer, Booking> bookingHistory;
    protected static List<Guest> guests = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Guest() {
        this.bookingHistory = new HashMap<>();
    }

    public Guest(int id, String password, String name, int phoneNo) {
        super(id, password, name);
    }

    public static void manageGuests() {
        boolean exitProgram = false;
        Scanner s = new Scanner(System.in);
        Guest g = new Guest();
        while (!exitProgram) {
            System.out.println("Choose an option:");
            System.out.println("1. View History of Bookings");
            System.out.println("2. Rate Trip");
            System.out.println("3. Exit");
            System.out.println("4. Return to Main Menu");

            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {

                case 1 -> {
                    g.viewBookingHistory();
                }
                case 2 -> {
                    g.rateTrip();
                }

                case 3 -> {
                    g.saveGuestsToBinaryFile();
                    exitProgram = true;
                }
                case 4 -> {
                    // Return to the main menu
                    exitProgram = false;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void saveGuestsToBinaryFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user_data.dat"))) {
            objectOutputStream.writeObject(guests);
            System.out.println("Guest information saved to binary file.");
        } catch (IOException e) {
            System.out.println("Error saving user information to binary file: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadGuestsFromBinaryFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user_data.dat"))) {
            guests = (List<Guest>) objectInputStream.readObject();
            System.out.println("Guest information loaded from binary file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user information from binary file: " + e.getMessage());
        }
    }


    public void rateTrip() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the trip you want to rate: ");
        int tripid = scanner.nextInt();
        scanner.nextLine();
        if (Trips.TripsMap.containsKey(tripid)) {
            Trips trip = Trips.TripsMap.get(tripid);
            System.out.println("Enter your rating of the trip (1 - 5): ");
            int rating;
            while (true) {
                try {
                    rating = scanner.nextInt();
                    if (rating < 1 || rating > 5) {
                        throw new InputMismatchException("Rating should be between 1 - 5");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input. Please enter your rating of the trip (1 - 5): ");
                    scanner.nextLine();
                }
            }
            System.out.println("Rating added Successfully!");
            Guest.addRating(tripid, rating);
        } else {
            System.out.println("Invalid Trip ID: " + tripid);
        }
    }
    
    public static void addRating(int tripId, int rating)
    {
        
        if(Trips.TripsMap.containsKey(tripId))
        {
            Trips.tripsRating.put(tripId, new ArrayList<>());
            Trips.tripsRating.get(tripId).add(rating);
            System.out.println("Rating added to Trip ID: " + tripId);
        }
        else
        {
            System.out.println("Invalid Trip ID: " + tripId);
        }
    }

    public void viewBookingHistory() {
        boolean exists = false;
        System.out.println("Booking history for guest: " + this.getName());
        System.out.println("Booking history: ");
        for (Booking booking : bookingHistory.values()) {
            if (booking.getGuest_Name().equalsIgnoreCase(this.getName())) {
                System.out.println("Booking ID: " + booking.getBooking_id()
                        + " , Trip ID: " + booking.getTrip_id()
                        + ", Booking Time: " + booking.getBooking_time()
                        + ", Number of Tickets: " + booking.getNo_of_tickets()
                        + ", Total Price: " + booking.getTotal_price());
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("No Bookings yet.");
        }

    }

    public void addBookingToHistory(Booking booking) {
        bookingHistory.put(booking.getBooking_id(), booking);
    }
    
    public List<Booking> getBookingHistory(int guestId) {
        List<Booking> history = new ArrayList<>();
        for (Booking booking : bookingHistory.values()) {
            if (booking.getGuest_id() == guestId) {
                history.add(booking);
            }
        }
        return history;
    }

    //Setters and Getters 
    public int getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(int guest_phone) {
        this.guest_phone = guest_phone;
    }

    public static List<Guest> getGuests() {
        return Guest.guests;
    }
    
    //Design 
    public void ratingSystem()
    {
        Stage ratingStage = new Stage();
        GridPane root = new GridPane();
        Label rate = new Label("Rate your Trip");
        
        RadioButton rb1 = new RadioButton("1");
        RadioButton rb2 = new RadioButton("2");
        RadioButton rb3 = new RadioButton("3");
        RadioButton rb4 = new RadioButton("4");
        RadioButton rb5 = new RadioButton("5");
        
        root.add(rb1, 0, 1);
        root.add(rb2,1,1);
        root.add(rb3,2,1);
        root.add(rb4,3,1);
        root.add(rb5,4,1);
        
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.setHgap(10);
        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);
        rb5.setToggleGroup(tg);
        RadioButton rb;
        rb = (RadioButton) tg.getSelectedToggle();
        
        Scene scene = new Scene(root, 300, 200);
        ratingStage.setTitle("Rating Page");
        ratingStage.setScene(scene);
        ratingStage.show();
    }
    
    

    
    void displayBooking()
    {
        for(Booking booking : Booking.book.values())
        {
            System.out.println(booking);
        }
    }

}
