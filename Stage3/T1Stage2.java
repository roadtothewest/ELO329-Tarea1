import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class T1Stage2 {
    T1Stage2() {
        territory = new Territory();
        nube = new ETNube();
    }
    public static void main (String args[]) throws IOException {
                                // throws exception to avoid catching
                                // exception in the program
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage2 <configFile> <moveFile>");
            System.exit(-1);
        }
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        T1Stage2 stage = new T1Stage2();
        PrintStream outputFile = new PrintStream(new File("output.csv"));
        stage.setupSimulator(confFile);
        stage.runSimulation(movFile, outputFile);
        confFile.close();
        movFile.close();
        outputFile.close();
    }
    public void setupSimulator(Scanner in) {  // create objects from file
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }
    private void setupPersonEquipment(Scanner in){
        Cellular cellular;
        float x, y;

        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet= in.nextInt()==1;
        x = in.nextFloat(); // cellular's location
        y = in.nextFloat();
        cellular = new Cellular(personName, x, y, nube);
        territory.addCellular(cellular);
        nube.updateLocation(personName, "celular", x, y);
        for (int j = 0; j < tagNumber; j++)
           setupEloTags(in, personName);
        if (isThereTablet) {
            in.nextFloat(); in.nextFloat();  // skip tablet's location
        }
    }

    private void setupEloTags(Scanner in, String personName) {
        EloTelTag tag;
        float x, y;
        String tagName = in.next();
        x = in.nextFloat();
        y = in.nextFloat();
        tag = new EloTelTag(personName,tagName,x, y);
        territory.addTag(tag);
        nube.updateLocation(tag.getOwnerName(),tag.getName(), x, y);
    }

/*
private void setupTablet(Scanner in, String personName) {
        Tablet tab;
        float x, y;
        x = in.nextFloat();
        y = in.nextFloat();
        tab = new Tablet(personName,x, y);
        territory.addTag(tag);
        nube.updateLocation(tag.getOwnerName(),tag.getName(), x, y);
    }
*/


    public void runSimulation(Scanner in, PrintStream output) {
        in.useLocale(Locale.US);
        nube.printHeader(output); // in this stage, print cloud's state
        nube.printState(output, step);

        while (in.hasNextLine() && in.hasNext()) {
            step++;
            String equipment = in.next(); // read person'a name . equipment's name
            String[] parts = equipment.split("\\.");
            String personName = parts[0];
            String equipmentName = parts[1];
            String action = in.next();
            if (!action.equals("FindMy")) {
                float deltaX = Float.parseFloat(action);
                float deltaY = in.nextFloat();

                if (equipmentName.equals("celular")) {
                    Cellular cell = territory.getCellular(personName);
                    if (cell != null) {
                        cell.move(deltaX, deltaY);
                        // Le avisamos a la nube que el celular se movió
                        nube.updateLocation(personName, "celular", cell.getX(), cell.getY());
                    }
                } else {
                    EloTelTag tag = territory.getTag(personName, equipmentName);
                    if (tag != null) { // ¡Esto evita que el programa se caiga al leer un tablet!
                        tag.move(deltaX, deltaY);
                    }
                }
                territory.forEachTagTryToReportLocation();
                nube.printState(output, step);
            } else{
                if(equipmentName == "celular"){
            territory.getCellular(personName).comandoFindMy();
                } else{

                    //pronto
                }
            }
        }
    }
    private int step=0;
    private Territory territory;  // it knows all the equipments and checks cellular nearby tags.
    private ETNube nube;
}
