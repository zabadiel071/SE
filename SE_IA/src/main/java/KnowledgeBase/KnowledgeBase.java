package KnowledgeBase;

import Files.FileManager;
import Files.Index;

import java.io.FileNotFoundException;
import java.io.IOException;

public class KnowledgeBase extends FileManager {

    /**
     * Total number of antecedents that a Rule can contain
     */
    private int totalAntecedents = 10;

    /**
     * Index file
     */
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
    public boolean insertRegister(Rule rule){
        try {
            if (index.logicAddress(rule.getId()) != -1){
                int logicalAddress = (int) randomAccessFile.length();

                randomAccessFile.seek(randomAccessFile.length());

                randomAccessFile.writeChars(rule.getId());
                randomAccessFile.writeChars(rule.getConsequent());

                for(String s : rule.getBackground()){
                    randomAccessFile.writeChars(s);
                }

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
    public Rule read(String id){
        int logicAddress = index.logicAddress(id);
        Rule rule = new Rule();
        if (logicAddress != -1){
            try{
                randomAccessFile.seek(logicAddress);
                rule.setId(readString(2));
                rule.setConsequent(readString(2));
              for (int i = 0; i < totalAntecedents ; i++) {
                rule.getBackground().add(readString(2));
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
          for (String s : rule.getBackground()) {
            randomAccessFile.writeChars(s);
          }
          randomAccessFile.seek(0);
          return true;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return false;
    }

}
