package shoppingView.listview.productsquare.moreInfoPanel;

import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.util.AmountUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

public class MoreInfoPanelController {

    @FXML
    private ImageView image;
    @FXML
    private Label productName;
    @FXML
    private Label description;
    @FXML
    private ComboBox<String> amount;
    @FXML
    private Label price;
    @FXML
    private AnchorPane comboPane;

    private Stage panelStage;
    private Product product;

    // Reference to the main application.
    private MainApp mainApp;

    public void setPanelStage(Stage panelStage) {
        this.panelStage = panelStage;
        this.panelStage.initStyle(StageStyle.UNDECORATED);

    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setProduct(Product product) {
        this.product = product;
        setImage();
        setProductName();
        initAmountBox();
    }

    private void setImage() {
        String imageName = product.getImageName();
        image.setImage(new Image("file:resources/images/products/" + imageName));
    }

    private void initAmountBox() {
        amount = AmountUtil.createAmountBox(product, 1);
        amount.setPrefHeight(39);
        amount.getStyleClass().add("combo-box-amount");

        comboPane.getChildren().add(amount);

    }

    @FXML
    private void returnButtonClicked() {
        panelStage.close();
    }

    @FXML
    private void addButtonClicked() {
        addProductToBasket();
        //returnButtonClicked();
    }

    @FXML
    private void addProductToBasket() {
        Basket.getInstance().addItem(
                new BasketItem(product, AmountUtil.amountFromString(amount.getValue())));
    }


    private void setProductName() {
        productName.textProperty().setValue(product.getName());
    }
}
