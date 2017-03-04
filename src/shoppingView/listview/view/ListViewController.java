package shoppingView.listview.view;

import main.ProductsModel;
import se.chalmers.ait.dat215.project.Product;
import shoppingView.MainApp;
import shoppingView.listview.productsquare.view.ProductView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import shoppingView.listview.productsquare.view.ProductViewFactory;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ListViewController implements Observer {

    private MainApp mainApp;

    private GridPane productGrid;

    @FXML
    private VBox gridPaneContainer;

    @FXML
    private void initialize() {
        productGrid = new GridPane();
        gridPaneContainer.getChildren().add(productGrid);
        ProductsModel.getInstance().addObserver(this);
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
    public void showResults(List<Product> productIDs) {
        productGrid.getChildren().clear();

        int columns = 4;
        int counter = 0;

        for (Product ID : productIDs) {
            productGrid.add(ProductViewFactory.createProductView(ID, mainApp), counter % columns, counter / columns);
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


    @Override
    public void update(Observable o, Object arg) {
        showResults(((ProductsModel) o).getProducts());
    }
}
