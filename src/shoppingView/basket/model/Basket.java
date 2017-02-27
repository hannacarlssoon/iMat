package shoppingView.basket.model;

import shoppingView.util.PriceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.chalmers.ait.dat215.project.Product;

public final class Basket {

    private static Basket basket;
    private ObservableList<BasketItem> items;

    public static Basket getInstance() {
        if (basket == null) {
            return new Basket();
        } else {
            return basket;
        }
    }

    private Basket() {
        items = FXCollections.observableArrayList();;
        basket = this;
    }

    public Basket(ObservableList<BasketItem> items) {
        this.items = items;
    }

    public void addItem(BasketItem newItem) {
        for (BasketItem item : items) {
            if (item.getProduct().equals(newItem.getProduct())) {
                //item.addAmount(newItem.getAmount());
                return;
            }
        }

        items.add(newItem);
    }

    public BasketItem removeItem(Product productToRemove) {
        for (BasketItem item : items) {
            if (item.getProduct().equals(productToRemove)) {
                items.remove(item);
                return item;
            }
        }

        return null;
    }

    public ObservableList<BasketItem> getItems() {
        return items;
    }

    public double getTotalSum() {
        double totalSum = 0;
        for (BasketItem item : items) {
            totalSum += item.getPrice();
        }
        return totalSum;
    }

    public String getTotalSumAsString() {
        return PriceUtil.toPriceFormat(getTotalSum());
    }
}
