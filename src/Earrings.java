public class Earrings extends Jewelry {
    private static int number;

    //constructor creates an earring pair with specified metal type and price
    //generates an individual ID for the earring pair
    public Earrings(String name, String metalType, double price, String description, int quantity){
        this.name = name;
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
        this.description = description; 
        this.quantity = quantity;
    }

    //sets the ID of the earrings, all IDs are 3 digits
    //earring IDs start with a 3, and count up from 300 from each earring pair made
    private int setID(){
        return 300+number++;
    }
}
