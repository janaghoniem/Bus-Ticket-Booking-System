/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaApplication1;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author sarad
 */
public  class Users implements Serializable{
    

    
    protected int ID;
    protected String Password;
    protected String Name;
    private static Users currentUser;
    static Map<Integer, String> usersMap = new HashMap<>();



    public Users(int ID, String Password, String Name) {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
    }
    
    
    public Users(int ID, String Password) {
        this.ID = ID;
        this.Password = Password;
    }
    
    
    //CONSTRUCTOR 
    public Users (){}

    
    
    //Read from File into HashMap
//    static void readFromFile() throws FileNotFoundException 
//    {
//        try
//        { 
//            File file = new File("file:C:/Users/sarad/Downloads/user_information.txt");
//            
//            if(!file.exists())
//            {
//                System.err.println("File not found" + file.getAbsolutePath());
//            }
//            
//            Scanner scanner =  new Scanner(file);
//
//            while(scanner.hasNext())
//            {
//                int id = scanner.nextInt();
//                String password = scanner.next();
//                String name = scanner.next();
//
//                usersMap.put(id, password);
//                
//                System.out.println("Read users: ID >> " + id + ", Password >> " + password + ", Name >> " + name);
//            }
//        } catch(FileNotFoundException e)
//        {
//            System.err.println("File not found"+e.getMessage());
//        }
//    }
    //Binary File solution 
    static void readFromFile() throws FileNotFoundException
    {
        FileInputStream file = new FileInputStream("user_data.dat");
      
        try(ObjectInputStream ois = new ObjectInputStream(file))
        {
            Users.usersMap  = (Map<Integer, String>) ois.readObject();
            System.out.println(Users.usersMap);
            System.out.println(Users.getUsersMap());
            ois.close(); file.close();
        } 
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error opening file: " + ex.getMessage());
        }
        catch(IOException e) 
        {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    //Write from HashMap into File
//    private void writeToFile() 
//    {
//        try 
//        {
//            File file = new File("user_information.txt");
//            try (FileWriter writer = new FileWriter(file, true)) 
//            {
//                writer.write(this.ID + " " + this.Password + " " + this.Name + "\n");
//            }
//        } catch (IOException e) 
//        {
//            System.out.println("Data already exist");
//        }
//    }
    
    //Binary File solution 
    private static void writeToFile() throws FileNotFoundException
    {
        FileOutputStream file = new FileOutputStream("user_data.dat");
        try(ObjectOutputStream oos = new ObjectOutputStream(file))
        {
            oos.writeObject(new Users());
            oos.close(); file.close();
        } catch(IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public boolean isValidAdmin(int input_id, String input_password) throws FileNotFoundException
    {
        Users.readFromFile();
        String admin = usersMap.get(input_id);
        return admin != null && admin.equals(input_password);
    }
    
    public boolean isValidRec(int input_id, String input_password)
    {
        String rec = usersMap.get(input_id);
        return rec != null && rec.equals(input_password);
    }
     
    public boolean isValidGuest(int input_id, String input_password)
    {
        String guest = usersMap.get(input_id);
        return guest != null && guest.equals(input_password);
    }
    
    public int Login2(int ID, String Password) throws FileNotFoundException
    {
        Password = Password.trim();
        
        if(isValidAdmin(ID,Password))
        {
            System.out.println("Login successfully!");
            return 1;
        }
        else if(isValidRec(ID,Password))
        {
            System.out.println("Login successfully!");
            return 2;
        }
        else if(isValidGuest(ID,Password))
        {
            System.out.println("Login successfully!");
            return 3;
        }
        else
        {
            System.out.println("Faild to login!");
            return -1;
        }
    }
    
    public int Login() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        boolean restart = true;
        System.out.println("Welcome User. Are you an (1) Admin, (2) Receptionist, or (3) Guest: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch(option){
            case 1: 
            {
                while(restart)
                {
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    while(count < 3)
                    {
                        if (isValidAdmin(id, password)) 
                        {
                            System.out.println("Login successful!");
                            restart = false;
                            return 1;
                        } 
                        else 
                        {
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
                        if (isValidGuest(id, password)) {
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
//    public void Signup(int input_id, String input_password){
//        this.ID = input_id;
//        this.Password = input_password;
//        
//        writeToFile();
//    }

    
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

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Users currentUser) {
        Users.currentUser = currentUser;
    }

    public static Map<Integer, String> getUsersMap() {
        return usersMap;
    }

    public static void setUsersMap(Map<Integer, String> usersMap) {
        Users.usersMap = (HashMap<Integer, String>) usersMap;
    }
    
    

    //methods 
     private void exit() {
         System.out.println("Thank you for using our bus ticket booking system.");
         System.exit(0);
     }
 
}
