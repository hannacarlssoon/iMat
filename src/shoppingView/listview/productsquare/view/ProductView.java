package shoppingView.listview.productsquare.view;

import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.util.AmountUtil;
import shoppingView.util.PriceUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.util.List;

public class ProductView extends AnchorPane {

    @FXML
    private ImageView image;
    @FXML
    private Label productName;
    @FXML
    private Label price;
    @FXML
    private ComboBox<String> amount;

    private Product product;

    // Reference to the main application.
    private MainApp mainApp;

    public ProductView(int productID, MainApp mainApp) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.mainApp = mainApp;

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

        List<Product> products = IMatDataHandler.getInstance().getProducts();
        product = products.get(productID);

        setImage();
        setProductName();
        setUpAmounts();
    }

    private void setProductName() {
        productName.textProperty().setValue(product.getName());
    }

    private void setImage() {
        String imageName = product.getImageName();
        image.setImage(new Image("file:resources/images/products/" + imageName));
    }

    private void setUpAmounts() {
        amount.getItems().setAll(AmountUtil.createAmountList(product));
        amount.setValue(amount.getItems().get(0));
        setPrice(amount.getValue());

        amount.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPrice(newValue);
            }
        });
    }

    private void setPrice(String newAmount) {
        double currentPrice = AmountUtil.amountFromString(newAmount) * product.getPrice();
        String currentPriceAsString = PriceUtil.toPriceFormat(currentPrice);
        price.textProperty().setValue(currentPriceAsString);
    }

    @FXML
    private void addProductToBasket() {
        Basket.getInstance().addItem(
                new BasketItem(product, AmountUtil.amountFromString(amount.getValue())));
    }

/*
    @FXML
    private void initialize() {
        List<Product> products = IMatDataHandler.getInstance().getProducts();
        String imageName = products.get(0).getImageName();

        image.setImage(new Image("file:resources/images/products/" + imageName)); //"file:resources/images/iMat_square.png"));
        //bgImage.setImage(new Image("file:resources/images/bg1.jpg"));
    }
*/

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void showMoreInfoPanel() {
        mainApp.showMoreInfoPanel(product);
    }
}
