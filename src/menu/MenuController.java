package menu;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
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

    //list of all category menu items
    List<Button> menuItems;

    //class for view
    private Menu menu;
    //content for the submenumodel
    private SubmenuModel submenuModel = new SubmenuModel();

    private Button selectedSubmenuItem;

    public void paintSubmenu(List<String> submenuList){
        int i = 0;
        for(String s:submenuList){
            i = menu.paintSubmenuItem(submenuPane,s,i+5,10);
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
        translateMenuItems();
        structureMenuItems(-1);
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
        //TODO: add action
        menuItemAction(0);
    }

    @FXML public void meatItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(1);
    }

    @FXML public void dairyItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(2);
    }

    @FXML public void drinksItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(3);
    }

    @FXML public void breadItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(4);
    }

    @FXML public void pantryItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(5);
    }

    @FXML public void sweetsItemActionPerformed(ActionEvent event){
        //TODO: add action
        menuItemAction(6);
    }

    @FXML public void favoriteItemActionPerformed(ActionEvent event){
        //TODO: add action
        emptySubmenu();
        structureMenuItems(7);
        menu.pushNodeItemToFront(favoriteItem);
    }

    @FXML public void logoActionPerformed(ActionEvent event){
        //TODO: add action
    }

    @FXML public void searchFieldActionPerformed(ActionEvent event){
        System.out.println(searchField.getText());
        //TODO add action
    }

    @FXML public void searchButtonActionPerformed(ActionEvent event){
        System.out.println(searchField.getText());
        //TODO add action
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
        paintSubmenu(submenuModel.getSubmenu(i));
    }

    public void emptySubmenu(){
        ObservableList<Node> children = submenuPane.getChildren();
        List<Node> submenuItems = new ArrayList<Node>();

        if(children.size()>2){
            for(int i=1;i<children.size();i++){
                submenuItems.add(children.get(i));
            }
        }

        for(Node s:submenuItems){
            children.remove(s);
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuItems = getMenuItems();
    }
}
