public class Cellular extends Equipo {
    public Cellular(String owner, float _x, float _y, ETNube nube) {
        super(owner, _x, _y);
        this.nube = nube;
    }

    public void reportTagLocation(EloTelTag tag) {  // it reports cellular location
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }
    private ETNube nube;
}
