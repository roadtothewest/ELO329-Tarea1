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
    
    private final String name;
    private static final float TRACKING_RANGE = 10;
}
