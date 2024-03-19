import java.util.Random;

public abstract class Item {
    protected int ID;
    protected double price;
    protected String image;
    protected String description;
    protected String name;

    //adds an image path to the item for a representative image
    public void addImage(String imagePath){
        this.image = imagePath;
    }

    //returns the string for the image path
    public String getImage(){
        return this.image;
    }

    //generates a discount on an item from 5% to 50%
    //equal likelihood for all discounts
    public void discount(){
        //gets random discount from 5-50%
        int percent = new Random().nextInt(5, 50);
        this.price = price - (percent*price)/100.0;
    }

    //returns the price of the item
    public double getPrice(){
        return this.price;
    }

    //returns the name of the object
    public String getName() {
        return name;
    }

    //returns a description of the product
    public String getDescription() {
        return this.description;
    }

    //returns the ID of the item
    public int getID(){
        return this.ID;
    }

    //displays a copy of important information of the item
    public void displayPhotocopy() {
        System.out.println("[Photocopy of " + this.getName() + " (ID: " + this.getID() + ")]");
        // Additional display logic specific to clothing items can be added here
    }
}
