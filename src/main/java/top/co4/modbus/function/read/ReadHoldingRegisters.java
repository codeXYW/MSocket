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
 * 保持型寄存器
 */
public class ReadHoldingRegisters {

    /**
     * TODO 生成读保持型寄存器报文
     * @param slaveId 从站地址
     * @param offset 寄存器地址
     * @param size 读取的个数
     * @return
     * @throws ModbusException
     */
    public static String getReadHoldingMsg(int slaveId, int offset, int size,int dataType) throws ModbusException {
        byte functionCode=FunctionCode.READ_HOLDING_REGISTERS;
        size=size* DataType.getRegisterCount(dataType);
        return Generate.getReadMsg(slaveId, functionCode, offset, size);
    }

    public static Object readHolding(Modbus modbus) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.READ_HOLDING_REGISTERS);
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

    /***
     * @Description //TODO 计算接收信息
     * @Param: [size]
     * @Return: int
     * @Author CodeXYW
     * @Date 2022/7/19 12:21
     */
    private static int getByteLength(int size){
        return 5+size*2;
    }
}
