public class EloTelTag {
    private final String name;
    private final String ownerName;
    private float x, y;

    public EloTelTag(String owner, String n, float _x, float _y) {
        ownerName = owner;
        name = n;
        x = _x;
        y = _y;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void move(float delta_x, float delta_y) {
        this.x += delta_x;
        this.y += delta_y;
    }

    public String getHeader() {
        // Genera el encabezado con el formato: Dueño.equipo.x \t Dueño.equipo.y
        return ownerName + "." + name + ".x\t" + ownerName + "." + name + ".y";
    }

    public String getState() {
        // Retorna las coordenadas separadas por TAB
        return x + "\t" + y;
    }
}
