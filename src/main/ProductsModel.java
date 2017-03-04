package main;

import se.chalmers.ait.dat215.project.*;

import java.util.*;

/**
 * Created by Henrik on 02/03/2017.
 */
public class ProductsModel extends Observable{

    //list of products to view
    private List<Product> products;

    //list of all products in store
    private List<Product> allProducts = handler.getProducts();

    //the latest search
    private String[] categories;

    //current used sort for the products list
    private String sort;

    private static final IMatDataHandler handler = IMatDataHandler.getInstance();

    private static final ProductsModel INSTANCE = new ProductsModel();



    private ProductsModel(){}

    public static ProductsModel getInstance()
    {
        return INSTANCE;
    }


    /**
     * Set products to all with matching category
     * @param categories
     */
    public void setProducts(String[] categories){
        this.categories = categories;

        products = new ArrayList<>();

        for(String s:categories){
            ProductCategory category = ProductCategory.valueOf(s);
            products.addAll(handler.getProducts(category));
        }

        setChanged();
        notifyObservers();
    }

    /**
     * set products to all in current list
     * that matches with "s"
     * @param s search string
     */
    public void setSubmenuProducts(String s){
        List<Product> submenuProducts = new ArrayList<>();
        String searchStr = s.toLowerCase();

        for(Product p:products){
            if(p.getName().toLowerCase().equals(searchStr))
                submenuProducts.add(p);
        }
        products = submenuProducts;

        setChanged();
        notifyObservers();
    }

    /**
     * Set products to those that matches with "s" among all products
     * @param s
     */
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

    /**
     * Change sort of products
     * @param s
     */
    public void sortProducts(String s){
        if(products!=null){
            if(s.equals("letter"))
                sortByLetter();
            else if(s.equals("price"))
                sortByPrice();
            else if(s.equals("init") && categories != null)
                setProducts(categories);
        }
    }

    private void sortByLetter(){
        products.sort(new CompLetter());
        setChanged();
        notifyObservers();
    }

    private void sortByPrice(){
        products.sort(new CompPrice());
        setChanged();
        notifyObservers();
    }

    public void setFavoritesAsProducts(){
        products = handler.favorites();
        setChanged();
        notifyObservers();
    }

    public List<Product> getProducts(){
        return products;
    }

    private class CompLetter implements Comparator<Product>{
        /**
         *
         * @param o1
         * @param o2
         * @return positive if o1>o2, 0 if equal, negative if o1<o2
         */
        @Override
        public int compare(Product o1, Product o2) {
            if(o1.getName().compareTo(o2.getName())<0)
                return -1;
            else if(o1.getName().compareTo(o2.getName())==0)
                return 0;
            else
                return 1;
        }
    }

    private class CompPrice implements Comparator<Product>{
        /**
         *
         * @param o1
         * @param o2
         * @return positive if o1>o2, 0 if equal, negative if o1<o2
         */
        @Override
        public int compare(Product o1, Product o2) {
            Double price1 = o1.getPrice();
            Double price2 = o2.getPrice();

            if(price1.compareTo(price2)<0)
                return -1;
            else if(price1.compareTo(price2)==0)
                return 0;
            else
                return -1;
        }
    }


    public List<Product> getAllProducts() {
        return allProducts;
    }
}
