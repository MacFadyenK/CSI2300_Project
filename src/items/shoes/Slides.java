package items.shoes;

public class Slides extends Shoes {
    static private int number = 0;

    //constructor creates slides object with specified shoe size and price
    //sets the individual ID of the object
    public Slides(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }
    
    //sets the ID of the slides, all IDs are 3 digits
    //slides IDs start with a 6, and count up from 600 from each slides object made
    private int setID(){
        return 600+number++;
    }
}
