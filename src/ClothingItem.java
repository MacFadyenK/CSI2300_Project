public abstract class ClothingItem extends Item {
    protected int size;
    protected String color;
    
 //returns the Clothing size
 public int getSize(){
    return this.size;
}

//sets the size of the clothes
public void setSize(int newSize){
    this.size = newSize;
}

//returns the color of the clothes
public String getColor() {
    return color;
}

public void setColor(String newColor){
    this.color = newColor;
}   
}
