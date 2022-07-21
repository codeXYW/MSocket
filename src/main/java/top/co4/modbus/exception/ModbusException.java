package top.co4.modbus;

import top.co4.modbus.code.ExceptionCode;

/**
 * @author CodeXYW
 * @date 2022/7/17 15:06
 */
public class ModbusException extends Exception{
    byte code;
    String msg;

    public ModbusException(byte code) {
        this.code = code;
        this.msg = ExceptionCode.getExceptionMessage(code);
    }
}
