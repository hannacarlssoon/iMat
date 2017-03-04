package shoppingView.listview.productsquare.view;


import main.ProductsModel;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductViewFactory {

    private static Map<Integer, ProductView> productViewMap = new HashMap<Integer, ProductView>();

    public static ProductView createProductView(Product product, MainApp mainApp) {
        if (productViewMap.containsKey(product.getProductId())) {
            return productViewMap.get(product.getProductId());
        } else {
            ProductView newProductView = new ProductView(product, mainApp);
            productViewMap.put(product.getProductId(), newProductView);
            return newProductView;
        }
    }

    public static void setUpAllProductViews(MainApp mainApp) {
        Thread setUpThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Product product : ProductsModel.getInstance().getAllProducts()) {
                    createProductView(product, mainApp);
                }
            }
        });

        setUpThread.start();
    }

}
