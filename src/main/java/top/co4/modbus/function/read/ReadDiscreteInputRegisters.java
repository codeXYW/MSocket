package top.co4.modbus.function.read;

import top.co4.modbus.code.ExceptionCode;
import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.msg.Generate;
import top.co4.modbus.msg.Parse;
import top.co4.modbus.utils.SocketUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author CodeXYW
 * @date 2022/7/19 18:03
 * (离散)离散寄存器
 */
public class ReadDiscreteInputRegisters {

    /**
     * @Description //TODO 生成读离散输入型寄存器报文
     * @Date 2022/7/25 16:44
     */
    public static String readDiscreteInput(int slaveId, int offset, int size) throws ModbusException {
        byte functionCode=FunctionCode.READ_DISCRETE_INPUTS;
        return Generate.getReadMsg(slaveId, functionCode, offset, size);
    }

    /**
     * @Description //TODO 入口
     * @Date 2022/7/21 11:36
     */
    public static List<Boolean> readDiscreteInput(Modbus modbus) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.READ_DISCRETE_INPUTS);
        //生成报文信息
        modbus.setMsg(Generate.getReadMsg(modbus));
        String value= SocketUtil.socketSend(modbus,getByteLength(modbus.getSize()));
        if (!SocketUtil.checkSocketValue(value)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }
        modbus.setValue(value);
        return Parse.parseSwitchValue(modbus);
    }

    private static int getByteLength(int size){
        return size<=8?6:6+(size/8);
    }
}
