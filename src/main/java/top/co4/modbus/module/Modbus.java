package top.co4.modbus.module;

import lombok.Data;

import java.net.Socket;

/**
 * @author CodeXYW
 * @date 2022/7/18 14:33
 */
@Data
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

}
