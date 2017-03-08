package shoppingView.listview.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
    private ComboBox<String> sortBox;
    @FXML
    private Label noResults;

    @FXML
    private void initialize() {
        productGrid = new GridPane();
        gridPaneContainer.getChildren().add(productGrid);
        ProductsModel.getInstance().addObserver(this);

        sortBox.getItems().clear();
        sortBox.getItems().add("Typ");
        sortBox.getItems().add("Pris");
        sortBox.getItems().add("A-Ö");
        sortBox.getSelectionModel().select(0);

        sortBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String sortBy = "";
                switch (sortBox.getValue()) {
                    case "Typ":
                        sortBy = "init";
                        break;
                    case "Pris":
                        sortBy = "price";
                        break;
                    case "A-Ö":
                        sortBy = "letter";
                        break;
                    default:
                        System.out.println("Fel i sorteringsboxen!");
                }

                System.out.println("SortBox");
                ProductsModel.getInstance().sortProducts(sortBy);

            }
        });

        noResults.textProperty().setValue("");
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
        noResults.textProperty().setValue("");
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

        if (productIDs.size() == 0) {
            noResults.textProperty().setValue("Din sökning gav tyvärr inga träffar. Försök gärna igen!");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        //Sets up all product views:
        ProductViewFactory.setUpAllProductViews(mainApp);
    }


    @Override
    public void update(Observable o, Object arg) {
        showResults(((ProductsModel) o).getProducts());
    }
}
