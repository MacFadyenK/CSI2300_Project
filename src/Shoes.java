public abstract class Shoes extends Item {
    protected String color = "White";
    //returns the color of the shoes
    public String getColor() {
        return color;
    }

    //sets the color
    public void setColor(String newColor){
        this.color = newColor;
    }
}
