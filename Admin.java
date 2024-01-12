
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
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


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
    private final StackPane stackPane = new StackPane();
    private static final String BACKGROUND_IMAGE_PATH = "file:///C:/Users/Electronica Care/Pictures/504795_pia00135_orig_718331.jpg";
    private VBox selectedMenuItem; // Track the selected menu item

    protected double Salary;
    protected int Bonus;
private static final String BACKGROUND_COLOR = "#34495e"; 

    public StackPane getStackPane() {
        return stackPane;
    }

private VBox createMenuItem(String itemName) {
        Label label = createStyledLabel(itemName);
        VBox menuItem = new VBox(label);
        menuItem.setAlignment(Pos.CENTER);
        addHoverAnimation(label);
        addSelectionAnimation(menuItem);

        menuItem.setOnMouseClicked(event -> handleMenuItemClick(menuItem, itemName));

        return menuItem;
    }

public VBox createSidebar() {
    VBox sidebar = new VBox(15); // Increased spacing between items

    // User Info
   String imagePath = "C:/Users/Electronica Care/Pictures/Screenshots/Screenshot 2024-01-11 153015.png";
    Image profileImage = new Image("file:" + imagePath);

    // User Info
    Circle profileIcon = new Circle(40);
    profileIcon.setFill(new ImagePattern(profileImage)); 
    Label userInfoLabel = createStyledLabel("Admin123");
    VBox userInfoBox = new VBox(profileIcon, userInfoLabel); 
    userInfoBox.setAlignment(Pos.CENTER);
    userInfoBox.setSpacing(5);
    sidebar.getChildren().add(userInfoBox);
    Separator separator = new Separator();
    separator.setMaxWidth(Double.MAX_VALUE);

    VBox manageUsersItem = createMenuItem("Manage Users");
        VBox manageTripsItem = createMenuItem("Manage Trips");
        VBox manageVehiclesItem = createMenuItem("Manage Vehicles");

        sidebar.setPadding(new Insets(150, 0, 0, 0));

        sidebar.getChildren().addAll(separator, manageUsersItem, manageTripsItem, manageVehiclesItem);
        sidebar.setStyle("-fx-background-color: " + BACKGROUND_COLOR);
        sidebar.setMinWidth(150);

        return sidebar;
}

private Label createStyledLabel(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
    return label;
}

private void handleMenuItemClick(VBox selectedItem, String itemName) {
        if (selectedMenuItem != null) {
            selectedMenuItem.setBorder(null);
        }

        selectedMenuItem = selectedItem;
        Border border = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        selectedMenuItem.setBorder(border);

        if (itemName.equals("Manage Users")) {
            ManageUsers();
        } else if (itemName.equals("Manage Trips")) {
            
        } else if (itemName.equals("Manage Vehicles")) {
            // logic for managing vehicles here
            System.out.println("Manage Vehicles clicked");
        }
    }

    private void addHoverAnimation(Label label) {
        label.setOnMouseEntered(event -> {
            label.setTextFill(Color.WHITE);
            label.setEffect(new DropShadow(20, Color.WHITE));
        });

       /* label.setOnMouseExited(event -> {
            label.setTextFill(Color.BLACK);
            label.setEffect(null);
        });*/
    }

    private void addSelectionAnimation(VBox menuItem) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), menuItem);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        menuItem.setOnMousePressed(event -> scaleTransition.play());

     menuItem.setOnMouseReleased(event -> {
    scaleTransition.setToX(1.0);
    scaleTransition.setToY(1.0);
    scaleTransition.play();
});
    }


    public Admin(){
                readUsersFromFile();

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
        readUsersFromFile(); 

    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    stackPane.getChildren().clear();
    stackPane.getChildren().add(manageUsersMenu);
    setSceneBackground(manageUsersMenu);
}

public Label createtheStyledLabel(String text) {
    Label styledLabel = new Label(text);
    styledLabel.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: white;");
    return styledLabel;
}

private VBox createManageUsersPane(Label welcomeLabel) {
    VBox manageUsersMenu = new VBox(20); // Increased vertical spacing
    manageUsersMenu.setAlignment(Pos.TOP_CENTER); // Set alignment to TOP_CENTER
    manageUsersMenu.setPadding(new Insets(30, 0, 0, 0)); // Added padding to the top

    TextField searchField = new TextField();
    searchField.setPromptText("Search Users");
    searchField.setStyle("-fx-font-size: 15;");
    searchField.setMaxWidth(300);
    searchField.setMinWidth(300);

    HBox searchBarBox = new HBox(searchField);
    searchBarBox.setAlignment(Pos.CENTER);

    ListView<String> listView = new ListView<>();
    listView.setMaxHeight(100);
    listView.setMinWidth(300);
    listView.setVisible(false);
    listView.setStyle("-fx-background-color: transparent; -fx-border-color: #ca7235;");

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        boolean searchTermEntered = !newValue.trim().isEmpty();
        listView.setVisible(searchTermEntered);
        search();
    });

    HBox buttonBox = new HBox(
            createStyledButton("Add User", event -> add()),
            createStyledButton("Remove User", event -> remove()),
            createStyledButton("Add Salary To User", event -> handleAddSalary()),
            createStyledButton("Add Bonus to User", event -> handleAddBonus()),
            createStyledButton("Display User Reports", event -> handleDisplayUsers()),
            createStyledButton("Display Booking Reports", event -> handleDisplayBookings()),
            createStyledButton("Edit User", event -> {
                edit();
                // Add a close button to return to the normal manage users view
                Button closeButton = createStyledButton("Close", closeEvent -> {
                    stackPane.getChildren().clear();
                    stackPane.getChildren().add(manageUsersMenu);
                    setSceneBackground(manageUsersMenu);
                });
                HBox closeBox = new HBox(closeButton);
                closeBox.setAlignment(Pos.CENTER);
                closeBox.setPadding(new Insets(10, 0, 0, 0));
                manageUsersMenu.getChildren().addAll(closeBox);
            })
    );
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setSpacing(10);

    manageUsersMenu.getChildren().addAll(
            welcomeLabel,
            searchBarBox,
            buttonBox,
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
    HBox addSalaryBox = new HBox(10);
    addSalaryBox.setAlignment(Pos.CENTER);

    Label welcomeLabel = createStyledLabel("Add Salary");
    welcomeLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");

    TextField salaryField = new TextField();
    salaryField.setPromptText("Salary");

    Button addSalaryBtn = createStyledButton("Add Salary", event -> handleAddSalaryAction(userIdField, salaryField, addSalaryBox, welcomeLabel));

    addSalaryBox.getChildren().addAll(
            userIdField,
            salaryField,
            addSalaryBtn
    );

    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.getChildren().add(addSalaryBox);

    updateStack(manageUsersMenu);
}

private void handleAddSalaryAction(TextField userId, TextField salary, HBox parentBox, Label welcomeLabel) {
    try {
        readUsersFromFile(); // Make sure to read users from file before checking existence
        int id = Integer.parseInt(userId.getText());
        double salaryValue = Double.parseDouble(salary.getText());

        if (userExists(id, "")) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            addSalary(id, salaryValue);
            saveUsersToFile(); // Save the changes to the file

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
    HBox addBonusBox = new HBox(10);
    addBonusBox.setAlignment(Pos.CENTER);

    Label welcomeLabel = createStyledLabel("Add Bonus");
    welcomeLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");

    TextField bonusField = new TextField();
    bonusField.setPromptText("Bonus");

    Button addBonusBtn = createStyledButton("Add Bonus", event -> handleAddBonusAction(userIdField, bonusField, addBonusBox, welcomeLabel));
    addBonusBox.getChildren().addAll(
            userIdField,
            bonusField,
            addBonusBtn
    );

    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.getChildren().add(addBonusBox);

    updateStack(manageUsersMenu);
}

private void handleAddBonusAction(TextField userId, TextField bonus, HBox parentBox, Label welcomeLabel) {
    try {
        readUsersFromFile(); 
        int id = Integer.parseInt(userId.getText());
        int bonusValue = Integer.parseInt(bonus.getText());

        if (userExists(id, "")) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            addBonus(id, bonusValue);
            saveUsersToFile(); 

            showAlert("Success", "Bonus added successfully.", Alert.AlertType.INFORMATION);
            parentBox.getChildren().clear();
            parentBox.getChildren().add(welcomeLabel);
        }
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid input format. Please enter valid values.", Alert.AlertType.ERROR);
    }
}


private void handleDisplayUsers() {
    Label receptionistBookingsLabel = createStyledLabel("Receptionist with the maximum no. of Bookings: " +
            Booking.findReceptionistWithMostBookings());

    Label guestBookingsLabel = createStyledLabel("Guest with the maximum no. of Bookings: " +
            Booking.findGuestWithMostBookings());

    Label receptionistRevenueLabel = createStyledLabel("Receptionist with the maximum revenue: " +
            Booking.findReceptionistWithMostRevenue());

    Label guestRevenueLabel = createStyledLabel("Guest with the maximum revenue: " +
            Booking.findGuestWithMostRevenue());

    // Assuming that the existing VBox in the Manage Users pane is accessible
    VBox manageUsersPane = (VBox) stackPane.getChildren().get(0);
    
    // Add the labels below the existing buttons
    manageUsersPane.getChildren().addAll(
            receptionistBookingsLabel,
            guestBookingsLabel,
            receptionistRevenueLabel,
            guestRevenueLabel
    );
}





private void updateStack(Pane pane) {
    System.out.println("update stack sha8al"); 

    VBox stackContainer = new VBox();
    setSceneBackground(stackContainer); // Set the background image

    stackContainer.getChildren().add(pane);
    stackPane.getChildren().clear();
    stackPane.getChildren().add(stackContainer);
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



   public static int generateGuestId() {
    Random random = new Random();
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
        FlowPane vehicleSceneLayout = new FlowPane();
        vehicleSceneLayout.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
        vehicleSceneLayout.setOrientation(Orientation.VERTICAL);
        vehicleSceneLayout.setAlignment(Pos.TOP_LEFT);
        
        
        //IMAGES & ICONS
        try {
            Image icon = new Image(new FileInputStream("C:/Users/Electronica Care/Pictures/504795_pia00135_orig_718331.jpg"));
// IMAGEVIEW FOR THE ICON
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
            scrollPane.setFitToHeight(true);
            vehicleTable.setSpacing(10);
            vehicleTable.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
            Vehicle.readFromFile();

            
            //TEXTFIELDS
            TextField searchBar = new TextField("");
            searchBar.setPrefHeight(30);
            searchBar.setMaxWidth(800);
            searchBar.setPrefWidth(600);
            searchBar.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-border-width: 3px");
            searchBar.setFont(Font.font("Helvetica World", FontWeight.BOLD, 15));
            HBox.setHgrow(searchBar, Priority.ALWAYS);
            
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
            o = FXCollections.observableArrayList("License Plate", "Category", "Number of Seats", "Price per Seat", "Driver's Name");
            ComboBox searchBy = new ComboBox(o);
            searchBy.setStyle("-fx-background-color: #ffb000; -fx-border-color: #ffb000;");
            searchBy.setMaxWidth(200);
            searchBy.setValue("Search by...");
            HBox.setHgrow(searchBy, Priority.ALWAYS);
            
            HBox horizontalLayoutBox = new HBox();
            HBox.setHgrow(horizontalLayoutBox, Priority.ALWAYS);
            
            horizontalLayoutBox.getChildren().addAll(addVehicle, searchBar, searchVehicle, searchBy);
            horizontalLayoutBox.setSpacing(10);
            horizontalLayoutBox.setMaxHeight(100);
            horizontalLayoutBox.setAlignment(Pos.BASELINE_LEFT);
           
            //ANIMATION (TRANSLATION??) OF ADD BUTTON SLIDING PANEL


                        
            //ADDING ELEMENTS TO LAYOUT
            vehicleSceneLayout.getChildren().addAll(manageVehiclesLabel, horizontalLayoutBox, Vehicle.addlayout, scrollPane);
            
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
        Scene vehicleScene = new Scene(vehicleSceneLayout, 800, 800);
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
    VBox addPane = new VBox();
    
    setSceneBackground(addPane);

    Label welcomeLabel = new Label();

    TextField idField = new TextField();
    idField.setPromptText("User ID");
    idField.setStyle("-fx-font-size: 12; -fx-background-color: transparent; -fx-border-color: #ca7235;");
    TextField nameField = new TextField();
    nameField.setPromptText("User Name");
    nameField.setStyle("-fx-font-size: 12; -fx-background-color: transparent; -fx-border-color: #ca7235;");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("User Password");
    passwordField.setStyle("-fx-font-size: 12; -fx-background-color: transparent; -fx-border-color: #ca7235;");

    Button addUserBtn = new Button("Add User");
    addUserBtn.setStyle("-fx-font-size: 12; -fx-background-color: #ca7235; -fx-text-fill: white;");
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
                            idField.clear();
                            nameField.clear();
                            passwordField.clear();
                            updateListView();
                        }
                    } catch (RuntimeException e) {
                        showAlert("Error", "Error adding user: " + e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
            }
        }
        
        setSceneBackground(addPane);
    });

    Button goBackBtn = createStyledButton("Go Back", event -> {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });
    goBackBtn.setStyle("-fx-font-size: 12; -fx-background-color: #ca7235; -fx-text-fill: white;");
    goBackBtn.setAlignment(Pos.TOP_LEFT);

    ListView<String> usersListView = new ListView<>();
    usersListView.setMaxHeight(30); 
    usersListView.setMinWidth(300);
    usersListView.setVisible(true);
    usersListView.setStyle("-fx-background-color: transparent; -fx-border-color: #ca7235;");

    VBox vbox = new VBox(10); // Adjusted spacing
    vbox.setAlignment(Pos.CENTER);

    vbox.getChildren().addAll(goBackBtn, idField, nameField, passwordField, addUserBtn, usersListView);

    stackPane.getChildren().clear();
    stackPane.getChildren().add(vbox);

    updateListView();
}


private void updateListView() {
    ListView<String> usersListView = new ListView<>();
    for (Users user : users) {
        usersListView.getItems().add("ID: " + user.getID() + " | Name: " + user.getName());
    }

    VBox currentVBox = (VBox) stackPane.getChildren().get(0);

    currentVBox.getChildren().removeIf(node -> node instanceof ListView);

    currentVBox.getChildren().add(usersListView);
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
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });

    HBox removeUserBox = new HBox(10); 
    removeUserBox.getChildren().addAll(idField, nameField, removeUserBtn, goBackBtn);
    removeUserBox.setAlignment(Pos.CENTER);

   
    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.getChildren().addAll(removeUserBox);

    updateStack(manageUsersMenu);
}

 @Override
public void edit() {
    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));

    Label editLabel = createStyledLabel("Edit User");
    editLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");

    TextField userNameField = new TextField();
    userNameField.setPromptText("User Name");

    VBox editContainer = new VBox(); // Container for edit items
    Button editUserBtn = createStyledButton("Edit User", event -> {
        try {
            int id = Integer.parseInt(userIdField.getText());
            String name = userNameField.getText();

            if (!userExists(id, name)) {
                showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
            } else {
                TextField newUserId = new TextField();
                newUserId.setPromptText("New User ID");

                TextField newUserName = new TextField();
                newUserName.setPromptText("New User Name");

                Button saveChangesBtn = createStyledButton("Save Changes", saveEvent -> {
                    try {
                        int newUserIdValue = Integer.parseInt(newUserId.getText());
                        if (userExists(newUserIdValue, newUserName.getText())) {
                            showAlert("Error", "User with the new ID and name already exists.", Alert.AlertType.ERROR);
                        } else {
                            try {
                                readUsersFromFile();

                                Optional<Users> userToEdit = users.stream()
                                        .filter(u -> u.getID() == id && u.getName().equals(name))
                                        .findFirst();

                                if (userToEdit.isPresent()) {
                                    if (!userExists(newUserIdValue, newUserName.getText())) {
                                        Users editedUser = userToEdit.get();
                                        editedUser.setID(newUserIdValue);
                                        editedUser.setName(newUserName.getText());

                                        saveUsersToFile(); // Save the modified users list to file
                                        showAlert("Success", "User updated successfully.", Alert.AlertType.INFORMATION);
                                        editContainer.getChildren().clear();
                                        editContainer.getChildren().add(editLabel);
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
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
                    }
                });

                GridPane gridPane = new GridPane();
                gridPane.setHgap(10); // Set horizontal gap between elements
                gridPane.setVgap(10); // Set vertical gap between elements

                // Add elements to the grid
                gridPane.add(new Label("New User ID:"), 0, 0);
                gridPane.add(newUserId, 1, 0);
                gridPane.add(new Label("New User Name:"), 0, 1);
                gridPane.add(newUserName, 1, 1);
                gridPane.add(saveChangesBtn, 0, 2, 2, 1); // Span the button across two columns

                // Clear the editContainer and add the gridPane
                editContainer.getChildren().clear();
                editContainer.getChildren().add(gridPane);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid ID format. Please enter a valid integer.", Alert.AlertType.ERROR);
        }
    });

    Button closeButton = createStyledButton("Close", closeEvent -> {
        ManageUsers();
    });

    HBox editUserBox = new HBox(
            userIdField,
            userNameField,
            editUserBtn
    );

    VBox combinedEditBox = new VBox(); // Container for combined edit and editUserAction items
    combinedEditBox.getChildren().addAll(
            editUserBox,
            editContainer // This container will hold items added by handleEditUserAction
    );

    manageUsersMenu.getChildren().addAll(
            editLabel,
            combinedEditBox,
            closeButton
    );

    updateStack(manageUsersMenu);
}

@Override
public void search() {
    TextField searchField = new TextField(); 
    ListView<String> listView = new ListView<>();

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

    listView.setVisible(true);
}


}
