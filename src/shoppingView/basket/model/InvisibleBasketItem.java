package shoppingView.basket.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.listview.amountSetter.AmountSetter;
import shoppingView.listview.deleteButton.DeleteButton;

public class InvisibleBasketItem extends BasketItem {

    public InvisibleBasketItem(Product product, int amount) {
        super(product, amount);
    }

    @Override
    public StringProperty getPriceAsString() {
        return new SimpleStringProperty("");
    }

    @Override
    public ObjectProperty<AmountSetter> getComboBox() {
        AmountSetter as = super.getComboBox().get();
        as.setVisible(false);
        return new SimpleObjectProperty<AmountSetter>(as);
    }

    @Override
    public StringProperty getName() {
        return new SimpleStringProperty("");
    }

    @Override
    public ObservableValue<DeleteButton> getDeleteButton() {
        return null;//super.getDeleteButton();
    }
}
