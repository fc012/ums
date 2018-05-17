package util;

/**
 * Created by admin on 2016/3/7.
 */
public class StringUtil {

    /**
     * 首字母变为大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        if(name.length()>=1){
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return  name;

    }
}
