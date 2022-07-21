package top.co4.modbus.utils;

import top.co4.modbus.module.Modbus;

import java.io.IOException;
import java.net.Socket;

/**
 * @author CodeXYW
 * @date 2022/7/18 15:22
 */
public class SocketUtil {

    public static String socketSend(Modbus modbus,int length) throws IOException {
        Socket socket=modbus.getSocket();
        socket.getOutputStream().write(DataUtil.getBytes(modbus.getMsg()));
        byte[] bytes;
        //通过socket 获取输入流
        int read = socket.getInputStream().read(bytes = new byte[length]);
        if (read != -1) {
            socket.close();
            return DataUtil.bytesToHexFun2(bytes);
        } else {
            return null;
        }
    }

    public static boolean checkSocketValue(String value){
        return value != null && value.length() >= 8;
    }
}
