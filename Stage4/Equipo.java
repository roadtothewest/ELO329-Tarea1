public class Equipo {
    public Equipo(String owner, float _x, float _y) {
        ownerName = owner;
        x=_x;
        y=_y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void move(float delta_x, float delta_y) {
        this.x += delta_x;
        this.y += delta_y;
    }

    public String getOwnerName() {
        return ownerName;
    }


    public boolean isWithinRange(Cellular cell) {
        double distance = Math.sqrt(Math.pow(this.x - cell.getX(), 2) + Math.pow(this.y - cell.getY(), 2));
        return distance <= TRACKING_RANGE;
    }

    //public String getHeader() {
      //..    
    //}
    //public String getState() {
      // ...
    //}
    protected  String ownerName;
    protected float x,y;
    boolean esTablet = false;
    private static final float TRACKING_RANGE = 10;

}
