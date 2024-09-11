package ai.remi.comm.redis.serializer;

import java.io.*;

/**
 * 序列化工具类
 */
public class SerializeUtils {
    /**
     * 将对象序列化
     * @Edit_Description:
     * @Create_Version:shebao-framelib 1.0
     */
    public static byte[] serialize(Object source) {
        byte[] bs = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            if (source != null) {
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(source);
                bs = bos.toByteArray();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return bs;
    }


    /**
     * 将字节数组反序列化
     * @Edit_Description:
     * @Create_Version:shebao-framelib 1.0
     */
    public static Object deserialize(byte[] source) {
        Object obj = null;
        ObjectInputStream inputStream = null;
        try {
            if (source != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(source);
                inputStream = new ObjectInputStream(bis);
                obj = inputStream.readObject();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return obj;
    }
}
