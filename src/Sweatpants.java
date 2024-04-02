public class Sweatpants extends ClothingItem {
    static private int number = 0;

    //constructor creates sweatpant object with specified size and price
    //sets the individual ID of the object
    public Sweatpants(String name, int size, double price, String color, String description, int quantity){
        this.name = name;
        this.size = size;
        this.price = price;
        this.ID = setID();
        this.color = color;
        this.description = description;
        this.quantity = quantity;
    }

    //sets the ID of the sweatpants, all IDs are 3 digits
    //sweatpant IDs start with a 9, and count up from 900 from each object made
    private int setID(){
        return 900+number++;
    }
}