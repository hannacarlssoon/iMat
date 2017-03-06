package shoppingView;

import javafx.scene.Parent;
import shoppingView.basket.view.BasketViewController;
import shoppingView.listview.productsquare.moreInfoPanel.MoreInfoPanelController;
import shoppingView.listview.view.ListViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private HBox root;
    private FXMLLoader fxmlLoader;
    private BasketViewController basketViewController;
    private ListViewController listViewController;

    public MainApp() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("combinedview/CombinedView.fxml"));
        root = loader.load();

        FXMLLoader basketLoader = new FXMLLoader();
        basketLoader.setLocation(MainApp.class.getResource("basket/view/BasketView.fxml"));
        AnchorPane basketView = basketLoader.load();
        basketViewController = basketLoader.getController();

        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(MainApp.class.getResource("listview/view/ListView.fxml"));
        AnchorPane listView = listLoader.load();
        listViewController = listLoader.getController();
        listViewController.setMainApp(this);
        listViewController.testSearch();

        root.getChildren().add(listView);
        root.getChildren().add(basketView);
    }

    public void showMoreInfoPanel(Product product) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("listview/productsquare/" +
                    "moreInfoPanel/MoreInfoPanel.fxml"));
            AnchorPane panel = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage panelStage = new Stage();
            panelStage.setTitle("...");
            panelStage.initModality(Modality.WINDOW_MODAL);
            panelStage.initOwner(primaryStage);
            Scene scene = new Scene(panel);
            panelStage.setScene(scene);

            // Set the person into the controller.
            MoreInfoPanelController controller = loader.getController();
            controller.setPanelStage(panelStage);
            controller.setProduct(product);

            // Show the dialog and wait until the user closes it
            panelStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent getRoot(){
        return root;
    }

    public BasketViewController getBasketViewController() {
        return basketViewController;
    }

    public ListViewController getListViewController() {
        return listViewController;
    }


}
