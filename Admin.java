
package project.trial;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.*;


/**
 *
 * @author jana
 */
public class Admin extends Users{
    
    public static final List<Users> users = new ArrayList<>();
    public static final List<Admin> admins = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final int GUEST_PASSWORD_LENGTH = 5;
//  private static final String GUEST_PASSWORD_PREFIX = "22";

    protected double Salary;
    protected int Bonus;

    public Admin(){
    }
    
    public Admin(int id, String password, String name) {
        super(id, password, name);
        admins.add(this);
        Salary = 0.0; 
        Bonus = 0;
        //readUsersFromFile();
        //readAdminsFromFile();
    }
    
    @Override
    public double getSalary() {
        return Salary;
    }

    @Override
    public void setSalary(double Salary) {
        this.Salary = Salary;
       
    }

    @Override
    public int getBonus() {
        return Bonus;
    }

    @Override
    public void setBonus(int Bonus) {
        this.Bonus = Bonus;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<Users> getUsers() {
        return users;
    }
    
    public static void readUsersFromFile() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("user_information.dat"))) {
        int numUsers = dis.readInt();
        for (int i = 0; i < numUsers; i++) {
            int id = dis.readInt();
            String name = dis.readUTF();
            String password = dis.readUTF(); // Read password

            Users user = new Users(id, password, name);
            users.add(user);
        }
        System.out.println("User information loaded from file.");
    } catch (FileNotFoundException e) {
        // Ignore if the file doesn't exist yet
    } catch (IOException e) {
        System.out.println("Error reading user information from file: " + e.getMessage());
    }
}

public static void saveUsersToFile() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("user_information.dat"))) {
        dos.writeInt(users.size());
        for (Users user : users) {
            dos.writeInt(user.getID());
            dos.writeUTF(user.getName());
            dos.writeUTF(user.getPassword()); // Save password
        }
        System.out.println("User information saved to file.");
        saveAdminsToFile(); // Save admins after saving users
    } catch (IOException e) {
        System.out.println("Error saving user information to file: " + e.getMessage());
    }
}

  
public boolean userExists(int id, String name) {
    for (Users u : users) {
        if (u.getID() == id && u.getName().equals(name)) {
            return true;
        }
    }
    return false;
}
public void addUser(int id, String userName, String password) {
    try {
        readUsersFromFile();
        

        if (userExists(id, userName)) {
            throw new RuntimeException("User already exists");
        } else {
            Users newUser = new Users(id, password, userName);
            users.add(newUser);

            Receptionist.storeReceptionistInfo(new Receptionist(newUser.getID(), newUser.getPassword(), newUser.getName()));
        saveUsersToFile();

            System.out.println("User added successfully!");
        }
    } catch (RuntimeException e) {
        System.out.println("Error adding user: " + e.getMessage());
    }
}
// Inside the Admin class
public void editUser(int currentId, String currentName, int newId, String newName) {
    try {
        readUsersFromFile();

        // Find the user with the current ID and name
        Optional<Users> userToEdit = users.stream()
                .filter(u -> u.getID() == currentId && u.getName().equals(currentName))
                .findFirst();

        if (userToEdit.isPresent()) {
            // Check if the new ID and name are unique
            if (!userExists(newId, newName)) {
                Users editedUser = userToEdit.get();
                editedUser.setID(newId);
                editedUser.setName(newName);

                System.out.println("User edited successfully!");
            } else {
                throw new RuntimeException("New ID or name already exists. Please choose unique values.");
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    } catch (RuntimeException e) {
        System.out.println("Error editing user: " + e.getMessage());
    }
}


   public static int generateGuestId() {
    Random random = new Random();
    // Generate a 5-digit ID with the first two digits being 22
    int guestId = Integer.parseInt("22" + String.format("%03d", random.nextInt(1000)));
    
    return guestId;
}

public static String generateGuestPassword() {
    Random random = new Random();
    StringBuilder passwordBuilder = new StringBuilder();
    for (int i = 0; i < GUEST_PASSWORD_LENGTH; i++) {
        passwordBuilder.append(random.nextInt(10));
    }

    return passwordBuilder.toString();
}

  /*public void removeUser() {
    try {
        System.out.println("Enter user ID or name to remove:");
        String inputToRemove = scanner.nextLine();

        Iterator<Users> iterator = users.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Users currentUser = iterator.next();
            if (String.valueOf(currentUser.getID()).equals(inputToRemove) || currentUser.getName().equals(inputToRemove)) {
                iterator.remove();
                System.out.println("User removed successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("User not found.");
            System.out.println("Do you want to return (return) or continue (continue)?");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "return": {
                    return; // Exit the method
                }
                case "continue": {
                }
                default: throw new RuntimeException("Invalid choice");
            }
            // Continue with the rest of the method
        }
    } catch (RuntimeException e) {
        System.out.println("Error removing user: " + e.getMessage());
    }
}
*/
public boolean findUser(int id, String name) {
    for (Users currentUser : users) {
        if (currentUser.getID() == id || currentUser.getName().equals(name)) {
            return true; // User found
        }
    }
    return false; // User not found
}

public void removeUser(int id, String name) {
            readUsersFromFile();

    
    try {
        boolean found = findUser(id, name);

        if (found) {
            // Remove the user
            users.removeIf(currentUser -> currentUser.getID() == id && currentUser.getName().equals(name));
            System.out.println("User removed successfully!");
        } else {
            System.out.println("User not found.");
            /*System.out.println("Do you want to return (return) or continue (continue)?");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "return": {
                    return; 
                }
                case "continue": {
                    // Continue with the rest of the method
                    break;
                }
                default:
                    throw new RuntimeException("Invalid choice");
            }*/
        }
    } catch (RuntimeException e) {
        System.out.println("Error removing user: " + e.getMessage());
    }
}



public List<Users> searchUsers(String searchTerm) {
    List<Users> matchingUsers = new ArrayList<>();

    for (Users user : users) {
        if (String.valueOf(user.getID()).contains(searchTerm) || user.getName().toLowerCase().contains(searchTerm)) {
            matchingUsers.add(user);
        }
    }

    return matchingUsers;
}
public void addSalary(int userId, double salary) {
                readUsersFromFile();
        super.setSalary(salary);
        Users.addSalaryToFile(userId, salary); 
    }
    
    public void addBonus(int userId, int bonus) {
                    readUsersFromFile();
        super.setBonus(bonus);
        Users.addBonusToFile(userId, bonus); 
    }

 /*   public void addSalary() {
        try {
            System.out.println("Enter user ID to add salary:");
            int idToAddSalary = scanner.nextInt();
            scanner.nextLine();

            Optional<Users> userWithSalary = users.stream()
                    .filter(u -> u.getID() == idToAddSalary)
                    .findFirst();

            if (userWithSalary.isPresent()) {
                // Check if the user is an admin
                if ("admin".equals(userWithSalary.get().getName())) {
                    // Assume salary is a double value
                    System.out.println("Enter salary:");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character left by nextDouble()

                    // Set or update the salary for the admin
                    setSalary(salary);

                    System.out.println("Salary added successfully!");
                } else {
                    throw new RuntimeException("Only admin users can have a salary.");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (RuntimeException e) {
            System.out.println("Error adding salary: " + e.getMessage());
        }
    }

 public void addBonus() {
        try {
            System.out.println("Enter user ID to add bonus:");
            int idToAddBonus = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            Optional<Users> userWithBonus = users.stream()
                    .filter(u -> u.getID() == idToAddBonus)
                    .findFirst();

            if (userWithBonus.isPresent()) {
                // Check if the user is an admin
                if ("admin".equals(userWithBonus.get().getName())) {
                    // Assume bonus is an integer value
                    System.out.println("Enter bonus:");
                    int bonuss = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character left by nextInt()

                    // Set or update the bonus for the admin
                    setBonus(bonuss);

                    System.out.println("Bonus added successfully!");
                } else {
                    throw new RuntimeException("Only admin users can have a bonus.");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (RuntimeException e) {
            System.out.println("Error adding bonus: " + e.getMessage());
        }
    }
*/
public void displayUserReports(Users currentUser) {
        System.out.println("User Details:");
        System.out.println("ID: " + currentUser.getID());
        System.out.println("Name: " + currentUser.getName());
        if ("guest".equals(currentUser.getName())) {
            System.out.println("Password: " + currentUser.getPassword());
        } else {
            // Assuming salary is a property in the user class
            System.out.println("Salary: " +getSalary());
        }
    }
public static void displayAdmins() {
        if (admins.isEmpty()) {
            System.out.println("No admins to display.");
        } else {
            System.out.println("Admins:");
            for (Admin currentAdmin : admins) {
                System.out.println("ID: " + currentAdmin.getID() + ", Name: " + currentAdmin.getName());
            }
        }
    }


public static void readAdminsFromFile() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("admin_information.dat"))) {
        int numAdmins = dis.readInt();
        for (int i = 0; i < numAdmins; i++) {
            int id = dis.readInt();
            String name = dis.readUTF();
            String password = dis.readUTF(); // Read password

            admins.add(new Admin(id, password, name));
        }
        System.out.println("Admin information loaded from file.");
    } catch (FileNotFoundException e) {
        // Ignore if the file doesn't exist yet
    } catch (IOException e) {
        System.out.println("Error reading admin information from file: " + e.getMessage());
    }
}

public static void saveAdminsToFile() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("admin_information.dat"))) {
        dos.writeInt(admins.size());
        for (Admin admin : admins) {
            dos.writeInt(admin.getID());
            dos.writeUTF(admin.getName());
            dos.writeUTF(admin.getPassword()); // Save password
        }
        System.out.println("Admin information saved to file.");
    } catch (IOException e) {
        System.out.println("Error saving admin information to file: " + e.getMessage());
    }
}


public static void manageUsers() {
    boolean exitProgram = false;
    Scanner s = new Scanner(System.in);

    Admin a = new Admin();
        readUsersFromFile();
        readAdminsFromFile();
    while (!exitProgram) {
        System.out.println("Choose an option:");
            System.out.println("1. Add Admin");
            System.out.println("2. Add Receptionist");
            System.out.println("3. Remove User");
            System.out.println("4. Search Users");
            System.out.println("5. Add salary for an employee");
            System.out.println("6. Add bonus to an employee");
            System.out.println("7. Display User Reports");
            System.out.println("8. Display Admins"); // New option to display admins
            System.out.println("9. Exit");
            System.out.println("10. Return to Main Menu");

            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("Enter admin ID:");
                    int adminId = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter admin password:");
                    String adminPassword = s.nextLine();
                    System.out.println("Enter admin name:");
                    String adminName = s.nextLine();

                    Admin newAdmin = new Admin(adminId, adminPassword, adminName);
                    admins.add(newAdmin);
                    //newAdmin.addUser();
                    break;
                }
                case 2: {
                    /*System.out.println("Enter receptionist ID:");
                    int receptionistId = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter receptionist password:");
                    String receptionistPassword = s.nextLine();
                    System.out.println("Enter receptionist password:");
                    String receptionistName = s.nextLine();

                    // Set name to "receptionist" for receptionist users
                    a = new admin(receptionistId, receptionistPassword, receptionistName);*/
                   // a.addUser();
                    break;
                }
                case 3:
                    //a.removeUser();
                    break;
                case 4:
//                    a.searchUsers();
                    break;
                case 5: {
                    if ("admin".equals(a.getName())) {
                        System.out.println("Admins cannot have a salary.");
                    } else {
//                        a.addSalary();
                    }
                    break;
                }
                case 6:
                   // a.addBonus();
                    break;
                case 7: {
                    // Display user details
                    System.out.println("Enter user ID:");
                    int idToDisplay = s.nextInt();
                    s.nextLine(); // Consume the newline character left by nextInt()

                    Optional<Users> userToDisplay = users.stream()
                            .filter(u -> u.getID() == idToDisplay)
                            .findFirst();

                    if (userToDisplay.isPresent()) {
                        a.displayUserReports(userToDisplay.get());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                }
                case 8: {
                    Admin.displayAdmins(); // Call the new method to display admins
                    break;
                }
                case 9: {
                    a.saveUsersToFile();
                    //receptionist.displayReceptionists();
                    exitProgram = true;
                    break;
                }
                case 10: {
                    // Return to the main menu
                    exitProgram = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }}
}
/*case 1 -> {
                System.out.println("Choose user type:");
                System.out.println("1. Regular User");
                System.out.println("2. Guest User");

                int userType = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left by nextInt()

                switch (userType) {
                    case 1 -> addUser();
                    case 2 -> {
                        // Add a guest user
                        int guestId = generateGuestId();
                        String guestPassword = generateGuestPassword();
                        String guestUsername = "guest";

                        users.add(new user(guestId, guestPassword, guestUsername));
                        System.out.println("Guest User added successfully!");
                        System.out.println("Guest User Information:");
                        System.out.println("ID: " + guestId);
                        System.out.println("Password: " + guestPassword);
                        System.out.println("Name: " + guestUsername);
                    }
                    default -> System.out.println("Invalid user type. Please try again.");
                }
            }*/
    
    public static void managesVehicle()
    {
        Vehicle v = new Vehicle();
        boolean continueEditing = true;
        String con;
        while(continueEditing)
        {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do: ");
        System.out.println("1. Add vehicle");
        System.out.println("2. Remove vehicle");
        System.out.println("3. Edit vehicle");
        System.out.println("4. Search by field");   
        System.out.println("5. Display available vehicles");
        switch (scanner.nextInt())
        {
                case(1): 
                { 
                    v.add();
                    break;
                }
                case(2):
                {
                                     
                    v.remove();
                    break;
                }
                case(3):
                {
                    v.edit();
                    break;
                }
                case(4):
                {
                    v.search();
                    break;
                }
                case (5):
                {
                    v.displayVehicles();
                    break;
                }
            }
            Vehicle.updateFile();
            System.out.println("Would you like to return to manageVehicles main menu?");
            con = scanner.next();
            if(!con.equalsIgnoreCase("yes") && !con.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you want to return to manageVehicles main menu or 'no' if you want to exit");
            }
            else if(con.equalsIgnoreCase("no"))
            {
                continueEditing = false;
            }
        } 
    }
    
    public static void managesTrips() throws IOException
    {
        Trips t = new Trips();
        boolean continueEditing = true;
        String con;
        while(continueEditing)
        {
        Scanner n = new Scanner(System.in);
        System.out.println("What would you like to do: ");
        System.out.println("1. Add trip");
        System.out.println("2. Remove trip");
        System.out.println("3. Edit trip");
        System.out.println("4. Search by field");   
        System.out.println("5. Display available trip");
        switch (n.nextInt())
        {
                case(1): { 
                    t.add();
                    break;
                }
                case(2): {
                                     
                    t.remove();
                    break;
                }
                case(3): {
                    t.edit();
                    break;
                }
                case(4): {
                    t.search();
                    break;
                }
                case (5): {
                    Trips.ViewTrips();
                    break;
                }
            }
            Trips.writeToFile();
            System.out.println("Would you like to return to manageTrips main menu?");
            con = n.next();
            if(!con.equalsIgnoreCase("yes") && !con.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input. Please enter 'yes' if you want to return to manageTrips main menu or 'no' if you want to exit");
            }
            else if(con.equalsIgnoreCase("no"))
            {
                continueEditing = false;
            }
        }    
    }
}
