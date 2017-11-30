package KnowledgeBase;

import Files.FileManager;
import Files.Index;

import java.io.FileNotFoundException;

public class KnowledgeBase extends FileManager {

    /**
     * Total number of antecedents that a Rule can contain
     */
    private int totalAntecedents = 0;

    protected Index index;

    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    KnowledgeBase(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Inserts a new Rule register into knowledge database
     * @param rule
     * @return
     */
    public boolean addRule(Rule rule){
        return true;
    }
}
