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
    double totalCost = 0.0;

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
                Label itemNameLabel = new Label(item.getName());
                Label itemQuantityLabel = new Label("Quantity: " + item.getQuantity());
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
                        totalCost -= item.getPrice();
                        updateTotalCostLabel(cartItemsBox, totalCost);
                    }
                });
                itemBox.getChildren().addAll(itemNameLabel, itemQuantityLabel, removeButton);
                cartItemsBox.getChildren().add(itemBox);

                totalCost += item.getPrice();
            }

            // Display total cost of all items
            Label totalCostLabel = new Label("Total Cost: $" + totalCost);
            cartItemsBox.getChildren().add(totalCostLabel);

            // Create a button to buy all items
            Button buyAllButton = new Button("Buy All");
            buyAllButton.setOnAction(event -> {
                // Display confirmation message for purchase
                Alert buyAlert = new Alert(Alert.AlertType.CONFIRMATION);
                buyAlert.setTitle("Confirm Purchase");
                buyAlert.setHeaderText("Are you sure you want to buy all items?");
                buyAlert.setContentText("This action cannot be undone.");

                // Set buttons to "Yes" and "No"
                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                buyAlert.getButtonTypes().setAll(yesButton, noButton);

                // Wait for user's response
                Optional<ButtonType> result = buyAlert.showAndWait();
                if (result.isPresent() && result.get() == yesButton) {
                    // If user confirms, clear the cart and update the view
                    cartItems.clear();
                    cartItemsBox.getChildren().clear();
                    totalCostLabel.setText("Total Cost: $0.00");
                }
            });

            // Add the "Buy All" button to the cart view
            cartItemsBox.getChildren().add(buyAllButton);
        });

        // Add the components to a border pane
        BorderPane root = new BorderPane();
        root.setCenter(viewCartButton);
        root.setTop(cartItemsBox);
        BorderPane.setAlignment(cartItemsBox, Pos.CENTER);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the total cost label
    private void updateTotalCostLabel(VBox cartItemsBox, double totalCost) {
        for (int i = 0; i < cartItemsBox.getChildren().size(); i++) {
            if (cartItemsBox.getChildren().get(i) instanceof Label) {
                Label label = (Label) cartItemsBox.getChildren().get(i);
                if (label.getText().startsWith("Total Cost:")) {
                    label.setText("Total Cost: $" + String.format("%.2f", totalCost));
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

