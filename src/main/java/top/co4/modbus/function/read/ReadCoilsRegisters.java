package top.co4.modbus.function.read;

import lombok.extern.slf4j.Slf4j;
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
 * @date 2022/7/18 14:27
 * 线圈寄存器
 */
@Slf4j
public class ReadCoilsRegisters {

    /**
     * TODO 生成读线圈报文
     * @param slaveId 从站地址
     * @param offset 寄存器地址
     * @param size 读取的个数
     * @return
     * @throws ModbusException
     */
    public static String getReadCoilsMsg(int slaveId, int offset, int size) throws ModbusException {
        byte functionCode=FunctionCode.READ_COILS;
        return Generate.getReadMsg(slaveId, functionCode, offset, size);
    }

    /***
     * @Description //TODO 入口
     * @Date 2022/7/19 12:23
     */
    public static List<Boolean> readCoils(Modbus modbus) throws ModbusException, IOException {
        modbus.setFunctionCode(FunctionCode.READ_COILS);
        //生成报文
        modbus.setMsg(Generate.getReadMsg(modbus));
        //解析值
        String value = SocketUtil.socketSend(modbus,getByteLength(modbus.getSize()));
        if (!SocketUtil.checkSocketValue(value)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }
        modbus.setValue(value);
        //解析值
        return Parse.parseSwitchValue(modbus);
    }


    /***
     * @Description //TODO 计算接收信息
     * @Param: [size]
     * @Return: int
     * @Author CodeXYW
     * @Date 2022/7/19 12:21
     */
    private static int getByteLength(int size){
        return size<=8?6:6+(size/8);
    }
}
