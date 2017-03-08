package menu.historik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;

/**
 * Created by hannacarlsson on 2017-03-05.
 */
public class HistorikMain extends Application {

    private Main main;
    private Stage panelStage;
    private HistorikController historikController;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("historik.fxml"));
        Parent root = fxmlLoader.load();
        historikController = fxmlLoader.getController();
        historikController.setHistorikMain(this);
        Scene scene = new Scene(root, 820, 720);
        stage.setScene(scene);
        setPanelStage(stage);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void closePanelStage() {
        panelStage.close();
        main.historyClosed();
    }

    public void setPanelStage(Stage panelStage) {
        this.panelStage = panelStage;
        this.panelStage.initStyle(StageStyle.UNDECORATED);
    }

    public void setMain(Main main){
        this.main = main;
    }
}
