package shoppingView.listview.view;

import shoppingView.MainApp;
import shoppingView.listview.productsquare.view.ProductView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListViewController {

    private MainApp mainApp;

    private GridPane productGrid;

    @FXML
    private VBox gridPaneContainer;

    @FXML
    private void initialize() {
        productGrid = new GridPane();
        gridPaneContainer.getChildren().add(productGrid);
    }



    @FXML
    public void testSearch() {
        productGrid.getChildren().clear();

        int columns = 4;
        int counter = 0;

        for (int i = 0; i < 20; i++) {
            productGrid.add(new ProductView(counter, mainApp), counter % columns, counter / columns);
            counter++;
        }
        productGrid.setHgap(20);
        productGrid.setVgap(20);
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setPadding(new Insets(20, 20, 40, 0));
    }

    @FXML
    public void showResults(List<Integer> productIDs) {
        productGrid.getChildren().clear();

        int columns = 4;
        int counter = 0;

        for (Integer ID : productIDs) {
            productGrid.add(new ProductView(ID, mainApp), counter % columns, counter / columns);
            counter++;
        }
        productGrid.setHgap(20);
        productGrid.setVgap(20);
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setPadding(new Insets(20, 20, 40, 0));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
