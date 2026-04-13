import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

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
        System.out.print("Step");
        output.print("Step,");
        for (Data d : cloudData) {
            System.out.print("\t" + d.ownerName + "." + d.equipmentName + ".x" +
                    "\t" + d.ownerName + "." + d.equipmentName + ".y");

            output.print(
                d.ownerName + "." + d.equipmentName + ".x" + "," +
                d.ownerName + "." + d.equipmentName + ".y,"
            );
        }
       System.out.println();
        output.println();
    }
    
    public void printState(PrintStream output, int step) {
        System.out.print(step);
        output.print(step + ",");
        for (Data d : cloudData) {
           // Redondeo a 2 decimales
           String strX = String.format(Locale.US, "%.2f", d.location.getX());
           String strY = String.format(Locale.US, "%.2f", d.location.getY());
           
           System.out.print("\t" + strX + "\t" + strY);
           output.print(strX + "," + strY + ",");
        }
        System.out.println();
        output.println();
    }

    public String stringFormatoFigura2(String nombre){
        String aux="Bienes de " + nombre + "\n";
        String aux2 = "";
        String aux3 = "";
        
        for (Data d : cloudData) {
            String strX = String.format(Locale.US, "%.2f", d.location.getX());
            String strY = String.format(Locale.US, "%.2f", d.location.getY());
            
            // Usamos equalsIgnoreCase para evitar errores por mayúsculas/minúsculas
            if (d.ownerName.equals(nombre) && !d.equipmentName.equalsIgnoreCase("celular") && !d.equipmentName.equalsIgnoreCase("tablet")) {
                aux2 = aux2 + d.equipmentName + ": " + strX + "," + strY + "\n";
            }
        }

        for (Data d : cloudData) {
            String strX = String.format(Locale.US, "%.2f", d.location.getX());
            String strY = String.format(Locale.US, "%.2f", d.location.getY());
            
            if (d.ownerName.equals(nombre) && (d.equipmentName.equalsIgnoreCase("celular") || d.equipmentName.equalsIgnoreCase("tablet"))) {
                aux3 = aux3 +  d.equipmentName + ": " + strX + "," + strY + "\n";
            }
        }

        aux = aux + "Items:\n" + aux2 + "Dispositivos:\n" + aux3;
        return aux;
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