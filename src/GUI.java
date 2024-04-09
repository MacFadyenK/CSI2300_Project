import java.io.File;
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

    //pane to combine each element for the final display
    BorderPane border =  new BorderPane();

    //middle display box for the main item display area
    VBox middleDisplay = new VBox();

    //Discount display box on the left
    VBox discountDisplay = new VBox();

    //instantiate shopping cart
    ShoppingCart cart = new ShoppingCart();

    //Add shopping cart nodes so they can be used across whole class
    Label shoppingCart = new Label("Cart:");
    Button cartButton = new Button("0");

    //search filter options
    String[] categories = {"All","Jewelry", "Shoes", "Clothing"};

    //search label and search box creation
    Label searchLbl = new Label("Search:");
    ComboBox<String> searchBox = new ComboBox<>(FXCollections
    .observableArrayList(categories));
    //variable to keep track of which item screen is currently showing
    //0 for all, 1 for jewelry, 2 for shoes, and 3 for clothing
    int displayType = 0;



    //add image paths into the item objects
    public void setImages(){
        // Get the project directory
        String projectDirectory = System.getProperty("user.dir");

        //tshirt
        // Construct relative paths within the project folder
        String relativeFilePath = "images\\TShirt.jpg";
        // Construct the absolute path by concatenating project directory and relative path
        String absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        tshirt.addImage(absoluteFilePath);

        //sweatshirt
        relativeFilePath = "images\\Sweatshirt.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        sweatshirt.addImage(absoluteFilePath);

        //sweatpants
        relativeFilePath = "images\\Sweatpants.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        sweatpants.addImage(absoluteFilePath);

        //tennis shoes
        relativeFilePath = "images\\TennisShoes.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        tennisshoes.addImage(absoluteFilePath);

        //slides
        relativeFilePath = "images\\Slides.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        slides.addImage(absoluteFilePath);

        //dress shoes
        relativeFilePath = "images\\DressShoes.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        dressshoes.addImage(absoluteFilePath);

        //ring
        relativeFilePath = "images\\DiamondRing.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        ring.addImage(absoluteFilePath);

        //earrings
        relativeFilePath = "images\\HoopEarrings.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        earrings.addImage(absoluteFilePath);

        //bracelet
        relativeFilePath = "images\\Bracelet.jpg";
        absoluteFilePath = "file:" + projectDirectory + File.separator + relativeFilePath;
        bracelet.addImage(absoluteFilePath);
    }



    //pulls up cart window when the cart button is clicked
    public void handleCartButtonClick(){
        // Create a VBox to hold cart items
        VBox cartItemsBox = new VBox(10);

        //total cost label in the items box
        Label totalCostLabel = new Label("Total Cost: $"+ String.format("%.2f", cart.getTotalCost()));
        cartItemsBox.getChildren().add(totalCostLabel);

        // Add items from the cart to the VBox
        for (Item item : cart.getCart()) {
            HBox itemBox = new HBox(10);
            //item details displayed
            Label itemLabel = new Label(item.getName());
            Label priceLabel = new Label("$"+ String.format("%.2f", (item.getPrice()*item.getQuantity())));
            Label quantityLabel = new Label("Quantity: "+ item.getQuantity());
            Label sizeLabel = new Label("Size: " + item.getSize());
            Label typeLabel = new Label();
            //puts color/metal type as text in label
            if(item instanceof Jewelry){
                typeLabel.setText("Type: " + ((Jewelry) item).getMetalType());
            }else if( item instanceof ClothingItem){
                typeLabel.setText("Type: " + ((ClothingItem) item).getColor());
            }else{
                typeLabel.setText("Type: " + ((Shoes) item).getColor());
            }
            
            Button removeButton = new Button("Remove");
            
            //when remove button is clicked
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
                    cartButton.setText("" + cart.getNumItems());
                    cartItemsBox.getChildren().remove(itemBox);
                    totalCostLabel.setText("Total Cost: $"+ String.format("%.2f", cart.getTotalCost()));
                    reloadMainPage();
                }
            });
            itemBox.getChildren().addAll(itemLabel, priceLabel, quantityLabel, sizeLabel, typeLabel, removeButton);
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
                totalCostLabel.setText("Total Cost: $0.00");
                confirmationLabel.setText("Purchase confirmed. Cart cleared.");
                //shows how many items are in the cart
                cartButton.setText("" + cart.getNumItems());

                //repopulates the main screen display to reflect cart changes
                middleDisplay.getChildren().clear();
                //shuffles display order
                Collections.shuffle(itemsForSale);
                populateFullDisplay();

                //repopulate discount display
                discountDisplay.getChildren().clear();
                createDiscountDisplay();

                //sets the search bar to display all
                searchBox.getSelectionModel().select(0);
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



    //makes a discount display layout with the on sale item
    public void createDiscountDisplay(){
        discountDisplay.getChildren().add(new Label("Discount!"));
        //finds sale item from all possible items
        for(Item i : itemsForSale){
            if(i.onSale == true){
                //adds image of item
                if(i.getImage() != null){
                    try {  //tries to add image with link
                        ImageView imageView = new ImageView(new Image(i.getImage()));
                        discountDisplay.getChildren().add(imageView);
                        imageView.setFitHeight(75); 
                        imageView.setFitWidth(75);
                    } catch (IllegalArgumentException e) {  //if image link doesnt work
                       System.out.println("Image could not be loaded");
                    }
                }
                //adds name and price and percent discount
                discountDisplay.getChildren().addAll(new Label(i.getName()), 
                new Label("$"+String.format("%.2f", i.getPrice())),
                new Label(i.getPercent()+"% OFF"));
                
                //adds button to buy discount item
                Button buyDealButton = new Button("Buy Now!");

                //button to remove item from cart
                Button removeDealItem = new Button("Remove");

                //adds in a button whether item is in cart or not in cart
                if(!i.inCart){  //buy button if not in cart
                    discountDisplay.getChildren().add(buyDealButton);
                }else{  //remove button if in cart
                    discountDisplay.getChildren().add(removeDealItem);
                }
                //deal button being pressed
                buyDealButton.setOnAction(e -> {handleCartAddition(i);
                    //switches button type
                    discountDisplay.getChildren().remove(buyDealButton);
                    discountDisplay.getChildren().add(removeDealItem);
                });

                //remove button being pressed
                removeDealItem.setOnAction(e ->{handleCartRemoval(i);
                    //switches button type
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
    }



    //populates the main display with 5 regular price items
    public void populateFullDisplay(){
        int itemsAdded = 0;
        //adds 5 items to the display if they are not on sale
        for (int i = 0; i < itemsForSale.size() && itemsAdded < 5; i++) {
            if (!itemsForSale.get(i).onSale) {
                middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i)));
                itemsAdded++;
            }
        }
    }
    


    //sets the display to only jewelry items
    public void displayAllJewelry(){
        for(Item item : itemsForSale){
            if(item instanceof Jewelry){
                if(item.onSale){
                    border.setLeft(discountDisplay);
                } else{
                    middleDisplay.getChildren().add(new ItemDisplay(item));
                }
            }
        }
    }



    //displays on main screen all shoe items
    public void displayAllShoes(){
        for(Item item : itemsForSale){
            if(item instanceof Shoes){
                if(item.onSale){
                    border.setLeft(discountDisplay);
                } else{
                    middleDisplay.getChildren().add(new ItemDisplay(item));
                }
            }
        }
    }
    


    //displays on main screen all clothing items
    public void displayAllClothing(){
        for(Item item : itemsForSale){
            if(item instanceof ClothingItem){
                if(item.onSale){
                    border.setLeft(discountDisplay);
                } else{
                    middleDisplay.getChildren().add(new ItemDisplay(item));
                }
            }
        }
    }



    //reloads info currently being displayed on the main screen
    public void reloadMainPage(){
        //clears page
        middleDisplay.getChildren().clear();
        discountDisplay.getChildren().clear();
        createDiscountDisplay();

        //reloads items based on which display was currently showing
        switch(displayType) {
            //if displaying all items
            case 0:
                populateFullDisplay();
                border.setLeft(discountDisplay);
                break;
            //if displaying only jewelry
            case 1:
                displayAllJewelry();
                break;
            //if displaying only shoes
            case 2:
                displayAllShoes();
                break;
            //if displaying only clothing
            case 3:
                displayAllClothing();
                break;
        }
    }



    //run upon launch
    @Override
    public void start(Stage primaryStage){
        //discounts an item upon start
        createDiscountItem();

        //title of the stage
        primaryStage.setTitle("Shopaholic");

        //add items into the sale list
        addSaleItems();

        //set the image paths for each item
        setImages();

        //put 5 random items on display in middle display
        Collections.shuffle(itemsForSale);
        populateFullDisplay();

        //create discount display
        createDiscountDisplay();
        border.setLeft(discountDisplay);
        
        //Search Bar area
        HBox searchArea = new HBox(10);
        searchArea.getChildren().addAll(searchLbl, searchBox);
        BorderPane.setAlignment(searchLbl, Pos.CENTER);

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
                    displayType = 1;
                    displayAllJewelry();
                    break;
                //filtering for shoes
                case "Shoes":
                    displayType = 2;
                    displayAllShoes();
                    break;
                //filtering for clothing
                case "Clothing":
                    displayType = 3;
                    displayAllClothing();
                    break;
                default:
                    displayType = 0;
                    border.setLeft(discountDisplay);
                    //shuffles display order
                    Collections.shuffle(itemsForSale);
                    populateFullDisplay();
                    break;
            }
        });

        //Shopping cart area
        HBox cartArea = new HBox(10);
        cartArea.getChildren().addAll(shoppingCart, cartButton);

        //sets the action upon cart button click
        cartButton.setOnAction(e -> handleCartButtonClick());

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



    //inner class creates an object that displays an item and its cursory information
    public class ItemDisplay extends HBox{
        //itemDisplay constructor
        public ItemDisplay(Item item) {
            //adds the item image to the display
            if(item.getImage() != null){
                try { //tries to add image with image link
                    ImageView imageView = new ImageView(new Image(item.getImage()));
                    this.getChildren().add(imageView);
                    imageView.setFitHeight(60); 
                    imageView.setFitWidth(60);
                } catch (IllegalArgumentException e) {  //if image link doesnt work
                    System.out.println("Image could not be loaded");
                }
            }

            //VBox for text info: name, price, and description
            VBox text = new VBox();
            text.getChildren().add(new Label(item.getName()));
            text.getChildren().add(new Label("$"+String.format("%.2f", item.getPrice())));
            text.getChildren().add(new Label(item.getDescription()));

            //add text info to main display
            this.getChildren().add(text);

            //initialized an add and remove button object
            Button addBtn = new Button("Add to Cart");
            Button removeBtn = new Button("Remove");

            //whether item being displayed is within the cart or not
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
        //adds item to cart
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
        itemLayout.setHgap(15);

        //shows item image
        if(item.getImage() != null){
            try{  //tries to add image
                ImageView imageView = new ImageView(new Image(item.getImage()));
                itemLayout.add(imageView, 0, 0);
                imageView.setFitHeight(175); 
                imageView.setFitWidth(175);
            }catch(IllegalArgumentException e){  //if link to image does not work
                System.out.println("Image could not be loaded");
            }
        }
        //box for item details: name, price, description, quantity, size, and color/metal type
        VBox text = new VBox();
        text.getChildren().add(new Label(item.getName()));
        text.getChildren().add(new Label("$"+String.format("%.2f", item.getPrice())));
        text.getChildren().add(new Label(item.getDescription()));
        
        //label and textfield for desired quantity
        Label qtyLabel = new Label("Quantity:");
        TextField quantityField = new TextField(""+item.getQuantity());
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
        sizeBox.getSelectionModel().select(item.getSize());

        //color selector
        Label colorLabel = new Label("Color:");
        ComboBox<String> colorBox = new ComboBox<>(FXCollections
        .observableArrayList(colors));
        
        //metal type selector
        Label metalLabel = new Label("Metal:");
        ComboBox<String> metalTypeBox = new ComboBox<>(FXCollections
        .observableArrayList(metalTypes));

        //add each element ( quantity, size, and color/metal) to text area
        text.getChildren().addAll(qtyLabel, quantityField, sizeLabel, sizeBox);
        if(item instanceof Jewelry){
            text.getChildren().addAll(metalLabel, metalTypeBox);
            metalTypeBox.getSelectionModel().select(((Jewelry) item).getMetalType());  //sets dropdown value
        }else if(item instanceof ClothingItem){
            text.getChildren().addAll(colorLabel, colorBox);
            colorBox.getSelectionModel().select(((ClothingItem) item).getColor());  //sets dropdown value
        }else{
            text.getChildren().addAll(colorLabel, colorBox);
            colorBox.getSelectionModel().select(((Shoes) item).getColor());  //sets dropdown value
        }

        //adds text to middle section of the grid layout
        itemLayout.add(text, 1, 0);

        //add to cart button
        Button addToCart = new Button("Add to cart");

        //remove from cart button
        Button removeFromCart = new Button("Remove");

        //adds the correct button to the display
        if(!item.inCart){  //add button if item isnt in cart
            itemLayout.add(addToCart, 2, 0);
        }else{  //remove button if item is in cart
            itemLayout.add(removeFromCart, 2, 0);
        }

        //when clicking the add to cart button
        addToCart.setOnAction(e -> {
            //change buttons
            itemLayout.getChildren().remove(addToCart);
            itemLayout.add(removeFromCart, 2, 0);

            //sets the quantity of the item
            try{
                item.setQuantity(Integer.parseInt(quantityField.getText()));
            }catch(NumberFormatException ex){  //for blank textfield
                item.setQuantity(1);
            }

            //sets the color/metaltype of the item
            if(item instanceof ClothingItem){
                ((ClothingItem) item).setColor(colorBox.getValue());
            }else if (item instanceof Shoes){
                ((Shoes) item).setColor(colorBox.getValue());
            }else{
                ((Jewelry) item).setMetalType(metalTypeBox.getValue());
            }

            //sets the size
            item.setSize(sizeBox.getValue());

            handleCartAddition(item);

            //reload the main page
            reloadMainPage();
        });

        //when removing item from cart
        removeFromCart.setOnAction(e -> {handleCartRemoval(item);
            //change buttons
            itemLayout.getChildren().remove(removeFromCart);
            itemLayout.add(addToCart, 2, 0);
            //reload page
            reloadMainPage();
        });

        //make new stage and scene for the item details page
        Scene itemScene = new Scene(itemLayout, 600, 500);
        Stage itemStage = new Stage();
        itemStage.setTitle("Shopaholic: "+item.getName());
        itemStage.setScene(itemScene);
        itemStage.show();
    }



    //main method
    public static void main(String[] args) {
        launch(args);
    }
}
