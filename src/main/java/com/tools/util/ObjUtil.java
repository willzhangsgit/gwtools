package main.java.com.tools.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class ObjUtil {
	
	public static Object RsToObject(ResultSet rs, Class<?> obj) throws Exception {
		ResultSetMetaData rsm = rs.getMetaData();
		Object entity = obj.newInstance();
		for (int i = 1; i <= rsm.getColumnCount(); i++) 
		{
			String colName = rsm.getColumnName(i);
			String methodName = "set" + colName.substring(0,1).toUpperCase() + colName.substring(1).toUpperCase();
			Field field = null;
			try
			{
				field=obj.getDeclaredField(colName.toUpperCase()); 
			}catch(Exception e)
			{
				continue;
			}	
			
			if(field.getType().equals(String.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {String.class});
				method.invoke(entity, new Object[] {rs.getString(colName) });
			}
			else if(field.getType().equals(int.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {int.class});
				Integer va = null;
				try
				{
					 va = new Integer(rs.getString(colName));
					 method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(Integer.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Integer.class});
				Integer va = null;
				try
				{
					va = new Integer(rs.getString(colName));
					method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(long.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {long.class});
				Long va = null;
				try
				{
					 va = new Long(rs.getString(colName));
					 method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(Long.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Long.class});
				Long va = null;
				try
				{
					va = new Long(rs.getString(colName));
					method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(double.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {double.class});
				Double va = null;
				try
				{
					va = new Double(rs.getString(colName));
					method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(Float.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Float.class});
				Float va = null;
				try
				{
					va = new Float(rs.getString(colName));
					method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(Double.class))
			{
				Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Double.class});
				Double va = null;
				try
				{
					va = new Double(rs.getString(colName));
					method.invoke(entity, new Object[] {va});
				}catch(Exception e)
				{
					va = null;
				}
			}
			else if(field.getType().equals(Timestamp.class))
			{
				try {
					Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Timestamp.class});
					method.invoke(entity, new Object[] {rs.getTimestamp(colName) });
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(field.getType().equals(Date.class))
			{
				try {
					String st = rs.getString(colName);
					Date de = null;
					if(st!=null)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						de = sdf.parse(st);	
					}
					Method method = entity.getClass().getDeclaredMethod(methodName, new Class[] {Date.class});
					method.invoke(entity, new Object[] {de});
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(field.getType().equals(Clob.class))
			{
				
			}
		}
		return entity;
	}
	
	public static String test(){
		return "gw test it";
	}
}
