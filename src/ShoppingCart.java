import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
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

    //gets the arraylist for all items in cart
    public List<Item> getCart(){
        return items;
    }

    //gets the total cost of the cart
    public double getTotalCost(){
        double totalCost = 0.00;
        for(Item i : items){
            totalCost += i.getQuantity() * i.getPrice();
        }
        return totalCost;
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
}