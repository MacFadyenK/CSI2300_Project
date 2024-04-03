import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    TShirt tshirt = new TShirt("T-Shirt", 20.00, "A basic t-shirt");

    @Override
    public void start(Stage primaryStage){
        //title of the stage
        primaryStage.setTitle("Shopaholic");

        //Search Bar
        HBox searchArea = new HBox(10);

        Label search = new Label("Search:");
        ComboBox searchBox = new ComboBox();

        searchArea.getChildren().addAll(search, searchBox);
        BorderPane.setAlignment(search, Pos.CENTER);

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

        //initial Display area
        VBox middleDisplay = new VBox();
        ItemDisplay shirt = new ItemDisplay(tshirt, false);
        middleDisplay.getChildren().add(shirt);

        //pane to combine each element for the final display
        BorderPane border =  new BorderPane();
        border.setTop(topBar);
        border.setCenter(middleDisplay);

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
            text.getChildren().add(new Label("$"+item.getPrice()));
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
            this.setOnMouseClicked(e -> {

            });
        }
    }
    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
