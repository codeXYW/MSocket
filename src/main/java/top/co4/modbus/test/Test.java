package top.co4.modbus.test;

import top.co4.modbus.code.DataType;
import top.co4.modbus.exception.ModbusException;
import top.co4.modbus.function.read.ReadCoilsRegisters;
import top.co4.modbus.function.read.ReadDiscreteInputRegisters;
import top.co4.modbus.function.read.ReadHoldingRegisters;
import top.co4.modbus.function.read.ReadInputRegisters;
import top.co4.modbus.function.write.WriteCoilRegisters;
import top.co4.modbus.function.write.WriteCoilsRegisters;
import top.co4.modbus.function.write.WriteRegister;
import top.co4.modbus.function.write.WriteRegisters;

import java.io.IOException;
import java.net.Socket;

/**
 * @author CodeXYW
 * @date 2022/7/17 8:23
 */
public class Test {

    static Socket socket;

    static {
        try {
            socket=new Socket("192.168.1.8", 8888);
            socket.setSoTimeout(3000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * @Description //TODO 读线圈
     * @Date 2022/7/19 18:13
     */
    public static void readCoils() throws IOException, ModbusException {
        String s = ReadCoilsRegisters.readCoils(1, 0, 1);
        System.out.println(s);
    }

    /***
     * @Description //TODO 读离散输入寄存器
     * @Date 2022/7/19 18:17
     */
    public static void readDiscreteInput() throws IOException, ModbusException {
        String s = ReadDiscreteInputRegisters.readDiscreteInput(1, 0, 2);
        System.out.println(s);
    }

    /**
     * @Description //TODO 读取输入型寄存器
     * @Date 2022/7/19 18:42
     */
    public static void readInput() throws ModbusException, IOException {
        String s = ReadInputRegisters.readInput(1, 0, 3, DataType.FLOAT);
        System.out.println(s);
    }

    /**
     * @Description //TODO 读取保持型寄存器
     * @Date 2022/7/19 18:43
     */
    public static void readHolding() throws ModbusException, IOException {
        String s = ReadHoldingRegisters.readHolding(1,0,3,DataType.FLOAT);
        System.out.println(s);
    }


    /**
     * @Description //TODO 写入单个线圈
     * @Date 2022/7/20 19:03
     */
    public static void writeCoil() throws IOException, ModbusException {
        String s = WriteCoilRegisters.writeCoil(1, 0, true);
        System.out.println(s);
    }

    /**
     * @Description //TODO 写入寄存器
     * @Date 2022/7/20 19:21
     */
    public static void writeRegister() throws IOException, ModbusException {
        String s = WriteRegister.writeRegister(1, 0, 2, DataType.INTEGER);
        System.out.println(s);
    }

    /**
     * @Description //TODO 批量写入线圈
     * @Date 2022/7/20 19:21
     */
    public static void writeCoils() throws Exception {
        String s = WriteCoilsRegisters.writeCoils(1, 1, new boolean[]{true, true, true, true, true, true, true, true, true});
        System.out.println(s);
    }

    /**
     * @Description //TODO 批量写入保持型寄存器(浮点数类型数据)
     * @Date 2022/7/21 11:15
     */
    public static void writeRegistersFloat() throws IOException, ModbusException {
        String s = WriteRegisters.writeRegisters(1, 0, new float[]{1, 12, 5}, DataType.FLOAT);
        System.out.println(s);
    }

    /**
     * @Description //TODO 批量写入保持型寄存器(Int类型数据)
     * @Date 2022/7/21 11:16
     */
    public static void writeRegistersInt() throws IOException, ModbusException {
        String s = WriteRegisters.writeRegisters(1, 0, new int[]{1, 12, 5}, DataType.INTEGER);
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException, ModbusException {
        writeRegister();
    }
}
