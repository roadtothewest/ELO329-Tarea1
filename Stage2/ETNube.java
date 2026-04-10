import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

public class ETNube {
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }
    public void updateLocation(String owner, String equipment, float x, float y) {
        Point2D location;
        if ((location=getLocation(owner, equipment)) == null) {
            location=new Point2D.Float(x,y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        }
        location.setLocation(x,y);
    }
    public Point2D getLocation(String owner, String equipment) {
        for (Data d : cloudData) {
            if (d.ownerName.equals(owner) && d.equipmentName.equals(equipment)) {
                return d.location;
            }
        }
        return null;
    }
    public void printHeader(PrintStream output) {
        output.print("Step");
        for (Data d : cloudData) {
            output.print("\t" + d.ownerName + "." + d.equipmentName + ".x" +
                    "\t" + d.ownerName + "." + d.equipmentName + ".y");
        }
        output.println();
    }
    public void printState(PrintStream output, int step) {
        output.print(step);
        for (Data d : cloudData) {
            output.print("\t" + d.location.getX() + "\t" + d.location.getY());
        }
        output.println();
    }
    private ArrayList<Data> cloudData;

    private static class Data {  // internal class
        public Data(String owner, String equipment, Point2D loc) {
            ownerName = owner;
            equipmentName = equipment;
            location = loc;
        }
        public Point2D location;
        public String ownerName, equipmentName;
    }
}
