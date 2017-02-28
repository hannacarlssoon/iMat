package paymentView.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PaymentController implements Initializable {

    //The different views
    @FXML private AnchorPane deliveryView;
    @FXML private AnchorPane defaultPayAtDoor;
    @FXML private AnchorPane creditCardView;
    @FXML private AnchorPane reciptView;
    @FXML private AnchorPane backgroundView;
    @FXML private AnchorPane invoiceView;

    //The different buttons and choiceboxes
    @FXML private ChoiceBox yearChoiceBox;
    @FXML private ChoiceBox monthChoiceBox;
    @FXML private Button confirmation;
    @FXML private Button pay;
    @FXML private Button delivery;
    @FXML private Button recipt;
    @FXML private Button payAtDoorButton;
    @FXML private Button invoiceButton;
    @FXML private Button creditCardButton;
    @FXML private Button emailButton;
    @FXML private Button phoneButton;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private RadioButton fm;
    @FXML private RadioButton em;

    //The different images for the tabs
    @FXML private ImageView tabNumberOne;
    @FXML private ImageView tabNumberTwo;
    @FXML private ImageView tabNumberThree;
    @FXML private ImageView tabNumberFour;

    //The different images for the pay options
    @FXML private ImageView door;
    @FXML private ImageView invoice;
    @FXML private ImageView creditCard;

    //Boolean variables to keep track of which view is showing
    private boolean isOne;
    private boolean isTwo;
    private boolean isThree;
    private boolean isFour;
    private boolean isInvoice;
    private boolean isPayAtDoor;
    private boolean isCreditCard;

    //The images used to change the icons from grey to blue when selected and vice versa when not selected
    private Image oneBlue = new Image("file:resources/images/paymentImages/1blue.png");
    private Image oneGrey = new Image("file:resources/images/paymentImages/1 grey.png");
    private Image twoBlue = new Image("file:resources/images/paymentImages/2 blue.png");
    private Image twoGrey = new Image("file:resources/images/paymentImages/2 grey.png");
    private Image threeBlue = new Image("file:resources/images/paymentImages/3 blue.png");
    private Image threeGrey = new Image("file:resources/images/paymentImages/3 grey.png");
    private Image fourBlue = new Image("file:resources/images/paymentImages/4 blue.png");
    private Image fourGrey = new Image("file:resources/images/paymentImages/4 grey.png");
    private Image doorGrey = new Image("file:resources/images/paymentImages/door grey.png");
    private Image doorBlue = new Image("file:resources/images/paymentImages/door blue.png");
    private Image invoiceGrey = new Image("file:resources/images/paymentImages/invoice grey.png");
    private Image invoiceBlue = new Image("file:resources/images/paymentImages/invoice blue.png");
    private Image ccGrey = new Image("file:resources/images/paymentImages/cc grey.png");
    private Image ccBlue = new Image("file:resources/images/paymentImages/cc blue.png");

    //Lists of the values you can choose from when choosing a year and a month for your credit card
    ObservableList<Integer> year = FXCollections.observableArrayList(17, 18, 19, 20, 21, 22, 23);
    ObservableList<Integer> month = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    //A method to do something
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Makes sure you can only select one radiobutton at a time
        ToggleGroup group = new ToggleGroup();
        fm.setToggleGroup(group);
        em.setToggleGroup(group);
    }

    //Closes the application
    @FXML
    protected void closeAboutActionPerformed(ActionEvent event) throws IOException {

    }

    //Sets the start page for the paymentView wizard
    @FXML
    protected void setStartPage(ActionEvent event) throws IOException {
        backgroundView.toFront();
        isOne = true;
        isTwo = false;
        isThree = false;
        isFour = false;
        changeTabSelected();
    }

    //Sets the delivery adress view
    @FXML
    protected void setDeliveryView(ActionEvent event) throws IOException {
        deliveryView.toFront();
        isOne = false;
        isTwo = true;
        isThree = false;
        isFour = false;
        changeTabSelected();
    }

    //Sets the pay view to "pay at door"
    @FXML
    protected void setPayAtDoor(ActionEvent event) throws IOException {
        defaultPayAtDoor.toFront();
        isOne = false;
        isTwo = false;
        isThree = true;
        isFour = false;
        isCreditCard = false;
        isInvoice = false;
        isPayAtDoor = true;
        changePayMethodSelected();
        changeTabSelected();
        payAtDoorButton.getStyleClass().add("tab-selected");
        creditCardButton.getStyleClass().removeAll("tab-selected");
        invoiceButton.getStyleClass().removeAll("tab-selected");
    }

    //Sets the pay view to credit card
    @FXML
    protected void setCreditCardView(ActionEvent event) throws IOException {
        creditCardView.toFront();
        isOne = false;
        isTwo = false;
        isThree = true;
        isFour = false;
        isCreditCard = true;
        isInvoice = false;
        isPayAtDoor = false;
        changePayMethodSelected();
        payAtDoorButton.getStyleClass().removeAll("tab-selected");
        creditCardButton.getStyleClass().add("tab-selected");
        invoiceButton.getStyleClass().removeAll("tab-selected");
    }

    //Sets the pay view to invoice
    @FXML
    protected void setInvoiceView(ActionEvent event) {
        invoiceView.toFront();
        isOne = false;
        isTwo = false;
        isThree = true;
        isFour = false;
        isCreditCard = false;
        isInvoice = true;
        isPayAtDoor = false;
        changePayMethodSelected();
        payAtDoorButton.getStyleClass().removeAll("tab-selected");
        creditCardButton.getStyleClass().removeAll("tab-selected");
        invoiceButton.getStyleClass().add("tab-selected");
    }

    //Sets the recipt view
    @FXML
    protected void setReciptView(ActionEvent event) throws IOException {
        reciptView.toFront();
        isOne = false;
        isTwo = false;
        isThree = false;
        isFour = true;
        changeTabSelected();
    }

    @FXML
    protected void setEmailButton(ActionEvent event) {
        if (emailTextField.disableProperty().get() == false) {
            emailTextField.disableProperty().setValue(true);
            emailTextField.getStyleClass().add("text-field-disabled");
            emailButton.setText("Har email");
            emailButton.getStyleClass().add("noEmailPhone-selected");
        }
        else  {
            emailTextField.disableProperty().setValue(false);
            emailTextField.getStyleClass().removeAll("text-field-disabled");
            emailButton.setText("Ingen email");
            emailButton.getStyleClass().removeAll("noEmailPhone-selected");
        }
    }

    @FXML
    protected void setPhoneButton(ActionEvent event) {
        if (phoneTextField.disableProperty().get() == false) {
            phoneTextField.disableProperty().setValue(true);
            phoneTextField.getStyleClass().add("text-field-disabled");
            phoneButton.setText("Har telefonnummer");
            phoneButton.getStyleClass().add("noEmailPhone-selected");
        }
        else {
            phoneTextField.disableProperty().setValue(false);
            phoneTextField.getStyleClass().removeAll("text-field-disabled");
            phoneButton.setText("Inget telefonnummer");
            phoneButton.getStyleClass().removeAll("noEmailPhone-selected");
        }
    }



    //Initialize the year choicebox
    @FXML
    protected void initializeYear() {
        yearChoiceBox.setValue(17);
        yearChoiceBox.setItems(year);
    }

    //Initialize the month choicebox
    @FXML
    protected void initializeMonth() {
        monthChoiceBox.setValue(1);
        monthChoiceBox.setItems(month);
    }

    //Changes which tab is selected
    @FXML
    protected void changeTabSelected() {
        if (isOne) {
            tabOneSelected();
        }
        else if (isTwo) {
            tabTwoSelected();
        }
        else if (isThree) {
            tabThreeSelected();
        }
        else if (isFour) {
            tabFourSelected();
        }
    }

    //Changes the pay method view
    @FXML
    protected void changePayMethodSelected() {
        if (isPayAtDoor){
            door.setImage(doorBlue);
            invoice.setImage(invoiceGrey);
            creditCard.setImage(ccGrey);
            payAtDoorSelected();
        }
        else if (isInvoice) {
            door.setImage(doorGrey);
            invoice.setImage(invoiceBlue);
            creditCard.setImage(ccGrey);
            invoiceSelected();
        }
        else if (isCreditCard) {
            door.setImage(doorGrey);
            invoice.setImage(invoiceGrey);
            creditCard.setImage(ccBlue);
            creditCardSelected();
        }
    }

    //Sets tab one to selected
    protected void tabOneSelected() {
        tabNumberOne.setImage(oneBlue);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeGrey);
        tabNumberFour.setImage(fourGrey);
        confirmation.getStyleClass().add("activeTab");
        delivery.getStyleClass().removeAll("activeTab");
    }

    //Sets tab two to selected
    protected void tabTwoSelected() {
        tabNumberOne.setImage(oneGrey);
        tabNumberTwo.setImage(twoBlue);
        tabNumberThree.setImage(threeGrey);
        tabNumberFour.setImage(fourGrey);
        delivery.getStyleClass().add("activeTab");
        confirmation.getStyleClass().removeAll("activeTab");
        pay.getStyleClass().removeAll("activeTab");
    }

    //Sets tab three to selecteed
    protected void tabThreeSelected() {
        tabNumberOne.setImage(oneGrey);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeBlue);
        tabNumberFour.setImage(fourGrey);
        pay.getStyleClass().add("activeTab");
        confirmation.getStyleClass().removeAll("activeTab");
        delivery.getStyleClass().removeAll("activeTab");
    }

    //Sets tab four to selected
    protected void tabFourSelected() {
        tabNumberOne.setImage(oneGrey);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeGrey);
        tabNumberFour.setImage(fourBlue);
        recipt.getStyleClass().add("activeTab");
        pay.getStyleClass().removeAll("activeTab");
    }

    protected void payAtDoorSelected () {
        payAtDoorButton.getStyleClass().add("activePayAtDoor-button");
        creditCardButton.getStyleClass().removeAll("activeCreditCard-button");
        invoiceButton.getStyleClass().removeAll("activeInvoice-button");
    }

    protected void creditCardSelected () {
        payAtDoorButton.getStyleClass().removeAll("activePayAtDoor-button");
        creditCardButton.getStyleClass().add("activeCreditCard-button");
        invoiceButton.getStyleClass().removeAll("activeInvoice-button");
    }

    protected void invoiceSelected () {
        payAtDoorButton.getStyleClass().removeAll("activePayAtDoor-button");
        creditCardButton.getStyleClass().removeAll("activeCreditCard-button");
        invoiceButton.getStyleClass().add("activeInvoice-button");
    }
}