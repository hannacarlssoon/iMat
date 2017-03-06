package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    Main main;

    @FXML
    AnchorPane menuHolder;
    @FXML
    AnchorPane shopHolder;
    @FXML
    AnchorPane home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMain(Main main){
        this.main = main;
    }
}
