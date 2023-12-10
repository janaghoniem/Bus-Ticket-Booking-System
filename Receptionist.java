/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nouran
 */
public class Receptionist extends Users {

    public Receptionist() {
    }
    
    public Receptionist(int id, String password, String name) {
        super(id, password, name);
    }

    public static List<Receptionist> receptionists = new ArrayList<>();

    public static void storeReceptionistInfo(Receptionist newReceptionist) {
        receptionists.add(newReceptionist);
    }

    public static void displayReceptionists() {
        System.out.println("Receptionists:");
        for (Receptionist r : receptionists) {
            System.out.println("ID: " + r.getID() + ", Name: " + r.getName() + ", Password: " + r.getPassword());
        }
    }
    
    public static void managesBooking()
    {
      Scanner in = new Scanner(System.in);
      Booking b = new Booking();
      while (true) {
      System.out.println("Welcome to the Booking Management System!");
      System.out.println("Choose an option:");
      System.out.println("1.Add booking");
      System.out.println("2.Remove booking");
      System.out.println("3.Edit booking");
      System.out.println("4.Calculate Average revenue, Total revenue, number of bookings over a specific period of time ");
      System.out.println("5.Calculate payment");
      System.out.println("6.View Reports in terminal");
      System.out.println("7.View Reports in file");
      System.out.println("8. Exit");

       int choose = in.nextInt();
      switch(choose){
          case 1:
              b.select_trip_details();
              b.add();
              break;
          case 2:
              b.remove();
              break;
          case 3:
              //b.edit();
              break;
          case 4:
              b.calculateAverageTotalRevenue(LocalDateTime.MAX, LocalDateTime.MIN);
              break;
          case 5:
              //b.calculate_payment();
              break;
          case 6:
              b.viewReports();
              break;
          case 7:
            String file= "View Bookings";
              //b.viewFile( file);
              
              break;
              case 8:
                    System.out.println("Thank you for using our Booking Management System. See you soon!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
              
      }
    }
}

