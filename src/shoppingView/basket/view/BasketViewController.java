package shoppingView.basket.view;

import javafx.event.ActionEvent;
import main.Main;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.util.PriceUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasketViewController {

    @FXML
    private ImageView basketImage;
    @FXML
    private javafx.scene.control.TableView<BasketItem> currentItems;
    @FXML
    private TableColumn<BasketItem, ComboBox<String>> amountColumn;
    @FXML
    private TableColumn<BasketItem, String> productNameColumn;
    @FXML
    private TableColumn<BasketItem, String> priceColumn;
    @FXML
    private TableColumn<BasketItem, ImageView> deleteColumn;

    @FXML
    private Label totalPrice;

    private MainApp mainApp;
    private Main main;


    @FXML
    private void initialize() {

        currentItems.setItems(Basket.getInstance().getItems());
        //Unables selection of rows:
        currentItems.setSelectionModel(null);

        //Init columns
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceAsString());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().getComboBox());
        deleteColumn.setCellValueFactory(cellData -> cellData.getValue().getDeleteIcon());

        currentItems.getItems().addListener(new ListChangeListener<BasketItem>() {
            @Override
            public void onChanged(Change<? extends BasketItem> c) {
                updateSumLabel();
            }
        });

        basketImage.setImage(new Image("file:resources/images/basket.png"));
    }

    @FXML
    private void toCheckout(ActionEvent e){
        main.switchScene();
    }

    //???
    private void updateSumLabel() {
        double sum = 0;
        System.out.println("Updating!");
        for (BasketItem item : Basket.getInstance().getItems()) {
            sum += item.getPrice() * item.getAmount();
        }
        totalPrice.textProperty().setValue(PriceUtil.toPriceFormat(sum));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
