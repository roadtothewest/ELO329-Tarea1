import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Locale;

public class T1Stage1 {
    private Territory territory;
    private int step = 0;

    T1Stage1() {
        territory = new Territory();
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));

        // Crea output.csv
        PrintStream outputFile = new PrintStream(new File("output.csv"));

        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile);

        // Pasar outputFile en lugar de System.out
        stage.runSimulation(movFile, outputFile);

        confFile.close();
        movFile.close();
        outputFile.close();
    }

    public void setupSimulator(Scanner in) {  
        in.useLocale(Locale.US);
	int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet = in.nextInt() == 1;
            
            in.nextFloat(); in.nextFloat();  // Saltamos la posición del celular
            
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                EloTelTag tag = new EloTelTag(personName, tagName, x, y);
                territory.addTag(tag);
            }
            if (isThereTablet) {
                in.nextFloat(); in.nextFloat();  // Saltamos la posición del tablet
            }
        }
    }

    public void runSimulation(Scanner in, PrintStream output) {
        in.useLocale(Locale.US);
	territory.printHeader(output);
        territory.printState(output, step);
        while (in.hasNextLine() && in.hasNext()) { // in.hasNext() previene errores de líneas vacías
            step++;
            String equipment = in.next();
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String tagName = parts[1];
            
            String action = in.next();
            if (!action.equals("FindMy")) {
                float deltaX = Float.parseFloat(action);
                float deltaY = in.nextFloat();
                EloTelTag tag = territory.getTag(personName, tagName);
                if (tag != null) {
                    tag.move(deltaX, deltaY);
                }
            }
            territory.printState(output, step);
        }
    }
}
