package top.co4.modbus.msg;

import top.co4.modbus.code.DataType;
import top.co4.modbus.module.Modbus;
import top.co4.modbus.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeXYW
 * @date 2022/7/19 19:06
 */
public class Parse {

    /**
     * @Description //TODO 接口开关类型数据
     * @Date 2022/7/19 19:13
     */
    public static List<Boolean> parseSwitchValue(Modbus modbus){
        //获取返回数据大小
        long dataSize= DataUtil.hexToDec(modbus.getValue().substring(4,6));
        StringBuilder data=new StringBuilder(modbus.getValue().substring(6, 6+Math.toIntExact(dataSize * 2)));
        //将数据转为二进制
        data = new StringBuilder(DataUtil.hexStrToBinary(data.toString()));
        ArrayList<Byte> values = new ArrayList<>();
        ArrayList<Boolean> booleans = new ArrayList<>();
        //数据长度不同采用不同的解析方案
        if (data.length()<8&&data.length()>0){
            //数据长度8位时可以直接高低位反转
            for (int j=data.length()-1; j>=0;j--){
                values.add((byte) data.toString().toCharArray()[j]);
            }
        }else {
            //数据长度超过8位时需要每次取8位来进行高低位反转
            for (int i = 0; i < data.length(); i+=8) {
                //每次截取八个
                String str=data.substring(i,i+8);
                //从末尾循环实现高低位取反
                for (int j=str.length()-1; j>=0;j--){
                    values.add((byte) str.toCharArray()[j]);
                }
            }
        }
        //因在数据进行进制转换的过程中结果会被转换成能和8取余的数，所以需要进行长度控制
        for (int i = 0; i < modbus.getSize(); i++) {
            booleans.add(values.get(i) ==49);
        }
        return booleans;
    }

    /**
     * @Description //TODO 解析数值类型值数据
     * @Date 2022/7/19 21:10
     */
    public static Object parseNumberValue(Modbus modbus){
        String value = modbus.getValue();
        long dataSize= DataUtil.hexToDec(value.substring(4,6));
        StringBuilder data=new StringBuilder(value.substring(6, 6+Math.toIntExact(dataSize * 2)));
        List<Object> values=new ArrayList<>();
        //根据数据类型进行解析与取值
        int dataType=DataType.getRegisterCount(modbus.getDataType());
        for (int i = 0; i < data.length(); i+=4* dataType) {
            String str=data.substring(i,i+4* dataType);
            values.add(getValue(str,dataType));
        }
        return values;
    }


    /**
     * @Description //TODO 字符串类型的值解析
     * @Date 2022/7/20 17:11
     */
    public static String parseStringValue(Modbus modbus){
        return null;
    }

    /**
     * @Description //TODO value转换
     * @Date 2022/7/20 17:09
     */
    private static Object getValue(String str,int id){
        switch (id){
            case 1:
                return Math.toIntExact(DataUtil.hexToDec(str));
            case 2:
                return Float.intBitsToFloat(Integer.valueOf(str.trim(), 16));
            default:
                return null;
        }
    }

    /**
     * @Description //TODO 校验写入多个数据是否成功
     * @Date 2022/7/21 17:25
     */
    public static boolean isSuccess(String s,int size){
        String data = s.substring(8, 12);
        return size==DataUtil.hexToDec(data);
    }


}
