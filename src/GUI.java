import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

    ArrayList<Item> itemsForSale = new ArrayList<>();
    public void addAndShuffleSaleItems(){
        itemsForSale.add(tshirt);
        itemsForSale.add(sweatshirt);
        itemsForSale.add(sweatpants);
        itemsForSale.add(tennisshoes);
        itemsForSale.add(slides);
        itemsForSale.add(dressshoes);
        itemsForSale.add(ring);
        itemsForSale.add(earrings);
        itemsForSale.add(bracelet);
        Collections.shuffle(itemsForSale);
    }

    //chooses one item to be the item on sale
    public void createDiscountItem(){
        Random rand = new Random();
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
    

    @Override
    public void start(Stage primaryStage){
        //discounts an item upon start
        createDiscountItem();

        //title of the stage
        primaryStage.setTitle("Shopaholic");

        //initial Display area of items
        VBox middleDisplay = new VBox();

        //put 5 items on display
        addAndShuffleSaleItems();
        for(int i = 0; i<5; i++){
            middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i), false));
        }

        //Search Bar
        HBox searchArea = new HBox(10);

        //search filter options
        String[] categories = {"All","Jewelry", "Shoes", "Clothing"};

        Label searchLbl = new Label("Search:");
        ComboBox<String> searchBox = new ComboBox<>(FXCollections
        .observableArrayList(categories));

        searchBox.setOnAction(e -> {
            middleDisplay.getChildren().clear();
            String filter = searchBox.getValue();
            switch(filter){
                case "Jewelry":
                    for(Item item : itemsForSale){
                        if(item instanceof Jewelry){
                            middleDisplay.getChildren().add(new ItemDisplay(item, false));
                        }
                    }
                    break;
                case "Shoes":
                    for(Item item : itemsForSale){
                        if(item instanceof Shoes){
                            middleDisplay.getChildren().add(new ItemDisplay(item, false));
                        }
                    }
                    break;
                case "Clothing":
                    for(Item item : itemsForSale){
                        if(item instanceof ClothingItem){
                            middleDisplay.getChildren().add(new ItemDisplay(item, false));
                        }
                    }
                    break;
                default:
                    Collections.shuffle(itemsForSale);
                    for(int i = 0; i<5; i++){
                        middleDisplay.getChildren().add(new ItemDisplay(itemsForSale.get(i), false));
                    }
            }
        });

        searchArea.getChildren().addAll(searchLbl, searchBox);
        BorderPane.setAlignment(searchLbl, Pos.CENTER);

        //Shopping cart
        HBox cartArea = new HBox(10);

        Label shoppingCart = new Label("Cart:");
        Button cartButton = new Button();

        cartArea.getChildren().addAll(shoppingCart, cartButton);

        //border pane creates the top bar area for the search bar and shopping cart
        //the search bar is in the middle and the shopping cart is on the left
        BorderPane topBar = new BorderPane();
        topBar.setCenter(searchArea);
        topBar.setRight(cartArea);
        BorderPane.setAlignment(searchArea, Pos.TOP_CENTER);

        //Discount display box on the left
        VBox discountDisplay = new VBox();
        discountDisplay.getChildren().add(new Label("Discount!"));
        //finds sale item from all possible items
        for(Item e : itemsForSale){
            if(e.onSale == true){
                //adds image of item
                if(e.getImage() != null){
                    discountDisplay.getChildren().add(new ImageView(new Image(e.getImage())));
                }
                //adds name and price and percent discount
                discountDisplay.getChildren().addAll(new Label(e.getName()), 
                new Label("$"+String.format("%.2f", e.getPrice())),
                new Label(e.getPercent()+"% OFF"));
            } 
        }
        //border style for the discount display
        discountDisplay.setStyle("-fx-padding: 10;" + 
        "-fx-border-style: solid inside;" + 
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" + 
        "-fx-border-radius: 5;" + 
        "-fx-border-color: black;");

        //pane to combine each element for the final display
        BorderPane border =  new BorderPane();
        border.setTop(topBar);
        border.setCenter(middleDisplay);
        border.setLeft(discountDisplay);

        //scene which displays initially upon launch
        Scene mainScene = new Scene(border, 500, 500);

        //setting initial scene
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public class ItemDisplay extends HBox{
        public ItemDisplay(Item item, boolean inCart){
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

            //whethere item display is within the cart or not
            if(inCart == true){    //item display is in cart
                //adds remove from cart button
                Button removeBtn = new Button("Remove");
                this.getChildren().add(removeBtn);

            }else{   //item display is not within the cart
                //adds an add to cart button
                Button addBtn = new Button("Add to Cart");
                this.getChildren().add(addBtn);

                //action taken when button clicked to add to cart
                addBtn.setOnAction(e -> {

                });
            }

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

    //handles when an item display is clicked on and changes the window to show the item more detailed
    private void handleItemClicked(Item item) {
        GridPane itemLayout = new GridPane();

        if(item.getImage() != null){
            itemLayout.add(new ImageView(new Image(item.getImage())), 0, 0);
        }
        VBox text = new VBox();
        text.getChildren().add(new Label(item.getName()));
        text.getChildren().add(new Label("$"+String.format("%.2f", item.getPrice())));
        text.getChildren().add(new Label(item.getDescription()));

        Scene itemScene = new Scene(itemLayout, 500, 500);
        Stage itemStage = new Stage();
        itemStage.setTitle("Shopaholic."+item.getName());
        itemStage.setScene(itemScene);
        itemStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
