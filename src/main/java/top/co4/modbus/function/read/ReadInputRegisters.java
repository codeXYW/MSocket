package top.co4.modbus.function;

import top.co4.modbus.code.ExceptionCode;
import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.msg.Generate;
import top.co4.modbus.msg.Parse;
import top.co4.modbus.utils.SocketUtil;

import java.io.IOException;

/**
 * @author CodeXYW
 * @date 2022/7/19 18:04
 * 输入寄存器
 */
public class ReadInputRegisters {

    public static Object readInput(Modbus modbus) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.READ_INPUT_REGISTERS);
        //获取报文信息
        modbus.setMsg(Generate.getReadMsg(modbus));
        //发送并接收返回值
        String value = SocketUtil.socketSend(modbus, getByteLength(modbus.getSize()));
        if (!SocketUtil.checkSocketValue(value)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }
        modbus.setValue(value);

        return Parse.parseNumberValue(modbus);
    }


    private static int getByteLength(int size){
        return 5+size*2;
    }
}
