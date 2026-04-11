public class EloTelTag extends Equipo {
    public EloTelTag(String owner, String n, float _x, float _y) {
        super(owner, _x, _y);
        name=n;
    }
    public String getName(){
        return name;
    }
    //public String getHeader() {
      //...
    //}
    public boolean isWithinRange(Cellular cell) {
        double distance = Math.sqrt(Math.pow(this.x - cell.getX(), 2) + Math.pow(this.y - cell.getY(), 2));
        return distance <= TRACKING_RANGE;
    }
    private final String name;
    private static final float TRACKING_RANGE = 10;
}
