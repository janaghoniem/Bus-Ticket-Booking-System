/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
/**
 *
 * @author jana
 */
public class Vehicle implements manages<Vehicle> {
    
    public enum vehicleCategory
    {
        MEGABUS, 
        BUS, 
        MINIBUS,
        MICROBUS
    }
    
    transient Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Vehicle> VehicleList = new HashMap<>(50);
    private static HashMap<Integer, Integer> AvailibilityMap = new HashMap<>(50);
    private static final File vehicleFile = new File("vehicleFile.txt");
    private String License_plate;
    private vehicleCategory Category;
    private int Number_of_seats;
    private double ticket_price;
    private int num_of_available_seats;
    private int booking;
    private String BusDriver_name;


    public Vehicle() {
    }

    public Vehicle(String License_plate, vehicleCategory Category, String BusDriver_name, int Number_of_seats, double ticket_price) {
        this.License_plate = License_plate;
        this.Category = Category;
        this.Number_of_seats = Number_of_seats;
        this.ticket_price = ticket_price;
        this.BusDriver_name = BusDriver_name;
    }

    public String getLicense_plate() {
        return License_plate;
    }

    public vehicleCategory getCategory() {
        return Category;
    }

    public int getNumber_of_seats() {
        return Number_of_seats;
    }

    public double getTicket_price() {
        return ticket_price;
    }
    
    public int getBooking() {
        return booking;
    }

    public static HashMap<String, Vehicle> getVehicleList() {
        return VehicleList;
    }

    public String getBusDriver_name() {
        return BusDriver_name;
    }

    public void setLicense_plate(String License_plate) {
        this.License_plate = License_plate;
    }

    public void setCategory(vehicleCategory Category) {
        this.Category = Category;
    }

    public void setNumber_of_seats(int Number_of_seats) {
        this.Number_of_seats = Number_of_seats;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setBusDriver_name(String BusDriver_name) {
        this.BusDriver_name = BusDriver_name;
    }
    
    
    @Override
    public void add()
    {
        String again = "yes";
        boolean continueprocess = true;
        while(again.equalsIgnoreCase("yes"))
        {
            Vehicle newVehicle = new Vehicle();
            while(true)
            {
                System.out.print("Enter your vehicle's License plate: ");
                String tempLP = scanner.next(); 
                if(!isUnique(tempLP))
                {
                    System.out.println("Vehicle already exists. Would you like to try again? ");
                    String response = scanner.next();
                    if(!response.equalsIgnoreCase("yes"))
                    {
                        continueprocess = false;
                        break;
                    }
                }
                else
                {
                    newVehicle.setLicense_plate(tempLP);
                    break;
                }
            }
            if(!continueprocess)
            {
                break;
            }
            System.out.print("Enter your vehicle's category (MEGABUS, BUS, MINIBUS, MICROBUS): ");      //NEED WAY TO LIMIT CATEGORIES TO THREE: MEGABUS, BUS, MINIBUS (MAYBE ENUM LIKE ROAA)
            while(true)
            {
                try
                {
                    newVehicle.setCategory(vehicleCategory.valueOf(scanner.next().toUpperCase()));
                    break;
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("Invalid Category. Please choose from the following categories (MEGABUS, BUS, MINIBUS, MICROBUS): ");
                }
            }
            
            scanner.nextLine();            
            System.out.print("Enter your vehicle's bus driver's name: ");
            newVehicle.setBusDriver_name(scanner.nextLine());            
            while(true)
            {
                try
                {
                    System.out.print("Enter your vehicle's Number_of_seats: ");
                    newVehicle.setNumber_of_seats(scanner.nextInt());
                    break;
                }

                catch(InputMismatchException e)
                {
                    System.out.print("Invalid input. Please enter an integer value: ");
                    scanner.next();
                }         
            }
            while(true)
            {
                try
                {
                    System.out.print("Enter your vehicle's ticket price: ");
                    newVehicle.setTicket_price(scanner.nextDouble()); 
                    break;
                }

                catch(InputMismatchException e)
                {
                    System.out.print("Invalid input. Please enter a numerical value: ");
                    scanner.next();
                }   
            }
        
            Vehicle.VehicleList.put(newVehicle.License_plate, newVehicle);
            System.out.println("Vehicle added successfully.");
            System.out.println("Would you like to add another vehicle?");
            again = scanner.next();
            while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to continue adding vehicles or 'no' if you would like to stop: ");
                again = scanner.next();
            }
        }
    }
    
    @Override
    public void remove()
    {
        String again = "yes";
        if(VehicleList.isEmpty())
        {
            System.out.println("No vehicles exist. Would you like to start adding vehicles?");
            String addresponse = scanner.next();
            while(!addresponse.equalsIgnoreCase("yes") && !addresponse.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to start adding vehicles or 'no' if you would like to stop: ");
                addresponse = scanner.next();
            }            
        
            if(addresponse.equalsIgnoreCase("yes"))
            {
                this.add();
                again = "no";
            }
        }
           
        while(again.equalsIgnoreCase("yes") && !VehicleList.isEmpty())
        {
            System.out.print("Enter the license plate of the vehicle you want to remove: ");
            String s = scanner.next();  
            if(VehicleList.containsKey(s))
            {
                VehicleList.remove(s);
                System.out.println("Vehicle removed successfully.");
                System.out.println("Would you like to remove another vehicle?");
                again = scanner.next();      
                while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
                {
                    System.out.println("Invalid input. Please enter 'yes' if you would like to continue removing vehicles or 'no' if you would like to stop: ");
                    again = scanner.next();
                }            
            }
            else
            {
                System.out.println("Vehicle doesn't exist. Would you like to try again?");
                again = scanner.next();
                if(!again.equalsIgnoreCase("yes"))
                {
                    break;
                }
            }
        }
    }
    
    @Override
    public void edit()
    {
        String again = "yes";
        String whichVehicle;
        if(VehicleList.isEmpty())
        {
            System.out.println("No vehicles exist. Would you like to start adding vehicles?");
            String addresponse = scanner.next();
            while(!addresponse.equalsIgnoreCase("yes") && !addresponse.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to start adding vehicles or 'no' if you would like to stop: ");
                addresponse = scanner.next();
            }            
        
            if(addresponse.equalsIgnoreCase("yes"))
            {
                this.add();
                again = "no";
            }
        }
        
        while(again.equalsIgnoreCase("yes") && !VehicleList.isEmpty())
           {
                System.out.println("Enter the license plate of the vehicle you want to edit: ");
                whichVehicle = scanner.next();
                if(VehicleList.containsKey(whichVehicle))
                {
                    Vehicle editedVehicle = VehicleList.get(whichVehicle);
                    System.out.println("Which field would you like to edit: ");
                    System.out.println("1. License Plate ");
                    System.out.println("2. Vehicle Category");
                    System.out.println("3. Number of Seats");
                    System.out.println("4. Ticket Prices");
                    System.out.println("5. Bus Driver");
                        
                    switch(scanner.nextInt())
                        {
                            case 1: 
                            {
                                System.out.print("Enter your vehicle's new liscense plate: ");
                                editedVehicle.setLicense_plate(scanner.next());
                                System.out.println("Vehicle information edited successfully");                               
                                break;
                            } 
                            case 2:  //NEED WAY TO LIMIT CATEGORIES TO THREE: MEGABUS, BUS, MINIBUS (MAYBE ENUM LIKE ROAA)
                            {
                                System.out.print("Enter your vehicle's new category: ");
                                while(true)
                                {
                                    try
                                    {
                                        editedVehicle.setCategory(vehicleCategory.valueOf(scanner.next().toUpperCase()));
                                        System.out.println("Vehicle information edited successfully");                               
                                        break;
                                    }
                                    catch(IllegalArgumentException e)
                                    {
                                        System.out.println("Invalid Category. Please choose from the following categories (MEGABUS, BUS, MINIBUS, MICROBUS): ");
                                    }
                                }
                                break;
                            }
                            
                            case 3: 
                            {
                                System.out.println("Enter your vehicle's number of seats: ");
                                while(true)
                                {
                                    try
                                    {
                                        editedVehicle.setNumber_of_seats(scanner.nextInt());
                                        System.out.println("Vehicle information edited successfully");                                       
                                        break;
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        System.out.print("Invalid input. Please enter an integer value: ");
                                        scanner.next();
                                    }         
                                }
                                break;
                            }
                                                    
                            case 4: 
                            {
                                System.out.println("Enter your vehicle's new ticket price: ");
                                while(true)
                                {
                                    try
                                    {
                                        editedVehicle.setTicket_price(scanner.nextInt());
                                        System.out.println("Vehicle information edited successfully");                                       
                                        break;
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        System.out.print("Invalid input. Please enter a numerical value: ");
                                        scanner.next();
                                    }         
                                }                                
                                break;
                            }
                            
                            case 5:        //IF CONDITION CHECKS IF DRIVER IS AN EMPLOYEE WITHIN THE COMPANY
                            {
                                System.out.println("Enter your vehicle's new bus driver: ");
                                editedVehicle.setBusDriver_name(scanner.nextLine());
                                System.out.println("Vehicle information edited successfully");  
                                break;
                            }
                        }
                }
                else
                {
                    System.out.println("License plate not found. Please enter a valid License plate.");
                    continue;
                }
                
                System.out.println("Would you like to continue editing your vehicle's information? ");
                again = scanner.next();
                while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
                {
                    System.out.println("Invalid input. Please enter 'yes' if you would like to continue editing vehicles or 'no' if you would like to stop: ");
                    again = scanner.next();
                }            
           }
    }
    
    @Override
    public void search()        
    {
        boolean isFound = false;
        boolean input = false;
        int s = 0;
        String again = "yes";
        String tempstring;
        int tempint;
        if(VehicleList.isEmpty())
        {
            System.out.println("No vehicles exist. Would you like to start adding vehicles?");
            String addresponse = scanner.next();
            while(!addresponse.equalsIgnoreCase("yes") && !addresponse.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to start adding vehicles or 'no' if you would like to stop: ");
                addresponse = scanner.next();
            }            
        
            if(addresponse.equalsIgnoreCase("yes"))
            {
                this.add();
                again = "no";
            }
        }
        while(again.equalsIgnoreCase("yes") && !VehicleList.isEmpty())
        {
          while(!input)
          {
            try
            {
                System.out.println("What field do wish to search by: ");
                System.out.println("1. License plate ");
                System.out.println("2. Vehicle category");
                System.out.println("3. Number of seats");
                System.out.println("4. Ticket prices");
                System.out.println("5. Bus Driver");
                s = scanner.nextInt();
                input = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid input. Please try again...");
            } 
          }  
      
        switch (s)
        {
          case 1: 
          {
              while(isFound == false)
              {
                  System.out.print("Enter the license plate you want to search by: ");
                  tempstring = scanner.next();
                  if(VehicleList.containsKey(tempstring))
                  {
                    isFound = true;
                    printResults(VehicleList.get(tempstring));
                  }
              }
              break;
            }
          
            case 2:
            {
              while(isFound == false)
              {
                  System.out.print("Enter the vehicle category you want to search by: ");
                  tempstring = scanner.next();
                  for(Vehicle v: VehicleList.values())
                  {
                      if((v.Category.name()).equalsIgnoreCase(tempstring))
                      {
                        isFound = true;
                        printResults(v);
                      }     
                  }
              }
              break;      
            }
            
            case 3:
            {
              while(isFound == false)
              {
                  System.out.print("Enter the number of seats you want to search by: ");
                  tempint = scanner.nextInt();
                  for(Vehicle v: VehicleList.values())
                  {
                      if((v.Number_of_seats) == tempint)
                      {
                        isFound = true;
                        printResults(v);
                      }     
                  }
              }
              break;      
            }
          
            case 4:
            {
              while(isFound == false)
              {
                  System.out.print("Enter the ticket price you want to search by: ");
                  tempint = scanner.nextInt();
                  for(Vehicle v: VehicleList.values())
                  {
                      if(v.ticket_price == tempint)
                      {
                        isFound = true;
                        printResults(v);
                      }     
                  }
              }
              break;      
            }
            
            case 5:
            {
                while(isFound == false)
                {
                    System.out.println("Enter the bus driver you want to search by: ");
                    tempstring = scanner.next();
                    for(Vehicle v: VehicleList.values())
                    {
                      if(v.BusDriver_name.equalsIgnoreCase(tempstring))
                      {
                        isFound = true;
                        printResults(v);
                      }     
                    }              
                }
            }
        }
        if(isFound == false)
        {
            System.out.println("No Vehicle matches the entered license plate. Would you like to try again? ");
            scanner.next();
            while(!scanner.next().equalsIgnoreCase("yes") && !scanner.next().equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to continue searching vehicles or 'no' if you would like to stop: ");
                scanner.next();
            }        
        }  
        
        System.out.print("Would you like to search for another item? ");
        again = scanner.next();
        while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
        {
            System.out.println("Invalid input. Please enter 'yes' if you would like to continue searching for vehicles or 'no' if you would like to stop: ");
            again = scanner.next();
        } 
        if(again.equalsIgnoreCase("yes"))
        {
            input = false;
        }
      }  
    }
    
    void displayVehicles()
    {
        if(VehicleList.isEmpty())
        {
            System.out.println("No vehicles exist. Would you like to start adding vehicles?");
            String addresponse = scanner.next();
            while(!addresponse.equalsIgnoreCase("yes") && !addresponse.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to start adding vehicles or 'no' if you would like to stop: ");
                addresponse = scanner.next();
            }            
        
            if(addresponse.equalsIgnoreCase("yes"))
            {
                this.add();
            }
        }
        
        else
        {
            for (Vehicle v : VehicleList.values()) 
            {
            System.out.println(v);
            }
        }
    }
    
    public static void initializeAvailibilityMap()
    {
        for(Trips t: Trips.TripsMap.values())
        {
            for(Vehicle v: VehicleList.values())
            {
                if(t.getVehicle_id().equalsIgnoreCase(v.License_plate))
                {
                    v.num_of_available_seats = v.Number_of_seats;
                    AvailibilityMap.put(t.getTrip_id(), v.num_of_available_seats);
                }
            }
        } 
    }
  
    public static boolean newBooking(int trip_id, int number_of_passengers) {
        for(Map.Entry<Integer, Integer> entry : AvailibilityMap.entrySet()) 
        {
            int key = entry.getKey();
            int value = entry.getValue();
            if(trip_id == key)
            {
                if(value - number_of_passengers >= 0)
                {
                    AvailibilityMap.replace(key, (value - number_of_passengers));
                }
                else
                {
                    System.out.println("Number of seats not available. Number of available seats: " + value);
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    public static void cancelBooking(int booking_id, int number_of_cancelled_passengers) {
        int temp_tripid = 0;
        for(Booking book: Booking.getBook().values())
        {
            if(booking_id == book.getBooking_id())
            {
                temp_tripid = book.getTrip_id();
                break;
            }
        }
        for(Map.Entry<Integer, Integer> entry : AvailibilityMap.entrySet()) 
        {
            int key = entry.getKey();
            int value = entry.getValue();
            if(temp_tripid == key)
            {
                AvailibilityMap.replace(key, (value + number_of_cancelled_passengers));
            }
        }
    }
    
    public void numberofBookings(Vehicle vehicle, LocalDateTime start, LocalDateTime end){
        if(VehicleList.containsKey(vehicle.getLicense_plate()))
        {
            int count = 0;
            for(Booking b: Booking.getBook().values())
            {
                if(b.getLicense_plate().equals(vehicle.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
                {
                    count++;
                }
            }
            if (count == 0)
            {
                System.out.println("No booking exists for the given vehicle within the given time frame.");
            }   
            else
                System.out.println("The vehicle's number of bookings within the given time frame is: " + count);
        }
        else
        {
            System.out.println("Vehicle does not exist.");
        }
    }
    
    public Vehicle mostBooked(LocalDateTime start, LocalDateTime end)
    {
        Vehicle mostbooked =  new Vehicle();
        int maxBookings = 0;
        for(Vehicle vehicle: VehicleList.values())
        {
            int countBookings = 0;
            for(Booking b: Booking.getBook().values())
            {
                if(b.getLicense_plate().equals(vehicle.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
                {
                    countBookings++;
                }
            }
            if(countBookings > maxBookings)
            {
                maxBookings = countBookings;
                mostbooked = vehicle;
            }
        } 
        return mostbooked;
    }
    
    public void numberofTrips(Vehicle vehicle, LocalDateTime start, LocalDateTime end)
    {
        if(VehicleList.containsKey(vehicle.getLicense_plate()))
        {
            int count = 0;
            for(Trips t: Trips.TripsMap.values())
            {
                if(t.getVehicle_id().equals(vehicle.getLicense_plate()) && (t.getDepartureDateTime().isAfter(start) || t.getDepartureDateTime().isEqual(start)) && (t.getDepartureDateTime().isBefore(end) || t.getDepartureDateTime().isEqual(end)))
                {
                    count++;
                }
            }
            if (count == 0)
            {
                System.out.println("No trips exists for the given vehicle within the given time frame.");
            }   
            else
                System.out.println("The vehicle's number of trips within the given time frame is: " + count);
        }
        else
        {
            System.out.println("Vehicle does not exist.");
        }
    }
        
    public Vehicle mostRevenue(LocalDateTime start, LocalDateTime end)
    {
        Vehicle mostRevenue = new Vehicle();
        double maxRevenue = 0;
        for(Vehicle vehicle: VehicleList.values())
        {
            double tempRevenue = 0;
            for(Booking b: Booking.getBook().values())
            {
                if(b.getLicense_plate().equals(vehicle.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
                {
                    tempRevenue += vehicle.getTicket_price();
                }
            }
            if(tempRevenue > maxRevenue)
            {
                maxRevenue = tempRevenue;
                mostRevenue = vehicle;
            }
        } 
        return mostRevenue;
    }
    
    
      public void printResults(Vehicle v)
      {
            System.out.println("Search Results: ");
            System.out.println(v);
      }
      
      @Override
      public String toString()
      {
        return ("License plate: " + License_plate +", Category: " + Category + ", Number of seats: " + Number_of_seats + ", Ticket price: " + ticket_price + ", Bus Driver: " + BusDriver_name);
      }
      
      
      //INCOMPLETE WHERES THE FILE???
      public void viewReports()
      {
          System.out.print("Enter the license plate of the vehicle you want to view the report of: ");
          String str = scanner.next(), tryagain = "";
          LocalDateTime start, end;
          while(tryagain.equalsIgnoreCase("yes"))
          {
            if(VehicleList.containsKey(str))
            {
                System.out.print("Enter the start date of the information you want within the report (dd-MM-yyyy): ");
                start = LocalDateTime.parse(scanner.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                System.out.print("Enter the end date of the information you want within the report(dd-MM-yyyy): ");
                end = LocalDateTime.parse(scanner.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Vehicle vehicle = VehicleList.get(str);
                System.out.println("Vehicle Category: " + vehicle.getCategory());
                vehicle.numberofBookings(vehicle, start, end);
                vehicle.numberofTrips(vehicle, start, end);
                System.out.println("Most booked vehicle within the given time frame: " + vehicle.mostBooked(start, end));
                System.out.println("Most revenue vehicle within the given time frame: " + vehicle.mostRevenue(start, end));
            }
            else
            {
                System.out.println("No vehicle with such a license plate exists. Would you like to try again?");
                tryagain = scanner.next();
            }              
          }

      }
    
      
      public static void updateFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(vehicleFile))) {
            for (Vehicle newVehicle : Vehicle.VehicleList.values()) {
                oos.writeObject(newVehicle);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
      }

    public static void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(vehicleFile))) {
            Vehicle.VehicleList.clear(); // Clear the existing data
            while (true) {
                try {
                    Vehicle newVehicle = (Vehicle) ois.readObject();
                    Vehicle.VehicleList.put(newVehicle.getLicense_plate(), newVehicle);
                } catch (ClassNotFoundException | IOException e) {
                    // End of file or other exception, break the loop
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

      
      public boolean isUnique(String tempLP)
      {
       for(Vehicle v: VehicleList.values())
       {
           if(tempLP.equalsIgnoreCase(v.License_plate))
           {
             return false;
            }
        }
        return true;
      }
}

