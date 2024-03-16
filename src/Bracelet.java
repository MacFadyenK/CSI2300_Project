public class Bracelet extends Jewelry {
    private static int number = 0;

    //constructor creates a bracelet item with the metal type and the price specified
    //generates an ID for the bracelet as well
    public Bracelet(String metalType, double price){
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
    }

    //sets the ID of the bracelet, all IDs are 3 digits
    //bracelet IDs start with a 2, and count up from 200 from each bracelet made
    private int setID(){
        return 200+number++;
    }
}
