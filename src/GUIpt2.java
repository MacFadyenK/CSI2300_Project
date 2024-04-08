import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIpt2 extends Application {
    // ArrayList to store items in the cart
    private ArrayList<Item> cartItems = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox to hold cart items
        VBox cartItemsBox = new VBox(10);

        // Create a button to view the cart
        Button viewCartButton = new Button("View Cart");
        viewCartButton.setOnAction(e -> {
            // Clear previous cart items
            cartItemsBox.getChildren().clear();

            // Add items from the cart to the VBox
            for (Item item : cartItems) {
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
                        cartItems.remove(item);
                        cartItemsBox.getChildren().remove(itemBox);
                    }
                });
                itemBox.getChildren().addAll(itemLabel, removeButton);
                cartItemsBox.getChildren().add(itemBox);
            }
        });

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
                cartItems.clear();
                cartItemsBox.getChildren().clear();
                confirmationLabel.setText("Purchase confirmed. Cart cleared.");
            }
        });

        // Add the components to a border pane
        BorderPane root = new BorderPane();
        root.setCenter(viewCartButton);
        root.setTop(cartItemsBox);
        root.setBottom(new HBox(10, buyAllButton, confirmationLabel));
        BorderPane.setAlignment(cartItemsBox, Pos.CENTER);
        BorderPane.setAlignment(buyAllButton, Pos.CENTER);
        BorderPane.setAlignment(confirmationLabel, Pos.CENTER);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Shopping Cart Confirmation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
