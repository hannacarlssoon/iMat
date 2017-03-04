package shoppingView.basket.model;

import java.util.List;
import java.util.Stack;

public class RemovedItemsHolder {

    Stack<IndexedBasketItem> removedItems = new Stack<IndexedBasketItem>();


    public void addItemToRemoved(BasketItem removedItem, int index) {
        removedItems.push(new IndexedBasketItem(index, removedItem));
    }

    public IndexedBasketItem getLastRemovedItem() {
        if (removedItems.size() == 0) {
            return null;
        }

        return removedItems.pop();
    }

    public boolean isEmpty() {
        return removedItems.size() == 0;
    }
}
