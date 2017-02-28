package menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @FXML private Button backButton;
    @FXML private Button forwardButton;

    //class for view
    private Menu menu;
    //content for the submenumodel
    private SubmenuModel submenuModel = new SubmenuModel();


    public void setMenu(Menu menu){
        this.menu = menu;
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
        menuItemAction(7);
    }

    @FXML public void logoActionPerformed(ActionEvent event){
        //TODO: add action
    }

    @FXML public void backButtonActionPerformed(ActionEvent event){
        //TODO: add action
    }

    @FXML public void forwardButtonActionPerformed(ActionEvent event){
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
        Button submenuItem = (Button)event.getSource();
        String itemText = submenuItem.getText().toLowerCase();
        System.out.println(itemText);
        //TODO: add action
    }

    private void menuItemAction(int i){
        menu.structureMenuItems(i);
        menu.emptySubmenu();
        menu.paintSubmenu(submenuModel.getSubmenu(i));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
