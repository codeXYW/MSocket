package top.co4.modbus.code;

/**
 * TODO:数据类型选择
 *
 * @author CodeWYX
 * @date 2022/3/7 21:44
 */
public class DataType {

    public static final int BOOLE = 1;
    public static final int FLOAT =2;

    public static final int INTEGER=3;


    public DataType() {
    }

    public static int getRegisterCount(int id) {
        switch (id) {
            case 1:
            case 3:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }

}
