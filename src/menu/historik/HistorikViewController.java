package menu.historik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by hannacarlsson on 2017-03-02.
 */
public class HistorikViewController implements Initializable {

    @FXML private Label total;
    @FXML private ListView listView;
    @FXML private AnchorPane mainAnchorPane;
    @FXML private Label summa;
    private IMatDataHandler instance = IMatDataHandler.getInstance();
    private ObservableList<ShoppingItem> orderList;
    private Order order;
    private FXMLLoader fxmlLoader;
    private HistorikController controller;
    private double amount = 0;



    public HistorikViewController(HistorikController controller, Order order) {
        this.order = order;
        this.controller = controller;

        for (int i = 0; i < order.getItems().size(); i++) {
            amount = amount + order.getItems().get(i).getTotal();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistorikView.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderList = FXCollections.observableArrayList(order.getItems());
        listView.setCellFactory(orderList -> new Receipt());
        listView.setItems(orderList);
        listView.setFixedCellSize(80);
        total.setText(amount + "0 kr");
        total.getStyleClass().add("label-historik-summa");
        summa.getStyleClass().add("label-historik-summa");
    }



    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }



}
