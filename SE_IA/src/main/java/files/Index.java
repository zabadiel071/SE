package files;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Index file
 * Key is defined integer
 */
public class Index extends FileManager{

    private static final Index INSTANCE = new Index();

    public static Index getInstance(){return INSTANCE;}

    /**
     *
     * @throws FileNotFoundException
     */
    private Index(){
        super("index");
        registerLength = Constants.ID_LENGTH + Constants.POSITION_LENGTH;
    }

    /**
     * Inserts new register into index at the final
     * @param key
     * @param logicAddress
     */
    public boolean insertRegister(byte key, long logicAddress){
        boolean insert = false;
        if (logicAddress(key) == -1){
            try{
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeByte(key);
                randomAccessFile.writeLong(logicAddress);
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
    public long logicAddress(byte key){
        long logicAddress = -1;
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i < nRegisters; i++){
                randomAccessFile.seek(i*registerLength);
                byte foundKey = randomAccessFile.readByte();
                if (foundKey == key){
                    logicAddress = randomAccessFile.readLong();
                }
            }
            randomAccessFile.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logicAddress;
    }

    /**
     *
     * @param key
     */
    public void delete(byte key) {
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i< nRegisters ; i++){
                randomAccessFile.seek(i*registerLength);
                byte foundKey = randomAccessFile.readByte();
                if (foundKey == key){
                    clearRegister(randomAccessFile.getFilePointer());
                }
            }
            randomAccessFile.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

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