package files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
    public FileManager(String fileName) throws FileNotFoundException {
        randomAccessFile = new RandomAccessFile(fileName,"rw");
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

    public int getRegLength() {
        return registerLength;
    }

    public void setRegLength(int regLength) {
        registerLength = regLength;
    }
}
