package top.co4.modbus.utils;

import top.co4.modbus.code.ExceptionCode;
import top.co4.modbus.exception.ModbusException;

/**
 * @author CodeXYW
 * @date 2022/7/17 15:11
 */
public class ModbusUtil {
    /***
     * @Description //TODO 从站地址格式转换
     * @Param: [slaveId]
     * @Return: java.lang.String
     * @Author CodeXYW
     * @Date 2022/7/17 15:17
     */
    public static String getSlaveId(int slaveId) throws ModbusException {
        String slaveIdStr;
        if (slaveId >= 1 && slaveId <= 255) {
            slaveIdStr = DataUtil.decToHex(slaveId);
            switch (slaveIdStr.length()) {
                case 1:
                    return  "0" + slaveIdStr;
                case 2:
                    return slaveIdStr;
                default:
                    return null;
            }
        }
        throw new ModbusException(ExceptionCode.SLAVE_ADDRESS_ERROR);
    }

    /***
     * @Description //TODO 寄存器地址格式转换
     * @Param: [offset]
     * @Return: java.lang.String
     * @Author CodeXYW
     * @Date 2022/7/18 13:04
     */
    public static String getOffset(Integer offset) throws ModbusException {
        if (offset>=0&&offset<=65535){
            String offsetStr;
            offsetStr = DataUtil.decToHex(offset);
            return getString(offsetStr);
        }
        throw new ModbusException(ExceptionCode.OFFSET_ERROR);
    }


    /***
     * @Description //TODO 数据长度格式转换
     * @Param: [size, offset]
     * @Return: java.lang.String
     * @Author CodeXYW
     * @Date 2022/7/18 13:20
     */
    public static String getSize(int size,int offset) throws ModbusException {
        String numberSrt;
        if (size >0 && size <= 255) {
            numberSrt = DataUtil.decToHex(size);
            if ((size+offset)>65536){
                throw new ModbusException(ExceptionCode.SIZE_ERROR);
            }
            return getString(numberSrt);
        }
        throw new ModbusException(ExceptionCode.SIZE_ERROR);
    }

    /***
     * @Description //TODO 补全
     * @Param: [str]
     * @Return: java.lang.String
     * @Author CodeXYW
     * @Date 2022/7/18 13:21
     */
    public static String getString(String str){
        switch (str.length()) {
            case 1:
                return "000" + str;
            case 2:
                return "00" + str;
            case 3:
                return "0" + str;
            default:
                return str;
        }
    }

    public static String getByte(int size) {

        String str = DataUtil.decToHex(size * 2);

        if (str.length() == 1) {
            return "0" + str;
        }
        return str;
    }

}
