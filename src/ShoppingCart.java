import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List<Item> items;
    private Scanner scanner;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

   // Method to add an item to the cart
   public void addItem(Item item) {
    //I think choose size and color should be separate from adding to cart for simplicity
    //item.setSize(chooseSize(item));
    //item.setColor(chooseColor(item));
    items.add(item);
    System.out.println("Item with ID " + item.getID() + " has been added to your cart.");
}

// Method to choose size for the item
private String chooseSize(Item item) {
    System.out.println("Enter size for " + item.getName() + ": ");
    return scanner.nextLine();
}

// Method to choose color for the item
private String chooseColor(Item item) {
    System.out.println("Enter color for " + item.getName() + ": ");
    return scanner.nextLine();
}


    // Method to display the items in the cart
    public void displayCart() {
        System.out.println("Items in your cart:");
        for (Item item : items) {
            System.out.println("- " + item.getName() + ", ID: " + item.getID());
            item.displayPhotocopy();
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Example items
        ClothingItem sweatshirt = new ClothingItem("Sweatshirt", 35.99, "Warm sweatshirt", "M", "Blue", 1);
        ClothingItem tshirt = new ClothingItem("T-Shirt", 15.99, "Casual t-shirt", "L", "Red", 2);
        ClothingItem sweatpants = new ClothingItem("Sweatpants", 29.99, "Comfortable sweatpants", "S", "Black", 3);
        /*
        Shoe dressShoes = new Shoe("Dress Shoes", 59.99, "Formal dress shoes", "9", "Black", 4);
        Shoe slides = new Shoe("Slides", 24.99, "Casual slides", "10", "Blue", 5);
        Shoe tennisShoes = new Shoe("Tennis Shoes", 49.99, "Athletic tennis shoes", "8", "White", 6);

        Jewelry ring = new Jewelry("Ring", 79.99, "Gold ring", "Size 7", "Gold", 7);
        Jewelry bracelet = new Jewelry("Bracelet", 49.99, "Silver bracelet", "One size", "Silver", 8);
        Jewelry earrings = new Jewelry("Earrings", 29.99, "Diamond earrings", "One size", "Diamond", 9);

        */
        // Adding items to the cart
        cart.addItem(sweatshirt);
        cart.addItem(tshirt);
        cart.addItem(sweatpants);
        /*
        cart.addItem(dressShoes);
        cart.addItem(slides);
        cart.addItem(tennisShoes);
        cart.addItem(ring);
        cart.addItem(bracelet);
        cart.addItem(earrings);
        */

        // Displaying the cart contents
        cart.displayCart();
    }
}


class ClothingItem extends Item {
    //this needs its own constructor because abstract classes like Item cannot have a constructor
    //also the clothing class needs sub classes for each of the 3 types of clothing
    public ClothingItem(String name, double price, String description, String size, String color, int id) {
        //super(name, price, description, size, color, id);   this doesnt work with an abstract super class
    }

    //No need for over ride
    /*
    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getID() + ")]");
        // Additional display logic specific to clothing items can be added here
    }
    */
}

/* These classes are unneeded because i already made them

class Shoe extends Item {
    public Shoe(String name, double price, String description, String size, String color, int id) {
        super(name, price, description, size, color, id);
    }

    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getID() + ")]");
        // Additional display logic specific to shoes can be added here
    }
}

class Jewelry extends Item {
    public Jewelry(String name, double price, String description, String size, String color, int id) {
        super(name, price, description, size, color, id);
    }

    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getID() + ")]");
        // Additional display logic specific to jewelry can be added here
    }
}
*/