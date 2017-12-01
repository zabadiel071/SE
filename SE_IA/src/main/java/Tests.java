import knowledgeBase.KnowledgeBase;
import knowledgeBase.Rule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) throws IOException {
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        ArrayList<String> background = new ArrayList<>();
        background.add("00E1");
        background.add("00E2");
        background.add("00E3");
        background.add("00E4");
        background.add("00E5");


        Rule rule = new Rule("01","00E1", background);
        knowledgeBase.insertRegister(rule);

        rule = new Rule("02","00E7", background);
        knowledgeBase.insertRegister(rule);

        rule = new Rule("03","00E3", background);
        knowledgeBase.insertRegister(rule);

        //knowledgeBase.delete("01");
        /*Rule ru = new Rule();
        ru.setId("03");
        ru.setConsequent("00E8");
        ArrayList<String> tst = new ArrayList<>();
        tst.add("00e5");
        tst.add("00e8");
        tst.add("00e6");
        ru.setBackground(tst);
        System.out.println(knowledgeBase.update(ru));*/
        knowledgeBase.loadRules();

        for(Rule r : knowledgeBase.rules){
            System.out.println(r.toString());
        }

    }
}
