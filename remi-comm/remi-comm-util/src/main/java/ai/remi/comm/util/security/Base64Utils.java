package ai.remi.comm.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc Base64Kits
 */
public class Base64Utils {
    public static final String CHARSET = "utf-8";

    private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
        //测试FileBase64
        //long start = System.currentTimeMillis();
        //String imgBase64Str = Base64Utils.convertFileToBase64("C:\\demos\\a3fa6ce0-b9a9-4313-93f0-501d1149596c.jpg");
        //System.out.println("本地图片转换Base64:" + imgBase64Str);
        //System.out.println("Base64字符串length=" + imgBase64Str.length());
        //Base64Utils.convertBase64ToFile(imgBase64Str, "C:\\demos\\", "test.jpg");
        //System.out.println("duration:" + (System.currentTimeMillis() - start));
        //测试StringBase64
        long start = System.currentTimeMillis();
        //ThisIsMyCustomSecretKey012345678
        //String base64Str = Base64Utils.encode("RemiAdminNacosCustomSecretKey2024");
        String base64Str = Base64Utils.encode("RemiAdminNacosCustomSecretKey2024");
        System.out.println("字符串加密后的Base64为:" + base64Str);
        System.out.println("Base64字符串length=" + base64Str.length());
        String decode = Base64Utils.decode(base64Str);
        //String decode = Base64Utils.decode("VGhpc0lzTXlDdXN0b21TZWNyZXRLZXkwMTIzNDU2Nzg=");
        System.out.println("Base64解密后的字符串为:"+decode);
        System.out.println("duration:" + (System.currentTimeMillis() - start));

    }
    /**
     *  字符串解密
     */
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }

            return new String(Base64.getDecoder().decode(data.getBytes(CHARSET)), CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，解密异常", data), e);
        }

        return null;
    }

    /**
     * 字符串加密
     */
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.getEncoder().encode(data.getBytes(CHARSET)), CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，加密异常", data), e);
        }

        return null;
    }

    /**
     * 本地文件（图片、excel等）转换成Base64字符
     */
    public static String convertFileToBase64(String filePath) throws UnsupportedEncodingException {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(filePath);
            System.out.println("文件大小（字节）=" + in.available());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        return new String(Base64.getEncoder().encode(data), CHARSET);
    }

    /**
     * 将base64字符串，生成文件
     */
    public static File convertBase64ToFile(String fileBase64String, String filePath, String fileName) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            byte[] bfile = Base64.getDecoder().decode(fileBase64String);
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
