public class Ring extends Jewelry {
    private int size;
    private static int number = 0;

    //ring constructor which sets the ring size, metal type, and the price
    //generates the individual item ID
    public Ring(String name, int size, String metalType, double price, String description){
        this.name = name;
        this.size = size;
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
        this.description = description;
    }

    //returns the ring size of the ring
    public int getSize(){
        return this.size;
    }

    //sets the size of the ring
    public void setSize(int newSize){
        this.size = newSize;
    }

    //sets the ID of the ring, all IDs are 3 digits
    //ring IDs start with a 1, and count up from 100 from each ring made
    private int setID(){
        return 100+number++;
    }
}
