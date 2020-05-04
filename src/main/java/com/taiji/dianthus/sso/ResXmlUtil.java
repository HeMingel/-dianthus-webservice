package com.taiji.dianthus.sso;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

public class ResXmlUtil {
    /**
     * 解析XML字符串
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> parseXmlStr(String xml)
            throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        return parseElement(root);
    }

    /**
     * 解析Element
     *
     * @param root
     * @return
     */
    private static Map<String, Object> parseElement(Element root) {
        Iterator<Element> rootItor = root.elementIterator();
        Map<String, Object> rMap = new HashMap<String, Object>();
        while (rootItor.hasNext()) {
            Element tmpElement = rootItor.next();
            String name = tmpElement.getName();
            if ("partTimes".toLowerCase().equals(name.toLowerCase())) {
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Element listElement = root.element("partTimes");
                Iterator<Element> listItor = listElement.elementIterator();
                while (listItor.hasNext()) {
                    Element ptElement = listItor.next();
                    Iterator<Element> ptItor = ptElement.elementIterator();
                    Map<String, String> partTime = new HashMap<String, String>();
                    while (ptItor.hasNext()) {
                        Element ptObjElement = ptItor.next();
                        String value = ptObjElement.getStringValue();
                        if ("null".equals(value)) {
                            value = null;
                        }
                        partTime.put(ptObjElement.getName().toLowerCase(), value);
                    }
                    list.add(partTime);
                }
                rMap.put("partTimes", list);
            } else {
                String value = tmpElement.getStringValue();
                if ("null".equals(value)) {
                    value = null;
                }
                rMap.put(name.toLowerCase(), value);
            }
        }
        return rMap;
    }

    /**
     * map拼接xml
     *
     * @return
     */
    public static String reqXml(Map map) {
        //拼接
        StringBuilder reqXml = new StringBuilder();
        reqXml.append("<xml>");
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = it.next();
            Object value = map.get(key);
            reqXml.append("<").append(key).append(">");
            reqXml.append(value);
            reqXml.append("</").append(key).append(">");
        }
        reqXml.append("</xml>");
        return reqXml.toString();
    }

    ;

    /**
     * 解析Element
     * 多附件专用
     *
     * @param xml
     * @return
     */
    public static Map<String, Object> parseElementAppFiles(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> fileList = root.elements("file");
        Map<String, Object> rMap = new HashMap<String, Object>();
        Iterator<Element> rootItor = null;
        for (Element e : fileList) {
            rootItor = e.elementIterator();
            String name = "";
            String content = "";
            while (rootItor.hasNext()) {
                Element tmpElement = rootItor.next();
                if ("fileName".toLowerCase().equals(tmpElement.getName().toLowerCase())) {
                    name = tmpElement.getStringValue();
                } else if ("fileContent".toLowerCase().equals(tmpElement.getName().toLowerCase())) {
                    content = tmpElement.getStringValue();
                }

            }
            rMap.put(name.toLowerCase(), content);
        }

        return rMap;
    }
}
