package menu.historik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by hannacarlsson on 2017-02-24.
 */
public class HistorikController implements Initializable {

    @FXML private AnchorPane mainAnchorPane;
    @FXML private Accordion accordion;

    IMatDataHandler instance = IMatDataHandler.getInstance();
    List<Order> orderList = instance.getOrders();
    private String[] datesArray;
    private HistorikViewController historikViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datesArray = new String[orderList.size()];

        for (int i = 0; i < datesArray.length; i++) {
            Order order = orderList.get(i);
            datesArray[i] = order.getDate() + "";
            historikViewController = new HistorikViewController(this, instance.getOrders().get(i));
            TitledPane t = new TitledPane();
            t.getStyleClass().add("HistorikCSS.css");
            t.setContent(historikViewController.getMainAnchorPane());
            t.setText(datesArray[i]);

            accordion.getPanes().add(t);
        }
    }
}
