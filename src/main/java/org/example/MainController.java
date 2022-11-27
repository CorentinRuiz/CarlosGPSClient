package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



import java.net.URL;

import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    public Button submit;

    @FXML
    public TextField destination;

    @FXML
    public TextField origin;

    @FXML
    private WebView webView;

    @FXML
    private Label exitCross;

    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();

        loadPage();
    }

    public void loadPage(){
        engine.load(getClass().getResource("/map.html").toString());
    }

    public void exitApp(){
        System.exit(1);
    }

    public void displayPath(){
        /*String originAdress = origin.getText().toString();
        String destAdress = destination.getText().toString();

        //List<Point> points = ServiceSimulation.getItinary(originAdress,destAdress);

        engine.getDocument().getElementById("origin").setAttribute("value",points.get(0).toString());
        engine.getDocument().getElementById("dest").setAttribute("value",points.get(1).toString());

        engine.executeScript("document.getElementById('submit').click()");*/
    }

}