package files;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Index file
 * Key is defined by char(2), are numbers
 */
public class Index extends FileManager{

    /**
     *
     * @throws FileNotFoundException
     */
    public Index() throws FileNotFoundException {
        super("index");
        this.regLength = 2*Character.BYTES + Integer.BYTES;
    }

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    private Index(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Inserts new register into index at the final
     * @param key
     * @param logicAddress
     */
    public boolean insertRegister(String key, int logicAddress){
        boolean insert = false;
        if (logicAddress(key) == -1){
            try{
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeChars(key);
                randomAccessFile.writeInt(logicAddress);
                randomAccessFile.seek(0);
                insert = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return insert;
    }

    /**
     *
     * @param key
     * @return
     */
    public int logicAddress(String key){
        int logicAddress = -1;
        try {
            long nRegisters = randomAccessFile.length() / regLength;
            for (int i = 0; i < nRegisters; i++){
                randomAccessFile.seek(i*regLength);
                if (readString(2).equals(key)){
                    logicAddress = randomAccessFile.readInt();
                }
            }
            randomAccessFile.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logicAddress;
    }


    /**
     *  Obtiene el archivo indice
     * @return
     */
    public String read(){
        String result = "";
        try {
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()){
                result += readString(2) + "\t";
                result += randomAccessFile.readInt() + "\n";
            }
            randomAccessFile.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }
}