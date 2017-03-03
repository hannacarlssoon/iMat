package paymentView.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;

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

    private BasketItem basketItem;
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(BasketItem basketItem, boolean empty) {
        this.basketItem = basketItem;
        super.updateItem(basketItem, empty);

        if (fxmlLoader == null) {
            fxmlLoader = new FXMLLoader(getClass().getResource("file:src/paymentView/view/shoppingCartItems.fxml"));
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            }
            catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }





        setGraphic(mainAnchorPane);
        if (basketItem != null) {
            String name = basketItem.getName().toString();
            image.setImage(new Image("file:resources/images/productImages/" + name));
            price.setText(basketItem.getPriceAsString().toString());
            title.setText(basketItem.getName().toString());
        }

    }

    @FXML
    protected void removeItem(ActionEvent event) {
        Basket.getInstance().getItems().remove(Basket.getInstance().getItems().indexOf(basketItem));
    }

    @FXML
    protected void addOne(ActionEvent event) {
        basketItem.addAmount(1);
    }

    @FXML
    protected void removeOneItem(ActionEvent event) {
        basketItem.addAmount(-1);
    }

    public ShoppingCartItemsController() {
        System.out.println("contructor in ShoppingCart blalkfs");
    }
}
