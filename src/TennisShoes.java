public class TennisShoes extends Shoes {
    static private int number = 0;

    //constructor creates tennis shoes object with specified shoe size and price
    //sets the individual ID of the object
    public TennisShoes(String name, int size, double price, String color, String description){
        this.name = name;
        this.size = size;
        this.price = price;
        this.ID = setID();
        this.color = color;
        this.description = description;
    }
    //sets the ID of the tennis shoes, all IDs are 3 digits
    //tennis shoe IDs start with a 4, and count up from 400 from each tennis shoe object made
    private int setID(){
        return 400+number++;
    }
}
