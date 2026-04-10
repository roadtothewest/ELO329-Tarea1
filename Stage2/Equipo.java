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

    //public String getHeader() {
      //..    
    //}
    //public String getState() {
      // ...
    //}
    protected final String ownerName;
    protected float x,y;
}
