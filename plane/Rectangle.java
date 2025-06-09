package plane;

public class Rectangle extends AbstractPlane {

    public Rectangle(String name){
        super(name);
    }
    public String getPlaneName(){
        return this.name;
    }
}
