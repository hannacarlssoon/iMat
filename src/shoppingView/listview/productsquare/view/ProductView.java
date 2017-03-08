package shoppingView.listview.productsquare.view;

import javafx.animation.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import main.ProductsModel;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingCart;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ProductView extends AnchorPane {

    @FXML
    private ImageView image;
    @FXML
    private Label productName;
    @FXML
    private Label price;
    @FXML
    private TextField amountField;
    @FXML
    private ImageView plusIcon;
    @FXML
    private ImageView minusIcon;
    @FXML
    private Button minusButton;
    @FXML
    private AnchorPane addedPane;
    @FXML
    private ImageView basketIcon;
    @FXML
    private ImageView infoLogo;
    @FXML
    private Tooltip productTip;

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


        //List<Product> products = IMatDataHandler.getInstance().getProducts();
        product = ProductsModel.getInstance().getAllProducts().get(productID);

        setImage();
        setProductName();
        setUpAmounts();
        setIcons();

        price.textProperty().setValue(PriceUtil.toPriceFormat(product.getPrice()));



        //Shows addedPane from the beginning when browsing (if product in basket):
        if (Basket.getInstance().contains(product)) {
            showAddedPane();
        }

        hackTooltipStartTiming(productTip);
    }

    public ProductView(Product newProduct, MainApp mainApp) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.mainApp = mainApp;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        //List<Product> products = IMatDataHandler.getInstance().getProducts();
        product = newProduct;

        setImage();
        setProductName();
        setUpAmounts();
        setIcons();

        price.textProperty().setValue(PriceUtil.toPriceFormat(product.getPrice()));

        //Shows addedPane from the beginning when browsing (if product in basket):
        if (Basket.getInstance().contains(product)) {
            showAddedPane();
        }

        hackTooltipStartTiming(productTip);
    }

    private void setIcons() {
        plusIcon.setImage(new Image("file:resources/images/paymentImages/add grey.png"));
        minusIcon.setImage(new Image("file:resources/images/paymentImages/remove grey.png"));
        infoLogo.setImage(new Image("file:resources/images/paymentImages/information.png"));
        basketIcon.setImage(new Image("file:resources/images/basket.png"));
        basketIcon.setFitHeight(25);
        basketIcon.setFitWidth(25);
        basketIcon.setLayoutX(92);
        basketIcon.setLayoutY(2);


    }

    private void setProductName() {

        productName.textProperty().setValue(product.getName()); //Standard: 20pt

        if (product.getName().length() >= 12) {
            System.out.println(product.getName());
            productName.setStyle("-fx-font-size: 18pt");
        }
        if (product.getName().length() >= 14) {
            System.out.println(product.getName());
            productName.setStyle("-fx-font-size: 16pt");
        }
        if (product.getName().length() >= 16) {
            System.out.println(product.getName());
            productName.setStyle("-fx-font-size: 14pt");
        }


    }

    private void setImage() {
        String imageName = product.getImageName();
        image.setImage(new Image("file:resources/images/products/" + imageName));
    }

    private void setUpAmounts() {
        //Real code:
        amountField.textProperty().setValue(AmountUtil.createAmountString(product, 1));
        minusButton.setDisable(true);
    }

    @FXML
    public void showAddedPane() {
//        FadeTransition ft = new FadeTransition(Duration.millis(1000), addedPane);
//        ft.setFromValue(0.0);
//        ft.setToValue(1.0);
//        //ft.setCycleCount(Timeline.INDEFINITE);
//        //ft.setAutoReverse(true);
//        ft.play();

        addedPane.prefHeightProperty().setValue(0);
        KeyValue keyValue1  = new KeyValue(addedPane.prefHeightProperty(), 30, Interpolator.EASE_OUT);
        KeyValue keyValueFade  = new KeyValue(addedPane.opacityProperty(), 1.0, Interpolator.EASE_OUT);

        KeyFrame keyFrame1  = new KeyFrame(Duration.millis(400), keyValue1);
        KeyFrame keyFrameFade  = new KeyFrame(Duration.millis(550), keyValueFade);

        Timeline timeline  = new Timeline();
        //timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame1, keyFrameFade);
        timeline.play();
    }

    @FXML
    public void hideAddedPane() {
        KeyValue keyValue1  = new KeyValue(addedPane.prefHeightProperty(), 0, Interpolator.EASE_OUT);
        KeyValue keyValueFade  = new KeyValue(addedPane.opacityProperty(), 0.0, Interpolator.EASE_OUT);

        KeyFrame keyFrame1  = new KeyFrame(Duration.millis(400), keyValue1);
        KeyFrame keyFrameFade  = new KeyFrame(Duration.millis(550), keyValueFade);

        Timeline timeline  = new Timeline();
        //timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame1, keyFrameFade);
        timeline.play();

    }

    @FXML
    private void incAmount() {
        int newAmount = AmountUtil.amountFromString(amountField.textProperty().get());
        amountField.textProperty().setValue(AmountUtil.createAmountString(product, newAmount + 1));
        if(AmountUtil.amountFromString(amountField.textProperty().getValue())==2){
            minusIcon.getParent().setDisable(false);
        }
        //Remove:
    }

    @FXML
    private void decAmount() {
        int newAmount = AmountUtil.amountFromString(amountField.textProperty().get());
        if (newAmount > 1) {
            amountField.textProperty().setValue(AmountUtil.createAmountString(product, newAmount - 1));
        }
        if(AmountUtil.amountFromString(amountField.textProperty().getValue())==1){
            minusIcon.getParent().setDisable(true);
        }
    }

    private void setPrice(String newAmount) {
        double currentPrice = AmountUtil.amountFromString(newAmount) * product.getPrice();
        String currentPriceAsString = PriceUtil.toPriceFormat(currentPrice);
        price.textProperty().setValue(currentPriceAsString);
    }

    @FXML
    private void addProductToBasket() {
        if (!Basket.getInstance().contains(product)) {
            showAddedPane();
        }

        Basket.getInstance().addItem(
                new BasketItem(product, AmountUtil.amountFromString(amountField.textProperty().getValue()), this));
        setUpAmounts();
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



    //For making the tooltip appear quicker:
    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
