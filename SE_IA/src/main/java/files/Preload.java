package files;

import knowledgeBase.KnowledgeBase;
import knowledgeBase.Rule;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Gets the rules from the text file
 */
public class Preload {
    public static void main(String[] args) {
        try {
            File file = new File("mockup");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            StringTokenizer stringTokenizer;

            String id, consequent = "";
            ArrayList<String> background = new ArrayList<>();

            KnowledgeBase knowledgeBase = new KnowledgeBase();

            while ((line = bufferedReader.readLine()) != null){
                stringTokenizer = new StringTokenizer(line);
                id = stringTokenizer.nextToken();
                consequent = stringTokenizer.nextToken();
                while (stringTokenizer.hasMoreElements()){
                    background.add(stringTokenizer.nextToken());
                }

                knowledgeBase.insertRegister(new Rule(
                        id,
                        consequent,
                        background
                ));

                background.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
