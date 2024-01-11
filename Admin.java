
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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author jana
 */
public class Admin extends Users implements manages<Admin>{
     public static VBox vehicleTable = new VBox();
    static ScrollPane scrollPane = new ScrollPane(vehicleTable);
    
    public static final List<Users> users = new ArrayList<>();
    public static final List<Admin> admins = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final int GUEST_PASSWORD_LENGTH = 5;
//  private static final String GUEST_PASSWORD_PREFIX = "22";
    private final StackPane stackPane = new StackPane();
    private static final String BACKGROUND_IMAGE_PATH = "file:///C:/Users/Electronica Care/Pictures/504795_pia00135_orig_718331.jpg";

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
public void toggleMenu(BorderPane border) {
    VBox menuVBox = (VBox) border.getLeft();

    if (menuVBox == null) {
        menuVBox = createMenuVBox();
        border.setLeft(menuVBox);
    } else {
        border.setLeft(null);
    }
}
public StackPane getStackPane() {
    return stackPane;
}

private VBox createMenuVBox() {
    VBox menuVBox = new VBox(10);

    Button manageUsersBtn = new Button("Manage Users");
    manageUsersBtn.setOnAction(event -> ManageUsers());

    Button manageTripsBtn = new Button("Manage Trips");
    manageTripsBtn.setOnAction(event -> handleManageTrips());

    Button manageVehiclesBtn = new Button("Manage Vehicles");
    manageVehiclesBtn.setOnAction(event -> {
    Scene vehicleScene = managesVehicle();
    Stage primaryStage = (Stage) manageVehiclesBtn.getScene().getWindow(); // Get the current stage
    primaryStage.setScene(vehicleScene);
});

    menuVBox.getChildren().addAll(manageUsersBtn, manageTripsBtn, manageVehiclesBtn);

    return menuVBox;
}
  private void setSceneBackground(VBox container) {
        String imagePath = "C:\\Users\\Electronica Care\\Pictures\\Screenshots\\Screenshot 2024-01-07 055407.png";

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

private void ManageUsers() {
    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    stackPane.getChildren().clear();
    stackPane.getChildren().add(manageUsersMenu);
    setSceneBackground(manageUsersMenu);
}

private Label createStyledLabel(String text) {
    Label styledLabel = new Label(text);
    styledLabel.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: white;");
    return styledLabel;
}

private VBox createManageUsersPane(Label welcomeLabel) {
    VBox manageUsersMenu = new VBox(10);
    manageUsersMenu.setAlignment(Pos.CENTER);

    TextField searchField = new TextField();
    searchField.setPromptText("Search Users");
    searchField.setStyle("-fx-font-size: 15;");
    searchField.setMaxWidth(300);

    ListView<String> listView = new ListView<>();
    listView.setMaxHeight(100);
    listView.setPrefWidth(10);
    listView.setVisible(false); // Initially set to invisible

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        boolean searchTermEntered = !newValue.trim().isEmpty();
        listView.setVisible(searchTermEntered);
        handleSearchUsers(searchField, listView);
    });

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(25);
    gridPane.setVgap(25);
    gridPane.add(createStyledButton("Add User", event -> add()), 0, 1);
    gridPane.add(createStyledButton("Remove User", event -> remove()), 1, 1);
    gridPane.add(searchField, 0, 0, 2, 1); // Span 2 columns for the search bar
    gridPane.add(createStyledButton("Add Salary To User", event -> handleAddSalary()), 0, 2);
    gridPane.add(createStyledButton("Add Bonus to User", event -> handleAddBonus()), 1, 2);
    gridPane.add(createStyledButton("Display User Reports", event -> handleDisplayUsers()), 0, 3);
    gridPane.add(createStyledButton("Display Booking Reports", event -> handleDisplayBookings()), 1, 3);
    gridPane.add(createStyledButton("Edit User", event -> edit()), 0, 4);

    manageUsersMenu.getChildren().addAll(
            welcomeLabel,
            gridPane,
            listView
    );

    return manageUsersMenu;
}


public Button createStyledButton(String text, EventHandler<ActionEvent> action) {
    Button button = new Button(text);
    button.setStyle("-fx-font-size: 18; -fx-background-color: #ca7235; -fx-text-fill: white;");
    button.setMinWidth(150);
    button.setOnAction(action);
    return button;
}


private void handleAddSalary() {
    VBox addSalaryMenu = new VBox(10);
    addSalaryMenu.setAlignment(Pos.CENTER);
    Label welcomeLabel = new Label("Add Salary");
    welcomeLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");

    TextField salaryField = new TextField();
    salaryField.setPromptText("Salary");

    Button addSalaryBtn = createStyledButton("Add Salary", event -> handleAddSalaryAction(userIdField, salaryField, addSalaryMenu, welcomeLabel));

    addSalaryMenu.getChildren().addAll(
            welcomeLabel,
            userIdField,
            salaryField,
            addSalaryBtn
    );

    updateStack(addSalaryMenu);
}

private void handleAddSalaryAction(TextField userId, TextField salary, VBox parentBox, Label welcomeLabel) {
    try {
        int id = Integer.parseInt(userId.getText());
        double salaryValue = Double.parseDouble(salary.getText());

        if (!userExists(id, "")) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            addSalary(id, salaryValue);

            showAlert("Success", "Salary added successfully.", Alert.AlertType.INFORMATION);
            parentBox.getChildren().clear();
            parentBox.getChildren().add(welcomeLabel);
        }
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid input format. Please enter valid values.", Alert.AlertType.ERROR);
    }
}

 private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

private void handleAddBonus() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Bonus");
    dialog.setHeaderText("Enter User ID");
    dialog.setContentText("User ID:");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        try {
            int userId = Integer.parseInt(result.get());
            if (userExists(userId, "")) {
                TextInputDialog bonusDialog = new TextInputDialog();
                bonusDialog.setTitle("Add Bonus");
                bonusDialog.setHeaderText("Enter Bonus");
                bonusDialog.setContentText("Bonus:");

                Optional<String> bonusResult = bonusDialog.showAndWait();
                if (bonusResult.isPresent()) {
                    try {
                        int bonus = Integer.parseInt(bonusResult.get());

                        addBonus(userId, bonus);

                        showAlert("Success", "Bonus added successfully.", Alert.AlertType.INFORMATION);
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Invalid bonus format. Please enter a valid integer.", Alert.AlertType.ERROR);
                    }
                }
            } else {
                showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid User ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
        }
    }
}


private void handleDisplayUsers() {
    StringBuilder report = new StringBuilder();
    report.append("Receptionist with the maximum no. of Bookings: ")
            .append(Booking.findReceptionistWithMostBookings())
            .append("\n");

    report.append("Guest with the maximum no. of Bookings: ")
            .append(Booking.findGuestWithMostBookings())
            .append("\n");

    report.append("Receptionist with the maximum revenue: ")
            .append(Booking.findReceptionistWithMostRevenue())
            .append("\n");

    report.append("Guest with the maximum revenue: ")
            .append(Booking.findGuestWithMostRevenue())
            .append("\n");

    showAlert("User Reports", report.toString(), Alert.AlertType.INFORMATION);
}




private void handleEditUserAction(TextField userId, TextField userName, VBox parentBox, Label welcomeLabel) {
    try {
        int id = Integer.parseInt(userId.getText());
        String name = userName.getText();

        if (!userExists(id, name)) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            TextField newUserId = new TextField();
            newUserId.setPromptText("New User ID");

            TextField newUserName = new TextField();
            newUserName.setPromptText("New User Name");

            Button saveChangesBtn = createStyledButton("Save Changes", event -> handleSaveChanges(id, name, newUserId.getText(), newUserName.getText(), parentBox, welcomeLabel));

            parentBox.getChildren().clear(); 
            parentBox.getChildren().addAll(newUserId, newUserName, saveChangesBtn);
        }
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
    }
}

private void handleSaveChanges(int oldUserId, String oldUserName, String newUserIdText, String newUserName, VBox parentBox, Label welcomeLabel) {
    try {
        int newUserId = Integer.parseInt(newUserIdText);
        if (userExists(newUserId, newUserName)) {
            showAlert("Error", "User with the new ID and name already exists.", Alert.AlertType.ERROR);
        } else {

            editUser(oldUserId, oldUserName, newUserId, newUserName);

            showAlert("Success", "User updated successfully.", Alert.AlertType.INFORMATION);
            parentBox.getChildren().clear();
            parentBox.getChildren().add(welcomeLabel);
        }
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
    }
}

 private void updateStack(Pane pane) {
    System.out.println("update stack sha8al"); 
    stackPane.getChildren().clear();
    stackPane.getChildren().add(pane);
}


private void handleDisplayBookings() {
    showAlert("Display Receptionists", "Displaying receptionists functionality is not implemented yet.", Alert.AlertType.INFORMATION);
}

private void handleManageTrips() {
    System.out.println("button el trips sha8al");
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
        saveAdminsToFile(); 
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


public void editUser(int currentId, String currentName, int newId, String newName) {
    try {
        readUsersFromFile();

        Optional<Users> userToEdit = users.stream()
                .filter(u -> u.getID() == currentId && u.getName().equals(currentName))
                .findFirst();

        if (userToEdit.isPresent()) {
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

  
public boolean findUser(int id, String name) {
    for (Users currentUser : users) {
        if (currentUser.getID() == id || currentUser.getName().equals(name)) {
            return true; 
        }
    }
    return false; 
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
    public static Scene managesVehicle()
    {
        Vehicle.listlabelsinitialization();
        vehicleTable.getChildren().add(Vehicle.listlabels);
        FlowPane vehicleSceneLayout = new FlowPane();
        vehicleSceneLayout.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
        vehicleSceneLayout.setOrientation(Orientation.VERTICAL);
        vehicleSceneLayout.setAlignment(Pos.TOP_LEFT);
        
        
        //IMAGES & ICONS
        try {
            Image icon = new Image(new FileInputStream("/home/jana/Downloads/magnifier(2).png"));
            //IMAGEVIEW FOR THE ICON
            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(30); 
            imageView.setPreserveRatio(true);  
            
            //LABELS
            Label manageVehiclesLabel = new Label();
            manageVehiclesLabel.setText("MANAGE VEHICLES");
            manageVehiclesLabel.setTextFill(Color.web("#ffb000"));
            manageVehiclesLabel.setFont(Font.font("Helvetica World", FontWeight.BOLD, 60));
                        
            //VBOX
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");             
            //scrollPane.setFitToHeight(true);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            //scrollPane.lookup(".scroll-bar:vertical").setStyle("-fx-background-color: transparent;");
            //scrollPane.lookup(".scroll-bar:vertical .thumb").setStyle("-fx-background-color: #ffb000;");
            scrollPane.setMaxHeight(550);
            vehicleTable.setSpacing(10);
            vehicleTable.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
            //vehicleTable.setMaxHeight(200);
            Vehicle.readFromFile();

            
            //TEXTFIELDS
            TextField searchBar = new TextField("");
            searchBar.setPrefHeight(30);
            searchBar.setMaxWidth(800);
            searchBar.setPrefWidth(600);
            searchBar.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-border-width: 3px");
            searchBar.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
            HBox.setHgrow(searchBar, Priority.ALWAYS);
            
            searchBar.setOnKeyTyped(eh -> 
            {
                Vehicle v = new Vehicle();
                v.search();
            });
            
            //ADD BUTTON
            Button addVehicle = new Button("Add Vehicle");
            addVehicle.setTextFill(Color.web("#0a0c26"));
            addVehicle.setStyle("-fx-background-color: #ffb000; -fx-border-color: #ffb000;");
            addVehicle.setFont(Font.font("Helvetica World", FontWeight.BOLD, 19));
            addVehicle.setPrefWidth(200);
            HBox.setHgrow(addVehicle, Priority.ALWAYS);

            
            //SEARCH BUTTON
            Button searchVehicle = new Button();
            searchVehicle.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
            searchVehicle.setGraphic(imageView);
            searchVehicle.setMaxHeight(20);
            HBox.setHgrow(searchVehicle, Priority.ALWAYS);
            
            //COMBO BOX
            ObservableList o;
            o = FXCollections.observableArrayList("General","License Plate", "Category", "Number of Seats", "Price per Seat", "Driver's Name");
            ComboBox searchBy = new ComboBox(o);
            searchBy.setStyle("-fx-background-color: #ffb000; -fx-border-color: #ffb000;");
            searchBy.setMaxWidth(200);
            searchBy.setValue("Search by...");
            HBox.setHgrow(searchBy, Priority.ALWAYS);
            
            HBox.setHgrow(horizontalLayoutBox, Priority.ALWAYS);
            
            horizontalLayoutBox.getChildren().addAll(addVehicle, searchBar, searchVehicle, searchBy);
            horizontalLayoutBox.setSpacing(10);
            horizontalLayoutBox.setMaxHeight(100);
            horizontalLayoutBox.setAlignment(Pos.BASELINE_LEFT);
           
            //ADDING ELEMENTS TO LAYOUT
            vehicleSceneLayout.getChildren().addAll(manageVehiclesLabel, horizontalLayoutBox, Vehicle.addlayout,Vehicle.listlabels ,scrollPane);
            
            //SET ALIGNMENT
            vehicleSceneLayout.setVgap(10);
            vehicleSceneLayout.setPadding(new javafx.geometry.Insets(40, 40, 40, 40));
            
            //EVENT-HANDLING
            addVehicle.setOnAction(e ->
            {
                Vehicle.addlayoutInitialization();
            });  

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        Scene vehicleScene = new Scene(vehicleSceneLayout, 1500, 800);
        return vehicleScene;
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

  @Override
public void add() {
    Label welcomeLabel = new Label();

    TextField idField = new TextField();
    idField.setPromptText("User ID");
    TextField nameField = new TextField();
    nameField.setPromptText("User Name");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("User Password");

    Button addUserBtn = new Button("Add User");
    addUserBtn.setOnAction((ActionEvent event) -> {
        String idText = idField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();

        if (idText.isEmpty() || name.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);
        } else {
            try {
                int id = Integer.parseInt(idText);
                if (userExists(id, name)) {
                    showAlert("Error", "User already exists.", Alert.AlertType.ERROR);
                } else {
                    try {
                        readUsersFromFile();

                        if (userExists(id, name)) {
                            throw new RuntimeException("User already exists");
                        } else {
                            Users newUser = new Users(id, password, name);
                            users.add(newUser);
                            saveUsersToFile();

                            System.out.println("User added successfully!");
                            updateStack(createManageUsersPane(welcomeLabel));
                        }
                    } catch (RuntimeException e) {
                        showAlert("Error", "Error adding user: " + e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
            }
        }
    });

    Button goBackBtn = createStyledButton("Go Back", event -> {
        // Go back to Manage Users page
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });

    VBox vbox = new VBox(20);
    vbox.setAlignment(Pos.CENTER);

    vbox.getChildren().addAll(idField, nameField, passwordField, addUserBtn, goBackBtn);

    stackPane.getChildren().clear();
    stackPane.getChildren().add(vbox);
}

    @Override
public void remove() {
    Label welcomeLabel = new Label();

    TextField idField = new TextField();
    idField.setPromptText("User ID");
    TextField nameField = new TextField();
    nameField.setPromptText("User Name");

    Button removeUserBtn = new Button("Remove User");
    removeUserBtn.setOnAction((ActionEvent event) -> {
        String idText = idField.getText();
        String name = nameField.getText();

        if (idText.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);
        } else {
            try {
                int id = Integer.parseInt(idText);

                readUsersFromFile(); // Moved this line to ensure data is up-to-date

                boolean found = findUser(id, name);

                if (found) {
                    // Remove the user
                    users.removeIf(currentUser -> currentUser.getID() == id && currentUser.getName().equals(name));
                    System.out.println("User removed successfully!");
                    updateStack(createManageUsersPane(welcomeLabel));
                } else {
                    showAlert("Error", "User doesn't exist", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
            } catch (RuntimeException e) {
                showAlert("Error", "Error removing user: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    });

    Button goBackBtn = createStyledButton("Go Back", event -> {
        // Go back to Manage Users page
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });

    VBox vbox = new VBox(10); // 10 is the spacing between components
    vbox.getChildren().addAll(idField, nameField, removeUserBtn, goBackBtn);
    vbox.setAlignment(Pos.CENTER);
    stackPane.getChildren().clear();
    stackPane.getChildren().add(vbox);
}

    @Override
public void edit() {
    VBox editUserMenu = new VBox(10);
    editUserMenu.setAlignment(Pos.CENTER);
    Label welcomeLabel = new Label("Edit User");
    welcomeLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");

    TextField userNameField = new TextField();
    userNameField.setPromptText("User Name");

    Button editUserBtn = createStyledButton("Edit User", event -> handleEditUserAction(userIdField, userNameField, editUserMenu, welcomeLabel));

    editUserMenu.getChildren().addAll(
            welcomeLabel,
            userIdField,
            userNameField,
            editUserBtn
    );

    updateStack(editUserMenu);
}

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void handleSearchUsers(TextField searchField, ListView<String> listView) {
    String searchTerm = searchField.getText().trim().toLowerCase();

    if (searchTerm.isEmpty()) {
        listView.getItems().clear();
        return;
    }

    List<Users> matchingUsers = searchUsers(searchTerm);

    if (matchingUsers.isEmpty()) {
        listView.getItems().setAll("No users found matching the search term.");
    } else {
        List<String> userStrings = matchingUsers.stream()
                .map(user -> "ID: " + user.getID() + ", Name: " + user.getName())
                .collect(Collectors.toList());

        listView.getItems().setAll(userStrings);
    }
}
}
