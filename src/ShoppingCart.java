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
    items.add(item);
    item.inCart = true;
    System.out.println("Item with ID " + item.getID() + " has been added to your cart.");
    }

    //Method counts the number of items in the cart
    public int getNumItems(){
        int count = 0;
        for(Item i : items){
            count += 1*i.getQuantity();
        }
        return count;
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

    //gets the arraylist for all items in cart
    public List<Item> getCart(){
        return items;
    }


    // Method to display the items in the cart
    public void displayCart() {
        System.out.println("Items in your cart:");
        for (Item item : items) {
            System.out.println("- " + item.getName() + ", ID: " + item.getID());
            item.displayPhotocopy();
        }
    }
    
   //Method for removing items from cart
    public void removeItem(Item item) {
        items.remove(item);
        item.inCart = false;
        System.out.println("Item with ID " + item.getID() + " has been removed from your cart.");
            return;
    }

    //clear all items in cart
    public void clearAll(){
        for(Item i : items){
            i.inCart = false;
        }
        items.clear();
    }
  

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();


        // Displaying the cart contents
        cart.displayCart();
    }
}