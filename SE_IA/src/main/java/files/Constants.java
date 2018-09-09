package files;

/**
 *
 */
public class Constants {
    //Should be X times the characters length of genre key
    public static final String NO_DATA = "XXXXXXXXXXXXXXXXXXXX";
    //Index
    public static byte ID_LENGTH= Byte.BYTES;
    public static byte GENRE_KEY_BYTES_LENGTH = 20*Character.BYTES;
    public static byte GENRE_KEY_LENGTH = 20;
    public static byte REGISTER_BACKGROUND = 10;
    //Master data
    public static byte POSITION_LENGTH = Long.BYTES;
}
