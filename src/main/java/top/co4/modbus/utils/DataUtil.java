package cn.msocket.modbus.utils;

/**
 * TODO:进制转换
 *
 * @author CodeWYX
 * @date 2022/3/7 21:21
 */
public class DataUtil {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String hexStr = "0123456789ABCDEF";


    /**
     * 十进制转十六进制
     **/
    public static String decToHex(String dec) {
        return Integer.toHexString(Integer.parseInt(dec));
    }

    /**
     * 十六进制转十进制
     **/
    public static Long hexToDec(String hex) {
        return Long.parseLong(hex, 16);
    }

    /**
     * 十进制转二进制
     **/
    public static String decToBinary(Integer dec) {
        return Integer.toBinaryString(dec);
    }

    /**
     * 十六进制转二进制
     **/
    public static String hexToBinary(String hex) {
        return decToBinary(hexToDec(hex).intValue());
    }

    /**
     * 将字符串转换为十六进制存储为byte数组
     **/
    public static byte[] getBytes(String str) {
        if (str == null || "".equals(str.trim())) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    /**
     * 将存储为byte类型的数组转换为字符串
     **/
    public static String bytesToHexFun2(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }


    /**
     * 将十六进制字符串转换为二进制
     **/
    public static String hexStrToBinary(String hexNum) {
        char[] chs = {'0', '1'};
        String str = "0123456789ABCDEF";
        char[] charArray = hexNum.toUpperCase().toCharArray();
        int pos = charArray.length * 4;
        char[] binaryArray = new char[pos];
        for (int i = charArray.length - 1; i >= 0; i--) {
            int temp = str.indexOf(charArray[i]);
            for (int j = 0; j < 4; j++) {
                binaryArray[--pos] = chs[temp & 1];
                temp = temp >>> 1;
            }
        }
        return new String(binaryArray);
    }

    /**
     * 二进制字符串转十六进制字符串
     **/
    public static String turn2to16(String str) {
        StringBuilder sum = new StringBuilder();

        int t = str.length() % 4;
        if (t != 0) {
            for (int i = str.length(); i - 4 >= 0; i = i - 4) {
                String s = str.substring(i - 4, i);
                int tem = Integer.parseInt(String.valueOf(s), 2);
                sum.insert(0, Integer.toHexString(tem).toUpperCase());
            }
            String st = str.substring(0, t);

            int tem = Integer.parseInt(String.valueOf(st), 2);
            sum.insert(0, Integer.toHexString(tem).toUpperCase());

        } else {
            for (int i = str.length(); i - 4 >= -1; i = i - 4) {
                String s = str.substring(i - 4, i);
                int tem = Integer.parseInt(String.valueOf(s), 2);
                sum.insert(0, Integer.toHexString(tem).toUpperCase());
            }
        }
        return sum.toString();
    }

}
