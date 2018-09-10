package inference;

import java.util.ArrayList;

public class Justification {

    /**
     * Singleton
     */
    private static final Justification INSTANCE = new Justification();

    /**
     * Singleton
     * @return
     */
    public static Justification getInstance() {
        return INSTANCE;
    }

    private ArrayList<String> fact_base = new ArrayList<>();

    public String print(){
        return fact_base.toString();
    }

    public ArrayList<String> getFact_base() {
        return fact_base;
    }

    public void setFact_base(ArrayList<String> fact_base) {
        this.fact_base = fact_base;
    }
}
