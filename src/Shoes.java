public abstract class Shoes extends Item {
    protected int size;
    protected String color;

    //returns the shoe size
    public int getSize(){
        return this.size;
    }

    //sets the size of the shoes
    public void setSize(int newSize){
        this.size = newSize;
    }

    //returns the color of the shoes
    public String getColor() {
        return color;
    }
    
    public void setColor(String newColor){
        this.color = newColor;
    }
}
