public class Sweatshirt extends ClothingItem {
    static private int number = 0;

    //constructor creates sweatshirt object with specified size and price
    //sets the individual ID of the object
    public Sweatshirt(String name, int size, double price, String color, String description){
        this.name = name;
        this.size = size;
        this.price = price;
        this.ID = setID();
        this.color = color;
        this.description = description;
    }
    //sets the ID of the sweatshirt, all IDs are 3 digits
    //sweatshirt IDs start with a 8, and count up from 800 from each object made
    private int setID(){
        return 800+number++;
    }
}