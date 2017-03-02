package main;

import se.chalmers.ait.dat215.project.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Henrik on 02/03/2017.
 */
public class ProductsModel extends Observable{
    private List<Product> products;
    private List<Product> allProducts;
    private static final IMatDataHandler handler = IMatDataHandler.getInstance();
    private static final ProductsModel INSTANCE = new ProductsModel();

    private ProductsModel(){}

    public static ProductsModel getInstance()
    {
        return INSTANCE;
    }
    public void setProducts(String[] categories){
        products = new ArrayList<>();
        for(String s:categories){
            ProductCategory category = ProductCategory.valueOf(s);
            products.addAll(handler.getProducts(category));
        }
        notifyObservers();
    }

    public void setSubmenuProducts(String s){
        List<Product> submenuProducts = new ArrayList<>();
        String searchStr = s.toLowerCase();

        for(Product p:products){
            if(p.getName().toLowerCase().equals(searchStr))
                submenuProducts.add(p);
        }
        setChanged();
        notifyObservers();
    }

    public void setFavoritesAsProducts(){
        products = handler.favorites();
        setChanged();
        notifyObservers();
    }

    public void searchProducts(String s){
        products = new ArrayList<>();
        String searchStr = s.toLowerCase();

        for(Product p:allProducts){
            if(p.getName().toLowerCase().equals(searchStr)){
                products.add(p);
            }
        }
        setChanged();
        notifyObservers();
    }

    public List<Product> getProducts(){
        return products;
    }


}
