package inference;

import files.Constants;
import knowledgeBase.KnowledgeBaseConnection;
import knowledgeBase.Rule;

import java.util.ArrayList;

public class InferenceEngine {

    private ArrayList<String> userFacts = new ArrayList<>();
    private ArrayList<Rule> knowledgeBase = new ArrayList<>();

    private ArrayList<String> deducted = new ArrayList<>();
    public ArrayList<String> factsBase = new ArrayList<>();

    /**
     * Singleton
     */
    private static final InferenceEngine INSTANCE = new InferenceEngine();

    /**
     * Singleton
     * @return
     */
    public static InferenceEngine getInstance() {
        return INSTANCE;
    }

    /**
     * Temporal facts base to test
     */
    private void setUserFacts(){
        userFacts.add("Blues");
        userFacts.add("R&B");
        userFacts.add("Gospel");
        userFacts.add("BoogieWoogie");
        userFacts.add("Jazz");
        userFacts.add("Country");
        //userFacts.add("Rockabilly");
        //userFacts.add("Folk");
        //userFacts.add("PostRock");
        //userFacts.add("Postpunk");
        //userFacts.add("GarageRock");
        //userFacts.add("Indie");
        //userFacts.add("PostGrunge");
    }

    public void init(){
        setUserFacts();
        KnowledgeBaseConnection.preloadRules();
        this.knowledgeBase = KnowledgeBaseConnection.getINSTANCE().get();
        forwardChaining();
    }

    private void forwardChaining(){
        String output = Constants.INIT_STATE_NOT_FOUND;
        String fact = "";
        for (Rule rule: knowledgeBase){
            deducted.clear();
            ArrayList<String> currentFacts = (ArrayList<String>) userFacts.clone();
            while (!currentFacts.isEmpty()){
                for (String ruleBackground: rule.getBackground()){
                    if (ruleBackground.contains(currentFacts.get(0))) {
                        deducted.add(currentFacts.get(0));
                        if (!factsBase.contains(currentFacts.get(0)))
                            factsBase.add(currentFacts.get(0));
                        if (!unifies(deducted,rule).equals(Constants.NOT_FOUND)){
                            output = unifies(deducted,rule);
                            fact = unifies(deducted,rule);
                        }
                    }
                }
                currentFacts.remove(0);
            }
            if (!fact.equals(Constants.NOT_FOUND) && !factsBase.contains(fact)){
                update(fact);
            }
        }
        System.out.println("Output result: "+output);
        Justification.getInstance().setFact_base(factsBase);
        System.out.println("Fact base: "+Justification.getInstance().print());
    }

    private String unifies(ArrayList<String> BC, Rule rule){
        String data;
        int elements = rule.getBackground().size();
        int countElements = 0;
        ArrayList<String> currentFacts = (ArrayList<String>) BC.clone();
        while (!currentFacts.isEmpty()){
            for (String ruleBackground: rule.getBackground()){
                if (ruleBackground.contains(currentFacts.get(0))) {
                    countElements++;
                }
            }
            currentFacts.remove(0);
        }
        if (countElements==elements)
            data = rule.getConsequent();
        else
            data = Constants.NOT_FOUND;
        return data;
    }

    private void update(String fact){
        factsBase.add(fact);
    }
}