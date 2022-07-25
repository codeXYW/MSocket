package top.co4.modbus.module;

import java.net.Socket;

/**
 * @author CodeXYW
 * @date 2022/7/18 14:33
 */
public class Modbus {
    /**
     * socket连接
     */
    private Socket socket;

    /**
     * 从站id
     */
    private int slaveId;

    /**
     * 寄存器地址
     */
    private int offset;

    /**
     * 数据大小
     */
    private int size=1;

    /**
     * 报文信息
     */
    private String msg;

    /**
     * 返回信息
     */
    private String value;

    /**
     * 功能码
     */
    private byte functionCode;

    /**
     * 数据类型
     */
    private int dataType;

    public Modbus() {
    }

    public Modbus(Socket socket, int slaveId, int offset, int size, String msg, String value, byte functionCode, int dataType) {
        this.socket = socket;
        this.slaveId = slaveId;
        this.offset = offset;
        this.size = size;
        this.msg = msg;
        this.value = value;
        this.functionCode = functionCode;
        this.dataType = dataType;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(byte functionCode) {
        this.functionCode = functionCode;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
