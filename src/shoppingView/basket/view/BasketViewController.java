package shoppingView.basket.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.Main;
import paymentView.view.PaymentController;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.basket.model.InvisibleBasketItem;
import shoppingView.listview.amountSetter.AmountSetter;
import shoppingView.listview.deleteButton.DeleteButton;
import shoppingView.listview.putBackButton.PutBackButton;
import shoppingView.util.PriceUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

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
    private AnchorPane putBack;
    @FXML
    private AnchorPane mainPane;

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
        productNameColumn.setPrefWidth(145);
        productNameColumn.setMaxWidth(145);
        productNameColumn.setMinWidth(145);

        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceAsString());
        priceColumn.setPrefWidth(90);

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
        //Added:

        System.out.println(priceColumn.getCellObservableValue(0));


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


    public void showPutBack() {
//        KeyValue keyValueFade  = new KeyValue(putBack.opacityProperty(), 1.0, Interpolator.EASE_OUT);
//
//        KeyFrame keyFrameFade  = new KeyFrame(Duration.millis(550), keyValueFade);
//
//        Timeline timeline  = new Timeline();
//        timeline.getKeyFrames().addAll(keyFrameFade);
//        timeline.play();

//        for (int i = 0; i < 5; i++) {
//            PutBackButton putBackButton = new PutBackButton(currentItems.getItems().get(0));
//            putBackButton.setLayoutY(84 + 44 * i);
//            mainPane.getChildren().add(putBackButton);
//        }

    }

    public void addPutBackButton(PutBackButton putBackButton) {
        mainPane.getChildren().add(putBackButton);
    }
    public void removePutBackButton() {
        //mainPane.getChildren().remove(putBackButton);

        List<Node> nodesToRemove = new ArrayList<Node>();
        for (Node node : mainPane.getChildren()) {
            if (node.getClass() == PutBackButton.class) {
                nodesToRemove.add(node);
                //mainPane.getChildren().remove(mainPane.getChildren().indexOf(node));
            }
        }
        for (Node node : nodesToRemove) {
            mainPane.getChildren().remove(node);
        }

        //Removes all the placeholders from the Basket:
        List<BasketItem> itemsToRemove = new ArrayList<BasketItem>();
        for (BasketItem item : Basket.getInstance().getItems()) {
            if (item.getClass() == InvisibleBasketItem.class) {
                itemsToRemove.add(item);
            }
        }
        for (BasketItem item : itemsToRemove) {
            Basket.getInstance().getItems().remove(item);
        }
    }

    public void hidePutBack() {
//        KeyValue keyValueFade  = new KeyValue(putBack.opacityProperty(), 0.0, Interpolator.EASE_OUT);
//
//        KeyFrame keyFrameFade  = new KeyFrame(Duration.millis(550), keyValueFade);
//
//        Timeline timeline  = new Timeline();
//        timeline.getKeyFrames().addAll(keyFrameFade);
//        timeline.play();

    }

    @FXML
    private void putBackClicked() {
        Basket.getInstance().putBackLastRemoved();
    }



}
