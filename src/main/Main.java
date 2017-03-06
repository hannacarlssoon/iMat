package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import menu.*;
import paymentView.view.PaymentController;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import shoppingView.MainApp;
import shoppingView.basket.model.Basket;
import shoppingView.basket.view.BasketViewController;

public class Main extends Application {

    private MainController controller;
    private Stage primaryStage;
    private Scene shoppingScene;
    private Scene paymentScene;

    private ProductsModel productsModel;
    private Basket basket = Basket.getInstance();

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

        Menu menu = new Menu();
        menu.start(primaryStage);
        setMenuHolderContent(menu.getRoot());

        MainApp mainShop = new MainApp();
        mainShop.start(primaryStage);
        setShopHolderContent(mainShop.getRoot());
        mainShop.getBasketViewController().setMain(this);

        paymentView.MainApp mainPayment = new paymentView.MainApp();
        mainPayment.start(primaryStage);
        mainPayment.getController().setMain(this);
        Parent scene2 = mainPayment.getRoot();
        paymentScene = new Scene(scene2, 1366, 768);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                IMatDataHandler.getInstance().shutDown();
            }
        }));
    }


    public void setMenuHolderContent(Node e){
        controller.menuHolder.getChildren().removeAll();
        controller.menuHolder.getChildren().add(e);
    }

    public void setShopHolderContent(Node e){
        controller.shopHolder.getChildren().removeAll();
        controller.shopHolder.getChildren().add(e);
    }

    public void switchScene(){
        if (primaryStage.getScene().equals(shoppingScene)){
            primaryStage.setScene(paymentScene);
            primaryStage.show();
            basket.notifyController();

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
