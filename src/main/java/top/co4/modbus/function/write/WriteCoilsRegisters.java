package top.co4.modbus.function.write;

import top.co4.modbus.code.DataType;
import top.co4.modbus.code.ExceptionCode;
import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.msg.Generate;
import top.co4.modbus.msg.Parse;
import top.co4.modbus.utils.SocketUtil;

/**
 * @author CodeXYW
 * @date 2022/7/20 14:08
 */
public class WriteCoilsRegisters {

    /**
     * @Description //TODO 生成写入多个线圈报文
     * @Date 2022/7/20 18:15
     */
    public static String writeCoils(int slaveId,int offset, boolean[] values) throws Exception {
        return Generate.getWritesMsg(slaveId,FunctionCode.WRITE_COILS,offset,values,DataType.BOOLE);
    }

    /**
     * @Description //TODO 写入多个线圈
     * @Date 2022/7/20 18:15
     */
    public static boolean writeCoils(Modbus modbus , boolean[] values) throws Exception {
        modbus.setFunctionCode(FunctionCode.WRITE_COILS);
        modbus.setDataType(DataType.BOOLE);
        modbus.setMsg(Generate.getWritesMsg(modbus,values));
        String s = SocketUtil.socketSend(modbus, 16);

        if (!SocketUtil.checkSocketValue(s)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }

        return  Parse.isSuccess(s,values.length);
    }
}
