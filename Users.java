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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 *
 * @author sarad
 */
public  class Users implements Serializable{
    
    enum Type
    {
        ADMIN,
        RECEPTIONIST, 
        GUEST
    }

    //Implementations 
    protected int ID;
    protected String Password;
    protected String Name;
    protected Type position;
    private double Salary;
    private int Bonus;
    private static Users currentUser;
    static Map<Integer, String> usersMap = new HashMap<>();
    
    
    //CONSTRUCTORS 
    public Users (){}
    public Users(int ID, String Password, String Name) 
    {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
        usersMap.put(ID,Password);
    }
    
    public Users(int ID, String Password, String Name, Type position) 
    {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
        this.position = position;
        usersMap.put(ID,Password);
    }
    
    
    public Users(int ID, String Password) 
    {
        this.ID = ID;
        this.Password = Password;
    }
    
    
    
  
    //Read from Binary File solution 
    static void readFromFile() throws FileNotFoundException
    {
        FileInputStream file = new FileInputStream("user_information.dat");
      
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
    
   
    //Write to Binary File solution 
    private static void writeToFile() throws FileNotFoundException
    {
        FileOutputStream file = new FileOutputStream("user_information.dat");
        try(ObjectOutputStream oos = new ObjectOutputStream(file))
        {
            oos.writeObject(new Users());
            oos.close(); file.close();
        } catch(IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    //Validation Functions 
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
    
    
    //Login Function 
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
    
    public void setSalary(double salary) {
        this.Salary = salary;
    }
    
    public double getSalary()
    {
        return Salary;
    }
    
    public void setBonus(int bonus) {
        this.Bonus = bonus;
    }
    
    public int getBonus() {
        return Bonus;
    }
    

    //methods 
    private void exit() 
    {
        System.out.println("Thank you for using our bus ticket booking system.");
        System.exit(0);
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
    
    //Design
    private void setSceneBackground(VBox container) {
        String imagePath = "C:\\Users\\sarad\\Downloads\\WhatsApp Image 2024-01-10 at 08.49.41_ceed04bf.jpg";

        Image backgroundImage = new Image("file:" + imagePath);

        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );

        Background backgroundWithImage = new Background(background);

        container.setBackground(backgroundWithImage);
    }

 
}
