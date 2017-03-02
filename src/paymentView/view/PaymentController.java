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

import main.Main;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;

public class PaymentController implements Initializable {

    IMatDataHandler instance = IMatDataHandler.getInstance();
    CreditCard cardInfo = instance.getCreditCard();
    Customer customer = instance.getCustomer();
    Basket basket = Basket.getInstance();

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
    @FXML private ChoiceBox cardType;
    @FXML private CheckBox saveCreditCard;
    @FXML private TextField CCFirstName;
    @FXML private TextField CCLastName;
    @FXML private TextField cvc;
    @FXML private TextField cardNumber;
    @FXML private Button deliveryButton;
    @FXML private Button payButton;
    @FXML private CheckBox saveDelivery;
    @FXML private ListView listView;
    @FXML private ListView listViewEnd;
    @FXML private Label endPrice;
    @FXML private Label totalAmount;

    //The different text fields
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField address;
    @FXML private TextField city;
    @FXML private TextField cityCode;

    //The different images for the tabs
    @FXML private ImageView tabNumberOne;
    @FXML private ImageView tabNumberTwo;
    @FXML private ImageView tabNumberThree;
    @FXML private ImageView tabNumberFour;
    @FXML private ImageView iMat;
    @FXML private ImageView fmtt;
    @FXML private ImageView emtt;
    @FXML private ImageView emailtt;
    @FXML private ImageView phonett;
    @FXML private ImageView print;

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
    private Image info = new Image("file:resources/images/paymentImages/information.png");
    private Image logga = new Image("file:resources/images/iMat.png");
    private Image printer = new Image("file:resources/images/paymentImages/printer.png");

    private Tooltip tip = new Tooltip("Du måste fylla i all information innan du kan gå vidare");
    private Tooltip tip2 = new Tooltip("Du måste fylla i alla rader innan du kan betala och gå vidare");
    private Tooltip tip3 = new Tooltip("Du måste fylla i all information innan du kan spara");

    private Main main;


    //Lists of the values you can choose from when choosing a year and a month for your credit card
    ObservableList<Integer> year = FXCollections.observableArrayList(17, 18, 19, 20, 21, 22, 23);
    ObservableList<Integer> month = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    ObservableList<String> type = FXCollections.observableArrayList("Visa", "Mastercard");
    ObservableList<BasketItem> hej = FXCollections.observableArrayList(basket.getItems());

    //A method to do something
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listView.setCellFactory(hej -> new ShoppingCartItemsController());
        listViewEnd.setCellFactory(hej -> new ShoppingCartItemReciptController());

        //Makes sure you can only select one radiobutton at a time
        ToggleGroup group = new ToggleGroup();
        fm.setToggleGroup(group);
        em.setToggleGroup(group);

        cardType.setItems(type);

        //Sets the first view
        tabNumberOne.setImage(oneBlue);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeGrey);
        tabNumberFour.setImage(fourGrey);
        backgroundView.toFront();
        fmtt.setImage(info);
        emtt.setImage(info);
        emailtt.setImage(info);
        phonett.setImage(info);
        iMat.setImage(logga);
        print.setImage(printer);
        tip.getStyleClass().add("tooltip");
        tip2.getStyleClass().add("tooltip");
        tip3.getStyleClass().add("tooltip");


        //Sets the saved information
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        cityCode.setText(customer.getPostCode());
        city.setText(customer.getPostAddress());
        emailTextField.setText(customer.getEmail());
        phoneTextField.setText(customer.getPhoneNumber());
        cardNumber.setText(cardInfo.getCardNumber());
        cvc.setText(cardInfo.getVerificationCode()+"");
        String fn = "";
        String en = "";
        for (int i = 0; i < cardInfo.getHoldersName().length(); i++) {
            if (cardInfo.getHoldersName().length() < 3) {
                break;
            }
            while (cardInfo.getHoldersName().charAt(i) != ' ') {
                fn = fn + cardInfo.getHoldersName().charAt(i);
            }
            en = en + cardInfo.getHoldersName().charAt(i);
        }
        CCFirstName.setText(fn);

        updatePrice();
    }

    //Closes the application
    @FXML
    protected void closeAboutActionPerformed(ActionEvent event) throws IOException {

    }

    @FXML
    protected void disableDeliveryButton() {
        if (isFirstNameDone() && isCCLastNameDone() && isCityDone() && isAddressDone() && isEmailDone() && isCityCodeDone() && isPhoneDone()) {
            deliveryButton.setDisable(false);
        }
        else {
            deliveryButton.setDisable(true);
            deliveryButton.setTooltip(tip);
        }
    }

    @FXML
    protected void disablePayButton() {
        if (isCCFistNameDone() && isCCLastNameDone() && isCardNumberDone() && isCVCDone()) {
            payButton.setDisable(false);
        }
        else {
            payButton.setDisable(true);
            payButton.setTooltip(tip2);
        }
    }


    @FXML
    protected void saveCreditCard() {
        if (isCCFistNameDone() && isCCLastNameDone() && isCardNumberDone() && isCVCDone()) {
            saveCreditCard.setTooltip(null);
            saveCreditCard.setDisable(false);
            cardInfo.setCardNumber(cardNumber.getText());
            cardInfo.setCardType(cardType.getTypeSelector());
            cardInfo.setHoldersName(CCFirstName.getText() + " " + CCLastName.getText());
            cardInfo.setValidMonth((int) monthChoiceBox.getValue());
            cardInfo.setValidYear((int) yearChoiceBox.getValue());
            Integer x = Integer.valueOf(cvc.getText());
            int i = x;
            cardInfo.setVerificationCode(i);
        }
        else {
            saveCreditCard.setDisable(true);
            saveCreditCard.setTooltip(tip3);
        }
    }

    @FXML
    protected void saveDeliveryInfo() {
        if (isFirstNameDone() && isCCLastNameDone() && isCityDone() && isAddressDone() && isEmailDone() && isCityCodeDone() && isPhoneDone()) {
            saveDelivery.setTooltip(null);
            saveDelivery.setDisable(false);
            customer.setAddress(address.getText());
            customer.setEmail(emailTextField.getText());
            customer.setPhoneNumber(phoneTextField.getText());
            customer.setFirstName(firstName.getText());
            customer.setLastName(lastName.getText());
            customer.setPostCode(cityCode.getText());
            customer.setPostAddress(city.getText());
        }
        else {
            saveDelivery.setDisable(true);
            saveDelivery.setTooltip(tip3);
        }
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
        price();
        listViewEnd.setItems(basket.getItems());
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

    protected boolean isFirstNameDone() {
        if (firstName.getText() != null && !firstName.getText().isEmpty() && firstName.getText().matches("[A-Öa-ö]") && firstName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    protected boolean isLastNameDone() {
        if (lastName.getText() != null && !lastName.getText().isEmpty() && lastName.getText().matches("[A-Öa-ö]") && lastName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    protected boolean isAddressDone() {
        if (address.getText() != null && !address.getText().isEmpty() && address.getText().matches("[0-9]*[A-Öa-ö]") && address.getText().length() > 2) {
            CharSequence c = address.getCharacters();
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPhoneDone() {
        if (phoneTextField.isDisable()) {
            return true;
        }
        if (phoneTextField.getText() != null && !phoneTextField.getText().isEmpty() && phoneTextField.getText().matches("[0-9]*") && phoneTextField.getText().length() == 10) {
            return true;
        }
        return false;
    }

    protected boolean isEmailDone() {
        if (emailTextField.isDisable()) {
            return true;
        }
        if (emailTextField.getText() != null && !emailTextField.getText().isEmpty() && emailTextField.getText().length() > 4) {
            CharSequence c = emailTextField.getCharacters();
            int x = 0;
            int y = 0;
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) == '@') {
                    x++;
                }
                if (c.charAt(i) == '.') {
                    y++;
                }
            }
            if (x == 1 && y > 0) {
                return true;
            }
        }
        return false;
    }

    protected boolean isCityCodeDone() {
        if (cityCode.getText() != null && !cityCode.getText().isEmpty() && cityCode.getText().matches("[0-9]*") && cityCode.getText().length() == 5) {
            return true;
        }
        return false;
    }

    protected boolean isCityDone() {
        if (city.getText() != null && !city.getText().isEmpty() && city.getText().matches("[A-Öa-ö]") && city.getText().length() > 1) {
            return true;
        }
        return false;
    }

    protected boolean isCCFistNameDone() {
        if (CCFirstName.getText() != null && !CCFirstName.getText().isEmpty() && CCFirstName.getText().matches("[A-Öa-ö]") && CCFirstName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    protected boolean isCCLastNameDone() {
        if (CCLastName.getText() != null && !CCLastName.getText().isEmpty() && CCLastName.getText().matches("[A-Öa-ö]") && CCLastName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    protected boolean isCardNumberDone() {
        if (cardNumber.getText() != null && !cardNumber.getText().isEmpty() && cardNumber.getText().matches("[0-9]*") && cardNumber.getText().length() == 16) {
            return true;
        }
        return false;
    }

    protected boolean isCVCDone() {
        if (cvc.getText() != null && !cvc.getText().isEmpty() && cvc.getText().matches("[0-9]*") && cvc.getText().length() == 3) {
            return true;
        }
        return false;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    protected void returnToStore(){
        main.switchScene();
    }

    protected void updatePrice() {
        totalAmount.setText(basket.getTotalSumAsString());
    }

    protected void price() {
        endPrice.setText(basket.getTotalSumAsString());
    }
}