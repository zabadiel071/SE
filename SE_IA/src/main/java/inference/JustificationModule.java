package inference;

import java.util.ArrayList;

public class JustificationModule {

    /**
     * Singleton
     */
    private static final JustificationModule INSTANCE = new JustificationModule();

    /**
     * Singleton
     * @return
     */
    public static JustificationModule getInstance() {
        return INSTANCE;
    }

    private ArrayList<Justification> fact_base = new ArrayList<>();

    public String print(){
        return fact_base.toString();
    }

    public ArrayList<Justification> getFact_base() {
        return fact_base;
    }

    public void setFact_base(ArrayList<Justification> fact_base) {
        this.fact_base = fact_base;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("Dado que se presentaron los siguientes generos origen:\n");
        for (Justification iterator: fact_base){
            for (String ruleBackground: iterator.data){
                data.append(ruleBackground).append(", ");
            }
            data.append("\nSe puede inferir que el genero resultado es: ").append(iterator.type)
                    .append(" que cumple con todas estas caracteristicas.\n\n");
        }
        return data.toString();
    }
}
