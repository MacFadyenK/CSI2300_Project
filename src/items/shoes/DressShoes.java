package items.shoes;

public class DressShoes extends Shoes {
    static private int number = 0;

    //constructor of dress shoes which specifies the shoe size and price of the object
    //generates an individualized ID for the object
    public DressShoes(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }

    //sets the ID of the dress shoes, all IDs are 3 digits
    //dress shoe IDs start with a 5, and count up from 500 from each dress shoe object made
    private int setID(){
        return 500+number++;
    }
}
