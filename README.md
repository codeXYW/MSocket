# MSocket4j
基于Modbus协议实现的框架

# 写在前面
通讯方式采用的是Java的Socket来进行通讯,也就是代表走的是TCP连接，但是使用的协议则是modbus-rtu协议,此框架不但支持与PLC设备通讯,同时还支持只生成报文信息,使得用户的选择可以更灵活,用户可以选择将连接交给框架来处理也可以选择将连接有自己来管理,同时将代码开源出来是因本人还在未毕业缺少实战经验,在封装框架的过程中可能会有些不足的之处,还请路过的大佬能帮忙指点一二

# 使用详情
参考 Test.java
~~~
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
        WriteCoilRegisters.writeCoil(modbus, true);
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
        WriteRegister.writeRegister(modbus, 2);
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
        WriteCoilsRegisters.writeCoils(modbus, new boolean[]{false,true,true,true,true,true,true,true,true});
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
        WriteRegisters.writeRegisters(modbus,new float[]{1,12,5});
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
        WriteRegisters.writeRegisters(modbus,new int[]{1,12,5});
    }

    public static void main(String[] args) throws Exception {
        System.out.println("欢迎使用MSocket");
    }

}

~~~
