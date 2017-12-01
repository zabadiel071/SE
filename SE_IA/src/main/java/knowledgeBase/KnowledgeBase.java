package knowledgeBase;

import files.FileManager;
import files.Index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class KnowledgeBase extends FileManager {
    private final int LENGHT_OF_ID = 2;
    private final int LENTGH_OF_DATA = 4;
    private final String NO_DATA = "XXXX";
    /**
     * Total number of antecedents that a Rule can contain
     */
    private int totalAntecedents = 10;

    /**
     * Index file
     */
    public Index index;

    public ArrayList<Rule> rules;

    public int size = 0;


  public KnowledgeBase() throws IOException {
      super("knowledge");
      index = new Index();
      loadRules();
      this.regLength = 6*Character.BYTES + 10 * (4*Character.BYTES);

      size = (int)randomAccessFile.length()/regLength;
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
                for (int i =0; i< aux; i++){ randomAccessFile.writeChars(NO_DATA); }

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
                rule.setId(readString(LENGHT_OF_ID));
                rule.setConsequent(readString(LENTGH_OF_DATA));
              for (int i = 0; i < totalAntecedents - 1; i++) {
                  String s = readString(LENTGH_OF_DATA);
                  if(s.equals(NO_DATA))
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
              randomAccessFile.writeChars(NO_DATA);
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
                randomAccessFile.writeChars("00");
                randomAccessFile.seek(0);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Load all rules from the knowledge base
     */
    public void loadRules(){
        this.rules = new ArrayList<Rule>();
        Rule r;
        String s;
        try {
            for (int i =0; i < size; i++) {
                randomAccessFile.seek(i*regLength);
                r = new Rule();
                r.setId(readString(LENGHT_OF_ID));
                r.setConsequent(readString(LENTGH_OF_DATA));
                for (int j = 0; j < totalAntecedents; j++) {
                    s = readString(LENTGH_OF_DATA);
                    if (s.equals(NO_DATA)) {
                        break;
                    }
                    r.getBackground().add(s);
                }
                this.rules.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
