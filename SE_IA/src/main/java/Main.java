import inference.InferenceEngine;
import inference.JustificationModule;

import java.util.ArrayList;

public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Blues");
        list.add("R&B");
        list.add("Gospel");
        list.add("BoogieWoogie");
        list.add("Jazz");
        list.add("Country");
        InferenceEngine.getInstance().init(list);

        JustificationModule.getInstance().toString();
    }
}
