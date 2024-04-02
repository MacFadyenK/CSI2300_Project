public class TShirt extends ClothingItem {
    static private int number = 0;

    //constructor creates tshirt object with specified tshirt size and price
    //sets the individual ID of the object
    public TShirt(String name, int size, double price, String color, String description, int quantity){
        this.name = name;
        this.size = size;
        this.price = price;
        this.ID = setID();
        this.color = color;
        this.description = description;
        this.quantity = quantity;
    }

    //sets the ID of the tshirt, all IDs are 3 digits
    //tshirt IDs start with a 7, and count up from 700 from each  object made
    private int setID(){
        return 700+number++;
    }
}