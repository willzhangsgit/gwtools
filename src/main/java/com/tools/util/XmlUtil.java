package main.java.com.tools.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {
	
	@SuppressWarnings("rawtypes")
	public static String toXml(Object obj, Class T) {
		XStream xStream = new XStream();
		xStream.alias("xml", T);
		return xStream.toXML(obj).replaceAll("__", "_");
	}
	
	public static Element getRoot(String rtnXml) {
		try {
			Document xmldoc = DocumentHelper.parseText(rtnXml);
			Element root = xmldoc.getRootElement();
			return root;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getNodeValue(String rtnXml, String nodename) {
		Element root = getRoot(rtnXml);
		if(root!=null){
			String returnCode = root.element(nodename).getText();
			return returnCode;
		}else{
			return null;
		}
	}
}
