package shoppingView.util;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import se.chalmers.ait.dat215.project.Product;

import java.util.ArrayList;
import java.util.List;

public class AmountUtil {

    private static final String SHOW_MORE = "Fler...";

    public static List<String> createAmountList(Product product) {
        List<String> amountList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            String suffix = product.getUnitSuffix(); //(product.getUnitSuffix().equals("förp")) ? "frp" : product.getUnitSuffix();
            String amountRow = i + " " + suffix;
            amountList.add(amountRow);
        }

        amountList.add(SHOW_MORE);
        return amountList;
    }

    public static String createAmountString(Product product, int amount) {
        return amount + " " + product.getUnitSuffix();
    }

    public static int amountFromString(String amountString) {
        String number = amountString.split(" ")[0];
        return Integer.parseInt(number);
    }

    public static String getShowMoreString() {
        return SHOW_MORE;
    }

    public static ComboBox<String> createAmountBox(Product product, int amount) {
        ComboBox<String> box = new ComboBox<String>(
                new ObservableListWrapper<String>(AmountUtil.createAmountList(product)));
        box.setValue(box.getItems().get(amount - 1));

        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals(AmountUtil.getShowMoreString())) {
                    //TODO
                    System.out.println("Add more alternatives to combo box...");
                }
            }
        });

        return box;
    }
}
