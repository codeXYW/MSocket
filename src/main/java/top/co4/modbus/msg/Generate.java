package top.co4.modbus.msg;

import lombok.extern.slf4j.Slf4j;
import top.co4.modbus.code.FunctionCode;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.utils.Crc16Util;
import top.co4.modbus.utils.DataUtil;
import top.co4.modbus.utils.ModbusUtil;

import java.util.Locale;

/**
 * @author CodeXYW
 * @date 2022/7/18 13:52
 */
@Slf4j
public class Generate {


    /***
     * @Description //TODO 读PLC数据报文
     * @Date 2022/7/18 14:19
     */
    public static String getReadMsg(int slaveId, byte functionCode, int offset, int size) throws ModbusException {
        //将信息格式转换 转十六进制 字符拼接等
        StringBuilder sb = new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset) + ModbusUtil.getSize(size, offset));
        //进行校验
        String crc = Crc16Util.getCRC(sb.toString());
        //拼接
        String msg = sb.append(crc).toString().toUpperCase();
        log.info("报文信息:{}", msg);
        return msg;
    }


    /**
     * @Description //TODO 读PLC数据报文(封装)
     * @Date 2022/7/20 18:54
     */
    public static String getReadMsg(Modbus modbus) throws ModbusException {
        return getReadMsg(modbus.getSlaveId(), modbus.getFunctionCode(), modbus.getOffset(), modbus.getSize());
    }


    /**
     * @Description //TODO 为PLC写入单个数据
     * @Date 2022/7/20 17:50
     */
    public static String getWriteMsg(int slaveId, byte functionCode, int offset, String value) throws ModbusException {
        //将信息格式转换 转十六进制 字符拼接等
        StringBuilder sb = new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset) + value);
        //进行校验
        String crc = Crc16Util.getCRC(sb.toString());
        //拼接
        String msg = sb.append(crc).toString().toUpperCase();
        log.info("报文信息:{}", msg);
        return msg;
    }


    /**
     * @Description //TODO 为PLC写入单个数据(封装)
     * @Date 2022/7/20 17:50
     */
    public static String getWriteMsg(Modbus modbus, Object value) throws ModbusException {
        String s = getOneValue(value, modbus.getDataType());
        return getWriteMsg(modbus.getSlaveId(), modbus.getFunctionCode(), modbus.getOffset(), s);
    }


    /**
     * @Description //TODO 为PLC写入多个数据
     * @Date 2022/7/20 17:50
     *
     */
    public static String getWritesMsg(int slaveId,byte functionCode,int offset,Object values,int dataType) throws ModbusException {
        switch (dataType){
            case 1:
                boolean[] booleans= (boolean[]) values;
                //生成批量写入线圈报文     从机地址  功能码  起始地址高位  起始地址低位  数量高位  数量低位  字节数  数据1  数据2  CRC高位  CRC低位
                StringBuilder sb0=new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset)+ModbusUtil.getString(DataUtil.decToHex(booleans.length))+getWriteCoilsMsg(booleans));
                //crc校验
                String s0 = Crc16Util.getCRC(sb0.toString());
                String booleansMsg = sb0.append(s0).toString().toUpperCase();
                log.info("报文信息:{}", booleansMsg);
                return booleansMsg;
            case 2:
                //生成批量写入保持型寄存器   从机地址  功能码  起始地址高位  起始地址低位  数量高位  数量低位  字节数  0001高位  0001低位  0002高位  0002低位  CRC高位  CRC低位
                float[] floats= (float[]) values;
                StringBuilder sb1=new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset)+ModbusUtil.getString(DataUtil.decToHex(floats.length*2))+getWriteRegistersMsg(floats,dataType));

                //crc校验
                String s1 = Crc16Util.getCRC(sb1.toString());
                String floatsMsg = sb1.append(s1).toString().toUpperCase();
                log.info("报文信息:{}", floatsMsg);
                return floatsMsg;
            case 3:
                int[] ints= (int[]) values;
                //生成批量写入保持型寄存器   从机地址  功能码  起始地址高位  起始地址低位  数量高位  数量低位  字节数  0001高位  0001低位  0002高位  0002低位  CRC高位  CRC低位
                StringBuilder sb2=new StringBuilder(ModbusUtil.getSlaveId(slaveId) + FunctionCode.getFunctionCodeString(functionCode) + ModbusUtil.getOffset(offset)+ModbusUtil.getString(DataUtil.decToHex(ints.length))+getWriteRegistersMsg(ints,dataType));
                //crc校验
                String s2 = Crc16Util.getCRC(sb2.toString());
                String intsMsg = sb2.append(s2).toString().toUpperCase();
                log.info("报文信息:{}", intsMsg);
                return intsMsg;
            default:
                return null;
        }
    }


    /**
     * @Description //TODO 为PLC写入多个数据(封装)
     * @Date 2022/7/20 17:50
     */
    public static String getWritesMsg(Modbus modbus, Object values) throws ModbusException {
        return getWritesMsg(modbus.getSlaveId(),modbus.getFunctionCode(),modbus.getOffset(),values , modbus.getDataType());
    }


    /**
     * @Description //TODO 写入单个数据时值转换
     * @Date 2022/7/20 21:30
     */
    private static String getOneValue(Object value, int dataType) {

        switch (dataType) {
            //写入的数据为开关类型
            case 1:
                boolean b = (boolean) value;
                return b ? "FF00" : "0000";
            //写入的数据为浮点型
            case 2:
                float f = (float) value;
                return Integer.toHexString(Float.floatToIntBits(f));
            //写入的数据为整型
            case 3:
                String str = String.valueOf(value);
                int idx = str.lastIndexOf(".");
                String strNum = str.substring(0,idx);
                int num = Integer.parseInt(strNum);
                String s = DataUtil.decToHex(num);
                return ModbusUtil.getString(s);
            default:
                return null;
        }
    }


    /**
     * @Description //TODO 写入多个线圈时生成数据部分报文
     * @Date 2022/7/20 19:36
     */
    public static String getWriteCoilsMsg(boolean[] values) {
        byte[] bytes;

        StringBuilder msg = new StringBuilder();

        //小于8添0 设置bytes长度默认为0
        if (values.length < 8) {
            bytes = new byte[values.length + (8 - values.length)];
        } else if (values.length % 8 == 0) {
            bytes = new byte[values.length];
        } else {
            //不能被8整除添0
            bytes = new byte[values.length + (8 - values.length % 8)];
        }

        //将boolean 转byte
        for (int i = 0; i < values.length; i++) {
            if (values[i]) {
                bytes[i] = 1;
            } else {
                bytes[i] = 0;
            }
        }

        //报文生成 每次取八个 然后高低位反转 然后转成16进制 拼接
        for (int a = 0; a < bytes.length; a += 8) {
            StringBuilder sb = new StringBuilder();
            for (int i = a; i < a + 8; i++) {
                sb = new StringBuilder(sb.toString() + bytes[i]);
            }
            sb.reverse();
            msg.append(DataUtil.turn2to16(sb.toString()));
        }

        //添加数据大小
        if (msg.length() == 2) {
            msg.insert(0, "01");
        } else {
            int dataSize = msg.length() / 2;
            StringBuilder s = new StringBuilder(DataUtil.decToHex(dataSize));
            if (s.length() == 1) {
                s = new StringBuilder("0").append(s);
            }
            msg.insert(0, s);
        }

        return msg.toString().toUpperCase(Locale.ROOT);
    }


    /**
     * @Description //TODO 写入多个保持型寄存器生成数据部分报文
     * @Date 2022/7/20 21:35
     */
    public static String getWriteRegistersMsg(Object values, int dataType) {
        StringBuilder msg = new StringBuilder();
        if (dataType == 2) {
            float[] floats= (float[]) values;
            for (float value : floats) {
                float f = value;
                String s = Integer.toHexString(Float.floatToIntBits(f));
                System.out.println(s);
                msg.append(ModbusUtil.getString(s));
            }
        } else {
            int[] ints= (int[]) values;
            for (int value : ints) {
                msg.append(ModbusUtil.getString(DataUtil.decToHex( value)));
            }
        }

        //添加数据大小
        if (msg.length() == 2) {
            msg.insert(0, "01");
        } else {
            int dataSize = msg.length() / 2;
            StringBuilder s = new StringBuilder(DataUtil.decToHex(dataSize));
            if (s.length() == 1) {
                s = new StringBuilder("0").append(s);
            }
            msg.insert(0, s);
        }
        return msg.toString().toUpperCase(Locale.ROOT);
    }
}
