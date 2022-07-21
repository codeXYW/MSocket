package top.co4.modbus.code;

/**
 * TODO:异常码
 *
 * @author CodeWYX
 * @date 2022/3/7 21:44
 */
public class ExceptionCode {
    public static final byte FUNCTION_CODE_ERROR = 1;
    public static final byte SLAVE_ADDRESS_ERROR = 2;
    public static final byte OFFSET_ERROR=3;
    public static final byte SIZE_ERROR=4;

    public static final byte SOCKET_VALUE_NULL_ERROR=5;

    public static final byte SOCKET_VALUE_ERROR=6;


    public ExceptionCode() {
    }

    public static String getExceptionMessage(byte id) {
        switch (id) {
            case 1:
                return "功能码错误";
            case 2:
                return "从机地址错误";
            case 3:
                return "寄存器地址异常";
            case 4:
                return "读数据大小异常";
            case 5:
                return "Socket返回值为空";
            default:
                return "ModbusException";
        }
    }
}
