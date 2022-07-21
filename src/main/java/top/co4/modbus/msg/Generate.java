package top.co4.modbus.msg;

import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.utils.Crc16Util;
import top.co4.modbus.utils.ModbusUtil;

/**
 * @author CodeXYW
 * @date 2022/7/18 13:52
 */
public class Generate {

    public static String getMsg(int slaveId,byte functionCode,int offset,int size) throws ModbusException {
        StringBuilder sb = new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset) + ModbusUtil.getSize(size, offset));
        String crc = Crc16Util.getCRC(sb.toString());
        return sb.append(crc).toString().toUpperCase();
    }
}
