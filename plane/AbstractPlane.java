package plane;

abstract class AbstractPlane {
    protected  String name;

    AbstractPlane(String name){
        this.name = name;
    }

    abstract  String getPlaneName();
        
    public void display() {
        System.out.println("This plane is " + name);
    }
}
