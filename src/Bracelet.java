public class Bracelet extends Jewelry {
    private static int number = 0;

    public Bracelet(String metalType, double price){
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
    }

    private int setID(){
        return 200+number++;
    }
}
