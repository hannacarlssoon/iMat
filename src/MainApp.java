
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Testkommentar
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Wizard.fxml"));
        //FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("Wizard.fxml"));
        //fxmlLoader.setController(this);
        //Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1366, 768);

        stage.setTitle("iMat");
        stage.setScene(scene);
        stage.show();
    }
}
