package shoppingView.historik;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import paymentView.view.ShoppingCartItemReciptController;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by hannacarlsson on 2017-03-02.
 */
public class HistorikViewController implements Initializable {

    @FXML private Label total;
    @FXML private ListView listView;
    @FXML private AnchorPane mainAnchorPane;
    private Order order;
    private ObservableList<ShoppingItem> shoppingItemList;
    private BasketItem basketItem;
    private FXMLLoader fxmlLoader;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        order = new Order();
        shoppingItemList = (ObservableList<ShoppingItem>) order.getItems();
        listView.setCellFactory(shoppingItemList -> new ShoppingCartItemReciptController());
        listView.setItems(shoppingItemList);

    }







}
