import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menu.Menu;
import shoppingView.MainApp;

public class Main extends Application {

    MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainFxmlLoader = new FXMLLoader();
        mainFxmlLoader.setLocation(getClass().getResource("main.fxml"));
        Parent root = mainFxmlLoader.load();
        Scene scene = new Scene(root, 1366, 768);
        controller = mainFxmlLoader.getController();
        controller.setMain(this);

        primaryStage.setTitle("iMat");
        primaryStage.setScene(scene);
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

    public static void main(String[] args) {
        launch(args);
    }
}
