public class Earrings extends Jewelry {
    private static int number;

    public Earrings(String metalType, double price){
        this.metalType = metalType;
        this.price = price;
        this.ID = setID();
    }
    private int setID(){
        return 300+number++;
    }
}
