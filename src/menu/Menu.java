package menu;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu extends Application {
    AnchorPane submenu;
    List<Button> menuItems = new LinkedList<>();
    MenuController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        controller.setMenu(this);

        primaryStage.setTitle("menu");
        primaryStage.setScene(new Scene(root, 1920, 216));

        getNodes(root);

        translateMenuItems();

        structureMenuItems(-1);

        primaryStage.show();


    }

    /**
     * remove all items from the submenu
     */
    public void emptySubmenu(){
        ObservableList<Node> children = submenu.getChildren();
        List<Node> submenuItems = new ArrayList<Node>();

        if(children.size()>2){
            for(int i=2;i<children.size();i++){
                submenuItems.add(children.get(i));
            }
        }

        for(Node s:submenuItems){
            children.remove(s);
        }
    }

    public void paintSubmenu(List<String> submenuList){
        int length = 30;
        for(String s:submenuList){
            //create new button & init properties
            Button submenuItem = new Button(s);
            submenuItem.setLayoutX(5 + length);
            submenuItem.setLayoutY(10);
            submenuItem.getStyleClass().add("submenuItem");

            //add new actionPerformed method
            submenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.submenuActionPerformed(event);
                }
            });

            //add button to anchorPane
            submenu.getChildren().add(submenuItem);

            //update submenu css
            submenu.applyCss();
            submenu.layout();

            length = length + 10 + (int)submenuItem.getWidth();
        }
    }

    private void getNodes(Parent root){
        List<Node> nodes = root.getChildrenUnmodifiable();

        ObservableList<String> menuItemStyleClass = nodes.get(0).getStyleClass();

        for(Node node:nodes){
            if(node instanceof Button && node.getStyleClass().equals(menuItemStyleClass))
                menuItems.add((Button)node);
            else if(node instanceof AnchorPane)
                submenu = (AnchorPane) node;
        }
    }

    private void translateMenuItems(){
        for(int i=0;i<menuItems.size()-1;i++){
            menuItems.get(i).setTranslateX(-7*i);
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
                menuItems.get(i).toBack();
            } if(i<toFront){
                menuItems.get(i).toFront();
            }
        }

        submenu.toFront();

        if(index>=0){
            menuItems.get(index).toFront();
            menuItems.get(index).getStyleClass().add("currentButton");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
