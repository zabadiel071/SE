package knowledgeBase;

import files.FileManager;
import files.Index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class KnowledgeBase extends FileManager {

    /**
     *
     */

    /**
     * Total number of antecedents that a Rule can contain
     */
    private int totalAntecedents = 10;

    /**
     * Index file
     */
    public Index index;

    public ArrayList<Rule> rules;

    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    KnowledgeBase(String fileName) throws FileNotFoundException {
        super(fileName);
        index = new Index();
    }

  public KnowledgeBase() throws FileNotFoundException {
      super("knowledge");
      index = new Index();
  }

  /**
     * Inserts a new Rule register into knowledge database
     * @param rule
     * @return
     */
    public boolean insertRegister(Rule rule){
        try {
            if (index.logicAddress(rule.getId()) == -1){
                int logicalAddress = (int) randomAccessFile.length();

                randomAccessFile.seek(randomAccessFile.length());

                randomAccessFile.writeChars(rule.getId());
                randomAccessFile.writeChars(rule.getConsequent());
                int aux = totalAntecedents - rule.getBackground().size();
                for(String s : rule.getBackground()){
                    randomAccessFile.writeChars(s);
                }
                for (int i =0; i< aux; i++){ randomAccessFile.writeChars("XX"); }

                randomAccessFile.seek(0);
                index.insertRegister(rule.getId(), logicalAddress);
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

  /**
   * Read one Rule from knowledge base
   * @param id : String
   * @return Rule
   */
    public Rule readRule(String id){
        int logicAddress = index.logicAddress(id);
        Rule rule = new Rule();
        if (logicAddress != -1){
            try{
                randomAccessFile.seek(logicAddress);
                rule.setId(readString(2));
                rule.setConsequent(readString(2));
              for (int i = 0; i < totalAntecedents - 1; i++) {
                  String s = readString(2);
                  if(s.equals("XX"))
                      break;
                  rule.getBackground().add(s);
              }
              randomAccessFile.seek(0);
              return  rule;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

  /**
   *
   * @param rule
   * @return
   */
    public boolean update(Rule rule){
      int logicAddress = index.logicAddress(rule.getId());
      if (logicAddress != -1) {
        try {
          randomAccessFile.seek(logicAddress);
          randomAccessFile.writeChars(rule.getId());
          randomAccessFile.writeChars(rule.getConsequent());
          int aux = totalAntecedents - rule.getBackground().size();
          for (String s : rule.getBackground()) {
            randomAccessFile.writeChars(s);
          }
          for(int i = 0; i<aux; i++){
              randomAccessFile.writeChars("XX");
          }
          randomAccessFile.seek(0);
          return true;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return false;
    }

    /**
     * Delete a register
     * @param id : String
     * @return boolean
     */
    public boolean delete(String id){
        int logicalAddress = index.logicAddress(id);
        if(logicalAddress != -1){
            try {
                randomAccessFile.seek(logicalAddress);
                randomAccessFile.writeChars("XX");
                randomAccessFile.seek(0);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void loadRules(){
        this.rules = new ArrayList<Rule>();
        Rule r;
        String s;
        for (int i =0; i < randomAccessFile.lenght; i++) {
            r = new Rule();
            r.setId(readString(2));
            r.setConsequent(readString(2));
            for (int i = 0; i < totalAntecedents; i++) {
                s = readString(2);
                if (s.equals("XX"))
                    break;
                r.getBackground().add(s);
            }
            this.rules.add(r);
        }
    }
}
