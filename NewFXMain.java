/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project.trial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jana
 */
public class NewFXMain extends Application {
    boolean slidingLayoutShown = false;
        
    //VBox TRIAL
    public static VBox vehicleTable = new VBox();
    
    
    @Override
    public void start(Stage primaryStage){
        
        //Vehicle.displayVehicles();
        
        //LOGIN - SIGNUP SARA
        
        //ADMIN PAGE MARIAM
        //will have different buttons each one switching to one of the scenes below
        
        //MANAGE USERS BUTTON PAGE MARIAM
        
        //MANAGE VEHICLES BUTTON PAGE JANA
        
        //LAYOUT
        
        GridPane vehicleSceneLayout = new GridPane();
        vehicleSceneLayout.setStyle("-fx-background-color: #292525; -fx-background-image: url('file:/home/jana/Downloads/backtrial.jpg'); -fx-background-size: cover;");
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        vehicleSceneLayout.getColumnConstraints().add(column);
        
        

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
            
            Label licensePlate = new Label("LICENSE PLATE");
            licensePlate.setTextFill(Color.web("#ffb000"));
            licensePlate.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            licensePlate.setPrefWidth(250);
            HBox.setHgrow(licensePlate, Priority.ALWAYS);
        
            Label category = new Label("BUS CATEGORY");
            category.setTextFill(Color.web("#ffb000"));
            category.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            category.setPrefWidth(250);
            HBox.setHgrow(category, Priority.ALWAYS);
        
            Label numberofSeats = new Label("NUMBER OF SEATS");
            numberofSeats.setTextFill(Color.web("#ffb000"));
            numberofSeats.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            numberofSeats.setPrefWidth(250);
            HBox.setHgrow(numberofSeats, Priority.ALWAYS);
        
            Label ticketPrice = new Label("PRICE PER SEAT");
            ticketPrice.setTextFill(Color.web("#ffb000"));
            ticketPrice.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));    
            ticketPrice.setPrefWidth(250);
            HBox.setHgrow(ticketPrice, Priority.ALWAYS);

            Label driverName = new Label("DRIVER'S NAME");
            driverName.setTextFill(Color.web("#ffb000"));
            driverName.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            driverName.setPrefWidth(250);
            HBox.setHgrow(driverName, Priority.ALWAYS);
            
            //HBox
            HBox horizontalLayoutBox = new HBox();
            HBox.setHgrow(horizontalLayoutBox, Priority.ALWAYS);
            HBox listlabels = new HBox(70,licensePlate, category, numberofSeats, ticketPrice, driverName);
            //listlabels.setAlignment(Pos.CENTER);
            
            //VBOX
            //vehicleTable.getChildren().add(listlabels);
            ScrollPane scrollPane = new ScrollPane(vehicleTable);
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
            
            horizontalLayoutBox.getChildren().addAll(addVehicle, searchBar, searchVehicle, searchBy);
            horizontalLayoutBox.setSpacing(10);
            horizontalLayoutBox.setMaxHeight(100);
            horizontalLayoutBox.setAlignment(Pos.BASELINE_LEFT);
           
            //ANIMATION (TRANSLATION??) OF ADD BUTTON SLIDING PANEL
            Label LicensePlate = new Label("License Plate:");
            LicensePlate.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
            TextField LicensePlateString = new TextField();
            LicensePlateString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-font-family: 'Helvetica World';");
            
            Label Category = new Label("Category:");
            Category.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
            ObservableList oo = FXCollections.observableArrayList("MicroBus", "MiniBus", "Bus", "MegaBus");
            ComboBox CategoryString = new ComboBox(oo);       
            CategoryString.setValue("Bus");
            
            Label NumberofSeats = new Label("Number of Seats:");
            NumberofSeats.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
            Spinner<Integer> seatsspinner = new Spinner<>();
            SpinnerValueFactory<Integer> seatsvalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 40);
            seatsspinner.setValueFactory(seatsvalueFactory);
            
            Label TicketPrice = new Label("Ticket Price:");
            TicketPrice.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
            Spinner<Integer> pricespinner = new Spinner<>();
            SpinnerValueFactory<Integer> pricevalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 20);
            pricespinner.setValueFactory(pricevalueFactory);        
            
            Label DriverName = new Label("Driver's Name:");
            DriverName.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
            TextField DriverNameString = new TextField();
            DriverNameString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-font-family: 'Helvetica World';");
            
            Button saveButton = new Button("SAVE");
            saveButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
            saveButton.setPrefWidth(100);
            Button cancelButton = new Button("CANCEL");
            cancelButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
            cancelButton.setPrefWidth(100);
            Button clearButton = new Button("CLEAR");
            clearButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
            clearButton.setPrefWidth(100);
            
            GridPane addlayout = new GridPane();
            
            
            addlayout.add(LicensePlate, 0, 0);
            addlayout.add(LicensePlateString, 1, 0);
            addlayout.add(Category, 2, 0);
            addlayout.add(CategoryString, 3, 0);
            addlayout.add(NumberofSeats, 0, 1);
            addlayout.add(seatsspinner, 1, 1);
            addlayout.add(TicketPrice, 2, 1);
            addlayout.add(pricespinner, 3, 1);
            addlayout.add(DriverName, 0, 2);
            addlayout.add(DriverNameString, 1, 2);
            addlayout.add(saveButton, 2, 2);
            addlayout.add(clearButton, 3, 2);           
            addlayout.add(cancelButton, 4, 2);
            addlayout.setVisible(false);

                        
            //ADDING ELEMENTS TO LAYOUT
            vehicleSceneLayout.add(manageVehiclesLabel, 0, 0);
            vehicleSceneLayout.add(horizontalLayoutBox, 0, 1);
            vehicleSceneLayout.add(addlayout, 0, 1);   
            vehicleSceneLayout.add(listlabels, 0, 2);               
            vehicleSceneLayout.add(scrollPane, 0, 4);
            
            //SET ALIGNMENT
            vehicleSceneLayout.setVgap(20);
            vehicleSceneLayout.setPadding(new javafx.geometry.Insets(40, 40, 40, 40));
            addlayout.setHgap(30);
            addlayout.setVgap(10);
            addlayout.setAlignment(Pos.TOP_LEFT);
            
        
            //EVENT-HANDLING
            addVehicle.setOnAction(e ->
            {
                addlayout.setTranslateY(0);
                listlabels.setTranslateY(0);
                vehicleTable.setTranslateY(0);                
                if(!slidingLayoutShown)
                {
                    TranslateTransition slideDownTransition = new TranslateTransition(Duration.millis(600), addlayout);
                    TranslateTransition slideDownTransition2 = new TranslateTransition(Duration.millis(600), listlabels);
                    TranslateTransition slideDownTransition3 = new TranslateTransition(Duration.millis(600), vehicleTable);
                    slideDownTransition.setByY(60);
                    slideDownTransition2.setByY(60);
                    slideDownTransition3.setByY(60);                    
                    slideDownTransition.play();
                    slideDownTransition2.play();
                    slideDownTransition3.play();                    
                    addlayout.setVisible(true);
                    slidingLayoutShown = true;
                }
            });
            
            saveButton.setOnAction(e -> {
                Vehicle newVehicle = new Vehicle();
                newVehicle.setLicense_plate(LicensePlateString.getText());
                newVehicle.setCategory(Vehicle.vehicleCategory.valueOf(CategoryString.getValue().toString().toUpperCase()));
                newVehicle.setNumber_of_seats(seatsspinner.getValue());
                newVehicle.setTicket_price(pricespinner.getValue());
                newVehicle.setBusDriver_name(DriverNameString.getText());
                newVehicle.add();
            });
            
            clearButton.setOnAction(e -> {
                LicensePlateString.clear();
                DriverNameString.clear();
            });
            
            cancelButton.setOnAction(e -> 
            {
                TranslateTransition slideUpTransition = new TranslateTransition(Duration.millis(600), addlayout);
                TranslateTransition slideUpTransition2 = new TranslateTransition(Duration.millis(600), listlabels);
                TranslateTransition slideUpTransition3 = new TranslateTransition(Duration.millis(600), vehicleTable);                
                slideUpTransition.setByY(-20);
                slideUpTransition2.setByY(-20);                
                slideUpTransition3.setByY(-20);
                slideUpTransition.setOnFinished(e2 -> addlayout.setVisible(false));
                slideUpTransition.play();
                slideUpTransition2.play();
                slideUpTransition3.play();                
                slidingLayoutShown = false;
            });

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        //MANAGE TRIPS BUTTON PAGE ROAA
        
        //MANAGE BOOKINGS BUTTON PAGE NOURAN
        
        //RECEPTIONIST PAGE MARIAM
        
        //MANAGE GUESTS BUTTON PAGE SARA

        

        //FOR TESTING - CHANGE THE PARAMETERS IF YOU NEED TO TEST YOUR OWN SCENE
        Scene scene = new Scene(vehicleSceneLayout, 800, 600);
        
        primaryStage.setTitle("Bus-Ticket Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
