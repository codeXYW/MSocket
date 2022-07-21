package top.co4.modbus.function.write;

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
 * @date 2022/7/20 19:15
 */
public class WriteRegisters {
    /**
     * @Description //TODO 写入多个寄存器
     * @Date 2022/7/20 18:15
     */
    public static boolean writeRegisters(Modbus modbus ,Object values) throws IOException, ModbusException {
        modbus.setFunctionCode(FunctionCode.WRITE_REGISTERS);
        modbus.setMsg(Generate.getWritesMsg(modbus,values));
        String s = SocketUtil.socketSend(modbus, 16);

        if (!SocketUtil.checkSocketValue(s)){
            throw new ModbusException(ExceptionCode.SOCKET_VALUE_NULL_ERROR);
        }

        switch (modbus.getDataType()){
            case 2:
                float[] floats= (float[]) values;
                return  Parse.isSuccess(s,floats.length*2);
            case 3:
                int[] ints = (int[]) values;
                return  Parse.isSuccess(s,ints.length);
            default:
                throw new ModbusException(ExceptionCode.SOCKET_VALUE_ERROR);
        }


    }
}
