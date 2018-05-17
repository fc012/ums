/**
 * Created by admin on 2017/4/14.
 */
public class Main {
    public static void main(String[] args) {
        try {
            GeneratorFactory.getInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
