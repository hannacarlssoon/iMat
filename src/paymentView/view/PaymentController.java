package paymentView.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import main.Main;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import shoppingView.basket.model.Basket;
import shoppingView.basket.model.BasketItem;
import shoppingView.basket.view.BasketViewController;

public class PaymentController implements Initializable {

    //Initializes the classes needed to use to delegate methods
    private IMatDataHandler instance = IMatDataHandler.getInstance();
    private CreditCard cardInfo = instance.getCreditCard();
    private Customer customer = instance.getCustomer();
    private Basket basket = Basket.getInstance();
    private Main main;
    private BasketViewController basketViewController;

    //Checks if the text fields are properly filled out
    private ChangeListener<String> creditCardStatus = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (isCardNumberDone() && isCCLastNameDone() && isCCFistNameDone() && isCVCDone()) {
                payButton.toFront();
                payButton.setDisable(false);
                saveCreditCard.setDisable(false);
            }
            else {
                invisiblePayButton.toFront();
                payButton.setDisable(true);
                saveCreditCard.setDisable(true);
            }
        }
    };

    private ChangeListener<String> deliveryInfoStatus = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (isFirstNameDone() && isLastNameDone() && isAddressDone() && isCityCodeDone() && isCityDone() && isEmailDone() && isPhoneDone() && (em.isSelected() ||fm.isSelected()) && (datePicker.getValue() != null)) {
                deliveryButton.toFront();
                deliveryButton.setDisable(false);
                saveDelivery.setDisable(false);
            }
            else {
                invisibleDeliveryButton.toFront();
                deliveryButton.setDisable(true);
                saveDelivery.setDisable(true);
            }
        }
    };

    private ChangeListener<Boolean> deliveryFixFirstName = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isFirstNameDone()) {
                labelFirstName.setText("* Inkorrekt namn");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixLastName = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isLastNameDone()) {
                labelLastName.setText("* Inkorrekt namn");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixAddress = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isAddressDone()) {
                labelAddress.setText("* Inkorrekt Adress");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixCity = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCityDone()) {
                labelCity.setText("* Inkorrekt stad");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixCityCode = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCityCodeDone()) {
                labelCityCode.setText("* Inkorrekt postnummer");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixPhone = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isPhoneDone() && !phoneTextField.isDisable()) {
                System.out.println("In change");
                labelPhone.setText("* Inkorrekt telefonnummer");
            }
        }
    };
    private ChangeListener<Boolean> deliveryFixEmail = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isEmailDone() && !emailTextField.isDisable()) {
                labelEmail.setText("* Inkorrekt email");
            }
        }
    };
    private ChangeListener<String> deliveryFixed = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (isFirstNameDone()) {
                labelFirstName.setText("");
            }
            if (isLastNameDone()) {
                labelLastName.setText("");
            }
            if (isAddressDone()) {
                labelAddress.setText("");
            }
            if (isCityCodeDone()) {
                labelCityCode.setText("");
            }
            if (isCityDone()) {
                labelCity.setText("");
            }
            if (isPhoneDone()) {
                labelPhone.setText("");
            }
            if (isEmailDone()) {
                labelEmail.setText("");
            }
        }
    };
    private ChangeListener<String> creditCardFixed = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (isCCFistNameDone()) {
                labelCCFirstName.setText("");
            }
            if (isCCLastNameDone()) {
                labelCCLastName.setText("");
            }
            if (isCardNumberDone()) {
                labelCardNumber.setText("");
            }
            if (isCVCDone()) {
                labelCVC.setText("");
            }
        }
    };
    private ChangeListener<Boolean> creditCardFixFirstName = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCCFistNameDone()) {
                labelCCFirstName.setText("* Inkorrekt namn");
            }
        }
    };
    private ChangeListener<Boolean> creditCardFixLastName = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCCLastNameDone()) {
                labelCCLastName.setText("* Inkorrekt namn");
            }
        }
    };
    private ChangeListener<Boolean> creditCardFixCardNumber = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCardNumberDone()) {
                labelCardNumber.setText("* Inkorrekt kortnummer");
            }
        }
    };
    private ChangeListener<Boolean> creditCardFixCVC = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue && !isCVCDone()) {
                labelCVC.setText("* Inkorrekt CVC");
            }
        }
    };
    private ChangeListener<LocalDate> x = new ChangeListener<LocalDate>() {
        @Override
        public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
            if (datePicker.getValue() != null && (em.isSelected() || fm.isSelected()) && isFirstNameDone() && isLastNameDone() && isAddressDone() && isCityCodeDone() && isCityDone() && isPhoneDone() && isEmailDone()) {
                deliveryButton.setDisable(false);
                deliveryButton.toFront();
            }
        }
    };


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
    @FXML private RadioButton fm;
    @FXML private RadioButton em;
    @FXML private ChoiceBox cardType;
    @FXML private CheckBox saveCreditCard;
    @FXML private Button deliveryButton;
    @FXML private Button payButton;
    @FXML private CheckBox saveDelivery;
    @FXML private Label endPrice;
    @FXML private Label totalAmount;
    @FXML private Button invisiblePayButton;
    @FXML private Button invisibleDeliveryButton;
    @FXML private Label labelFirstName;
    @FXML private Label labelLastName;
    @FXML private Label labelCityCode;
    @FXML private Label labelAddress;
    @FXML private Label labelCity;
    @FXML private Label labelEmail;
    @FXML private Label labelPhone;
    @FXML private Label labelCardNumber;
    @FXML private Label labelCCFirstName;
    @FXML private Label labelCCLastName;
    @FXML private Label labelCVC;
    @FXML private Label calenderText;
    @FXML private Button confirmationButton;
    @FXML private Button toolTipButton;

    //The two different Listview to represent the confirmation basket and recipt
    @FXML private ListView<BasketItem> listView;
    @FXML private ListView<BasketItem> listViewEnd;

    //The different text fields
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField address;
    @FXML private TextField city;
    @FXML private TextField cityCode;
    @FXML private TextField CCFirstName;
    @FXML private TextField CCLastName;
    @FXML private TextField cvc;
    @FXML private TextField cardNumber;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private Pane pane;

    //The different images views
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
    @FXML private ImageView door;
    @FXML private ImageView invoice;
    @FXML private ImageView creditCard;
    @FXML private DatePicker datePicker;
    @FXML private ImageView calender;

    //Boolean variables to keep track of which view is showing
    private boolean isOne;
    private boolean isTwo;
    private boolean isThree;
    private boolean isFour;
    private boolean isInvoice;
    private boolean isPayAtDoor;
    private boolean isCreditCard;
    private boolean isDatePicker = true;
    private String email = "";
    private String phone = "";

    //The images used in the delivery view
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
    private Image logga = new Image("file:src/menu/logotype.png");
    private Image printer = new Image("file:resources/images/paymentImages/printer.png");
    private Image cal = new Image("file:resources/images/paymentImages/calendar.png");

    //Lists of the values you can choose from when choosing a year, a month and type of card for your credit card
    private ObservableList<Integer> year = FXCollections.observableArrayList(17, 18, 19, 20, 21, 22, 23);
    private ObservableList<Integer> month = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    private ObservableList<String> type = FXCollections.observableArrayList("Visa", "Mastercard");

    //List of the products in the basket
    private ObservableList<BasketItem> basketList;
    private ToggleGroup group;

    //The method that runs when the program is started
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Calls a method which makes sure you get the right list
        basket.setPaymentController(this);

        //Sets the choice boxes choices
        initializeCardType();
        initializeMonth();
        initializeYear();

        //Makes sure you can only select one radiobutton at a time
        group = new ToggleGroup();
        fm.setToggleGroup(group);
        em.setToggleGroup(group);

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
        totalAmount.setText(basket.getTotalSumAsString());
        calender.setImage(cal);

        //Sets the basketlist to the list cells
        listView.setCellFactory(basketList -> new ShoppingCartItemsController(this));
        listViewEnd.setCellFactory(basketList -> new ShoppingCartItemReciptController());

        //Sets the saved information
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        cityCode.setText(customer.getPostCode());
        city.setText(customer.getPostAddress());
        emailTextField.setText(customer.getEmail());
        if (emailTextField.getText().equals("")) {
            emailTextField.disableProperty().setValue(true);
            emailTextField.getStyleClass().add("text-field-disabled");
            emailButton.setText("Har email");
            emailButton.getStyleClass().add("noEmailPhone-selected");
        }
        phoneTextField.setText(customer.getPhoneNumber());
        if (phoneTextField.getText().equals("")) {
            phoneTextField.disableProperty().setValue(true);
            phoneTextField.getStyleClass().add("text-field-disabled");
            phoneButton.setText("Har telefonnummer");
            phoneButton.getStyleClass().add("noEmailPhone-selected");
        }
        cardNumber.setText(cardInfo.getCardNumber());
        yearChoiceBox.setValue(cardInfo.getValidYear());
        monthChoiceBox.setValue(cardInfo.getValidMonth());
        cardType.setValue(cardInfo.getCardType());
        String fn = "";
        String en = "";
        boolean keepTrack = true;
        for (int i = 0; i < cardInfo.getHoldersName().length(); i++) {
            if (cardInfo.getHoldersName().length() < 3) {
                break;
            }
            if (cardInfo.getHoldersName().charAt(i) != ' ' && keepTrack) {
                fn = fn + cardInfo.getHoldersName().charAt(i);
            }
            else {
                en = en + cardInfo.getHoldersName().charAt(i);
                keepTrack = false;
            }
        }
        CCFirstName.setText(fn);
        CCLastName.setText(en);

        //Calls the method which adds a listner to the text fields so it will notify the view when they are changed
        addsCreditCardListners();
        addsDeliveryInfoListners();
        addsDeliveryFixed();
        addsCreditCardFixed();
        firstName.focusedProperty().addListener(deliveryFixFirstName);
        lastName.focusedProperty().addListener(deliveryFixLastName);
        address.focusedProperty().addListener(deliveryFixAddress);
        city.focusedProperty().addListener(deliveryFixCity);
        cityCode.focusedProperty().addListener(deliveryFixCityCode);
        emailTextField.focusedProperty().addListener(deliveryFixEmail);
        phoneTextField.focusedProperty().addListener(deliveryFixPhone);
        CCFirstName.focusedProperty().addListener(creditCardFixFirstName);
        CCLastName.focusedProperty().addListener(creditCardFixLastName);
        cardNumber.focusedProperty().addListener(creditCardFixCardNumber);
        cvc.focusedProperty().addListener(creditCardFixCVC);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (group.getSelectedToggle() != null && isFirstNameDone() && isLastNameDone() && isAddressDone() && isCityCodeDone() && isCityDone() && (datePicker.getValue() != null)) {
                    deliveryButton.setDisable(false);
                    deliveryButton.toFront();
                }
            }
        });
        datePicker.valueProperty().addListener(x);
        emailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setEmailButton(event);
                if (emailTextField.isDisable()) {
                    labelEmail.setText("");
                }
                if (isFirstNameDone() && isLastNameDone() && isAddressDone() && isCityCodeDone() && isCityDone() && isEmailDone() && isPhoneDone() && (em.isSelected() ||fm.isSelected()) && (datePicker.getValue() != null)) {
                    deliveryButton.toFront();
                    deliveryButton.setDisable(false);
                    saveDelivery.setDisable(false);
                }
                else {
                    invisibleDeliveryButton.toFront();
                    deliveryButton.setDisable(true);
                    saveDelivery.setDisable(true);
                }
            }
        });
        phoneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPhoneButton(event);
                if (phoneTextField.isDisable()) {
                    labelPhone.setText("");
                }
                if (isFirstNameDone() && isLastNameDone() && isAddressDone() && isCityCodeDone() && isCityDone() && isEmailDone() && isPhoneDone() && (em.isSelected() ||fm.isSelected()) && (datePicker.getValue() != null)) {
                    deliveryButton.toFront();
                    deliveryButton.setDisable(false);
                    saveDelivery.setDisable(false);
                }
                else {
                    invisibleDeliveryButton.toFront();
                    deliveryButton.setDisable(true);
                    saveDelivery.setDisable(true);
                }
            }
        });
    }

    //Adds listners to the credit card fix labels
    protected void addsCreditCardFixed() {
        CCFirstName.textProperty().addListener(creditCardFixed);
        CCLastName.textProperty().addListener(creditCardFixed);
        cardNumber.textProperty().addListener(creditCardFixed);
        cvc.textProperty().addListener(creditCardFixed);
    }

    //Adds listeners to the credit card text fields
    protected void addsCreditCardListners() {
        CCFirstName.textProperty().addListener(creditCardStatus);
        CCLastName.textProperty().addListener(creditCardStatus);
        cvc.textProperty().addListener(creditCardStatus);
        cardNumber.textProperty().addListener(creditCardStatus);
        /*cardType.converterProperty().addListener(x);
        monthChoiceBox.converterProperty().addListener(x);
        yearChoiceBox.converterProperty().addListener(x); */

    }

    //Adds listners to the delivery fix labels
    protected void addsDeliveryFixed() {
        firstName.textProperty().addListener(deliveryFixed);
        lastName.textProperty().addListener(deliveryFixed);
        address.textProperty().addListener(deliveryFixed);
        city.textProperty().addListener(deliveryFixed);
        cityCode.textProperty().addListener(deliveryFixed);
        phoneTextField.textProperty().addListener(deliveryFixed);
        emailTextField.textProperty().addListener(deliveryFixed);
    }

    //Adds listeners to the delivery info text fields
    protected void addsDeliveryInfoListners() {
        lastName.textProperty().addListener(deliveryInfoStatus);
        firstName.textProperty().addListener(deliveryInfoStatus);
        address.textProperty().addListener(deliveryInfoStatus);
        city.textProperty().addListener(deliveryInfoStatus);
        cityCode.textProperty().addListener(deliveryInfoStatus);
        emailTextField.textProperty().addListener(deliveryInfoStatus);
        phoneTextField.textProperty().addListener(deliveryInfoStatus);
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

    //Closes the application
    @FXML
    protected void closeAboutActionPerformed(ActionEvent event) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                instance.shutDown();
            }
        }));
    }

    //Sets credit card to selected
    protected void creditCardSelected () {
        payAtDoorButton.getStyleClass().removeAll("activePayAtDoor-button");
        creditCardButton.getStyleClass().add("activeCreditCard-button");
        invoiceButton.getStyleClass().removeAll("activeInvoice-button");
        if (CCFirstName.getText() == "") {
            payButton.setDisable(true);
        }
    }

    //Initialize the card type choicebox
    protected void initializeCardType() {
        cardType.setValue("Visa");
        cardType.setItems(type);
    }

    //Initialize the month choicebox
    protected void initializeMonth() {
        monthChoiceBox.setValue(1);
        monthChoiceBox.setItems(month);
    }

    //Initialize the year choicebox
    protected void initializeYear() {
        yearChoiceBox.setValue(17);
        yearChoiceBox.setItems(year);
    }

    //Sets invoice to selected
    protected void invoiceSelected () {
        payAtDoorButton.getStyleClass().removeAll("activePayAtDoor-button");
        creditCardButton.getStyleClass().removeAll("activeCreditCard-button");
        invoiceButton.getStyleClass().add("activeInvoice-button");
    }

    //Checks if postcode is correct
    protected boolean isCityCodeDone() {
        if (cityCode.getText() != null && !cityCode.getText().isEmpty() && cityCode.getText().matches("[0-9]*") && cityCode.getText().length() == 5) {
            return true;
        }
        return false;
    }

    //Checks if address is correct
    protected boolean isAddressDone() {
        if (address.getText() != null && !address.getText().isEmpty() && address.getText().length() > 2) {
            CharSequence c = address.getCharacters();
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    //Checks if city is correct
    protected boolean isCityDone() {
        if (city.getText() != null && !city.getText().isEmpty() && city.getText().length() > 1) {
            return true;
        }
        return false;
    }

    //Checks if cvc is correct
    protected boolean isCVCDone() {
        if (cvc.getText() != null && !cvc.getText().isEmpty() && cvc.getText().matches("[0-9]*") && cvc.getText().length() == 3) {
            return true;
        }
        return false;
    }

    //Checks if credit card first name is correct
    protected boolean isCCFistNameDone() {
        if (CCFirstName.getText() != null && !CCFirstName.getText().isEmpty() && CCFirstName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    //Checks if credit card last name is correct
    protected boolean isCCLastNameDone() {
        if (CCLastName.getText() != null && !CCLastName.getText().isEmpty() && CCLastName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    //Checks if card number is correct
    protected boolean isCardNumberDone() {
        if (cardNumber.getText() != null && !cardNumber.getText().isEmpty() && cardNumber.getText().matches("[0-9]*") && cardNumber.getText().length() == 16) {
            return true;

        }
        return false;
    }

    //Checks if email is correct
    protected boolean isEmailDone() {
        if (emailTextField.isDisable()) {
            return true;
        }
        if (emailTextField.getText() != null && !emailTextField.getText().isEmpty() && emailTextField.getText().length() > 4) {
            CharSequence c = emailTextField.getCharacters();
            int x = 0;
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) == '@') {
                    return true;
                }
            }

        }
        return false;
    }

    //Checks if first name is correct
    protected boolean isFirstNameDone() {
        if (firstName.getText() != null && !firstName.getText().isEmpty() && firstName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    //Checks if last name is correct
    protected boolean isLastNameDone() {
        if (lastName.getText() != null && !lastName.getText().isEmpty() && lastName.getText().length() > 1) {
            return true;
        }
        return false;
    }

    //Checks if phone number is correct
    protected boolean isPhoneDone() {
        if (phoneTextField.isDisable()) {
            return true;
        }
        if (phoneTextField.getText() != null && !phoneTextField.getText().isEmpty() && phoneTextField.getText().matches("[0-9]*") && phoneTextField.getText().length() == 10) {
            return true;
        }
        return false;
    }

    //Sets "pay at door" selected
    protected void payAtDoorSelected () {
        payAtDoorButton.getStyleClass().add("activePayAtDoor-button");
        creditCardButton.getStyleClass().removeAll("activeCreditCard-button");
        invoiceButton.getStyleClass().removeAll("activeInvoice-button");
    }

    //Sets total sum of the cart
    protected void price() {
        endPrice.setText(basket.getTotalSumAsString());
    }

    //Sets the shopping view scene
    @FXML
    protected void returnToStore(){
        main.switchScene();
    }

    @FXML
    protected void returnToStoreAfterPurchase(){
        basket.clearBasket();
        backgroundView.toFront();
        tabOneSelected();
        main.switchScene();
    }

    @FXML
    protected void returnToStoreHome(){
        main.setHomeDisabled(false);
        main.switchScene();
    }

    //Saves the creditcard information
    protected void saveCreditCard() {
        if (isCCFistNameDone() && isCCLastNameDone() && isCardNumberDone()) {
            cardInfo.setCardNumber(cardNumber.getText());
            cardInfo.setCardType(cardType.getValue().toString());
            cardInfo.setHoldersName(CCFirstName.getText() + " " + CCLastName.getText());
            cardInfo.setValidMonth((int) monthChoiceBox.getValue());
            cardInfo.setValidYear((int) yearChoiceBox.getValue());
        }
    }

    //Saves the delivery information
    protected void saveDeliveryInfo() {
        if (isEmailDone() && isLastNameDone() && isCityDone() && isPhoneDone() && isCityCodeDone() && isAddressDone() && isFirstNameDone()) {
            customer.setAddress(address.getText());
            customer.setEmail(emailTextField.getText());
            customer.setPhoneNumber(phoneTextField.getText());
            customer.setFirstName(firstName.getText());
            customer.setLastName(lastName.getText());
            customer.setPostCode(cityCode.getText());
            customer.setPostAddress(city.getText());
        }

    }

    //Sets the pay view to credit card
    @FXML
    protected void setCreditCardView(ActionEvent event) throws IOException {
        payButton.setDisable(true);
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

    //Sets the delivery adress view
    @FXML
    protected void setDeliveryView(ActionEvent event) throws IOException {
        deliveryButton.setDisable(true);
        deliveryView.toFront();
        isOne = false;
        isTwo = true;
        isThree = false;
        isFour = false;
        changeTabSelected();
    }

    //Sets the email text field to disabled/not disabled when clicked
    @FXML
    protected void setEmailButton(ActionEvent event) {

        if (emailTextField.disableProperty().get() == false) {
            emailTextField.disableProperty().setValue(true);
            emailTextField.getStyleClass().add("text-field-disabled");
            email = emailTextField.getText();
            emailTextField.clear();
            emailButton.setText("Har email");
            emailButton.getStyleClass().add("noEmailPhone-selected");
            addsDeliveryInfoListners();
        }
        else  {
            emailTextField.disableProperty().setValue(false);
            emailTextField.getStyleClass().removeAll("text-field-disabled");
            emailButton.setText("Ingen email");
            emailTextField.setText(email);
            emailButton.getStyleClass().removeAll("noEmailPhone-selected");
            addsDeliveryInfoListners();
        }
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

    //Sets main to main
    public void setMain(Main main) {
        this.main = main;
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
        if (saveDelivery.isSelected()) {
            saveDeliveryInfo();
        }
    }

    //Sets the phone text field to disabled/not disabled when clicked
    @FXML
    protected void setPhoneButton(ActionEvent event) {
        if (phoneTextField.disableProperty().get() == false) {
            phoneTextField.disableProperty().setValue(true);
            phoneTextField.getStyleClass().add("text-field-disabled");
            phone = phoneTextField.getText();
            phoneTextField.clear();
            phoneButton.setText("Har telefonnummer");
            phoneButton.getStyleClass().add("noEmailPhone-selected");
            addsDeliveryInfoListners();
        }
        else {
            phoneTextField.disableProperty().setValue(false);
            phoneTextField.getStyleClass().removeAll("text-field-disabled");
            phoneButton.setText("Inget telefonnummer");
            phoneTextField.setText(phone);
            phoneButton.getStyleClass().removeAll("noEmailPhone-selected");
            addsDeliveryInfoListners();
        }
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
        if (saveCreditCard.isSelected()) {
            saveCreditCard();
        }

        basket.addBasketToFavorites();
        basket.putBasketItemsInShoppingCart();

        instance.placeOrder();
    }

    //Sets the start page for the paymentView wizard
    @FXML
    public void setStartPage(ActionEvent event) throws IOException {
        backgroundView.toFront();
        isOne = true;
        isTwo = false;
        isThree = false;
        isFour = false;
        changeTabSelected();
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

    //Sets tab one to selected
    protected void tabOneSelected() {
        tabNumberOne.setImage(oneBlue);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeGrey);
        tabNumberFour.setImage(fourGrey);
        confirmation.getStyleClass().add("activeTab");
        delivery.getStyleClass().removeAll("activeTab");
        recipt.getStyleClass().removeAll("activeTab");
    }

    //Sets tab three to selected
    protected void tabThreeSelected() {
        tabNumberOne.setImage(oneGrey);
        tabNumberTwo.setImage(twoGrey);
        tabNumberThree.setImage(threeBlue);
        tabNumberFour.setImage(fourGrey);
        pay.getStyleClass().add("activeTab");
        confirmation.getStyleClass().removeAll("activeTab");
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

    //Sets the basket list so you know which products have been added to the cart
    public void updateBasket(){
        basketList = basket.getItems();
        listView.setItems(basketList);
        listViewEnd.setItems(basketList);
        totalAmount.setText(basket.getTotalSumAsString());
        if (getBasketList().size() < 1) {
            confirmationButton.setDisable(true);
            toolTipButton.toFront();
        }
        else {
            confirmationButton.setDisable(false);
            confirmationButton.toFront();
        }
    }

    //Returns the basket list
    public ObservableList<BasketItem> getBasketList() {
        return basketList;
    }

    @FXML
    protected void setCalender(ActionEvent event) {
        datePicker.requestFocus();
        datePicker.show();
    }

    @FXML
    protected void calenderButtonClicked(ActionEvent event) {
        setCalender(event);
        setPane();
    }

    protected void setPane() {
        pane.toBack();
        calenderText.setText("");
    }

}