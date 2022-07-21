package top.co4.modbus.function.read;

import top.co4.modbus.code.DataType;
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

    /**
     * TODO 生成读输入型寄存器报文
     * @param slaveId 从站地址
     * @param offset 寄存器地址
     * @param size 读取的个数
     * @return
     * @throws ModbusException
     */
    public static String getReadInputMsg(int slaveId, int offset, int size,int dataType) throws ModbusException {
        byte functionCode=FunctionCode.READ_INPUT_REGISTERS;
        //一个浮点数32位
        size=size* DataType.getRegisterCount(dataType);
        return Generate.getReadMsg(slaveId, functionCode, offset, size);
    }

    /**
     * @Description //TODO 入口
     * @Date 2022/7/21 11:45
     */
    public static Object readInput(Modbus modbus) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.READ_INPUT_REGISTERS);
        modbus.setSize(modbus.getSize()* DataType.getRegisterCount(modbus.getDataType()));
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
