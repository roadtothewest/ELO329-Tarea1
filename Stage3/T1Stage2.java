import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Locale;

public class T1Stage2 {
    private int step = 0;
    private Territory territory;
    private ETNube nube;

    public T1Stage2() {
        territory = new Territory();
        nube = new ETNube();
    }

    public static void main (String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage2 <configFile> <moveFile>");
            System.exit(-1);
        }
        
        // 1. LOCALE GLOBAL: Soluciona el problema de los decimales de raíz para ambos archivos.
        Scanner confFile = new Scanner(new File(args[0]));
        confFile.useLocale(Locale.US);
        
        Scanner movFile = new Scanner(new File(args[1]));
        movFile.useLocale(Locale.US);
        
        T1Stage2 stage = new T1Stage2();
        PrintStream outputFile = new PrintStream(new File("output.csv"));
        
        stage.setupSimulator(confFile);
        stage.runSimulation(movFile, outputFile);
        
        confFile.close();
        movFile.close();
        outputFile.close();
    }

    public void setupSimulator(Scanner in) {
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            setupPersonEquipment(in);
        }
    }

    private void setupPersonEquipment(Scanner in){
        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;
        
        float x = in.nextFloat();
        float y = in.nextFloat();
        
        Cellular cellular = new Cellular(personName, x, y, nube);
        territory.addCellular(cellular);
        nube.updateLocation(personName, "celular", x, y);
        
        for (int j = 0; j < tagNumber; j++) {
           setupEloTags(in, personName);
        }
           
        if (isThereTablet) {
            setupTablet(in, personName); // 2. INTEGRADO: Ya no salta la línea, ahora la lee.
        }
    }

    private void setupEloTags(Scanner in, String personName) {
        String tagName = in.next();
        float x = in.nextFloat();
        float y = in.nextFloat();
        EloTelTag tag = new EloTelTag(personName, tagName, x, y);
        territory.addTag(tag);
        nube.updateLocation(personName, tagName, x, y);
    }

    // 3. BUG CORREGIDO: Tu compañero había copiado y pegado variables del tag aquí.
    private void setupTablet(Scanner in, String personName) {
        float x = in.nextFloat();
        float y = in.nextFloat();
        Tablet tab = new Tablet(personName, x, y);
        territory.addTab(tab);
        nube.updateLocation(personName, "tablet", x, y);
    }

    public void runSimulation(Scanner in, PrintStream output) {
        nube.printHeader(output);
        nube.printState(output, step);

        // 4. WHILE LIMPIO: Solo lee y delega, ya no es un "espagueti".
        while (in.hasNext()) {
            step++;
            String equipment = in.next();
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String equipmentName = parts[1];
            String action = in.next();
            
            if (action.equals("FindMy")) {
                procesarComandoFindMy(personName, equipmentName);
            } else {
                float deltaX = Float.parseFloat(action);
                float deltaY = in.nextFloat();
                procesarMovimiento(personName, equipmentName, deltaX, deltaY);
            }
            
            territory.forEachTagTryToReportLocation();
            // territory.forEachTabletTryToReportLocation(); // Descomentar en Etapa 4
            nube.printState(output, step);
        }
    }

    // 5. MÉTODOS DE DELEGACIÓN: Aíslan la lógica de búsqueda y movimiento.
    private void procesarMovimiento(String personName, String equipmentName, float deltaX, float deltaY) {
        if (equipmentName.equals("celular")) {
            Cellular cell = territory.getCellular(personName);
            if (cell != null) {
                cell.move(deltaX, deltaY);
                nube.updateLocation(personName, "celular", cell.getX(), cell.getY());
            }
        } else if (equipmentName.equals("tablet")) {
            Tablet tab = territory.getTablet(personName);
            if (tab != null) {
                tab.move(deltaX, deltaY);
                // Nota: Los tablets reportan vía celular cercano, no directo.
            }
        } else {
            EloTelTag tag = territory.getTag(personName, equipmentName);
            if (tag != null) {
                tag.move(deltaX, deltaY);
            }
        }
    }

    private void procesarComandoFindMy(String personName, String equipmentName) {
        if (equipmentName.equals("celular")) {
            Cellular cell = territory.getCellular(personName);
            if (cell != null) {
                cell.comandoFindMy();
            }
        } else if (equipmentName.equals("tablet")) {
            Tablet tab = territory.getTablet(personName);
            // if (tab != null) tab.comandoFindMy(); // Descomentar cuando agregues el método en Tablet.java
        }
    }
}