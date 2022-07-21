package cn.msocket.modbus.code;

import java.math.BigInteger;

/**
 * TODO:数据类型选择
 *
 * @author CodeWYX
 * @date 2022/3/7 21:44
 */
public class DataType {
    public static final int BINARY = 1;

    public static final int TWO_BYTE_INT_UNSIGNED = 2;
    public static final int TWO_BYTE_INT_SIGNED = 3;
    public static final int TWO_BYTE_INT_UNSIGNED_SWAPPED = 22;
    public static final int TWO_BYTE_INT_SIGNED_SWAPPED = 23;
    public static final int FOUR_BYTE_INT_UNSIGNED = 4;
    public static final int FOUR_BYTE_INT_SIGNED = 5;
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED = 6;
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED = 7;
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED = 24;
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED = 25;
    public static final int FOUR_BYTE_FLOAT = 8;
    public static final int FOUR_BYTE_FLOAT_SWAPPED = 9;

    public static final int FOUR_BYTE_FLOAT_SWAPPED_INVERTED = 21;
    public static final int FOUR_BYTE_FLOAT_INVERTED = 26;

    public static final int FOUR_BYTE_FLOAT_SWAPPED_SWAPPED = 27;
    public static final int EIGHT_BYTE_INT_UNSIGNED = 10;
    public static final int EIGHT_BYTE_INT_SIGNED = 11;
    public static final int EIGHT_BYTE_INT_UNSIGNED_SWAPPED = 12;
    public static final int EIGHT_BYTE_INT_SIGNED_SWAPPED = 13;
    public static final int EIGHT_BYTE_FLOAT = 14;
    public static final int EIGHT_BYTE_FLOAT_SWAPPED = 15;
    public static final int TWO_BYTE_BCD = 16;
    public static final int FOUR_BYTE_BCD = 17;
    public static final int FOUR_BYTE_BCD_SWAPPED = 20;

    public static final int CHAR = 18;
    public static final int VARCHAR = 19;

    public DataType() {
    }

    public static int getRegisterCount(int id) {
        switch (id) {
            case 1:
            case 2:
            case 3:
            case 16:
            case 22:
            case 23:
                return 1;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 17:
            case 20:
            case 21:
            case 24:
            case 25:
                return 2;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 4;
            case 18:
            case 19:
            default:
                return 0;
        }
    }

    public static Class<?> getJavaType(int id) {
        switch (id) {
            case 1:
                return Boolean.class;
            case 2:
            case 22:
                return Integer.class;
            case 3:
            case 23:
                return Short.class;
            case 4:
                return Long.class;
            case 5:
                return Integer.class;
            case 6:
            case 24:
                return Long.class;
            case 7:
            case 25:
                return Integer.class;
            case 8:
                return Float.class;
            case 9:
                return Float.class;
            case 10:
                return BigInteger.class;
            case 11:
                return Long.class;
            case 12:
                return BigInteger.class;
            case 13:
                return Long.class;
            case 14:
                return Double.class;
            case 15:
                return Double.class;
            case 16:
                return Short.class;
            case 17:
            case 20:
                return Integer.class;
            case 18:
            case 19:
                return String.class;
            case 21:
                return Float.class;
            default:
                return null;
        }
    }
}
