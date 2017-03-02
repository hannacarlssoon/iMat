package menu;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu extends Application {
    private MenuController controller;
    private AnchorPane submenuPane;
    private FXMLLoader fxmlLoader;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
        root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        controller.setMenu(this);

        submenuPane = controller.getSubmenuPane();

        //primaryStage.setTitle("menu");
        //primaryStage.setScene(new Scene(root, 1366, 153));
        //primaryStage.show();
    }

    public Parent getRoot(){
        return root;
    }

    /**
     *
     * @param layoutX layoutX property
     * @param layoutY layoutY property
     * @return the rightmost x pos of the menu item
     */
    public int paintSubmenuItem(String s, int layoutX, int layoutY){
        //create new button & init properties
        Button submenuItem = new Button(s);
        submenuItem.setLayoutX(layoutX);
        submenuItem.setLayoutY(layoutY);
        submenuItem.getStyleClass().add("submenuItem");

        //add action
        submenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.submenuActionPerformed(event);
            }
        });

        //add button to anchorPane
        submenuPane.getChildren().add(submenuItem);

        //update submenu css
        submenuPane.applyCss();
        submenuPane.layout();

        return (int)submenuItem.getLayoutX() +(int)submenuItem.getWidth();
    }

    public void pushNodeItemToFront(Node node){
        node.toFront();
    }

    public void pushNodeItemToBack(Node node){
        node.toBack();
    }

    public void translateMenuItem(Button item,int translate){
        item.setTranslateX(translate);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
