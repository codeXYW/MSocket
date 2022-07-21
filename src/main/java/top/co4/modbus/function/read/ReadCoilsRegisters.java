package top.co4.modbus.function;

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

    /***
     * @Description //TODO 入口
     * @Date 2022/7/19 12:23
     */
    public static List<Boolean> readCoils(Modbus modbus) throws ModbusException, IOException {
        //生成报文
        modbus.setMsg(Generate.getReadMsg(modbus));
        modbus.setFunctionCode(FunctionCode.READ_COILS);
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
