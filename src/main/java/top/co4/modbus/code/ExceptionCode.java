package cn.msocket.modbus.code;

/**
 * TODO:异常码
 *
 * @author CodeWYX
 * @date 2022/3/7 21:44
 */
public class ExceptionCode {
    public static final byte ILLEGAL_FUNCTION = 1;
    public static final byte ILLEGAL_DATA_ADDRESS = 2;
    public static final byte ILLEGAL_DATA_VALUE = 3;
    public static final byte SLAVE_DEVICE_FAILURE = 4;
    public static final byte ACKNOWLEDGE = 5;
    public static final byte SLAVE_DEVICE_BUSY = 6;
    public static final byte MEMORY_PARITY_ERROR = 8;
    public static final byte GATEWAY_PATH_UNAVAILABLE = 10;
    public static final byte GATEWAY_TARGET_DEVICE_FAILED_TO_RESPOND = 11;
    public static final byte ILLEGAL_SLAVE_ID = 12;
    public static final byte ILLEGAL_ADDRESS_VALUE = 13;

    public ExceptionCode() {
    }

    public static String getExceptionMessage(byte id) {
        switch (id) {
            case 1:
                return "非法的功能";
            case 2:
                return "非法数据地址";
            case 3:
                return "非法的数据值";
            case 4:
                return "设备故障";
            case 5:
                return "Acknowledge";
            case 6:
                return "从设备正忙";
            case 7:
            case 9:
            default:
                return "未知的异常: " + id;
            case 8:
                return "内存奇偶校验错误";
            case 10:
                return "网关路径不可用";
            case 11:
                return "网关目标设备未能响应";
            case 12:
                return "非法的站地址";
            case 13:
                return "非法的地址值";
            case 14:
                return "CoilStatusException";
        }
    }
}
