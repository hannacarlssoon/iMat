package shoppingView;

import shoppingView.listview.productsquare.moreInfoPanel.MoreInfoPanelController;
import shoppingView.listview.view.ListViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private HBox rootLayout;

    public MainApp() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Exempel på produktvy och varukorg");
        this.primaryStage.setResizable(false);

        //Sets the window icon:
        this.primaryStage.getIcons().add(new Image("file:resources/images/iMat_square.png"));

        initRootLayout();
        showListViewAndBasket();
    }

    private void showListViewAndBasket() {
        try {
            FXMLLoader basketLoader = new FXMLLoader();
            basketLoader.setLocation(MainApp.class.getResource("basket/view/BasketView.fxml"));
            AnchorPane basketView = (AnchorPane) basketLoader.load();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("listview/view/ListView.fxml"));
            AnchorPane listView = (AnchorPane) loader.load();
            ((ListViewController) loader.getController()).setMainApp(this);
            ((ListViewController) loader.getController()).testSearch();

            rootLayout.getChildren().add(listView);
            rootLayout.getChildren().add(basketView);


//            FXMLLoader productLoader = new FXMLLoader();
//            productLoader.setLocation(MainApp.class.getResource("listview/productsquare/view/ProductView.fxml"));
//            AnchorPane productView = (AnchorPane) productLoader.load();
//            ProductView productView = new ProductView();
//            productView.setLayoutX(100);
//            productView.setLayoutY(100);

//            listView.getChildren().add(productView);
            // Give the controller access to the main app.
//            WorkingAreaController controller = loader.getController();
//            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("combinedview/CombinedView.fxml"));
            rootLayout = (HBox) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
