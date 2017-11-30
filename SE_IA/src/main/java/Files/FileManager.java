package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Clase para realizar control sobre archivos,
 * permite obtener un string, y crear un archivo con el nombre determinado
 */
public class FileManager {
    /**
     * Objeto para manejar el archivo de acceso aleatorio
     */
    protected RandomAccessFile randomAccessFile;

    /**
     * Longitud de registro
     */
    protected int regLength;

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public FileManager(String fileName) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(fileName,"rw");
    }

    /**
     * Reads a string for the size specified
     * @param length : expected length for the string
     * @return String
     */
    protected String readString(int length) throws IOException {
        char [] result = new char[length];
            for (int i = 0; i < length ; i++){
                result[i] = this.randomAccessFile.readChar();
            }
        return String.valueOf(result);
    }

    public int getRegLength() {
        return regLength;
    }

    public void setRegLength(int regLength) {
        this.regLength = regLength;
    }
}
