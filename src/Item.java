import java.util.Random;

public abstract class Item {
    protected int ID;
    protected double price;
    protected String image;

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

    //returns the ID of the item
    public int getID(){
        return this.ID;
    }
}
