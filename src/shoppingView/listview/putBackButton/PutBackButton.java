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
    private int listIndex;
    private boolean destroyed = false;

    // Reference to the main application.
    private MainApp mainApp;

    private Thread destroyerThread = new Thread();

    public PutBackButton(BasketItem item, int listIndex) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PutBackButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        //this.mainApp = mainApp;
        this.item = item;
        this.listIndex = listIndex;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        destroyerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*if (!destroyed) {
                    Basket.getInstance().getItems().remove(listIndex);
                    Basket.getInstance().removePutBackButton();
                }*/
                Basket.getInstance().removePutBackButton();
                System.out.println("Removing----------");
            }
        });
        //destroyerThread.start();
    }

    @FXML
    private void putBackClicked() {
        destroyed = true;
        Basket.getInstance().getItems().remove(listIndex);
        Basket.getInstance().getItems().add(listIndex, item);
        Basket.getInstance().removePutBackButton();
    }

    private void destroySelf() {
        Basket.getInstance().getItems().remove(listIndex);
        Basket.getInstance().removePutBackButton();
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
