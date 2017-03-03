package shoppingView.basket.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import main.Main;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.listview.amountSetter.AmountSetter;
import shoppingView.listview.deleteButton.DeleteButton;
import shoppingView.util.PriceUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
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
    private TableColumn<BasketItem, AmountSetter> amountColumn;
    @FXML
    private TableColumn<BasketItem, String> productNameColumn;
    @FXML
    private TableColumn<BasketItem, String> priceColumn;
    @FXML
    private TableColumn<BasketItem, DeleteButton> deleteColumn;

    @FXML
    private Label totalPrice;

    private MainApp mainApp;
    private Main main;


    @FXML
    private void initialize() {

        currentItems.setItems(Basket.getInstance().getItems());
        //Unables selection of rows:
        currentItems.setSelectionModel(null);

        Basket.setBasketViewController(this);

        //Init columns
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        productNameColumn.setPrefWidth(135);

        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceAsString());
        priceColumn.setPrefWidth(80);

        amountColumn.setCellValueFactory(cellData -> cellData.getValue().getComboBox());
        deleteColumn.setCellValueFactory(cellData -> cellData.getValue().getDeleteButton());

        currentItems.getItems().addListener(new ListChangeListener<BasketItem>() {
            @Override
            public void onChanged(Change<? extends BasketItem> c) {
                updateSumLabel();
            }
        });

        basketImage.setImage(new Image("file:resources/images/basket.png"));
    }

    public void updateColumns() {
        System.out.println("updateColumns()");
        Product a = new Product();
        currentItems.getItems().add(new BasketItem(a, 1));
        currentItems.getItems().remove(currentItems.getItems().size() - 1);
    }

    //???
    private void updateSumLabel() {
        double sum = 0;
        System.out.println("Updating!");
        for (BasketItem item : Basket.getInstance().getItems()) {
            sum += item.getTotal();
        }
        totalPrice.textProperty().setValue(PriceUtil.toPriceFormat(sum));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void toCheckout(ActionEvent e){
       main.switchScene();
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
