package inference;

import knowledgeBase.KnowledgeBase;
import knowledgeBase.Rule;

import java.util.ArrayList;

public class InferenceEngine {
    private Justification justification;
    private ArrayList<String> fact_base;
    private ArrayList<Rule> conflictSet;
    private KnowledgeBase knowledgeBase;

    public InferenceEngine(KnowledgeBase kb){
        this.knowledgeBase = kb;
    }

    /**
     * Make inference using forwardChaining algorithm
     * @param goal : String
     * @return boolean
     */
    public boolean forwardChaining(String goal){
        ArrayList<Rule> R;
        ArrayList<String> new_facts;
        conflictSet = new ArrayList<>();
        conflictSet.add(this.knowledgeBase.readRule("1"));
        while(!exist(goal, fact_base) && !conflictSet.isEmpty()){
            conflictSet = equate(knowledgeBase.rules, fact_base);
            if(!conflictSet.isEmpty()){
                R = solve(conflictSet);
                new_facts = apply(R, fact_base);
                update(fact_base, new_facts);
            }
        }
        if(exist(goal, fact_base)){
            return true;
        }
        return false;
    }

    /**
     * TODO
     * @param goal
     */
    /*public boolean backwardChaining(String goal){
        if(verify(goal, fact_base))
            return true;
        else
            return false;

    }*/

    /**
     * TODO
     * @param goal
     * @param fact_base
     * @return
     */
    /*public boolean verify(String goal, ArrayList<String> fact_base){
        ArrayList<Rule> R;
        ArrayList<String> new_goals;
        boolean verified = false;
        if(exist(goal, getFact_base())){
            return true;
        }else{
            conflictSet = equate(knowledgeBase.rules, goal);
            while(!conflictSet.isEmpty() && !verified){
                R = solve(conflictSet);
                delete(R, conflictSet);
                new_goals = get_antecedent(R), verified = true;
                while(!new_goals.isEmpty() && verified){
                    goal = selectGoal(new_goals);
                    delete(goal, new_goals);
                    verified = verify(goal, fact_base);
                    if(verified)
                        add(goal, fact_base);

                }
            }
            return verified;
        }
    }*/

    /**
     * Check if exist a goal in a fact base
     * @param goal : String
     * @param fact_base : ArrayList<String>
     * @return boolean
     */
    private boolean exist(String goal, ArrayList<String> fact_base){ return false; }

    /**
     * Make equate between kb and fact_base
     * @param kb : ArrayList<Rule>
     * @param fact_base : ArrayList<Rule>
     * @return ArrayList<Rule>
     */
    private ArrayList<Rule> equate(ArrayList<Rule> kb, ArrayList<String> fact_base) { return null; }

    private ArrayList<Rule> solve(ArrayList<Rule> cs) { return null; }

    private ArrayList<String> apply(ArrayList<Rule> R, ArrayList<String> fb) { return null; }

    private void update(ArrayList<String> fb, ArrayList<String> new_facts) { }

    public Justification getJustification() {
        return justification;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public ArrayList<String> getFact_base() {
        return fact_base;
    }

    public void setFact_base(ArrayList<String> fact_base) {
        this.fact_base = fact_base;
    }
}