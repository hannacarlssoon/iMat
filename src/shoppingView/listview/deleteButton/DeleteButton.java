package shoppingView.listview.deleteButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;

public class DeleteButton extends AnchorPane {

    @FXML
    private ImageView trashIcon;
    private BasketItem item;

    // Reference to the main application.
    private MainApp mainApp;

    public DeleteButton(BasketItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        //this.mainApp = mainApp;
        this.item = item;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        trashIcon.setImage(new Image("file:resources/images/trash.png"));
    }

    @FXML
    private void delete() {
        item.hideAddedPane();
//        Basket.getInstance().getItems().remove(
//                Basket.getInstance().getItems().indexOf(item)
//        );
        Basket.getInstance().removePutBackButton();
        Basket.getInstance().removeItem(item.getProduct());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
