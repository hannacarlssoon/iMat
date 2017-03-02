package paymentView.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shoppingView.basket.model.BasketItem;

/**
 * Created by hannacarlsson on 2017-03-02.
 */
public class ShoppingCartItemReciptController extends ListCell<BasketItem> {

    @FXML private AnchorPane mainAnchorPane;
    @FXML private ImageView image;
    @FXML private Label title;
    @FXML private Label number;
    @FXML private Label price;

    @Override
    protected void updateItem(BasketItem basketItem, boolean empty) {
        super.updateItem(basketItem, empty);

        setGraphic(mainAnchorPane);
        if (basketItem != null) {
            title.setText(basketItem.getName().toString());
            number.setText(basketItem.getAmount()+"");
            price.setText(basketItem.getPriceAsString().toString());
            String name = basketItem.getName().toString();
            image.setImage(new Image("file:resources/images/products/" + name));
        }
    }

    public ShoppingCartItemReciptController() {

    }

}
