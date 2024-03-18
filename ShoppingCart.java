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
    item.setSize(chooseSize(item));
    item.setColor(chooseColor(item));
    items.add(item);
    System.out.println("Item with ID " + item.getId() + " has been added to your cart.");
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
            System.out.println("- " + item.getName() + ", ID: " + item.getId());
            item.displayPhotocopy();
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Example items
        ClothingItem sweatshirt = new ClothingItem("Sweatshirt", 35.99, "Warm sweatshirt", "M", "Blue", 1);
        ClothingItem tshirt = new ClothingItem("T-Shirt", 15.99, "Casual t-shirt", "L", "Red", 2);
        ClothingItem sweatpants = new ClothingItem("Sweatpants", 29.99, "Comfortable sweatpants", "S", "Black", 3);

        Shoe dressShoes = new Shoe("Dress Shoes", 59.99, "Formal dress shoes", "9", "Black", 4);
        Shoe slides = new Shoe("Slides", 24.99, "Casual slides", "10", "Blue", 5);
        Shoe tennisShoes = new Shoe("Tennis Shoes", 49.99, "Athletic tennis shoes", "8", "White", 6);

        Jewelry ring = new Jewelry("Ring", 79.99, "Gold ring", "Size 7", "Gold", 7);
        Jewelry bracelet = new Jewelry("Bracelet", 49.99, "Silver bracelet", "One size", "Silver", 8);
        Jewelry earrings = new Jewelry("Earrings", 29.99, "Diamond earrings", "One size", "Diamond", 9);

        // Adding items to the cart
        cart.addItem(sweatshirt);
        cart.addItem(tshirt);
        cart.addItem(sweatpants);
        cart.addItem(dressShoes);
        cart.addItem(slides);
        cart.addItem(tennisShoes);
        cart.addItem(ring);
        cart.addItem(bracelet);
        cart.addItem(earrings);

        // Displaying the cart contents
        cart.displayCart();
    }
}

abstract class Item {
    private String name;
    private double price;
    private String description;
    private String size;
    private String color;
    private int id;

    public Item(String name, double price, String description, String size, String color, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }


    public void setColor(String chooseColor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setColor'");
    }


    public void setSize(String chooseSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSize'");
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    // Method to display a photocopy of the item
    public abstract void displayPhotocopy();
}

class ClothingItem extends Item {
    public ClothingItem(String name, double price, String description, String size, String color, int id) {
        super(name, price, description, size, color, id);
    }

    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getId() + ")]");
        // Additional display logic specific to clothing items can be added here
    }
}

class Shoe extends Item {
    public Shoe(String name, double price, String description, String size, String color, int id) {
        super(name, price, description, size, color, id);
    }

    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getId() + ")]");
        // Additional display logic specific to shoes can be added here
    }
}

class Jewelry extends Item {
    public Jewelry(String name, double price, String description, String size, String color, int id) {
        super(name, price, description, size, color, id);
    }

    @Override
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + getName() + " (ID: " + getId() + ")]");
        // Additional display logic specific to jewelry can be added here
    }
}
