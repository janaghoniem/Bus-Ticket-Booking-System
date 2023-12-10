

/**
 *
 * @author Nouran
 */

package project.trial;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Nouran
 */
public class Booking implements manages<Booking>{

  private static final HashMap<Integer, Booking> book = new HashMap<>();
  private int booking_id=0;
  private int guest_id;
  private String guestPassword;
  private int trip_id;
  private String guest_Name;
  private double total_price;
  private String license_plate;
  private int receptionist_id;
  private LocalDateTime booking_time;
  private int no_of_tickets;
  Scanner in = new Scanner(System.in);

    public Booking() {
    }
  
  //constructor

    public Booking(int guest_id, String guestPassword, int trip_id, String guest_Name, double total_price, String license_plate, int receptionist_id, LocalDateTime booking_time, int no_of_tickets) {
        this.guest_id = guest_id;
        this.guestPassword = guestPassword;
        this.trip_id = trip_id;
        this.guest_Name = guest_Name;
        this.total_price = total_price;
        this.license_plate = license_plate;
        this.receptionist_id = receptionist_id;
        this.booking_time = booking_time;
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

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
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

    public void setNo_of_tickets(int no_of_tickets) {
        this.no_of_tickets = no_of_tickets;
    }   

  
    

  //methods
 
    //add
    public  Booking select_trip_details()
    {
        try{
        System.out.println("--Welcome to bus transportation--");
        System.out.println("Please enter your Name :");
        guest_Name=in.nextLine();
        guestPassword = Admin.generateGuestPassword();
        guest_id = Admin.generateGuestId();
        System.out.println("Your id is " +guest_id);
        System.out.println("Your password is "+guestPassword);
        System.out.println("Entered name is " + guest_Name);
        System.out.println("Your booking id is "+booking_id);
        System.out.println("Here are the available trips :");
        Trips.ViewTrips();
        System.out.println("Please select your destination by writting the id in numeric value : ");
        while(true)       
        {
            try
            {
                trip_id=in.nextInt();
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid input. Please select your destination by writting the id in numeric value : ");
            }
        }

        if (!Trips.TripsMap.containsKey(trip_id)) {
            throw new RuntimeException("Invalid Trip ID: " + trip_id);
        }
        Trips selectedTrip = Trips.TripsMap.get(trip_id);
        System.out.println("Selected destination: " + selectedTrip.getDestination());
        System.out.println("Available vehicle: " + selectedTrip.getVehicle_id());
        System.out.println("The time of the trip :");
        System.out.println("-> Arrival timing:" + selectedTrip.getArrivalDateTime());
        System.out.println("->Departure timing: " + selectedTrip.getDepartureDateTime());
        System.out.println("How many tickets do you want : " );
        no_of_tickets=in.nextInt();
        calculate_payment( trip_id, no_of_tickets);
       //receptionist id generation is missing
        System.out.println("Receptionist id : "+ Users.getCurrentUser().ID);
       return new Booking(guest_id, guestPassword, trip_id, guest_Name, total_price, license_plate, receptionist_id, booking_time, no_of_tickets);
        }
        catch(NumberFormatException e){
        throw new InputMismatchException("Invalid input. Please enter a valid numeric value.");
        
        }
         
  
    }
    
  @Override
    public void add(){
        System.out.println("--Add--");
        try{
            Booking selectedBooking = select_trip_details();
            booking_id++;
            if(!book.containsKey(booking_id)){
                if(false == Vehicle.newBooking(selectedBooking.trip_id, selectedBooking.no_of_tickets))
                {
                    System.out.println("Sorry cant book! Try to look for another available trip");
                }
                else {
                    book.put( booking_id,selectedBooking);
                    System.out.println("Your booking has been successfully added! Your booking id: "+booking_id);
                   
                }
            }
            
            else{
                throw new RuntimeException("This booking already exists with the id :"+booking_id);
            }
        }
   
        catch(IllegalStateException  e){
            System.err.println("Error in adding booking "+e.getMessage());
        }
    }
    
    //remove
  @Override
    public void remove(){
        System.out.println("--remove--");
        System.out.println("please enter the booking id to remove");
        booking_id=in.nextInt();
        if (book.containsKey(booking_id)) {
        book.remove( booking_id);
        Vehicle.cancelBooking(booking_id, no_of_tickets);
        System.out.println("Your booking has been successfully deleted.");
    } else {
            System.out.println("Booking with ID " + booking_id + " not found. No changes were made.");
    }
        }
        

    //calculate the most revenue
    public void calculateAverageTotalRevenue(LocalDateTime MAX, LocalDateTime MIN) {
        System.out.println("--calculate (Average , Total) Revenue--");
        System.out.println("Please enter the start date in the format yyyy-MM-dd HH:mm");
        String start=in.nextLine();
        System.out.println("Please enter the end date in the format yyyy-MM-dd HH:mm");
        String end=in.nextLine();
    
        try{
            LocalDateTime start_Time = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime end_Time = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
             int booking_count=0; 
        double total_revenues=0;
        for (Booking booking : book.values()) {
        LocalDateTime booking_timing= booking.getBooking_time();
    if(booking_timing.isAfter(start_Time)&&booking_timing.isBefore(end_Time)){
        booking_count++;
        total_revenues+=booking.gettotal_price();
        }
        }
        if(booking_count>0)
        {
             System.out.println("The number of booked trips over a specific periodof time : " +booking_count);
             System.out.println("The total revenue over the specific period of time : " +total_revenues);
             double average= total_revenues/booking_count;
             System.out.println("The average amount of revenues over a specific period of time : " +average);
        
        
        }
        else{
            System.out.println("SORRY! no bookings were found at this specific period of time.");
        }}
        catch(Exception e){
            System.err.println("Invalid date format! Please enter the date in a correct format.");
        }
     
    }

  
    
    
    //view reports in the main
  public void viewReports() {
      try{
          System.out.println("--Reports--");
        System.out.println(" Booking details :" );
        for (Booking booking : book.values()){
     System.out.println("Guest Name: " + booking.getGuest_Name());
        System.out.println("Booking id: " + booking.getBooking_id());
        System.out.println("Guest id : "+booking.getGuest_id());
        System.out.println("Trip id: " + booking.getTrip_id());
         Trips selectedTrip = Trips.TripsMap.get(getTrip_id());
       System.out.println("Selected destination: " + selectedTrip.getDestination());
        System.out.println(" vehicle: " + selectedTrip.getVehicle_id());
        System.out.println("The time of the trip :");
        System.out.println("-> Arrival timing:" + selectedTrip.getArrivalDateTime());
        System.out.println("->Departure timing: " + selectedTrip.getDepartureDateTime());
        System.out.println("Total payment: " + booking.gettotal_price());
        System.out.println("Receptionist id:" +booking.getReceptionist_id());
        System.out.println(" ");
        }
      }
      catch(Exception e)
      {
          System.err.println("Failed to view the reports! try again later"+e.getMessage());
      }
  }
  //viewing reports in file:
    public void viewFile(String file)  {
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
          for (Booking booking : book.values()){
              writer.write("--View Reports--");
              
                 writer.write("Guest Name: " + booking.getGuest_Name() + "\n");
                  writer.write("Booking id: " + booking.getBooking_id() + "\n");
                   writer.write("Booking id: " + booking.getGuest_id() + "\n");
                    writer.write("Trip id: " + booking.getTrip_id() + "\n");
                    Trips selectedTrip = Trips.TripsMap.get(getTrip_id());
                writer.write( "Selected destination: " + selectedTrip.getDestination()+ "\n");
                writer.write(" vehicle: " + selectedTrip.getVehicle_id()+ "\n");
                writer.write("The time of the trip :"+"/n");
                        writer.write("-> Arrival timing:" + selectedTrip.getArrivalDateTime()+"/n");
                                writer.write("->Departure timing: " + selectedTrip.getDepartureDateTime()+"/n");
                  writer.write("Ticket payment: " + booking.gettotal_price() + "\n");
                  writer.write("Receptionist id: " + booking.getReceptionist_id() + "\n");
                writer.write("\n"); 
          }
        
            System.out.println("The booking  details has been successfully saved into a file");
     
      }
      catch(IOException e)
      {
          System.err.println("Failed to view the reports! try again later"+e.getMessage());
      }
      }
      
    //  INCOMPLETE FUNCTIONS (DEPENDS ON OTHER CLASSES)
    
     //calculate payment 
    public void calculate_payment(int trip_id,int no_of_tickets)
    {
        try{
        if(no_of_tickets<0){
            throw new IllegalArgumentException("The number inserted in below zero (Negative) Please try to insert a valid value");
            }
       
         if (!Trips.TripsMap.containsKey(trip_id)) {
            throw new RuntimeException("Invalid Trip ID: " + this.getTrip_id());
        }
          
        Trips trip = Trips.TripsMap.get(trip_id);
  
      total_price=no_of_tickets*trip.getPrice();
        System.out.println("the total payment = "+total_price);
       
        }
        catch(IllegalArgumentException e){
            System.err.println("Error in payment calculation! please try again "+e.getMessage());
        
                }
        catch (RuntimeException e) {
        System.err.println("Error: " + e.getMessage());
      
    }
    }
    
  @Override
 public void edit() //partially implemented
  {
      try{
         
          System.out.println("Please enter your booking id");
          booking_id=in.nextInt();
          if(book.containsKey(booking_id)){
          Booking editbooking = book.get(booking_id);
              System.out.println("Which section would you like to edit");
             
              System.out.println("1. Trip id (trip destination)");
              System.out.println("2.Change number of tickets");
          int choice=in.nextInt();
          switch(choice)
          {
            case 1: {
                System.out.println("Here are the available trips :");
                Trips.ViewTrips();
                System.out.println("Please enter the new trip id to change your destination");
                trip_id = in.nextInt();
                if (Trips.TripsMap.containsKey(trip_id)) {
                    Trips newTrip = Trips.TripsMap.get(trip_id);
                    if(book.containsKey(booking_id)){
                        if(false == Vehicle.newBooking(editbooking.trip_id, editbooking.no_of_tickets))
                        {
                            System.out.println("Sorry cant book! try to look for another available trip");
                        }
                        else{
                            editbooking.setTrip_id(trip_id);
                            System.out.println("Your booking details have been updated succesffully");
                        }
                    }
                }
                else{
                    System.out.println("Invalid Trip Id: " + trip_id);
                }   
            }
            
            case 2:{
                
                 System.out.println("How many tickets do you want ");
                 no_of_tickets=in.nextInt();
                 if(no_of_tickets<0){
                    throw new IllegalArgumentException("The number inserted in below zero (Negative) Please try to insert a valid value");
                }
                else{     
                    if(false == Vehicle.newBooking(editbooking.trip_id, editbooking.no_of_tickets))
                    {
                        System.out.println("Sorry cant book! try to look for another available trip");
                    }
                    
                    else{
                    editbooking.setNo_of_tickets(no_of_tickets);
                    }
                     
                }
             
            }           
            default: System.out.println("Invalid choice please try again later");
          }
        }
          
        else{
              System.out.println("This booking id does not exist"+booking_id);
        }
              //
              System.out.println("Your booking details have been updates successfully!");
      }
      catch(InputMismatchException e){
      System.err.println("Error while trying to edit your booking "+e.getMessage());
      }
      
      catch (RuntimeException e) {
        System.err.println("Error: " + e.getMessage());
    }
      
 }

  @Override
  public void search(){
      try {
      System.out.println("Enter booking id : ");
      booking_id=in.nextInt();
      if(book.containsKey(booking_id))
      {
           boolean found = false;
          for(Booking booking : book.values()){
               if (booking.getBooking_id() == booking_id) {
                    found = true;
                    System.out.println("--Booking is found-- ");
                     System.out.println(" Booking details :" );
    
     System.out.println("Guest Name: " + booking.getGuest_Name());
        System.out.println("Booking id: " + booking.getBooking_id());
        System.out.println("Guest id : "+booking.getGuest_id());
        System.out.println("Trip id: " + booking.getTrip_id());
         Trips selectedTrip = Trips.TripsMap.get(getTrip_id());
       System.out.println("Selected destination: " + selectedTrip.getDestination());
        System.out.println(" vehicle: " + selectedTrip.getVehicle_id());
        System.out.println("The time of the trip :");
        System.out.println("-> Arrival timing:" + selectedTrip.getArrivalDateTime());
        System.out.println("->Departure timing: " + selectedTrip.getDepartureDateTime());
        System.out.println("Total payment: " + booking.gettotal_price());
        System.out.println("Receptionist id:" +booking.getReceptionist_id());
        System.out.println(" ");
        break;
          }
          }
      }
      else{
          System.out.println("Id : "+booking_id+" not found");
      }
      }
      catch (InputMismatchException e) {
        System.err.println("Invalid input. Please enter a valid numeric value.");
        in.nextLine(); // Consume the invalid input to prevent an infinite loop
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
    }

}

 
}