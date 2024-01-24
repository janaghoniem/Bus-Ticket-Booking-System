
package project.trial;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author jana
 */
public class Admin extends Users implements manages<Admin>{
    public static VBox vehicleTable = new VBox();
    static ScrollPane scrollPane = new ScrollPane(vehicleTable);
    public static HBox horizontalLayoutBox = new HBox();
    public static Scene homepage;
    
    public static final List<Users> users = new ArrayList<>();
    public static final List<Admin> admins = new ArrayList<>();
    //private static final int GUEST_PASSWORD_LENGTH = 5;
    private final StackPane stackPane = new StackPane();
   
    private TextField searchField;  // Declare as instance variable
    private ListView<String> listView;  // Declare as instance variable
    private static final String ACCOUNTING_FILE_PATH = "accounting.dat";

    protected double Salary;
    protected int Bonus;

    public StackPane getStackPane() {
        return stackPane;
    }
    
    public Scene manageUsers() throws FileNotFoundException {
        VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageUsersMenu);
        setSceneBackground(manageUsersMenu);

        Scene manageUsersScene = new Scene(stackPane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        manageUsersScene.setFill(Color.rgb(135, 206, 250)); // Set the background color of the scene
        stackPane.setStyle("-fx-background-color: #358dca;"); // Set the background color of the StackPane

        return manageUsersScene;
    }
 
 
private Label createStyledLabel(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-size: 50; -fx-text-fill: white; -fx-font-family: 'Helvetica World'; -fx-font-weight: bold;");
    return label;
}


public Scene HomePageScene() throws FileNotFoundException
  {
      BorderPane pane = new BorderPane();
      pane.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project(2).jpg'); -fx-background-size: cover;");
      
      Button logout = new Button();
      logout.setStyle("-fx-background-color: transparent;");
      ImageView log_out = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/leave.png")));
      log_out.setFitWidth(40);
      log_out.setFitHeight(40);
      logout.setGraphic(log_out);
      
      logout.setOnMouseEntered(eh -> 
      {
          logout.setCursor(Cursor.HAND);
      });
      
      logout.setOnAction(eh ->
      {
          Alert logoutq = new Alert(AlertType.CONFIRMATION);
          logoutq.setHeaderText(null);
          logoutq.setContentText("Are you sure you want to log out?");
          Optional<ButtonType> response = logoutq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
            Stage primaryStage = (Stage) pane.getScene().getWindow();
            primaryStage.setScene(NewFXMain.loginscene);
          }

      });
      
      Label welcomelbl = new Label("Welcome " + this.Name + ".");
      welcomelbl.setTextFill(Color.web("#ffb000"));
      welcomelbl.setFont(Font.font("Montserrat ", FontWeight.BOLD, 60));
      HBox welcomebox = new HBox( 500, welcomelbl, logout);
      welcomebox.setAlignment(Pos.TOP_RIGHT);

      
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
            
            manageUsers.setOnMouseClicked(eh ->
            {
                try {
                    Scene manageU = createManageUsersScene();
                    Stage primaryStage = (Stage) manageUsers.getScene().getWindow();
                    primaryStage.setScene(manageU);
                    primaryStage.setTitle("Bus-Ticket Booking System - Manage Users Page");
                    primaryStage.sizeToScene();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
                try {
                    Scene manageV = managesVehicle();
                    Stage primaryStage = (Stage) manageVehicles.getScene().getWindow();
                    primaryStage.setScene(manageV);
                    primaryStage.setTitle("Bus-Ticket Booking System - Manage Vehicles Page");
                    primaryStage.sizeToScene();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            
            manageTrips.setOnMouseClicked(eh -> 
            {
                try {
                    Scene manageT = managesTrips();
                    Stage primaryStage = (Stage) manageVehicles.getScene().getWindow();
                    primaryStage.setScene(manageT);
                    primaryStage.setTitle("Bus-Ticket Booking System - Manage Vehicles Page");
                    primaryStage.sizeToScene();
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
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
      
     
      homepage = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
      return homepage;
  }
  
  public static void upUponHover(StackPane pane)
  {
    TranslateTransition up = new TranslateTransition(Duration.millis(200), pane);
    up.setByY(-10);
    up.play();
  }
  
  public static void downUponExit(StackPane pane)
  {
    TranslateTransition down = new TranslateTransition(Duration.millis(200), pane);
    down.setByY(10);
    pane.setTranslateY(0);
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
    
    public Scene createManageUsersScene() throws FileNotFoundException {
        VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
        manageUsersMenu.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageUsersMenu);
        setSceneBackground(manageUsersMenu);

        Scene manageUsersScene = new Scene(stackPane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        manageUsersScene.setFill(Color.rgb(135, 206, 250)); // Set the background color of the scene
        stackPane.setStyle("-fx-background-color: #358dca;"); // Set the background color of the StackPane

        return manageUsersScene;
    }

    private void setSceneBackground(VBox container) {
    String imagePath = "/home/jana/Downloads/backtrial.jpg";

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

public void ManageUsers() throws FileNotFoundException {

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

    private VBox createManageUsersPane(Label welcomeLabel) throws FileNotFoundException {

            VBox manageUsersMenu = new VBox(20);
            manageUsersMenu.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
            manageUsersMenu.setAlignment(Pos.CENTER);
            manageUsersMenu.setPadding(new Insets(30, 0, 0, 30));
            
            Button logout = new Button();
            logout.setStyle("-fx-background-color: transparent;");
            ImageView log_out = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/leave.png")));
            log_out.setFitWidth(40);
            log_out.setFitHeight(40);
            logout.setGraphic(log_out);
            
            logout.setOnMouseEntered(eh ->
            {
                logout.setCursor(Cursor.HAND);
            });
            
            logout.setOnAction(eh ->
            {
                Alert logoutq = new Alert(Alert.AlertType.CONFIRMATION);
                logoutq.setHeaderText(null);
                logoutq.setContentText("Are you sure you want to log out?");
                Optional<ButtonType> response = logoutq.showAndWait();
                if(response.isPresent() && response.get() == ButtonType.OK)
                {
                    Stage primaryStage = (Stage) manageUsersMenu.getScene().getWindow();
                    primaryStage.setScene(NewFXMain.loginscene);
                    primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
                }
            });
            
            Button home = new Button();
            home.setStyle("-fx-background-color: transparent;");
            ImageView homee = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/home.png")));
            homee.setFitWidth(40);
            homee.setFitHeight(40);
            home.setGraphic(homee);
            
            home.setOnMouseEntered(eh ->
            {
                home.setCursor(Cursor.HAND);
            });
            
            home.setOnAction(eh -> {
                Alert homeq = new Alert(Alert.AlertType.CONFIRMATION);
                homeq.setHeaderText(null);
                homeq.setContentText("Are you sure you want to return to homepage?");
                Optional<ButtonType> response = homeq.showAndWait();
                if(response.isPresent() && response.get() == ButtonType.OK)
                {
                    Stage primaryStage = (Stage) manageUsersMenu.getScene().getWindow();
                    primaryStage.setScene(homepage);
                }
            });
            
            HBox buttons = new HBox(2,logout, home );
            
            HBox welcomeBox = new HBox(900, welcomeLabel, buttons);
            welcomeBox.setAlignment(Pos.TOP_RIGHT);
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
                    createStyledButton("Add Salary To User", event -> {
                try {
                    handleAddSalary();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }),
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


private void handleAddSalary() throws FileNotFoundException {
        try {
            HBox addSalaryBox = new HBox(10);
            addSalaryBox.setAlignment(Pos.CENTER);
            
            Label welcomeLabel = createStyledLabel("Add Salary");
            welcomeLabel.setStyle("-fx-font-size: 24;");
            
            TextField userIdField = createStyledTextField("User ID");
            TextField salaryField = createStyledTextField("Salary");
            
            Button addSalaryBtn = new Button("Add Salary");
            addSalaryBtn.setOnAction(event -> handleAddSalaryAction(userIdField, salaryField, addSalaryBox, welcomeLabel));
            
            Button closeButton = new Button("Close");
            closeButton.setOnAction(closeEvent -> {
                try {
                    ManageUsers();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            addSalaryBox.getChildren().addAll(
                    userIdField,
                    salaryField,
                    addSalaryBtn,
                    closeButton
            );
            
            VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
            manageUsersMenu.getChildren().add(addSalaryBox);
            
            updateStack(manageUsersMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            HBox addBonusBox = new HBox(10);
            addBonusBox.setAlignment(Pos.CENTER);
            
            Label welcomeLabel = createStyledLabel("Add Bonus");
            welcomeLabel.setStyle("-fx-font-size: 24;");
            
            TextField userIdField = createStyledTextField("User ID");
            TextField bonusField = createStyledTextField("Bonus");
            
            Button addBonusBtn = new Button("Add Bonus");
            addBonusBtn.setOnAction(event -> handleAddBonusAction(userIdField, bonusField, addBonusBox, welcomeLabel));
            
            Button closeButton = new Button("Close");
            closeButton.setOnAction(closeEvent -> {
                try {
                    ManageUsers();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            addBonusBox.getChildren().addAll(
                    userIdField,
                    bonusField,
                    addBonusBtn,
                    closeButton
            );
            
            VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
            manageUsersMenu.getChildren().add(addBonusBox);
            
            updateStack(manageUsersMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    stackContainer.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
    stackContainer.setAlignment(Pos.TOP_CENTER);
    setSceneBackground(stackContainer); // Set the background image

    stackContainer.getChildren().add(pane);
    stackPane.getChildren().clear();
    stackPane.getChildren().add(stackContainer);
}


private void handleDisplayBookings() {
    Label mostBookedTripLabel = createStyledLabel("Most Booked Trip: " +
            (Trips.mostBookedTrip() != null ? Trips.mostBookedTrip().toString() : "No trips available"));
    setLabelStyles(mostBookedTripLabel);

    Label mostRevenuedTripLabel = createStyledLabel("Most Revenued Trip: " +
            (Trips.mostRevenuedTrip() != null ? Trips.mostRevenuedTrip().toString() : "No trips available"));
    setLabelStyles(mostRevenuedTripLabel);

    // Create labels for average revenue and number of bookings over a specific period
    Label averageRevenueLabel = createStyledLabel("Enter a period to calculate average revenue:");
    setLabelStyles(averageRevenueLabel);

    Label bookingCountLabel = createStyledLabel("Enter a period to calculate the number of bookings:");
    setLabelStyles(bookingCountLabel);

    Label avgRevenueResultLabel = createStyledLabel("");
    setLabelStyles(avgRevenueResultLabel);

    Label bookingCountResultLabel = createStyledLabel("");
    setLabelStyles(bookingCountResultLabel);

    DatePicker avgRevenueStartDatePicker = new DatePicker();
    avgRevenueStartDatePicker.setPromptText("Select Start Date");

    DatePicker avgRevenueEndDatePicker = new DatePicker();
    avgRevenueEndDatePicker.setPromptText("Select End Date");

    DatePicker bookingCountStartDatePicker = new DatePicker();
    bookingCountStartDatePicker.setPromptText("Select Start Date");

    DatePicker bookingCountEndDatePicker = new DatePicker();
    bookingCountEndDatePicker.setPromptText("Select End Date");

    Button avgRevenueOKButton = createStyledButton("OK", event -> {
        LocalDate startDate = avgRevenueStartDatePicker.getValue();
        LocalDate endDate = avgRevenueEndDatePicker.getValue();

        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

            double avgRevenue = Booking.calculateTotalRevenue(startDateTime, endDateTime);

            avgRevenueResultLabel.setText("Average Revenue: " + avgRevenue);

            // Update the label without shifting the layout
            avgRevenueResultLabel.setVisible(true);
        } else {
            showAlert("Error", "Invalid date range. Please select a valid date range.", Alert.AlertType.ERROR);
        }
    });

    Button bookingCountOKButton = createStyledButton("OK", event -> {
        LocalDate startDate = bookingCountStartDatePicker.getValue();
        LocalDate endDate = bookingCountEndDatePicker.getValue();

        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

            int bookingCount = Booking.getBookingsCountOverTimePeriod(startDateTime, endDateTime);

            bookingCountResultLabel.setText("Number of Bookings: " + bookingCount);

            // Update the label without shifting the layout
            bookingCountResultLabel.setVisible(true);
        } else {
            showAlert("Error", "Invalid date range. Please select a valid date range.", Alert.AlertType.ERROR);
        }
    });

    VBox avgRevenueDateSelectionBox = new VBox(10, avgRevenueStartDatePicker, avgRevenueEndDatePicker, avgRevenueOKButton);
    avgRevenueDateSelectionBox.setAlignment(Pos.CENTER);

    VBox bookingCountDateSelectionBox = new VBox(10, bookingCountStartDatePicker, bookingCountEndDatePicker, bookingCountOKButton);
    bookingCountDateSelectionBox.setAlignment(Pos.CENTER);

    // Assuming that the existing VBox in the Manage Users pane is accessible
    VBox manageUsersPane = (VBox) stackPane.getChildren().get(0);

    // Clear existing labels before adding new ones
    manageUsersPane.getChildren().removeIf(node -> node instanceof Label);

    // Add the dateSelectionBoxes and labels above the existing buttons
    manageUsersPane.getChildren().addAll(
            mostBookedTripLabel,
            mostRevenuedTripLabel,
            averageRevenueLabel,
            avgRevenueDateSelectionBox,
            avgRevenueResultLabel,
            bookingCountLabel,
            bookingCountDateSelectionBox,
            bookingCountResultLabel
    );
}


private void setLabelStyles(Label label) {
    label.setTextFill(Color.WHITE);
    label.setStyle("-fx-font-family: 'Helvetica World'; -fx-font-size: 20; -fx-font-weight: bold;");
}

private void handleDisplayUsers() {
   Label receptionistBookingsLabel = createStyledLabel("Receptionist with the maximum no. of Bookings: " +
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


public static void readUsersFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_information.dat"))) {
        users.clear(); // Clear existing users before reading from file
        int numUsers = ois.readInt();
        for (int i = 0; i < numUsers; i++) {
            Users user = (Users) ois.readObject();
            users.add(user);
            Users.usersMap.put(user.ID, user);
            if(user.position.ordinal() == 0)
            {
                Admin a = new Admin(user.ID, user.Password, user.Name);
                admins.add(a);
            }
            
            else if(user.position.ordinal() == 1)
            {
                Receptionist r = new Receptionist(user.ID, user.Password, user.Name);
                Receptionist.storeReceptionistInfo(r);
            }
            
            else if(user.position.ordinal() == 2)
            {
                Guest g = new Guest(user.ID, user.Password, user.Name);
                Guest.guests.put(g.ID, g);
            }
            
        }
        System.out.println("User information loaded from file.");
    } catch (FileNotFoundException e) {
        // Handle file not found exception
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error reading user information from file: " + e.getMessage());
    }
}

public static void saveUsersToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_information.dat", false))) {
        oos.writeInt(users.size());
        for (Users user : users) {
            oos.writeObject(user);
        }
        System.out.println("User information saved to file.");
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
 

    public static Scene managesVehicle() throws FileNotFoundException
    {
        Vehicle.listlabelsinitialization();
        vehicleTable.getChildren().add(Vehicle.listlabels);
        FlowPane vehicleSceneLayout = new FlowPane();
        vehicleSceneLayout.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
        vehicleSceneLayout.setOrientation(Orientation.VERTICAL);
        vehicleSceneLayout.setAlignment(Pos.TOP_LEFT);
        
        Button logout = new Button();
        logout.setStyle("-fx-background-color: transparent;");
        ImageView log_out = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/leave.png")));
        log_out.setFitWidth(40);
        log_out.setFitHeight(40);
        logout.setGraphic(log_out);
      
        logout.setOnMouseEntered(eh -> 
        {
            logout.setCursor(Cursor.HAND);
        });
      
        logout.setOnAction(eh ->
        {
          Alert logoutq = new Alert(Alert.AlertType.CONFIRMATION);
          logoutq.setHeaderText(null);
          logoutq.setContentText("Are you sure you want to log out?");
          Optional<ButtonType> response = logoutq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
            Stage primaryStage = (Stage) vehicleSceneLayout.getScene().getWindow();
            primaryStage.setScene(NewFXMain.loginscene);
            primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
          }
        });
        
        Button home = new Button();
        home.setStyle("-fx-background-color: transparent;");
        ImageView homee = new ImageView(new Image(new FileInputStream("/home/jana/Downloads/home.png")));
        homee.setFitWidth(40);
        homee.setFitHeight(40);
        home.setGraphic(homee);
      
        home.setOnMouseEntered(eh -> 
        {
            home.setCursor(Cursor.HAND);
        });
      
        home.setOnAction(eh ->
        {
          Alert homeq = new Alert(Alert.AlertType.CONFIRMATION);
          homeq.setHeaderText(null);
          homeq.setContentText("Are you sure you want to return to homepage?");
          Optional<ButtonType> response = homeq.showAndWait();
          if(response.isPresent() && response.get() == ButtonType.OK)
          {
              Stage primaryStage = (Stage) vehicleSceneLayout.getScene().getWindow();
              primaryStage.setScene(homepage);
          }
        });
        
        HBox buttons = new HBox(2,logout, home );
        buttons.setAlignment(Pos.TOP_RIGHT);
                
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
            HBox top = new HBox(1000,manageVehiclesLabel, buttons );
            buttons.setAlignment(Pos.TOP_RIGHT);
                       
            
            //VBOX
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");             
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setMaxHeight(550);
            vehicleTable.setSpacing(10);
            vehicleTable.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

            
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
            addVehicle.setCursor(Cursor.HAND);
            HBox.setHgrow(addVehicle, Priority.ALWAYS);

            
            //SEARCH BUTTON
            Button searchVehicle = new Button();
            searchVehicle.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); 
            searchVehicle.setGraphic(imageView);
            searchVehicle.setMaxHeight(20);
            HBox.setHgrow(searchVehicle, Priority.ALWAYS);
            
            //COMBO BOX
            ObservableList o;
            o = FXCollections.observableArrayList("General","License Plate", "Category", "Number of Seats", "Price per Seat", "Driver's Name", "Date Purchased");
            ComboBox searchBy = new ComboBox(o);
            searchBy.setStyle("-fx-background-color: #ffb000; -fx-border-color: #ffb000;");
            searchBy.setMaxWidth(200);
            searchBy.setValue("Search by...");
            searchBy.setCursor(Cursor.HAND);
            HBox.setHgrow(searchBy, Priority.ALWAYS);
            
            HBox.setHgrow(horizontalLayoutBox, Priority.ALWAYS);
            
            horizontalLayoutBox.getChildren().addAll(addVehicle, searchBar, searchVehicle, searchBy);
            horizontalLayoutBox.setSpacing(10);
            horizontalLayoutBox.setMaxHeight(100);
            horizontalLayoutBox.setAlignment(Pos.BASELINE_LEFT);
           
            //ADDING ELEMENTS TO LAYOUT
            vehicleSceneLayout.getChildren().addAll(top, horizontalLayoutBox, Vehicle.addlayout,Vehicle.listlabels ,scrollPane);
            
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
        
        Scene vehicleScene = new Scene(vehicleSceneLayout, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        return vehicleScene;
    }
    
    
    public static Scene managesTrips() throws IOException
    {
        Trips t = new Trips();
        Scene scene = t.createMainScene();
        return scene;
    }
    
    
@Override
public void add() {
    VBox addPane = new VBox();
    String imagePath = "file:" + Paths.get("file:home/jana/Downloads/Project(6).png").toUri().toString();
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
  //  usersListView.setStyle("-fx-background-color: #292525; -fx-border-color: #ca7235;"); // Set background color

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
                            Admin a = new Admin(newUser.ID, newUser.Password, newUser.Name);
                            admins.add(a);
                            saveUsersToFile();
if (userType == Type.RECEPTIONIST && newUser instanceof Receptionist) {
                            Receptionist.storeReceptionistInfo((Receptionist) newUser);
                        }

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
        try {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(welcomeLabel);
            VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
            
            updateStack(manageUsersMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
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
                            try {
                                updateStack(createManageUsersPane(welcomeLabel));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
                try {
                    stackPane.getChildren().clear();
                    stackPane.getChildren().add(welcomeLabel);
                    updateStack(createManageUsersPane(welcomeLabel));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            goBackBtn.setStyle("-fx-font-size: 12; -fx-background-color: #ffb000; -fx-text-fill: #0a0c26;");
            
            HBox removeUserBox = new HBox(10);
            removeUserBox.setAlignment(Pos.CENTER);
            
            removeUserBox.getChildren().addAll(goBackBtn, idField, nameField, removeUserBtn);
            VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
            manageUsersMenu.getChildren().addAll(removeUserBox);
            
            updateStack(manageUsersMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
}


@Override
public void edit() {
        try {
            VBox manageUsersMenu = createManageUsersPane(createStyledLabel("Manage Users"));
            manageUsersMenu.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
            
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
                try {
                    ManageUsers();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            .filter(user -> String.valueOf(user.getID()).toLowerCase().contains(searchTerm)
                    || user.getName().toLowerCase().contains(searchTerm)
                    || user.position.toString().toLowerCase().contains(searchTerm))
            .collect(Collectors.toList());

    if (matchingUsers.isEmpty()) {
        listView.getItems().setAll("No users found matching the search term.");
    } else {
        List<String> userStrings = matchingUsers.stream()
                .map(user -> "ID: " + user.getID() + " | Name: " + user.getName() + " | Type: " + user.position)
                .collect(Collectors.toList());

        listView.getItems().setAll(userStrings);
    }

    listView.setVisible(true);

}

}
