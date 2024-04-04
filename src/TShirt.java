public class TShirt extends ClothingItem {
    static private int number = 0;

    //constructor creates tshirt object with specified tshirt size and price
    //sets the individual ID of the object
    public TShirt(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }

    //sets the ID of the tshirt, all IDs are 3 digits
    //tshirt IDs start with a 7, and count up from 700 from each object made
    private int setID(){
        return 700+number++;
    }
}