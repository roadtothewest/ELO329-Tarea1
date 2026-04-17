public class Tablet extends Equipo {
    public Tablet(String owner, float _x, float _y , ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
        esTablet = true;
    }
    Viewer view = new Viewer();
ETNube nube;

    public void comandoFindMy(){

  view.verFindMy(nube, ownerName);

}
   
    public boolean isWithinRange(Cellular cell) {
        double distance = Math.sqrt(Math.pow(this.x - cell.getX(), 2) + Math.pow(this.y - cell.getY(), 2));
        return distance <= TRACKING_RANGE;
    }
    private static final float TRACKING_RANGE = 10;
}
