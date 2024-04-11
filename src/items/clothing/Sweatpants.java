package items.clothing;

public class Sweatpants extends ClothingItem {
    static private int number = 0;

    //constructor creates sweatpant object with specified size and price
    //sets the individual ID of the object
    public Sweatpants(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }

    //sets the ID of the sweatpants, all IDs are 3 digits
    //sweatpant IDs start with a 9, and count up from 900 from each object made
    private int setID(){
        return 900+number++;
    }
}