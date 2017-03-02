package menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.ProductsModel;

public class MenuController implements Initializable{
    //fx variables
    @FXML private Button fruitsItem;
    @FXML private Button meatItem;
    @FXML private Button dairyItem;
    @FXML private Button drinksItem;
    @FXML private Button breadItem;
    @FXML private Button pantryItem;
    @FXML private Button sweetsItem;
    @FXML private Button favoriteItem;
    @FXML private Button logo;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private AnchorPane submenuPane;

    //list of all menu items
    List<Button> menuItems;

    //class for view
    private Menu menu;

    //content for the submenumodel
    private SubmenuModel submenuModel;

    //
    private Button selectedSubmenuItem;

    //model for products
    ProductsModel productsModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuItems = getMenuItems();
    }

    public void setMenu(Menu menu){
        this.menu = menu;
        submenuModel = new SubmenuModel(menu);

        translateMenuItems();
        structureMenuItems(-1);
    }

    public AnchorPane getSubmenuPane(){
        return submenuPane;
    }

    public List<Button> getMenuItems(){
        List<Button> menuItems = new ArrayList<>();

        menuItems.add(fruitsItem);
        menuItems.add(meatItem);
        menuItems.add(dairyItem);
        menuItems.add(drinksItem);
        menuItems.add(breadItem);
        menuItems.add(pantryItem);
        menuItems.add(sweetsItem);
        menuItems.add(favoriteItem);

        return menuItems;
    }


    @FXML public void fruitsItemActionPerformed(ActionEvent event){
        menuItemAction(0);
        String[] strings = {"FRUIT","CABBAGE","CITRUS_FRUIT","HERB","ROOT_VEGETABLE","POD","MELONS","VEGETABLE_FRUIT","BERRY"};
        productsModel.setProducts(strings);
    }

    @FXML public void meatItemActionPerformed(ActionEvent event){
        String[] strings = {"FISH","MEAT"};
        productsModel.setProducts(strings);
        menuItemAction(1);
    }

    @FXML public void dairyItemActionPerformed(ActionEvent event){
        String[] strings = {"DAIRIES"};
        productsModel.setProducts(strings);
        menuItemAction(2);
    }

    @FXML public void drinksItemActionPerformed(ActionEvent event){
        String[] strings = {"HOT_DRINKS","COLD_DRINKS"};
        productsModel.setProducts(strings);
        menuItemAction(3);
    }

    @FXML public void breadItemActionPerformed(ActionEvent event){
        String[] strings = {"BREAD"};
        productsModel.setProducts(strings);
        menuItemAction(4);
    }

    @FXML public void pantryItemActionPerformed(ActionEvent event){
        String[] strings = {"FLOUR_SUGAR_SALT","NUTS_AND_SEEDS","PASTA","POTATO_RICE"};
        productsModel.setProducts(strings);
        menuItemAction(5);
    }

    @FXML public void sweetsItemActionPerformed(ActionEvent event){
        String[] strings = {"SWEETS"};
        productsModel.setProducts(strings);
        menuItemAction(6);
    }

    @FXML public void favoriteItemActionPerformed(ActionEvent event){
        productsModel.setFavoritesAsProducts();
        emptySubmenu();
        structureMenuItems(7);
        menu.pushNodeItemToFront(favoriteItem);
    }

    @FXML public void logoActionPerformed(ActionEvent event){
        //TODO: add action
    }

    @FXML public void searchFieldActionPerformed(ActionEvent event){
        productsModel.searchProducts(searchField.getText());
        emptySubmenu();
        structureMenuItems(-1);
    }

    @FXML public void searchButtonActionPerformed(ActionEvent event){
        productsModel.searchProducts(searchField.getText());
        emptySubmenu();
        structureMenuItems(-1);
    }

    public void submenuActionPerformed(ActionEvent event){
        if(selectedSubmenuItem!=null)
            selectedSubmenuItem.getStyleClass().remove("selectedSubmenuItem");

        Button submenuItem = (Button)event.getSource();
        submenuItem.getStyleClass().add("selectedSubmenuItem");
        selectedSubmenuItem = submenuItem;

        String itemText = submenuItem.getText().substring(2).toLowerCase();
        System.out.println(itemText);
        //TODO: add action
    }

    private void menuItemAction(int i){
        structureMenuItems(i);
        emptySubmenu();
        submenuModel.setSubmenu(i);
    }

    private void emptySubmenu(){
        submenuPane.getChildren().remove(0,submenuPane.getChildren().size());
    }

    /**
     * places the menuItem with index in front
     * submenu in front if index is negative
     * @param index
     */
    public void structureMenuItems(int index){
        int toFront;
        if(index<0)
            toFront = 0;
        else
            toFront = index;

        for(int i=0;i<menuItems.size();i++){
            menuItems.get(i).getStyleClass().remove("currentButton");

            if(i>toFront){
                menu.pushNodeItemToBack(menuItems.get(i));
            } if(i<toFront){
                menu.pushNodeItemToFront(menuItems.get(i));
            }
        }

        submenuPane.toFront();

        if(index>=0){
            menu.pushNodeItemToFront(menuItems.get(index));
            menuItems.get(index).getStyleClass().add("currentButton");
        }
    }

    private void translateMenuItems(){
        for(int i=0;i<menuItems.size()-1;i++){
            menu.translateMenuItem(menuItems.get(i),-7*i);
        }
    }
}
