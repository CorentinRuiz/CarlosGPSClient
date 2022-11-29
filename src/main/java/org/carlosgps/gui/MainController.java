package org.carlosgps.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.carlosgps.classes.ItineraryRequesterService;


import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;


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
    private WebView webView;

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

    public void displayPath(){
        String originAdress = origin.getText().toString();
        String destAdress = destination.getText().toString();

        ItineraryRequesterService itineraryRequesterService = new ItineraryRequesterService();
        itineraryRequesterService.setBestItinerary(originAdress,destAdress);

        totalDistanceResult.setText(itineraryRequesterService.getTotalDistance().toString() +" m");

        System.out.println(itineraryRequesterService.getOriginPoint().toString());
        System.out.println(itineraryRequesterService.getDestinationPoint().toString());
        engine.getDocument().getElementById("origin").setAttribute("value",itineraryRequesterService.getOriginPoint().toString());
        engine.getDocument().getElementById("dest").setAttribute("value",itineraryRequesterService.getDestinationPoint().toString());

        engine.executeScript("document.getElementById('submit').click()");
    }



}