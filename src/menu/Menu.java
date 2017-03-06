package menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shoppingView.MainApp;

import java.util.*;

public class Menu extends Application {
    private MenuController controller;
    private AnchorPane submenuPane;
    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
        root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        controller.setMenu(this);

        submenuPane = controller.getSubmenuPane();
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

    public void showHistory(){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("historik/historik.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
        } catch (Exception e){

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
