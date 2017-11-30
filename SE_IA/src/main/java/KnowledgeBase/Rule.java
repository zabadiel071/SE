package KnowledgeBase;

import java.util.ArrayList;

/**
 * Represents a Rule to be inferred and managed by the KnowledgeBase
 */
public class Rule {
    /**
     *
      */
   private ArrayList<String> background;

    /**
     *
     */
   private String consequent;

    /**
     * Id char[2]
     */
   private String id;

    /**
     *
     * @param background
     * @param consequent
     * @param id
     */
    public Rule(ArrayList<String> background, String consequent, String id) {
        this.background = background;
        this.consequent = consequent;
        this.id = id;
    }

  public Rule() {

  }

  /**
     *
     * @return int
     */
   int quantity(){
       return background.size();
   }


    public ArrayList<String> getBackground() {
        return background;
    }

    public void setBackground(ArrayList<String> background) {
        this.background = background;
    }

    public String getConsequent() {
        return consequent;
    }

    public void setConsequent(String consequent) {
        this.consequent = consequent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
