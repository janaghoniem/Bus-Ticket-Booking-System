/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sarad
 */
public class Guest extends Users {

    private static Iterator<Users> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int guest_phone;
    static List<Guest> guests= new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public Guest() {
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
                
                case 1: 
                {
                    g.viewBookingHistory();
                    break;
                }
                case 2:
                {
                    g.rateTrip();
                    break;
                }
                  
                case 3: {
                    g.saveGuestsToFile();
                    //receptionist.displayReceptionists();
                    exitProgram = true;
                    break;
                }
                case 4: {
                    // Return to the main menu
                    exitProgram = false;
                    break;
                }
                default: System.out.println("Invalid choice. Please try again.");
            }
    }
    
}
    
    private void saveGuestsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("guest_information.txt"))) {
            for (Guest currentGuest : guests) {
                writer.write("ID: " + currentGuest.getID() + ", Name: " + currentGuest.getName());
               
                writer.newLine();
            }
            System.out.println("Guest information saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving user information to file: " + e.getMessage());
        }
    }
    
    public Guest(int id, String password, String name, int phoneNo) {
        super(id, password,name);
    }
    
   
    public void rateTrip(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ID of the trip you want to rate: ");
        int tempid = s.nextInt();
        s.nextLine();
        if(Trips.TripsMap.containsKey(tempid))
        {
            Trips temp = Trips.TripsMap.get(tempid);
            System.out.println("Enter your rating of the trip (1 - 5): ");
            while(true)
            {
                try
                {
                    int rate = s.nextInt();
                    break;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Invalid Input. Please enter your rating of the trip (1 - 5): ");
                }
            }
//            temp.addRating(rate);
        }
    }
    
    public void viewBookingHistory(){
        boolean exists = false;
        System.out.println("Booking history for guest: " + this.getName());
        System.out.println("Booking history: ");
        for(Booking b: Booking.getBook().values())
        {
            if(b.getGuest_Name().equalsIgnoreCase(this.getName()))
            {
                System.out.println("Booking ID: "+ b.getBooking_id() + " , Trip ID: " + b.getTrip_id() + ", Booking Time: " + b.getBooking_time() + ", Number of Tickets: " + b.getNo_of_tickets() + ", Total Price: " + b.getTotal_price());
                exists = true;
                break;
            }
        }
        if(!exists)
        {
            System.out.println("No Bookings yet.");
        }
        
    }


    public int getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(int guest_phone) {
        this.guest_phone = guest_phone;
    }

    public static List<Guest> getGuests() {
        return guests;
    }
    
    
}