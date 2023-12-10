
package project.trial;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Map;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Roaa
 */
public class Trips implements manages{
    public enum TripCategory {
    SAFARI,        
    CULTURAL,       
    ADVENTURE,     
    EDUCATIONAL,   
    WELLNESS        
   
    }
    public enum Destination{
        CAIRO,LUXOR,ALEXANDRIA,
        PORT_SAID,ISAMAILIA,
        BENISUEF,QENA,
        FAYOUM,ASYUT,ASWAN,
        SUEZ,GIZA,DAHAB,
        MARASSI
    }
 
    Scanner n = new Scanner(System.in);
    
    private TripCategory tripCategory;      //final
    private int trip_id;                     //unique *
    private String vehicle_id;
    private int numOfPassengers;   
    private Destination destination;       //range
    private double  price;            //price range  
    private LocalDateTime   ArrivalDateTime;
    private LocalDateTime DepartureDateTime;
    public static  HashMap<Integer,Trips>TripsMap=new HashMap<>();  
    public  int countTrips=0;
    
  //Constructors
    
    public Trips()
    {
        
    }

    public Trips(int trip_id, String vehicle_id, int numOfPassengers, Destination  destination, double price, LocalDateTime DepartureDateTime, LocalDateTime  ArrivalDateTime, TripCategory tripCategory,int countTrips) 
    {
        this.trip_id = trip_id;
        this.vehicle_id = vehicle_id;
        this.numOfPassengers = numOfPassengers;
        this.destination = destination;
        this.price = price;
        this.DepartureDateTime = DepartureDateTime;
        this.ArrivalDateTime = ArrivalDateTime;
        this.tripCategory=tripCategory;
        this.countTrips=countTrips;
    }
    
  
  //Setters & Getters
    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getArrivalDateTime() {
        return ArrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime ArrivalDateTime) {
        this.ArrivalDateTime = ArrivalDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime DepartureDateTime) {
        this.DepartureDateTime = DepartureDateTime;
    }

   
    public TripCategory getTripCategory() {
        return tripCategory;
    }
    
    public void setTripCategory(TripCategory tripCategory) {
        this.tripCategory = tripCategory;
    }

    public int getCountTrips() {
        return countTrips++;
    }

    public void setCountTrips(int countTrips) {
        this.countTrips = countTrips;
    }

    
    @Override
    public String toString() {
        return "Destination: " + destination +
               "\nDeparture Time: " + DepartureDateTime +
               "\nArrival Time: " + ArrivalDateTime +
               "\nPrice: " + price +
               "\nLicense Plate: " + vehicle_id +
               "\nTrip Category: "+ tripCategory +"\n";
    }

      //Methods    
    //Add Function
    @Override
    public void add()
    {
                    System.out.println("Add trip details");
       while(true)
        {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Trips trip = new Trips();
        boolean validDestination = false;                                             //    DESTINATION ENUM
        while(!validDestination) {
           try{ 
               System.out.print("Trip destination: ");
               String enteredDestination=n.nextLine();
               Destination dest=Destination.valueOf(enteredDestination.toUpperCase());
               trip.setDestination(dest);
               validDestination=true;
            }
            catch (IllegalArgumentException e) {
               System.out.println("Invalid destination. Please enter a valid String");
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
            }
        }
       
        boolean validCategory = false;               //  Trip category
        while (!validCategory) {
           try {
               System.out.print("Trip Category: ");
               String enteredCategory = n.nextLine();
               TripCategory category = TripCategory.valueOf(enteredCategory.toUpperCase());
               trip.setTripCategory(category);
               validCategory = true;
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter a valid category.");
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
                System.out.println("Error: "+ e.getMessage());
            }
        }
     
        boolean validDeparture=false;                        // Trip departure Date Time
        while(!validDeparture)
        {
            try{
                System.out.print("Trip Departure Time  [yyyy-MM-dd HH:mm:ss Format]: " );      
                String departureDateTime = n.nextLine();
                LocalDateTime parsedDeparture = LocalDateTime.parse(departureDateTime,formatter);
                trip.setDepartureDateTime(parsedDeparture);
                validDeparture=true;
            }
            catch(DateTimeParseException e){
                System.out.println("Invalid time format, Please enter the correct format: ");
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
                System.out.println("Error: "+ e.getMessage());
            }
        }
       
        boolean validArrival=false;                // Trip Arrival date Time
        while(!validArrival)
        {
            try{
                System.out.print("Trip Arrival Date Time [yyyy-MM-dd HH:mm:ss Format]:  ");           
                String arrivalDateTime = n.nextLine();
                LocalDateTime parsedArrival = LocalDateTime.parse(arrivalDateTime,formatter);
                if(parsedArrival.isAfter(trip.getDepartureDateTime()))
                {
                    trip.setArrivalDateTime(parsedArrival);
                    validArrival=true;
                }
                else
                {
                    System.out.println("Invalid Arrival date time . please enter a Date Time after departure date and time");      
                }
            }
            catch(DateTimeParseException e){
                System.out.println("Invalid date time format, Please enter the correct format : ");
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
                System.out.println("Error: "+ e.getMessage());
            }
        }

        boolean validVehicleId=false;                  //  Vehicle ID or License plate
        while(!validVehicleId)
        {
            try{
                System.out.print("Vehicle License plate: " );  
                String enteredVehicleID = n.nextLine();
                for(Vehicle v: Vehicle.getVehicleList().values())
                {
                    if(v.getLicense_plate().equalsIgnoreCase(enteredVehicleID))
                    {
                        trip.setVehicle_id(enteredVehicleID);
                        validVehicleId=true;
                    }
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid value for Vehicle License plate.");
                n.nextLine();
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
                System.out.println("Error: "+ e.getMessage());
            }
        }
      
        boolean validRange = false;           // price 
        while (!validRange)
        {
            try{
                System.out.print("Trip Price: ");
                double enteredPrice = n.nextDouble();
                if (enteredPrice > 10 && enteredPrice < 10000) 
                {
                    trip.setPrice(enteredPrice);
                    validRange = true;
                } 
                else
                {
                    System.out.println("Invalid price! Please enter within 50 < price < 600 range");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter integer values");
                n.nextLine(); // Consume the invalid input
            }
            catch(Exception e) {
                System.out.println("An unexpected error has occurred. Please try again.");
                System.out.println("Error: "+ e.getMessage());
            }    
        }
       boolean newTrip=true;
        countTrips++;
        trip.setCountTrips(countTrips);
        trip.setTrip_id(countTrips);
 
        for(Trips t:TripsMap.values())
        {
            if(t.getDestination()==trip.getDestination()&& t.getDepartureDateTime().isEqual(trip.getDepartureDateTime()) &&
              t.getArrivalDateTime().isEqual(trip.getArrivalDateTime()) && t.getVehicle_id().equals(trip.getVehicle_id()))
            {
                newTrip=false;
                break;
            }
        }
        if(newTrip==true)
        {
            n.nextLine();
            TripsMap.put(trip.getTrip_id(), trip); 
            System.out.println(trip.getTrip_id() + " Trip added successfully");
            System.out.println("Would you like to add another trip? ");
            String Response = n.nextLine();
            if (!Response.equalsIgnoreCase("yes")) 
            {
                break;
            }
        }
        else if(newTrip==false)
        {
            n.nextLine();
             System.out.println("There is already an existing trip with the same details.");
             System.out.println("Would you like to try adding a trip again? ");
             String Response = n.nextLine();
             if (!Response.equalsIgnoreCase("yes")) 
             {
                 break;
             }
         n.nextLine();
        }
}
   
        
        
 
        
    
    } //  End add function
    
    
    //  Remove function
    //@Override
    public void remove()
    {
                            System.out.println("Remove trip:");
        Scanner n=new Scanner(System.in);
        System.out.print("Please enter trip ID::");
        int enteredId = n.nextInt();
        if(TripsMap.containsKey(enteredId))
        {
            TripsMap.remove(enteredId);
            System.out.println(enteredId+" Removed successfully");
        }
        else
        {
            System.out.println(enteredId+" Not in the system");
        }     
    }    //  End  Remove function

        
     //View Reports Function
    public static void ViewTrips()
    {
        if(TripsMap.isEmpty())
        {     
            System.out.println("The trips list is empty");
        }
        else
        {
              for (Map.Entry<Integer, Trips> i : TripsMap.entrySet())  
              {
            
                  System.out.println("Key: " + i.getKey()  + i.getValue());
             }
}
    }//    End

         
     // Search by any field Function
    @Override
    public void search() {
        if(TripsMap.isEmpty())             //  check if there is trips in the hashmap (system) 
        {
            System.out.println("No trips to search from.");
        }
        else
        {
            System.out.println("Search trip details:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // set dateTime format
            System.out.println("1) Trip ID");
            System.out.println("2) Arrival  Date Time");
            System.out.println("3) Destination");
            System.out.println("4) Departure Date Time");
            System.out.println("5) License Plate");
            System.out.println("6) Trip Price");
            System.out.println("7) Trip Category");
            boolean validInput = false; 
            int input = 0 ;  
            while(validInput==false)    //  check if user input is valid   [ for switch case ]
            { 
                System.out.println("What field you want to search with:");
                System.out.print("Choose from the list above: ");
                try
                {
                    input = n.nextInt();  
                    validInput=true;
                } 
                catch(InputMismatchException e)      
                {
                    System.out.println("Invalid input. Please enter a valid number.");
                    n.nextLine();
                }
                catch(Exception e)
                {
                    System.out.println("An unexpected error has occurred. Please try again later.");                                        
                    n.nextLine();
                }
            }
            
           // -------------------------------
             boolean validSearch=false;  //to validate successful search
            switch (input) 
            {
               
                case 1://  Trip ID
                {
                    System.out.print("Enter Trip ID to search: ");
                    try
                    {                
                        int tripID = n.nextInt();
                        n.nextLine();
                        if(TripsMap.containsKey(tripID))    // check if entered trip id in hashmap
                        {
                            System.out.println(TripsMap.get(tripID));
                        }            
                        else             //  if not found 
                        {
                            System.out.println("We don't have trip with ID "+tripID);
                        }  
                    }
                    catch(InputMismatchException e)    //incase of wrong input
                    {
                        System.out.println("Invalid input. The entered input is not a valid number");
                    }
                    catch(Exception e)   // handling any exception
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                                                    System.out.println("Error: "+ e.getMessage());
                    } 
                    break;
                }

                case 2:// ARRIVAL  Date Time
                {
                    try
                    {
                        System.out.print("Enter the Arrival Date Time to search in  [yyyy-MM-dd HH:mm:ss Format]:  ");
                        n.nextLine();
                        String arrivalDateTime = n.nextLine();
                        LocalDateTime parsedArrival = LocalDateTime.parse(arrivalDateTime,formatter);
                        for (Map.Entry<Integer, Trips> i : TripsMap.entrySet())    // search for  entered arrival dateTime
                        {
                            Trips trip = i.getValue();
                            if (trip.getArrivalDateTime().equals(parsedArrival))   // if found
                            {
                                System.out.println("Key:  " + i.getKey());  
                                System.out.println(trip);
                                validSearch=true;
                            }
                        }
                        if(validSearch==false)
                        {
                            System.out.println("No trip with Arrival Date Time  "+parsedArrival);
                        }
                    }
                            
                    
                    catch(DateTimeParseException e)   // if entered format is wrong
                    {
                        System.out.print("Wrong dateTime format.\nThe correct Format [yyyy-MM-dd HH:mm:ss Format] ");
                        n.nextLine();
                    }
                    catch(Exception e)   //if any exception occured
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");       
                        System.out.println("Error: "+ e.getMessage());
                        n.nextLine();
                    }
                    break;
                }

                case 3:              //  destination
                {
                    try
                    {
                        System.out.print("Enter Destination to search: ");
                        n.nextLine();
                        String destination_input = n.nextLine();
                        Destination destination=Destination.valueOf(destination_input.toUpperCase());

                        for (Map.Entry<Integer, Trips> i : TripsMap.entrySet())    //search for entered destination
                        {
                            if (destination.equals(i.getValue().getDestination()))   // if found
                            {
                                System.out.println("Key:  " + i.getKey());
                                System.out.println(i.getValue());
                                validSearch=true;
                            }
                        }    
                          if(validSearch==false)
                        {
                        System.out.println("There is not trip with  "+ destination_input);
                        }
                    }
                    catch(InputMismatchException e)    //incase of wrong input
                    {
                        System.out.println("Invalid input. The entered input is not a valid String");
                        n.nextLine();
                    }
                    catch(Exception e)   // handling any exception
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                        System.out.println("Error: "+ e.getMessage());
                        n.nextLine();
                    }
                    break;
                }

                case 4:   
                {
                    try
                    {
                        System.out.print("Enter the Departure Date Time to search in  [yyyy-MM-dd HH:mm:ss Format]:  ");
                        n.nextLine();
                        String DepartureDateTime = n.nextLine();
                        LocalDateTime parsedDeparture= LocalDateTime.parse(DepartureDateTime,formatter);
                        for (Map.Entry<Integer, Trips> i : TripsMap.entrySet())    // search for  entered Departure dateTime
                        {
                            Trips trip = i.getValue();
                            if (trip.getDepartureDateTime().equals(parsedDeparture))   // if found
                            {
                                System.out.println("Key:  " + i.getKey());  
                                System.out.println(trip);
                                validSearch=true;
                            }
                        
                        }
                        if(validSearch==false)
                        {
                                System.out.println("No trip with Departure Date Time  "+parsedDeparture);
                        }
                                                    
                    }
                    catch(DateTimeParseException e)   // if entered format is wrong
                    {
                        System.out.print("Wrong dateTime format.\nThe correct Format: [yyyy-MM-dd HH:mm:ss Format] ");
                                                n.nextLine();
                    }
                    catch(Exception e)   //if any exception occured
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                                                n.nextLine();
                    }    
                    break;
                }
            
                case 5:             //vehicle ID
                {
                    try{
                        System.out.print("Enter License plate to search: ");
                        n.nextLine();
                        String vehicleID = n.nextLine(); 
                        for(Map.Entry<Integer, Trips>i:TripsMap.entrySet())
                        {
                            Trips trip=i.getValue();
                            if(trip.getVehicle_id().equalsIgnoreCase(vehicleID))
                            {
                                System.out.println("Key: "+ i.getKey());
                                System.out.println(trip);
                                validSearch=true;
                            }
                            if(validSearch==false)
                            {
                                                            System.out.println("No trip with vehicle ID "+vehicleID);
                            }
                        }
                        
                    }
                    catch(InputMismatchException e)    //incase of wrong input
                    {
                        System.out.println("Invalid input. The entered String is invalid");
                        n.nextLine();
                    }
                    catch(Exception e)   // handling any exception
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                    }
                    break;
                }
                
                case 6:
                {
                    try 
                    {
                        System.out.print("Enter Trip price to search: ");
                        n.nextLine();
                        double tripPrice = n.nextDouble();
                        for(Map.Entry<Integer, Trips>i:TripsMap.entrySet())
                        {
                            Trips trip=i.getValue();
                            if(trip.getPrice()==tripPrice)
                            {
                                System.out.println("Key: "+ i.getKey());
                                System.out.println(trip);
                                validSearch=true;
                            }
                        }
                        if(validSearch==false)
                        {
                                                    System.out.println("No trip with price  "+tripPrice);
                        }
                    }
                    catch(InputMismatchException e)    //incase of wrong input
                    {
                        System.out.println("Invalid input. The entered input is not a valid number");
                        n.nextLine();
                    }
                    catch(Exception e)   // handling any exception
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                    }  
                    break;
                }
                
                case 7:
                {
                    System.out.print("Enter Trip category to search: ");
                    try
                    { 
                        n.nextLine();   //to empty the input buffer  if not used -> cant take input and goes straight to the exception 
                        String category_entered = n.nextLine();   //take user input
                      
                        TripCategory category=TripCategory.valueOf(category_entered.toUpperCase());  //convert input into uppercase and an enum constant   //may throw illigal exception
                    
                        for (Map.Entry<Integer, Trips> i : TripsMap.entrySet())    //search for entered category    
                        {
                            if (category.equals(i.getValue().getTripCategory()))   // if found
                            {
                                System.out.println("Key:  " + i.getKey());   //print key and the whole trip details
                                System.out.println(i.getValue());
                                validSearch=true;
                            }
                        }
                        if(validSearch==false)
                        {
                                                        System.out.println("No trip with  category  "+category_entered);  //if not found
                        }
                      }
                    catch(IllegalArgumentException e)
                    {
                            System.out.println("Invalid category: " + e.getMessage());
                    }
                    catch(InputMismatchException e)    //incase of wrong input
                    {
                        System.out.println("Invalid input. The entered input is not a valid String");
                        n.nextLine();
                    }
                    catch(Exception e)   // handling any exception
                    {
                        System.out.println("An unexpected error has occurred. Please try again later.");
                                                System.out.println("Error: "+ e.getMessage());
                    }
                    break;
                }

                default:
                {
                    System.out.println("Invalid choice. Please choose a number between 1 and 7.");
                    break;
                }
            }
        }  
    }

  
    @Override
    public void edit()
    {
        int tripID = 0;
        boolean Empty = false;
        boolean validTripID=false;
    
        if(TripsMap.isEmpty())    //check empty?
        {
            System.out.println("No trips to edit.");
            Empty = true;
        }
        else    //  if not empty ->  enter trip id 
        { 
                                        System.out.println("Edit trip details:");
            try
            {               
                System.out.print("Enter Trip ID: ");
                tripID = n.nextInt();        
                if(TripsMap.containsKey(tripID))    // check if entered trip id in hashmap
                {
                    System.out.println(TripsMap.get(tripID));
                    validTripID=true;
                }            
                else             //  if not found 
                {
                    System.out.println("We don't have trip with ID "+ tripID);
                    validTripID=false;
                }
            }
            catch(InputMismatchException e)    //incase of wrong input
            {
                System.out.println("Invalid input. The entered input is not a valid number");
                n.nextLine();
            }
            catch(Exception e)   // handling any exception
            {
                System.out.println("An unexpected error occured,please try again later.");
            }
        }
        if(validTripID==true)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // set dateTime format
            System.out.println("Enter the field you want to edit:");
            System.out.println("1) Trip Price");
            System.out.println("2) Departure  Date Time");
            System.out.println("3) Destination");
            System.out.println("4) Arrival Date Time");
            System.out.println("5) License Plate");
              
            boolean validInput = true; 
            int choice = 0;  
            while(validInput)    //  check if user input is valid   [ for switch case ]
            { 
                System.out.println("Choose from the list above: ");
                try
                {
                    choice = n.nextInt();  
                    validInput = false;
                } 
                catch(InputMismatchException e)      
                {
                    System.out.println("Invalid input. The entered input is not an integer. Please try again.");
                    n.nextLine();
                }
                catch(Exception e)
                {
                    System.out.println("An unexpected error occurred. Please try again later.");
                }
            }
            Trips trip=TripsMap.get(tripID);

            switch(choice)
            {
                case 1:
                {
                    boolean validRange = false;           // price    ****
                    while (!validRange)
                    {
                        try{
                            System.out.print("Trip Price: ");
                            double enteredPrice = n.nextDouble();
                            if (enteredPrice > 50 && enteredPrice < 600) 
                            {
                                trip.setPrice(enteredPrice);
                                validRange = true;
                            } 
                            else
                            {   
                                System.out.println("Invalid price! Please enter within 50 < price < 600 range");
                            }
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Invalid input. The entered is not an integer");
                        }
                        catch(Exception e)
                        {
                            System.out.println("An unexpected error occured,please try again later");
                            System.out.println("Error: "+ e.getMessage());
                        }    
                    }
                    break;
                }
        
                 
                case 2:          // Trip departure Date Time     **************
                {
                    n.nextLine();
                    boolean validDeparture=false;                        
                    while(!validDeparture)
                    {
                        try{
                                System.out.print("Trip Departure Time  [yyyy-MM-dd HH:mm:ss Format]: " );      
                                String departureDateTime = n.nextLine();
                                LocalDateTime parsedDeparture = LocalDateTime.parse(departureDateTime,formatter);
                                  if(parsedDeparture.isBefore(trip.getArrivalDateTime()))
                            {
                                trip.setDepartureDateTime(parsedDeparture);
                                validDeparture=true;
                            }
                               
                                  else
                                  {
                                        System.out.println("Invalid departure date time . please enter a Date Time before arrival date and time");      
                                                                        validDeparture=false;
                                  }
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid time format. The correct format [yyyy-MM-dd HH:mm:ss Format].");
                            n.nextLine();
                        }
                        catch(Exception e)
                        {
                            System.out.println("An unexpected error occured,please try again");
                        }
                    }
                    break;
                }
                
                case 3:
                {
                    n.nextLine();
                    boolean validDestination=false;                                             //    DESTINATION ENUM        **************
                    while(!validDestination){
                        try{ 
                            System.out.print("Trip destination:");
                            String enteredDestination=n.nextLine().trim();
                            Destination dest=Destination.valueOf(enteredDestination.toUpperCase());
                            trip.setDestination(dest);
                            validDestination=true;
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println("Invalid destination. Please enter a valid String.");
                        }
                        catch(Exception e)
                        {
                            System.out.println("An unexpected error occured,please try again later.");
                        }
                    }
                    break;
                }
                
                case  4:    ///arrival date time ****************
                {
                    n.nextLine();
                    boolean validArrival=false;                        
                    while(!validArrival)
                    {
                        try{
                            System.out.print("Trip Arrival Time  [yyyy-MM-dd HH:mm:ss Format]: " );      
                            String ArrivalDateTime = n.nextLine();
                            LocalDateTime parsedArrival = LocalDateTime.parse(ArrivalDateTime,formatter);
                            if(parsedArrival.isAfter(trip.getDepartureDateTime()))
                            {
                                 trip.setArrivalDateTime(parsedArrival);
                                 validArrival = true;
                            }
                            else
                            {
                                  System.out.println("Invalid Arrival date time . please enter a Date Time after departure date and time");      
                            }
                                                    
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid time format\n The correct format [yyyy-MM-dd HH:mm:ss Format]. ");
                            n.nextLine();
                        }
                        catch(Exception e){
                            System.out.println("An unexpected error occured,please try again later ");
                        }
                    }
                    break;            
                }

                case 5:
                {
                     n.nextLine();
                    boolean validVehicleId=false;                  //  Vehicle ID
                    while(!validVehicleId)
                    {
                        try
                        {
                            System.out.print("License plate: " );  
                            String enteredVehicleID=n.nextLine();
                            for(Vehicle v: Vehicle.getVehicleList().values())
                            {
                                if(v.getLicense_plate().equalsIgnoreCase(enteredVehicleID))
                                {
                                    trip.setVehicle_id(enteredVehicleID);
                                    validVehicleId=true;
                                }
                            }
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Invalid input. The entered is not an integer");
                            n.nextLine();
                        }
                        catch(Exception e)
                        {
                            System.out.println("An unexpected error has occurred. Please try again later.");
                            System.out.println("Error: "+ e.getMessage());
                        }
                    }
                    break;
                }
                
                default:
                {
                    System.out.println("Invalid choice.");
                    break;
                }
            }
        }
    }
     //file for the hashmap 

    public static void writeToFile() throws IOException
    {
        File file = new File("Trips.txt");                                                                
            try (FileWriter writer = new FileWriter(file)) {
            for (Map.Entry<Integer, Trips> entry : TripsMap.entrySet()) {
                int tripId = entry.getKey();
                Trips trip = entry.getValue();

                writer.write("Trip ID: " + trip.trip_id + "\n");
                writer.write("Destination: " + trip.destination + "\n" +
                        "Vehicle ID: " + trip.vehicle_id +"\n" +
                         "Trip Category: " + trip.tripCategory + "\n"  +
                         "Number of Passangers: " + trip.numOfPassengers + "\n" +
                         "Trip Price: " + trip.price + "\n" +
                         "Departure Time: " + trip.DepartureDateTime + "\n" +
                         "Arrival Time: " + trip.ArrivalDateTime + "\n"
                );


                writer.write("\n");
            }
        }
        System.out.println("Data written to user_data.txt");
 }
}
