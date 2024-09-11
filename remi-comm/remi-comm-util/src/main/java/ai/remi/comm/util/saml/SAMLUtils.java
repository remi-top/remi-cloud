package ai.remi.comm.util.saml;

import cn.hutool.core.codec.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class SAMLUtils {

    /**
     * 解析SAMLRequest SAMLResponse
     */
    public static String decodeAndInflateXML(String samlStr) throws Exception {
        // base64解码
        byte[] decodedBytes = Base64.decode(java.net.URLDecoder.decode(samlStr, "utf-8"));
        // 获取输入流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);
        InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream, new Inflater(true));
        byte[] buffer = new byte[decodedBytes.length];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 信息写到输出流
        for (int i = 0; i != -1; i = inflaterInputStream.read(buffer)) {
            byteArrayOutputStream.write(buffer, 0, i);
        }
        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
    }
}
