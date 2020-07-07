package main.java.com.Ent;

import main.java.com.tools.util.ObjUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ToolEnt {
	private static Logger logger = Logger.getLogger(ToolEnt.class);
	
	public ToolEnt() {
		// TODO Auto-generated constructor stub
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
	}
	
	public String test1(){
		logger.debug("catch method ToolEnt.test1");
		return ObjUtil.test();
	}
}
