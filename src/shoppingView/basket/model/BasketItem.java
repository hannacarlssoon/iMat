package shoppingView.basket.model;

import shoppingView.util.AmountUtil;
import shoppingView.util.PriceUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import se.chalmers.ait.dat215.project.Product;

public class BasketItem {

    private Product product;
    private int amount;
    private ComboBox<String> box;
    private SimpleStringProperty price;

    public BasketItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public double getPrice() {
        return product.getPrice() * amount;
    }

    public StringProperty getPriceAsString() {
        String priceString = PriceUtil.toPriceFormat(getPrice());
        price = new SimpleStringProperty(priceString);
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public ObjectProperty<ComboBox<String>> getComboBox() {
        box = AmountUtil.createAmountBox(product, amount);
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                amount = AmountUtil.amountFromString(newValue);
                price.setValue("Hej"); //Varför funkar detta??
            }
        });

        box.getStyleClass().add("combo-box-amount-basket");

        ObjectProperty<ComboBox<String>> propertyBox = new SimpleObjectProperty<ComboBox<String>>(box);

        return propertyBox;
    }

    public StringProperty getName() {
        return new SimpleStringProperty(product.getName());
    }

    public void addAmount(int amountToAdd) {
        amount += amountToAdd;
    }

    public ObservableValue<ImageView> getDeleteIcon() {
        ImageView image = new ImageView(new Image("file:resources/images/trash.png"));
        image.setFitHeight(20);
        image.setFitWidth(20);


        image.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Delete clicked.");
            }
        });

        ObjectProperty<ImageView> propertyImage = new SimpleObjectProperty<ImageView>(image);

        return propertyImage;
    }
}
