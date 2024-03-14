import java.util.Random;

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
