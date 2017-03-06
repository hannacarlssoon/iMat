package paymentView.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hannacarlsson on 2017-03-02.
 */
public class ShoppingCartItemReciptController extends ListCell<BasketItem> {

    @FXML private AnchorPane mainAnchorPane;
    @FXML private ImageView image;
    @FXML private Label title;
    @FXML private Label number;
    @FXML private Label price;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(BasketItem basketItem, boolean empty) {
        setGraphic(null);
        super.updateItem(basketItem, empty);
        if (basketItem == null) return;


        if (fxmlLoader == null) {
            fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartItemRecipt.fxml"));
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
            title.setText(basketItem.getName().getValue());
            number.setText(basketItem.getAmount()+"");
            price.setText(basketItem.getPriceAsString().getValue());
            String name = basketItem.getProduct().getImageName();
            image.setImage(new Image("file:resources/images/products/" + name));
        }
    }

    public ShoppingCartItemReciptController() {

    }

}
