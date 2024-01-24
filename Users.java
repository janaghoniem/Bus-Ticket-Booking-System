/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jana
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
    public static Users currentUser;
    static Map<Integer, Users> usersMap = new HashMap<>();
    
    
    //CONSTRUCTORS 
    public Users (){}
    public Users(int ID, String Password, String Name) 
    {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
        usersMap.put(ID,this);
    }
    
    public Users(int ID, String Password, String Name, Type position) 
    {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
        this.position = position;
        usersMap.put(ID,this);
    }
    
    //Read from Binary File solution 
    private void readFromFile()
    {
        FileInputStream file = null;
        try {
            file = new FileInputStream("user_information.dat");
            while(true){
                try(ObjectInputStream ois = new ObjectInputStream(file))
                {
                    Users user = (Users) ois.readObject();
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
                
            } } catch (FileNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
    //Write to Binary File solution 
    private void writeToFile()
    {
        FileOutputStream file = null;
        try{
            file = new FileOutputStream("user_information.dat");
            try(ObjectOutputStream oos = new ObjectOutputStream(file))
            {
                for(Users u: Users.usersMap.values())
                {
                    oos.writeObject(new Users());
                }
                oos.close(); file.close();
            } catch(IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //Validation Functions 
    public boolean isValidAdmin(int input_id, String input_password) throws FileNotFoundException
    {
        Users admin = usersMap.get(input_id);
        return admin != null && admin.Password.equals(input_password);
    }
    
    public boolean isValidRec(int input_id, String input_password)
    {
        Users rec = usersMap.get(input_id);
        return rec != null && rec.Password.equals(input_password);
    }
     
    public boolean isValidGuest(int input_id, String input_password)
    {
        Users guest = usersMap.get(input_id);
        return guest != null && guest.Password.equals(input_password);
    }
    
    
    //Login Function 
    public int Login2(int ID, String password) throws FileNotFoundException {
    Users.currentUser = null; // Reset currentUser before login attempt

    if (usersMap.containsKey(ID)) {
        Users user = usersMap.get(ID);

        if (user.getPassword().equals(password)) {
            Users.currentUser = user; 
            return user.position.ordinal();
        }
    }

    return -1; 
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

    public static Map<Integer, Users> getUsersMap() {
        return usersMap;
    }

    public static void setUsersMap(Map<Integer, Users> usersMap) {
        Users.usersMap = (HashMap<Integer, Users>) usersMap;
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
