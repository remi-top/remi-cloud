package ai.remi.comm.util.security;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc PwdUtils 用户密码工具类
 */
public class PwdUtils {

    //private final static String DEFAULT_PASS="Remi2022";

    private final static String DEFAULT_PASS="Remi@123";


    public static boolean isValidPassword(String password,String encodePassword) {
        return encode(password).equals(encodePassword);
    }


    public static String encode(String s){
        return Md5Utils.encode(s);
    }

    public static String getDefaultPassEncryption(){
        return encode(DEFAULT_PASS);
    }

    public static void main(String[] args) {
        System.out.println(getDefaultPassEncryption());
        System.out.println(Md5Utils.encode(DEFAULT_PASS));
    }
}
