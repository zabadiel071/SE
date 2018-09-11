package files;

import java.util.Arrays;

/**
 *
 */
public class Constants {

    //Should be X times the characters length of genre key
    public static String NO_DATA = NO_DATA();
    //Index
    public static byte ID_LENGTH= Byte.BYTES;
    public static byte GENRE_KEY_BYTES_LENGTH = 20*Character.BYTES;
    public static byte GENRE_KEY_LENGTH = 20;
    public static byte REGISTER_BACKGROUND = 10;
    //Master data
    public static byte POSITION_LENGTH = Long.BYTES;
    public static String NOT_FOUND = "404";
    public static String INIT_STATE_NOT_FOUND = "No se ha podido equiparar con ningun genero ya que no se cumplieron todas las caracteristicas :(";

    private static String NO_DATA(){
        char[] chars = new char[GENRE_KEY_LENGTH];
        Arrays.fill(chars, Character.MIN_VALUE);
        return new String(chars);
    }
}
