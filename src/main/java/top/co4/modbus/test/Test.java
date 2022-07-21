package top.co4.modbus.test;

import lombok.extern.slf4j.Slf4j;
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
import top.co4.modbus.module.Modbus;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * @author CodeXYW
 * @date 2022/7/17 8:23
 */
@Slf4j
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
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSocket(socket);
        List<Boolean> booleans = ReadCoilsRegisters.readCoils(modbus);
        for (Boolean aBoolean : booleans) {
            System.out.println(aBoolean);
        }
    }

    /***
     * @Description //TODO 读离散输入寄存器
     * @Date 2022/7/19 18:17
     */
    public static void readDiscreteInput() throws IOException, ModbusException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSize(1);
        modbus.setSocket(socket);
        List<Boolean> booleans = ReadDiscreteInputRegisters.readDiscreteInput(modbus);
        for (Boolean aBoolean : booleans) {
            System.out.println(aBoolean);
        }
    }

    /**
     * @Description //TODO 读取输入型寄存器
     * @Date 2022/7/19 18:42
     * 01 04 04 03 E8 00 00 7B F4
     */
    public static void readInput() throws ModbusException, IOException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSize(3);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.FLOAT);
        Object o = ReadInputRegisters.readInput(modbus);
        System.out.println(o);
    }

    /**
     * @Description //TODO 读取保持型寄存器
     * @Date 2022/7/19 18:43
     * 01 03 04 FF FF 00 01 3B D7
     */
    public static void readHolding() throws ModbusException, IOException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSize(3);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.FLOAT);
        Object o = ReadHoldingRegisters.readHolding(modbus);
        System.out.println(o);
    }


    /**
     * @Description //TODO 写入单个线圈
     * @Date 2022/7/20 19:03
     */
    public static void writeCoil() throws IOException, ModbusException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.BOOLE);
        boolean b = WriteCoilRegisters.writeCoil(modbus, true);
        System.out.println(b);
    }

    /**
     * @Description //TODO 写入寄存器
     * @Date 2022/7/20 19:21
     */
    public static void writeRegister() throws IOException, ModbusException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.INTEGER);
        boolean b = WriteRegister.writeRegister(modbus, 2);
        System.out.println(b);
    }

    /**
     * @Description //TODO 批量写入线圈
     * @Date 2022/7/20 19:21
     */
    public static void writeCoils() throws Exception {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(1);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.BOOLE);
        boolean b = WriteCoilsRegisters.writeCoils(modbus, new boolean[]{false, true, true, true, true, true, true, true, true});
        System.out.println(b);
    }

    /**
     * @Description //TODO 批量写入保持型寄存器(浮点数类型数据)
     * @Date 2022/7/21 11:15
     */
    public static void writeRegistersFloat() throws IOException, ModbusException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.FLOAT);
        boolean b = WriteRegisters.writeRegisters(modbus, new float[]{1, 12, 5});
        System.out.println(b);
    }

    /**
     * @Description //TODO 批量写入保持型寄存器(Int类型数据)
     * @Date 2022/7/21 11:16
     */
    public static void writeRegistersInt() throws IOException, ModbusException {
        Modbus modbus = new Modbus();
        modbus.setSlaveId(1);
        modbus.setOffset(0);
        modbus.setSocket(socket);
        modbus.setDataType(DataType.INTEGER);
        boolean b = WriteRegisters.writeRegisters(modbus, new int[]{1, 12, 5});
        System.out.println(b);
    }

    public static void main(String[] args) throws Exception {
//        readCoils();
//        readHolding();
//        readDiscreteInput();
//        readInput();
//        writeCoil();
//        writeRegister();
//        writeCoils();
        writeRegistersInt();
//       writeRegistersFloat();
        System.out.println("欢迎使用MSocket");
    }

}
