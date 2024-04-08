import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    //initial items for sale
    TShirt tshirt = new TShirt("T-Shirt", 20.00, "A basic t-shirt");
    Sweatshirt sweatshirt = new Sweatshirt("Sweatshirt", 30.00, "A cozy sweatshirt");
    Sweatpants sweatpants = new Sweatpants("Sweatpants", 30.00, "Casual pants");
    TennisShoes tennisshoes = new TennisShoes("Tennis Shoes", 65.00, "Pair of tennis shoes for everyday wear.");
    Slides slides = new Slides("Slides", 33.99, "Casual slides");
    DressShoes dressshoes = new DressShoes("Dress Shoes", 110.00, "Real leather dress shoes");
    Ring ring = new Ring("Diamond Ring", 3500.00, "1 carat diamond ring");
    Earrings earrings = new Earrings("Hoop earring", 49.99, "12mm diameter hoop earring set");
    Bracelet bracelet = new Bracelet("Gold Clasp Bracelet", 69.99, "Gold bracelet with an easy use clasp");
    //list for items
    ArrayList<Item> itemsForSale = new ArrayList<>();

    //middle display box for the main item display area
    VBox middleDisplay = new VBox();

    //instantiate shopping cart
    ShoppingCart cart = new ShoppingCart();

    //Add shopping cart nodes so they can be used across whole class
    Label shoppingCart = new Label("Cart:");
    Button cartButton = new Button("0");

    //pulls up cart window when the cart button is clicked
    public void handleCartButtonClick(){
        // Create a VBox to hold cart items
        VBox cartItemsBox = new VBox(10);

        // Add items from the cart to the VBox
        for (Item item : cart.getCart()) {
            HBox itemBox = new HBox(10);
            Label itemLabel = new Label(item.getName());
            Button removeButton = new Button("Remove");
            removeButton.setOnAction(event -> {
                // Display confirmation message for item removal
                Alert removeAlert = new Alert(Alert.AlertType.CONFIRMATION);
                removeAlert.setTitle("Confirm Item Removal");
                removeAlert.setHeaderText("Are you sure you want to remove this item?");
                removeAlert.setContentText("This action cannot be undone.");

                // Set buttons to "Yes" and "No"
                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                removeAlert.getButtonTypes().setAll(yesButton, noButton);

                // Wait for user's response
                Optional<ButtonType> result = removeAlert.showAndWait();
                if (result.isPresent() && result.get() == yesButton) {
                    // If user confirms, remove the item from the cart and update the view
                    cart.removeItem(item);
                    cartItemsBox.getChildren().remove(itemBox);
                }
            });
            itemBox.getChildren().addAll(itemLabel, removeButton);
            cartItemsBox.getChildren().add(itemBox);
        }

        // Create a label for confirmation message
        Label confirmationLabel = new Label("");
        confirmationLabel.setStyle("-fx-text-fill: green;");

        // Create a button to trigger the purchase confirmation
        Button buyAllButton = new Button("Buy All");
        buyAllButton.setOnAction(e -> {
            // Display confirmation message
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Purchase");
            alert.setHeaderText("Are you sure you want to buy all items?");
            alert.setContentText("This action cannot be undone.");

            // Set buttons to "Yes" and "No"
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);

            // Wait for user's response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                // Clear the cart and update the view
                cart.clearAll();
                cartItemsBox.getChildren().clear();
                confirmationLabel.setText("Purchase confirmed. Cart cleared.");
            }
            //shows how many items are in the cart
            cartButton.setText("" + cart.getNumItems());

            //repopulates the main screen display to reflect cart changes
            middleDisplay.getChildren().clear();
            for(int i = 0; i<5; i++){
                //does not display the item on sale
                if(itemsForSale.get(i).onSale){
                    i--;
                    continue;
                }
                middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i)));
            }
        });

        // Add the components to a border pane
        BorderPane root = new BorderPane();
        root.setCenter(cartItemsBox);
        root.setBottom(new HBox(10, buyAllButton, confirmationLabel));
        BorderPane.setAlignment(cartItemsBox, Pos.CENTER);
        BorderPane.setAlignment(buyAllButton, Pos.CENTER);
        BorderPane.setAlignment(confirmationLabel, Pos.CENTER);

         // Set up the scene and stage
         Scene scene = new Scene(root, 400, 300);
         Stage cartStage = new Stage();
         cartStage.setTitle("Shopping Cart Confirmation");
         cartStage.setScene(scene);
         cartStage.show();
    }

    //adds all items to the shopping cart
    public void addSaleItems(){
        itemsForSale.add(tshirt);
        itemsForSale.add(sweatshirt);
        itemsForSale.add(sweatpants);
        itemsForSale.add(tennisshoes);
        itemsForSale.add(slides);
        itemsForSale.add(dressshoes);
        itemsForSale.add(ring);
        itemsForSale.add(earrings);
        itemsForSale.add(bracelet);
    }

    //chooses one item to be the item on sale
    public void createDiscountItem(){
        Random rand = new Random();
        //creates a random multiple of 100 between 100 and 900, 
        int randomItem = rand.nextInt(1, 10) * 100;
        switch (randomItem){
            //IDs in 100s are rings
            case 100:
                ring.discount();
                break;
            //IDs in 200s are bracelets
            case 200:
                bracelet.discount();
                break;
            //IDs in 300s are earrings
            case 300:
                earrings.discount();
                break;
            //IDs in 400s are tennis shoes
            case 400:
                tennisshoes.discount();
                break;
            //IDs in 500s are dress shoes
            case 500:
                dressshoes.discount();
                break;
            //IDs in 600s are slides
            case 600:
                slides.discount();
                break;
            //IDs in 700s are tshirts
            case 700:
                tshirt.discount();
                break;
            //IDs in 800s are sweatshirts
            case 800:
                sweatshirt.discount();
                break;
            //IDs in 900s are sweatpants
            case 900:
                sweatpants.discount();
                break;
        }
    }

    public void populateFullDisplay(){
        Collections.shuffle(itemsForSale);
        for(int i = 0; i<5; i++){
            //does not display the item on sale
            if(itemsForSale.get(i).onSale){
                i--;
                continue;
            }
            middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i)));
        }
    }
    

    @Override
    public void start(Stage primaryStage){
        //discounts an item upon start
        createDiscountItem();

        //title of the stage
        primaryStage.setTitle("Shopaholic");

        //pane to combine each element for the final display
        BorderPane border =  new BorderPane();

        //add items into the sale list
        addSaleItems();

        //Display area of items

        //put 5 items on display in middle display
        populateFullDisplay();

        //Discount display box on the left
        VBox discountDisplay = new VBox();
        discountDisplay.getChildren().add(new Label("Discount!"));
        //finds sale item from all possible items
        for(Item i : itemsForSale){
            if(i.onSale == true){
                //adds image of item
                if(i.getImage() != null){
                    discountDisplay.getChildren().add(new ImageView(new Image(i.getImage())));
                }
                //adds name and price and percent discount
                discountDisplay.getChildren().addAll(new Label(i.getName()), 
                new Label("$"+String.format("%.2f", i.getPrice())),
                new Label(i.getPercent()+"% OFF"));
                
                //adds button to buy discount item
                Button buyDealButton = new Button("Buy Now!");
                discountDisplay.getChildren().add(buyDealButton);

                //button to remove item from cart
                Button removeDealItem = new Button("Remove");

                //deal button being pressed
                buyDealButton.setOnAction(e -> {handleCartAddition(i);
                    discountDisplay.getChildren().remove(buyDealButton);
                    discountDisplay.getChildren().add(removeDealItem);
                });

                //remove button being pressed
                removeDealItem.setOnAction(e ->{handleCartRemoval(i);
                    discountDisplay.getChildren().remove(removeDealItem);
                    discountDisplay.getChildren().add(buyDealButton);
                });

                //sets click on item handler
                discountDisplay.setOnMouseClicked(e -> handleItemClicked(i));
            }
        }
        //border style for the discount display
        discountDisplay.setStyle("-fx-padding: 10;" + 
        "-fx-border-style: solid inside;" + 
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" + 
        "-fx-border-radius: 5;" + 
        "-fx-border-color: black;");

        border.setLeft(discountDisplay);

        //Search Bar

        HBox searchArea = new HBox(10);

        //search filter options
        String[] categories = {"All","Jewelry", "Shoes", "Clothing"};

        Label searchLbl = new Label("Search:");
        ComboBox<String> searchBox = new ComboBox<>(FXCollections
        .observableArrayList(categories));

        //when an option is picked in the dropdown search box
        searchBox.setOnAction(e -> {
            //clear the middle display
            middleDisplay.getChildren().clear();
            border.getChildren().remove(discountDisplay);
            
            String filter = searchBox.getValue();

            //filters the items on display based on selected filter
            switch(filter){
                //filtering for Jewelry
                case "Jewelry":
                    for(Item item : itemsForSale){
                        if(item instanceof Jewelry){
                            if(item.onSale){
                                border.setLeft(discountDisplay);
                            } else{
                                middleDisplay.getChildren().add(new ItemDisplay(item));
                            }
                        }
                    }
                    break;
                //filtering for shoes
                case "Shoes":
                    for(Item item : itemsForSale){
                        if(item instanceof Shoes){
                            if(item.onSale){
                                border.setLeft(discountDisplay);
                            } else{
                                middleDisplay.getChildren().add(new ItemDisplay(item));
                            }
                        }
                    }
                    break;
                //filtering for clothing
                case "Clothing":
                    for(Item item : itemsForSale){
                        if(item instanceof ClothingItem){
                            if(item.onSale){
                                border.setLeft(discountDisplay);
                            } else{
                                middleDisplay.getChildren().add(new ItemDisplay(item));
                            }
                        }
                    }
                    break;
                default:
                    Collections.shuffle(itemsForSale);
                    for(int i = 0; i<5; i++){
                        middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i)));
                    }
                    border.setLeft(discountDisplay);
                    break;
            }
        });
        
        searchArea.getChildren().addAll(searchLbl, searchBox);
        BorderPane.setAlignment(searchLbl, Pos.CENTER);

        //Shopping cart

        HBox cartArea = new HBox(10);

        //sets the action upon cart button click
        cartButton.setOnAction(e -> handleCartButtonClick());

        cartArea.getChildren().addAll(shoppingCart, cartButton);

        //border pane creates the top bar area for the search bar and shopping cart
        //the search bar is in the middle and the shopping cart is on the left
        BorderPane topBar = new BorderPane();
        topBar.setCenter(searchArea);
        topBar.setRight(cartArea);
        BorderPane.setAlignment(searchArea, Pos.TOP_CENTER);

        border.setTop(topBar);
        border.setCenter(middleDisplay);

        //scene which displays initially upon launch
        Scene mainScene = new Scene(border, 500, 500);

        //setting initial scene
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public class ItemDisplay extends HBox{
        Item itemBeingDisplayed;
        public ItemDisplay(Item item){
            itemBeingDisplayed = item;
            //adds the item image to the display
            if(item.getImage() != null){
                this.getChildren().add(new ImageView(new Image(item.getImage())));
            }

            //VBox for text info
            VBox text = new VBox();
            text.getChildren().add(new Label(item.getName()));
            text.getChildren().add(new Label("$"+String.format("%.2f", item.getPrice())));
            text.getChildren().add(new Label(item.getDescription()));

            //add text info to main display
            this.getChildren().add(text);

            //initialized an add and remove button object
            Button addBtn = new Button("Add to Cart");
            Button removeBtn = new Button("Remove");

            //whethere item display is within the cart or not
            if(item.inCart == true){    //item display is in cart
                //adds remove from cart button
                this.getChildren().add(removeBtn);

            }else{   //item display is not within the cart
                //adds an add to cart button
                this.getChildren().add(addBtn);
            }

            //action taken when remove from cart button is clicked
            removeBtn.setOnAction(e -> {handleCartRemoval(item);
                this.getChildren().remove(removeBtn);
                this.getChildren().add(addBtn);
            });

            //action taken when button clicked to add to cart
            addBtn.setOnAction(e -> {handleCartAddition(item);
                this.getChildren().remove(addBtn);
                this.getChildren().add(removeBtn);
            });

            //when user clicks on the item display
            this.setOnMouseClicked(e -> handleItemClicked(item));

            //border style for the display
            this.setStyle("-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: black;");
        }
    }

    //handles when a button is pressed to add it to the cart.
    private void handleCartAddition(Item item){
        //if item is already in the cart when add button is pressed
        if(item.inCart == true){
            Label alrAddedText = new Label("Item has already been added to cart.");
            Scene alrAddedPopup = new Scene(alrAddedText, 300, 100);
            Stage popupStage = new Stage();
            popupStage.setTitle("Warning");
            popupStage.setScene(alrAddedPopup);
            popupStage.show();
            return;
        }

        //adds item if not in cart
        cart.addItem(item);
        //shows how many items are in the cart
        cartButton.setText("" + cart.getNumItems());
    }

    //handles when the remove button is pressed in the cart
    private void handleCartRemoval(Item item){
        cart.removeItem(item);
        cartButton.setText("" + cart.getNumItems());
    }

    //arrays for item detail dropdowns
    String[] metalTypes = {"Gold", "Silver", "Rose Gold", "Platinum", "Tungsten"};
    String[] colors = {"White", "Black", "Gray", "Red", "Pink", "Blue", "Green", "Yellow", "Orange"};
    String[] sizes = {"S", "M", "L", "XL"};

    //checks if string is an integer
    private boolean isValidInt(String text) {
        if (text == null || text.isEmpty()) {
            return true; // Allow empty input
        }

        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //handles when an item display is clicked on and changes the window to show the item more detailed
    private void handleItemClicked(Item item) {
        GridPane itemLayout = new GridPane();

        //shows item image
        if(item.getImage() != null){
            itemLayout.add(new ImageView(new Image(item.getImage())), 0, 0);
        }
        //box for item details
        VBox text = new VBox();
        text.getChildren().add(new Label(item.getName()));
        text.getChildren().add(new Label("$"+String.format("%.2f", item.getPrice())));
        text.getChildren().add(new Label(item.getDescription()));
        
        //quantity, size, and color dropdowns
        HBox dropdowns = new HBox(10);

        Label qtyLabel = new Label("Quantity:");
        TextField quantityField = new TextField("1");
        //only allows integer values to be input into text field
        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidInt(newValue)) {
                quantityField.setText(oldValue);
            }
        });

        //size selector
        Label sizeLabel = new Label("Size:");
        ComboBox<String> sizeBox = new ComboBox<>(FXCollections
        .observableArrayList(sizes));
        sizeBox.getSelectionModel().select(0);

        //color selector
        Label colorLabel = new Label("Color:");
        ComboBox<String> colorBox = new ComboBox<>(FXCollections
        .observableArrayList(colors));
        colorBox.getSelectionModel().select(0);

        //metal type selector
        Label metalLabel = new Label("Metal:");
        ComboBox<String> metalTypeBox = new ComboBox<>(FXCollections
        .observableArrayList(metalTypes));
        metalTypeBox.getSelectionModel().select(0);

        //add each element to dropdowns area
        dropdowns.getChildren().addAll(qtyLabel, quantityField, sizeLabel, sizeBox);
        if(item instanceof Jewelry){
            dropdowns.getChildren().addAll(metalLabel, metalTypeBox);
        }else{
            dropdowns.getChildren().addAll(colorLabel, colorBox);
        }

        //add dropdowns to text area
        text.getChildren().add(dropdowns);

        itemLayout.add(text, 1, 0);

        //add to cart button
        Button addToCart = new Button("Add to cart");

        //remove from cart button
        Button removeFromCart = new Button("Remove");
        if(!item.inCart){
            itemLayout.add(addToCart, 2, 0);
        }else{
            itemLayout.add(removeFromCart, 2, 0);
        }

        //when clicking the add to cart button
        addToCart.setOnAction(e -> {
            itemLayout.getChildren().remove(addToCart);
            itemLayout.add(removeFromCart, 2, 0);

            //sets the quantity of the item
            try{
                item.setQuantity(Integer.parseInt(quantityField.getText()));
            }catch(NumberFormatException ex){
                item.setQuantity(1);
            }
            System.out.println(item.getQuantity());

            //sets the color/metaltype of the item
            if(item instanceof ClothingItem){
                ((ClothingItem) item).setColor(colorBox.getValue());
                System.out.println(((ClothingItem) item).getColor());
            }else if (item instanceof Shoes){
                ((Shoes) item).setColor(colorBox.getValue());
                System.out.println(((Shoes) item).getColor());
            }else{
                ((Jewelry) item).setMetalType(metalTypeBox.getValue());
            }

            //sets the size
            item.setSize(sizeBox.getValue());

            handleCartAddition(item);
        });

        //when removing item from cart
        removeFromCart.setOnAction(e -> {handleCartRemoval(item);
            itemLayout.getChildren().remove(removeFromCart);
            itemLayout.add(addToCart, 2, 0);
        });

        //make new stage and scene for the item details page
        Scene itemScene = new Scene(itemLayout, 600, 500);
        Stage itemStage = new Stage();
        itemStage.setTitle("Shopaholic: "+item.getName());
        itemStage.setScene(itemScene);
        itemStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
