package items.jewelry;

public class Ring extends Jewelry {
    private static int number = 0;

    //ring constructor which sets the ring size, metal type, and the price
    //generates the individual item ID
    public Ring(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }

    //sets the ID of the ring, all IDs are 3 digits
    //ring IDs start with a 1, and count up from 100 from each ring made
    private int setID(){
        return 100+number++;
    }
}
