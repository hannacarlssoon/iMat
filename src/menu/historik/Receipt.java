package menu.historik;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;


    public class Receipt extends ListCell<ShoppingItem> {

        @FXML
        private AnchorPane mainAnchorPane;
        @FXML private ImageView image;
        @FXML private Label title;
        @FXML private Label number;
        @FXML private Label price;

        private FXMLLoader fxmlLoader;
        private int i = 0;
        private ShoppingItem shoppingItem;

        @Override
        protected void updateItem(ShoppingItem shoppingItem, boolean empty) {
            super.updateItem(shoppingItem, empty);
            this.shoppingItem = shoppingItem;
            setGraphic(null);

            if (shoppingItem == null) return;


            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                }
                catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }



                    title.setText(shoppingItem.getProduct().getName());
                    number.setText(shoppingItem.getAmount() + "");
                    price.setText(shoppingItem.getProduct().getPrice() + "0 kr");
                    String name = shoppingItem.getProduct().getImageName();
                    image.setImage(new Image("file:resources/images/products/" + name));
                    setGraphic(mainAnchorPane);



                /*System.out.println(order.getItems().size());
                for (int i = 0; i < order.getItems().size(); i++) {
                    setGraphics(order.getItems().get(i));
                }*/

            updateI();
        }

        public Receipt() {

        }

        protected void updateI() {
            i = i+1;
        }

    }

