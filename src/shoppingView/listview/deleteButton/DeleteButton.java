package shoppingView.listview.deleteButton;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

import java.io.IOException;
import java.util.List;

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
        Basket.getInstance().getItems().remove(
                Basket.getInstance().getItems().indexOf(item)
        );
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
