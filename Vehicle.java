/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/**
 *
 * @author jana
 */
public class Vehicle implements manages<Vehicle>, Serializable {
    
    public enum vehicleCategory
    {
        MEGABUS, 
        BUS, 
        MINIBUS,
        MICROBUS
    }
    
    private static HashMap<String, Vehicle> VehicleList = new HashMap<>(50);
    private static HashMap<Integer, Integer> AvailibilityMap = new HashMap<>(50);
        
    //NEEDED FOR MANAGE VEHICLES
    public static GridPane addlayout = new GridPane();
    private static boolean slidingLayoutShown = false;
    public static HBox listlabels = new HBox();
        
    private static File vehicleFile = new File("vehicleFile.dat");
    private String License_plate;
    private vehicleCategory Category;
    private int Number_of_seats;
    private double ticket_price;
    private int num_of_available_seats;
    private int booking; //what if someone has one booking for the vehicle but with two tickets
    private String BusDriver_name;
    private LocalDate datePurchased;


    public Vehicle() {
    }

    public Vehicle(String License_plate, vehicleCategory Category, String BusDriver_name, int Number_of_seats, double ticket_price) {
        this.License_plate = License_plate;
        this.Category = Category;
        this.Number_of_seats = Number_of_seats;
        this.ticket_price = ticket_price;
        this.BusDriver_name = BusDriver_name;
    }

    public String getLicense_plate() {
        return License_plate;
    }

    public vehicleCategory getCategory() {
        return Category;
    }

    public int getNumber_of_seats() {
        return Number_of_seats;
    }

    public double getTicket_price() {
        return ticket_price;
    }
    
    public int getBooking() {
        return booking;
    }

    public static HashMap<String, Vehicle> getVehicleList() {
        return VehicleList;
    }

    public String getBusDriver_name() {
        return BusDriver_name;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }
    

    public void setLicense_plate(String License_plate) {
        this.License_plate = License_plate;
    }

    public void setCategory(vehicleCategory Category) {
        this.Category = Category;
    }

    public void setNumber_of_seats(int Number_of_seats) {
        this.Number_of_seats = Number_of_seats;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setBusDriver_name(String BusDriver_name) {
        this.BusDriver_name = BusDriver_name;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }
    
    
    public static void listlabelsinitialization()
    {
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
        
        Label datePurchased = new Label("DATE PURCHASED");
        datePurchased.setTextFill(Color.web("#ffb000"));
        datePurchased.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
        datePurchased.setPrefWidth(250);
        HBox.setHgrow(datePurchased, Priority.ALWAYS);            
        //HBox
        listlabels.getChildren().addAll(licensePlate, category, numberofSeats, ticketPrice, driverName, datePurchased);
        listlabels.setSpacing(20);
    }
    
    
    public static void addlayoutInitialization()
    {
        if(!slidingLayoutShown)
        {
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
            
        Label DatePurchased = new Label("Date Purchased");
        DatePurchased.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        DatePicker datePurchased = new DatePicker();
        datePurchased.setValue(LocalDate.now());
            
        Button saveButton = new Button("SAVE");
        saveButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
        saveButton.setPrefWidth(100);
        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
        cancelButton.setPrefWidth(100);
        Button clearButton = new Button("CLEAR");
        clearButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
        clearButton.setPrefWidth(100);            
            
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
        addlayout.add(DatePurchased, 2, 2);
        addlayout.add(datePurchased, 3, 2);
        addlayout.add(saveButton, 0, 3);
        addlayout.add(clearButton, 2, 3);           
        addlayout.add(cancelButton, 1, 3);
        addlayout.setVisible(false);
        
        addlayout.setHgap(50);
        addlayout.setVgap(15);
        addlayout.setAlignment(Pos.TOP_LEFT);
        
        addlayout.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-padding: 10px;");
             
        
        saveButton.setOnAction(e -> {
        int row = 4;
        removeAllErrorLabels();
        resetStyle(DriverNameString);
        resetStyle(LicensePlateString);

        if (isValidLicensePlate(LicensePlateString.getText()) && !DriverNameString.getText().isEmpty()) {
            String licensenumber = LicensePlateString.getText().substring(0, 3);
            String licenseletters =  LicensePlateString.getText().substring(3).toUpperCase();
            StringBuilder validLicensePlate = new StringBuilder();
            validLicensePlate.append(licensenumber);
            validLicensePlate.append(licenseletters);        
        
            Vehicle newVehicle = new Vehicle();
            newVehicle.setLicense_plate(validLicensePlate.toString());
            newVehicle.setCategory(Vehicle.vehicleCategory.valueOf(CategoryString.getValue().toString().toUpperCase()));
            newVehicle.setNumber_of_seats(seatsspinner.getValue());
            newVehicle.setTicket_price(pricespinner.getValue());
            newVehicle.setBusDriver_name(DriverNameString.getText());
            newVehicle.setDatePurchased(datePurchased.getValue());
            newVehicle.add();
        } else {

            if (DriverNameString.getText().isEmpty()) {
                showErrorLabel("*Driver's Name Field cannot be empty.", row, DriverNameString);
                row++;
            } else {
                resetStyle(DriverNameString);
            }

            if (!isValidLicensePlate(LicensePlateString.getText())) {
                showErrorLabel("*Invalid License Plate.", row, LicensePlateString);
                row++;

                if (LicensePlateString.getText().isEmpty()) {
                    showErrorLabel("*License Plate Field cannot be empty.", row, LicensePlateString);
                    row++;
                } else {
                    showErrorLabel("*Valid license plate: 4 numbers, 3 letters.", row, LicensePlateString);
                }
            } else {
                resetStyle(LicensePlateString);
            }
        }});
        
        clearButton.setOnAction(e -> {
            LicensePlateString.clear();
            CategoryString.setValue("Bus");
            seatsspinner.setValueFactory(seatsvalueFactory);
            pricespinner.setValueFactory(pricevalueFactory);        
            datePurchased.setValue(LocalDate.now());
            DriverNameString.clear();
        });
            
        cancelButton.setOnAction(e -> 
        {
            removeAllErrorLabels();
            resetStyle(DriverNameString);
            resetStyle(LicensePlateString);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.014));
            pause.setOnFinished(event -> slideUp());
            pause.play();

        });
        
        slideDown();
      }
    }
    
    private static void showErrorLabel(String errorMessage, int row, TextField textField) {
        textField.setStyle("-fx-background-color: rgba(0,0,0,0);-fx-border-color: red;-fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        Label errorLabel = new Label(errorMessage);
        errorLabel.setStyle("-fx-background-color: rgba(0,0,0,0);-fx-text-fill: red; -fx-font-family: 'Helvetica World';");
        addlayout.add(errorLabel, 0, row);
    }

    private static void resetStyle(TextField textField) {
        System.out.println("Resetting style for: " + textField.getText());
        textField.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
    }
    
    public static void removeErrorLabel(String labelText) {
        Iterator<Node> iterator = addlayout.getChildren().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();

            if (node instanceof Label) {
                Label label = (Label) node;

                if (label.getText().equals(labelText)) {
                    iterator.remove();
                }
            }
        }
    }
    
    public static void removeAllErrorLabels()
    {
        removeErrorLabel("*Driver's Name Field cannot be empty.");
        removeErrorLabel("*License Plate Field cannot be empty.");
        removeErrorLabel("*Invalid License Plate.");
        removeErrorLabel("*Valid license plate: 4 numbers, 3 letters.");
    }
    
    public static void slideDown() {
        if (!slidingLayoutShown) {
            Duration slideduration = new Duration(300);
            Duration fadeduration = new Duration(600);

            // Slide down transition for the panel
            TranslateTransition slideDownTransition = new TranslateTransition(slideduration, addlayout);
            slideDownTransition.setToY(0);

            //Slide down transition for Vehicle.listlabels (el labels el heya license plate..)
            TranslateTransition slideDownTransition2 = new TranslateTransition(slideduration, listlabels);
            slideDownTransition2.setToY(0.0);
            
            // Slide down transition for Admin.vehicleTable
            //Admin.scrollPane.setMaxHeight(400);
            TranslateTransition slideDownTransition3 = new TranslateTransition(slideduration, Admin.scrollPane);
            slideDownTransition3.setToY(0.0);

            // Fade in transition for the panel
            FadeTransition fadeInTransition = new FadeTransition(fadeduration, addlayout);
            fadeInTransition.setToValue(1.0);

            addlayout.setVisible(true);

            // Play both transitions simultaneously
            ParallelTransition parallelTransition = new ParallelTransition(slideDownTransition, slideDownTransition2,slideDownTransition3, fadeInTransition);
            parallelTransition.play();
            slidingLayoutShown = true;
        }
    }

    public static void slideUp() {
        if (slidingLayoutShown) {
            Duration slideduration = new Duration(300);
            Duration fadeduration = new Duration(100);
            double panelHeight = addlayout.getBoundsInParent().getHeight();

            TranslateTransition slideUpTransition = new TranslateTransition(slideduration, addlayout);
            slideUpTransition.setToY(-panelHeight);

            TranslateTransition slideUpTransition2 = new TranslateTransition(slideduration, Admin.scrollPane);
            slideUpTransition2.setToY(-panelHeight);
            //Admin.scrollPane.setMaxHeight(700);
            
            TranslateTransition slideUpTransition3 = new TranslateTransition(slideduration, listlabels);
            slideUpTransition3.setToY(-panelHeight);

            FadeTransition fadeOutTransition = new FadeTransition(fadeduration, addlayout);
            fadeOutTransition.setToValue(0.0);

            fadeOutTransition.setOnFinished(e -> addlayout.setVisible(false));

            ParallelTransition parallelTransition = new ParallelTransition(slideUpTransition, slideUpTransition2, slideUpTransition3, fadeOutTransition);
            parallelTransition.setOnFinished(event -> Admin.scrollPane.setTranslateY(-panelHeight));
            parallelTransition.play();
            slidingLayoutShown = false;
        }
    }


    @Override
    public void add()
    {
        if (VehicleList.containsKey(this.License_plate)) {
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Duplicate License Plate");
            confirmation.setHeaderText(null);
            confirmation.setContentText("A vehicle with the same license plate already exists. Do you want to replace its information?");

            ButtonType replaceButton = new ButtonType("Replace");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmation.getButtonTypes().setAll(replaceButton, cancelButton);

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == cancelButton) {
                return;
            }
        }
        
        System.out.println("cancel debugging statement");
        VehicleList.put(this.License_plate, this);
        this.rowDisplay();
        System.out.println("Vehicle added successfully.");
        System.out.println("neshoof kam object 3andena: ");
        for(Vehicle v: Vehicle.VehicleList.values())
        {
            System.out.println(v.License_plate);
        }
    }


    
    @Override
    public void remove()
    {
        VehicleList.remove(this.License_plate);
        System.out.println("Vehicle removed successfully.");
    }
    
    @Override
    public void edit()
    {
        System.out.println("edit function entered");
        
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);

        Label LicensePlate = new Label("License Plate:");
        LicensePlate.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        TextField LicensePlateString = new TextField(this.License_plate);
        LicensePlateString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-font-family: 'Helvetica World';");
            
        Label BusCategory = new Label("Category:");
        BusCategory.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        ObservableList oo = FXCollections.observableArrayList("MicroBus", "MiniBus", "Bus", "MegaBus");
        ComboBox CategoryString = new ComboBox(oo);       
        CategoryString.setValue(this.Category);
            
        Label NumberofSeats = new Label("Number of Seats:");
        NumberofSeats.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        Spinner<Integer> seatsspinner = new Spinner<>();
        SpinnerValueFactory<Integer> seatsvalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, this.Number_of_seats);
        seatsspinner.setValueFactory(seatsvalueFactory);
            
        Label TicketPrice = new Label("Ticket Price:");
        TicketPrice.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        Spinner<Double> pricespinner = new Spinner<>();
        SpinnerValueFactory<Double> pricevalueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(5, 100, this.ticket_price);
        pricespinner.setValueFactory(pricevalueFactory);        
            
        Label DriverName = new Label("Driver's Name:");
        DriverName.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
        TextField DriverNameString = new TextField(this.BusDriver_name);
        DriverNameString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-font-family: 'Helvetica World';");
            
        Button applyButton = new Button("APPLY");
        applyButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
        applyButton.setPrefWidth(100);
                
        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle("-fx-background-color: #ffb000;-fx-text-fill: #0a0c26; -fx-border-color: #ffb000; -fx-border-radius: 5;"); 
        cancelButton.setPrefWidth(100);
        
        GridPane editpane = new GridPane();
        editpane.add(LicensePlate, 0, 0);
        editpane.add(LicensePlateString, 1, 0);
        editpane.add(BusCategory, 0, 1);
        editpane.add(CategoryString, 1, 1);
        editpane.add(NumberofSeats, 0, 2);
        editpane.add(seatsspinner, 1, 2);
        editpane.add(TicketPrice, 0, 3);
        editpane.add(pricespinner, 1, 3);
        editpane.add(DriverName, 0, 4);
        editpane.add(DriverNameString, 1, 4);
        editpane.add(applyButton, 0, 5);
        editpane.add(cancelButton, 1, 5);
        
        editpane.setHgap(30);
        editpane.setVgap(10);
        editpane.setAlignment(Pos.CENTER);
        
        editpane.setStyle("-fx-background-color: #090D26; -fx-padding: 10px;");
        
        applyButton.setOnAction(ev -> 
        {
            System.out.println("apply button clicked");
            if (isValidLicensePlate(LicensePlateString.getText()) && !DriverNameString.getText().isEmpty()) 
            {
                System.out.println("license plate is valid");
                
                //edit gui row
                for (Node element : Admin.vehicleTable.getChildren()) {
                    if (element instanceof HBox) {
                        System.out.println("found first hbox");
                        HBox hbox = (HBox) element;
                        if (hbox.getChildren().get(0) instanceof HBox) {
                            System.out.println("inner hbox found");
                            HBox innerHBox = (HBox) hbox.getChildren().get(0);
                            if (innerHBox.getChildren().get(0) instanceof Label) {
                                System.out.println("label found");
                                Label licenseLabel = (Label) innerHBox.getChildren().get(0);
                                if (licenseLabel.getText().equals(this.getLicense_plate())) {
                                    System.out.println("lisence plate found.");
                                    
                                    this.setLicense_plate(LicensePlateString.getText());
                                    Label updatedLPlbl = new Label(LicensePlateString.getText());
                                    updatedLPlbl.setTextFill(Color.web("white"));
                                    updatedLPlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                                    updatedLPlbl.setPrefWidth(250);

                                    this.setCategory(Vehicle.vehicleCategory.valueOf(CategoryString.getValue().toString().toUpperCase()));
                                    Label updatedCAlbl = new Label(CategoryString.getValue().toString());
                                    updatedCAlbl.setTextFill(Color.web("white"));
                                    updatedCAlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                                    updatedCAlbl.setPrefWidth(250);
                                
                                    this.setNumber_of_seats(seatsspinner.getValue());
                                    Label updatedNSlbl = new Label(seatsspinner.getValue().toString());
                                    updatedNSlbl.setTextFill(Color.web("white"));
                                    updatedNSlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                                    updatedNSlbl.setPrefWidth(250);
                                
                                    this.setTicket_price(pricespinner.getValue());
                                    Label updatedTPlbl = new Label(pricespinner.getValue().toString());
                                    updatedTPlbl.setTextFill(Color.web("white"));
                                    updatedTPlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                                    updatedTPlbl.setPrefWidth(250);
                                
                                    this.setBusDriver_name(DriverNameString.getText());
                                    Label updatedBDlbl = new Label(DriverNameString.getText());
                                    updatedBDlbl.setTextFill(Color.web("white"));
                                    updatedBDlbl.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
                                    updatedBDlbl.setPrefWidth(250);
                                
                                    innerHBox.getChildren().set(0, updatedLPlbl);
                                    innerHBox.getChildren().set(1, updatedCAlbl);
                                    innerHBox.getChildren().set(2, updatedNSlbl);
                                    innerHBox.getChildren().set(3, updatedTPlbl);
                                    innerHBox.getChildren().set(4, updatedBDlbl);

                                    break;
                                }
                            }
                        }
                    }
                }
                              
                System.out.println("hashmap edited successfully.");
                dialogStage.close();
                
            } else {
                System.out.println("validation check entered.");
                if (DriverNameString.getText().isEmpty()) {
                    DriverNameString.setStyle("-fx-background-color: rgba(0,0,0,0);-fx-text-fill: #ffb000; -fx-border-color: red; -fx-font-family: 'Helvetica World';");
                } else {
                    DriverNameString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-border-color: #ffb000; -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
                }

                if (LicensePlateString.getText().isEmpty() || !isValidLicensePlate(LicensePlateString.getText())) {
                    LicensePlateString.setStyle("-fx-background-color: rgba(0,0,0,0);-fx-text-fill: #ffb000; -fx-border-color: red; -fx-font-family: 'Helvetica World';");
                } else {
                    LicensePlateString.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-border-color: #ffb000; -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");
                }
            }
        });
        
        cancelButton.setOnAction(event -> 
        {
            System.out.println("cancel button clicked");
            dialogStage.close();
        });
                
        Scene editScene = new Scene(editpane, 500, 300);
        dialogStage.setScene(editScene);
        dialogStage.setTitle("Edit Vehicle");
        dialogStage.showAndWait();

        
    }
    
    @Override
    public void search()        
    {
        TextField searchBar = (TextField) Admin.horizontalLayoutBox.getChildren().get(1);
        ComboBox<String> searchBy = (ComboBox<String>) Admin.horizontalLayoutBox.getChildren().get(3);
        String searchFilter = searchBy.getValue();
        Admin.vehicleTable.getChildren().clear();
        String searchText = searchBar.getText().toLowerCase();
        
        switch (searchFilter)
        {
            case "License Plate":
            {
                for(String s: VehicleList.keySet())
                {
                    if(s.toLowerCase().startsWith(searchText))
                    {
                        Vehicle v = VehicleList.get(s);
                        v.rowDisplay();
                    }
                }
                break;
            }
            case "Category": 
            {
                for(Vehicle v: VehicleList.values())
                {
                    if(String.valueOf(v.Category).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                }
                break;
            }
            case "Number of Seats": 
            {
                for(Vehicle v: VehicleList.values())
                {
                    if(String.valueOf(v.Number_of_seats).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                }
                break;
            }
            case "Price per Seat": 
            {
                for(Vehicle v: VehicleList.values())
                {
                    if(String.valueOf(v.ticket_price).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                }
                break;
            }
            case "Driver's Name": 
            {
                for(Vehicle v: VehicleList.values())
                {
                    if(v.BusDriver_name.toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                }
                break;
            }
            default:
            {
                for(Vehicle v: VehicleList.values())
                {
                    if(v.License_plate.toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                    else if(String.valueOf(v.Category).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                    else if(String.valueOf(v.Number_of_seats).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                    else if(String.valueOf(v.ticket_price).toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                    else if(v.BusDriver_name.toLowerCase().startsWith(searchText))
                    {
                        v.rowDisplay();
                    }
                }
                break;
            }
        }
    }
    
//    public static void displayVehicles() throws FileNotFoundException {
//    
//        Admin.vehicleTable.getChildren().clear();
//    
//        for (Vehicle v : VehicleList.values()) {
//            v.rowDisplay();
//        }
//    }


    public void rowDisplay()
    {
        try {
            Label licensePlate = new Label(this.getLicense_plate());
            licensePlate.setTextFill(Color.web("white"));
            licensePlate.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            licensePlate.setPrefWidth(250);
            HBox.setHgrow(licensePlate, Priority.ALWAYS);
            
            Label category = new Label(this.getCategory().toString());
            category.setTextFill(Color.web("white"));
            category.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            category.setPrefWidth(250);
            HBox.setHgrow(category, Priority.ALWAYS);
            
            Label numberofSeats = new Label(String.valueOf(this.getNumber_of_seats()));
            numberofSeats.setTextFill(Color.web("white"));
            numberofSeats.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            numberofSeats.setPrefWidth(250);
            HBox.setHgrow(numberofSeats, Priority.ALWAYS);
            
            Label ticketPrice = new Label(String.valueOf(this.getTicket_price()));
            ticketPrice.setTextFill(Color.web("white"));
            ticketPrice.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            ticketPrice.setPrefWidth(250);
            HBox.setHgrow(ticketPrice, Priority.ALWAYS);
            
            Label driverName = new Label(this.getBusDriver_name());
            driverName.setTextFill(Color.web("white"));
            driverName.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            driverName.setPrefWidth(250);
            HBox.setHgrow(driverName, Priority.ALWAYS);
            
            Label DatePurchased = new Label(this.getDatePurchased().toString());
            DatePurchased.setTextFill(Color.web("white"));
            DatePurchased.setFont(Font.font("Helvetica World", FontWeight.BOLD, 20));
            DatePurchased.setPrefWidth(250);
            HBox.setHgrow(DatePurchased, Priority.ALWAYS);            
            
            //icons
            Image editicon = new Image(new FileInputStream("/home/jana/Downloads/pen.png"));
            ImageView editimageView = new ImageView(editicon);
            editimageView.setFitHeight(30);
            editimageView.setPreserveRatio(true);
            
            Image deleteicon = new Image(new FileInputStream("/home/jana/Downloads/bin(3).png"));
            ImageView deleteimageView = new ImageView(deleteicon);
            deleteimageView.setFitHeight(30);
            deleteimageView.setPreserveRatio(true);                  
            
            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
            editButton.setGraphic(editimageView);
            
            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
            deleteButton.setGraphic(deleteimageView);
            
            Button viewReportButton = new Button("View Report");
            viewReportButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
            //viewReportButton.setGraphic(reportimageView);
            
            String normalStyle = "-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);";
            String hoverStyle = "-fx-text-fill: #ffb000; -fx-background-color: rgba(0, 0, 0, 0);";
            viewReportButton.setStyle(normalStyle);
            
            HBox tempHBox = new HBox(new HBox(30,licensePlate, category, numberofSeats, ticketPrice, driverName,DatePurchased), new HBox(10,viewReportButton, editButton, deleteButton));
            Admin.vehicleTable.getChildren().add(tempHBox);
            
            //EDIT BUTTON EVENT-HANDLING
            editButton.setOnAction(e -> {
                this.edit();
            });
            
            //DELETE BUTTON EVENT-HANDLING
            deleteButton.setOnAction(e -> {
                Admin.vehicleTable.getChildren().remove(tempHBox);
                this.remove();
            });
            
            //VIEW REPORT BUTTON EVENT-HANDLING
            //hover event
            viewReportButton.setOnMouseEntered(eh ->
            {
                viewReportButton.setStyle(hoverStyle);
            });
            
            //non-hover event
            viewReportButton.setOnMouseExited(eh ->
            {
                viewReportButton.setStyle(normalStyle);
            });
            
            //clicked event
            viewReportButton.setOnAction(eh ->
            {
                this.viewReports();
            });
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public static void initializeAvailibilityMap()
    {
        for(Trips t: Trips.TripsMap.values())
        {
            for(Vehicle v: VehicleList.values())
            {
                if(t.getVehicle_id().equalsIgnoreCase(v.License_plate))
                {
                    v.num_of_available_seats = v.Number_of_seats;
                    AvailibilityMap.put(t.getTrip_id(), v.num_of_available_seats);
                }
            }
        } 
    }
  
    public static boolean newBooking(int trip_id, int number_of_passengers) {
        for(Map.Entry<Integer, Integer> entry : AvailibilityMap.entrySet()) 
        {
            int key = entry.getKey();
            int value = entry.getValue();
            if(trip_id == key)
            {
                if(value - number_of_passengers >= 0)
                {
                    AvailibilityMap.replace(key, (value - number_of_passengers));
                }
                else
                {
                    System.out.println("Number of seats not available. Number of available seats: " + value);
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    public static void cancelBooking(int booking_id, int number_of_cancelled_passengers) {
        int temp_tripid = 0;
        for(Booking book: Booking.getBook().values())
        {
            if(booking_id == book.getBooking_id())
            {
                temp_tripid = book.getTrip_id();
                break;
            }
        }
        for(Map.Entry<Integer, Integer> entry : AvailibilityMap.entrySet()) 
        {
            int key = entry.getKey();
            int value = entry.getValue();
            if(temp_tripid == key)
            {
                AvailibilityMap.replace(key, (value + number_of_cancelled_passengers));
            }
        }
    }
    
    public String numberofBookings(LocalDateTime start, LocalDateTime end){
        int count = 0;
        for(Booking b: Booking.getBook().values())
        {
            if(b.getLicense_plate().equals(this.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
            {
                count++;
            }
        }
        if (count == 0)
        {
            String none = "No booking exists for the given vehicle within the given time frame.";
            return none;
        }   
        return String.valueOf(count);
    }
    
    public Vehicle mostBooked(LocalDateTime start, LocalDateTime end)
    {
        Vehicle mostbooked =  new Vehicle();
        int maxBookings = 0;
        for(Vehicle vehicle: VehicleList.values())
        {
            int countBookings = 0;
            for(Booking b: Booking.getBook().values())
            {
                if(b.getLicense_plate().equals(vehicle.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
                {
                    countBookings++;
                }
            }
            if(countBookings > maxBookings)
            {
                maxBookings = countBookings;
                mostbooked = vehicle;
            }
        } 
        return mostbooked;
    }
    
    public String numberofTrips(LocalDateTime start, LocalDateTime end)
    {
        int count = 0;
        for(Trips t: Trips.TripsMap.values())
        {
            if(t.getVehicle_id().equals(this.getLicense_plate()) && (t.getDepartureDateTime().isAfter(start) || t.getDepartureDateTime().isEqual(start)) && (t.getDepartureDateTime().isBefore(end) || t.getDepartureDateTime().isEqual(end)))
            {
                count++;
            }
        }
        if (count == 0)
        {
            String none = "No trip exists for the given vehicle within the given time frame.";
            return none;
        }   
        return String.valueOf(count);
    }
        
    public Vehicle mostRevenue(LocalDateTime start, LocalDateTime end)
    {
        Vehicle mostRevenue = new Vehicle();
        double maxRevenue = 0;
        for(Vehicle vehicle: VehicleList.values())
        {
            double tempRevenue = 0;
            for(Booking b: Booking.getBook().values())
            {
                if(b.getLicense_plate().equals(vehicle.getLicense_plate()) && (b.getBooking_time().isAfter(start) || b.getBooking_time().isEqual(start)) && (b.getBooking_time().isBefore(end) || b.getBooking_time().isEqual(end)))
                {
                    tempRevenue += vehicle.getTicket_price();
                }
            }
            if(tempRevenue > maxRevenue)
            {
                maxRevenue = tempRevenue;
                mostRevenue = vehicle;
            }
        } 
        return mostRevenue;
    }
    

    @Override
    public String toString()
    {
      return ("License plate: " + License_plate +", Category: " + Category + ", Number of seats: " + Number_of_seats + ", Ticket price: " + ticket_price + ", Bus Driver: " + BusDriver_name);
    }
      
      
    public void viewReports() //UNFINISHED
    {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
        
        Label LicensePlate = new Label("License Plate:");
        LicensePlate.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");        
        Label LicensePlatelbl = new Label(this.License_plate);
        LicensePlatelbl.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");        
        
        Label BusCategory = new Label("Bus Category:");
        BusCategory.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");        
        Label Categorylbl = new Label(this.Category.toString());
        Categorylbl.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");        
        
        Label startDate = new Label("Information Start Date:");
        startDate.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                
        DatePicker start = new DatePicker();
        start.setValue(this.datePurchased);
        
        Label endDate = new Label("Information End Date:");
        endDate.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                
        DatePicker end = new DatePicker();
        end.setValue(LocalDate.now());
        
        Label numberofTrips = new Label("Number of Trips:");
        numberofTrips.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                        
        Label nooftrips = new Label();
        nooftrips.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");                        
        
        Label numberofBookings = new Label("Number of Bookings:");
        numberofBookings.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                                
        Label noofbookings = new Label();        
        noofbookings.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");                        
       
        Label mostBookedVehicle = new Label("Most Booked Vehicle:");
        mostBookedVehicle.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                                
        Label mostbooked = new Label();  
        mostbooked.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");                        
        
        Label mostRevenueVehicle = new Label("Most Revenue Vehicle:");
        mostRevenueVehicle.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ffb000; -fx-font-family: 'Helvetica World';");                                        
        Label mostrevenue = new Label(); 
        mostrevenue.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white; -fx-font-family: 'Helvetica World';");                        

        
        if (start.getValue() != null && end.getValue() != null) {
            nooftrips.setText(numberofTrips(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            noofbookings.setText(numberofBookings(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            mostbooked.setText(mostBooked(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
            mostrevenue.setText(mostRevenue(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
        }
        
        start.setOnInputMethodTextChanged(eh ->
        {
            nooftrips.setText(numberofTrips(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            noofbookings.setText(numberofBookings(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            mostbooked.setText(mostBooked(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
            mostrevenue.setText(mostRevenue(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
        });
            
        end.setOnInputMethodTextChanged(eh ->
        {
            nooftrips.setText(numberofTrips(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            noofbookings.setText(numberofBookings(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()));
            mostbooked.setText(mostBooked(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
            mostrevenue.setText(mostRevenue(start.getValue().atStartOfDay(), end.getValue().atStartOfDay()).toString());
        });
        
        GridPane gridpane = new GridPane();
        gridpane.add(LicensePlate, 0, 0);
        gridpane.add(LicensePlatelbl, 1, 0);
        gridpane.add(BusCategory, 0, 1);
        gridpane.add(Categorylbl, 1, 1);
        gridpane.add(startDate, 0, 2);
        gridpane.add(start, 1, 2);
        gridpane.add(endDate, 0, 3);
        gridpane.add(end, 1, 3);
        gridpane.add(numberofTrips, 0, 4);
        gridpane.add(nooftrips, 1, 4);
        gridpane.add(numberofBookings, 0, 5);
        gridpane.add(noofbookings, 1, 5);
        gridpane.add(mostBookedVehicle, 0, 6);
        gridpane.add(mostbooked, 1, 6);
        gridpane.add(mostRevenueVehicle, 0, 7);
        gridpane.add(mostrevenue, 1, 7);
        
        Insets in = new Insets(10);        
        
        gridpane.setHgap(20);
        gridpane.setVgap(10);
        gridpane.setPadding(in);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setStyle("-fx-background-color: #090D26; -fx-padding: 10px;");

        
        Scene scene = new Scene(gridpane, 810, 300);
        dialogStage.setTitle("Vehicle Report");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
      
    public static void updateFile() {
        int i = 0;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(vehicleFile))) {
            for (Vehicle newVehicle : Vehicle.VehicleList.values()) {
                out.writeObject(newVehicle);
                i++;
            }
            System.out.println("number of objects written into file: " + i);
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public static void readFromFile() {
        int i = 0;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(vehicleFile))) {
            while (true) {
                try {
                    Vehicle newVehicle = (Vehicle) in.readObject();
                    newVehicle.add();
                    i++;
                } catch (EOFException e) {
                    // End of file reached
                    System.out.println("number of objects read from file into hashmap: " + i);
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Error reading object: " + e);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }


    private static boolean isValidLicensePlate(String licensePlate) {
        return licensePlate.matches("[0-9]{4}[A-Za-z]{3}");
    }
}


