import knowledgeBase.KnowledgeBase;
import knowledgeBase.Rule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) throws IOException {
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        ArrayList<String> background = new ArrayList<>();
        background.add("E1");
        background.add("E2");
        background.add("E3");
        background.add("E4");
        background.add("E5");


        //Rule rule = new Rule("01","E1", background);
        //knowledgeBase.insertRegister(rule);

//        rule = new Rule("02","E7", background);
        //knowledgeBase.insertRegister(rule);

  //      rule = new Rule("03","E3", background);
        //knowledgeBase.insertRegister(rule);

        knowledgeBase.loadRules();

        for(Rule r : knowledgeBase.rules){
            System.out.println(r.toString());
        }

    }
}
