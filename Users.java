/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sarad
 */
public  class Users {
    
     static Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected int ID;
    protected String Password;
    protected String Name;
    private static Users currentUser;

    private Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Users(int ID, String Password, String Name) {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
    }
    
    
    //CONSTRUCTOR 
    public Users (){
    
//         userList = new ArrayList<>();
        // Read the file and add to the ArrayList
//        readFromFile();
        
    }
    
    //Read file into ArrayList 
    static void readFromFile() throws FileNotFoundException {

        try{ 
        File file = new File("user_information.tx");
        Scanner scanner =  new Scanner(file);
         
        ArrayList info = new ArrayList();
        while(scanner.hasNext()){
        info.add(scanner.next());
        }
        System.out.println(info);
        }catch(FileNotFoundException e){
    System.err.println("File not found"+e.getMessage());
    }
    }

    private void writeToFile() {
        try {
            File file = new File("user_information.txt");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(this.ID + "," + this.Password);
            }
        } catch (IOException e) {
             System.out.println("Data already exist");
        }
    }
    
    public boolean isValidAdmin(int input_id, String input_password) {
        for (Admin admin : Admin.getAdmins()) {
            
            if (admin.getID() == input_id && admin.getPassword().equals(input_password)) {
                currentUser = admin;
                return true;
            }
        }
        return false;
    }
    
     public boolean isValidRec(int input_id, String input_password) {
        for (Users user : Admin.getUsers()) {
            
            if ((input_id) == user.getID() && user.getPassword().equals(input_password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    
    public int Login(){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        boolean restart = true;
        System.out.println("Welcome User. Are you an (1) Admin, (2) Receptionist, or (3) Guest: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch(option){
            case 1: {
                while(restart){
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    while(count < 3){
                         if (isValidAdmin(id, password)) {
                                System.out.println("Login successful!");
                                restart = false;
                                return 1;
                            } else {
                                System.out.println("Invalid ID or password.");
                                count++;
                            }
                            if(count == 3){ exit(); }
                            break;
                        }
                    }
                }
                case 2: {
                     while(restart){
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();


                        while(count < 3){
                            if (isValidRec(id, password)) {
                                System.out.println("Login successful!");
                                restart = false;
                                return 2;
                            } else {
                                System.out.println("Invalid ID or password.");
                                count++;
                            }
                            if(count == 3){ exit(); }
                        }
                        break;
                    }
                }
                 case 3: {
                   while(restart){
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();


                        while(count < 3){
                            if (isValidRec(id, password)) {
                                System.out.println("Login successful!");
                                restart = false;
                                return 3;
                            } else {
                                System.out.println("Invalid ID or password.");
                                count++;
                            }
                            if(count == 3){ exit(); }
                            break;
                    }
                        
                }
            }
        }
        return -1;
    }
    
    
    
    //Sign up function 
    public void Signup(int input_id, String input_password){
        this.ID = input_id;
        this.Password = input_password;
        
        writeToFile();
    }

    
    //Setters and Getters 
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public static Users getCurrentUser() {
        return currentUser;
    }
    
    
  
    //methods 
     private void exit() {
         System.out.println("Thank you for using our bus ticket booking system.");
     }

    //zyada 3ashan el admin
 public void setSalary(double salary) {
        this.Salary = salary;
    }

    // New method to get salary for users
    public double getSalary() {
        return Salary;
    }

    // New method to set bonus for users
    public void setBonus(int bonus) {
        this.Bonus = bonus;
    }

    // New method to get bonus for users
    public int getBonus() {
        return Bonus;
    }

 public static void addSalaryToFile(int userId, double salary) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("user_information.dat", true))) {
            dos.writeInt(userId);
            dos.writeDouble(salary);
            System.out.println("Salary added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding salary to file: " + e.getMessage());
        }
    }
 public static void addBonusToFile(int userId, int bonus) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("user_information.dat", true))) {
            dos.writeInt(userId);
            dos.writeInt(bonus);
            System.out.println("Bonus added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding bonus to file: " + e.getMessage());
        }
    }

 
}
