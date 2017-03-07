package paymentView.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hannacarlsson on 2017-03-02.
 */
public class ShoppingCartItemsController extends ListCell <BasketItem> {

    @FXML private ImageView image;
    @FXML private Label price;
    @FXML private Label title;
    @FXML private Button removeItem;
    @FXML private Button addOne;
    @FXML private Button removeOne;
    @FXML private AnchorPane mainAnchorPane;
    @FXML private ImageView trashcan;
    @FXML private ImageView plus;
    @FXML private ImageView minus;
    @FXML private TextField textField;

    private BasketItem basketItem;
    private FXMLLoader fxmlLoader;
    private ObservableList<BasketItem> basketItemObservableList;
    Basket basket = Basket.getInstance();

    @Override
    protected void updateItem(BasketItem basketItem, boolean empty) {
        super.updateItem(basketItem, empty);
        this.basketItem = basketItem;
        setGraphic(null);

        if (basketItem == null) return;

        if (fxmlLoader == null) {
            fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartItems.fxml"));
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            }
            catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

        setGraphic(mainAnchorPane);
            minus.setImage(new Image("file:resources/images/paymentImages/remove grey.png"));
            plus.setImage(new Image("file:resources/images/paymentImages/add grey.png"));
            trashcan.setImage(new Image("file:resources/images/paymentImages/trashcan.png"));
            String name = basketItem.getProduct().getImageName();
            image.setImage(new Image("file:resources/images/products/" + name));
            price.setText(basketItem.getPriceAsString().getValue());
            title.setText(basketItem.getName().getValue());
            textField.setText(basketItem.getAmount()+"");
    }

    @FXML
    protected void removeItem(ActionEvent event) {

        if (basketItem != null) {;
            Product temp = basketItem.getProduct();
            basket.removeItem(temp);
            basketItemObservableList.remove(temp);
            setBasketItemObservableList();
            basket.notifyController();
        }
    }

    @FXML
    protected void addOne(ActionEvent event) {
        basketItem.addAmount(1);
        textField.setText(basketItem.getAmount()+"");
        price.setText((basketItem.getPriceAsString().getValue()));
        basket.notifyController();
    }

    @FXML
    protected void removeOneItem(ActionEvent event) {
        if (basketItem.getAmount() > 1) {
            basketItem.addAmount(-1);
            textField.setText(basketItem.getAmount() + "");
            price.setText(basketItem.getPriceAsString().getValue());
            basket.notifyController();
        }
    }

    public ShoppingCartItemsController(PaymentController paymentController) {
        basketItemObservableList = paymentController.getBasketList();
    }

    private void setBasketItemObservableList() {
        basketItemObservableList = basket.getItems();
    }
}