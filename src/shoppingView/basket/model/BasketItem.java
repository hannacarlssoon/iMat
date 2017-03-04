package shoppingView.basket.model;

import shoppingView.basket.view.BasketViewController;
import shoppingView.listview.amountSetter.AmountSetter;
import shoppingView.listview.deleteButton.DeleteButton;
import shoppingView.listview.productsquare.view.ProductView;
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
    private AmountSetter amountSetter;
    private SimpleStringProperty price = new SimpleStringProperty("");
    private ProductView productView;

    public BasketItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public BasketItem(Product product, int amount, ProductView productView) {
        this.product = product;
        this.amount = amount;
        this.productView = productView;
    }

    public double getTotal() {
        return product.getPrice() * amount;
    }

    public StringProperty getPriceAsString() {
        //String priceString = PriceUtil.toPriceFormat(getTotal());
        //price = new SimpleStringProperty(priceString);

        String priceString = PriceUtil.toPriceFormat(product.getPrice());
        //price = new SimpleStringProperty(amount + " x " + priceString);
        price.setValue(amount + " x " + priceString);
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmountSetter(AmountSetter amountSetter) {
        this.amountSetter = amountSetter;
    }

    public ObjectProperty<AmountSetter> getComboBox() {

        //return propertyBox;
        return new SimpleObjectProperty<AmountSetter>(new AmountSetter(product.getProductId(), amount, this));
    }

    public StringProperty getName() {
        return new SimpleStringProperty(product.getName());
    }

    public void addAmount(int amountToAdd) {
        amount += amountToAdd;
        amountSetter.updateAmount();
        Basket.updateBasket();
        getPriceAsString();
    }

    public ObservableValue<DeleteButton> getDeleteButton() {
        DeleteButton button = new DeleteButton(this);

        ObjectProperty<DeleteButton> propertyButton = new SimpleObjectProperty<DeleteButton>(button);

        return propertyButton;
    }

    public void hideAddedPane() {
        productView.hideAddedPane();
    }
}
