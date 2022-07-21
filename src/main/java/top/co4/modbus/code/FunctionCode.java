package top.co4.modbus.code;

import top.co4.modbus.exception.ModbusException;

/**
 * @author CodeXYW
 * @date 2022/7/17 15:00
 */
public class FunctionCode {
    public static final byte READ_COILS = 1;
    public static final byte READ_DISCRETE_INPUTS = 2;
    public static final byte READ_HOLDING_REGISTERS = 3;
    public static final byte READ_INPUT_REGISTERS = 4;
    public static final byte WRITE_COIL = 5;
    public static final byte WRITE_COILS = 15;
    public static final byte WRITE_REGISTER = 6;
    public static final byte WRITE_REGISTERS = 10;
    //特殊功能码
    public static final byte READ_EXCEPTION_STATUS = 7;

    public static final byte REPORT_SLAVE_ID = 17;
    public static final byte WRITE_MASK_REGISTER = 22;

    public static String getFunctionCodeString(byte functionCode) throws ModbusException {
        switch (functionCode) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 10:
                return "10";
            case 15:
                return "0F";
            case 17:
                return "11";
            case 7:
                return "7";
            case 22:
                return "16";
            default:
                throw new ModbusException(ExceptionCode.FUNCTION_CODE_ERROR);

        }
    }
}

