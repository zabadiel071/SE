package knowledgeBase;

import java.util.ArrayList;

/**
 * Represents a Rule to be inferred and managed by the knowledgeBase
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
     *
     */
   private byte id;

    /**
     *
     * @param id
     * @param consequent
     * @param background
     */
    public Rule(byte id , String consequent, ArrayList<String> background) {
        this.background = background;
        this.consequent = consequent;
        this.id = id;
    }

  public Rule() {
        this.background = new ArrayList<String>();
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

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

  @Override
  public String toString() {
    return "Rule{" +
            "background=" + background +
            ", consequent='" + consequent + '\'' +
            ", id=" + id +
            '}';
  }
}
