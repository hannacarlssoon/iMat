package shoppingView.listview.amountSetter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.util.AmountUtil;
import shoppingView.util.PriceUtil;

import java.io.IOException;
import java.util.List;

public class AmountSetter extends AnchorPane {

    @FXML
    private Label amount;
    @FXML
    private ImageView plusIcon;
    @FXML
    private ImageView minusIcon;
    @FXML
    private Button plusButton;

    private Product product;

    private BasketItem item;

    // Reference to the main application.
    private MainApp mainApp;

    public AmountSetter(int productID, int amount, BasketItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AmountSetter.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        //this.mainApp = mainApp;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {

                }
            }
        });

        this.amount.textProperty().setValue(amount + "");

        List<Product> products = IMatDataHandler.getInstance().getProducts();
        product = products.get(productID);

        setUpAmounts();
        setIcons();
        this.item = item;
        item.setAmountSetter(this);
    }

    private void setIcons() {
        plusIcon.setImage(new Image("file:resources/images/paymentImages/add grey.png"));
        plusIcon.setFitHeight(10);
        plusIcon.setFitWidth(10);
        minusIcon.setImage(new Image("file:resources/images/paymentImages/remove grey.png"));
        minusIcon.setFitHeight(10);
        minusIcon.setFitWidth(10);


//        BackgroundImage backgroundImage = new BackgroundImage( new Image("file:resources/images/paymentImages/add grey.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//
//        plusButton.setBackground(background);
    }

    private void setUpAmounts() {
        //Real code:
        //amountField.textProperty().setValue(AmountUtil.createAmountString(product, 1));

    }

    public void updateAmount() {
        amount.textProperty().setValue(item.getAmount() + "");
    }

    @FXML
    private void incAmount() {
        //int newAmount = AmountUtil.amountFromString(amountField.textProperty().get());
        //amountField.textProperty().setValue(AmountUtil.createAmountString(product, newAmount + 1));
        amount.textProperty().setValue((Integer.parseInt(amount.textProperty().getValue()) + 1) + "");
        item.addAmount(1);
    }

    @FXML
    private void decAmount() {
//        int newAmount = AmountUtil.amountFromString(amountField.textProperty().get());
//        if (newAmount > 1) {
//            amountField.textProperty().setValue(AmountUtil.createAmountString(product, newAmount - 1));
//        }

        if ((Integer.parseInt(amount.textProperty().getValue()) - 1) > 0) {
            amount.textProperty().setValue((Integer.parseInt(amount.textProperty().getValue()) - 1) + "");
            item.addAmount(-1);
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void showMoreInfoPanel() {
        mainApp.showMoreInfoPanel(product);
    }
}
