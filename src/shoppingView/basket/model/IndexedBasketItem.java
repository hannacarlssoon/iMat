package shoppingView.basket.model;

public class IndexedBasketItem {
    private int index;
    private BasketItem basketItem;

    public IndexedBasketItem(int index, BasketItem basketItem) {
        this.index = index;
        this.basketItem = basketItem;
    }

    public int getIndex() {
        return index;
    }

    public BasketItem getBasketItem() {
        return basketItem;
    }
}
