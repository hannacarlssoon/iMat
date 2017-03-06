package shoppingView.listview.putBackButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;

public class PutBackButton extends AnchorPane {

    @FXML
    private BasketItem item;

    // Reference to the main application.
    private MainApp mainApp;

    public PutBackButton(BasketItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PutBackButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        //this.mainApp = mainApp;
        this.item = item;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @FXML
    private void putBackClicked() {
        System.out.println("--putBackClicked()");
    }

    @FXML
    private void delete() {
        item.hideAddedPane();
//        Basket.getInstance().getItems().remove(
//                Basket.getInstance().getItems().indexOf(item)
//        );
        Basket.getInstance().removeItem(item.getProduct());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
