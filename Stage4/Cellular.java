public class Cellular extends Equipo {
    public void reportTabletLocation(Tablet tab) {  
        nube.updateLocation(tab.getOwnerName(), "tablet", this.x, this.y);
    }
    public Cellular(String owner, float _x, float _y, ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
    }
    Viewer view = new Viewer();
    public void comandoFindMy(){
        view.verFindMy(nube, ownerName);
    }
    public void reportTagLocation(EloTelTag tag) {  // it reports cellular location
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }
    private ETNube nube;
}
