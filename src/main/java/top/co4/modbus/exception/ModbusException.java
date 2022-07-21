package top.co4.modbus.exception;

import top.co4.modbus.code.ExceptionCode;

/**
 * @author CodeXYW
 * @date 2022/7/17 15:06
 */
public class ModbusException extends Exception{
    byte code;

    public ModbusException(byte code) {
        super(ExceptionCode.getExceptionMessage(code));
        this.code = code;
    }
}
