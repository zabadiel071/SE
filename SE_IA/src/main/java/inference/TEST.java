package inference;

import knowledgeBase.KnowledgeBase;

import java.io.IOException;
import java.util.ArrayList;

public class TEST {
    public static void main(String[] args) throws IOException {
        KnowledgeBase kb = new KnowledgeBase();
        InferenceEngine inferenceEngine = new InferenceEngine(kb);
        ArrayList<String> facts = new ArrayList<String>();
        facts.add("e1");
        facts.add("e2");
        facts.add("e3");
        facts.add("e4");
        facts.add("e5");
        inferenceEngine.setFact_base(facts);
        inferenceEngine.forwardChaining("MA");
    }
}
