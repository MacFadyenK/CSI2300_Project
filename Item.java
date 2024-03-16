import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to the cart
    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " has been added to your cart.");
    }

    // Method to display the items in the cart
    public void displayCart() {
        System.out.println("Items in your cart:");
        for (Item item : items) {
            item.displayItem();
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        // Example items
        ClothingItem clothingItem = new ClothingItem("Shirt", 25.99, "Casual shirt", "M", "Blue");
        Shoe shoe = new Shoe("Sneakers", 49.99, "Running shoes", "US 10", "Black");
        Jewelry jewelry = new Jewelry("Necklace", 79.99, "Gold necklace", "", "");

        // Adding items to the cart
        cart.addItem(clothingItem);
        cart.addItem(shoe);
        cart.addItem(jewelry);

        // Displaying the cart contents
        cart.displayCart();

        scanner.close();
    }
}

public abstract class Item {
    private int ID;
    private double price;

    //yet to be implemented
    public void addImage(String imagePath){

    }

    //generates a discount on an item from 5% to 50%
    //equal likelihood for all discounts
    public void discount(){
        //gets random discount from 5-50%
        double percent = new Random().nextInt(5, 50);
        this.price = price - (percent*price)/100.0;
    }

    //returns the price of the item
    public double getPrice(){
        return this.price;
    }

    //returns the ID of the item
    public int getID(){
        return this.ID;
    }
}
