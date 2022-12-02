package org.carlosgps.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.carlosgps.classes.GPSPoint;
import org.carlosgps.classes.InstructionsConsumer;
import org.carlosgps.classes.ItineraryRequesterService;


import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainController implements Initializable {
    @FXML
    public Button submit;

    @FXML
    public TextField destination;

    @FXML
    public TextField origin;

    @FXML
    public Label totalDistanceResult;

    @FXML
    public Label instructions;

    @FXML
    public ImageView instructPicture;

    @FXML
    private WebView webView;

    @FXML
    private Label minimize;

    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();

        loadPage();
    }

    public void loadPage(){
        engine.load(getClass().getClassLoader().getResource("map.html").toString());
    }

    public void exitApp(){
        System.exit(1);
    }

    public void minimizeApp(){
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void displayPath(){
        String originAddress = origin.getText().toString();
        String destAddress = destination.getText().toString();

        ItineraryRequesterService itineraryRequesterService = new ItineraryRequesterService();
        itineraryRequesterService.setBestItinerary(originAddress,destAddress);

        totalDistanceResult.setText(itineraryRequesterService.getTotalDistance().toString() +" m");

        engine.getDocument().getElementById("origin").setAttribute("value",itineraryRequesterService.getOriginPoint().toString());
        engine.getDocument().getElementById("dest").setAttribute("value",itineraryRequesterService.getDestinationPoint().toString());

        engine.getDocument().getElementById("station1").setAttribute("value",itineraryRequesterService.getFirstStationPoint().toString());
        System.out.println("First Bike Station :" +itineraryRequesterService.getFirstStationPoint().toString());
        engine.getDocument().getElementById("station2").setAttribute("value",itineraryRequesterService.getLastStationPoint().toString());
        System.out.println("Last Bike Station :" + itineraryRequesterService.getLastStationPoint().toString());

        System.out.println("All Coordinates :");

        for(GPSPoint point : itineraryRequesterService.getWayPoints()){
            System.out.println(point.toString());
        }

        engine.getDocument().getElementById("waypoint").setAttribute(
                "value",itineraryRequesterService.getFirstStationPoint().toString() + ";" +itineraryRequesterService.getLastStationPoint().toString());

        displayInstruction();
        engine.executeScript("document.getElementById('submit').click()");
    }

    public void displayInstruction(){
        InstructionsConsumer consumer = new InstructionsConsumer();
        consumer.run();
        System.out.println(consumer.getInstructions());

        Image instructPics = chooseIconForDirection(consumer.getInstructions());

        if(instructPics != null){
            instructPicture.setImage(instructPics);
        }

        instructions.setText(consumer.getInstructions());
        instructions.setWrapText(true);
        instructions.setTextAlignment(TextAlignment.JUSTIFY);
    }

    public Image chooseIconForDirection(String instruction){
        Pattern p = Pattern.compile("droite|gauche|Continuez|rond-point|Restez|Arrivée|vélo");
        Matcher m = p.matcher(instruction);
        if(m.find()){
            switch (m.group()){
                case "droite":
                    return new Image(getClass().getClassLoader().getResource("icons/turn-right.png").toString());
                case "gauche":
                    return new Image(getClass().getClassLoader().getResource("icons/turn-left.png").toString());
                case "rond-point":
                    return new Image(getClass().getClassLoader().getResource("icons/rond-point.png").toString());
                case "Arrivée":
                    return new Image(getClass().getClassLoader().getResource("icons/drapeau-darrivee.png").toString());
                case "vélo":
                    return new Image(getClass().getClassLoader().getResource("icons/bicyclette.png").toString());
                default:
                    return new Image(getClass().getClassLoader().getResource("icons/up-straight-arrow.png").toString());
            }
        }

        return null;
    }



}