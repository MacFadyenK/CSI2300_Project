package items.clothing;

import items.Item;

public abstract class ClothingItem extends Item {
    protected String color = "White";

    //returns the color of the clothes
    public String getColor() {
        return color;
    }

    //sets the color of the clothes
    public void setColor(String newColor){
        this.color = newColor;
    }   
}
