public class Earrings extends Jewelry {
    private static int number;

    //constructor creates an earring pair with specified metal type and price
    //generates an individual ID for the earring pair
    public Earrings(String metalType, double price){
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
    }

    //sets the ID of the earrings, all IDs are 3 digits
    //earring IDs start with a 3, and count up from 300 from each earring pair made
    private int setID(){
        return 300+number++;
    }
}
