package files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;

/**
 * Used to manage file operations
 */
public class FileManager {
    /**
     *
     */
    protected RandomAccessFile randomAccessFile;

    /**
     * 
     */
    protected int registerLength;

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public FileManager(String fileName) {
        try {
            randomAccessFile = new RandomAccessFile(fileName,"rw");
            randomAccessFile.setLength(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a string for the size specified
     * @param length : expected length for the string
     * @return String
     */
    protected String readString(int length) throws IOException {
        char [] result = new char[length];
            for (int i = 0; i < length ; i++){
                result[i] = randomAccessFile.readChar();
            }
        return String.valueOf(result);
    }

    /**
     * Sets all data in row equals to 0
     * @param position
     * @throws IOException
     */
    public void clearRegister(long position) throws IOException {
        randomAccessFile.seek(position);
        for (int i = 0; i < registerLength;i++){
            randomAccessFile.writeByte(0);
        }
    }

    public String formatString(String s, int length){
        String format = s.trim();
        while (format.length()<length)
            format += Character.MIN_VALUE;
        return format.substring(0,length);
    }

    public int getRegLength() {
        return registerLength;
    }

    public void setRegLength(int regLength) {
        registerLength = regLength;
    }
}


