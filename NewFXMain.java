package project.trial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class NewFXMain extends Application {
    public static Scene loginscene;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Users userSystem = new Users();

        // Page format
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/Project.jpg'); -fx-background-size: cover;");


        // Image
        Image i = new Image(new FileInputStream("/home/jana/Downloads/user(1).png"));
        ImageView iv = new ImageView(i);
        iv.setFitHeight(150); 
        iv.setFitWidth(150);

        // Functionality...

        // Labels
        Label ID = new Label("ID:");
        ID.setTextFill(Color.web("#ffb000"));
        ID.setFont(Font.font("Helvetica World", FontWeight.BOLD, 30)); 

        Label passw = new Label("Password:");
        passw.setTextFill(Color.web("#ffb000"));
        passw.setFont(Font.font("Helvetica World", FontWeight.BOLD, 30));  

        // Fields
        TextField id = new TextField();
        id.setMinSize(300, 60);  // Adjust the size as needed
        id.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-border-width: 4px; -fx-text-fill: #ffb000; -fx-font-size: 18px;");

        PasswordField password = new PasswordField();
        password.setMinSize(300, 60);  // Adjust the size as needed
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-border-width: 4px; -fx-text-fill: #ffb000; -fx-font-size: 18px;");

        // Buttons
        Button login = new Button("Login");
        login.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        login.setPrefWidth(300);
        login.setFont(Font.font("Helvetica World", FontWeight.BOLD, 24)); 
        login.setOnMouseEntered(eh ->
        {
            login.setCursor(Cursor.HAND);
            login.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-width: 4px;");
        });

        // non-hover event
        login.setOnMouseExited(eh ->
        {
            login.setCursor(Cursor.DEFAULT);
            login.setStyle("-fx-background-color: transparent; -fx-border-color: #ffb000; -fx-border-radius: 5px; -fx-text-fill: #ffb000; -fx-border-width: 4px;");
        });

        // GridPane
        GridPane gridpane = new GridPane();
        gridpane.add(ID, 0, 0);
        gridpane.add(id, 1, 0);
        gridpane.add(passw, 0, 1);
        gridpane.add(password, 1, 1);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(40);
        gridpane.setVgap(25);

        // VBox
        VBox vbox = new VBox();
        vbox.setMinSize(600, 600);
        vbox.getChildren().addAll(iv, gridpane, login);
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.CENTER);
        
        // StackPane
        StackPane stackpane = new StackPane();
        Rectangle rectangle = new Rectangle(600, 600);
        rectangle.setArcWidth(20); 
        rectangle.setArcHeight(20); 
        rectangle.setFill(Color.rgb(10,12,38, 0.5));
        rectangle.setStyle("-fx-border-radius: 5px;");
        stackpane.getChildren().addAll(rectangle, vbox);

        // Adding items to the page
        root.setCenter(stackpane);
        Region centerRegion = (Region) root.getCenter();
        centerRegion.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Event handler for buttons
        login.setOnAction(e ->
        {
            
            try {
                    int ident = Integer.parseInt(id.getText());

                    int userType;
                    try {
                        userType = userSystem.Login2(ident, password.getText());
                        if (userType != -1) {
                            
                            switch (userType) {
                                case 0:
                                    //admin home page

                                    for(Admin a: Admin.admins)
                                    {
                                        if(a.ID == ident)
                                        {
                                            Scene AdminsScene = a.HomePageScene();
                                            primaryStage.setScene(AdminsScene);
                                            primaryStage.setTitle("Bus-Ticket Booking System - Admin HomePage");
                                        }
                                    }                                    
                                    break;
                                    
                                case 1:
                                    //receptionist home page
                                    for(Receptionist r: Receptionist.receptionists)
                                    {
                                        if(r.ID == ident)
                                        {
                                            primaryStage.setScene(r.ManageBookings(primaryStage));
                                            primaryStage.setTitle("Bus-Ticket Booking System - Receptionist HomePage");
                                        }
                                    }
                                    break;
                                case 2:
                                    //guest home page
                                    for(Guest g: Guest.guests.values())
                                    {
                                        if(g.ID == ident)
                                        {
                                            primaryStage.setScene(g.guestHomePage());
                                            primaryStage.setTitle("Bus-Ticket Booking System - Guest HomePage");
                                        }
                                    }                                    
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            showAlert(AlertType.WARNING, "Login Failed", "Invalid ID or Password. Please try again.");
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                    }
            } catch (NumberFormatException x) {
                    showAlert(AlertType.WARNING, "Invalid ID format", "Please enter a valid Integer ID.");
            }
            
            id.clear();
            password.clear();
            
        });

        loginscene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setTitle("Bus-Ticket Booking System - Login Page");
        primaryStage.setScene(loginscene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
//Admin@Parent101
    public static void main(String[] args) throws IOException {
        Admin.readUsersFromFile();
        Guest.loadGuestsFromBinaryFile();
        Vehicle.readFromFile();
        Booking.readFromFile();
        
        Trips t = new Trips();
        t.DisplayTrips();

        Vehicle.initializeAvailibilityMap();
        
        Admin ParentAdmin = new Admin(101, "101", "Parent_Admin");
        Receptionist recep = new Receptionist(102, "101", "Receptionist_1");
        Guest guest = new Guest(103, "101", "Guest_1");
        
        launch(args);
        
        Vehicle.updateFile();
        Booking.saveToFile();
        Guest.saveGuestsToBinaryFile();
        Admin.saveUsersToFile();
    }
}
