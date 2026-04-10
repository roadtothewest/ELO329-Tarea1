import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {  // Piece of land where cellulars, tags, and tablets are located and moved.
    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }
    public void forEachTagTryToReportLocation() {
        for (EloTelTag tag : tags) {
            Cellular cell = findNearByCellular(tag);
            if (cell != null) {
                cell.reportTagLocation(tag);
            }
        }
    }
    private Cellular findNearByCellular(EloTelTag tag) {
        for (Cellular cell : cellulars) if (tag.isWithinRange(cell)) return cell;
        return null;
    }
    public Cellular getCellular(String ownerName) {
        for (Cellular cell : cellulars)
            if (cell.getOwnerName().equals(ownerName)) return cell;
        return null;
    }
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (EloTelTag tag : tags) {
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName)) {
                return tag;
            }
        }
        return null; // Si no lo encuentra
    }

    private ArrayList<Cellular> cellulars = new ArrayList<Cellular>();
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();
}
