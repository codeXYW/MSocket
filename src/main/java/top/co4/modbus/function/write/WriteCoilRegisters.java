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
 * @date 2022/7/20 14:06
 */
public class WriteCoilRegisters {

    /**
     * @Description //TODO 写入单个线圈
     * @Date 2022/7/20 18:15
     */
    public static boolean writeCoil(Modbus modbus ,boolean value) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.WRITE_COIL);
        modbus.setMsg(Generate.getWriteMsg(modbus,value));
        String s = SocketUtil.socketSend(modbus, (modbus.getMsg().length() / 2));
        if (!SocketUtil.checkSocketValue(s)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }
        return s.equals(modbus.getMsg());
    }

}
