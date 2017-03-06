package shoppingView.basket.model;

import paymentView.view.PaymentController;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingView.basket.view.BasketViewController;
import shoppingView.listview.productsquare.view.ProductView;
import shoppingView.util.PriceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.chalmers.ait.dat215.project.Product;

public final class Basket {

    private static final Basket basket = new Basket();
    private ObservableList<BasketItem> items;
    private static BasketViewController basketViewController;
    private RemovedItemsHolder removedItemsHolder = new RemovedItemsHolder();
    private static final IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    private PaymentController paymentController;

    public static void setBasketViewController(BasketViewController basketViewController) {
        Basket.basketViewController = basketViewController;
    }

    public static void updateBasket() {
        basketViewController.updateColumns();
    }

    public static Basket getInstance() {
        return basket;
    }

    private Basket() {
        items = FXCollections.observableArrayList();;
    }

    public Basket(ObservableList<BasketItem> items) {
        this.items = items;
    }

    public void addItem(BasketItem newItem) {
        for (BasketItem item : items) {
            if (item.getProduct().equals(newItem.getProduct())) {
                item.addAmount(newItem.getAmount());
                return;
            }
        }

        items.add(0, newItem);
    }

    public boolean contains(Product product) {
        for (BasketItem item : items) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }

    public BasketItem removeItem(Product productToRemove) {
        for (BasketItem item : items) {
            if (item.getProduct().equals(productToRemove)) {
                int index = items.indexOf(item);

                items.remove(item);
                removedItemsHolder.addItemToRemoved(item, index);
                System.out.println("Removed");

                basketViewController.showPutBack();

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
            totalSum += item.getTotal();
        }
        return totalSum;
    }

    public String getTotalSumAsString() {
        return PriceUtil.toPriceFormat(getTotalSum());
    }

    public void clearBasket() {
        while (items.size() > 0) {
            removeItem(items.get(0).getProduct());
        }
    }

    public void putBasketItemsInShoppingCart() {
        ShoppingCart shoppingCart = IMatDataHandler.getInstance().getShoppingCart();
        shoppingCart.clear();
        for (BasketItem item : items) {
            ShoppingItem correspondingItem = new ShoppingItem(item.getProduct(), item.getAmount());
            shoppingCart.addItem(correspondingItem);
        }
    }

    public void setPaymentController(PaymentController p) {
        paymentController = p;
    }

    public void notifyController() {
        paymentController.updateBasket();
    }

    public void addBasketToFavorites(){
        for(BasketItem item:items){
            iMatDataHandler.addFavorite(item.getProduct());
        }
    }

    public void putBackLastRemoved() {
        IndexedBasketItem itemToPutBack = removedItemsHolder.getLastRemovedItem();
        if (itemToPutBack != null) {
            for (BasketItem item : items) {
                if (item.getProduct().equals(itemToPutBack.getBasketItem().getProduct())) {
                    item.addAmount(itemToPutBack.getBasketItem().getAmount());
                    return;
                }
            }

            if (items.size() > itemToPutBack.getIndex()) {
                items.add(itemToPutBack.getIndex(), itemToPutBack.getBasketItem());
            } else {
                items.add(itemToPutBack.getBasketItem());
            }
            itemToPutBack.getBasketItem().showAddedPane();

            if (removedItemsHolder.isEmpty()) {
                basketViewController.hidePutBack();
            }
        } else {
            basketViewController.hidePutBack();
        }
    }
}
