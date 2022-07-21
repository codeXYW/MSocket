package top.co4.modbus.function.write;

import top.co4.modbus.code.ExceptionCode;
import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.msg.Generate;
import top.co4.modbus.utils.SocketUtil;

import java.io.IOException;

/**
 * @author CodeXYW
 * @date 2022/7/20 19:14
 */
public class WriteRegister {
    /**
     * @Description //TODO 写入单个寄存器
     * @Date 2022/7/21 10:45
     */
    public static boolean writeRegister(Modbus modbus , float value) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.WRITE_REGISTER);
        modbus.setMsg(Generate.getWriteMsg(modbus,value));
        String s = SocketUtil.socketSend(modbus, (modbus.getMsg().length() / 2));
        if (!SocketUtil.checkSocketValue(s)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }
        return s.equals(modbus.getMsg());
    }
}
