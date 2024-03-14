public class Ring extends Jewelry {
    private int size;
    private static int number = 0;

    public Ring(int size,String metalType, double price){
        this.size = size;
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
    }
    public int getSize(){
        return this.size;
    }
    private int setID(){
        return 100+number++;
    }
}
