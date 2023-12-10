/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project.trial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author jana
 */

public class ProjectTrial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{

        Admin ParentAdmin = new Admin(101, "Admin@Parent101", "Parent_Admin");
        Scanner s = new Scanner(System.in);
        boolean homepage = true;
        Vehicle.initializeAvailibilityMap();
        
        while(true)
        {
        int who = ParentAdmin.Login();
        
        while(homepage)
        {
            switch(who)
            {
                case(1):
                {
                    System.out.println("Welcome Admin. What would you like to do: ");
                    System.out.println("1. Manage Vehicles");
                    System.out.println("2. Manage Trips");
                    System.out.println("3. Manage Users");
                    int response = s.nextInt();
                    s.nextLine();
        
                    switch(response)
                    {
                        case 1:
                        {
                            Admin.managesVehicle();
                            System.out.println("Would you like to return to home page?");
                            String home = s.nextLine();
                            if(!home.equalsIgnoreCase("yes"))
                            {
                                homepage = false;
                            }
                            break;
                        }
                        case 2: 
                        {
                            Admin.managesTrips();
                            System.out.println("Would you like to return to home page?");
                            String home = s.nextLine();
                            if(!home.equalsIgnoreCase("yes"))
                            {
                                homepage = false;
                            }
                            break;
                        }
                        case 3:
                        {
                            Admin.manageUsers();
                            System.out.println("Would you like to return to home page?");
                            String home = s.nextLine();
                            if(!home.equalsIgnoreCase("yes"))
                            {
                                homepage = false;
                            }
                            break;
                        }
                    }
                    break;
                }
                case(2):
                {
                    System.out.println("Welcome Receptionist.");
                    Receptionist.managesBooking();
                    break;
                }
                case(3):
                {
                    System.out.println("Welcome Guest.");
                    break;
                }
            }
        }
            System.out.println("Would you like to return to log in page? ");
            String response = s.nextLine();
            while(!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you would like to return to log in page or 'no' if you would like to exit: ");
                response = s.next();
            }
            if(response.equalsIgnoreCase("no"))
            {
                System.out.println("Goodbye " + Users.getCurrentUser().Name);
                break;
            }
        }
    } 
}
