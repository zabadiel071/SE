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
        Rule R;
        conflictSet = new ArrayList<>();
        conflictSet.add(this.knowledgeBase.readRule("01"));
        while(!exist(goal, fact_base) && !conflictSet.isEmpty()){
            conflictSet = equate(knowledgeBase.rules, fact_base);
            if(!conflictSet.isEmpty()){
                R = solve(conflictSet);
                apply_update(R, fact_base);
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
    private boolean exist(String goal, ArrayList<String> fact_base){
        return fact_base.contains(goal);
    }

    /**
     * Make equate between kb and fact_base
     * @param kb : ArrayList<Rule>
     * @param fact_base : ArrayList<Rule>
     * @return ArrayList<Rule>
     */
    private ArrayList<Rule> equate(ArrayList<Rule> kb, ArrayList<String> fact_base) {
        ArrayList<Rule> resultRules = new ArrayList<>();
        boolean isToAdd = true;
        for (Rule r : kb){
            for (String s : fact_base){
                if (!r.getBackground().contains(s)){
                    isToAdd = false;
                }
            }
            if (isToAdd)
                resultRules.add(r);
        }
        return resultRules;
    }

    /**
     *
     * @param cs
     * @return
     */
    private Rule solve(ArrayList<Rule> cs) {
        Rule aux = null;
        for(Rule r : cs){
           if (aux != null){
               if (Integer.parseInt(r.getId()) < Integer.parseInt(aux.getId()))
                   aux = r;
           }
        }
        return aux;
    }

    private void apply_update(Rule R, ArrayList<String> fb) {
        fb.add(R.getConsequent());
    }

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
