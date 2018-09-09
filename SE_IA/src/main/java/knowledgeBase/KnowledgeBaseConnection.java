package knowledgeBase;

import files.Constants;
import files.FileManager;
import files.Index;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 */
public class KnowledgeBaseConnection extends FileManager{

    /**
     * Singleton
     */
    private static final KnowledgeBaseConnection INSTANCE = new KnowledgeBaseConnection("knowledgeBase");

    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    private KnowledgeBaseConnection(String fileName) {
        super(fileName);
        registerLength = Constants.ID_LENGTH +
                Constants.GENRE_KEY_BYTES_LENGTH +
                Constants.REGISTER_BACKGROUND * Constants.GENRE_KEY_BYTES_LENGTH;
    }

    /**
     * Singleton
     * @return
     */
    public static KnowledgeBaseConnection getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Inserts a rule in the last space available
     * @param rule
     * @return
     */
    public boolean insert(Rule rule){
        try {
            if (getLogicalAddress(rule.getId()) == -1 ){
                randomAccessFile.seek(randomAccessFile.length());
                long logicalAddress = randomAccessFile.getFilePointer();

                randomAccessFile.writeByte(rule.getId());
                randomAccessFile.writeChars(formatString(rule.getConsequent(),Constants.GENRE_KEY_LENGTH));

                //Background defined
                for (String s : rule.getBackground())
                    randomAccessFile.writeChars(formatString(s,Constants.GENRE_KEY_LENGTH));

                //Background undefined
                for ( int i = rule.getBackground().size();i < Constants.REGISTER_BACKGROUND; i++ )
                    randomAccessFile.writeChars(formatString("",Constants.GENRE_KEY_LENGTH));

                randomAccessFile.seek(0);
                Index.getInstance().insertRegister(rule.getId(), logicalAddress);
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Returns a rule with the id specified, returns null if it doesn't exists
     * @param id
     * @return
     */
    public Rule get(byte id){
        long logicalAddress = getLogicalAddress(id);
        if (logicalAddress != -1){
            try {
                randomAccessFile.seek(logicalAddress);
                return readRule();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Reads a rule at pointer established
     * @return
     */
    private Rule readRule(){
        Rule rule = new Rule();
        try {
            byte id = randomAccessFile.readByte();
            if (id == 0){
                return null;
            }
            rule.setId(id);
            rule.setConsequent(readString(Constants.GENRE_KEY_LENGTH));
            String s = "";
            int i = 0;
            while (i < Constants.REGISTER_BACKGROUND) {
                s = readString(Constants.GENRE_KEY_LENGTH);
                if (s.charAt(0) == Character.MIN_VALUE) break;
                rule.getBackground().add(s);
                i++;
            }
            randomAccessFile.seek(0);

            return rule;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param rule
     * @return
     */
    public boolean update (Rule rule){
        long logicalAddress = getLogicalAddress(rule.getId());
        if (logicalAddress != -1){
            try {
                randomAccessFile.seek(logicalAddress);
                randomAccessFile.writeByte(rule.getId());
                randomAccessFile.writeChars(formatString(rule.getConsequent(),Constants.GENRE_KEY_LENGTH));

                //Background defined
                for (String s : rule.getBackground())
                    randomAccessFile.writeChars(formatString(s,Constants.GENRE_KEY_LENGTH));

                //Background undefined
                for ( int i = rule.getBackground().size();i < Constants.REGISTER_BACKGROUND; i++ )
                    randomAccessFile.writeChars(formatString("",Constants.GENRE_KEY_LENGTH));

                randomAccessFile.seek(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * Delete from master and index file
     * @param id
     * @return
     */
    public boolean delete(byte id){
        long logicalAddress = getLogicalAddress(id);
        if (logicalAddress != -1){
            try {
                clearRegister(logicalAddress);
                Index.getInstance().delete(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Returns all elements(rules) in ArrayList
     * @return
     */
    public ArrayList<Rule> get(){
        ArrayList<Rule> rulesList = new ArrayList<>();
        try {
            long registers = randomAccessFile.length() / registerLength;
            for(int i = 0; i< registers; i++){
                randomAccessFile.seek(i*registerLength);
                Rule aux = readRule();
                if (aux != null)
                    rulesList.add(aux);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rulesList;
    }

    /**
     *
     * @param id
     * @return
     */
    private long getLogicalAddress(byte id){
        return Index.getInstance().logicAddress(id);
    }

    public static void preloadRules(){
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader("RULES.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            Rule rule;
            byte id;
            String key;
            ArrayList<String> background;
            while ((line = bufferedReader.readLine()) != null){

                StringTokenizer stringTokenizer = new StringTokenizer(line);
                id = Byte.parseByte(stringTokenizer.nextToken());
                key = stringTokenizer.nextToken();
                background = new ArrayList<>();

                while (stringTokenizer.hasMoreElements()){
                    background.add(stringTokenizer.nextToken());
                }

                rule = new Rule(id,key,background);
                KnowledgeBaseConnection.getINSTANCE().insert(rule);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        KnowledgeBaseConnection.getINSTANCE().preloadRules();
        System.out.println(KnowledgeBaseConnection.getINSTANCE().get());
    }
}
