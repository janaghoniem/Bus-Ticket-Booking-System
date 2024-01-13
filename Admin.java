
package project.trial;
import com.sun.javafx.logging.PlatformLogger.Level;
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
import java.lang.System.Logger;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.shape.Rectangle;
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
    //private static final int GUEST_PASSWORD_LENGTH = 5;
    private final StackPane stackPane = new StackPane();
    //private static final String BACKGROUND_IMAGE_PATH = "file:///C:/Users/Electronica Care/Pictures/504795_pia00135_orig_718331.jpg";
 //   private VBox selectedMenuItem; // Track the selected menu item
private TextField searchField;  // Declare as instance variable
private ListView<String> listView;  // Declare as instance variable
    private static final String ACCOUNTING_FILE_PATH = "accounting.dat";

    protected double Salary;
    protected int Bonus;

    public StackPane getStackPane() {
        return stackPane;
    }
 public Scene manageUsers() {
        VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageUsersMenu);
        setSceneBackground(manageUsersMenu);

        Scene manageUsersScene = new Scene(stackPane, 600, 400);
        manageUsersScene.setFill(Color.rgb(135, 206, 250)); // Set the background color of the scene
        stackPane.setStyle("-fx-background-color: #358dca;"); // Set the background color of the StackPane

        return manageUsersScene;
    }
 
 
private Label createStyledLabel(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-size: 50; -fx-text-fill: white; -fx-font-family: 'Helvetica World'; -fx-font-weight: bold;");
    return label;
}


public Scene HomePageScene()
  {
      BorderPane pane = new BorderPane();
      pane.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(2).jpg'); -fx-background-size: cover;");
      
      Label welcomelbl = new Label("Welcome " + this.Name + ".");
      welcomelbl.setTextFill(Color.web("#ffb000"));
      welcomelbl.setFont(Font.font("Montserrat ", FontWeight.BOLD, 60));
      HBox welcomebox = new HBox(welcomelbl);
      welcomebox.setAlignment(Pos.CENTER);

      
      try {
            ImageView userimg = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/group.png")));
            userimg.setFitHeight(200);
            userimg.setFitWidth(200);            
            Label userslbl = new Label("Manage Users");
            userslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
            VBox users = new VBox(userimg, userslbl);
            users.setAlignment(Pos.CENTER);
            users.setSpacing(15);
            StackPane manageUsers = new StackPane();
            Rectangle rectangle1 = new Rectangle(600, 600);
            rectangle1.setArcWidth(20); 
            rectangle1.setArcHeight(20); 
            rectangle1.setFill(Color.rgb(10,12,38, 0.5));
            rectangle1.setStyle("-fx-border-radius: 5px;");
            manageUsers.getChildren().addAll(rectangle1, users);
            
            manageUsers.setOnMouseEntered(eh -> {
                manageUsers.setCursor(Cursor.HAND);
                rectangle1.setFill(Color.rgb(87, 90, 131, 0.5));
                userslbl.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    userimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/group(1).png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                // stackpane slightly goes up when hovered on or clicked, and back down when exited
                upUponHover(manageUsers);
            });
            
            manageUsers.setOnMouseExited(eh ->
            {
                manageUsers.setCursor(Cursor.DEFAULT);
                rectangle1.setFill(Color.rgb(10,12,38, 0.5));
                userslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    userimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/group.png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                downUponExit(manageUsers);
            });
            
            ImageView busimg = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/bus.png")));
            busimg.setFitHeight(200);
            busimg.setFitWidth(200);    
            Label buseslbl = new Label("Manage Vehicles");
            buseslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
            VBox buses = new VBox(busimg, buseslbl);
            buses.setAlignment(Pos.CENTER);
            buses.setSpacing(15);
            StackPane manageVehicles = new StackPane();
            Rectangle rectangle2 = new Rectangle(600, 600);
            rectangle2.setArcWidth(20); 
            rectangle2.setArcHeight(20); 
            rectangle2.setFill(Color.rgb(10,12,38, 0.5));
            rectangle2.setStyle("-fx-border-radius: 5px;");
            manageVehicles.getChildren().addAll(rectangle2, buses);
            manageVehicles.setStyle("-fx-cursor: hand");
            
            manageVehicles.setOnMouseEntered(eh -> {
                manageVehicles.setCursor(Cursor.HAND);
                rectangle2.setFill(Color.rgb(87, 90, 131, 0.5));
                buseslbl.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    busimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/bus(2).png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                // stackpane slightly goes up when hovered on or clicked, and back down when exited
                upUponHover(manageVehicles);
            });
            
            manageVehicles.setOnMouseExited(eh ->
            {
                manageVehicles.setCursor(Cursor.DEFAULT);
                rectangle2.setFill(Color.rgb(10,12,38, 0.5));
                buseslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    busimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/bus.png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                downUponExit(manageVehicles);
            });
            
            manageVehicles.setOnMouseClicked(eh ->
            {
                Scene manageV = managesVehicle();
                Stage primaryStage = (Stage) manageVehicles.getScene().getWindow();
                primaryStage.setScene(manageV);
                primaryStage.setTitle("Bus-Ticket Booking System - Manage Vehicles Page");
                primaryStage.sizeToScene();
            });
            
            
            ImageView tripsimg = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/map.png")));
            tripsimg.setFitHeight(200);
            tripsimg.setFitWidth(200); 
            Label tripslbl = new Label("Manage Trips");
            tripslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
            VBox trips = new VBox(tripsimg, tripslbl);
            trips.setAlignment(Pos.CENTER);
            trips.setSpacing(15);
            StackPane manageTrips = new StackPane();
            Rectangle rectangle3 = new Rectangle(600, 600);
            rectangle3.setArcWidth(20); 
            rectangle3.setArcHeight(20); 
            rectangle3.setFill(Color.rgb(10,12,38, 0.5));
            rectangle3.setStyle("-fx-border-radius: 5px;");
            manageTrips.getChildren().addAll(rectangle3, trips);
            
            manageTrips.setOnMouseEntered(eh -> {
                manageTrips.setCursor(Cursor.HAND);
                rectangle3.setFill(Color.rgb(87, 90, 131, 0.5));
                tripslbl.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    tripsimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/map(1).png")));
                } catch (FileNotFoundException ex) {
                  //  Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                // stackpane slightly goes up when hovered on or clicked, and back down when exited
                upUponHover(manageTrips);
            });
            
            manageTrips.setOnMouseExited(eh ->
            {
                manageTrips.setCursor(Cursor.DEFAULT);
                rectangle3.setFill(Color.rgb(10,12,38, 0.5));
                tripslbl.setStyle("-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);");
                try {
                    tripsimg.setImage(new Image(new FileInputStream("/home/jana/Downloads/map.png")));
                } catch (FileNotFoundException ex) {
                  //  Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                downUponExit(manageTrips);
            });
            
            HBox box = new HBox(20, manageUsers, manageVehicles, manageTrips);
            box.setAlignment(Pos.CENTER);
            Insets in = new Insets(50);
            pane.setPadding(in);
            pane.setCenter(box);
            pane.setTop(welcomebox);
            
      } catch (FileNotFoundException ex) {
            System.out.println(ex);
      }
      
     
      Scene homepage = new Scene(pane, 1200, 800);
      return homepage;
  }
  
  private void upUponHover(StackPane pane)
  {
    TranslateTransition up = new TranslateTransition(Duration.millis(200), pane);
    up.setByY(-10);
    up.play();
  }
  
  private void downUponExit(StackPane pane)
  {
    TranslateTransition down = new TranslateTransition(Duration.millis(200), pane);
    down.setByY(10);
    down.play();
  }
    public Admin(){

    }
    
     public Admin(int id, String password, String name) {
        super(id, password, name, Type.ADMIN);
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
public Scene createManageUsersScene() {
        VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageUsersMenu);
        setSceneBackground(manageUsersMenu);

        Scene manageUsersScene = new Scene(stackPane, 600, 400);
        manageUsersScene.setFill(Color.rgb(135, 206, 250)); // Set the background color of the scene
        stackPane.setStyle("-fx-background-color: #358dca;"); // Set the background color of the StackPane

        return manageUsersScene;
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

public void ManageUsers() {

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
    VBox manageUsersMenu = new VBox(20);
    manageUsersMenu.setAlignment(Pos.TOP_LEFT);
    manageUsersMenu.setPadding(new Insets(30, 0, 0, 30));
HBox welcomeBox = new HBox(welcomeLabel);
    welcomeBox.setAlignment(Pos.TOP_LEFT);
    welcomeBox.setPadding(new Insets(0, 0, 20, 0));  // Add padding to separate from searchField
    manageUsersMenu.getChildren().add(welcomeBox);

    searchField = new TextField();  
    searchField.setPromptText("Search Users");
    searchField.setStyle("-fx-font-size: 15; -fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-border-width: 3px");
    searchField.setMaxWidth(500);
    searchField.setMinWidth(500);

    listView = new ListView<>();  
    listView.setMaxHeight(100);
    listView.setMinWidth(300);
    listView.setVisible(false);
    listView.setStyle("-fx-background-color: transparent; -fx-border-color: #ca7235;");

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
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
            searchField,
            buttonBox,
            listView
    );

    return manageUsersMenu;
}
public Button createStyledButton(String text, EventHandler<ActionEvent> action) {
    Button button = new Button(text);
    button.setFont(Font.font("Helvetica World", FontWeight.BOLD, 19));
    button.setPrefWidth(200);
    button.setStyle("-fx-background-color: #ffb000; -fx-border-color: #ffb000; -fx-text-fill: #0a0c26;");
    button.setOnAction(action);
    HBox.setHgrow(button, Priority.ALWAYS);
    return button;
}


private void handleAddSalary() {
    HBox addSalaryBox = new HBox(10);
    addSalaryBox.setAlignment(Pos.CENTER);

    Label welcomeLabel = createStyledLabel("Add Salary");
    welcomeLabel.setStyle("-fx-font-size: 24;");

    TextField userIdField = createStyledTextField("User ID");
    TextField salaryField = createStyledTextField("Salary");

    Button addSalaryBtn = new Button("Add Salary");
    addSalaryBtn.setOnAction(event -> handleAddSalaryAction(userIdField, salaryField, addSalaryBox, welcomeLabel));

    Button closeButton = new Button("Close");
    closeButton.setOnAction(closeEvent -> ManageUsers());

    addSalaryBox.getChildren().addAll(
            userIdField,
            salaryField,
            addSalaryBtn,
            closeButton
    );

    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.getChildren().add(addSalaryBox);

    updateStack(manageUsersMenu);
}

private void saveAccountingInformation(int userId, double salary, int bonus) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ACCOUNTING_FILE_PATH, true))) {
            dos.writeInt(userId);
            dos.writeDouble(salary);
            dos.writeInt(bonus);
            System.out.println("Accounting information saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving accounting information to file: " + e.getMessage());
        }
    }

private void handleAddSalaryAction(TextField userId, TextField salary, HBox parentBox, Label welcomeLabel) {
    try {
        readUsersFromFile();
        int id = Integer.parseInt(userId.getText());
        double salaryValue = Double.parseDouble(salary.getText());

        if (!userExists(id, "")) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            addSalary(id, salaryValue);
            saveUsersToFile();

            // Save accounting information only if the user exists
            saveAccountingInformation(id, salaryValue, 0);

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

    TextField userIdField = createStyledTextField("User ID");
    TextField bonusField = createStyledTextField("Bonus");

    Button addBonusBtn = new Button("Add Bonus");
    addBonusBtn.setOnAction(event -> handleAddBonusAction(userIdField, bonusField, addBonusBox, welcomeLabel));

    Button closeButton = new Button("Close");
    closeButton.setOnAction(closeEvent -> ManageUsers());

    addBonusBox.getChildren().addAll(
            userIdField,
            bonusField,
            addBonusBtn,
            closeButton
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

        if (!userExists(id, "")) {
            showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
        } else {
            addBonus(id, bonusValue);
            saveUsersToFile();

            // Save accounting information only if the user exists
            saveAccountingInformation(id, 0, bonusValue);

            showAlert("Success", "Bonus added successfully.", Alert.AlertType.INFORMATION);
            parentBox.getChildren().clear();
            parentBox.getChildren().add(welcomeLabel);
        }
    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid input format. Please enter valid values.", Alert.AlertType.ERROR);
    }
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
   /*be  ostBookedTripLabel = createStyledLabel("Most Booked Trip: " +
            (Trips.mostBookedTrip() != null ? Trips.mostBookedTrip().toString() : "No trips available"));
    setLabelStyles(mostBookedTripLabel);

    Label mostRevenuedTripLabel = createStyledLabel("Most Revenued Trip: " +
            (Trips.mostRevenuedTrip() != null ? Trips.mostRevenuedTrip().toString() : "No trips available"));
    setLabelStyles(mostRevenuedTripLabel);

  DatePicker startDatePicker = new DatePicker();
    startDatePicker.setPromptText("Select Start Date");
    
    DatePicker endDatePicker = new DatePicker();
    endDatePicker.setPromptText("Select End Date");
    
    Button okButton = createStyledButton("OK", event -> {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

            double totalRevenue = Booking.calculateTotalRevenue(startDateTime, endDateTime);

            Label totalRevenueLabel = createStyledLabel("Total Revenue: " + totalRevenue);
            setLabelStyles(totalRevenueLabel);

            // Assuming that the existing VBox in the Manage Users pane is accessible
            VBox manageUsersPane = (VBox) stackPane.getChildren().get(0);

            // Clear existing labels before adding new ones
            manageUsersPane.getChildren().removeIf(node -> node instanceof Label);

            // Add the labels below the existing buttons
            manageUsersPane.getChildren().addAll(
                    mostBookedTripLabel,
                    mostRevenuedTripLabel,
                    totalRevenueLabel
            );
        } else {
            showAlert("Error", "Invalid date range. Please select a valid date range.", Alert.AlertType.ERROR);
        }
    });

    VBox dateSelectionBox = new VBox(10, startDatePicker, endDatePicker, okButton);
    dateSelectionBox.setAlignment(Pos.CENTER);

    // Assuming that the existing VBox in the Manage Users pane is accessible
    VBox manageUsersPane = (VBox) stackPane.getChildren().get(0);

    // Clear existing labels before adding new ones
    manageUsersPane.getChildren().removeIf(node -> node instanceof Label);

    // Add the dateSelectionBox above the existing buttons
    manageUsersPane.getChildren().add(dateSelectionBox);*/
}

private void handleDisplayUsers() {
   /*abel receptionistBookingsLabel = createStyledLabel("Receptionist with the maximum no. of Bookings: " +
            Booking.findReceptionistWithMostBookings());
    setLabelStyles(receptionistBookingsLabel);

    Label guestBookingsLabel = createStyledLabel("Guest with the maximum no. of Bookings: " +
            Booking.findGuestWithMostBookings());
    setLabelStyles(guestBookingsLabel);

    Label receptionistRevenueLabel = createStyledLabel("Receptionist with the maximum revenue: " +
            Booking.findReceptionistWithMostRevenue());
    setLabelStyles(receptionistRevenueLabel);

    Label guestRevenueLabel = createStyledLabel("Guest with the maximum revenue: " +
            Booking.findGuestWithMostRevenue());
    setLabelStyles(guestRevenueLabel);

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

private void setLabelStyles(Label label) {
    label.setTextFill(Color.web("#0a0c26"));
    label.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-size: 20; -fx-font-weight: bold;"); */
}


public static void readUsersFromFile() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("user_information.dat"))) {
        int numUsers = dis.readInt();
        for (int i = 0; i < numUsers; i++) {
            int id = dis.readInt();
            String name = dis.readUTF();
            String password = dis.readUTF(); // Read password

            Type userType = Type.RECEPTIONIST;  // Set the type to RECEPTIONIST
            if ("AdminName".equals(name)) {
                userType = Type.ADMIN;  // Set the type to ADMIN for the admin user
            }

            Users user = new Users(id, password, name, userType);
            users.add(user);
        }
        System.out.println("User information loaded from file.");
    } catch (FileNotFoundException e) {
        // Handle file not found exception
    } catch (IOException e) {
        System.out.println("Error reading user information from file: " + e.getMessage());
    }
}


public static void saveUsersToFile() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("user_information.dat", false))) {
        dos.writeInt(users.size());
        for (Users user : users) {
            dos.writeInt(user.getID());
            dos.writeUTF(user.getName());
            dos.writeUTF(user.getPassword()); // Save password
        }
        System.out.println("User information saved to file.");
        //saveAdminsToFile();
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
    for (int i = 0; i < 5; i++) {
        passwordBuilder.append(random.nextInt(10));
    }

    return passwordBuilder.toString();
}

  
/*/public boolean findUser(int id, String name) {
    for (Users currentUser : users) {
        if (currentUser.getID() == id || currentUser.getName().equals(name)) {
            return true; 
        }
    }
    return false; 
}
*/

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
    String imagePath = "file:" + Paths.get("C:/Users/Electronica Care/Pictures/Screenshots/Screenshot 2024-01-13 074436.png").toUri().toString();
    addPane.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-size: cover;");

    Label welcomeLabel = new Label();

    TextField idField = createStyledTextField("User ID");
    TextField nameField = createStyledTextField("User Name");
    PasswordField passwordField = createStyledPasswordField("User Password");

    ComboBox<Type> userTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(Type.values()));
    userTypeComboBox.setPromptText("Select User Type");
    userTypeComboBox.setStyle("-fx-font-size: 12; -fx-background-color: transparent; -fx-border-color: #ca7235; -fx-text-fill: #ffb000;");

    ListView<String> usersListView = new ListView<>(); // Create the ListView here
    usersListView.setMaxHeight(30);
    usersListView.setMinWidth(50);
    usersListView.setVisible(true);
    usersListView.setStyle("-fx-background-color: #292525; -fx-border-color: #ca7235;"); // Set background color

    Button addUserBtn = createStyledButton("Add User", event -> {
        String idText = idField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        Type userType = userTypeComboBox.getValue();

        if (idText.isEmpty() || name.isEmpty() || password.isEmpty() || userType == null) {
            showAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);
        } else {
            try {
                int id = Integer.parseInt(idText);
                if (userExists(id, name)) {
                    showAlert("Error", "User already exists.", Alert.AlertType.ERROR);
                } else {
                    try {

                        if (userExists(id, name)) {
                            throw new RuntimeException("User already exists");
                        } else {
                            Users newUser = new Users(id, password, name, userType);
                            users.add(newUser);
                            saveUsersToFile();

                            System.out.println("User added successfully!");
                            idField.clear();
                            nameField.clear();
                            passwordField.clear();
                            userTypeComboBox.getSelectionModel().clearSelection();
                            updateListView(usersListView); // Pass the usersListView to the updateListView method
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
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });
    goBackBtn.setStyle("-fx-font-size: 12; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

    VBox vbox = new VBox(10); // Adjusted spacing
    vbox.setAlignment(Pos.CENTER);

    vbox.getChildren().addAll(goBackBtn, idField, nameField, passwordField, userTypeComboBox, addUserBtn, usersListView);

    stackPane.getChildren().clear();
    stackPane.getChildren().add(vbox);

    updateListView(usersListView);
}


private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 10; -fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: #ffb000; -fx-border-width: 3px");
textField.setMaxWidth(200);
    textField.setMinWidth(200);    
    return textField;
    }

    private PasswordField createStyledPasswordField(String promptText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle("-fx-font-size: 12; -fx-background-color: transparent; -fx-border-color: #ca7235; -fx-text-fill: #ffb000;");
passwordField.setMaxWidth(300);
    passwordField.setMinWidth(300);
    return passwordField;
    }


private void updateListView(ListView<String> usersListView) {
    usersListView.getItems().clear(); // Clear existing items
    for (Users user : users) {
        usersListView.getItems().add("ID: " + user.getID() + " | Name: " + user.getName());
    }

    usersListView.setMinWidth(300);
    usersListView.setMaxWidth(300);

    usersListView.setMaxHeight(Double.MAX_VALUE);

    VBox currentVBox = (VBox) stackPane.getChildren().get(0);
    currentVBox.setStyle("-fx-background-color: #292525;");
    currentVBox.getChildren().removeIf(node -> node instanceof ListView);

    currentVBox.getChildren().add(usersListView);
}


   @Override
public void remove() {
    Label welcomeLabel = new Label();

    TextField idField = createStyledTextField("User ID");
    TextField nameField = createStyledTextField("User Name");

    Button removeUserBtn = createStyledButton("Remove User", event -> {
        String idText = idField.getText();
        String name = nameField.getText();

        if (idText.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);
        } else {
            try {
                int id = Integer.parseInt(idText);

                //readUsersFromFile(); // Moved this line to ensure data is up-to-date

                boolean found = userExists(id, name);

                if (found) {
                    users.removeIf(currentUser -> currentUser.getID() == id && currentUser.getName().equals(name));
                    System.out.println("User removed successfully!");
                    saveUsersToFile();
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

    Button goBackBtn = createStyledButton("close", event -> {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(welcomeLabel);
        updateStack(createManageUsersPane(welcomeLabel));
    });
    goBackBtn.setStyle("-fx-font-size: 12; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

    HBox removeUserBox = new HBox(10);
    removeUserBox.setAlignment(Pos.CENTER);

    removeUserBox.getChildren().addAll(goBackBtn, idField, nameField, removeUserBtn);
    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.getChildren().addAll(removeUserBox);

    updateStack(manageUsersMenu);
}


@Override
public void edit() {
    VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
    manageUsersMenu.setAlignment(Pos.CENTER);

    Label editLabel = createStyledLabel("Edit User");
    editLabel.setStyle("-fx-font-size: 24;");
    editLabel.setAlignment(Pos.CENTER);

    TextField userIdField = createStyledTextField("User ID");
    TextField userNameField = createStyledTextField("User Name");

    VBox editContainer = new VBox(); // Container for edit items
    editContainer.setAlignment(Pos.CENTER);
    editContainer.setSpacing(20);

    Button editUserBtn = createStyledButton("Edit User", event -> {
        try {
            int id = Integer.parseInt(userIdField.getText());
            String name = userNameField.getText();

            if (!userExists(id, name)) {
                showAlert("Error", "User does not exist.", Alert.AlertType.ERROR);
            } else {
                TextField newUserId = createStyledTextField("New User ID");
                TextField newUserName = createStyledTextField("New User Name");

                Button saveChangesBtn = createStyledButton("Save Changes", saveEvent -> {
                    try {
                        int newUserIdValue = Integer.parseInt(newUserId.getText());
                        if (userExists(newUserIdValue, newUserName.getText())) {
                            showAlert("Error", "User with the new ID and name already exists.", Alert.AlertType.ERROR);
                        } else {
                            try {

                                Optional<Users> userToEdit = users.stream()
                                        .filter(u -> u.getID() == id && u.getName().equals(name))
                                        .findFirst();

                                if (userToEdit.isPresent()) {
                                    if (!userExists(newUserIdValue, newUserName.getText())) {
                                        Users editedUser = userToEdit.get();
                                        editedUser.setID(newUserIdValue);
                                        editedUser.setName(newUserName.getText());

                                        //saveUsersToFile();
                                        showAlert("Success", "User updated successfully.", Alert.AlertType.INFORMATION);
                                        editContainer.getChildren().clear();
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
                gridPane.setAlignment(Pos.CENTER);
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
   closeButton.setStyle("-fx-font-size: 12; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");

    HBox editUserBox = new HBox(20,
            userIdField,
            userNameField,
            editUserBtn
    );
    editUserBox.setAlignment(Pos.CENTER);

    VBox combinedEditBox = new VBox(); // Container for combined edit and editUserAction items
    combinedEditBox.getChildren().addAll(
            editUserBox,
            editContainer // This container will hold items added by handleEditUserAction
    );
    combinedEditBox.setAlignment(Pos.CENTER);
    combinedEditBox.setSpacing(20);

    manageUsersMenu.getChildren().addAll(
            editLabel,
            combinedEditBox,
            closeButton
    );

    updateStack(manageUsersMenu);
}

     @Override
     public void search() {
    String searchTerm = searchField.getText().trim().toLowerCase();

    if (searchTerm.isEmpty()) {
        listView.getItems().clear();
        listView.setVisible(false);
        return;
    }

    List<Users> matchingUsers = users.stream()
            .filter(user -> String.valueOf(user.getID()).contains(searchTerm) || user.getName().toLowerCase().contains(searchTerm))
            .collect(Collectors.toList());

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
