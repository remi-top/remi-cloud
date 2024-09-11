package ai.remi.comm.util.xml;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-26
 * @desc DOMUtils
 */
public class DOMUtils {

    /**
     * 添加根节点元素
     *
     * @param document 根文档
     * @param rootName 根节点名称
     * @return 新增节点
     */
    public static Element addRoot(Document document, String rootName) {
        // 添加子节点元素
        return document.addElement(rootName);
    }

    /**
     * 添加根节点元素
     *
     * @param document  根文档
     * @param rootName  根节点名称
     * @param rootValue 根节点值
     * @return 新增节点
     */
    public static Element addRoot(Document document, String rootName, String rootValue) {
        // 添加子节点元素
        Element root = document.addElement(rootName);
        // 为子节点元素设值
        root.setText(rootValue == null ? "" : rootValue);
        return root;
    }

    /**
     * 添加根节点元素
     *
     * @param document   根文档
     * @param rootName   根节点名称
     * @param attributes 根节点属性
     * @return 新增节点
     */
    public static Element addRoot(Document document, String rootName, Map<String, String> attributes) {
        // 添加子节点元素
        Element root = document.addElement(rootName);
        // 为子节点元素设属性
        if (MapUtils.isEmpty(attributes)) {
            return root;
        }
        attributes.forEach((key, value) -> {
            if (key.contains(":")) {
                //处理冒号属性问题
                root.addNamespace(key.substring(key.indexOf(":")+1), value);
            }
            root.addAttribute(key, value);
        });
        return root;
    }

    /**
     * 添加根节点元素
     *
     * @param document   根文档
     * @param rootName   根节点名称
     * @param rootValue  根节点值
     * @param attributes 根节点属性
     * @return 新增节点
     */
    public static Element addRoot(Document document, String rootName, String rootValue, Map<String, String> attributes) {
        // 添加子节点元素
        Element root = document.addElement(rootName);
        // 为子节点元素设值
        root.setText(rootValue == null ? "" : rootValue);
        // 为子节点元素设属性
        if (MapUtils.isEmpty(attributes)) {
            return root;
        }
        attributes.forEach((key, value) -> {
            if (key.contains(":")) {
                //处理冒号属性问题
                root.addNamespace(key.substring(key.indexOf(":")+1), value);
            }
            root.addAttribute(key, value);
        });
        return root;
    }

    /**
     * 添加孩子节点元素
     *
     * @param parent    父节点
     * @param childName 孩子节点名称
     * @return 新增节点
     */
    public static Element addChild(Element parent, String childName) {
        // 添加子节点元素
        return parent.addElement(childName);
    }

    /**
     * 添加孩子节点元素
     *
     * @param parent     父节点
     * @param childName  孩子节点名称
     * @param childValue 孩子节点值
     * @return 新增节点
     */
    public static Element addChild(Element parent, String childName, String childValue) {
        // 添加子节点元素
        Element child = parent.addElement(childName);
        // 为子节点元素设值
        child.setText(childValue == null ? "" : childValue);
        return child;
    }

    /**
     * 添加孩子节点元素
     *
     * @param parent     父节点
     * @param childName  孩子节点名称
     * @param attributes 孩子节点属性
     * @return 新增节点
     */
    public static Element addChild(Element parent, String childName, Map<String, String> attributes) {
        // 添加子节点元素
        Element child = parent.addElement(childName);
        // 为子节点元素设属性
        if (MapUtils.isEmpty(attributes)) {
            return child;
        }
        attributes.forEach((key, value) -> {
            if (key.contains(":")) {
                //处理冒号属性问题
                //child.addNamespace(key.substring(key.indexOf(":")+1), value);
            }
            child.addAttribute(key, value);
        });
        return child;
    }

    /**
     * 添加孩子节点元素
     *
     * @param parent     父节点
     * @param childName  孩子节点名称
     * @param childValue 孩子节点值
     * @param attributes 孩子节点属性
     * @return 新增节点
     */
    public static Element addChild(Element parent, String childName, String childValue, Map<String, String> attributes) {
        // 添加子节点元素
        Element child = parent.addElement(childName);
        // 为子节点元素设值
        child.setText(childValue == null ? "" : childValue);
        // 为子节点元素设属性
        if (MapUtils.isEmpty(attributes)) {
            return child;
        }
        attributes.forEach((key, value) -> {
            if (key.contains(":")) {
                //处理冒号属性问题
                //child.addNamespace(key.substring(key.indexOf(":")+1), value);
            }
            child.addAttribute(key, value);
        });
        return child;
    }

    /**
     * 将文档对象写入对应的文件中
     *
     * @param xmlstr 字符串
     * @param path     写入文档的路径
     * @throws IOException
     */
    public final static void writeXmlStrToFile(String xmlstr, String path) throws Exception {
        if (StringUtils.isBlank(xmlstr)) {
            return;
        }
        Document document = DocumentHelper.parseText(xmlstr);
        writeXMLToFile(document, path);
    }

    /**
     * 将文档对象写入对应的文件中
     *
     * @param document 文档对象
     * @param path     写入文档的路径
     * @throws IOException
     */
    public final static void writeXMLToFile(Document document, String path) throws IOException {
        if (document == null || path == null) {
            return;
        }
        XMLWriter writer = new XMLWriter(new FileWriter(path));
        writer.write(document);
        writer.close();
    }

    /**
     * 将文档对象写入对应的文件中
     *
     * @param document 文档对象
     * @param path     写入文档的路径
     * @throws IOException
     */
    public final static void writeXMLToFile(Document document, String path, String encoding) throws IOException {
        if (document == null || path == null) {
            return;
        }
        // 设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        encoding = StringUtils.isBlank(encoding) ? "UTF-8" : encoding;
        // 设置编码格式
        format.setEncoding(encoding);
        XMLWriter writer = new XMLWriter(new FileWriter(path), format);
        writer.write(document);
        writer.close();
    }

    /**
     * 通过文件的路径获取xml的document对象
     *
     * @param path 文件的路径
     * @return 返回文档对象
     */
    public static Document getXMLByFilePath(String path) {
        if (null == path) {
            return null;
        }
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 通过xml字符获取document文档
     *
     * @param xmlstr 要序列化的xml字符
     * @return 返回文档对象
     * @throws DocumentException
     */
    public static Document getXMLByString(String xmlstr) throws DocumentException {
        if (xmlstr == "" || xmlstr == null) {
            return null;
        }
        Document document = DocumentHelper.parseText(xmlstr);
        return document;
    }

    /**
     * 获取某个元素的所有的子节点
     *
     * @param node 制定节点
     * @return 返回所有的子节点
     */
    public static List<Element> getChildElements(Element node) {
        if (null == node) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Element> lists = node.elements();
        return lists;
    }

    /**
     * 获取指定节点的子节点
     *
     * @param node      父节点
     * @param childnode 指定名称的子节点
     * @return 返回指定的子节点
     */
    public static Element getChildElement(Element node, String childnode) {
        if (null == node || null == childnode || "".equals(childnode)) {
            return null;
        }
        return node.element(childnode);
    }

    /**
     * 获取所有的属性值
     *
     * @param node
     * @param arg
     * @return
     */
    public static Map<String, String> getAttributes(Element node, String... arg) {
        if (node == null || arg.length == 0) {
            return null;
        }
        Map<String, String> attrMap = new HashMap<String, String>();
        for (String attr : arg) {
            String attrValue = node.attributeValue(attr);
            attrMap.put(attr, attrValue);
        }
        return attrMap;
    }

    /**
     * 获取element的单个属性
     *
     * @param node 需要获取属性的节点对象
     * @param attr 需要获取的属性值
     * @return 返回属性的值
     */
    public static String getAttribute(Element node, String attr) {
        if (null == node || attr == null || "".equals(attr)) {
            return "";
        }
        return node.attributeValue(attr);
    }

    /**
     * DOM4j的Document对象转为XML报文串
     *
     * @param document
     * @param charset
     * @return 经过解析后的xml字符串
     */
    public static String documentToString(Document document, String charset) {
        StringWriter stringWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();// 获得格式化输出流
        format.setEncoding(charset);// 设置字符集,默认为UTF-8
        XMLWriter xmlWriter = new XMLWriter(stringWriter, format);// 写文件流
        try {
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }

    /**
     * 去掉声明头的(即<?xml...?>去掉)
     *
     * @param document
     * @param charset
     * @return
     */
    public static String documentToStringNoDeclaredHeader(Document document, String charset) {
        String xml = documentToString(document, charset);
        return xml.replaceFirst("\\s*<[^<>]+>\\s*", "");
    }

    /**
     * 解析XML为Document对象
     *
     * @param xml 被解析的XMl
     * @return Document
     */
    public final static Element parseXml(String xml) {
        StringReader sr = new StringReader(xml);
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(sr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = document != null ? document.getRootElement() : null;
        return rootElement;
    }

    /**
     * 获取element对象的text的值
     *
     * @param e   节点的对象
     * @param tac 节点的tac
     * @return
     */
    public final static String getText(Element e, String tac) {
        Element _e = e.element(tac);
        if (_e != null) {
            return _e.getText();
        } else {
            return null;
        }
    }

    /**
     * 获取去除空格的字符串
     *
     * @param e
     * @param tac
     * @return
     */
    public final static String getTextTrim(Element e, String tac) {
        Element _e = e.element(tac);
        if (_e != null) {
            return _e.getTextTrim();
        } else {
            return null;
        }
    }

    /**
     * 获取节点值.节点必须不能为空，否则抛错
     *
     * @param parent 父节点
     * @param tac    想要获取的子节点
     * @return 返回子节点
     */
    public final static String getTextTrimNotNull(Element parent, String tac) {
        Element e = parent.element(tac);
        if (e == null) {
            throw new NullPointerException("节点为空");
        } else {
            return e.getTextTrim();
        }
    }

    /**
     * 节点必须不能为空，否则抛错
     *
     * @param parent 父节点
     * @param tac    想要获取的子节点
     * @return 子节点
     */
    public final static Element elementNotNull(Element parent, String tac) {
        Element e = parent.element(tac);
        if (e == null) {
            throw new NullPointerException("节点为空");
        } else {
            return e;
        }
    }

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }
}
