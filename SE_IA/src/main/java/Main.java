import files.Preload;
import inference.InferenceEngine;
import knowledgeBase.KnowledgeBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * to read user input
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * to order exit to menu
     */
    private boolean exit = false;

    public static void main(String[] args) {
        Main menuPrincipal = new Main();
        menuPrincipal.showMenu();
    }

    private void showMenu() {
        String title = "Rules-Based System v0.1";
        System.out.println(title);
        int respuesta = 0;
        do {
            System.out.println(mockInfo());
            System.out.println("Insert an option");
            System.out.println("0->Exit");
            System.out.println("1->Insert facts");
            respuesta = scanner.nextInt();
            switch (respuesta) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    ArrayList l = insertFacts();
                    try {
                        System.out.println("Goal to search:");
                        String s = scanner.next();

                        InferenceEngine engine = new InferenceEngine(new KnowledgeBase());
                        engine.setFact_base(l);
                        if(engine.forwardChaining(s))
                            System.out.println("JUSTIFICACION:\n" + engine.getJustification().print());
                        else
                            System.out.println("No se ingresaron los efectos necesarios para determinar una droga.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }while (!exit);

    }

    private String mockInfo() {
        return Preload.readAll();
    }

    /**
     *
     */
    private ArrayList insertFacts() {
        System.out.println("Symptoms:");
        ArrayList<String> symptoms = new ArrayList<>();

        boolean exitFacts = false;
        String symp = "";
        do{
            System.out.println("Write symptom key :");
            symp = scanner.next();
            symptoms.add(symp);

            System.out.println("¿Insert other? (Y/N)");
            if (scanner.next().equals("N"))
                exitFacts = true;

        }while (!exitFacts);
        return  symptoms;
    }

}
