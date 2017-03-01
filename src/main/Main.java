package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menu.Menu;
import shoppingView.MainApp;
import shoppingView.basket.view.BasketViewController;

public class Main extends Application {

    private MainController controller;
    private Stage primaryStage;
    private Scene shoppingScene;
    private Scene paymentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader mainShoppingSceneFxmlLoader = new FXMLLoader();
        mainShoppingSceneFxmlLoader.setLocation(getClass().getResource("main.fxml"));
        Parent scene1 = mainShoppingSceneFxmlLoader.load();
        shoppingScene = new Scene(scene1, 1366, 768);
        controller = mainShoppingSceneFxmlLoader.getController();
        controller.setMain(this);

        primaryStage.setTitle("iMat");
        primaryStage.setScene(shoppingScene);
        primaryStage.getIcons().add(new Image("file:resources/images/iMat_square.png"));
        primaryStage.show();

        FXMLLoader mainMenuFxmlLoader = new FXMLLoader();
        mainMenuFxmlLoader.setLocation(Menu.class.getResource("menu.fxml"));
        setMenuHolderContent(mainMenuFxmlLoader.load());

        FXMLLoader mainListFxmlLoader = new FXMLLoader();
        mainListFxmlLoader.setLocation(MainApp.class.getResource("listview/view/ListView.fxml"));
        setListHolderContent(mainListFxmlLoader.load());

        FXMLLoader mainBasketFxmlLoader = new FXMLLoader();
        mainBasketFxmlLoader.setLocation(MainApp.class.getResource("basket/view/BasketView.fxml"));
        setBasketHolderContent(mainBasketFxmlLoader.load());
        BasketViewController basketViewController = mainBasketFxmlLoader.getController();
        basketViewController.setMain(this);

        FXMLLoader mainPaymentSceneFxmlLoader = new FXMLLoader();
        mainPaymentSceneFxmlLoader.setLocation(paymentView.MainApp.class.getResource("view/Wizard.fxml"));
        Parent scene2 = mainPaymentSceneFxmlLoader.load();
        paymentScene = new Scene(scene2, 1366, 768);
    }


    public void setMenuHolderContent(Node e){
        controller.menuHolder.getChildren().removeAll();
        controller.menuHolder.getChildren().add(e);
    }

    public void setListHolderContent(Node e){
        controller.listHolder.getChildren().removeAll();
        controller.listHolder.getChildren().add(e);
    }

    public void setBasketHolderContent(Node e){
        controller.basketHolder.getChildren().removeAll();
        controller.basketHolder.getChildren().add(e);
    }

    public void switchScene(){
        if (primaryStage.getScene().equals(shoppingScene)){
            primaryStage.setScene(paymentScene);
            primaryStage.show();
        }
        else if (primaryStage.getScene().equals(paymentScene)){
            primaryStage.setScene(shoppingScene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
