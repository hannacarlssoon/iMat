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
    private HistorikMain historikMain;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datesArray = new String[orderList.size()];

        for (int i = datesArray.length -1; i >= 0; i--) {
            Order order = orderList.get(i);
            datesArray[i] = order.getDate() + "";
            historikViewController = new HistorikViewController(this, instance.getOrders().get(i));
            TitledPane t = new TitledPane();
            t.getStyleClass().add("titled-pane");
            t.setContent(historikViewController.getMainAnchorPane());
            String newWord = "";
            String[] words = datesArray[i].split(" ");

            for (int j = 0; j < words.length; j++) {
                if (words[j].equals("Mon")) {
                    newWord = "Måndag " + newWord;
                }
                if (words[j].equals("Tue")) {
                    newWord = "Tisdag " + newWord;
                }
                if (words[j].equals("Wed")) {
                    newWord = "Onsdag " + newWord;
                }
                if (words[j].equals("Thur")) {
                    newWord = "Torsdag " + newWord;
                }
                if (words[j].equals("Fri")) {
                    newWord = "Fredag " + newWord;
                }
                if (words[j].equals("Sat")) {
                    newWord = "Lördag " + newWord;
                }
                if (words[j].equals("Sun")) {
                    newWord = "Söndag " + newWord;
                }
                if (words[j].equals("Jan")) {
                    newWord = newWord + "Januari ";
                }
                if (words[j].equals("Feb")) {
                    newWord = newWord + "Februari ";
                }
                if (words[j].equals("Mar")) {
                    newWord = newWord + "Mars ";
                }
                if (words[j].equals("April")) {
                    newWord = newWord + "April ";
                }
                if (words[j].equals("May")) {
                    newWord = newWord + "Maj ";
                }
                if (words[j].equals("June")) {
                    newWord = newWord + "Juni ";
                }
                if (words[j].equals("July")) {
                    newWord = newWord + "Juli ";
                }
                if (words[j].equals("Aug")) {
                    newWord = newWord + "Augusti ";
                }
                if (words[j].equals("Sep")) {
                    newWord = newWord + "September ";
                }
                if (words[j].equals("Oct")) {
                    newWord = newWord + "Oktober ";
                }
                if (words[j].equals("Nov")) {
                    newWord = newWord + "November ";
                }
                if (words[j].equals("Dec")) {
                    newWord = newWord + "December ";
                }
            }
            newWord = newWord + words[2] + "   " + words[3] + " " + words[5];
            t.setText(newWord);
            accordion.getPanes().add(t);
        }
    }

    @FXML
    private void returnButtonClicked() {
        historikMain.closePanelStage();
    }

    public void setHistorikMain(HistorikMain historikMain){
        this.historikMain = historikMain;
    }
}
