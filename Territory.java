import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {  
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    public EloTelTag getTag(String ownerName, String equipmentName) {
        // Busca en la lista el tag que coincida con el dueño y el nombre
        for (EloTelTag tag : tags) {
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName)) {
                return tag;
            }
        }
        return null; // Retorna null si no lo encuentra
    }

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (EloTelTag tag : tags) {
            output.print(tag.getHeader() + "\t");
        }
        output.println();
    }

    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for (EloTelTag tag : tags) {
            output.print(tag.getState() + "\t");
        }
        output.println();
    }
}
