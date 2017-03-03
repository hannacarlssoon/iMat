package paymentView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paymentView.view.PaymentController;

//Testkommentar
public class MainApp extends Application {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private PaymentController paymentController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view/Wizard.fxml"));
        root = fxmlLoader.load();
        paymentController = fxmlLoader.getController();
    }

    public Parent getRoot(){
        return root;
    }

    public PaymentController getController(){
        return paymentController;
    }
}
