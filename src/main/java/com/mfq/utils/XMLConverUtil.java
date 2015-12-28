package com.mfq.utils;

import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * XML 数据接收对象转换工具类
 * 
 * @author hui
 *
 */
public class XMLConverUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(XMLConverUtil.class);

    /**
     * XML(SOAP) to Map
     * 
     * @param xml
     * @return
     */
    public static Map<String, Object> convertSoapToMap(String xml) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            map = readSoap(nodeElement);
        } catch (Exception e) {
            logger.error("convertSoapToMap_Exception", e);
        }
        logger.info("soap2map ret:{}", map);
        return map;
    }

    public static Map<String, Object> readSoap(Element element) {
        Map<String, Object> map = Maps.newHashMap();
        if (element == null || element.elements() == null
                || element.elements().size() == 0) {
            logger.warn("element label:{}, value:{}", element.getName(),
                    element.getText());
            return null;
        }
        @SuppressWarnings("unchecked")
        Iterator<Element> iter = element.elementIterator();
        while(iter.hasNext()) {
            Element ele = iter.next();
            map.put(ele.getName(), ele.getText().trim());
            if (ele.elements() != null && ele.elements().size() != 0) {
                readSoap(ele);
            }
        }
        return map;
    }
    

    public static String writeObj2Xml(Object xmlObj){
    	 
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xStreamForRequestPostData.autodetectAnnotations(true);
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String dataXML = xStreamForRequestPostData.toXML(xmlObj);
        return dataXML;
    }
    

    public static void main(String[] args) {
//    	ResponseData resp = new  ResponseData();
//    	resp.setReturn_code("SUCCESS");
//        resp.setReturn_msg("OK");
//    	String yyy = writeObj2Xml(resp);
//    	System.out.println(yyy);
	}
}