/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.trial;

import java.io.DataInputStream;
import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Map;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.BorderFactory;
import project.trial.Trips.Destination;
import project.trial.Vehicle.vehicleCategory;
import project.trial.Vehicle;

/**
 *
 * @author Roaa
 */
public class Trips implements manages, Serializable {

    public enum Destination {
        CAIRO("Cairo"),
        ALEXANDRIA("Alexandria"),
        HURGHADA("Hurghada"),
        MARSA_ALAM("Marsa Alam"),
        SOKHNA("Sokhna"),
        NUWEIBA("Nuweiba"),
        MARSA_MATROUH("Marsa Matrouh"),
        RAS_SEDR("Ras Sedr"),
        PORT_SAID("Port Said");

        private final String displayName;

        Destination(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static Map<String, String> getDisplayNames() {
            Map<String, String> displayNames = new HashMap<>();
            for (Destination destination : values())
            {
                displayNames.put(destination.name(), destination.getDisplayName());
            }
            return displayNames;
        }

        public static List<String> getDestinationBusStops(Destination destination) {
            return destinationBusStops.get(destination);
        }

        public static HashMap<Destination, List<String>> getDestinationBusStops() {
            return destinationBusStops;
        }

        public static List<String> getCairoBusStops() {
            return cairoBusStops;
        }

        public static List<String> getAlexBusStops() {
            return alexBusStops;
        }

        public static List<String> getHurghadaBusStops() {
            return hurghadaBusStops;
        }

        public static List<String> getMarsaAlamBusStops() {
            return marsaAlamBusStops;
        }

        public static List<String> getSokhnaBusStops() {
            return sokhnaBusStops;
        }

        public static List<String> getNuweibaBusStops() {
            return nuweibaBusStops;
        }

        public static List<String> getMarsaMatrouhBusStops() {
            return marsaMatrouhBusStops;
        }

        public static List<String> getRasSedrBusStops() {
            return rasSedrBusStops;
        }

        public static List<String> getPortSaidBusStops() {
            return portSaidBusStops;
        }

        public static HashMap<Destination, List<String>> destinationBusStops = new HashMap<>();
        public static List<String> cairoBusStops = new ArrayList<>();
        public static final List<String> alexBusStops = new ArrayList<>();
        public static final List<String> hurghadaBusStops = new ArrayList<>();
        public static final List<String> marsaAlamBusStops = new ArrayList<>();
        public static final List<String> sokhnaBusStops = new ArrayList<>();
        public static final List<String> nuweibaBusStops = new ArrayList<>();
        public static final List<String> marsaMatrouhBusStops = new ArrayList<>();
        public static final List<String> rasSedrBusStops = new ArrayList<>();
        public static final List<String> portSaidBusStops = new ArrayList<>();

        static
        {

            // Cairo
            cairoBusStops.add("Cairo (Tahrir)");
            cairoBusStops.add("Cairo (Giza)");
            cairoBusStops.add("Cairo (Almaza)");
            cairoBusStops.add("Tagamou El-Khames");
            cairoBusStops.add("NasrCity Station");
            cairoBusStops.add("Nady El Sekka");
            cairoBusStops.add("ElRehab");
            cairoBusStops.add("Madinaty");
            cairoBusStops.add("Cairo (Kolaly)");
            cairoBusStops.add("Sixth of October");
            cairoBusStops.add("Alex Gate");

            // Alexandria
            alexBusStops.add("Sedi Gaber - Smouha");
            alexBusStops.add("Alexandria (Moharam BK)");
            alexBusStops.add("Amirya");
            alexBusStops.add("Miamy");

            //  Hurghada
            hurghadaBusStops.add("Hurghada");
            hurghadaBusStops.add("Ahyaa");

            //  marsa allam
            marsaAlamBusStops.add("Marsa Alam");
            marsaAlamBusStops.add("Marsa Alam 2");
            //  porto elsokhna
            sokhnaBusStops.add("Porto El Sokhna");
            sokhnaBusStops.add("Porto South Beach");
            sokhnaBusStops.add("Cancun");
            sokhnaBusStops.add("Mountain View 1 (Dayra Camp)");
            sokhnaBusStops.add("Grand Ocean");

            //  nuweiba
            nuweibaBusStops.add("Nuweiba");
            nuweibaBusStops.add("Taba Heights");

            //  marsa Matrouh
            marsaMatrouhBusStops.add("Marsa Matrouh");
            marsaMatrouhBusStops.add("Porto Matrouh");

            //  ras sedr
            rasSedrBusStops.add("Mousa Coast");
            rasSedrBusStops.add("Matarma Bay_ Ras Sedr");
            rasSedrBusStops.add("Tavira_ Ras Sedr");
            rasSedrBusStops.add("Lahaisenda_ Ras Sedr");

            //  port siad
            portSaidBusStops.add("Port Said Downtown");
            portSaidBusStops.add("Port Said");
            // Add more bus stops as needed

            // Associate each destination with its list of bus stops
            destinationBusStops.put(Destination.CAIRO, cairoBusStops);
            destinationBusStops.put(Destination.ALEXANDRIA, alexBusStops);
            destinationBusStops.put(Destination.HURGHADA, hurghadaBusStops);
            destinationBusStops.put(Destination.MARSA_ALAM, marsaAlamBusStops);
            destinationBusStops.put(Destination.SOKHNA, sokhnaBusStops);
            destinationBusStops.put(Destination.NUWEIBA, nuweibaBusStops);
            destinationBusStops.put(Destination.MARSA_MATROUH, marsaMatrouhBusStops);
            destinationBusStops.put(Destination.RAS_SEDR, rasSedrBusStops);
            destinationBusStops.put(Destination.PORT_SAID, portSaidBusStops);

        }

    }
    private static final long serialVersionUID = 6159440674871578737L;
    private int trip_id;                     //unique *
    private String vehicle_id;
    private transient Vehicle vehicle;
    private Destination From_Destination;
    private Destination To_Destination;
    private String From_BusStop;
    private String To_BusStop;
    private double price;            //price range  
    private LocalDateTime ArrivalDateTime;
    private LocalDateTime DepartureDateTime;
    private transient LocalTime ArrivalTime;
    private transient LocalTime DepartureTime;
    private transient LocalDate ArrivalDate;
    private transient LocalDate DepartureDate;
    public static HashMap<Integer, Trips> TripsMap = new HashMap<>();
    public transient int countTrips = 0;
    private transient String labelStyle = "-fx-text-fill: #F0F0F0;";
    private transient Font boldFont = Font.font("Arial", FontWeight.BOLD, 18);
    private transient String comboBoxStyle = "-fx-background-color: #F0F0F0;"
            + "-fx-border-color: #B0B0B0; "
            + "-fx-border-radius: 3;"
            + "-fx-padding: 3 5 3 5;";
    private transient String timeStyle = "-fx-background-color: #F0F0F0;"
            + "-fx-border-color: #B0B0B0; "
            + "-fx-border-radius: 3;"
            + "-fx-padding: 3 5 3 5;"
            + "-fx-arrow-button-fill: #000000;";
    private transient Stage primaryStage;
    private transient VBox vbox = new VBox();

//Constructors
    public Trips() {

    }

    public Trips(int trip_id, String vehicle_id, Destination From_Destination, Destination To_Destination, String From_BusStop, String To_BusStop, double price, LocalTime ArrivalTime, LocalTime DepartureTime, LocalDateTime ArrivalDateTime, LocalDateTime DepartureDateTime) {
        this.trip_id = trip_id;
        this.vehicle_id = vehicle_id;
        this.From_Destination = From_Destination;
        this.To_Destination = To_Destination;
        this.From_BusStop = From_BusStop;
        this.To_BusStop = To_BusStop;
        this.price = price;
        this.ArrivalTime = ArrivalTime;
        this.DepartureTime = DepartureTime;
        this.ArrivalDateTime = ArrivalDateTime;
        this.DepartureDateTime = DepartureDateTime;
    }

    public Trips(int trip_id, String vehicle_id, Destination From_Destination, Destination To_Destination, String From_BusStop, String To_BusStop, double price, LocalDateTime ArrivalDateTime, LocalDateTime DepartureDateTime) {
        this.trip_id = trip_id;
        this.vehicle_id = vehicle_id;
        this.From_Destination = From_Destination;
        this.To_Destination = To_Destination;
        this.From_BusStop = From_BusStop;
        this.To_BusStop = To_BusStop;
        this.price = price;
        this.ArrivalDateTime = ArrivalDateTime;
        this.DepartureDateTime = DepartureDateTime;
    }

//Setters & Getters
    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getVehicle_id() {// de leeha alert if null ,lama ngm3 el classes nb2a nshlha 
        return (vehicle_id != null) ? vehicle_id : "";
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getArrivalDateTime() {
        return ArrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime ArrivalDateTime) {
        this.ArrivalDateTime = ArrivalDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime DepartureDateTime) {
        this.DepartureDateTime = DepartureDateTime;
    }

    public int getCountTrips() {
        return countTrips++;
    }

    public void setCountTrips(int countTrips) {
        this.countTrips = countTrips;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Destination getFrom_Destination() {
        return From_Destination;
    }

    public void setFrom_Destination(Destination From_Destination) {
        this.From_Destination = From_Destination;
    }

    public Destination getTo_Destination() {
        return To_Destination;
    }

    public void setTo_Destination(Destination To_Destination) {
        this.To_Destination = To_Destination;
    }

    public String getFrom_BusStop() {
        return From_BusStop;
    }

    public void setFrom_BusStop(String From_BusStop) {
        this.From_BusStop = From_BusStop;
    }

    public String getTo_BusStop() {
        return To_BusStop;
    }

    public void setTo_BusStop(String To_BusStop) {
        this.To_BusStop = To_BusStop;
    }

    public LocalTime getArrivalTime() {
        if (ArrivalTime != null)
        {
            return ArrivalTime;
        } else
        {
            return LocalTime.MIN;
        }
    }

    public void setArrivalTime(LocalTime ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    public LocalTime getDepartureTime() {
        if (DepartureTime != null)
        {
            return DepartureTime;
        } else
        {
            return LocalTime.MIN;
        }
    }

    public void setDepartureTime(LocalTime DepartureTime) {
        this.DepartureTime = DepartureTime;
    }

    public LocalDate getArrivalDate() {
        if (ArrivalDate != null)
        {
            return ArrivalDate;
        } else
        {
            return LocalDate.MIN;
        }
    }

    public void setArrivalDate(LocalDate ArrivalDate) {
        this.ArrivalDate = ArrivalDate;
    }

    public LocalDate getDepartureDate() {
        if (DepartureDate != null)
        {
            return DepartureDate;
        } else
        {
            return LocalDate.MIN;
        }
    }

    public void setDepartureDate(LocalDate DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public static HashMap<Integer, Trips> getTripsMap() {
        return TripsMap;
    }

    public static void setTripsMap(HashMap<Integer, Trips> TripsMap) {
        Trips.TripsMap = TripsMap;
    }

    @Override
    public String toString() {
        return "Trips{trip_id=" + trip_id + ", vehicle_id=" + vehicle_id + ", From_Destination="
                + From_Destination + ", To_Destination=" + To_Destination + ", From_BusStop=" + From_BusStop
                + ", To_BusStop=" + To_BusStop + ", price=" + price + ", ArrivalDateTime=" + ArrivalDateTime
                + ", DepartureDateTime=" + DepartureDateTime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.vehicle_id);
        hash = 59 * hash + Objects.hashCode(this.From_Destination);
        hash = 59 * hash + Objects.hashCode(this.To_Destination);
        hash = 59 * hash + Objects.hashCode(this.From_BusStop);
        hash = 59 * hash + Objects.hashCode(this.To_BusStop);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.ArrivalDateTime);
        hash = 59 * hash + Objects.hashCode(this.DepartureDateTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Trips other = (Trips) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price))
        {
            return false;
        }
        if (!Objects.equals(this.vehicle_id, other.vehicle_id))
        {
            return false;
        }
        if (!Objects.equals(this.From_BusStop, other.From_BusStop))
        {
            return false;
        }
        if (!Objects.equals(this.To_BusStop, other.To_BusStop))
        {
            return false;
        }
        if (this.From_Destination != other.From_Destination)
        {
            return false;
        }
        if (this.To_Destination != other.To_Destination)
        {
            return false;
        }
        if (!Objects.equals(this.ArrivalDateTime, other.ArrivalDateTime))
        {
            return false;
        }
        return Objects.equals(this.DepartureDateTime, other.DepartureDateTime);
    }

    public static void WriteBinaryFile() throws FileNotFoundException {
        File file = new File("TripsB.dat");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
        {
            for (Trips trip : TripsMap.values())
            {
                out.writeObject(trip);
            }
        } catch (IOException e)
        {
            e.printStackTrace(); // Print the stack trace for debugging
        }
        TripsMap.values().forEach(tri -> System.out.println(tri));
        System.out.println("Data written to " + file);
    }

    public static void ReadBinaryFile() throws FileNotFoundException {
        TripsMap.clear(); // Clear the map before reading

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("TripsB.dat")));)
        {
            System.out.println("File found");
            while (true)
            {
                try
                {
                    System.out.println("Reading");
                    Trips trip = (Trips) in.readObject();
                    TripsMap.put(trip.getTrip_id(), trip);
                    System.out.println("Successfully read Trip object from file. Trip ID: " + trip.getTrip_id());
                    System.out.println(trip);
                } catch (EOFException e)
                {
                    System.out.println("End of file reached");
                    break;
                } catch (ClassNotFoundException | IOException e)
                {
                    if (e instanceof EOFException)
                    {
                        System.out.println("File is empty or contains no valid objects.");
                    } else
                    {
                        System.out.println("Error reading object: " + e);
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (IOException ex)
        {
            System.out.println("IOException while reading from file: " + ex.getMessage());
        }
    }

    public static void writeToFile(Trips trip) throws IOException {
        File file = new File("Trips.txt");
        System.out.println("File exists: " + file.exists());

        try (FileWriter writer = new FileWriter(file, false))
        {

            for (Map.Entry<Integer, Trips> entry : TripsMap.entrySet())
            {
                int tripId = entry.getKey();
                trip = entry.getValue();
                writer.write("Trip ID: " + trip.trip_id + "\n");
                writer.write("FromDestination: " + trip.From_Destination + "\n"
                        + "From busStop: " + trip.From_BusStop + "\n"
                        + "ToDestination: " + trip.To_Destination + "\n"
                        + "To busStop: " + trip.To_BusStop + "\n"
                        + "License plate " + trip.vehicle_id + "\n"
                        + "Price: " + trip.price + "\n"
                        + "Departure dateTime: " + trip.DepartureDateTime + "\n"
                        + "Arrival Datetime: " + trip.ArrivalDateTime + "\n"
                );
                writer.write("\n");
            }
            writer.close();
        }
        System.out.println("Data written to " + file);
        System.out.println("File path: " + file.getAbsolutePath());
    }

    public static int generateRandomID() {
        int min = 1000;
        int max = 9000;
        Random random = new Random();

        int randomID;
        boolean isDuplicate;

        do
        {
            randomID = random.nextInt((max - min) + 1) + min;
            isDuplicate = Trips.TripsMap.containsKey(randomID);
        } while (isDuplicate);

        return randomID;
    }

    public static VBox create_destination_ComboBox(Font font, int spacing) {
        // ComboBox for Destinations
        ObservableList<String> destOptions = FXCollections.observableArrayList();
        for (Destination dest : Destination.values())
        {
            destOptions.add(dest.getDisplayName());
        }
        ComboBox<String> destinationCBO = new ComboBox<>(destOptions);
        destinationCBO.setPrefWidth(100); // Set the preferred width in pixels
        destinationCBO.setPrefHeight(30); // Set the preferred height in pixels
//        destinationCBO.setStyle("-fx-background-color: white;");
        // ComboBox for Bus Stops
        ComboBox<String> busStopsCBO = new ComboBox<>();
        busStopsCBO.setPrefWidth(100); // Set the preferred width in pixels
        busStopsCBO.setPrefHeight(30); // Set the preferred height in pixels
        busStopsCBO.setStyle(
                "-fx-background-color: F0F0F0; "
                + "-fx-border-color: white; "
                + "-fx-border-width: 0.5px; "
                + "-fx-padding: 1px; "
                + "-fx-font-family: 'Arial'; "
                + // Set the font family
                "-fx-font-size: 13px;"
        );
        destinationCBO.setStyle(
                "-fx-background-color: F0F0F0; "
                + "-fx-border-color: white; "
                + "-fx-border-width: 0.5px; "
                + "-fx-padding: 1px; "
                + "-fx-font-family: 'Arial'; "
                + // Set the font family
                "-fx-font-size: 13px;"
        );

// Update bus stops when the selected destination changes
        destinationCBO.setOnAction(event ->
        {
            String selectedDestination = destinationCBO.getValue();
            selectedDestination = selectedDestination.replaceAll("\\s", "_");
            List<String> busStopsList = Destination.destinationBusStops.get(Destination.valueOf(selectedDestination.toUpperCase()));
            if (busStopsList != null && !busStopsList.isEmpty())
            {
                busStopsCBO.setVisible(true);
                ObservableList<String> busStopObs = FXCollections.observableArrayList(busStopsList);
                busStopsCBO.setItems(busStopObs);
            } else
            {
                busStopsCBO.setVisible(false);
                busStopsCBO.getItems().clear();
                busStopsCBO.setValue("no"); // Set bus stops to no when there are no bus stops
            }
        });

        busStopsCBO.setVisible(false);

        VBox vbox = new VBox(spacing, destinationCBO, busStopsCBO);

        return vbox;
    }

    public VBox DisplayTrips() {
        System.out.println("TripsMap size: " + Trips.TripsMap.size());
        try
        {
            Trips.ReadBinaryFile();
        } catch (FileNotFoundException e)
        {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("TripsMap size: " + Trips.TripsMap.size());
        int count = 0;
        VBox vbox = new VBox(30);
        String frombusStop;
        String tobusStop;
        GridPane displayGrid = new GridPane();

        for (Trips trip : Trips.TripsMap.values())
        {
            frombusStop = trip.getFrom_BusStop();
            tobusStop = trip.getTo_BusStop();
            HBox pane = new HBox(45);
            pane.setAlignment(Pos.CENTER);
            Button editButton = new Button();
            Image delete_img = new Image("file:///C:/Users/Roaa/Downloads/trash.png");
            ImageView iconView = new ImageView(delete_img);
            iconView.setFitHeight(20);
            iconView.setFitWidth(20);
            Image edit_img = new Image("file:///C:/Users/Roaa/Downloads/draw.png");
            ImageView iconView1 = new ImageView(edit_img);
            iconView1.setFitHeight(20);
            iconView1.setFitWidth(20);
            Button deleteButton = new Button();
            deleteButton.setGraphic(iconView);
            deleteButton.setStyle("-fx-background-color:transparent;");
            editButton.setGraphic(iconView1);
            editButton.setStyle("-fx-background-color:transparent;");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedArrivalDateTime = trip.getArrivalDateTime().format(formatter);
            String formattedDepartureDateTime = trip.getDepartureDateTime().format(formatter);
            Label tripIdLabel = new Label(Integer.toString(trip.getTrip_id()));
            tripIdLabel.setMinWidth(10);
            Label fromBusStopLabel = new Label(frombusStop);
            fromBusStopLabel.setMinWidth(150);
            Label toBusStopLabel = new Label(tobusStop);
            toBusStopLabel.setMinWidth(150);
            Label departureLabel = new Label(formattedDepartureDateTime);
            departureLabel.setMinWidth(100);
            Label arrivalLabel = new Label(formattedArrivalDateTime);
            arrivalLabel.setMinWidth(100);
            Label vehicleIdLabel = new Label(trip.getVehicle_id());
            vehicleIdLabel.setMinWidth(40);
            Label priceLabel = new Label(Double.toString(trip.getPrice()));
            priceLabel.setMinWidth(10);
            pane.getChildren().addAll(
                    tripIdLabel,
                    fromBusStopLabel,
                    toBusStopLabel,
                    departureLabel,
                    arrivalLabel,
                    vehicleIdLabel,
                    priceLabel,
                    editButton,
                    deleteButton
            );
            vbox.getChildren().add(pane);
            editButton.setOnAction(event -> edit(trip, pane, vbox));
            deleteButton.setOnAction(event -> handleDeleteButton(trip, pane, vbox));
            count++;
        }
        return vbox;
    }

    public VBox DisplayTrips(String searchValue) {
        int count = 0;
        VBox vbox = new VBox(30);

        for (Trips trip : Trips.TripsMap.values())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedArrivalDateTime = trip.getArrivalDateTime().format(formatter);
            String formattedDepartureDateTime = trip.getDepartureDateTime().format(formatter);
            String stringTrip = String.format("%s %s %s %d %.2f %s %s ",
                    trip.getFrom_BusStop(),
                    trip.getTo_BusStop(),
                    trip.getVehicle_id(),
                    trip.getTrip_id(),
                    trip.getPrice(),
                    formattedArrivalDateTime,
                    formattedDepartureDateTime);
            if (stringTrip.toLowerCase().contains(searchValue.toLowerCase()))
            {
                HBox pane = new HBox(45);
                pane.setAlignment(Pos.CENTER);
                Button editButton = new Button();
                Image delete_img = new Image("file:///C:/Users/Roaa/Downloads/trash.png");
                ImageView iconView = new ImageView(delete_img);
                iconView.setFitHeight(20);
                iconView.setFitWidth(20);
                Image edit_img = new Image("file:///C:/Users/Roaa/Downloads/draw.png");
                ImageView iconView1 = new ImageView(edit_img);
                iconView1.setFitHeight(20);
                iconView1.setFitWidth(20);
                Button deleteButton = new Button();
                deleteButton.setGraphic(iconView);
                deleteButton.setStyle("-fx-background-color:transparent;");
                editButton.setGraphic(iconView1);
                editButton.setStyle("-fx-background-color:transparent;");
                Label tripIdLabel = new Label(Integer.toString(trip.getTrip_id()));
                tripIdLabel.setMinWidth(10);
                Label fromBusStopLabel = new Label(trip.getFrom_BusStop());
                fromBusStopLabel.setMinWidth(150);
                Label toBusStopLabel = new Label(trip.getTo_BusStop());
                toBusStopLabel.setMinWidth(150);
                Label departureLabel = new Label(formattedDepartureDateTime);
                departureLabel.setMinWidth(100);
                Label arrivalLabel = new Label(formattedArrivalDateTime);
                arrivalLabel.setMinWidth(100);
                Label vehicleIdLabel = new Label(trip.getVehicle_id());
                vehicleIdLabel.setMinWidth(40);
                Label priceLabel = new Label(Double.toString(trip.getPrice()));
                priceLabel.setMinWidth(10);
                pane.getChildren().addAll(
                        tripIdLabel,
                        fromBusStopLabel,
                        toBusStopLabel,
                        departureLabel,
                        arrivalLabel,
                        vehicleIdLabel,
                        priceLabel,
                        editButton,
                        deleteButton
                );
                vbox.getChildren().add(pane);
                editButton.setOnAction(event -> edit(trip, pane, vbox));
                deleteButton.setOnAction(event -> handleDeleteButton(trip, pane, vbox));
                count++;
            }

        }
        if (count == 0)
        {
            Label noResultsLabel = new Label("No matching trips found.");
            vbox.getChildren().add(noResultsLabel);
        }

        return vbox;
    }

    public HBox displayLabels() {
        Font boldFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 18);
        String labelStyle = "-fx-text-fill: #000000;";
        Label ID = new Label("ID");
        ID.setFont(boldFont);
        ID.setStyle(labelStyle);
        Label from = new Label("From");
        from.setFont(boldFont);
        from.setStyle(labelStyle);
        Label to = new Label("To");
        to.setFont(boldFont);
        to.setStyle(labelStyle);
        Label departure = new Label("Departure");
        departure.setFont(boldFont);
        departure.setStyle(labelStyle);
        Label arrival = new Label("Arrival");
        arrival.setFont(boldFont);
        arrival.setStyle(labelStyle);
        Label LicensePlate = new Label("License Plate");
        LicensePlate.setFont(boldFont);
        LicensePlate.setStyle(labelStyle);
        Label price = new Label("Price");
        price.setFont(boldFont);
        price.setStyle(labelStyle);
        HBox pane = new HBox(100, ID, from, to, departure, arrival, LicensePlate, price);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    public void handleDeleteButton(Trips trip, HBox pane, VBox vbox) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
        {

            if (result.get() == ButtonType.OK)
            {
                Trips.TripsMap.remove(trip.getTrip_id());
                try
                {
                    Trips.WriteBinaryFile();

                } catch (FileNotFoundException ex)
                {
                    Logger.getLogger(NewFXMain.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                vbox.getChildren().remove(pane);
            } else if (result.get() == ButtonType.CANCEL)
            {
                alert.close();
            }
        }
    }
    private Scene createMainScene() {
        Font manageFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 30);
        Label manageTrips = new Label("Manage Trips");
        manageTrips.setFont(manageFont);
        manageTrips.setAlignment(Pos.TOP_LEFT);
// delete button
        Image delete_img = new Image("file:///C:/Users/Roaa/Downloads/trash.png");
        ImageView iconView = new ImageView(delete_img);
        iconView.setFitHeight(20);
        iconView.setFitWidth(20);
        Button deleteAllButton = new Button();
        deleteAllButton.setGraphic(iconView);
        deleteAllButton.setStyle("-fx-background-color:transparent;");
        // add button
        Image add_img = new Image("file:///C:/Users/Roaa/Downloads/add.png");
        ImageView iconView2 = new ImageView(add_img);
        iconView2.setFitHeight(20);
        iconView2.setFitWidth(20);
        Button addTripsButton = new Button();
        addTripsButton.setGraphic(iconView2);
        addTripsButton.setStyle("-fx-background-color:transparent;");
        TextField searchField = new TextField();
        searchField.setPrefWidth(400);
        // Row 1
        HBox row1 = new HBox(10);
        row1.getChildren().addAll(deleteAllButton, searchField, addTripsButton);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = displayLabels();
        vbox = DisplayTrips();
        ScrollPane scrollpane = new ScrollPane(vbox);
        scrollpane.setStyle("-fx-background-color: white;");
        vbox.setStyle("-fx-background-color: white;");
        scrollpane.setPadding(new Insets(30));
        searchField.setOnKeyPressed(event ->
        {
            String searchTerm = searchField.getText().trim();
            vbox.getChildren().clear(); // Clear existing display
            vbox.getChildren().add(DisplayTrips(searchTerm));
        });
        // Row 3 
        VBox layout = new VBox(30, manageTrips, row1, row2, scrollpane);
        deleteAllButton.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
            {
                if (result.get() == ButtonType.OK)
                {
                    vbox.getChildren().clear();
                    Trips.TripsMap.clear();
                    try
                    {
                        Trips.WriteBinaryFile();
                    } catch (FileNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                } else if (result.get() == ButtonType.CANCEL)
                {
                    alert.close();
                }
            }
        });
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #FFFFFF;");
        addTripsButton.setOnAction(event -> add());
        return new Scene(layout, 1200, 500);
    }

    @Override
    public void edit() {
        Font font = new Font(15);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);

        ComboBox<String> box = new ComboBox<>();
        box.setItems(FXCollections.observableArrayList("Destination", "Time", "License Plate", "Price"));
        box.setPrefWidth(150);
        box.setPrefHeight(26);
        Label comboLabel = new Label("Select the prefered category:");
        Font boldFont1 = Font.font("Arial", FontWeight.BOLD, 13);
        HBox cboedit = new HBox(20, comboLabel, box);
        cboedit.setPadding(new Insets(18));
        cboedit.setAlignment(Pos.CENTER);
        comboLabel.setFont(boldFont1);
        Button okbtn = new Button("OK");
        okbtn.setFont(font);
        Stage edit = new Stage();
        Scene main = new Scene(cboedit, 400, 250);
        edit.setScene(main);
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");  //   UPDATED
        grid.setHgap(15);
        
        
    }

    public void edit(Trips trip, HBox pane, VBox vbox) {
        Font font = new Font(15);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);

        ComboBox<String> box = new ComboBox<>();
        box.setItems(FXCollections.observableArrayList("Destination", "Time", "License Plate", "Price"));
        box.setPrefWidth(150);
        box.setPrefHeight(26);
        Label comboLabel = new Label("Select the prefered category:");
        Font boldFont1 = Font.font("Arial", FontWeight.BOLD, 13);
        HBox cboedit = new HBox(20, comboLabel, box);
        cboedit.setPadding(new Insets(18));
        cboedit.setAlignment(Pos.CENTER);
        comboLabel.setFont(boldFont1);
        Button okbtn = new Button("OK");
        okbtn.setFont(font);
        Stage edit = new Stage();
        Scene main = new Scene(cboedit, 400, 250);
        edit.setScene(main);
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");  //   UPDATED

        grid.setHgap(15);
        cancel.setOnAction(e ->       //  UPDATED
        {
            edit.close();
        });

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), grid);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        edit.setResizable(false);

        box.setOnAction(e ->
        {
            String selectedCategory = box.getSelectionModel().getSelectedItem();
            if ("Destination".equals(selectedCategory))
            {
                grid.setHgap(20);
                Insets gridPadding = new Insets(10, 10, 10, 10); // top, right, bottom, left
                grid.setPadding(gridPadding);
                Font boldFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 18);
                VBox from_VBox = Trips.create_destination_ComboBox(boldFont, 10);
                VBox to_VBox = Trips.create_destination_ComboBox(boldFont, 10);
                ComboBox<String> fromDestinationCBO = (ComboBox<String>) from_VBox.getChildren().get(0);
                ComboBox<String> fromBusStopsCBO = (ComboBox<String>) from_VBox.getChildren().get(1);
                ComboBox<String> toDestinationCBO = (ComboBox<String>) to_VBox.getChildren().get(0);
                ComboBox<String> toBusStopsCBO = (ComboBox<String>) to_VBox.getChildren().get(1);
                fromDestinationCBO.setPromptText("Destination");
                fromBusStopsCBO.setPromptText("BusStops");
                toDestinationCBO.setPromptText("Destination");
                toBusStopsCBO.setPromptText("BusStops");
                Label from = new Label("From");
                from.setFont(boldFont1);
                Label to = new Label("To");
                to.setFont(boldFont1);
//                grid.add(cboedit, 1, 0);     //   UPDATED
                grid.add(from, 0, 1);
                grid.add(from_VBox, 0, 2);
                grid.add(to, 2, 1);
                grid.add(to_VBox, 2, 2);
                grid.add(save, 1, 3);
                grid.add(cancel, 2, 3);
                VBox vbox1 = new VBox(5, grid);
                vbox1.setStyle("-fx-background-color: white;");

                save.setOnAction(event ->
                {
                    //validations
                    if (fromDestinationCBO.getValue() == null || toDestinationCBO.getValue() == null
                            || fromBusStopsCBO.getValue() == null || toBusStopsCBO.getValue() == null)
                    {
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.initStyle(StageStyle.TRANSPARENT);

                        Label label = new Label("All fields are required. Please fill in all the details.");
                        label.setStyle("-fx-background-color: red; -fx-padding: 10px;");

                        VBox popupVBox = new VBox(label);
                        popupVBox.setAlignment(Pos.CENTER);

                        Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                        popupStage.setScene(popupScene);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(3), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                        );
                        timeline.setOnFinished(ev -> popupStage.close());
                        popupStage.show();
                        timeline.play();
                        return;
                    } else if (fromDestinationCBO.getValue() == toDestinationCBO.getValue())
                    {
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.initStyle(StageStyle.TRANSPARENT);

                        Label label = new Label("Invalid Input. Starting point and ending point cannot be the same");
                        label.setStyle("-fx-background-color: red; -fx-padding: 10px;");

                        VBox popupVBox = new VBox(label);
                        popupVBox.setAlignment(Pos.CENTER);

                        Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                        popupStage.setScene(popupScene);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(3), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                        );
                        timeline.setOnFinished(ev -> popupStage.close());
                        popupStage.show();
                        timeline.play();
                        return;
                    } else
                    {
                        //from destination
                        String FromDestination_Input = fromDestinationCBO.getValue().toUpperCase().replaceAll(" ", "_");
                        Destination FromDestination_Enum = Destination.valueOf(FromDestination_Input);
                        //To destination
                        String ToDestination_Input = toDestinationCBO.getValue().toUpperCase().replaceAll(" ", "_");
                        Destination ToDestination_Enum = Destination.valueOf(ToDestination_Input);
                        //from bustops   //to busStops
                        String fromBusStops = fromBusStopsCBO.getValue();
                        String toBusStops = toBusStopsCBO.getValue();
                        trip.setFrom_Destination(FromDestination_Enum);
                        trip.setTo_Destination(ToDestination_Enum);
                        trip.setFrom_BusStop(fromBusStops);
                        trip.setTo_BusStop(toBusStops);
                        Trips.TripsMap.put(trip.getTrip_id(), trip);
                        try
                        {
                            Trips.writeToFile(trip);
                            Trips.WriteBinaryFile();
                            vbox.getChildren().clear();
                            vbox.getChildren().addAll(DisplayTrips());
                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                        pane.layout();
                        System.out.println("After updating labels: " + pane.getChildren());
                        edit.close(); //   UPDATED
                    }
                });
                edit.setScene(new Scene(vbox1, 400, 250));    //   UPDATED

            } else if ("Time".equals(selectedCategory))
            {
                grid.setAlignment(Pos.CENTER);
                Label departure = new Label("Departure Time");
                departure.setFont(boldFont1);
                Label arrival = new Label("Arrival Time");
                arrival.setFont(boldFont1);
                DatePicker Arrival_date = new DatePicker();
                DatePicker departure_date = new DatePicker();
                // disable ay yom abl enahrda  -> departure calender
                departure_date.setDayCellFactory(picker -> new DateCell() {
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(LocalDate.now()));
                    }
                });// disable ay yom abl enahrda  -> arrival calender
                Arrival_date.setDayCellFactory(picker -> new DateCell() {
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(LocalDate.now()));
                    }
                });
                departure_date.valueProperty().addListener((observable, oldValue, newValue)
                        -> Arrival_date.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(departure_date.getValue()) || date.isEqual(departure_date.getValue()));
                    }
                })
                );
                ObservableList hours = FXCollections.observableArrayList(
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
                );
                ObservableList minutes = FXCollections.observableArrayList("0", "15", "30", "45");

                ComboBox<String> ArrivalHr = new ComboBox<>(hours);
                ComboBox<String> ArrivalMin = new ComboBox<>(minutes);
                ComboBox<String> DepartureHr = new ComboBox<>(hours);
                ComboBox<String> DepartureMin = new ComboBox<>(minutes);
                HBox Atime = new HBox(27, ArrivalHr, ArrivalMin);
                HBox Dtime = new HBox(27, DepartureHr, DepartureMin);
                grid.add(departure, 0, 0);
                grid.add(departure_date, 0, 1);
                grid.add(arrival, 2, 0);
                grid.add(Arrival_date, 2, 1);
                grid.add(Atime, 2, 2);
                grid.add(Dtime, 0, 2);
                VBox vbox1 = new VBox(10, grid, save, cancel);

                save.setOnAction(event ->
                {
                    //validations
                    if (Arrival_date.getValue() == null || departure_date.getValue() == null
                            || ArrivalHr.getValue() == null || ArrivalMin.getValue() == null
                            || DepartureHr.getValue() == null || DepartureMin.getValue() == null)
                    {
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.initStyle(StageStyle.TRANSPARENT);

                        Label label = new Label("All fields are required. Please fill in all the details.");
                        label.setStyle("-fx-background-color: red; -fx-padding: 10px;");

                        VBox popupVBox = new VBox(label);
                        popupVBox.setAlignment(Pos.CENTER);

                        Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                        popupStage.setScene(popupScene);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(3), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                        );
                        timeline.setOnFinished(ev -> popupStage.close());
                        popupStage.show();
                        timeline.play();
                        return;
                    }
                    System.out.println("Before updating labels: " + pane.getChildren());

                    // 1**Take values from ComboBox
                    String arrivalHour = ArrivalHr.getValue();
                    String arrivalMinute = ArrivalMin.getValue();
                    String DepartureHour = DepartureHr.getValue();
                    String DepartureMinute = DepartureMin.getValue();
                    // arrival datetime      //departure datetime
                    LocalDate ArrivalDate = Arrival_date.getValue();
                    LocalDate DepartureDate = departure_date.getValue();

                    // 2** store in a localtime
                    LocalTime ArrivalTime = LocalTime.of(Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
                    LocalTime DepartureTime = LocalTime.of(Integer.parseInt(DepartureHour), Integer.parseInt(DepartureMinute));
                    // store it in a localdatetime                  
                    LocalDateTime departureDateTime = DepartureDate.atTime(DepartureTime);
                    LocalDateTime arrivalDateTime = ArrivalDate.atTime(ArrivalTime);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedArrivalDateTime = trip.getArrivalDateTime().format(formatter);
                    String formattedDepartureDateTime = trip.getDepartureDateTime().format(formatter);
                    trip.setArrivalTime(ArrivalTime);
                    trip.setDepartureTime(DepartureTime);
                    trip.setArrivalDateTime(arrivalDateTime);
                    trip.setDepartureDateTime(departureDateTime);
                    ((Label) pane.getChildren().get(4)).setText(formattedArrivalDateTime);
                    ((Label) pane.getChildren().get(3)).setText(formattedDepartureDateTime);
                    Trips.TripsMap.put(trip.getTrip_id(), trip);
                    try
                    {
                        Trips.WriteBinaryFile();
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(DisplayTrips());
                    } catch (FileNotFoundException ex)
                    {
                        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pane.layout();
                    edit.close();     //   UPDATED

                });

                edit.setScene(new Scene(vbox1, 400, 260));   //   UPDATED

            } else if ("License Plate".equals(selectedCategory))
            {
                Label license = new Label("License Plate");
                license.setFont(font);
                ObservableList licensePlateList = FXCollections.observableArrayList();

                for (Vehicle vehicle : Vehicle.getVehicleList().values())
                {
                    licensePlateList.add(vehicle.getLicense_plate());
                }
                ComboBox<String> licensePlate_cbo = new ComboBox<>(licensePlateList);

                grid.add(license, 1, 0);
                grid.add(licensePlate_cbo, 3, 0);
                VBox vbox1 = new VBox(10, grid, save, cancel);

                save.setOnAction(event ->
                {
                    if (licensePlate_cbo == null)
                    {
                        {
                            Stage popupStage = new Stage();
                            popupStage.initModality(Modality.APPLICATION_MODAL);
                            popupStage.initStyle(StageStyle.TRANSPARENT);

                            Label label = new Label("All fields are required. Please fill in all the details.");
                            label.setStyle("-fx-background-color: red; -fx-padding: 10px;");

                            VBox popupVBox = new VBox(label);
                            popupVBox.setAlignment(Pos.CENTER);

                            Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                            popupStage.setScene(popupScene);
                            Timeline timeline = new Timeline(
                                    new KeyFrame(Duration.seconds(3), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                            );
                            timeline.setOnFinished(ev -> popupStage.close());
                            popupStage.show();
                            timeline.play();
                            return;
                        }
                    }
                    String licensevalue = licensePlate_cbo.getValue();
                    Vehicle vehicle = new Vehicle();
                    vehicle.setLicense_plate(licensevalue);
                    trip.setVehicle_id(licensevalue);
                    ((Label) pane.getChildren().get(5)).setText(trip.getVehicle_id());
                    Trips.TripsMap.put(trip.getTrip_id(), trip);
                    try
                    {
                        Trips.WriteBinaryFile();
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(DisplayTrips());
                    } catch (FileNotFoundException ex)
                    {
                        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pane.layout();
                    System.out.println("After updating labels: " + pane.getChildren());
                    edit.close();   //   UPDATED

                }
                );

                edit.setScene(new Scene(vbox1, 400, 260));    //   UPDATED      

            } else if ("Price".equals(selectedCategory))
            {
                Label priceLb = new Label("Price");
                priceLb.setFont(boldFont1);
                Spinner<Integer> priceSpinner = new Spinner<>(0, 900, 0);

                grid.add(priceLb, 0, 0);
                grid.add(priceSpinner, 0, 1);
                VBox vbox1 = new VBox(10, grid, save, cancel);

                save.setOnAction(event ->
                {
                    if (priceSpinner.getValue() == 0)
                    {
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.initStyle(StageStyle.TRANSPARENT);

                        Label label = new Label("All fields are required. Please fill in all the details.");
                        label.setStyle("-fx-background-color: red; -fx-padding: 10px;");

                        VBox popupVBox = new VBox(label);
                        popupVBox.setAlignment(Pos.CENTER);

                        Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                        popupStage.setScene(popupScene);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(3), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                        );
                        timeline.setOnFinished(ev -> popupStage.close());
                        popupStage.show();
                        timeline.play();
                        return;

                    }
                    priceSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                    {
                        trip.setPrice(newValue.intValue());
                    });

                    int prices = priceSpinner.getValue();
                    trip.setPrice(prices);
                    ((Label) pane.getChildren().get(6)).setText(Double.toString(trip.getPrice()));
                    Trips.TripsMap.put(trip.getTrip_id(), trip);
                    try
                    {
                        Trips.WriteBinaryFile();
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(DisplayTrips());
                    } catch (FileNotFoundException ex)
                    {
                        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pane.layout();
                    System.out.println("After updating labels: " + pane.getChildren());
                    edit.close();   //   UPDATED

                }
                );
                edit.setScene(new Scene(vbox1, 400, 260));   //   UPDATED

            }

        }
        );

        edit.showAndWait();

    }  //check

    @Override
    public void add() {
        Font boldFontt = Font.font("Arial", FontWeight.BOLD, 14);
        Stage add = new Stage();

        GridPane grid = new GridPane();

        Insets gridPadding = new Insets(20, 20, 20, 30); // top, right, bottom, left
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        Button OKbtn = new Button("OK");
        OKbtn.setPrefWidth(60);
        OKbtn.setPrefHeight(20);
        OKbtn.setFont(boldFontt);
        Button cancelbtn = new Button("Cancel");
        cancelbtn.setPrefWidth(70);
        cancelbtn.setPrefHeight(20);
        cancelbtn.setFont(boldFontt);
        Label liscencePlate_LB = new Label("License Plate");
        liscencePlate_LB.setFont(boldFontt);
        Label departureLb = new Label("Departure Time");
        departureLb.setFont(boldFontt);
        Label arrivalLb = new Label("Arrival Time");
        arrivalLb.setFont(boldFontt);
        Label priceLb = new Label("Price");
        priceLb.setFont(boldFontt);
        Label fromLb = new Label("From");
        priceLb.setFont(boldFontt);
        Label toLb = new Label("To");
        priceLb.setFont(boldFontt);

        VBox from_VBox = Trips.create_destination_ComboBox(boldFontt, 13);
        VBox to_VBox = Trips.create_destination_ComboBox(boldFontt, 13);
        ComboBox<String> fromDestinationCBO = (ComboBox<String>) from_VBox.getChildren().get(0);
        ComboBox<String> fromBusStopsCBO = (ComboBox<String>) from_VBox.getChildren().get(1);

        ComboBox<String> toDestinationCBO = (ComboBox<String>) to_VBox.getChildren().get(0);
        ComboBox<String> toBusStopsCBO = (ComboBox<String>) to_VBox.getChildren().get(1);
        fromDestinationCBO.setPromptText("Destination");
        fromBusStopsCBO.setPromptText("BusStops");
        toDestinationCBO.setPromptText("Destination");
        toBusStopsCBO.setPromptText("BusStops");
        DatePicker Arrival_date = new DatePicker();
        DatePicker departure_date = new DatePicker();
// disable ay yom abl enahrda  -> departure calender
        departure_date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

// disable ay yom abl enahrda  -> arrival calender
        Arrival_date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
        departure_date.valueProperty().addListener((observable, oldValue, newValue)
                -> Arrival_date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(departure_date.getValue())
                        || date.isEqual(departure_date.getValue()));

            }
        })
        );
        ObservableList hours = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23"
        );
        ObservableList minutes = FXCollections.observableArrayList("0", "15", "30", "45");

        ComboBox<String> ArrivalHr = new ComboBox<>(hours);
        ComboBox<String> ArrivalMin = new ComboBox<>(minutes);
        ComboBox<String> DepartureHr = new ComboBox<>(hours);
        ComboBox<String> DepartureMin = new ComboBox<>(minutes);
        ArrivalHr.setPromptText("Hour");
        ArrivalMin.setPromptText("MInute");
        DepartureHr.setPromptText("Hour");
        DepartureMin.setPromptText("Minute");
        HBox Atime = new HBox(10, ArrivalHr, ArrivalMin);
        HBox Dtime = new HBox(10, DepartureHr, DepartureMin);
        Arrival_date.setStyle(
                "-fx-background-color: F0F0F0; "
                + "-fx-border-color: white; "
                + "-fx-border-width: 0.5px; "
                + "-fx-padding: 1px; "
                + "-fx-font-family: 'Arial'; "
                + // Set the font family
                "-fx-font-size: 10px;"
        );
        departure_date.setStyle(
                "-fx-background-color: F0F0F0; "
                + "-fx-border-color: white; "
                + "-fx-border-width: 0.5px; "
                + "-fx-padding: 1px; "
                + "-fx-font-family: 'Arial'; "
                + // Set the font family
                "-fx-font-size: 10px;"
        );
        double smallFontSize = 10;
        ArrivalHr.setStyle("-fx-font-size: " + smallFontSize + "px;");
        ArrivalMin.setStyle("-fx-font-size: " + smallFontSize + "px;");
        DepartureHr.setStyle("-fx-font-size: " + smallFontSize + "px;");
        DepartureMin.setStyle("-fx-font-size: " + smallFontSize + "px;");
        Label license = new Label("License Plate");
        license.setFont(boldFontt);
        ObservableList licensePlateList = FXCollections.observableArrayList();
        for (Vehicle vehicle : Vehicle.getVehicleList().values())
        {
            licensePlateList.add(vehicle.getLicense_plate());
        }
        ComboBox<String> licensePlate_cbo = new ComboBox<>(licensePlateList);
        licensePlate_cbo.setPrefWidth(50);
        priceLb.setFont(boldFontt);
        Spinner<Integer> priceSpinner = new Spinner<>(0, 900, 0);

        grid.add(fromLb, 0, 0);
        grid.add(from_VBox, 0, 1);
        grid.add(toLb, 2, 0);
        grid.add(to_VBox, 2, 1);
        grid.add(departureLb, 0, 2);
        grid.add(departure_date, 0, 3);
        grid.add(arrivalLb, 2, 2);
        grid.add(Arrival_date, 2, 3);
        grid.add(Atime, 2, 4);
        grid.add(Dtime, 0, 4);
        grid.add(license, 0, 5);
        grid.add(licensePlate_cbo, 0, 6);
        grid.add(priceLb, 2, 5);
        grid.add(priceSpinner, 2, 6);
        grid.add(OKbtn, 0, 8);
        grid.add(cancelbtn, 2, 8);
        //handling ok button
        OKbtn.setOnAction(event ->
        {
            // validations
            if (fromDestinationCBO.getValue() == null || toDestinationCBO.getValue() == null
                    || fromBusStopsCBO.getValue() == null || toBusStopsCBO.getValue() == null
                    || Arrival_date.getValue() == null || departure_date.getValue() == null
                    || ArrivalHr.getValue() == null || ArrivalMin.getValue() == null
                    || DepartureHr.getValue() == null || DepartureMin.getValue() == null
                    || /*licensePlate_cbo.getValue() == null || */ priceSpinner.getValue() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("All fields are required. Please fill in all the details.");
                alert.showAndWait();
                return;
            } else if (fromDestinationCBO.getValue() == toDestinationCBO.getValue())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Input. Starting point and ending point cannot be the same");
                alert.showAndWait();
                return;
            } else
            {
                // 1**Take values from ComboBox
                String arrivalHour = ArrivalHr.getValue();
                String arrivalMinute = ArrivalMin.getValue();
                String DepartureHour = DepartureHr.getValue();
                String DepartureMinute = DepartureMin.getValue();
                // arrival datetime      //departure datetime
                LocalDate ArrivalDate = Arrival_date.getValue();
                LocalDate DepartureDate = departure_date.getValue();
                // 2** store in a localtime

                LocalTime ArrivalTime = LocalTime.of(Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
                LocalTime DepartureTime = LocalTime.of(Integer.parseInt(DepartureHour), Integer.parseInt(DepartureMinute));
                // store it in a localdatetime                  
                LocalDateTime departureDateTime = DepartureDate.atTime(DepartureTime);
                LocalDateTime arrivalDateTime = ArrivalDate.atTime(ArrivalTime);
                Trips trip = new Trips();
                //from destination
                String FromDestination_Input = fromDestinationCBO.getValue().toUpperCase().replaceAll(" ", "_");
                Destination FromDestination_Enum = Destination.valueOf(FromDestination_Input);

                //To destination
                String ToDestination_Input = toDestinationCBO.getValue().toUpperCase().replaceAll(" ", "_");
                Destination ToDestination_Enum = Destination.valueOf(ToDestination_Input);

                //from bustops
                String fromBusStops = fromBusStopsCBO.getValue();
                //to busStops
                String toBusStops = toBusStopsCBO.getValue();
                // license plate
                String licensePlate = licensePlate_cbo.getValue();
                //Price
                // if any updates happen (listeners)

                int tripID = Trips.generateRandomID();

                Trips t = new Trips(tripID, licensePlate, FromDestination_Enum, ToDestination_Enum,
                        fromBusStops, toBusStops, 0, ArrivalTime, DepartureTime, arrivalDateTime, departureDateTime);
                priceSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                {
                    t.setPrice(newValue.intValue());
                });
                t.setPrice(priceSpinner.getValue().intValue());
                boolean valid = true;
                for (Trips tr : Trips.TripsMap.values())
                {
                    if (t.equals(tr))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("A trip with the same details already exists #" + tr.getTrip_id());
                        alert.showAndWait();
                        valid = false;
                        break;
                    }
                }
                if (valid)
                {
                    Trips.TripsMap.put(tripID, t);
                    System.out.println("TripsMap contents: " + t);
                    System.out.println("tripid: " + t.getTrip_id());

                    try
                    {
                        Trips.writeToFile(t);
                        Trips.WriteBinaryFile();
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(DisplayTrips());
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }

                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.initStyle(StageStyle.TRANSPARENT);

                    Label label = new Label("Trip #" + t.getTrip_id() + " saved Successfully.");
                    label.setStyle("-fx-background-color: lightgreen; -fx-padding: 10px;");

                    VBox popupVBox = new VBox(label);
                    popupVBox.setAlignment(Pos.CENTER);

                    Scene popupScene = new Scene(popupVBox, Color.TRANSPARENT);
                    popupStage.setScene(popupScene);

                    // Set up a timeline for fade-out animation
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(2), new KeyValue(popupStage.getScene().getRoot().opacityProperty(), 0))
                    );
                    Timeline timeline1 = new Timeline(
                            new KeyFrame(Duration.seconds(0.5), new KeyValue(add.getScene().getRoot().opacityProperty(), 0))
                    );
                    timeline.setOnFinished(e -> popupStage.close());
                    timeline1.setOnFinished(e -> add.close());
                    popupStage.show();
                    timeline.play();
                    timeline1.play();
                }
            }
        });

        add.setScene(new Scene(grid, 500, 400));
        add.show();
        cancelbtn.setOnAction(eh ->
        {
            add.close();
        });

    }

    @Override
    public void remove() {

    }

    @Override
    public void search() {

    }

}
